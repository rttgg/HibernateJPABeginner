package com.hibernate.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int did;
    @NonNull
    @Column(length = 150, unique = true, nullable = false)
    String name;
    @NonNull
    String state;

//    @NonNull
//    @ManyToOne(cascade = CascadeType.ALL)
//    Employee employee;
//
//
//    public Department(String dep1, String wa) {
//
//    }
}
