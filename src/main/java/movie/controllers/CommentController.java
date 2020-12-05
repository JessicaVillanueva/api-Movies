/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.controllers;

import java.util.List;
import movie.dao.CommentDao;
import movie.models.Comment;
import movie.services.CommentService;
import spark.Request;
import spark.Response;

/**
 *
 * @author HP
 */
public class CommentController {
    public List<Comment> index(Request req, Response res) {
        res.type("application/json");
        CommentService commentService = new CommentService(new CommentDao());
        //int page = req.queryParams("page") != null ?  Integer.parseInt(req.queryParams("page")) : 0;
               
        return commentService.getAll();
    }
}
