/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.routes;

import com.google.gson.Gson;
import movie.controllers.CommentController;
import static spark.Spark.get;
import static spark.Spark.port;

/**
 *
 * @author HP
 */
public class CommentRoute {
    private CommentController commentCtlr;
    public CommentRoute(CommentController movieController) {
        Gson gson = new Gson();
        port(5555);
        get("/comments", (req, res)->commentCtlr.index(req, res), gson::toJson);
    }
}
