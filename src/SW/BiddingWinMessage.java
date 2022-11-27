package SW;
import java.io.PrintWriter;
import java.util.Map;

//15.	낙찰정보전달(서버 -> 클라이언트): 낙찰 정보 메시지
public class BiddingWinMessage {
    public BiddingWinMessage(){

        for(Map.Entry<String, PrintWriter> entry : UserData.userConnectionList.entrySet()){
            if(UserData.currentBidUser.equals(entry.getKey())){
                entry.getValue().println("200/UserChat/" + ServerData.currentCard + "를 " +
                        UserData.currentBidCost + "원에 낙찰받으셨습니다!");
                entry.getValue().flush();
            }
        }
    }

}
