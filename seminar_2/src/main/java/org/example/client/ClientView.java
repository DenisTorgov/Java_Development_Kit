package org.example.client;

public interface ClientView {
    String CONNECTED = "Connected";
    String DISCONNECTED = "Disconnected";

    void updatetxt(String msg);
    void updateSRV(String msg);
    void ChangeConnectionStatus(boolean b);
}
