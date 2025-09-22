package main.java.util;

import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public class Helper {

    public static String generateId(String pattern){
        String uniqueId = UUID.randomUUID().toString().replace("-", "");
        return (pattern + new BigInteger(uniqueId,16));
    }

    public static String dateFormater(Date date){
        return DateTimeFormatter.ofPattern("hh-MM-yy HH-mm-ss").toString();
    }
}
