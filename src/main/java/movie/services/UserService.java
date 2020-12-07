/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.services;

import movie.dao.UserDao;
import movie.models.User;

/**
 *
 * @author jessi
 */
public class UserService {
    private UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }
    
    public int save(User u){
        return this.dao.save(u);
    }
}
