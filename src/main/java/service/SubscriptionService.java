package main.java.service;

import main.java.dao.SubscriptionDAO;
import main.java.entity.Subscription;
import main.java.entity.SubscriptionWithEngagement;
import main.java.entity.SubscriptionWithoutEngagement;
import main.java.enums.SubscriptionStatus;

import java.time.LocalDateTime;

public class SubscriptionService {

    public SubscriptionDAO subscriptionDAO = new SubscriptionDAO();

    public void createSubscription(
            String serviceName, float monthlyAmount,
            LocalDateTime startDate, LocalDateTime endDate,
            Integer monthsEngagementPeriod, SubscriptionStatus status){

        try{
            if(monthsEngagementPeriod > 1){
                SubscriptionWithEngagement subscription = new SubscriptionWithEngagement();
                subscription.setServiceName(serviceName);
                subscription.setMonthlyAmount(monthlyAmount);
                subscription.setMonthsEngagementPeriod(monthsEngagementPeriod);
                subscription.setStartDate(startDate);
                subscription.setEndDate(endDate);
                subscription.setStatus(status);

                subscriptionDAO.addSubscription(subscription);

            }
            else {
                SubscriptionWithoutEngagement subscription = new SubscriptionWithoutEngagement();
                subscription.setServiceName(serviceName);
                subscription.setMonthlyAmount(monthlyAmount);
                subscription.setStartDate(startDate);
                subscription.setEndDate(endDate);
                subscription.setStatus(status);

                subscriptionDAO.addSubscription(subscription);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateSubscription(String id ,LocalDateTime startDate,
             LocalDateTime endDate,
             Integer monthsEngagementPeriod ,
             SubscriptionStatus status){

    }
}

