package ClientThread;

import Page.GamePagePanel.UserChat;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static Frame.Frame.client;
import static Page.GamePageView.currentChatting;

public class ClientConnect {
    private Socket socket;
    private BufferedReader inMsg;
    private PrintWriter outMsg;
    public void connectServer(){
        try{
            this.socket = new Socket("127.0.0.1", 1111);
            System.out.println("[Client]Server 연결 성공!!");
            this.inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outMsg = new PrintWriter(socket.getOutputStream(), true);
        }catch (Exception e){}
    }
    public BufferedReader getInMsg(){
        return inMsg;
    }
    public PrintWriter getOutMsg(){
        return outMsg;
    }
    public Socket getSocket(){
        return socket;
    }
}
