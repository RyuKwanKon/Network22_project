package SW;

import java.io.*;
import java.net.*;

//입찰요청(클라이언트 -> 서버): 유저 이름/호가(5)/현재 입찰가/현재 보유 금액
public class Func11 {

    public void bid_request(String bidder, int currentPrice, int retention) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1",1111);
        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());


        if (currentPrice > retention) {
            outToServer.writeBytes(bidder+"/0/"+
                    Integer.toString(currentPrice)+"/"+
                    Integer.toString(retention)+"\n");
        }
        else {
            currentPrice += 5;


            outToServer.writeBytes(bidder+"/5/"+
                    Integer.toString(currentPrice)+"/"+
                    Integer.toString(retention)+"/n");
        }
        clientSocket.close();
    }


}