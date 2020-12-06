/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movies;

import movie.controllers.CommentController;
import movie.controllers.MovieController;
import movie.routes.CommentRoute;
import movie.routes.MovieRoute;

/**
 *
 * @author jessi
 */
public class Main {
    public static void main(String[] args){
        new MovieRoute(new MovieController());
        new CommentRoute (new CommentController());
    }
}
