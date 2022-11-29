package SW;

import java.util.Map;

public class AddCard {

    private String card = "";
    public AddCard(){
        card = ServerData.currentCard;
        for(Map.Entry<String, String> elem : UserData.userDeckList.entrySet()){
            if(UserData.currentBidUser.equals(elem.getKey())){
                elem.getValue().concat(ServerData.currentCard + "/");
                UserData.userDeckList.put(UserData.currentBidUser, elem.getValue());
            }
        }
    }
}