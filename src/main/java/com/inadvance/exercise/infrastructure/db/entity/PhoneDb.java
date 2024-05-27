package com.inadvance.exercise.infrastructure.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "phone")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhoneDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id")  // this column will hold the foreign key to the UserDb
    private UserDb user;
}
