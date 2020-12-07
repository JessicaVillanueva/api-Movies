/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.routes;

import com.google.gson.Gson;
import static movie.config.ResourceNames.API;
import static movie.config.ResourceNames.MOVIE;
import movie.controllers.MovieController;
import static spark.Spark.get;
import static spark.Spark.path;

/**
 *
 * @author jessi
 */
public class MovieRoute {
    private MovieController movieController;
    public MovieRoute(MovieController movieController) {
        Gson gson = new Gson();
        path(API, () ->{
            get(MOVIE, (req, res)->movieController.index(req, res), gson::toJson);
            get(MOVIE + "/:id", (req, res)->movieController.show(req, res), gson::toJson);
        });
        
    }
}
