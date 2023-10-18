package org.example.server;

import org.example.client.ChatClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatServerGUI extends JFrame implements ChatServerView{
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    ChatClientGUI chatClientGUI;
    ChatServer chatServer;
    JButton btnServStart, btnServStop;
    JTextArea textArea;

    public ChatServerGUI(){
        chatServer = new ChatServer(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Server");
        setResizable(true);
        add(createServBottoms(), BorderLayout.SOUTH);
        textArea = createTextArea();
        add(textArea);

        chatServer.serverStatus = SERVER_STOPPED;
        setVisible(true);
        chatClientGUI = new ChatClientGUI(this);
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
                chatServer.serverStatus = SERVER_WORK;
                updatetxt(chatServer.serverStatus + "\n");
            }
        });
        btnServStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatServer.serverStatus = SERVER_STOPPED;
                updatetxt(chatServer.serverStatus + "\n");
                chatClientGUI.updateSRV("Server disconnected\n");
                chatClientGUI.ChangeConnectionStatus(false);
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
    @Override
    public void updatetxt(String msg) {
        chatServer.updatetxt(msg);
    }
    @Override
    public void clientLogin(ChatClientGUI cg, String IP, String Port, String NickName, String password) {
        chatServer.clientLogin(cg, IP, Port, NickName, password);
    }
}
