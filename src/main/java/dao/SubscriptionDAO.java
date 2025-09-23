package main.java.dao;

import main.java.entity.Subscription;
import main.java.util.DataBaseConnection;
import main.java.util.Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAO {

    static Connection connection = DataBaseConnection.getConnection();

    public static void addSubscription(List<Subscription> data){

        try{
        Statement statement = connection.createStatement();

        //String values = "("+ +","++","++","+ Helper.dateFormater(data.get(0).getStartDate()) +","+Helper.dateFormater(data.get(0).getEndDate())+","+10 +")";

        String insertSQL = "INSERT INTO subscriptions (id, servicename, monthlyamount, startdate, enddate, monthsengagementperiod) VALUES (? , ?, ?, ?, ?, ?)";

        PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
        insertStatement.setString(1,data.get(0).getId());
        insertStatement.setString(2,data.get(0).getServiceName());
        insertStatement.setFloat(3,data.get(0).getMonthlyAmount());
        insertStatement.setTimestamp(4,Helper.dateFormaterToDate(data.get(0).getStartDate()));
        insertStatement.setTimestamp(5,Helper.dateFormaterToDate(data.get(0).getEndDate()));
        insertStatement.setInt(6,10);

        int rowAffected = insertStatement.executeUpdate();

            System.out.println(
                    "Inserted data into 'subscriptions' table! row : "+rowAffected);

        }catch(SQLException e){
            System.out.println(e.getMessage());;
        }
    }
}
