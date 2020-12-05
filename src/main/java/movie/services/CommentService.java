/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.services;

import java.util.List;
import movie.dao.CommentDao;
import movie.models.Comment;

/**
 *
 * @author HP
 */
public class CommentService {
    private CommentDao dao;
    
    public CommentService (CommentDao dao){
        this.dao = dao;
    }
    
     public List<Comment> getAll(){
        return this.dao.getAll();
    }
}
