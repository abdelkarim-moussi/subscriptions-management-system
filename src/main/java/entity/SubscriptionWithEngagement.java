package main.java.entity;

import main.java.enums.SubscriptionStatus;
import main.java.enums.SubscriptionType;

import java.time.LocalDateTime;

public class SubscriptionWithEngagement extends Subscription{
    private Integer monthsEngagementPeriod;

    public SubscriptionWithEngagement() {this.setSubscriptionType();}

    public SubscriptionWithEngagement(String serviceName, float monthlyAmount,
                                      LocalDateTime startDate, LocalDateTime endDate,
                                      SubscriptionStatus status,
                                      int monthsEngagementPeriod) {
        super(serviceName, monthlyAmount, startDate, endDate,status);
        this.monthsEngagementPeriod = monthsEngagementPeriod;
        this.setSubscriptionType();
    }

    @Override
    public Integer getMonthsEngagementPeriod() {
        return this.monthsEngagementPeriod;
    }

    public void setMonthsEngagementPeriod(Integer monthsEngagementPeriod) {
        this.monthsEngagementPeriod = monthsEngagementPeriod;
    }

    public void setSubscriptionType(){
        this.subscriptionType = SubscriptionType.subscription_with_engagement;
    }

    public String toString(){

        return "Subscription With Engagement : \n" +
                "Service Name : "+ this.getServiceName() +
                "\nStatus : "+ this.getStatus() +
                "\nMonths Engagement Period "+ this.monthsEngagementPeriod +
                "\nMonthly Amount : "+ this.getMonthlyAmount() +
                "\nStart Date : "+ this.getStartDate() +
                "\nEnd Date : "+ this.getEndDate()+"\n";
    }

}
