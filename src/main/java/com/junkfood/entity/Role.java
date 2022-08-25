package com.junkfood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    private String id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<Authority> authorities;

}
