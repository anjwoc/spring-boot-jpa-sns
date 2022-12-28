package me.anjwoc.sns.domain.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.anjwoc.sns.domain.post.Post;
import me.anjwoc.sns.domain.user.User;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "comments")
@SQLDelete(sql = "update comments set deletedAt = NOW() where id=?")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @org.hibernate.annotations.Comment("생성 일자")
    @CreatedDate
    private LocalDateTime createdAt;

    @org.hibernate.annotations.Comment("수정 일자")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @org.hibernate.annotations.Comment("삭제 일자")
    private LocalDateTime deletedAt;

    public static Comment of(String comment, Post post, User user) {
        Comment entity = new Comment();
        entity.setComment(comment);
        entity.setPost(post);
        entity.setUser(user);
        return entity;
    }
}
