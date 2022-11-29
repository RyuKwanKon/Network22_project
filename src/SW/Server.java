package SW;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Server {
    private UserData userData;
    public int socketCount = 0;
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public Server() {
        this.userData = new UserData();
    }

    public void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(1111);
//            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//            service.scheduleAtFixedRate(new ServerTime(), 0, 1, TimeUnit.SECONDS);
            while (true) {
                //4명의 유저를 받는다.
                System.out.println("[Server] Wait until client come...");
                if(socketCount == 4){
                    GameThread gameThread = new GameThread();
                    gameThread.start();
                    //System.out.println("아니 왜 안켜지냐고 ㅡㅡ");
                }
                socket = serverSocket.accept();
                System.out.println("[server] New client connected");
                if (userData.count == 4) {
                    System.out.println("접속 불가");
                    continue;
                }
                ServerThread serverthread = new ServerThread(socket);
                serverthread.start();
                socketCount++;

            }
        } catch (IOException e) {
            System.out.println("[Server] User disconnection");
            //e.printStackTrace();
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

    String[] splitMessage = null;
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
                splitMessage = requestMessage.split("/");
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
                        userData.userDeckList.put(splitMessage[1], "");
//                        if (userData.count == 4) {
//                            GameThread gameThread = new GameThread();
//                            gameThread.start();
//                            System.out.println("아니 왜 안켜지냐고 ㅡㅡ");
//                        }
                        while (userData.count < 4) {
                            wait(1);
                        }
                        String userNameList = "";
                        String result;
                        for (String e : userData.nameList) {
                            userNameList = userNameList.concat("/" + e);
                        }
                        outToClient.println("200/gameStart/Server/" + splitMessage[1] + "/100" + userNameList);
                        outToClient.flush();
                    }
                    break;
                    case "UserChat": {
                        String message = splitMessage[1] + ": ";
                        for (int i = 2; i < splitMessage.length; i++) {
                            message += splitMessage[i];
                        }
                        //for(String msg : splitMessage)
                        ChatThread chatThread = new ChatThread("200/UserChat/Chat/" + message);
                        chatThread.start();
                    }
                    break;
                    case "RegisterBid": {
                        System.out.println("qwe");
                        if(ServerData.auctionState == false) break;
                        userData.registerBid(splitMessage[1]);
                        serverData.auctionRemainTime = 5;
                        break;
                    }
