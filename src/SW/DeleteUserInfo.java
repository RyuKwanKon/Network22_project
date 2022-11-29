package SW;

public class DeleteUserInfo {

    public DeleteUserInfo(String userName) {
        UserData.nameList.remove(userName);
        UserData.userBiddingInfo.remove(userName);
        UserData.userConnectionList.remove(userName);
        UserData.userAccount.remove(userName);
        UserData.userDeckList.remove(userName);
    }
}
