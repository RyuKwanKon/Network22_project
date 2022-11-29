package SW;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

class ChatThread extends Thread {
    private UserData userData;
    private String message;
    public ChatThread(String message) {
        this.userData = new UserData();
        this.message = message;
    }

    public synchronized void run() {
        if(message.length() >= 17){
            System.out.println(message);
            for (Map.Entry<String, PrintWriter> elem : userData.userConnectionList.entrySet()) {
                elem.getValue().println(message);
                elem.getValue().flush();
            }
        }
    }
}