package SW;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ServerData {  // 서버가 관리하는 자원
    public BufferedReader inFromClient = null;
    public PrintWriter outToClient = null;
    public int currentRound = 0;
    public String currentCard = null;

    public String[] usedCardList = new String[52];
}
