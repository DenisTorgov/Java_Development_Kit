package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ChatServer extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private static final String SERVER_STOPPED = "Server stopped";
    private static final String SERVER_WORK = "Server is working";
    private static final String NickName = "SERVER";
    public String serverStatus, message;
    JButton btnServStart, btnServStop;
    JTextArea textArea;
    ChatClient chatClient;
//    FileWriter fw;
    ChatServer(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Server");
        setResizable(true);
        add(createServBottoms(), BorderLayout.SOUTH);
        textArea = createTextArea();
        add(textArea);


        serverStatus = SERVER_STOPPED;
        setVisible(true);
        chatClient = new ChatClient(this);
    }

    private Component createServBottoms() {
        JPanel servBottons = new JPanel(new GridLayout(1, 2));
        btnServStart = new JButton("Start");
        btnServStop = new JButton("Stop");
        servBottons.add(btnServStart);
        servBottons.add(btnServStop);
        btnServStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverStatus = SERVER_WORK;
                update(serverStatus);
            }
        });
        btnServStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverStatus = SERVER_STOPPED;
                update(serverStatus + "\n");
                chatClient.update("Server disconnected\n");
                chatClient.connectionStatus = chatClient.DISCONNECTED;
            }
        });
        return servBottons;
    }
    private JTextArea createTextArea() {
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        ta.setText("Server stopped\n");
        return ta;
    }
    protected void update(String msg) {
        textArea.append(msg);
        Logging(msg);
    }
    public void clientLogin(String IP, String Port, String NickName, String password) {
        if (serverStatus == SERVER_STOPPED) {
            chatClient.updateSRV("Can\'t connect to server");
        }
        else if (IP.isEmpty() || Port.isEmpty() ||
                NickName.isEmpty() || password.isEmpty()) {
            chatClient.updateSRV("Connection failed");
        }
        else {
            chatClient.connectionStatus = chatClient.CONNECTED;
            chatClient.updateSRV( "Connection successful");
            sendStory();
            message = NickName + ": joined to chat";
            update(message + "\n");
            chatClient.updateSRV(message);
            Logging(message);
        }
    }
    public void Logging (String msg) {
        try (FileWriter fw = new FileWriter("log.txt", true))
        {
            fw.append(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendStory() {
        try (BufferedReader br = new BufferedReader( new FileReader("log.txt")))
        {
            while (br.readLine() != null)
                chatClient.updateSRV(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
