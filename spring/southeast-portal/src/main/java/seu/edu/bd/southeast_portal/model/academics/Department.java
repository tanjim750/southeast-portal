package seu.edu.bd.southeast_portal.model.academics;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String shortTitle;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private String thumbnailUrl;

    @Column(nullable = false)
    private String aboutDepartment;

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true )
    @JoinColumn(name = "department_id", nullable = false)
    private List<DepartmentInfo> departmentInfo = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true )
    @JoinColumn(name = "department_id", nullable = false)
    private  List<Faculty> facultyList = new ArrayList<>();

    public boolean isValidFields(){
        if(!name.isEmpty() && !shortTitle.isEmpty() && !details.isEmpty() &&
                !thumbnailUrl.isEmpty() && !aboutDepartment.isEmpty() && !departmentInfo.isEmpty()
                && !facultyList.isEmpty()){

            return true;
        }

        return false;
    }
}