//                    break;
//                    case "successBid": {
//                        //[Server -> Client] Thread도 있어야 할 것 같은데 모르겠다
//                        //낙찰/유저이름/트럼프카드/금액
//                        //낙찰된 유저의 출력 스트림 & 이름 저장
////                            PrintWriter dataToClient = null;
////                            for(Map.Entry<PrintWriter, String> entry: userConnectionList.entrySet()){
////                                if(entry.getValue().equals(splitMessage[1])){
////                                    dataToClient = entry.getKey();
////                                }
////                            }
//                        // 낙찰된 유저에게 낙찰 메시지 전송
////                            dataToClient.println("낙찰");
////                            dataToClient.flush();
//                        outToClient.println("낙찰");
//                        outToClient.flush();
//                        // 모든 유저들에게 낙찰 공지
//                        ChatThread chatThread = new ChatThread("익명의 참가자가" + splitMessage[3] +
//                                "원으로" + splitMessage[2] + "를 낙찰받았습니다!");
//                        chatThread.start();
//                    }
//                    case "noBid": {
//                        //아무도 입찰 안함
//                        ChatThread chatThread = new ChatThread("아무도 응찰하지 않아" + splitMessage[2] + "는 유찰되었습니다!");
//                        chatThread.start();
//                    }
//                    case "win": {
//                        // 조합 완성시에
//                        // win/유저이름
////                            PrintWriter dataToClient = null;
////                            for(Map.Entry<PrintWriter, String> entry: userConnectionList.entrySet()){
////                                if(entry.getValue().equals(splitMessage[1])){
////                                    dataToClient = entry.getKey();
////                                }
////                            }
//                        outToClient.println("승리");
//                        outToClient.flush();
////                            dataToClient.println("승리");
////                            dataToClient.flush();
//
//                        ChatThread chatThread = new ChatThread("참가자" + splitMessage[1] + "" +
//                                "이(가) 가장 먼저 조합을 완성해 우승하였습니다!");
//                        chatThread.start();
//                    }
                    default:
                        break;
                }
            }
            System.out.println("[" + requestUserName + " terminate connection]");
        } catch (IOException e) {
            System.out.println("[Server] User " + splitMessage[1] + " is disconnected");
            // User disconnected - 유저 정보 삭제해야함
            //e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class GameThread extends Thread {
    public synchronized void run() {
        System.out.println("GameStart");
        ServerData.currentCard = new DrawRandomCard().randomCard();
        ChatThread firstCardInfo = new ChatThread("200/CurrentCard/" + ServerData.currentCard);
        firstCardInfo.start();
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new ServerTime(), 0, 1, TimeUnit.SECONDS);  // 무조건 1초마다 남은시간 출력
        try {
            while (ServerData.currentRound <= 25) {
                ServerData.currentRound++;
                while(ServerData.auctionRemainTime > -1){
                    wait(1);
                }
                ServerData.auctionState = false;
                System.out.println(ServerData.currentRound + "라운드가 종료되었습니다");
                ChatThread EndRound = new ChatThread("200/UserChat/Server/" + ServerData.currentRound + "라운드가 종료되었습니다!");
                EndRound.start();
                wait(1000);
                ChatThread chatThread;
                if(UserData.currentBidUser.equals("noBid")){
                    chatThread = new ChatThread("200/UserChat/Server/아무도 응찰하지 않아 " + ServerData.currentCard + "는 유찰되었습니다!");
                }else{
                    chatThread = new ChatThread("200/UserChat/Server/" + UserData.currentBidCost + "원을 입찰한 익명의 유저가 낙찰되었습니다!");
                }
                chatThread.start();


                ServerData.usedCardList.add(ServerData.currentCard); // -- 3번
                new AddCard(); // -- 1번
                new EndRoundMessage(); // -- 2번
                //UpdateUserAccount 에서 에러발생함 - 한번 낙찰받으면 다음에 낙찰이 안됨
                //new UpdateUserAccount().updateWinnerAccount(); // -- 4번
                ServerData.currentCard = new DrawRandomCard().randomCard();   //새로운 카드 추가
                ChatThread cardInfo = new ChatThread("200/CurrentCard/" + ServerData.currentCard);
                System.out.println(UserData.currentBidUser);
                System.out.println(UserData.userDeckList);

                if(UserData.currentBidCost != 0) {
                    if (VictoryCondition.check() != 0) {
                        chatThread = new ChatThread("200/UserChat/Server/12345축하합니다! " + UserData.currentBidUser + "님이 승리하였습니다!");
                        chatThread.start();
                        GameThread.sleep(2000);
                        GameThread.interrupted();

                    }
                }


                UserData.currentBidCost = 0;    // 입찰가 초기화
                UserData.currentBidUser = "noBid";  // 입찰자 초기화
                ServerData.auctionRemainTime = 5;   // 경매 카운트다운 초기화
                ServerData.auctionState = true; // 다음 라운드시 입찰할 수 있게
                // 1 -- 유저 덱리스트에 카드 추가
                // 2 -- 낙찰된 유저에서 낙찰 메시지 출력 O
                // 3 -- 카드 중복 안되게 유저 카드 리스트에 카드 추가 O
                // 4 -- 유저 계좌에 돈 감소시키기 O
                // 5 -- 낙찰된 유저의 카드 리스트 확인
                // 6 -- 만약 조합이 완성되었다면 break;
                // 7 -- 다음 카드를 선택하는 중입니다... - 출력 - 하든 안하든 무방
                // 8 -- 라운드 마다 지급하는 돈 주기 O
                cardInfo.start();   // 카드 뿌려줌



                // -- 8번인데 클라이언트에서 다음 라운드 카드 받을때 자체적으로 금액 증가하므로 서버의 클라 금액만 증가
                new UpdateUserAccount().giveUserIncome();
                wait(4000);

            }
            if(ServerData.currentRound == 26){
                ChatThread chatThread = new ChatThread("200/UserChat/Server/아무도 조합을 완성하지 못해서 게임이 무승부로 끝났습니다!");
                chatThread.start();
            }
            else{

            }

        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }


   // public void Message
}