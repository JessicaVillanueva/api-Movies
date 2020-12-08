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
    
     public List<LikeDislike> getAllLikes(int movie_id){
        return this.dao.getAllLikes(movie_id);
    }
    
    public List<LikeDislike> getAllDislikes(int movie_id){
        return this.dao.getAllDislikes(movie_id);
    }
     
      public int save(LikeDislike ld) {
        return this.dao.save(ld);
    }
    
    public int update(LikeDislike ld, int id) {
        return this.dao.update(ld, id);
    }
}
