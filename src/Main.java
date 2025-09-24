import main.java.enums.SubscriptionStatus;
import main.java.service.SubscriptionService;
import main.java.util.DataBaseConnection;

import java.sql.Connection;
import java.time.LocalDateTime;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static Connection con = DataBaseConnection.getConnection();
    static SubscriptionService subscriptionService = new SubscriptionService();

    static void createSub(){
        subscriptionService.createSubscription(
                "Amazon",
                300,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30),
                10, SubscriptionStatus.active);
    }
    public static void main(String[] args) {
        createSub();
    }
}