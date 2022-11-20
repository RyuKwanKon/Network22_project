package Page;

import Event.*;

import Page.GamePagePanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

import Component.*;

import static Event.MainTimer.randNumber;
import static Event.MainTimer.randTitle;
import static Page.GamePagePanel.Alarm.startPrice;
import static Page.GamePagePanel.OnChat.input;
import static Page.GamePagePanel.ScrollChatting.vertical;

public class GamePageView extends JPanel {
    public static JLabel[] card = new JLabel[52];
    private Timer mainTimer = new Timer(3000, new MainTimer());
    public static JLabel CenterCard = new CardView(590, 150, 141, 200, (char) randTitle + String.valueOf(randNumber));
    public static JPanel currentChatting = new Chat();
    public static JPanel onChat = new OnChat();
    public static JLabel alarm = new Alarm();
    public static boolean on = false;
    public static JPanel connectUser = new ConnectUserPanel();
    public static JScrollPane Chatting = new ScrollChatting();
    public GamePageView(){
        mainTimer.start();
        Color background = new Color(255, 255, 255);
        setLayout(null);
        setBorder(null);
        setBounds(0, 0, 1280, 720);
        setBackground(background);
        add(alarm);

        CenterCard.setOpaque(false);
        add(CenterCard);
        add(new Bid());

        add(Chatting);
        add(onChat);
        onChat.setVisible(on);

        add(new CurrentTitle());
        char[] title = {'A', 'B', 'C', 'D'};
        int[] h = {450, 500, 550, 600};
        for(int i = 0; i < 52; i++){
            card[i] = new CardView(800 + (i%13)*35, h[i / 13], 35, 50, title[i / 13] + String.valueOf((i%13)+1));
            add(card[i]);
        }

        add(connectUser);

        add(new GamePage());

        setFocusable(true);
        requestFocus();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(!on){
                        vertical.setValue(vertical.getMaximum());
                        GamePageView.onChat.setVisible(true);
                        input.requestFocus();
                    }
                    on = !on;
                }
            }
        });
    }
    class CurrentTitle extends JLabel{
        CurrentTitle(){
            setText("남아있는 카드");
            setBounds(1150, 410, 150, 30);
            setFont(FontStyle.BidFont);
            setForeground(new Color(154, 254, 132));
        }
    }
}
