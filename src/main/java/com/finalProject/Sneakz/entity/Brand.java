package com.finalProject.Sneakz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="brand")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {

    private static final long SerialVersionUID=1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Product> products;*/

    @Lob
    private String brandName;
}
