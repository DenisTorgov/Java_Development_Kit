package org.example.client;

import org.example.server.ChatServer;
import org.example.server.ChatServerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class ChatClientGUI extends JFrame implements ClientView{
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private static final String DEFAULT_SERVER = "127.0.0.1";
    private static final String DEFAULT_PORT = "12345";
    private static final String DEFAULT_NICKNAME = "Your Name";
    public String NickName;
    JTextArea clientText;
    ChatClient cc;
    ChatServerGUI csg;

    public ChatClientGUI(ChatServerGUI chatServerGUI) {
        csg = chatServerGUI;
        cc = new ChatClient(csg, this);
        cc.connectionStatus = DISCONNECTED;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(csg.getWidth() + 50, csg.getY());
        setTitle("Chat client");
        setResizable(true);
        add(createClientTopButtons(), BorderLayout.NORTH);
        add(createClientBottomButtons(), BorderLayout.SOUTH);
        clientText = createClientText();
        clientText.setLineWrap(true);
//        JScrollPane scroll = new JScrollPane(clientText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        scroll.setBounds(10, 10, 200, 200);
//        add(scroll);
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
                cc.updatetxt(textSend.getText());
            }
        });
        textSend.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    cc.updatetxt(textSend.getText());
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
        panelLayer1.add(new JButton());
        panelLayer2.add(textNickName);
        panelLayer2.add(password);
        panelLayer2.add(btnLogin);

        topPanel.add(panelLayer1);
        topPanel.add(panelLayer2);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NickName = textNickName.getText();
                cc.login(csg, textIP.getText(), textPort.getText(), textNickName.getText(), password.getText());
            }
        });
        return topPanel;
    }
    private JTextArea createClientText() {
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        return ta;
    }
    @Override
    public void updatetxt(String msg) {
        cc.updatetxt(msg);
    }
    @Override
    public void updateSRV(String msg) {
        clientText.append(msg);
    }
    @Override
    public void ChangeConnectionStatus(boolean b) {
        cc.ChangeConnectionStatus(b);
    }
}
