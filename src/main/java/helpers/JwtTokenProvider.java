/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import auth0.jwt.algorithms.Algorithm;
import java.util.Calendar;
import java.util.Date;
import com.auth0.jwt.*;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import static movie.config.Secret.*;
import com.todo.exceptions.NotFoundTokenException;
import com.todo.exceptions.NotFoundUserIdException;
import com.todo.models.User;
import spark.Request;

/**
 *
 * @author jessi
 */
public class JwtTokenProvider {
    private static String HEADER_AUTH = "Authorization";
    private static String TOKEN_TYPE = "Bearer ";
    
    public static String generateToken(User u) {
        
        Date now = (Date) Calendar.getInstance().getTime();
        Date expireDate = new Date(now.getTime() + TOKEN_EXPIRATION);
        Algorithm alg = Algorithm.HMAC256(JWT_SECRET);
        
        return JWT.create()
                .withClaim("email", u.getEmail())
                .withClaim("id", u.getId())
                .withExpiresAt(expireDate)
                .sign(alg);
    }
    
    public static boolean validateToken(String token) {
        try {
            Algorithm alg = Algorithm.HMAC256(JWT_SECRET);
            JWT.require(alg)
                    .build().verify(token);
            return true;
        } catch(JWTVerificationException ex) {
            return false;
        }
    }
    
    public static int getUserId(String token) throws NotFoundUserIdException {
         try {
            Algorithm alg = Algorithm.HMAC256(JWT_SECRET);
            
             DecodedJWT jwt = JWT.require(alg)
                    .build()
                    .verify(token);
             Claim claim = jwt.getClaim("id");
            return claim.asInt();
        } catch(JWTVerificationException ex) {
            throw new NotFoundUserIdException("Not found user id");
        }
    }
    
    public static boolean headerHasToken(String jwtToken){
        return jwtToken != null ?  jwtToken.startsWith(TOKEN_TYPE) : false;
    }
    
    public static String extractTokenfromRequest(Request req) throws NotFoundTokenException {
         String jwtToken = req.headers(HEADER_AUTH);
        if(!headerHasToken(jwtToken))
            throw new NotFoundTokenException("Token not found");
        
        return jwtToken.substring(TOKEN_TYPE.length());
    }
}
