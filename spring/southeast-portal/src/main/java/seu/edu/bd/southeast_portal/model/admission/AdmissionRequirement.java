package seu.edu.bd.southeast_portal.model.admission;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AdmissionRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String requirement;
}
