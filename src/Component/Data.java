package Component;

import java.util.HashMap;

public class Data {
    public static HashMap<String, String> currentChat = new HashMap<String, String>();
    public static char [] cardInfo = {'♠','◆','♥','♣'};
//    public static HashMap<String, String> cardInfo = new HashMap<>(){{
//        put("A", "♠︎");
//        put("B", "♦︎");
//        put("C", "♥︎");
//        put("D", "♣︎");
//    }};
    public static HashMap<Integer, String> cardInfoNumber = new HashMap<>(){{
        put(1, "A");
        put(2, "2");
        put(3, "3");
        put(4, "4");
        put(5, "5");
        put(6, "6");
        put(7, "7");
        put(8, "8");
        put(9, "9");
        put(10, "10");
        put(11, "J");
        put(12, "Q");
        put(13, "K");
    }};
}
