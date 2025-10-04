package seu.edu.bd.southeast_portal.model.about;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MissionAndValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String details;

    private String imageUrl;
}
