/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.routes;

import com.google.gson.Gson;
import static movie.config.ResourceNames.API;
import static movie.config.ResourceNames.USERS;
import movie.controllers.UserController;
import static spark.Spark.*;

/**
 *
 * @author jessi
 */
public class UserRoute {
    private UserController userController;

    public UserRoute(UserController userController) {
        Gson gson = new Gson();
        path(API, () ->{
            post(USERS, (req, res) -> userController.store(req, res), gson::toJson);
        });
    }
 
}
