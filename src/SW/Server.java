package SW;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Server {
    private UserData userData;
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
    public Server(){
        this.userData = new UserData();
    }

    public void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(1111);
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(new ServerTime(), 0, 1, TimeUnit.SECONDS);
            while (true) {
                //4명의 유저를 받는다.
                System.out.println("[Server] Wait until client come...");
                socket = serverSocket.accept();
                System.out.println("[server] New client connected");
                if (userData.count == 4) {
                    System.out.println("접속 불가");
                    continue;
                }
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

class ServerThread extends Thread {
    private UserData userData;
    private ServerData serverData;
    private Socket socket = null;
    BufferedReader inFromClient = null;
    PrintWriter outToClient = null;

    public ServerThread(Socket socket) {
        this.serverData = new ServerData();
        this.userData = new UserData();
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
        try {
            //client의 요청을 기다림
            while (inFromClient != null) {
                requestMessage = inFromClient.readLine();
                System.out.println(requestMessage);
                String[] splitMessage = requestMessage.split("/");
                requestUserName = splitMessage[1];
                // userConnection/류관곤/현재 금액/입찰 금액/
                if (splitMessage[0].equals("end")) return;
                switch (splitMessage[0]) {
                    case "userConnection": {
                        userData.count++;
                        System.out.println(splitMessage[1]);
                        userData.userConnectionList.put(splitMessage[1], outToClient);
                        userData.userAccount.put(splitMessage[1], 100);
                        userData.nameList.add(splitMessage[1]);
                        while(userData.count < 4){wait(1);}
                        String userNameList = "";
                        String result;
                        for(String e : userData.nameList){userNameList = userNameList.concat("/"+e);}
                        outToClient.println("200/gameStart/"+splitMessage[1] + "/100" +userNameList);
                        outToClient.flush();
                    }
                    break;
                    case "UserChat": {
                        String message = splitMessage[1] + ": " + splitMessage[2];
                        ChatThread chatThread = new ChatThread("200/UserChat/" + message);
                        chatThread.start();
                    }
                    break;
                    case "RegisterBid": {
                        userData.registerBid(splitMessage[1]);
                        serverData.AuctionRemainTime = 5;
                    }
                    break;
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
                        ChatThread chatThread = new ChatThread("익명의 참가자가" + splitMessage[3] +
                                "원으로" + splitMessage[2] + "를 낙찰받았습니다!");
                        chatThread.start();
                    }
                    case "noBid": {
                        //아무도 입찰 안함
                        ChatThread chatThread = new ChatThread("아무도 응찰하지 않아" + splitMessage[2] + "는 유찰되었습니다!");
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

                        ChatThread chatThread = new ChatThread("참가자" + splitMessage[1] + "" +
                                "이(가) 가장 먼저 조합을 완성해 우승하였습니다!");
                        chatThread.start();
                    }
                    default:
                        break;
                }
            }
            System.out.println("[" + requestUserName + " terminate connection]");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
