package com.junkfood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Id
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "photo")
    private String photo;

    @JsonIgnore
    @OneToMany(mappedBy = "account", orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Order> orders;


    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "accountAuth",cascade = CascadeType.ALL)
    private Collection<Authority> authorities = new LinkedHashSet<Authority>();
//    private List<Authority> authorities;

}
