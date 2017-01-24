package hr.foi.air.solex.utils;

/**
 * Created by Asus on 12.11.2016..
 */

public enum UserType {
    COMPANY("company", 0),
    DEVELOPER("developer", 1);

    private String stringValue;
    private int intValue;
    UserType(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    public String stringVal() {
        return stringValue;
    }

    public int intVal(){
        return intValue;
    }
}
