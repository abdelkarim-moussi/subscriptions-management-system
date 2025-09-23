package main.java.service;

import main.java.dao.SubscriptionDAO;
import main.java.entity.Subscription;
import main.java.entity.SubscriptionWithEngagement;
import main.java.entity.SubscriptionWithoutEngagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionService {

    public SubscriptionDAO subscriptionDAO = new SubscriptionDAO();

    public static void creteSubscription(
            String serviceName,float monthlyAmount,
            LocalDateTime startDate,LocalDateTime endDate,
            int monthsEngagementPeriod){
        List <Subscription> data = new ArrayList<>();

        try{
            if(monthsEngagementPeriod > 1){
                SubscriptionWithEngagement subscription = new SubscriptionWithEngagement();
                subscription.setServiceName(serviceName);
                subscription.setMonthlyAmount(monthlyAmount);
                subscription.setMonthsEngagementPeriod(monthsEngagementPeriod);
                subscription.setStartDate(startDate);
                subscription.setEndDate(endDate);

                data.add(subscription);
                SubscriptionDAO.addSubscription(data);

            }
            else {
                SubscriptionWithoutEngagement subscription = new SubscriptionWithoutEngagement();
                subscription.setServiceName(serviceName);
                subscription.setMonthlyAmount(monthlyAmount);
                subscription.setStartDate(startDate);
                subscription.setEndDate(endDate);

                data.add(subscription);
                SubscriptionDAO.addSubscription(data);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
