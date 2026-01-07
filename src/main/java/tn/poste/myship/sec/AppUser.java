package tn.poste.myship.sec;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class AppUser {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;    
    private String username;
    private String password;  
    private String email;
    @OneToMany
    List<AppRole> appRoles;
      
    public AppUser() {
    }
    public AppUser(String username, String password, String email, List<AppRole> appRoles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.appRoles = appRoles;
    }
    public Long getUserId() {
        return userId;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AppRole> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(List<AppRole> appRoles) {
        this.appRoles = appRoles;
    }

    
}
