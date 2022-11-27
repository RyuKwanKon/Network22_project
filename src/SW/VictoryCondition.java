package SW;

import java.util.Arrays;
import java.util.*;

//17. 카드조합확인(클라이언트): 카드 리스트의 카드 조합 확인
//
public class VictoryCondition {
    public static int check(HashMap<String, String> decks) {
        int userNum = 1;
        String value;

        for(Map.Entry<String, String> e : decks.entrySet()) {
            value = e.getValue();
            String[] deck = value.split("/");
            String[] hand = new String[25];
            for(int i = 0; i < 25; i++)
                hand[i] = null;

            for(int i = 0; i < deck.length; i++)
                hand[i] = deck[i];

            String[] num = new String[25];
            String[] shape = new String[25];
            int[] intArr = new int[25];
            int fourShape = 0;
            int threeConsecutive = 0;
            int twoNum = 0;
            int spade = 0;
            int heart = 0;
            int diamond = 0;
            int clover = 0;

            for (int i = 0; i < 25; i++) {
                if (hand[i] == null) {
                    num[i] = "0";
                    shape[i] = "0";
                } else {
                    shape[i] = hand[i].substring(0, 1);
                    num[i] = hand[i].substring(1);
                }
            }

            for (int i = 0; i < 25; i++) {
                switch (shape[i]) {

                    case "A":
                        spade++;
                        break;

                    case "C":
                        heart++;
                        break;

                    case "B":
                        diamond++;
                        break;

                    case "D":
                        clover++;
                        break;

                    default:
                        break;
                }
            }

            if (spade >= 4 || heart >= 4 || diamond >= 4 || clover >= 4) {
                fourShape++;
            }

            for (int i = 0; i < 25; i++) {
                if (num[i] == "") {
                    intArr[i] = 0;
                } else {
                    intArr[i] = Integer.parseInt(num[i]);
                }
            }
            Arrays.sort(intArr);
            for (int i = 0; i < 23; i++) {
                for (int j = i + 1; j < 24; j++) {
                    for (int k = j + 1; k < 25; k++) {
                        if (intArr[j] == intArr[i] + 1 && intArr[k] == intArr[j] + 1)
                            threeConsecutive++;
                    }
                }
            }

            for (int i = 0; i < 24; i++) {
                for (int j = i + 1; j < 25; j++) {
                    if (num[i].equals(num[j]) && num[i] != "0" && num[j] != "0") {
                        twoNum++;
                    }
                }
            }


            if (fourShape != 0 || threeConsecutive != 0 || twoNum != 0) {
                return userNum;
            }
            userNum++;
        }
        return 0;
    }
}
