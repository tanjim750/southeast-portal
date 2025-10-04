package seu.edu.bd.southeast_portal.model.academics;

import jakarta.persistence.*;
import lombok.Data;
import seu.edu.bd.southeast_portal.model.table.HtmlTable;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class DepartmentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String infoName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "department_info_id")
    private List<HtmlTable> titles = new ArrayList<>();
}
