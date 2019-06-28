package gui;

import com.TimeData;
import com.UserInfoCard;
import java.io.File;
import java.util.Date;

public class MainFrameTest {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame("Test Main Frame");
        UserInfoCard user1 = new UserInfoCard(
                new File("src\\gui\\icons\\png\\64\\default_avatar.png"),
                "Ali",
                new TimeData(new Date().getTime() - (56*60*1000 + 2)), "Shape adofads my heart");

        frame.getSidePanel().addCard(user1);
        frame.getSidePanel().addCard(user1);
        frame.getSidePanel().addCard(user1);
        frame.getSidePanel().addCard(user1);
        frame.getSidePanel().addCard(user1);
        frame.getSidePanel().addCard(user1);
        frame.getSidePanel().addCard(user1);
        frame.getSidePanel().addCard(user1);
//        frame.getSongsPanel().addSongCard("tests song","album 1","Ali Faraji",user1.getAvatarSrc(),595945488,true);
//        frame.getSongsPanel().addSongCard("testasds song","album 1","Ali Faraji",user1.getAvatarSrc(),595945488,true);
//        frame.getSongsPanel().addSongCard("tesasdts song","album 1","Ali Faraji",user1.getAvatarSrc(),595945488,true);
//        frame.setMainPanel(frame.getSongsPanel());
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
        frame.setVisible(true);

        try{
            Thread.sleep(2000);
        }catch (Exception e){
        }
        UserInfoCard user2 = new UserInfoCard(
                new File("src\\gui\\icons\\png\\64\\default-boy-avatar.png"),
                "Erfan",
                new TimeData(new Date().getTime() - (15*60*1000 + 2)), "Shape asdeart");

        frame.getSidePanel().addCard(user2);
        frame.getSidePanel().addCard(user1);
        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
        try{
            Thread.sleep(2000);
        }catch (Exception e){
        }
//        frame.getSongsPanel().addSongCard("tesdts asdsong","album 1","Ali Faraji",user1.getAvatarSrc(),595945488,true);
        try{
            Thread.sleep(6000);
        }catch (Exception e){
        }
        frame.getSongsPanel().setCardState("tsadsong",true);

        frame.setMainPanel(frame.getScrollableSongsPanel());
    }

}
