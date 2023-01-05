package com.cinema.auth.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@EqualsAndHashCode(exclude = "roles")
//TODO зачем вообще переопределять toString?
@ToString(exclude = "roles")
public class Account {

    @Id
    //TODO посмотреть тру стратегию
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //TODO посмотреть как генерить нормальные Id для большого кол-ва пользователей
    //TODO изучить чем 6 хибер отличается от 5
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
