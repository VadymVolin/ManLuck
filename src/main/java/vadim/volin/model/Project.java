package vadim.volin.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project", schema = "manluck")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "project_id", updatable = false, unique = true, nullable = false)
    private Integer project_id;

    @Column(name = "project_name")
    private String project_name;

    

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

}
