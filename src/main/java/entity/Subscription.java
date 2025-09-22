package main.java.entity;

import main.java.util.Helper;

import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

public abstract class Subscription {

    protected String id;
    protected String serviceName;
    protected float monthlyAmount;
    protected Date startDate;
    protected Date endDate;
    protected enum status{Active,Suspended,Terminated};

    public Subscription(){
        this.setId();
    }
    Subscription(String serviceName,float monthlyAmount,Date startDate,Date endDate){
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

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
