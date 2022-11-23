package SW;

import java.io.PrintWriter;

public class CongratulationMsg {

    public CongratulationMsg(PrintWriter outToClient){
        outToClient.println("Congratulation! You are the winner");
        outToClient.flush();
    }

}
