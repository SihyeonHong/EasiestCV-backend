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
}
