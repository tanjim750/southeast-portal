package seu.edu.bd.southeast_portal.model.header;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String url;

    private String icon;

}
