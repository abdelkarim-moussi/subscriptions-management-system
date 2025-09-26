package main.java.test;

import main.java.entity.Subscription;
import main.java.entity.SubscriptionWithEngagement;
import main.java.entity.SubscriptionWithoutEngagement;
import main.java.enums.PaymentStatus;
import main.java.enums.PaymentType;
import main.java.enums.SubscriptionStatus;
import main.java.service.PaymentService;
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

    static PaymentService paymentService = new PaymentService();


    public static void createSub(){
        subscriptionService.createSubscription(
                "Orange",
                40,
                0);
    }

    public static void updateSub(){

        // String serviceName, float monthlyAmount, LocalDateTime startDate, LocalDateTime endDate, int monthsEngagementPeriod
        Subscription subscription = new SubscriptionWithEngagement("jaaza",300,LocalDateTime.now(),
                LocalDateTime.now().plusDays(30),SubscriptionStatus.terminated,30);

        subscriptionService.updateSubscription(
                "sub-193883259479442979307280185077140071773",
                subscription
                );
    }

    public static void deleteSub(){
        subscriptionService.deleteSubscription("sub-35740342449062213816371332580601835317");
    }

    public static void allSubs(){
        subscriptionService.getAllSubscriptions();
    }

    //Payment Test

    public static void createPay(){
        paymentService.createPayment("sub-227588569865594870118431465200625915628", PaymentType.pay_with_card, PaymentStatus.payed);
    }

    public static void updatePay(){
        paymentService.updatePayment("pay-153908655531777072495171733021793930435","sub-227588569865594870118431465200625915628",LocalDateTime.now(),LocalDateTime.now().plusDays(30),PaymentType.paypal, PaymentStatus.late);
    }

    public static void deletePay(){
        paymentService.deletePayment("pay-153908655531777072495171733021793930435");
    }
}
