package main.java.service;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import main.java.dao.SubscriptionDAO;
import main.java.entity.Subscription;
import main.java.entity.SubscriptionWithEngagement;
import main.java.entity.SubscriptionWithoutEngagement;
import main.java.enums.SubscriptionStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionService {

    SubscriptionDAO subscriptionDAO;
    public SubscriptionService(){
        this.subscriptionDAO = new SubscriptionDAO();
    }

    public void createSubscription(
            String serviceName, float monthlyAmount,
            Integer monthsEngagementPeriod){

        try{
            if(monthsEngagementPeriod > 1){
                SubscriptionWithEngagement subscription = new SubscriptionWithEngagement();
                subscription.setServiceName(serviceName);
                subscription.setMonthlyAmount(monthlyAmount);
                subscription.setMonthsEngagementPeriod(monthsEngagementPeriod);
                subscription.setStartDate(LocalDateTime.now());
                subscription.setEndDate(LocalDateTime.now().plusDays(30));
                subscription.setStatus(SubscriptionStatus.active);

                subscriptionDAO.add(subscription);

            }
            else {
                SubscriptionWithoutEngagement subscription = new SubscriptionWithoutEngagement();
                subscription.setServiceName(serviceName);
                subscription.setMonthlyAmount(monthlyAmount);
                subscription.setStartDate(LocalDateTime.now());
                subscription.setEndDate(LocalDateTime.now().plusDays(30));
                subscription.setStatus(SubscriptionStatus.active);

                subscriptionDAO.add(subscription);
            }

        }catch(Exception e){
            e.getStackTrace();
        }
    }

    public void updateSubscription(String id ,String serviceName, float monthlyAmount,
                                   Integer monthsEngagementPeriod){

        try{

            Subscription storedSubscription = subscriptionDAO.findById(id);
            Subscription subscription = null;
            if(storedSubscription != null){
                if(serviceName.trim() != "" &&
                        monthlyAmount > 0
                ){
                        if (monthsEngagementPeriod >= 1){
                           subscription = new SubscriptionWithEngagement(serviceName,
                                    monthlyAmount,LocalDateTime.now(),
                                    LocalDateTime.now().plusDays(30),
                                    storedSubscription.getStatus(),monthsEngagementPeriod);
                        subscriptionDAO.update(id,subscription);
                            System.out.println(id+" "+subscription);
                        } else {
                            subscription = new SubscriptionWithoutEngagement(serviceName,
                                    monthlyAmount, LocalDateTime.now(),
                                    LocalDateTime.now().plusDays(30),
                                    storedSubscription.getStatus());
                            subscriptionDAO.update(id, subscription);
                            System.out.println(id+" "+subscription);
                        }
                }

            }

        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void deleteSubscription(String id){

        try{

            if(!id.trim().isEmpty()){

                Subscription dataBaseSubscription = subscriptionDAO.findById(id);

                if(dataBaseSubscription != null){
                    subscriptionDAO.delete(id);
                    System.out.println("deleted");
                }
                else System.err.println("Id is required and can not be null");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List <Subscription> getAllSubscriptions(){

        try{
            List <Subscription> subscriptions = subscriptionDAO.findAll();
            System.out.println(subscriptions);
            return subscriptions;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

