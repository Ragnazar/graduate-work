package ru.skypro.homework.model.entity;

import lombok.Data;
import lombok.ToString;
import ru.skypro.homework.model.dto.RoleEnum;

import javax.persistence.*;
import java.util.Set;

import javax.persistence.Table;
/**
 * User
 */

@Entity
@Data
@Table(name = "profile_user")
public class ProfileUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;
    @ToString.Exclude
    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
    private Set<Ads> ads;

    @Column(name = "reg_date")
    private String regDate;
    @Column(name = "city")
    private String city;
    @Column(name = "pass")
    private String password;
    @Column(name = "role")
    private RoleEnum role;
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Avatar avatar;

}
