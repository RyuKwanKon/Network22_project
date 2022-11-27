package SW;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UpdateUserAccount {
    private int money = 0;
//    public void updateWinnerAccount(){
//        for(Map.Entry<String, Integer> elem : UserData.userAccount.entrySet()){
//            if(UserData.currentBidUser.equals(elem.getKey())){
//                money = elem.getValue() - UserData.currentBidCost;
//                UserData.userAccount.put(UserData.currentBidUser, money);
//            }
//        }
//
//        for(Map.Entry<String, PrintWriter> elem : UserData.userConnectionList.entrySet()){
//            if(UserData.currentBidUser.equals(elem.getKey())){
//                elem.getValue().println("200/EndRound/WinBidding/" + String.valueOf(money));
//                elem.getValue().flush();
//            }
//        }
//    }
    public void giveUserIncome(){
        for(String name : UserData.userAccount.keySet()){
            UserData.userAccount.put(name, UserData.userAccount.get(name) + 10);
        }
    }
}
