package franco.ortiz.blogspring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, name = "USERNAME")
    private String username;

    @Column(unique = true, nullable = false, name = "EMAIL")
    private String email;

    @Column(nullable = false, name = "PASSWORD_HASH")
    private String password;

    @Column(nullable = false, name = "PHONE", length = 45)
    private String phoneNumber;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
}
