package EasiestCV.easiestCV.dto;

import EasiestCV.easiestCV.model.User;

public class UserDTO {
    private String userid;
    private String username;
    private String email;
    private String img;
    private String pdf;
    private String token;

    public UserDTO(User user, String token){
        this.userid = user.getUserid();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.img = user.getImg();
        this.pdf = user.getPdf();
        this.token = token;
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
