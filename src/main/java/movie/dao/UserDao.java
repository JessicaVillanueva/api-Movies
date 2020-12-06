/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import movie.db.ConnectionDB;

/**
 *
 * @author jessi
 */
public class UserDao {
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
                u.setId(rs.getInt("Id"));
                u.setNombre(rs.getString("nombre"));
                u.setApPaterno(rs.getString("ap_p"));
                u.setApMaterno(rs.getString("ap_m"));
                u.setEmail(rs.getString("email"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return u;
    }
}
