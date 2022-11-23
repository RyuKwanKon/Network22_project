package SW;
import java.util.Random;

//5.	트럼프카드설정(서버): 임의의 트럼프 카드 출력
public class Func5 {
    String[] shape = {"A", "B", "C", "D"};
    String[] num = {"1","2","3","4","5","6","7","8","9",
            "10","11","12","13"};

    public String randomCard() {
        Random random = new Random();
        return shape[random.nextInt(4)]+num[random.nextInt(13)];
    }
}