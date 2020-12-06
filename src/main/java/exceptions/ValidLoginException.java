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
public class ValidLoginException extends Exception{
    public ValidLoginException(String message) {
        super(message);
    }
}
