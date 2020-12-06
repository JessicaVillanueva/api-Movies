/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.routes;

import com.google.gson.Gson;

/**
 *
 * @author jessi
 */
public class AuthRoute {
    public AuthRoute(AuthController authController) {
        Gson gson = new Gson();
        
        post(AUTH_LOGIN, (req, res) -> authController.login(req, res), gson::toJson);
    }
}