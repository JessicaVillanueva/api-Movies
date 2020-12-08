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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movie.db.ConnectionDB;
import movie.models.LikeDislike;

/**
 *
 * @author HP
 */
public class LikeDao {
    public List<LikeDislike> getAllLikes(int movie_id) {
        ArrayList <LikeDislike> likes = new ArrayList<LikeDislike>();
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM V_COUNT_LIKES_MOVIES";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, movie_id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
                LikeDislike ld = new LikeDislike();
                ld.setId(rs.getInt("id"));
                ld.setCriterio(rs.getString("criterio"));
                ld.setMovie_id(rs.getInt("movie_id"));
                likes.add(ld);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LikeDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LikeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return likes;
    }
    
    public List<LikeDislike> getAllDislikes(int movie_id) {
        ArrayList <LikeDislike> likes = new ArrayList<LikeDislike>();
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM V_COUNT_DISLIKES_MOVIES";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, movie_id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
                LikeDislike ld = new LikeDislike();
                ld.setId(rs.getInt("id"));
                ld.setCriterio(rs.getString("criterio"));
                ld.setMovie_id(rs.getInt("movie_id"));
                likes.add(ld);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LikeDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LikeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return likes;
    }
    public int save(LikeDislike ld){
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        int rs = 0;
        try{
            conn = db.getConnection();
            String query = "CALL LIKE_DISLIKE(?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, ld.getCriterio());
            pstm.setInt(2, ld.getMovie_id());
            pstm.setInt(3, ld.getUser_id());
            rs = pstm.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LikeDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            Logger.getLogger(LikeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            db.close();
        }
        return rs;
    }
    
    public int update(LikeDislike ld, int id){
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        int rs = 0;
        try{
            conn = db.getConnection();
            String query = "UPDATE likes SET criterio = ? WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, ld.getCriterio());
            pstm.setInt(2, id); 
            rs = pstm.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LikeDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LikeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return rs;
    }
        
}
