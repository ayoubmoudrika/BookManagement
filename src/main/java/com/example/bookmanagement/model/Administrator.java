package com.example.bookmanagement.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="administrators")
@Getter
@Setter
@ToString
public class Administrator extends User {
    public Administrator() {

    }
}
