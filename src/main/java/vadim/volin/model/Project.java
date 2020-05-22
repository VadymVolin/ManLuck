package vadim.volin.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project", schema = "manluck")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "project_id", updatable = false, unique = true, nullable = false)
    private Integer project_id;

    @Column(name = "project_name")
    private String project_name;

    @ManyToMany(mappedBy = "projects")
    List<User> team;

    public Project() {
    }

    public Project(Integer project_id, String project_name) {
        this.project_id = project_id;
        this.project_name = project_name;
    }

    public Project(String project_name) {
        this.project_name = project_name;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public List<User> getTeam() {
        return team == null ? new ArrayList<>() : team;
    }

    public void setTeam(List<User> team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return Objects.equals(getProject_id(), project.getProject_id()) &&
                Objects.equals(getProject_name(), project.getProject_name()) &&
                Objects.equals(getTeam(), project.getTeam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProject_id(), getProject_name(), getTeam());
    }

    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
                '}';
    }
}
