package main.java.service;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import main.java.dao.SubscriptionDAO;
import main.java.entity.Subscription;
import main.java.entity.SubscriptionWithEngagement;
import main.java.entity.SubscriptionWithoutEngagement;
import main.java.enums.SubscriptionStatus;

import java.time.LocalDateTime;

public class SubscriptionService {

    SubscriptionDAO subscriptionDAO;
    public SubscriptionService(){
        this.subscriptionDAO = new SubscriptionDAO();
    }
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

                subscriptionDAO.add(subscription);

            }
            else {
                SubscriptionWithoutEngagement subscription = new SubscriptionWithoutEngagement();
                subscription.setServiceName(serviceName);
                subscription.setMonthlyAmount(monthlyAmount);
                subscription.setStartDate(startDate);
                subscription.setEndDate(endDate);
                subscription.setStatus(status);

                subscriptionDAO.add(subscription);
            }

        }catch(Exception e){
            e.getStackTrace();
        }
    }

    public void updateSubscription(String id ,Subscription subscription){

        try{

            Subscription storedSubscription = subscriptionDAO.findById(id);
            System.out.println("storedSub : "+storedSubscription);
            if(subscription != null){

                if(subscription.getServiceName().trim() != "" &&
                        subscription.getMonthlyAmount() != 0 &&
                        !subscription.getStartDate().isBefore(LocalDateTime.now())
                        && subscription.getEndDate().isAfter(subscription.getStartDate().plusDays(30))
                ){
                    if(subscription instanceof SubscriptionWithEngagement){
                        if (subscription.getMonthsEngagementPeriod() >= 1){
                        subscriptionDAO.update(storedSubscription.getId(),subscription);
                        }
                        else System.err.println("subscription with engagement must have a months engagement period");
                    }
                    else {
                        subscriptionDAO.update(storedSubscription.getId(),subscription);
                    }
                }

            }

        }catch (Exception e){
            e.getStackTrace();
        }
    }
}

