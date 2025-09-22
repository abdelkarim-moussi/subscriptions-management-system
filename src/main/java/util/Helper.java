package main.java.util;

import java.math.BigInteger;
import java.util.UUID;

public class Helper {

    public static String generateId(String pattern){
        String uniqueId = UUID.randomUUID().toString().replace("-", "");
        return (pattern + new BigInteger(uniqueId,16));
    }
}
