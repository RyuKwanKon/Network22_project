package SW;

import java.io.PrintWriter;
import java.util.Map;

public class NoneBidding {
    public NoneBidding(){  // 유찰되었을때

        for (Map.Entry<String, PrintWriter> entry : UserData.userConnectionList.entrySet()) {
            entry.getValue().println("200/EndRound/NoneBidding/" + entry.getKey() + "/" +
                    UserData.userAccount.get(entry.getKey()) + "/" + ServerData.currentCard);
            entry.getValue().flush();
        }
    }
}