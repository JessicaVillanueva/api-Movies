/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.models;

/**
 *
 * @author jessi
 */
public class User {
    private int id;
    private String name;
    private String ln_paternal;
    private String ln_maternal;
    private String email;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLn_paternal() {
        return ln_paternal;
    }

    public void setLn_paternal(String ln_paternal) {
        this.ln_paternal = ln_paternal;
    }

    public String getLn_maternal() {
        return ln_maternal;
    }

    public void setLn_maternal(String ln_maternal) {
        this.ln_maternal = ln_maternal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
