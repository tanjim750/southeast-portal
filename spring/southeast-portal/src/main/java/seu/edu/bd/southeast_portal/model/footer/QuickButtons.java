package seu.edu.bd.southeast_portal.model.footer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class QuickButtons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String buttonText;
    private String buttonColor;
    @Column(nullable = false)
    private String linkUrl;

    @ManyToOne
    @JoinColumn(name = "footer_id")
//    @NotEmpty(message = "Quick buttons footer can't be empty")
    private Footer footer;
}
