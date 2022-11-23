package SW;

import java.net.*;
import java.io.*;

//13.	낙찰(서버-> 클라이언트): 유저 이름/트럼프 카드/낙찰가
public class Func13 extends Server{

    public void cardAwarded (DataOutputStream outToClient, String nickname, String card, int awardPrice) throws IOException {
        outToClient.writeBytes(nickname+"/"+card+"/"+String.valueOf(awardPrice)+"\n");
    }
}
