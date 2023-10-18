package org.example.server;

import org.example.client.ChatClientGUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ChatServer implements ChatServerView{
    public String serverStatus, message;
    ChatServerGUI chatServerGUI;
    public ChatServer(ChatServerGUI sg){
        chatServerGUI = sg;
    }
    @Override
    public void updatetxt(String msg) {
        chatServerGUI.textArea.append(msg);
        Logging(msg);
    }
    @Override
    public void clientLogin(ChatClientGUI cg, String IP, String Port, String NickName, String password) {
        if (serverStatus.equals(SERVER_STOPPED)) {
            cg.updateSRV("Can't connect to server: " + ServerName + "\n");
        }
        else if (IP.isEmpty() || Port.isEmpty() ||
                NickName.isEmpty() || password.isEmpty()) {
            cg.updateSRV(ServerName + "Connection failed\n");
        }
        else {
            cg.ChangeConnectionStatus(true);
            cg.updateSRV( "Connection successful\n");
            sendStory(cg);
            message = NickName + ": joined to chat\n";
            updatetxt(message);
            cg.updateSRV(message);
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
    public void sendStory(ChatClientGUI cg) {
        try (BufferedReader br = new BufferedReader( new FileReader("log.txt")))
        {
            while (br.readLine() != null)
                cg.updateSRV(br.readLine() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
