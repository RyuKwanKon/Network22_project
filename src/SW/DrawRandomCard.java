package SW;
import java.util.Random;

//5.	트럼프카드설정(서버): 임의의 트럼프 카드 출력
public class DrawRandomCard {
    boolean cardDuplication = true;
    String randomCard = null;
    String[] shape = {"A", "B", "C", "D"};
    String[] num = {"1","2","3","4","5","6","7","8","9",
            "10","11","12","13"};

    public String randomCard() {

        while(cardDuplication){
            Random random = new Random();
            randomCard = shape[random.nextInt(4)]+num[random.nextInt(13)];

            for(String usedCard : ServerData.usedCardList){
                if(randomCard.equals(usedCard))
                    continue;
            }
            cardDuplication = false;
        }

        return randomCard;
//        return shape[random.nextInt(4)]+num[random.nextInt(13)];
    }
}