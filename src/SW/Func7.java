package SW;

import java.io.*;
import java.net.*;

//7.	입찰여부송신(클라이언트 -> 서버): 유저 번호/입찰 여부(0or1)
public class Func7 {
    public void bid(int userNum) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1",1111);
        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(String.valueOf(userNum)+"/"+
                "1"+"\n");
        clientSocket.close();
    }

    public void nonBid(int userNum)  {
        try{
            Socket clientSocket = new Socket("127.0.0.1",1111);
            DataOutputStream outToServer =
                    new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(String.valueOf(userNum)+"/"+
                    "0"+"\n");
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}