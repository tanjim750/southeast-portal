package seu.edu.bd.southeast_portal.model.table;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TableData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String details;
}
