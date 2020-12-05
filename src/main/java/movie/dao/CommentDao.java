/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.dao;

import java.sql.Connection;
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
   public List<Comment> getAll() {
        ArrayList <Comment> comments = new ArrayList<Comment>();
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        
        try {
            conn = db.getConnection();
            String query = "SELECT comment FROM comments INNER JOIN movies ON comments.movie_id = movies.id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
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
}
