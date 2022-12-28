package me.anjwoc.sns.domain.like;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.anjwoc.sns.domain.post.Post;
import me.anjwoc.sns.domain.user.User;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "likes")
@SQLDelete(sql = "update likes set deletedAt = NOW() where id=?")
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Comment("생성 일자")
    @CreatedDate
    private LocalDateTime createdAt;

    @Comment("수정 일자")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Comment("삭제 일자")
    private LocalDateTime deletedAt;

    public static Like of (Post post, User user) {
        Like entity = new Like();
        entity.setPost(post);
        entity.setUser(user);
        return entity;
    }
}
