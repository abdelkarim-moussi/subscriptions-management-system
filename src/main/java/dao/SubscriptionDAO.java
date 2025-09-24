package main.java.dao;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import main.java.entity.Subscription;
import main.java.entity.SubscriptionWithEngagement;
import main.java.enums.SubscriptionStatus;
import main.java.enums.SubscriptionType;
import main.java.util.DataBaseConnection;
import main.java.util.Helper;

import java.sql.*;
import java.util.List;

public class SubscriptionDAO implements SubscriptionDAOInterface<Subscription,String>{

    static Connection connection = DataBaseConnection.getConnection();

    @Override
    public void addSubscription(Subscription subscription) throws SQLException{
        try{
        Statement statement = connection.createStatement();

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
            System.out.println(e.getMessage());;
        }
    }

    @Override
    public void deleteSubscription(String s) throws SQLException {

    }

    @Override
    public void findSubscriptionById(String s) throws SQLException {

    }

    @Override
    public void findAllSubscription(String s) throws SQLException {

    }

    @Override
    public void updateSubscription(String s, List<Subscription> data) throws SQLException {

    }
}

