package main.java.dao;

import main.java.entity.Payment;
import main.java.enums.PaymentStatus;
import main.java.enums.PaymentType;
import main.java.util.DataBaseConnection;
import main.java.util.Helper;
import org.postgresql.jdbc.PgConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentDAO implements DAOInterface <Payment,String> {

    static Connection connection = DataBaseConnection.getConnection();

    @Override
    public int add(Payment payment) throws SQLException {

            String insertSql = "INSERT INTO payments (id, subscriptionid, duedate, paymentdate, type, status) VALUES(? ,? ,?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);

            if(payment != null){
                insertStatement.setString(1,payment.getId());
                insertStatement.setString(2,payment.getSubscriptionId());
                insertStatement.setTimestamp(3, Helper.dateFormaterToDate(payment.getDueDate()));
                insertStatement.setTimestamp(4,Helper.dateFormaterToDate(payment.getPaymentDate()));
                insertStatement.setObject(5,payment.getPaymentType(),Types.OTHER);
                insertStatement.setObject(6,payment.getPaymentStatus(), Types.OTHER);

                int rowSet = insertStatement.executeUpdate();

                return rowSet;
            }
            insertStatement.close();
            return 0;

    }

    @Override
    public int update(String id, Payment payment) throws SQLException {

        String updateSql = "UPDATE payments SET duedate = ? , paymentdate = ?, type = ?, status = ? WHERE id = ?";
        PreparedStatement updateStatement = connection.prepareStatement(updateSql);

            updateStatement.setTimestamp(1,Helper.dateFormaterToDate(payment.getDueDate()));
            updateStatement.setTimestamp(2,Helper.dateFormaterToDate(payment.getPaymentDate()));
            updateStatement.setObject(3,payment.getPaymentType(),Types.OTHER);
            updateStatement.setObject(4,payment.getPaymentStatus(),Types.OTHER);
            updateStatement.setString(5,id);

            int rowAffected = updateStatement.executeUpdate();

            updateStatement.close();
            System.out.println("rs : "+rowAffected);
            return rowAffected;

    }

    @Override
    public int delete(String id) throws SQLException {

        if(!id.trim().isEmpty()){
            String deleteSql = "DELETE FROM payments WHERE id = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);

            deleteStatement.setString(1,id);
            int rowsAffected = deleteStatement.executeUpdate();
            return rowsAffected;
        }
        else return 0;
    }

    @Override
    public Payment findById(String id) throws SQLException {

        String findOneSql = "SELECT * FROM payments WHERE id = ?";
        PreparedStatement findOneStatement = connection.prepareStatement(findOneSql);
        Payment payment = null;

            if(!id.trim().isEmpty()){
            findOneStatement.setString(1,id);
            ResultSet resultSet = findOneStatement.executeQuery();

            while (resultSet.next()){
                payment = new Payment(
                        resultSet.getString("subscriptionid"),
                        resultSet.getTimestamp("duedate").toLocalDateTime(),
                        resultSet.getTimestamp("paymentdate").toLocalDateTime(),
                        PaymentType.valueOf(resultSet.getObject("type").toString()),
                        PaymentStatus.valueOf(resultSet.getObject("status").toString()));
            }

            }

        return payment;
    }

    public List<Payment> findBySubscription(String subId) throws SQLException{

        List<Payment> subPayments = new ArrayList<>();
        String findBySubSql = "SELECT * FROM payments WHERE subscriptionid = ?";
        PreparedStatement findBySubStatement = connection.prepareStatement(findBySubSql);

        if(!subId.trim().isEmpty()){
        findBySubStatement.setString(1,subId);

        ResultSet resultSet = findBySubStatement.executeQuery();

        while (resultSet.next()){
            Payment payment = new Payment(resultSet.getString("subscriptionid"),
                    resultSet.getTimestamp("duedate").toLocalDateTime(),
                    resultSet.getTimestamp("paymentdate").toLocalDateTime(),
                    PaymentType.unknown,
                    PaymentStatus.valueOf(resultSet.getObject("status").toString()));

            subPayments.add(payment);
        }

        }

        return subPayments;

    }

    @Override
    public List<Payment> findAll() throws SQLException {
        return Collections.emptyList();
    }
}
