package vadim.volin.model;


import javax.persistence.*;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Entity
@Table(name = "project_files", schema = "public")
public class ProjectFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "project_files_id", updatable = false, unique = true, nullable = false)
    private Integer project_id;

    @Column(name = "file_path")
    private String file_path;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    public ProjectFile() {
    }

    public ProjectFile(String file_path) {
        this.file_path = file_path;
    }

    public ProjectFile(Integer project_id, String file_path) {
        this.project_id = project_id;
        this.file_path = file_path;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String project_name) {
        this.file_path = project_name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getFileName() {
        Path p = Paths.get(file_path);
        return p.getFileName().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectFile)) return false;
        ProjectFile that = (ProjectFile) o;
        return Objects.equals(getProject_id(), that.getProject_id()) &&
                Objects.equals(getFile_path(), that.getFile_path());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProject_id(), getFile_path());
    }

    @Override
    public String toString() {
        return "ProjectFile{" +
                "project_id=" + project_id +
                ", project_name='" + file_path + '\'' +
                '}';
    }
}
