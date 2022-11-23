package SW;
import java.io.*;
import java.net.*;

//1. 게임참가확인(클라이언트->서버): 유저 닉네임
public class Func1 {
    public void nicknameToServer(String nickname) {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 1111);
            DataOutputStream outToServer =
                    new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(nickname + '\n');
            clientSocket.close();
        }catch(IOException e){

        }
    }

}