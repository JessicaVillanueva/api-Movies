/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.routes;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.google.gson.Gson;
import movie.exceptions.NotFoundTokenException;
import movie.helpers.DataResponse;
import movie.helpers.JwtTokenProvider;
import static movie.config.ResourceNames.API;
import static movie.config.ResourceNames.MOVIE;
import static movie.config.StatusCode.UNAUTHORIZED;
import movie.controllers.MovieController;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.path;

/**
 *
 * @author jessi
 */
public class MovieRoute {
    private MovieController movieController;
    public MovieRoute(MovieController movieController) {
        Gson gson = new Gson();
        path(API + MOVIE, () ->{
            before("/*", (req, res) ->{
                res.type("application/json");
                 DataResponse response = new DataResponse();
                try {
                    String jwtToken = JwtTokenProvider.extractTokenFromRequest(req);
                    JwtTokenProvider.validateToken(jwtToken);
                    req.attribute("id", JwtTokenProvider.getUserId(jwtToken));
                } catch(NotFoundTokenException e) {
                    response.setStatus(UNAUTHORIZED).write(e.getMessage());
                    halt(UNAUTHORIZED, new Gson().toJson(response));
                } catch(JWTVerificationException e) {
                    response.setStatus(UNAUTHORIZED).write(e.getMessage());
                    halt(UNAUTHORIZED, new Gson().toJson(response));
                }
            });
            get("/", (req, res)->movieController.index(req, res), gson::toJson);
            get("/:id", (req, res)->movieController.show(req, res), gson::toJson);
        });
        
    }
}
