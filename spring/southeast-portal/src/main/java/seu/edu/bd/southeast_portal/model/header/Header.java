package seu.edu.bd.southeast_portal.model.header;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Header {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty
    private String logoUrl;

    @Column(nullable = true)
    @NotEmpty
    private String shortDetails;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "header_id", nullable = false)
    @NotEmpty(message = "Menu can't be empty")
    List<Menu> menus = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "header_id")
    List<MoreLink> moreLinks = new ArrayList<>();

    public boolean isValidFields(){
        if(!logoUrl.isEmpty() && !menus.isEmpty()){

            return true;
        }

        return false;
    }
}
