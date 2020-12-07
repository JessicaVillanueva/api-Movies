/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.exceptions;

/**
 *
 * @author jessi
 */
public class ValidateLoginException extends Exception{
    public ValidateLoginException(String message) {
        super(message);
    }
}
