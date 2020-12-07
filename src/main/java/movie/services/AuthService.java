/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.services;

import exceptions.ValidLoginException;
import movie.dao.UserDao;
import movie.models.User;

/**
 *
 * @author jessi
 */
public class AuthService {
    private UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }
            
    public User login(String email, String password) throws ValidLoginException {
        User u = this.userDao.findByCredentials(email, password);
        if(u == null) {
            throw new ValidLoginException("Usuario o contraseña incorrectos");
        } 
        return u;
    }
}
