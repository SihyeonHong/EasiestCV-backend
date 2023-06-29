package EasiestCV.easiestCV.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tabs")
public class Tab {
    @Id
    @Column(name = "idtabs", nullable = false, unique = true)
    private String idtabs;

    @Column(name = "tabsorder", nullable = false)
    private String tabsorder;

    @Column(name = "tabsname", nullable = false)
    private String tabsname;

    @ManyToOne
    @JoinColumn(name="fk_tabs_userid")
    private User user;

    @OneToMany(mappedBy = "tab", cascade = CascadeType.ALL)
    private List<Content> contents = new ArrayList<>();

    public Tab(){}

    public String getIdtabs() {
        return idtabs;
    }

    public void setIdtabs(String idtabs) {
        this.idtabs = idtabs;
    }

    public String getTabsorder() {
        return tabsorder;
    }

    public void setTabsorder(String tabsorder) {
        this.tabsorder = tabsorder;
    }

    public String getTabsname() {
        return tabsname;
    }

    public void setTabsname(String tabsname) {
        this.tabsname = tabsname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
