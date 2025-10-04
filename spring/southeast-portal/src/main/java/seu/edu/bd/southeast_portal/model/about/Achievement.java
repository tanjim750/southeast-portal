package seu.edu.bd.southeast_portal.model.about;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String achievement;
    @Column(nullable = false)
    private String details;
    @Column(nullable = false)
    private String iconUrl;
}
