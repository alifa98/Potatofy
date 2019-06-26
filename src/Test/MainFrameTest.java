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
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
//        frame.getSidePanel().addCard(user1);
        frame.setVisible(true);

        try{
            Thread.sleep(3000);

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
    }

}
