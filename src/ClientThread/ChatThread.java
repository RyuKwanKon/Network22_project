package ClientThread;

import Page.GamePagePanel.OnChat;
import Page.GamePagePanel.UserChat;
import Page.GamePagePanel.UserCurrentText;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static Page.GamePagePanel.OnChat.input;
import static Page.GamePageView.currentChatting;

public class ChatThread extends Thread {
    Socket socket = null;
    private JTextField chat;
    Scanner scanner = new Scanner(System.in);

    public ChatThread( Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println(input.getText());
            PrintWriter outMsg = new PrintWriter(socket.getOutputStream(), true);
            outMsg.println();
        } catch (Exception e) {
        }
    }
}