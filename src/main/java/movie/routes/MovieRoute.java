/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.routes;

import com.google.gson.Gson;
import movie.controllers.MovieController;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;

/**
 *
 * @author jessi
 */
public class MovieRoute {
    private MovieController movieController;
    public MovieRoute(MovieController movieController) {
        Gson gson = new Gson();
        port(5555);
        get("/movies", (req, res)->movieController.index(req, res), gson::toJson);
        get("/movies/:id", (req, res)->movieController.show(req, res), gson::toJson);
    }
}
