/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;
import static movie.config.StatusCode.OK;
/**
 *
 * @author HP
 */
public class DataResponse {
    private int status;
    private String msg;
    private Object data;
    
    public DataResponse(){
        this.status = OK;
    }
    
    public int getStatus(){
        return status;
    }
    
    
    public DataResponse setStatus(int status){
        this.status = status;
        return this;
    }
    
    public String getMsg(){
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    public DataResponse write(String msg) {
        this.msg = msg;
        return this;
    }
    
    public DataResponse write(Object data) {
        this.data = data;
        return this;
    }
    
    public DataResponse write(String msg, Object data) {
        this.msg = msg;
        this.data = data;
        return this;
    }
}
