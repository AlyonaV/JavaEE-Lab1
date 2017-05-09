package com.mycompany.model;

/**
 *
 * @author Alona
 */
public class Glossary {
    private Integer glosId;
    private String title;
    private User user;

    public Integer getGlosId() {
        return glosId;
    }

    public void setGlosId(Integer glosId) {
        this.glosId = glosId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
   
    
}
