/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.controllers;

import movie.config.StatusCode;
import movie.helpers.DataResponse;
import movie.helpers.JwtTokenProvider;
import movie.models.JwtToken;
import movie.dao.UserDao;
import movie.exceptions.ValidateLoginException;
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
        
        AuthService authService = new AuthService(new UserDao());
        DataResponse response = new DataResponse();
        String msg = "";
        int status;
        Object data = null;
        try {
            String email = req.queryParams("email");
            String password = req.queryParams("password");
            User u = authService.login(email, password);
            JwtToken jwtToken = new JwtToken();
            jwtToken.setToken(JwtTokenProvider.generateToken(u));
            status = StatusCode.OK;
            data = jwtToken;
        } catch(ValidateLoginException e) {
            status = StatusCode.UNAUTHORIZED;
            msg = "Usuario o contraseña incorrectos";
        }
        res.status(status);
        return response.setStatus(status).write(msg, data);
    }
}