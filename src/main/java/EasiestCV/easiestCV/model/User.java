package EasiestCV.easiestCV.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "userid", nullable = false, unique = true)
    private String userid;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // will be encoded later
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "img", nullable = true)
    private String img;

    @Column(name = "pdf", nullable = true)
    private String pdf;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Tab> tabs = new ArrayList<>();

    public User() {}

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

//    public List<Tab> getTabs() {
//        return tabs;
//    }
//
//    public void setTabs(List<Tab> tabs) {
//        this.tabs = tabs;
//    }
}
