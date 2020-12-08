/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.services;

import java.sql.SQLException;
import java.util.List;
import movie.dao.LikeDao;
import movie.models.LikeDislike;

/**
 *
 * @author HP
 */
public class LikeDislikeService {
    private LikeDao dao;
     public LikeDislikeService (LikeDao dao){
        this.dao = dao;
    }
    
     public List<LikeDislike> getAll(int movie_id) throws SQLException{
        return this.dao.getAll(movie_id);
    }
     
      public int save(LikeDislike ld) {
        return this.dao.save(ld);
    }
    
    public int update(LikeDislike ld, int id) {
        return this.dao.update(ld, id);
    }
}
