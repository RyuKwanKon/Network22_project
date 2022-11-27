package SW;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerData {  // 서버가 관리하는 자원

    static boolean auctionState = true;
    static int currentRound = 0;
    static String currentCard = null;
    static ArrayList<String> usedCardList = new ArrayList<String>(26);
    static int auctionRemainTime = 5;
}