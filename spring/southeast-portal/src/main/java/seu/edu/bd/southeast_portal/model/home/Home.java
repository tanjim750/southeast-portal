package seu.edu.bd.southeast_portal.model.home;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty
    private String slogan;

    @Column(nullable = true)
    private String sloganIconUrl;

    @Column(nullable = true)
    private String circle;

    @Column(nullable = false)
    @NotEmpty
    private String title;

    @Column(nullable = false)
    @NotEmpty
    private String details;

    @Column(nullable = true)
    private String imageOneUrl;

    @Column(nullable = true)
    private String imageTwoUrl;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "home_id")
    private List<Button> buttons;

    public boolean isValidFields(){
        if(!title.isEmpty() && !details.isEmpty() && !slogan.isEmpty()){

            return true;
        }

        return false;
    }
}
