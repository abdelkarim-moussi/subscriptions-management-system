package main.java.test;

import main.java.entity.Subscription;
import main.java.entity.SubscriptionWithEngagement;
import main.java.entity.SubscriptionWithoutEngagement;
import main.java.enums.SubscriptionStatus;
import main.java.service.SubscriptionService;

import java.time.LocalDateTime;

public class Test {

    public static void test(){
        SubscriptionWithoutEngagement sub1 = new SubscriptionWithoutEngagement();
        sub1.setServiceName("OnlineCourses");
        sub1.setStartDate(LocalDateTime.now());
        sub1.setEndDate(sub1.getStartDate());
        sub1.setMonthlyAmount(1200);

        SubscriptionWithEngagement sub2 = new SubscriptionWithEngagement();
        sub2.setServiceName("Netflix");
        sub2.setStartDate(LocalDateTime.now());
        sub2.setEndDate(sub1.getStartDate().plusDays(30));
        sub2.setMonthlyAmount(500);
        sub2.setMonthsEngagementPeriod(10);

        System.out.println(sub1+"\n"+sub2);
    }

    static SubscriptionService subscriptionService = new SubscriptionService();

    public static void createSub(){
        subscriptionService.createSubscription(
                "QliExpress",
                100,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30),
                0, SubscriptionStatus.suspended);
    }


}
