/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.services;

import java.util.List;
import movie.dao.MovieDao;
import movie.models.Movie;

/**
 *
 * @author jessi
 */
public class MovieService {
    private MovieDao dao;

    public MovieService(MovieDao dao) {
        this.dao = dao;
    }
    
    public List<Movie> getAll(){
        return this.dao.getAll();
    }
    
    public Movie get(int id){
        return this.dao.get(id);
    }
    
     public List<Movie> find(String search) {
        return this.dao.find(search);
    }
    

}
