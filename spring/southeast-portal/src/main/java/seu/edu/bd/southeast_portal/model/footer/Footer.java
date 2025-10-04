package seu.edu.bd.southeast_portal.model.footer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "footer_id")
    @NotEmpty(message = "Social media links can't be empty")
    private List<SocialMediaLinks> socialMediaLinks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "footer_id")
    @NotEmpty(message = "Menue links can't be empty")
    private List<MenuLinks> menuLinks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "footer_id")
    @NotEmpty(message = "Quick buttons can't be empty")
    private List<QuickButtons> quickButtons = new ArrayList<>();

    public boolean isFieldsValid(){
        if(logoUrl == null || logoUrl.isBlank() || description == null ||
                description.isBlank() || copyrightText == null || copyrightText.isBlank() ||
                socialMediaLinks.size() == 0 || menuLinks.size() == 0 || quickButtons.size()==0){
            return false;
        }
        return true;
    }
}
