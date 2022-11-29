package SW;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class UserData {
    static int count = 0;
    static ArrayList<String> nameList = new ArrayList<String>(4);   //유저 이름
    static HashMap<String, Integer> userBiddingInfo = new HashMap<String, Integer>(4);
    // 2번 참가자 정보 저장
    static HashMap<String, PrintWriter> userConnectionList = new HashMap<String, PrintWriter>(4);
    static String currentBidUser = "noBid";
    static int currentBidCost = 0;
    public static HashMap<String, Integer> userAccount = new HashMap<String, Integer>(4);
    public static HashMap<String, String> userDeckList = new HashMap<String, String>(4);

//    static String[] user1Deck = new String[26];
//    static String[] user2Deck = new String[26];
//    static String[] user3Deck = new String[26];
//    static String[] user4Deck = new String[26];

    public void registerBid(String userName){
        if(UserData.userAccount.get(userName) > currentBidCost) {
            UserData.currentBidUser = userName;
            UserData.currentBidCost += 5;
            ChatThread chatThread = new ChatThread("200/RegisterBid/" + this.currentBidUser + "/" + this.currentBidCost);
            chatThread.start();
        }
    }
}