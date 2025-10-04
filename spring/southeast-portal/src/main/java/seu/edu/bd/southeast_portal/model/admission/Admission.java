package seu.edu.bd.southeast_portal.model.admission;

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
import lombok.Data;

@Data
@Entity
public class Admission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String currentSession;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "admission_id", nullable = false)
    private List<AdmissionRequirement> requirements = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "admission_id")
    private List<AdmissionInfo> othersInfo = new ArrayList<>();

    public boolean isValidFields(){
        if(!currentSession.isEmpty() && !requirements.isEmpty()){

            return true;
        }

        return false;
    }
}
