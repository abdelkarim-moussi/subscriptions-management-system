package main.java.entity;

import java.time.LocalDateTime;

public class SubscriptionWithoutEngagement extends Subscription{

    public SubscriptionWithoutEngagement(){}

    public SubscriptionWithoutEngagement(String serviceName, float monthlyAmount, LocalDateTime startDate, LocalDateTime endDate){
        super(serviceName,monthlyAmount,startDate,endDate);
    }

    @Override
    public Integer getMonthsEngagementPeriod() {
        return null;
    }

    public String toString(){
        return "Subscription Without Engagement : \n" +
                "Service Name : "+ this.getServiceName() +
                "\nMonthly Amount : "+ this.getMonthlyAmount() +
                "\nStart Date : "+ this.getStartDate() +
                "\nEnd Date : "+ this.getEndDate();
    }

}
