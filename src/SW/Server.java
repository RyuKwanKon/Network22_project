package SW;

import java.io.*;
import java.net.*;
import java.util.*;

class Ex_variable{
    public static HashMap<PrintWriter, String> userConnectionInfo = new HashMap<PrintWriter, String>(4);
    public static HashMap<PrintWriter, >
    public BufferedReader inFromClient = null;
    public PrintWriter outToClient = null;
    public int currentRound = 0;
    public String currentCard = null;
}

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        int i = 1;
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(1111);

            while (i <= 4) {
                System.out.println("[Server] Wait until client come...");
                socket = serverSocket.accept();
                System.out.println("[server] New client connected");
                ServerThread serverthread = new ServerThread(socket, i++);
                serverthread.start();
            }
            GameStart gameStart = new GameStart();
            gameStart.start();

            try {
                serverSocket = new ServerSocket(1111);

                while (true) {
                    System.out.println("[Game] Wait until request come...");
                    socket = serverSocket.accept();
                    System.out.println("[Game] New request received");
                    GameThread gameThread = new GameThread(socket);
                    gameThread.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    System.out.println("[Server] Server closed");

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("[Server] Server error");
                }
            }
        }
    }
}

public class GameStart() {

    public void start() {
        System.out.println("게임 시작");
        sendAll("게임 시작합니다. 5초만 기다려 주세요");
        sendAll("이번 " + currentRound + " 에 경매할 카드는 + " + currentCard + " 입니다!");
        sendAll("입찰 여부를 10초 동안 결정해주세요!");
    }

    {
        inFromClient.readLine();
        while (inFromClient != null) {
            String data = inFromClient.readLine();
            System.out.println(data);
        }
    }
}

class ServerThread extends Thread {
    int userNum = 0;
    static HashMap<PrintWriter, Integer> userConnectionInfo = new HashMap<PrintWriter, Integer>(4);
    static HashMap<String, Integer> userInfo = new HashMap<String, Integer>(4);
    private Socket socket = null;
    BufferedReader inFromClient = null;
    PrintWriter outToClient = null;

    public ServerThread(Socket socket, int i) {

        this.socket = socket;
        this.userNum = i;

        try {
            outToClient = new PrintWriter(socket.getOutputStream());
            inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            userConnectionInfo.put(outToClient, userNum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void run() {
        String name = "";
        try {
            name = inFromClient.readLine();

            userInfo.put(name, userNum);
            System.out.println("[Server] Create new connection");
            sendAll("[" + name + "] has entered the room");

            /*
            if(userNum >= 1){
                sendAll("모든 유저가 접속했습니다");
                //this.GameStart();
            }

             */
            //sendAll("모든 유저가 접속했습니다");

            while (inFromClient != null) {

                String string = inFromClient.readLine();
                if ("chat : ".equals(string.substring(0, 7))) {
                    if ("chat : 게임 서버 접속".equals(string)) {
                        System.out.println("유저 접속");
                        continue;
                    }
                    sendAll(name + " : " + string.substring(7) + "");
                } else if ("data : ".equals(string.substring(0, 7))) {

                    if ("data : bid".equals(string)) {
                    }
                    System.out.println(name + " : " + string.substring(7) + "");
                }

                //sendAll(name + " : " + chatting + "");
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("[" + name + "] is disconnected");
        } finally {
            sendAll("[" + name + "] has left the game");
            userConnectionInfo.remove(outToClient);
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[" + name + " terminate connection]");
    }

    private void sendAll(String s) {
        for (PrintWriter out : userConnectionInfo.keySet()) {
            out.println(s);
            out.flush();
        }
    }
}

class GameThread extends Thread {
    Socket socket = null;

    public GameThread(Socket socket) {

    }
}