package seu.edu.bd.southeast_portal.model.academics;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import seu.edu.bd.southeast_portal.model.table.HtmlTable;

@Data
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String currentSession;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private String thumbnailUrl;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "school_id",nullable = false)
    private List<Department> departments = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private Undergraduate undergraduate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private Masters masters;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "school_id", nullable = false)
    private List<HtmlTable> tuitionFee = new ArrayList<>();

    public boolean isValidFields(){
        if(!title.isEmpty() && !currentSession.isEmpty() && !details.isEmpty()
                && !departments.isEmpty() && !thumbnailUrl.isEmpty() && !tuitionFee.isEmpty()){

            return true;
        }

        return false;
    }
}
