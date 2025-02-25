package unid.hyodoring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import unid.hyodoring.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HelloPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column
    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "helloPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HelloPostImage> helloPostImages = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "helloPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    private Family family;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    public void addHelloPostImage(HelloPostImage helloPostImage) {
        helloPostImages.add(helloPostImage);
        helloPostImage.setHelloPost(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setHelloPost(this);
    }
}
