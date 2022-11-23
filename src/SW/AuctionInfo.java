package SW;

public class AuctionInfo {

    private int countRound = 0;
    String card = null;

    public void storeInfo(int countRound, String card){
        this.countRound = countRound;
        this.card = card;
    }
    public String currentRoundInfo(){
        return countRound + card;
    }
}
