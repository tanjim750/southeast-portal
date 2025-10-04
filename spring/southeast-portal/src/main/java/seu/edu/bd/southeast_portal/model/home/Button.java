package seu.edu.bd.southeast_portal.model.home;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class Button {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotEmpty
    private String buttonText;
    private String icon;

    @Column(nullable = false)
    @NotEmpty
    private String linkUrl;
}
