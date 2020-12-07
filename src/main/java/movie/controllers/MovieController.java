/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.controllers;

import com.google.gson.Gson;
import java.util.List;
import static movie.config.StatusCode.CORRECT;
import static movie.config.StatusCode.NO_CONTENT;
import movie.dao.MovieDao;
import movie.helpers.DataResponse;
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
        DataResponse response = new DataResponse();
        String msg;
        int status;
        if(req.queryParams("search") != null){
            msg = "Búsqueda realizada con éxito.";
            status = CORRECT;
            return movieService.find(req.queryParams("search"));
        } else{
            msg = "No se encontraron películas.";
            status = NO_CONTENT;
        }    
        res.status(status);
        return movieService.getAll();
    }
    
    public Movie show(Request req, Response res){
        res.type("application/json");
        MovieService movieService = new MovieService(new MovieDao());
        int id = Integer.parseInt(req.params(":id"));
        return movieService.get(id);
    }
    
}
