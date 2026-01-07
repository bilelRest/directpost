package tn.poste.myship.sec;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;    
    private String roleName;  
    
    public AppRole() {
    }
    public AppRole(String roleName) {
        this.roleName = roleName;
    }
    public Long getRoleId() {
        return roleId;
    }
    
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
