/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movie.db.ConnectionDB;
import movie.models.Movie;


/**
 *
 * @author jessi
 */
public class MovieDao {
    public List<Movie> getAll() {
        ArrayList <Movie> movies = new ArrayList<Movie>();
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM movies";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Movie m = new Movie();
                m.setId(rs.getInt("id"));
                m.setTitle(rs.getString("title"));
                m.setSynopsis(rs.getString("synopsis"));
                m.setImage(rs.getString("image"));
                movies.add(m);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return movies;
    }
    
    public Movie get(int id) {
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        Movie movie = null;
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM movies WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setSynopsis(rs.getString("synopsis"));
                movie.setImage(rs.getString("image"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return movie;
    }
    
    public List<Movie> find(String search) {
        ArrayList <Movie> movies = new ArrayList<Movie>();
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM movies WHERE title LIKE ?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, "%"+search+"%");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
                Movie m = new Movie();
                m.setId(rs.getInt("id"));
                m.setTitle(rs.getString("title"));
                m.setSynopsis(rs.getString("synopsis"));
                m.setImage(rs.getString("image"));
                movies.add(m);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return movies;
    } 
    
   
}
