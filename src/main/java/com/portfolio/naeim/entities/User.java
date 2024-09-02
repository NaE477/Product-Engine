package com.portfolio.naeim.entities;

import com.portfolio.naeim.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;

    private String email;
}
