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

    @Column(name = "project_tasks")
    private String project_tasks;

    @ManyToMany(mappedBy = "projects")
    List<User> team = new ArrayList<>();

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProjectFile> projectFiles = new ArrayList<>();


    public Project() {
    }

    public Project(String project_name) {
        this.project_name = project_name;
    }

    public Project(Integer project_id, String project_name) {
        this.project_id = project_id;
        this.project_name = project_name;
    }

    public Project(Integer project_id, String project_name, String project_tasks) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_tasks = project_tasks;
    }

    public Project(Integer project_id, String project_name, List<ProjectFile> projectFiles) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.projectFiles = projectFiles;
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

    public String getProject_tasks() {
        return project_tasks;
    }

    public void setProject_tasks(String project_tasks) {
        this.project_tasks = project_tasks;
    }

    public List<User> getTeam() {
        return team == null ? new ArrayList<>() : team;
    }

    public void setTeam(List<User> team) {
        this.team = team;
    }

    public List<ProjectFile> getProjectFiles() {
        return projectFiles == null ? new ArrayList<>() : projectFiles;
    }

    public void setProjectFiles(List<ProjectFile> projectFiles) {
        this.projectFiles = projectFiles;
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
                ", project_tasks='" + project_tasks + '\'' +
                ", project_files='" + projectFiles + '\'' +
                '}';
    }
}
