/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.controllers;

import com.google.gson.Gson;
import movie.helpers.DataResponse;
import static movie.config.StatusCode.BAD_REQUEST;
import static movie.config.StatusCode.CORRECT;
import movie.dao.UserDao;
import movie.models.User;
import movie.services.UserService;
import spark.Request;
import spark.Response;

/**
 *
 * @author jessi
 */
public class UserController {
    public DataResponse store(Request req, Response res){
        res.type("application/json");
        User u = new Gson().fromJson(req.body(), User.class);
        UserService userService = new UserService(new UserDao());
        int rs = userService.save(u);
        DataResponse response = new DataResponse();
        String msg;
        int status;
        if(rs == 1){
            msg = "Datos del usuario almacenados correctamente";
            status = CORRECT;
        }else{
            msg = "Ocurrio un error al insertar almacenar los datos del usuario";
            status = BAD_REQUEST;
        }
        res.status(status);
        return response.setStatus(status).write(msg);
    }
    
}
