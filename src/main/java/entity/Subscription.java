package main.java.entity;

import main.java.enums.SubscriptionStatus;
import main.java.util.Helper;

import java.time.LocalDateTime;

public abstract class Subscription {

    protected String id;
    protected String serviceName;
    protected float monthlyAmount;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected SubscriptionStatus status;

    public Subscription(){
        this.setId();
    }
    Subscription(String serviceName,float monthlyAmount,LocalDateTime startDate,LocalDateTime endDate){
        this.setId();
        this.serviceName = serviceName;
        this.monthlyAmount = monthlyAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId(){
        return this.id;
    }
    public void setId(){
        this.id = Helper.generateId("sub-");
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public float getMonthlyAmount() {
        return this.monthlyAmount;
    }

    public void setMonthlyAmount(float monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public SubscriptionStatus getStatus(){
        return this.status;
    }

    public void setStatus(SubscriptionStatus status){
        this.status = status;
    }
}
