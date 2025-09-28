package main.java.dao;

import main.java.entity.Subscription;
import main.java.entity.SubscriptionWithEngagement;
import main.java.entity.SubscriptionWithoutEngagement;
import main.java.enums.SubscriptionStatus;
import main.java.enums.SubscriptionType;
import main.java.util.DataBaseConnection;
import main.java.util.Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAO implements DAOInterface<Subscription,String> {

    static Connection connection = DataBaseConnection.getConnection();


    @Override
    public int add(Subscription subscription) throws SQLException{
        String insertSQL = "INSERT INTO subscriptions (id, servicename, monthlyamount, startdate, enddate, monthsengagementperiod,status,subscriptionType) VALUES (? , ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertSQL);

        if(subscription != null){
            insertStatement.setString(1,subscription.getId());
            insertStatement.setString(2,subscription.getServiceName());
            insertStatement.setFloat(3,subscription.getMonthlyAmount());
            insertStatement.setTimestamp(4,Helper.dateFormaterToDate(subscription.getStartDate()));
            insertStatement.setTimestamp(5,Helper.dateFormaterToDate(subscription.getEndDate()));
            insertStatement.setInt(6,subscription.getMonthsEngagementPeriod());
            insertStatement.setObject(7,subscription.getStatus(),Types.OTHER);
            insertStatement.setObject(8, subscription.getSubscriptionType(),Types.OTHER);

            int rowAffected = insertStatement.executeUpdate();
            return rowAffected;
        }

        return 0;
    }

    @Override
    public int update(String id, Subscription subscription) throws SQLException {

            String updateSQL = "UPDATE subscriptions SET monthlyamount = ?, startdate = ?, enddate = ?, monthsengagementperiod = ?,status = ? ,subscriptionType = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);

            if(!id.trim().isEmpty() && subscription != null){

                updateStatement.setFloat(1,subscription.getMonthlyAmount());
                updateStatement.setTimestamp(2,Helper.dateFormaterToDate(subscription.getStartDate()));
                updateStatement.setTimestamp(3,Helper.dateFormaterToDate(subscription.getEndDate()));
                updateStatement.setInt(4,subscription.getMonthsEngagementPeriod());
                updateStatement.setObject(5,subscription.getStatus(),Types.OTHER);
                updateStatement.setObject(6, subscription.getSubscriptionType(),Types.OTHER);
                updateStatement.setString(7,id);

                int rowAffected = updateStatement.executeUpdate();
                updateStatement.close();

                return rowAffected;

            }

        return 0;

    }

    @Override
    public int delete(String id) throws SQLException {

        if(!id.trim().isEmpty()){
            String deleteSql = "DELETE FROM subscriptions WHERE id = ?";
            String deletePaymentsSql = "DELETE FROM payments WHERE subscriptionid = ?";
            connection.setAutoCommit(false);

            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setString(1,id);
            deleteStatement.executeUpdate();

            PreparedStatement deletePaymentsStatement = connection.prepareStatement(deletePaymentsSql);
            deletePaymentsStatement.setString(1,id);
            deletePaymentsStatement.executeUpdate();

            connection.commit();
            connection.rollback();

            return 1;
        }
        return 0;

    }

    @Override
    public Subscription findById(String id) throws SQLException {

        String findOneSql = "SELECT * FROM subscriptions WHERE id = ?";
        PreparedStatement findOneStatement = connection.prepareStatement(findOneSql);

            findOneStatement.setString(1, id);
            ResultSet storedSubscription = findOneStatement.executeQuery();

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

        return null;
    }

    @Override
    public List <Subscription> findAll() throws SQLException {

        List <Subscription> subscriptions = new ArrayList<>();

        String selectAllSql = "SELECT * FROM subscriptions";

        Statement selectAllStatement = connection.createStatement();

            ResultSet resultSet = selectAllStatement.executeQuery(selectAllSql);

            while(resultSet.next()){
                if(resultSet.getString("subscriptiontype") != null){
                    if (resultSet.getString("subscriptiontype").equals(SubscriptionType.subscription_without_engagement.toString())) {
                        Subscription subscription = new SubscriptionWithoutEngagement(
                                resultSet.getString("servicename"),
                                resultSet.getFloat("monthlyamount"),
                                resultSet.getTimestamp("startdate").toLocalDateTime(),
                                resultSet.getTimestamp("enddate").toLocalDateTime(),
                                SubscriptionStatus.valueOf(resultSet.getString("status"))
                        );

                        subscriptions.add(subscription);

                    } else if (resultSet.getString("subscriptiontype").equals(SubscriptionType.subscription_with_engagement.toString())) {

                        Subscription subscription = new SubscriptionWithEngagement(
                                resultSet.getString("servicename"),
                                resultSet.getFloat("monthlyamount"),
                                resultSet.getTimestamp("startdate").toLocalDateTime(),
                                resultSet.getTimestamp("enddate").toLocalDateTime(),
                                SubscriptionStatus.valueOf(resultSet.getString("status")),
                                resultSet.getInt("monthsengagementperiod")
                        );
                        subscriptions.add(subscription);
                    }
                }

            }


        return subscriptions;
    }

}

