package SW;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class UserData {

    static boolean isStart = false;
    static int count = 0;
    static ArrayList<String> nameList = new ArrayList<String>(4);
    static HashMap<String, Integer> userBiddingInfo = new HashMap<String, Integer>(4);
    // 2번 참가자 정보 저장
    static HashMap<String, PrintWriter> userConnectionList = new HashMap<String, PrintWriter>(4);
    static String currentBidUser = null;
    public HashMap<String, Integer> userAccount = new HashMap<String, Integer>(4);
}
