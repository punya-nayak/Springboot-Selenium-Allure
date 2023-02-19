package com.softwaretestingboard.Utilities.UserUtilities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*Spring Repository annotation is a specialization of @Component annotation,
so this interface is autodetected by spring framework through classpath scanning.
This interface only has methods which are used to retrieve users */

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /*These interfaces are being implemented by Spring Container at Runtime based on naming conventions*/
    List<User> findByUsertypeAndStatus(String usertype, String status);

    List<User> findByUsername(String username);

}
