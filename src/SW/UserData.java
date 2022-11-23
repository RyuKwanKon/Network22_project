package SW;

import java.io.PrintWriter;
import java.util.HashMap;

public class UserData {
    static int count = 0;
    static HashMap<String, Integer> userBiddingInfo = new HashMap<String, Integer>(4);
    // 2번 참가자 정보 저장
    static HashMap<PrintWriter, String> userConnectionList = new HashMap<PrintWriter, String>(4);
    static String currentBidUser = null;
    public HashMap<String, Integer> userAccount = new HashMap<String, Integer>(4);
}