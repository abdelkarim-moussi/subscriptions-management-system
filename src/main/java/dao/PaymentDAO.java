package main.java.dao;

import main.java.entity.Payment;
import main.java.enums.PaymentStatus;
import main.java.enums.PaymentType;
import main.java.util.DataBaseConnection;
import main.java.util.Helper;
import org.postgresql.jdbc.PgConnection;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class PaymentDAO implements DAOInterface <Payment,String> {

    static Connection connection = DataBaseConnection.getConnection();

    @Override
    public void add(Payment payment) throws SQLException {

            String insertSql = "INSERT INTO payments (id, subscriptionid, duedate, paymentdate, type, status) VALUES(? ,? ,?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);

        try{

            if(payment != null){
                insertStatement.setString(1,payment.getId());
                insertStatement.setString(2,payment.getSubscriptionId());
                insertStatement.setTimestamp(3, Helper.dateFormaterToDate(payment.getDueDate()));
                insertStatement.setTimestamp(4,Helper.dateFormaterToDate(payment.getPaymentDate()));
                insertStatement.setObject(5,payment.getPaymentType(),Types.OTHER);
                insertStatement.setObject(6,payment.getPaymentStatus(), Types.OTHER);

                int rowSet = insertStatement.executeUpdate();

                System.out.println("row inserted succefully - "+rowSet);
            }
            insertStatement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public int update(String id, Payment payment) throws SQLException {

        String updateSql = "UPDATE payments SET duedate = ? , paymentdate = ?, type = ?, status = ? WHERE id = ?";
        PreparedStatement updateStatement = connection.prepareStatement(updateSql);

        try{
            updateStatement.setTimestamp(1,Helper.dateFormaterToDate(payment.getDueDate()));
            updateStatement.setTimestamp(2,Helper.dateFormaterToDate(payment.getPaymentDate()));
            updateStatement.setObject(3,payment.getPaymentType(),Types.OTHER);
            updateStatement.setObject(4,payment.getPaymentStatus(),Types.OTHER);
            updateStatement.setString(5,id);

            int rowAffected = updateStatement.executeUpdate();

            updateStatement.close();
            System.out.println("rs : "+rowAffected);
            return rowAffected;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int delete(String s) throws SQLException {
        return 0;
    }

    @Override
    public Payment findById(String id) throws SQLException {

        String findOneSql = "SELECT * FROM payments WHERE id = ?";
        PreparedStatement findOneStatement = connection.prepareStatement(findOneSql);
        Payment payment = null;

        try{
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

        }catch (SQLException e){
            e.printStackTrace();
        }
        return payment;
    }

    @Override
    public List<Payment> findAll() throws SQLException {
        return Collections.emptyList();
    }
}
