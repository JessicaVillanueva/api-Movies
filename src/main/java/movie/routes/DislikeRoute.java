/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.routes;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.google.gson.Gson;
import static movie.config.ResourceNames.API;
import static movie.config.ResourceNames.DISLIKES;
import static movie.config.StatusCode.UNAUTHORIZED;
import movie.controllers.LikeController;
import movie.exceptions.NotFoundTokenException;
import movie.helpers.DataResponse;
import movie.helpers.JwtTokenProvider;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

/**
 *
 * @author HP
 */
public class DislikeRoute {
    private LikeController likeCtlr;
    public DislikeRoute(LikeController dislikeCtlr) {
        Gson gson = new Gson();
        path(API + DISLIKES, () ->{
             before("/*", (req, res) ->{
                res.type("application/json");
                 DataResponse response = new DataResponse();
                try {
                    String jwtToken = JwtTokenProvider.extractTokenFromRequest(req);
                    JwtTokenProvider.validateToken(jwtToken);
                    //aqui obtienes el id del token y lo pones en el req
                    req.attribute("id", JwtTokenProvider.getUserId(jwtToken));
                } catch(NotFoundTokenException e) {
                    response.setStatus(UNAUTHORIZED).write(e.getMessage());
                    halt(UNAUTHORIZED, new Gson().toJson(response));
                } catch(JWTVerificationException e) {
                    response.setStatus(UNAUTHORIZED).write(e.getMessage());
                    halt(UNAUTHORIZED, new Gson().toJson(response));
                }
            });
            
            get("/:movie_id", (req, res)->likeCtlr.index_dos(req, res), gson::toJson);
            post("/", (req, res)-> likeCtlr.store(req, res), gson::toJson);
            put("/:id", (req, res)-> likeCtlr.update(req, res), gson::toJson);
        });
    }
}