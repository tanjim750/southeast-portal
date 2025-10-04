package seu.edu.bd.southeast_portal.model.academics;

import jakarta.persistence.*;
import lombok.Data;
import seu.edu.bd.southeast_portal.model.table.HtmlTable;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Undergraduate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String details;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "masters_id")
    private List<HtmlTable> info = new ArrayList<>();
}

