package me.anjwoc.sns.domain.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.anjwoc.sns.domain.user.User;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "posts")
@SQLDelete(sql = "update posts set deletedAt = NOW() where id=?")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Comment("게시글 제목")
    private String title;

    @Lob
    @Comment("게시글 내용")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Comment("생성 일자")
    @CreatedDate
    private LocalDateTime createdAt;

    @Comment("수정 일자")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Comment("삭제 일자")
    private LocalDateTime deletedAt;
}
