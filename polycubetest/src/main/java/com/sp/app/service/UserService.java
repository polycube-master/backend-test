package com.sp.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.app.domain.User;
import com.sp.app.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
    	User dto = null;
    	
    	try {
			Optional<User> user = userRepository.findById(id);
			dto = user.get(); 		
		} catch (Exception e) {
			
		}
    	
        return dto;
    }

    public void saveUser(User entitiy) {
    	
    	try {
			
    		userRepository.save(entitiy);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    }
    
    public void update(User entitiy) {
    	
    	try {
			userRepository.save(entitiy);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    }


	

}
