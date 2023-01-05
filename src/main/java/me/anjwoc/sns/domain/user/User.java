package me.anjwoc.sns.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "users")
@SQLDelete(sql = "update users set deletedAt = NOW() where id=?")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Comment("유저 이메일")
    private String email;

    @Comment("유저 이름")
    private String userName;

    @Comment("유저 패스워드")
    private String password;

    @Comment("유저 권한")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Comment("생성 일자")
    @CreatedDate
    private LocalDateTime createdAt;

    @Comment("수정 일자")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Comment("삭제 일자")
    private LocalDateTime deletedAt;

    public static User of(String email, String hashedPassword) {
        User user = new User();
        user.setEmail(email);
        user.setUserName(email.split("@")[0]);
        user.setPassword(hashedPassword);
        return user;
    }
}
