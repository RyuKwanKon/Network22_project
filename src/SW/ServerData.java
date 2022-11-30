package SW;

import java.util.ArrayList;

public class ServerData {  // 서버가 관리하는 자원
    static boolean isStart = false;
    static boolean startGame = false;
    static boolean auctionState = true;
    static int currentRound = 0;
    static String currentCard = null;
    static ArrayList<String> usedCardList = new ArrayList<String>(26);
    static int auctionRemainTime = 5;

}