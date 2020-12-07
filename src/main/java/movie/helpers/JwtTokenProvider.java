/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Date;
import static movie.config.Secret.*;
import movie.exceptions.NotFoundTokenException;
import movie.models.User;
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
        
        return JWT.create().withClaim("id", u.getId())
                .withClaim("email", u.getEmail())
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
            //return false;
            throw new JWTVerificationException(ex.getMessage());
        }
    }
    
    public static int getUserId(String token) {
       Algorithm alg = Algorithm.HMAC256(JWT_SECRET);
       
        DecodedJWT jwtToken = JWT.require(alg)
                .build()
                .verify(token);
        Claim claim = jwtToken.getClaim("id");
        return claim.asInt();
    }
    
    public static boolean isValidFormatJWT(String jwtToken) {
        return jwtToken != null ? jwtToken.startsWith(TOKEN_TYPE) : false;
    }
    
    public static String extractTokenFromRequest(Request req) throws NotFoundTokenException {
        String jwtToken = req.headers(HEADER_AUTH);
        if(!isValidFormatJWT(jwtToken)) 
           throw new NotFoundTokenException("Invlaid jwt token format");
        
        return jwtToken.substring(TOKEN_TYPE.length());
    }
}
