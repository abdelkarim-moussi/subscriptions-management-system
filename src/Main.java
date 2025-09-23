import main.java.service.SubscriptionService;
import main.java.test.Test;
import main.java.util.DataBaseConnection;

import java.sql.Connection;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static Connection con = DataBaseConnection.getConnection();

    public static void main(String[] args) {
        Test.test();

        SubscriptionService.creteSubscription(
                "Netflix",
                500,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30),
                10);
    }
}