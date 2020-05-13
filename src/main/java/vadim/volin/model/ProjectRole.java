package vadim.volin.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "team_roles", schema = "manluck")
public class ProjectRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "team_role_id", updatable = false, unique = true, nullable = false)
    private Integer team_role_id;

    @Column(name = "team_role")
    private String team_role;

    public ProjectRole() {
    }

    public ProjectRole(String team_role) {
        this.team_role = team_role;
    }

    public ProjectRole(Integer team_role_id, String team_role) {
        this.team_role_id = team_role_id;
        this.team_role = team_role;
    }
}
