package SW;

public class DeleteUserInfo {
    synchronized public static void deleteUserInfo(String userName) {
        UserData.nameList.remove(userName);
        UserData.userBiddingInfo.remove(userName);
        UserData.userConnectionList.remove(userName);
        UserData.userAccount.remove(userName);
        UserData.userDeckList.remove(userName);
    }
}
