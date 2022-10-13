package com.vask.ysellbtoheroku.model;
import com.vask.ysellbtoheroku.model.enums.Role;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private int id;
    private String firstName;
    private String secondName;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    @Builder.Default
    private boolean active = true;
    private boolean activeEmail;
    private int emailActivationCode;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    @ToString.Exclude
    private Avatar avatar;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Product> products;
    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }


}
