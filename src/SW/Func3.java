package SW;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

//3. 참가자입장시상태(서버 -> 클라이언트): 유저 번호(들어온 순서부터 1~4)/기본 금액 0
public class Func3 extends Server{
    public static int userNum = 0;

    public void playerState(DataOutputStream outToClient) {
        try {
            userNum++;
            outToClient.writeBytes(userNum + "/0");
            if (userNum == 4)
                userNum = 0;
        }catch(IOException e){

        }
    }
}