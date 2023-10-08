package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatClient extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private static final String DEFAULT_SERVER = "127.0.0.1";
    private static final String DEFAULT_PORT = "12345";
    private static final String DEFAULT_NICKNAME = "Your Name";
    protected static final String CONNECTED = "Connected";
    protected static final String DISCONNECTED = "Disconnected";
    public String connectionStatus, NickName, message;
    JTextArea clientText;
    ChatServer cs;
    ChatClient(ChatServer chatServer) {
        cs = chatServer;
        connectionStatus = DISCONNECTED;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(cs.getWidth() + 50, cs.getY());
        setTitle("Chat client");
        setResizable(true);
        add(createClientTopButtons(), BorderLayout.NORTH);
        add(createClientBottomButtons(), BorderLayout.SOUTH);
        clientText = createClientText();
        add(clientText);
        setVisible(true);
    }
    private Component createClientBottomButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JTextField textSend = new JTextField(100);
        JButton btnSend = new JButton("Send");
        panel.add(textSend);
        panel.add(btnSend);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (connectionStatus == DISCONNECTED) {
                    update("No connection to server\n");
                } else {
                    message = NickName + ": " + textSend.getText() + "\n";
                    update(message);
                    cs.update(message);
                }
            }
        });

        return panel;
    }
    private Component createClientTopButtons() {
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JPanel panelLayer1 = new JPanel(new GridLayout(1, 3));
        JPanel panelLayer2 = new JPanel(new GridLayout(1, 3));
        JTextField textIP = new JTextField(DEFAULT_SERVER);
        JTextField textPort = new JTextField(DEFAULT_PORT);
        JTextField textNickName = new JTextField(DEFAULT_NICKNAME);
        JPasswordField password = new JPasswordField();
        JButton btnLogin = new JButton("Login");
        panelLayer1.add(textIP);
        panelLayer1.add(textPort);
        panelLayer2.add(textNickName);
        panelLayer2.add(password);
        panelLayer2.add(btnLogin);

        topPanel.add(panelLayer1);
        topPanel.add(panelLayer2);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NickName = textNickName.getText();
                login(cs, textIP.getText(), textPort.getText(), textNickName.getText(), password.getText());
            }
        });
        return topPanel;
    }
    private void login(ChatServer cs, String IP, String Port, String NickName, String password) {
        cs.clientLogin(IP, Port, NickName, password);
    }
    private JTextArea createClientText() {
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        return  ta;
    }
//    public void update(JTextArea showToClient, String msg) {
//        showToClient.append(msg +"\n");
//    }
//    public void update(JTextArea showToClient, String client, String msg) {
//        showToClient.append(client + ": " + msg +"\n");
//    }
    public void update(String msg) {
        clientText.append(msg);
    }
}
