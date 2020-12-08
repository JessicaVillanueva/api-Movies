/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.controllers;

import com.google.gson.Gson;
import movie.helpers.DataResponse;
import java.util.ArrayList;
import java.util.List;
import static movie.config.StatusCode.BAD_REQUEST;
import static movie.config.StatusCode.CORRECT;
import static movie.config.StatusCode.NO_CONTENT;
import static movie.config.StatusCode.OK;
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
        int movie_id = Integer.parseInt(req.params(":movie_id"));
        return commentService.getAll(movie_id);
    }
    
    public DataResponse store(Request req, Response res){
        res.type("application/json");
        Comment c = new Gson().fromJson(req.body(), Comment.class);
        CommentService commentService = new CommentService(new CommentDao());
        int userId = req.attribute("id");
        c.setUser_id(userId);
        int result = commentService.save(c);
        
        DataResponse response = new DataResponse();
        String msg;
        int status;
        if (result == 1){
            msg = "Comentario agregado con éxito.";
            status = CORRECT;
        } else{
           msg = "Ocurrió un error.";
           status = BAD_REQUEST;
        }
        res.status(status);
        return response.setStatus(status).write(msg, new ArrayList());
    }
    
    public DataResponse update (Request req, Response res){
        res.type("application/java");
        CommentService commentService = new CommentService(new CommentDao());
        int id = Integer.parseInt(req.params(":id"));
        Comment c = new Gson().fromJson(req.body(), Comment.class);
        int rs = commentService.update(c, id);
        DataResponse response = new DataResponse();
        String msg;
        int status;
        switch(rs){
            case -1:
               msg = "El formato de los datos es incorrecto, favor de verificarlos";
               status = BAD_REQUEST;
               break;
           case 0:
               msg = "El comentario no existe";
               status = NO_CONTENT;
               break;
           case 1:
                msg = "Comentario actualizado correctamente";
               status = OK;
               break;           
           default:
               msg = "Ocurrió un error al intentar actualizar el comentario";
                status = BAD_REQUEST; 
        }
        res.status(status);
        return response.setStatus(status).write(msg);
    }
}
