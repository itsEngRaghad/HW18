package com.example.dbhw17.Repository;

import com.example.dbhw17.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

// Check if username and password are correct
    User findUserByUsernameAndPassword(String username, String password);
    User deleteUserById(Integer id);

//Get user by email
  User findUserByEmail(String email);

//Get All users with specific role
    List<User>findUserByRole(String role);

//Get All users with specific age or above
    List<User>findUserByAgeGreaterThanEqual(Integer age);

    // find user by email byJPQL

    @Query("select x from User x where x.email=?1")//?1 means the first parameter in JPQLemail method
    User JPQLemail(String email);




}
