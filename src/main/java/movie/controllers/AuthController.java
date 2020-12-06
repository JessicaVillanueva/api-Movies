/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.controllers;

import com.google.gson.Gson;
import exceptions.ValidLoginException;
import helpers.DataResponse;
import movie.dao.UserDao;
import movie.models.User;
import movie.services.AuthService;
import spark.Request;
import spark.Response;

/**
 *
 * @author jessi
 */
public class AuthController {
    public DataResponse login(Request req, Response res) {
       res.type("application/json");
       
       AuthService movieService = new AuthService(new UserDao());
       DataResponse response = new DataResponse();
        String msg = "";
       int status;
       Object data = null;
       try {
            String email = req.queryParams("email"); 
            String password = req.queryParams("password");
            User u = movieService.login(email, password);
            JwtToken token = new JwtToken();
            token.setToken(JwtTokenProvider.generateToken(u));
            status = OK;
            data = token;
            
       }catch(ValidLoginException e) {
           status = UNAUTHORIZED;
           msg = "Usuario o contraseña incorrectos";
       }
       
        res.status(status);
       return response.setStatus(status).write(msg, data);
    }
}
