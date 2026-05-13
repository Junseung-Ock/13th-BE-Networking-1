package cotato.backend.domain.application.entity;

import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.like.entity.Like;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Column(nullable = false)
    private Integer period;

    @Column(nullable = false)
    private Part part;

    @Column(nullable = false)
    private Integer ability;

    @Column(nullable = false)
    private Integer passion;

    @Column(nullable = false)
    private String applicationTime;

    // list.add() 없이 지원서에 해당하는 좋아요 리스트 생성
    @OneToMany(mappedBy = "application")
    private List<Like> likes = new ArrayList<>();

    @Builder
    public Application(Applicant applicant, Integer period, Part part,
                       Integer ability, Integer passion, String applicationTime) {
        this.applicant = applicant;
        this.period = period;
        this.part = part;
        this.ability = ability;
        this.passion = passion;
        this.applicationTime = applicationTime;
    }

    public int getLikeCount() {
        return this.likes.size();
    }
}