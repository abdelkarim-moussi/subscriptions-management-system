package main.java.entity;

import main.java.enums.SubscriptionStatus;

import java.time.LocalDateTime;

public class SubscriptionWithoutEngagement extends Subscription{

    public SubscriptionWithoutEngagement(){}

    public SubscriptionWithoutEngagement(String serviceName, float monthlyAmount,
                                         LocalDateTime startDate, LocalDateTime endDate,
                                         SubscriptionStatus status){
        super(serviceName,monthlyAmount,startDate,endDate,status);
    }

    @Override
    public Integer getMonthsEngagementPeriod() {
        return 0;
    }

    public String toString(){
        return "Subscription Without Engagement : \n" +
                "Service Name : "+ this.getServiceName() +
                "\nStatus : "+ this.getStatus() +
                "\nMonthly Amount : "+ this.getMonthlyAmount() +
                "\nStart Date : "+ this.getStartDate() +
                "\nEnd Date : "+ this.getEndDate();
    }

}
