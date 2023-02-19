package com.softwaretestingboard.Utilities.UserUtilities;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;


@Component
public class UserManagement {

    public LinkedHashMap<String, String> busyUserMap = new LinkedHashMap<>();

    public void setBusyUserMap(String threadName, String userName) {
        busyUserMap.put(threadName, userName);
    }

    public String getBusyUserFromMap(String threadName) {
        return busyUserMap.get(threadName);
    }
}
