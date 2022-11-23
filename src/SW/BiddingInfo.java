package SW;

import java.util.Map;

public class BiddingInfo {
    private static int bidCount = 0;
    public void StoreInfo(String userName, int bidInfo){
        UserData.userBiddingInfo.put(userName, bidInfo);
        if (bidInfo == 1)
            bidCount++;
    }

    public static int BiddingNum() {
        if(bidCount == 0)
            return 0;
        else return 1;
    }

    public String SoloBidUser () {
        for(Map.Entry<String, Integer> User : UserData.userBiddingInfo.entrySet()){
            if(User.getValue() == 1)
                return User.getKey();
        }
        return "No one bidding this card";
    }
}
