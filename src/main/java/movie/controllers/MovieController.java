/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.controllers;

import com.google.gson.Gson;
import java.util.List;
import movie.dao.MovieDao;
import movie.models.Movie;
import movie.services.MovieService;
import spark.Request;
import spark.Response;

/**
 *
 * @author jessi
 */
public class MovieController {
    public List<Movie> index(Request req, Response res) {
        res.type("application/json");
        MovieService movieService = new MovieService(new MovieDao());
        //int page = req.queryParams("page") != null ?  Integer.parseInt(req.queryParams("page")) : 0;
        if(req.queryParams("search") != null) 
            return movieService.find(req.queryParams("search"));
        
        return movieService.getAll();
    }
    
    public Movie show(Request req, Response res){
        res.type("application/json");
        MovieService movieService = new MovieService(new MovieDao());
        int id = Integer.parseInt(req.params(":id"));
        return movieService.get(id);
    }
    
}
