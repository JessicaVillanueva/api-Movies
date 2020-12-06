/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.routes;

import com.google.gson.Gson;
import static movie.config.ResourceNames.COMMENT;
import movie.controllers.CommentController;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;

/**
 *
 * @author HP
 */
public class CommentRoute {
    private CommentController commentCtlr;
    public CommentRoute(CommentController commentCtlr) {
        Gson gson = new Gson();
        port(5555);
        get(COMMENT + "/:movie_id", (req, res)->commentCtlr.index(req, res), gson::toJson);
        post(COMMENT, (req, res)-> commentCtlr.store(req, res), gson::toJson);
        put(COMMENT + "/:id", (req, res)-> commentCtlr.update(req, res), gson::toJson);
    }
}
