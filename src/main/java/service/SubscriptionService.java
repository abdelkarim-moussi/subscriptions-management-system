package main.java.service;

import main.java.entity.Subscription;
import main.java.entity.SubscriptionWithEngagement;
import main.java.entity.SubscriptionWithoutEngagement;

import java.time.LocalDateTime;

public class SubscriptionService {

    public static void creteSubscription(
            String serviceName,float monthlyAmount,
            LocalDateTime startDate,LocalDateTime endDate,
            int monthsEngagementPeriod){

        try{
            if(monthsEngagementPeriod > 1){
                SubscriptionWithEngagement subscription = new SubscriptionWithEngagement();
                subscription.setServiceName(serviceName);
                subscription.setMonthlyAmount(monthlyAmount);
                subscription.setMonthsEngagementPeriod(monthsEngagementPeriod);
                subscription.setStartDate(startDate);
                subscription.setEndDate(endDate);
            }
            else {
                SubscriptionWithoutEngagement subscription = new SubscriptionWithoutEngagement();
                subscription.setServiceName(serviceName);
                subscription.setMonthlyAmount(monthlyAmount);
                subscription.setStartDate(startDate);
                subscription.setEndDate(endDate);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
