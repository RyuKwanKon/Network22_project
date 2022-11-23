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

    public void run() {
        System.out.println("200/UserChat/" + message);
        for (Map.Entry<String, PrintWriter> elem : userData.userConnectionList.entrySet()) {
            elem.getValue().println("200/UserChat/" + message);
            elem.getValue().flush();
        }
    }
}