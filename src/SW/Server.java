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
    public static boolean canConnection = true;
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
    public void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(1111);
            while (true) {
                //4명의 유저를 받는다.
                    System.out.println("[Server] Wait until client come...");
                    socket = serverSocket.accept();
                    System.out.println("[server] New client connected");
                    ServerThread serverthread = new ServerThread(socket);
                    serverthread.start();
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

//public class GameStart() {
//
//    public void start() {
//        System.out.println("게임 시작");
//        sendAll("게임 시작합니다. 5초만 기다려 주세요");
//        sendAll("이번 " + currentRound + " 에 경매할 카드는 + " + currentCard + " 입니다!");
//        sendAll("입찰 여부를 10초 동안 결정해주세요!");
//    }
//
//    {
//        inFromClient.readLine();
//        while (inFromClient != null) {
//            String data = inFromClient.readLine();
//            System.out.println(data);
//        }
//    }
//}

//    GameStart gameStart = new GameStart();
//            gameStart.start();
//
//                    try {
//                    serverSocket = new ServerSocket(1111);
//
//                    while (true) {
//                    System.out.println("[Game] Wait until request come...");
//                    socket = serverSocket.accept();
//                    System.out.println("[Game] New request received");
//                    GameThread gameThread = new GameThread(socket);
//                    gameThread.start();
//                    }
//                    }

class ServerThread extends Thread {
    int count = 0;
    int userNum = 0;
    static HashMap<PrintWriter, Integer> userConnectionInfo = new HashMap<PrintWriter, Integer>(4);
    static HashMap<String, Integer> userInfo = new HashMap<String, Integer>(4);
    private Socket socket = null;
    BufferedReader inFromClient = null;
    PrintWriter outToClient = null;

    public ServerThread(Socket socket) {

        this.socket = socket;
//        try {
//            outToClient = new PrintWriter(socket.getOutputStream());
//            inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            userConnectionInfo.put(outToClient, userNum);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    @Override
    public synchronized void run() {
        String request = "";
        String requestMessage = "";
        String responseMessage = "";
        try {
            //client의 요청을 기다림
            requestMessage = inFromClient.readLine();
            // userConnection/류관곤/현재 금액/입찰 금액/
            //요청을 쪼개주세요.
            //switch case로 요청에 해당하는 처리를 만들어.
            if(count == 4) {
                System.out.println("접속 불가");
                return;
            }
            //chatThread 만들어.
            while(!request.equals("end")){
                switch(request){
                    case "userConnection": {
                        count++;
                        //User의 정보를 받아서
                        //4명이 요청이 올때까지 기다려.
                        while(count != 4){}
                        //4명이 접속했다는 요청을 클라이언트에게 보내
                        responseMessage = "류관곤/이승원/이한슬/이은섭";
                    }
                    case "낙찰!!" : {

                    }
                    case "유찰!!" :{

                    }
                    default: break;
                }
            }

//            userInfo.put(requestMessage, userNum);
//            System.out.println("[Server] Create new connection");
//            sendAll("[" + name + "] has entered the room");

            /*
            if(userNum >= 1){
                sendAll("모든 유저가 접속했습니다");
                //this.GameStart();
            }

             */
            //sendAll("모든 유저가 접속했습니다");

//            while (inFromClient != null) {
//
//                String string = inFromClient.readLine();
//                if ("chat : ".equals(string.substring(0, 7))) {
//                    if ("chat : 게임 서버 접속".equals(string)) {
//                        System.out.println("유저 접속");
//                        continue;
//                    }
//                    sendAll(name + " : " + string.substring(7) + "");
//                } else if ("data : ".equals(string.substring(0, 7))) {
//
//                    if ("data : bid".equals(string)) {
//                    }
//                    System.out.println(name + " : " + string.substring(7) + "");
//                }
//
//            }
            //sendAll(name + " : " + chatting + "");
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