package SW;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ServerData {  // 서버가 관리하는 자원

    static int currentRound = 0;
    static String currentCard = null;
    static String[] usedCardList = new String[52];
    static int AuctionRemainTime = 5;
}