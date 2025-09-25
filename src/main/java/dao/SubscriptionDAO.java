package main.java.dao;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import main.java.entity.Subscription;
import main.java.entity.SubscriptionWithEngagement;
import main.java.entity.SubscriptionWithoutEngagement;
import main.java.enums.SubscriptionStatus;
import main.java.enums.SubscriptionType;
import main.java.util.DataBaseConnection;
import main.java.util.Helper;

import java.sql.*;
import java.util.List;

public class SubscriptionDAO implements SubscriptionDAOInterface<Subscription,String>{

    static Connection connection = DataBaseConnection.getConnection();

    @Override
    public void add(Subscription subscription) throws SQLException{
        try{

        String insertSQL = "INSERT INTO subscriptions (id, servicename, monthlyamount, startdate, enddate, monthsengagementperiod,status,subscriptionType) VALUES (? , ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
        insertStatement.setString(1,subscription.getId());
        insertStatement.setString(2,subscription.getServiceName());
        insertStatement.setFloat(3,subscription.getMonthlyAmount());
        insertStatement.setTimestamp(4,Helper.dateFormaterToDate(subscription.getStartDate()));
        insertStatement.setTimestamp(5,Helper.dateFormaterToDate(subscription.getEndDate()));
        insertStatement.setInt(6,subscription.getMonthsEngagementPeriod());
        insertStatement.setObject(7,subscription.getStatus(),Types.OTHER);


        if(subscription instanceof SubscriptionWithEngagement){
            insertStatement.setObject(8, SubscriptionType.subscription_with_engagement,Types.OTHER);
        }
        else
            insertStatement.setObject(8, SubscriptionType.subscription_without_engagement,Types.OTHER);

        int rowAffected = insertStatement.executeUpdate();

            System.out.println(
                    "Inserted data into 'subscriptions' table! row : "+rowAffected);

        }catch(SQLException e){
            e.getStackTrace();
        }
    }

    @Override
    public void update(String id, Subscription subscription) throws SQLException {

        try{
            if(id != null && subscription != null){

                String updateSQL = "UPDATE subscriptions SET monthlyamount = ?, startdate = ?, enddate = ?, monthsengagementperiod = ?,status = ? ,subscriptionType = ? WHERE id = ?";

                PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
                updateStatement.setFloat(1,subscription.getMonthlyAmount());
                updateStatement.setTimestamp(2,Helper.dateFormaterToDate(subscription.getStartDate()));
                updateStatement.setTimestamp(3,Helper.dateFormaterToDate(subscription.getEndDate()));
                updateStatement.setInt(4,subscription.getMonthsEngagementPeriod());
                updateStatement.setObject(5,subscription.getStatus(),Types.OTHER);
                updateStatement.setString(6,id);


                if(subscription instanceof SubscriptionWithEngagement){
                    updateStatement.setObject(8, SubscriptionType.subscription_with_engagement,Types.OTHER);
                }
                else
                    updateStatement.setObject(8, SubscriptionType.subscription_without_engagement,Types.OTHER);

                int rowAffected = updateStatement.executeUpdate();

                System.out.println(
                        "row updated successfully : "+subscription + "-"+rowAffected);

                updateStatement.close();
            }

        }catch (Exception e){
            e.getStackTrace();
        }

    }

    @Override
    public void delete(String s) throws SQLException {

    }

    @Override
    public Subscription findById(String id) throws SQLException {

        String findOneSql = "SELECT * FROM subscriptions WHERE id = ?";

        try (PreparedStatement findOneStatement = connection.prepareStatement(findOneSql)) {

            findOneStatement.setString(1, id);
            ResultSet storedSubscription = findOneStatement.executeQuery();

            Subscription subscription;

            if (storedSubscription.next()) {

                if (storedSubscription.getString("subscriptiontype").equals(SubscriptionType.subscription_without_engagement.toString())) {
                   return new SubscriptionWithoutEngagement(
                            storedSubscription.getString("servicename"),
                            storedSubscription.getFloat("monthlyamount"),
                            storedSubscription.getTimestamp("startdate").toLocalDateTime(),
                            storedSubscription.getTimestamp("enddate").toLocalDateTime(),
                            SubscriptionStatus.valueOf(storedSubscription.getString("status"))
                    );

                } else if (storedSubscription.getString("subscriptiontype").equals(SubscriptionType.subscription_with_engagement.toString())) {

                    return new SubscriptionWithEngagement(
                            storedSubscription.getString("servicename"),
                            storedSubscription.getFloat("monthlyamount"),
                            storedSubscription.getTimestamp("startdate").toLocalDateTime(),
                            storedSubscription.getTimestamp("enddate").toLocalDateTime(),
                            SubscriptionStatus.valueOf(storedSubscription.getString("status")),
                            storedSubscription.getInt("monthsengagementperiod")
                    );

                }
            }
            storedSubscription.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Subscription findAll(String s) throws SQLException {
        return null;
    }

}

