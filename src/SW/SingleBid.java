package SW;
import SW.Server.*;
public class SingleBid {
    public SingleBid() {
        ServerThread.ChatThread chatThread = new ServerThread.ChatThread(UserData.userConnectionList, "");
        chatThread.start();
    }
}
