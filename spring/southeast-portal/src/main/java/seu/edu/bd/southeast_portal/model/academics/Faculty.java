package seu.edu.bd.southeast_portal.model.academics;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String profileImageUrl;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private String degree;

    @Column(nullable = false)
    private String institution;

    private String faceBookProfileUrl;
    private String linkedInProfileUrl;
    private String websiteUrl;
    private String publication;
    private String research;
    private String roomNumber;
    private String ext;

}
