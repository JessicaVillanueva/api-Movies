/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.controllers;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static movie.config.StatusCode.BAD_REQUEST;
import static movie.config.StatusCode.CORRECT;
import static movie.config.StatusCode.NO_CONTENT;
import static movie.config.StatusCode.OK;
import movie.dao.CommentDao;
import movie.dao.LikeDao;
import movie.helpers.DataResponse;
import movie.models.Comment;
import movie.models.LikeDislike;
import movie.services.CommentService;
import movie.services.LikeDislikeService;
import spark.Request;
import spark.Response;


/**
 *
 * @author HP
 */
public class LikeController {
    public List<LikeDislike> index(Request req, Response res) throws SQLException {
        res.type("application/json");
        LikeDislikeService likeService = new LikeDislikeService(new LikeDao());
        int movie_id = Integer.parseInt(req.params(":movie_id"));
        return likeService.getAll(movie_id);
    }
    
    public DataResponse store(Request req, Response res){
        res.type("application/json");
        LikeDislike ld = new Gson().fromJson(req.body(), LikeDislike.class);
        LikeDislikeService likeService = new LikeDislikeService(new LikeDao());
        int result = likeService.save(ld);
        DataResponse response = new DataResponse();
        String msg;
        int status;
        if (result == 1){
            msg = "Criterio agregado con éxito.";
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
        LikeDislikeService likeService = new LikeDislikeService(new LikeDao());
        int id = Integer.parseInt(req.params(":id"));
        LikeDislike ld = new Gson().fromJson(req.body(), LikeDislike.class);
        int rs = likeService.update(ld, id);
        DataResponse response = new DataResponse();
        String msg;
        int status;
        switch(rs){
            case -1:
               msg = "El formato de los datos es incorrecto, favor de verificarlos";
               status = BAD_REQUEST;
               break;
           case 0:
               msg = "El criterio no existe";
               status = NO_CONTENT;
               break;
           case 1:
                msg = "Comentario actualizado correctamente";
               status = OK;
               break;           
           default:
               msg = "Ocurrió un error al intentar actualizar el criterio";
                status = BAD_REQUEST; 
        }
        res.status(status);
        return response.setStatus(status).write(msg);
    }
}
