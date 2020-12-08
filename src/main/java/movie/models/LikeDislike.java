/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.models;

/**
 *
 * @author HP
 */
public class LikeDislike {
    private int id;
    private String criterio;
    private int movie_id;
    private int user_id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCriterio() {
        return criterio;
    }
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }


    public int getMovie_id() {
        return movie_id;
    }
    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
}
