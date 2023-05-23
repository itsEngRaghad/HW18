package com.example.dbhw17.Service;

import com.example.dbhw17.APIException.APIException;
import com.example.dbhw17.Model.User;
import com.example.dbhw17.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //get
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //add user

    public void addUser(User user){
        userRepository.save(user);
    }

    //update
    public boolean updateUser(User user, Integer id){
        User oldUser=userRepository.getById(id);
        if(oldUser==null){
            return false;
        }

        oldUser.setName(user.getName());
        oldUser.setAge(user.getAge());
        oldUser.setRole(user.getRole());
        oldUser.setEmail(user.getEmail());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());

        userRepository.save(oldUser); //save it in the olduser
        return true;
    }

    //Delete
    public boolean deleteUser(Integer id){
        User oldUser=userRepository.getById(id);
        if(oldUser==null){
            return false;
        }
        userRepository.delete(oldUser);
        return true;
    }

//    Get user by email
    public User findUserByEmail(String email){
        User user=userRepository.findUserByEmail(email);
        if(email==null){
            throw new APIException("Email Not Found, Please Try Again");
        }
        return user;
    }

//    Get All users with specific role

    public List<User> findUserByRole(String role){
        List<User>users=userRepository.findUserByRole(role);
        return users;
    }

//    Get All users with specific age or above

    public List<User>findUserByAgeGreaterThanEqual(Integer age){
        List<User>users=userRepository.findUserByAgeGreaterThanEqual(age);
        return users;
    }

//    Check if username and password are correct

    public User findUserByUsernameAndPassword(String username, String password){
     User user=userRepository.findUserByUsernameAndPassword(username,password);
        if(user==null)
        {
            throw new APIException("User, Password Not Found, Please Try Again");
        }
        return user;
    }

    // find user by email byJPQL

    public User JPQLemail(String email){
        User user=userRepository.findUserByEmail(email);
        if(email==null){
            throw new APIException("Email Not Found, Please Try Again");
        }
        return user;
    }

    //done, done

}
