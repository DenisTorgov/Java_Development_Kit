package org.example.server;

import org.example.client.ChatClientGUI;

public interface ChatServerView {
    String SERVER_STOPPED = "Server stopped";
    String SERVER_WORK = "Server is working";
    String ServerName = "SERVER";
    void updatetxt(String msg);
    void clientLogin(ChatClientGUI cg, String IP, String Port, String NickName, String password);

}
