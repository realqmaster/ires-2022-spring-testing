package it.euris.ires.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany
    @JoinColumn(name = "office_id")
    private Set<Employee> members;
}
