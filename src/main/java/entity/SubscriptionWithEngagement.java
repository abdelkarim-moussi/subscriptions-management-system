package main.java.entity;

import java.time.LocalDateTime;

public class SubscriptionWithEngagement extends Subscription{
    private Integer monthsEngagementPeriod;

    public SubscriptionWithEngagement() {}

    SubscriptionWithEngagement(String serviceName, float monthlyAmount, LocalDateTime startDate, LocalDateTime endDate, int monthsEngagementPeriod) {
        super(serviceName, monthlyAmount, startDate, endDate);
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
                "\nMonths Engagement Period "+ this.monthsEngagementPeriod +
                "\nMonthly Amount : "+ this.getMonthlyAmount() +
                "\nStart Date : "+ this.getStartDate() +
                "\nEnd Date : "+ this.getEndDate();
    }


}
