package com.example.bookmanagement.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customers")
@Getter
@Setter
@ToString
public class Customer extends User {


    public Customer() {

    }
}
