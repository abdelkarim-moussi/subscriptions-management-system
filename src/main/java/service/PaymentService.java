package main.java.service;

import main.java.dao.PaymentDAO;
import main.java.dao.SubscriptionDAO;
import main.java.dao.DAOInterface;
import main.java.entity.Payment;
import main.java.entity.Subscription;
import main.java.enums.PaymentStatus;
import main.java.enums.PaymentType;
import main.java.enums.SubscriptionType;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class PaymentService {

    private DAOInterface subDAOInterface;
    private DAOInterface payDAOInterface;
    private PaymentDAO paymentDao;

    public PaymentService(){
        subDAOInterface = new SubscriptionDAO();
        payDAOInterface = new PaymentDAO();
        paymentDao = new PaymentDAO();
    }

    public Payment createPayment(String subscriptionId,
                                 PaymentType paymentType,
                                 LocalDateTime dueDate,
                                 PaymentStatus paymentStatus) {
        Payment payment = null;

        try{
            if(!subscriptionId.trim().isEmpty()){
//                Subscription subscription = (Subscription) subDAOInterface.findById(subscriptionId);

                if(paymentType != null && paymentStatus != null && dueDate != null){

                    payment = new Payment(subscriptionId,
                            dueDate,
                            LocalDateTime.now(),
                            paymentType,
                            paymentStatus);

                    payDAOInterface.add(payment);
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return payment;

    }

    public int updatePayment(String idPayment,
                             LocalDateTime paymentDate,
                             PaymentType paymentType,
                             PaymentStatus paymentStatus){

        Payment payment;
        try{
            if(!idPayment.trim().isEmpty()){

                Payment dbPayment = (Payment) payDAOInterface.findById(idPayment);
                if(dbPayment != null){
                    if(paymentType != null && paymentDate != null){
                            payment = new Payment();
                            payment.setPaymentDate(paymentDate);
                            payment.setPaymentType(paymentType);
                            payment.setPaymentStatus(paymentStatus);
                            int rowResult = payDAOInterface.update(idPayment,payment);

                            return rowResult;
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public int deletePayment(String id){

        try{

            if(!id.trim().isEmpty()){

                Payment dbPayment = (Payment) payDAOInterface.findById(id);

                if(dbPayment != null){
                    return payDAOInterface.delete(id);
                }
                else System.err.println("Id is required and can not be null");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<Payment> getSubscriptionPayments(String subId) throws SQLException{

        List<Payment> subPayments = null;

        if(!subId.trim().isEmpty()){
            Subscription subscription = (Subscription) subDAOInterface.findById(subId);
            if(subscription != null){
                  subPayments = paymentDao.findBySubscription(subId);

            }
        }

        return subPayments;
    }

    public List<Payment> getMissedPayments(String Id){

        List<Payment> missedPayments = null;

        try {
            Subscription subscription = (Subscription)subDAOInterface.findById(Id);
            System.out.println(subscription);
            if(subscription != null){
                if(subscription.getSubscriptionType() == SubscriptionType.subscription_with_engagement){
                    List<Payment> payments = paymentDao.findBySubscription(Id);

                    if(payments != null){
                        missedPayments =  payments.stream().filter(payment -> payment.getDueDate().isBefore(LocalDateTime.now()))
                                .collect(Collectors.toList());

                    }
                }
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return missedPayments;
    }

    public List<Payment> lastPayments(){
        List<Payment> lastPayments = null;
        try{
            List<Payment> payments = payDAOInterface.findAll();
            payments.sort(Comparator.comparing(Payment::getDueDate).reversed());
            lastPayments = payments.stream().limit(5).collect(Collectors.toList());

        }catch (SQLException e){
            e.printStackTrace();
        }

        return lastPayments;

    }
}
