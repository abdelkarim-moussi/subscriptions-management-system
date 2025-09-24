package main.java.util;

import main.java.enums.SubscriptionStatus;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Helper {

    public static String generateId(String pattern){
        String uniqueId = UUID.randomUUID().toString().replace("-", "");
        return (pattern + new BigInteger(uniqueId,16));
    }

    public static String dateFormater(LocalDateTime date){
        return DateTimeFormatter.ofPattern("hh-MM-yy HH-mm-ss").toString();
    }

    //format LocalDateTime to Date
    public static Timestamp dateFormaterToDate(LocalDateTime date){
        return java.sql.Timestamp.valueOf(date);
    }


}
