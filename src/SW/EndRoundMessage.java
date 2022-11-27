package SW;
import java.io.PrintWriter;
import java.util.Map;

//15.	낙찰정보전달(서버 -> 클라이언트): 낙찰 정보 메시지
public class EndRoundMessage {
    public EndRoundMessage() {

        // 1번째 - 입찰한 유저가 있을 경우
        // 2번째 - 입찰한 유저가 아무도 없는 경우 -- Client에서 판단

        for (Map.Entry<String, PrintWriter> entry : UserData.userConnectionList.entrySet()) {
            if (UserData.currentBidUser.equals(entry.getKey())) {
                entry.getValue().println("200/EndRound/WinBidding" + entry.getKey() + "/" +
                        UserData.userAccount.get(entry.getKey()) + "/" + ServerData.currentCard); // 낙찰받은 유저
                entry.getValue().flush();
            }
            entry.getValue().println("200/EndRound/NoBidding" + entry.getKey() + "/" +
                    UserData.userAccount.get(entry.getKey()) + "/" + ServerData.currentCard);
            entry.getValue().flush();
        }
    }
}