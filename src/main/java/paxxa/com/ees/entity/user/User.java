package paxxa.com.ees.entity.user;

import org.hibernate.validator.constraints.Email;
import paxxa.com.ees.annotation.UniqueUserName;
import paxxa.com.ees.entity.personalData.PersonalData;
import paxxa.com.ees.entity.role.Role;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 1, message = "Nazwa konta musi posiadać conajmniej jeden znak")
    @Column(unique = true)
    @UniqueUserName(message = "Podana nazwa jest już zajęta")
    private String name;

    @Size(min = 1, message = "Nieprawidłowy adres email")
    @Email
    private String email;

    @Size(min = 5, message = "Hasło musi składać się z conajmniej 5 znaków")
    private String password;

    private boolean enabled;

    @ManyToMany
    @JoinTable
    private List<Role> roles;

    @OneToOne
    @JoinColumn
    private PersonalData personalData;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "OWNED_USER_ID")
    private List<User> userList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
