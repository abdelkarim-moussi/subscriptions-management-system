package main.java.entity;

import main.java.enums.PaymentStatus;
import main.java.enums.PaymentType;
import main.java.util.Helper;

import java.time.LocalDateTime;

public class Payment {

    private String id;
    private String subscriptionId;
    private LocalDateTime dueDate;
    private LocalDateTime paymentDate;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;

    public Payment(){
        this.setId();
    }

    public Payment(String subscriptionId,
                   LocalDateTime dueDate,
                   LocalDateTime paymentDate,
                   PaymentType  paymentType,
                   PaymentStatus paymentStatus){

        this.setId();
        this.subscriptionId = subscriptionId;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = Helper.generateId("pay-");
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "\nid-Subscription='" + subscriptionId + '\'' +
                "\ndueDate=" + dueDate +
                "\npaymentDate=" + paymentDate +
                "\npaymentType=" + paymentType +
                "\npaymentStatus=" + paymentStatus +
                '}';
    }
}
