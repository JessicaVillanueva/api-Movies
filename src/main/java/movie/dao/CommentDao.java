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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movie.db.ConnectionDB;
import movie.models.Comment;

/**
 *
 * @author HP
 */
public class CommentDao {
   public List<Comment> getAll(int movie_id) {
        ArrayList <Comment> comments = new ArrayList<Comment>();
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM comments WHERE movie_id = ?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, movie_id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                c.setDescription(rs.getString("description"));
                c.setMovie_id(rs.getInt("movie_id"));
                c.setUser_id(rs.getInt("user_id"));
                comments.add(c);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return comments;
    }
   
    public int save(Comment c){
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        int rs = 0;
        try{
            conn = db.getConnection();
            String query = "CALL ADD_COMMENT(?)";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, c.getComment());
            rs = pstm.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            db.close();
        }
        return rs;
    }
    
    public int update(Comment c, int id){
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        int rs = 0;
        try{
            conn = db.getConnection();
            String query = "UPDATE comments SET comment=? WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, c.getComment());
            pstm.setInt(2, id);
            rs = pstm.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return rs;
    }
}
