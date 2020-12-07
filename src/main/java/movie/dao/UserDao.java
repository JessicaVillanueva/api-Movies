/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import movie.db.ConnectionDB;
import movie.models.User;

/**
 *
 * @author jessi
 */
public class UserDao {
    
    public int save(User u){
       ConnectionDB db = new ConnectionDB();
       int rs = 0;
       try(Connection conn = db.getConnection()){
           String query = "INSERT INTO users(email, password, name, ln_paternal, ln_maternal) VALUES(?,SHA1(?),?,?,?)";
           PreparedStatement pstm = conn.prepareStatement(query);
           pstm.setString(1, u.getEmail());
           pstm.setString(2, u.getPassword());
           pstm.setString(3, u.getName());
           pstm.setString(4, u.getLn_paternal());
           pstm.setString(5, u.getLn_maternal());
           rs = pstm.executeUpdate();
       } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       return rs;
    }
    
    
    public User findByCredentials(String email, String password) {
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        User u = null;
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM users WHERE email=? AND password=SHA1(?)";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, email); 
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setLn_paternal(rs.getString("ln_paternal"));
                u.setLn_maternal(rs.getString("ln_maternal"));
                u.setEmail(rs.getString("email"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return u;
    }
}
