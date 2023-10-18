package org.example.client;

import org.example.server.ChatServerGUI;

public class ChatClient implements ClientView{
    public String connectionStatus, message;
    ChatClientGUI cg;
    ChatServerGUI csg;
    public ChatClient(ChatServerGUI chatServerGUI, ChatClientGUI chatClientGUI) {
        csg = chatServerGUI;
        cg = chatClientGUI;
    }
    public void login(ChatServerGUI csg, String IP, String Port, String NickName, String password) {
        csg.clientLogin(cg, IP, Port, NickName, password);
    }

    public void updatetxt(String msg) {
        message = cg.NickName + ": " + msg + "\n";
        cg.clientText.append(message);
        if (connectionStatus.equals(DISCONNECTED)) {
            cg.clientText.append("No connection to server\n");
        } else {
            csg.updatetxt(message);
        }
    }
    public void updateSRV(String msg) {
        cg.clientText.append(msg);
    }

    @Override
    public void ChangeConnectionStatus(boolean b) {
        if (b) {
            connectionStatus = ClientView.CONNECTED;
        } else {
            connectionStatus = ClientView.DISCONNECTED;
        }
    }
}
