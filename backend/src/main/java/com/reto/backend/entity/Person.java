package com.reto.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class Person {
    @Id
    @Column(name = "person_id")
    private String personId;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "person_last_name")
    private String personLastName;

}
