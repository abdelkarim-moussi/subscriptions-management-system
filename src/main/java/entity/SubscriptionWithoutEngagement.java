package main.java.entity;

import main.java.entity.Subscription;

import java.util.Date;

public class SubscriptionWithoutEngagement extends Subscription{

    public SubscriptionWithoutEngagement(){}

    public SubscriptionWithoutEngagement(String serviceName, float monthlyAmount, Date startDate, Date endDate){
        super(serviceName,monthlyAmount,startDate,endDate);
    }

}
