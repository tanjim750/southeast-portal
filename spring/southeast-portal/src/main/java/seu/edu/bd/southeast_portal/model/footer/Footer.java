package seu.edu.bd.southeast_portal.model.footer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Footer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String logoUrl;


    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String copyrightText;

    // Relationships
    @OneToMany(mappedBy = "footer", cascade = CascadeType.ALL)
    @NotEmpty(message = "Social media links can't be empty")
    private Set<SocialMediaLinks> socialMediaLinks = new HashSet<>();

    @OneToMany(mappedBy = "footer", cascade = CascadeType.ALL)
    @NotEmpty(message = "Menue links can't be empty")
    private Set<MenuLinks> menuLinks = new HashSet<>();

    @OneToMany(mappedBy = "footer", cascade = CascadeType.ALL)
    @NotEmpty(message = "Quick buttons can't be empty")
    private Set<QuickButtons> quickButtons = new HashSet<>();
}
