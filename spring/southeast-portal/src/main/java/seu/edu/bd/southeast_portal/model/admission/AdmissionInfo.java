package seu.edu.bd.southeast_portal.model.admission;

import jakarta.persistence.*;
import lombok.Data;
import seu.edu.bd.southeast_portal.model.table.HtmlTable;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AdmissionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "admission_info_id", nullable = false)
    private List<HtmlTable> othersInfo = new ArrayList<>();
}
