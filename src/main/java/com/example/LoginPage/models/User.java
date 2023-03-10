package com.example.LoginPage.models;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String fullname;

    private boolean enabled;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Role> roles;
    private String password;

    public Long getId() {
        return id;
    }
    public void SetId(Long id) {
        this.id = id;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPassword(){
        return password;
    }
    public String getFullname(){
        return fullname;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setFullname(String fullname){
        this.fullname = fullname;
    }
    public boolean isEnabled(){
        return enabled;
    }
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
    public Set<Role> getRoles(){
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
