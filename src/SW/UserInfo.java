package SW;

import java.util.HashMap;

public class UserInfo {
    HashMap<String, Integer> userInfo = new HashMap<String, Integer>(4);

    public UserInfo(String name, int userNum){
        userInfo.put(name, userNum);
    }
    public int isFull() {
        if(userInfo.size() < 4)
            return 0;
        else return 1;
    }
}
