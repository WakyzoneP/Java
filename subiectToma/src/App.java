import java.util.Date;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            // date is 12.01.2021
            GymCard card1 = new GymCard("John Doe", 0, new Date((2023 - 1900), 1, 12), GymCard.CardType.bronze);
            System.out.println(card1.isExpired());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
