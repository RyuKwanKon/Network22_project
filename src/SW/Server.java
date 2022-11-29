package SW;

import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {
    private UserData userData;
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public Server() {
        this.userData = new UserData();
    }

    public synchronized void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(1111);
            service.scheduleAtFixedRate(new ServerTime(), 0, 1, TimeUnit.SECONDS);
            while (true) {
                //4명의 유저를 받는다.
                System.out.println("[Server] Wait until client come...");
                if(UserData.socketCount == 4){   // 이거 말고 소켓은 무한대로 받아도 되고 그냥 Userdata.count == 4일떄 게임 쓰레드 시작하게
                    while(UserData.count < 4){
                        wait(1);
                    }
                    ServerData.auctionRemainTime = 5;
                    GameThread gameThread = new GameThread();
                    gameThread.start();
                }
                socket = serverSocket.accept();
                System.out.println("[server] New client connected");
                if (userData.count == 4) {
                    System.out.println("접속 불가");
                    continue;
                }
                ServerThread serverthread = new ServerThread(socket);
                serverthread.start();
                UserData.socketCount++;
            }
        } catch (IOException e) {
            System.out.println("[Server] User disconnection");
            //e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    service.shutdown();
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

                if (splitMessage[0].equals("end")) return;
                switch (splitMessage[0]) {
                    case "userConnection": {
                        userData.userConnectionList.put(splitMessage[1], outToClient);
                        userData.userAccount.put(splitMessage[1], 100);
                        userData.nameList.add(splitMessage[1]);
                        userData.userDeckList.put(splitMessage[1], "");
                        userData.count++;
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
                        wait(500);
                    }
                    break;
                    case "UserChat": {
                        String message = splitMessage[1] + ": ";
                        for (int i = 2; i < splitMessage.length; i++) {
                            message += splitMessage[i];
                        }
                        ChatThread chatThread = new ChatThread("200/UserChat/Chat/" + message);
                        chatThread.start();
                    }
                    break;
                    case "RegisterBid": {
                        if(ServerData.auctionState == false) break;
                        userData.registerBid(splitMessage[1]);
                        serverData.auctionRemainTime = 5;
                        break;
                    }
                    case "Finish": {
                        DeleteUserInfo.deleteUserInfo(splitMessage[1]);
                        return;
                    }
                    default:
                        break;
                }
            }
            System.out.println("[" + requestUserName + " terminate connection]");
        } catch (NullPointerException e){
            disconnect(splitMessage[1]);
            if(userData.nameList.size() == 1) endProgram(userData.nameList.get(0));
        } catch (IOException e) {
            disconnect(splitMessage[1]);
            if(userData.nameList.size() == 1) endProgram(userData.nameList.get(0));
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("error1");
        }
    }
    synchronized public void disconnect(String name){
        System.out.println("[Server] User " + name + " is disconnected");
        DeleteUserInfo.deleteUserInfo(name);
        ChatThread chatThread = new ChatThread("200/userChat/\"" + name + "\"님이 탈주하셨습니다.");
        chatThread.start();
        chatThread = new ChatThread("300/Disconnect/" + name);
        chatThread.start();
    }
    synchronized public void endProgram(String name){
        ChatThread chatThread = new ChatThread("200/UserChat/Server/\""+name+"\"님이 승리하셨습니다.");
        chatThread.start();
        chatThread = new ChatThread("200/UserChat/Server/Finish");
        chatThread.start();
    }
}



class GameThread extends Thread {
    public synchronized void run() {
        System.out.println("GameStart");

        try {
//            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//            service.scheduleAtFixedRate(new ServerTime(), 0, 1, TimeUnit.SECONDS);  // 무조건 1초마다 남은시간 출력
            wait(500);
            while (ServerData.currentRound <= 25) {
                while (ServerData.auctionRemainTime > -1) {
                    wait(1);
                }
                ServerData.auctionState = false;
                if (ServerData.currentRound != 0) {
                    new UpdateUserAccount().giveUserIncome();
                    System.out.println(ServerData.currentRound + "라운드가 종료되었습니다");

                    ChatThread EndRound = new ChatThread("200/UserChat/Server/" + ServerData.currentRound + "라운드가 종료되었습니다!");
                    EndRound.start();
                    wait(1000);
                    ChatThread chatThread;
                    if (UserData.currentBidUser.equals("noBid")) {
                        NoneBidding.noneBidding();
                    } else {
                        chatThread = new ChatThread("200/UserChat/Server/" + UserData.currentBidCost + "원을 입찰한 익명의 유저가 낙찰되었습니다!");
                        chatThread.start();
                        EndRoundMessage.EndRoundMessage(); // -- 2번
                    }
                    ServerData.usedCardList.add(ServerData.currentCard); // -- 3번
                    new AddCard(); // -- 1번
                    if (UserData.currentBidCost != 0) { //승리 조건 체크해서 승리자 있으면 승리메세지 출력, 스레드 종료
                        if (VictoryCondition.check() != 0) {
                            chatThread = new ChatThread("200/UserChat/Server/축하합니다! " + UserData.currentBidUser + "님이 승리하였습니다!");
                            chatThread.start();
                            ChatThread end = new ChatThread("200/UserChat/Server/Finish");
                            end.start();
                            ServerData.usedCardList.clear();
                            UserData.count = 0;
                            UserData.socketCount = 0;
                            ServerData.currentRound = 0;
                            UserData.currentBidCost = 0;    // 입찰가 초기화
                            UserData.currentBidUser = "noBid";  // 입찰자 초기화
                            ServerData.auctionRemainTime = 5;   // 경매 카운트다운 초기화
                            ServerData.auctionState = true; // 다음 라운드시 입찰할 수 있게
                            return;
                        }
                    }
                }
                //UpdateUserAccount 에서 에러발생함 - 한번 낙찰받으면 다음에 낙찰이 안됨
                //new UpdateUserAccount().updateWinnerAccount(); // -- 4번
                ServerData.currentCard = new DrawRandomCard().randomCard();   //새로운 카드 추가
                ChatThread cardInfo = new ChatThread("200/CurrentCard/" + ServerData.currentCard);

                ServerData.currentRound++;  // 라운드 증가
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
                wait(1000);
            }
            if (ServerData.currentRound == 26) {
                ChatThread chatThread = new ChatThread("200/UserChat/Server/게임이 무승부로 끝났습니다!");
                chatThread.start();
                ChatThread end = new ChatThread("200/UserChat/Server/Finish");
                end.start();
                UserData.count = 0;
                UserData.socketCount = 0;
                ServerData.currentRound = 0;
                UserData.currentBidCost = 0;    // 입찰가 초기화
                UserData.currentBidUser = "noBid";  // 입찰자 초기화
                ServerData.auctionRemainTime = 5;   // 경매 카운트다운 초기화
                ServerData.auctionState = true; // 다음 라운드시 입찰할 수 있게
                ServerData.usedCardList.clear();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// public void Message// public void Message