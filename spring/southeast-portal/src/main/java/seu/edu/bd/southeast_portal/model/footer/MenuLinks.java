package seu.edu.bd.southeast_portal.model.footer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class MenuLinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String menuName;
    @Column(nullable = false)
    private String linkUrl;

    // Relationship with Footer

    @ManyToOne
    @JoinColumn(name = "footer_id")
//    @NotEmpty(message = "Menu links footer can't be empty")
    private Footer footer;
}
