package SW;

import java.io.PrintWriter;
import java.util.Map;

public class NoneBidding {
    synchronized public static void noneBidding(){  // 유찰되었을때
        for (Map.Entry<String, PrintWriter> entry : UserData.userConnectionList.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().println("200/EndRound/NoneBidding/" + entry.getKey() + "/" +
                    UserData.userAccount.get(entry.getKey()) + "/" + ServerData.currentCard);
            entry.getValue().flush();
        }
    }
}