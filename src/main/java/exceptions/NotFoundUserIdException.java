/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author jessi
 */
public class NotFoundUserIdException extends Exception{
    public NotFoundUserIdException(String message) {
        super(message);
    }
}
