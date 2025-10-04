package seu.edu.bd.southeast_portal.model.tuition;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TuitionPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String shortTitle;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private String thumbnailUrl;

    public boolean isValidFields(){
        if(!title.isEmpty() && !shortTitle.isEmpty()
                && !details.isEmpty() && !thumbnailUrl.isEmpty() ){

            return true;
        }

        return false;
    }
}
