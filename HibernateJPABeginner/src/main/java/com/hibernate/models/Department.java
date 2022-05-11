package com.hibernate.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Set;


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
    String name;
    @NonNull
    String state;

    @ToString.Exclude
    @ManyToMany(mappedBy = "departments", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    Set<Employee> employees = new LinkedHashSet<>();

    public Department(String name) {

    }
}
