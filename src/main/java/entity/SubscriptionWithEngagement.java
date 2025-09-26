package main.java.entity;

import main.java.enums.SubscriptionStatus;

import java.time.LocalDateTime;

public class SubscriptionWithEngagement extends Subscription{
    private Integer monthsEngagementPeriod;

    public SubscriptionWithEngagement() {}

    public SubscriptionWithEngagement(String serviceName, float monthlyAmount,
                                      LocalDateTime startDate, LocalDateTime endDate,
                                      SubscriptionStatus status,
                                      int monthsEngagementPeriod) {
        super(serviceName, monthlyAmount, startDate, endDate,status);
        this.monthsEngagementPeriod = monthsEngagementPeriod;
    }

    @Override
    public Integer getMonthsEngagementPeriod() {
        return this.monthsEngagementPeriod;
    }

    public void setMonthsEngagementPeriod(Integer monthsEngagementPeriod) {
        this.monthsEngagementPeriod = monthsEngagementPeriod;
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
