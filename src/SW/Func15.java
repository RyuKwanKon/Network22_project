package SW;
import java.io.DataOutputStream;
import java.io.IOException;
//15.	낙찰정보전달(서버 -> 클라이언트): 낙찰 정보 메시지
public class Func15 extends Server{

    public void award(DataOutputStream outToClient, String nickname, String card,
                      int currentPrice, int retention) throws IOException {
        String awardMsg =
                nickname+"님이 "+card+"를 "+currentPrice+"에 낙찰했습니다.";

        outToClient.writeBytes(awardMsg);
    }
}
