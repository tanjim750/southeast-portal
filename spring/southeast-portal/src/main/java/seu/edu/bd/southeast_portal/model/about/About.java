package seu.edu.bd.southeast_portal.model.about;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String universityName;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String shortTitle;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private String establishYear;

    @Column(nullable = false)
    private String thumbnailUrl;

    @Column(nullable = false)
    private String achievementImageUrl;

    @Column(nullable = false)
    private String historyImageUrl;

    @Column(nullable = false)
    private String historyDetails;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "about_id", nullable = false)
    @NotEmpty(message = "Achievements can't be empty")
    private List<Achievement> achievements = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "about_id", nullable = false)
    @NotEmpty(message = "Mission and values can't be empty")
    private List<MissionAndValues> missionAndValues = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "about_id")
    private List<AboutImages> images = new ArrayList<>();

    public boolean isValidFields(){
        if(!title.isEmpty() && !shortTitle.isEmpty() && !achievementImageUrl.isEmpty() && !universityName.isEmpty()
                && !historyImageUrl.isEmpty() && !historyDetails.isEmpty() && !establishYear.isEmpty() &&
                !thumbnailUrl.isEmpty() && !achievements.isEmpty() && !missionAndValues.isEmpty()){

            return true;
        }

        return false;
    }

}
