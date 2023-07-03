package com.Spring.SpringBoot.configuration;

import com.Spring.SpringBoot.Dao.UserDao;
import com.Spring.SpringBoot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserdetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user= userDao.findByEmailIgnoreCase(email);
       if(user == null)
       {
           throw new UsernameNotFoundException("Could not found user");
       }
       //CustomUserDetail customUserDetail=new CustomUserDetail(user);
        return new CustomUserDetail(user);
    }
}
