package Page.GamePagePanel;

import javax.swing.*;
//455 50
public class AuctionCard extends JPanel {
    public AuctionCard(){
        setBounds(750, 530, 455, 200);
        setLayout(null);
        setOpaque(false);
        char[] title = {'A', 'B', 'C', 'D'};
        int[] h = {500, 550, 600, 650};
        add(new CardView(750, 530, 141, 200, "A1"));
//        for(int i = 0; i < 52; i++){
//            System.out.println(h[i / 13]);
//            System.out.println(750 + (i%13)*35);
//            System.out.println(title[i / 13] + String.valueOf((i%13)+1));
//            card[i] = new CardView(750 + (i%13)*35, h[i / 13], 35, 50, title[i / 13] + String.valueOf((i%13)+1));
//            add(card[i]);
//        }
    }
}
