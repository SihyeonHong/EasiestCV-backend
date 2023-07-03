package EasiestCV.easiestCV.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contents")
public class Content {
    @Id
    @Column(name = "idcontents", nullable = false, unique = true)
    private String idcontents;

    @Column(name = "contentsorder", nullable = false)
    private String contentsorder;

    @Column(name = "contentstype", nullable = false)
    private String contentstype;

    @Column(name = "contentscol", nullable = false)
    private String contentscol;

    @ManyToOne
    @JoinColumn(name="fk_tabs_userid")
    private User user;

    @ManyToOne
    @JoinColumn(name="idtabs")
    private Tab tab;

    public Content() {
    }

    public String getIdcontents() {
        return idcontents;
    }

    public void setIdcontents(String idcontents) {
        this.idcontents = idcontents;
    }

    public String getContentsorder() {
        return contentsorder;
    }

    public void setContentsorder(String contentsorder) {
        this.contentsorder = contentsorder;
    }

    public String getContentstype() {
        return contentstype;
    }

    public void setContentstype(String contentstype) {
        this.contentstype = contentstype;
    }

    public String getContentscol() {
        return contentscol;
    }

    public void setContentscol(String contentscol) {
        this.contentscol = contentscol;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }
}
