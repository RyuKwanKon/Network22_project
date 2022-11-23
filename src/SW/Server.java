package SW;

import java.io.*;
import java.net.*;
import java.util.*;

import static SW.Server.count;

class Ex {
    public BufferedReader inFromClient = null;
    public PrintWriter outToClient = null;
    public int currentRound = 0;
    public String currentCard = null;

    public void sendAll(HashMap<PrintWriter, String> userConnectionList, String msg) {
        for (PrintWriter out : userConnectionList.keySet()) {
            out.println(msg);
            out.flush();
        }
    }
//    private void sendAll(String s) {
//        for (PrintWriter out : userConnectionInfo.keySet()) {
//            out.println(s);
//            out.flush();
//        }
//    }
}

public class Server {
    public static boolean canConnection = true;
    public static int count = 0;
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
    int userNum = 0;
    //static HashMap<PrintWriter, Integer> userConnectionInfo = new HashMap<PrintWriter, Integer>(4);
    //static ArrayList<String> userNameList = new ArrayList<String>(4);
    static HashMap<PrintWriter, String> userConnectionList = new HashMap<PrintWriter, String>(4);
    private Socket socket = null;
    BufferedReader inFromClient = null;
    PrintWriter outToClient = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
        try {
            outToClient = new PrintWriter(socket.getOutputStream());
            inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("연결 오류");
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void run() {
        String requestUserName = "";
        String requestMessage = "";
        String responseMessage = "";
        try {
            //client의 요청을 기다림
            while (inFromClient != null) {
                requestMessage = inFromClient.readLine();
                System.out.println(requestMessage);
                if (count == 5) {
                    System.out.println("접속 불가");
                    return;
                }
                String[] splitMessage = requestMessage.split("/");
                requestUserName = splitMessage[1];
                // userConnection/류관곤/현재 금액/입찰 금액/
                //요청을 쪼개주세요.
                //switch case로 요청에 해당하는 처리를 만들어.
                //chatThread 만들어.
                if (splitMessage[0].equals("end")) return;
                switch (splitMessage[0]) {
                    case "userConnection": {    //userConnection/UserName;
                        System.out.println(splitMessage[1]);
                        count++;
                        //userConnectionList.put(outToClient, splitMessage[1]);
                        //User의 정보를 받아서
                        //4명이 요청이 올때까지 기다려.
                        System.out.println(count);
                        try{
                            if(count != 4) wait();
                            else if(count == 4){
                                notifyAll();
                            }
                        }catch (Exception error){}
                        outToClient.println("200/gameStart");
                        outToClient.flush();
                        System.out.println(count + "game start");
                        //4명이 접속했다는 요청을 클라이언트에게 보내
//                        ChatThread chatThread = new ChatThread(userConnectionList);
//                        chatThread.start();
                        break;
                    }
                    case "successBid": {
                        //[Server -> Client] Thread도 있어야 할 것 같은데 모르겠다
                        //낙찰/유저이름/트럼프카드/금액
                        //낙찰된 유저의 출력 스트림 & 이름 저장

//                            PrintWriter dataToClient = null;
//                            for(Map.Entry<PrintWriter, String> entry: userConnectionList.entrySet()){
//                                if(entry.getValue().equals(splitMessage[1])){
//                                    dataToClient = entry.getKey();
//                                }
//                            }
                        // 낙찰된 유저에게 낙찰 메시지 전송
//                            dataToClient.println("낙찰");
//                            dataToClient.flush();
                        outToClient.println("낙찰");
                        outToClient.flush();
                        // 모든 유저들에게 낙찰 공지
                        ChatThread chatThread = new ChatThread(userConnectionList, "익명의 참가자가" + splitMessage[3] +
                                "원으로" + splitMessage[2] + "를 낙찰받았습니다!");
                        chatThread.start();
                    }
                    case "noBid": {
                        //아무도 입찰 안함
                        ChatThread chatThread = new ChatThread(userConnectionList, "아무도 응찰하지 않아" + splitMessage[2] + "는 유찰되었습니다!");
                        chatThread.start();
                    }
                    case "chat": {
                        // 채팅 쓰레드 - ChatThread
                        // chat/msg
                        ChatThread chatThread = new ChatThread(userConnectionList, splitMessage[1]);
                        chatThread.start();
                    }
                    case "win": {
                        // 조합 완성시에
                        // win/유저이름
//                            PrintWriter dataToClient = null;
//                            for(Map.Entry<PrintWriter, String> entry: userConnectionList.entrySet()){
//                                if(entry.getValue().equals(splitMessage[1])){
//                                    dataToClient = entry.getKey();
//                                }
//                            }
                        outToClient.println("승리");
                        outToClient.flush();
//                            dataToClient.println("승리");
//                            dataToClient.flush();

                        ChatThread chatThread = new ChatThread(userConnectionList, "참가자" + splitMessage[1] + "" +
                                "이(가) 가장 먼저 조합을 완성해 우승하였습니다!");
                        chatThread.start();
                    }
                    default:
                        break;
                }
            }
//            } catch(IOException e){
//                //e.printStackTrace();
//                System.out.println("[" + requestMessage + "] is disconnected");
//            } finally{
//                sendAll("[" + requestUserName + "] has left the game");
//                userConnectionList.remove(outToClient);
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            System.out.println("[" + requestUserName + " terminate connection]");
        } catch (IOException e) {
            throw new RuntimeException(e);
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


//        private void sendAll (String s){
//            for (PrintWriter out : userConnectionList.keySet()) {
//                out.println(s);
//                out.flush();
//            }
//        }
    }

    class ChatThread extends Thread {
        HashMap<PrintWriter, String> userConnectionList = new HashMap<PrintWriter, String>(4);
        String msg = "";

        public ChatThread(HashMap userConnectionList) {
            this.userConnectionList = userConnectionList;
            msg += "Player is : ";
            for (Object out : userConnectionList.values()) {
                msg += out;
            }
        }

        public ChatThread(HashMap userConnectionList, String msg) {
            this.userConnectionList = userConnectionList;
            this.msg.concat(msg);
        }

        public void run() {

            sendAll(userConnectionList, msg);
        }

        public void sendAll(HashMap<PrintWriter, String> userConnectionList, String msg) {
            for (PrintWriter out : userConnectionList.keySet()) {
                out.println(msg);
                out.flush();
            }
        }
    }
}
