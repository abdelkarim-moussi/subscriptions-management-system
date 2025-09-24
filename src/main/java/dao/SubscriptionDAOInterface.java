package main.java.dao;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDAOInterface<T,S> {
    void addSubscription(T t) throws SQLException;
    void updateSubscription(S s ,List<T> data) throws SQLException;
    void deleteSubscription(S s) throws SQLException;
    void findSubscriptionById(S s) throws SQLException;
    void findAllSubscription(S s) throws SQLException;
}
