package com.jview.security;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jview.dao.UserDao;
import com.jview.model.User;


public class UserDetailServices implements UserDetailsService {

	private ApplicationContext context;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
    	this.context = new ClassPathXmlApplicationContext("META-INF/jview-context.xml");
 		UserDao userDao = (UserDao) this.context.getBean("UserDao");
        
        User user = null;
		try {
			user = userDao.findByUserName(username);
			return new UserDetailsApp(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
    }
}

