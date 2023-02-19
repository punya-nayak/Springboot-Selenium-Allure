package com.softwaretestingboard.Utilities.UserUtilities;

import com.softwaretestingboard.Base.BasePage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserUtils extends BasePage {

    @Autowired
    public UserManagement userManagement;

    @Autowired
    private UserRepository repository;

    private static final String lockObject = "l1";
    private static final String lockObject1 = "l2";


    /**
     * Once the users are loaded to repository in basepage, this method is used to get the first user of the mentioned type
     * Once the user is chosen, it is entered in a busy user map with current thread
     * The status of it is marked as busy and saved in table.
     *
     * @param usertype mentioned UserType
     * @return User to be used to login
     */
    public synchronized String getUser(String usertype) {
        String freeUserName = null;
        List<User> userList = null;
        synchronized (lockObject) {
            User freeUser = null;
            try {
                userList = new ArrayList<>(this.repository.findByUsertypeAndStatus(usertype.toLowerCase(), "free"));
                freeUser = userList.get(0);
                freeUserName = (userList.get(0).getUsername());
                System.out.println("User chosen: " + freeUserName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            assert freeUser != null;
            freeUser.setStatus("busy");
            System.out.println("Setting status of " + freeUserName + " to " + freeUser.getStatus() + "Thread name " + Thread.currentThread().getName());
            this.userManagement.setBusyUserMap(Thread.currentThread().getName(), freeUserName);
            repository.save(freeUser);
        }
        return freeUserName;
    }

    /**
     * @param username Username for which password is required
     * @return password of that user
     */
    public synchronized String getPassword(String username) {
        String password = null;
        List<User> usr = null;
        synchronized (this) {
            try {
                usr = repository.findByUsername(username);
                password = (usr.get(0).getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return password;
        }

    }

    /**
     * @param username Username which needs to be reset to free
     */
    public synchronized void resetUserToFree(String username) {
        List<User> userList = null;
        synchronized (lockObject1) {
            try {
                userList = repository.findByUsername(username);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (userList != null) {
                User user = userList.get(0);
                user.setStatus("free");
                repository.save(user);
            }
        }
    }
}
