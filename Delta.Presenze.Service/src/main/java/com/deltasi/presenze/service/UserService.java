/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deltasi.presenze.service;

import com.deltasi.presenze.dao.UserDao;
import com.deltasi.presenze.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.deltasi.presenze.contracts.IUserService;
import org.apache.log4j.Logger;

/**
 *
 * @author AdminDSI
 */

@Service("userDetailsService")
public class UserService implements UserDetailsService , IUserService {

    private Logger logger = Logger.getLogger(UserService.class);
    
     @Autowired
    private UserDao userDAO;
    
    @Override
    @Transactional 
    public void addUtente(User utente) {
      userDAO.addUtente(utente);
    }

    @Override
    public List<User> getAllUtenti() {
       return userDAO.getAllUtenti();
    }

    @Override
    public void deleteUtente(Integer id) {
       userDAO.deleteUtente(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUtente(int id) {
       return userDAO.getUtente(id);
    }

    @Override
    public User updateUtente(User utente) {
        return userDAO.updateUtente(utente);
    }
    
     @Override
     @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
       User user = userDAO.findUserByUsername(username);
    UserBuilder builder = null;
    try 
    {
    if (user != null) {
      
      builder = org.springframework.security.core.userdetails.User.withUsername(username);
      builder.disabled(!user.isEnabled());
      builder.password(user.getPassword());
      String[] authorities = user.getAuthorities()
          .stream().map(a -> a.getAuthority()).toArray(String[]::new);

      builder.authorities(authorities);   
    } else {
       logger.error("Utente non trovato");
      throw new UsernameNotFoundException("Utente non trovato");
    }
    }
    catch(Exception ex)
    {
         logger.error(ex.getMessage());
    }
    return builder.build();
    }
    
}
