package seu.edu.bd.southeast_portal.model.footer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class SocialMediaLinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String platform;
    @Column(nullable = false)
    private String linkUrl;
    private String iconClass;
    private String iconUrl;

    // Relationship with Footer
    @ManyToOne
    @JoinColumn(name = "footer_id")
//    @NotEmpty(message = "Social media links footer can't be empty")
    private Footer footer;
}
