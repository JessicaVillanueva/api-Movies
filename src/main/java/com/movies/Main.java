/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movies;

import movie.controllers.AuthController;
import movie.controllers.CommentController;
import movie.controllers.LikeController;
import movie.controllers.MovieController;
import movie.controllers.UserController;
import movie.routes.AuthRoute;
import movie.routes.CommentRoute;
import movie.routes.LikeRoute;
import movie.routes.MovieRoute;
import movie.routes.UserRoute;
import static spark.Spark.port;

/**
 *
 * @author jessi
 */
public class Main {
    public static void main(String[] args){
        port(5555);
        
        new MovieRoute(new MovieController());
        new CommentRoute (new CommentController());
        new LikeRoute(new LikeController());
        new UserRoute(new UserController());
        new AuthRoute(new AuthController());
    }
}
