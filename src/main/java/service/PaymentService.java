package main.java.service;

import main.java.dao.PaymentDAO;
import main.java.dao.SubscriptionDAO;
import main.java.dao.DAOInterface;
import main.java.entity.Payment;
import main.java.entity.Subscription;
import main.java.enums.PaymentStatus;
import main.java.enums.PaymentType;

import java.time.LocalDateTime;


public class PaymentService {

    private DAOInterface subDAOInterface;

    private DAOInterface payDAOInterface;

    public PaymentService(){
        subDAOInterface = new SubscriptionDAO();
        payDAOInterface = new PaymentDAO();
    }

    public Payment createPayment(String subscriptionId,
                                 PaymentType paymentType,
                                 PaymentStatus paymentStatus){
        Payment payment = null;

        try{
            if(!subscriptionId.trim().isEmpty()){
                if(paymentType != null && paymentStatus != null){

                    Subscription subscription = (Subscription) subDAOInterface.findById(subscriptionId);
                    System.out.println(subscription);
                    payment = new Payment(subscriptionId,
                            LocalDateTime.now().plusDays(30),
                            LocalDateTime.now(),
                            paymentType,paymentStatus);

                    payDAOInterface.add(payment);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return payment;

    }

    public int updatePayment(String idPayment, String subscriptionId,
                             LocalDateTime dueDate, LocalDateTime paymentDate,
                             PaymentType paymentType, PaymentStatus paymentStatus){

        Payment payment;
        try{
            if(!idPayment.trim().isEmpty()){

                Payment dbPayment = (Payment)payDAOInterface.findById(idPayment);
                if(dbPayment != null){
                    if(!subscriptionId.trim().isEmpty() && paymentType != null && paymentStatus != null){
                        if(subscriptionId.equals(dbPayment.getSubscriptionId())){
                            payment = new Payment(subscriptionId,dueDate,paymentDate,paymentType,paymentStatus);
                            int res = payDAOInterface.update(idPayment,payment);
                            System.out.println(res);
                            return res;
                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }
}
