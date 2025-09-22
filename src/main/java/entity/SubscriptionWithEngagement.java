package main.java.entity;
import main.java.entity.Subscription;

import java.util.Date;

public class SubscriptionWithEngagement extends Subscription{

    private int monthsEngagementPeriod;

    public SubscriptionWithEngagement(){}

    SubscriptionWithEngagement(String serviceName, float monthlyAmount, Date startDate, Date endDate,int monthsEngagementPeriod){
        super(serviceName,monthlyAmount,startDate,endDate);
                this.monthsEngagementPeriod = monthsEngagementPeriod;
    }


}
