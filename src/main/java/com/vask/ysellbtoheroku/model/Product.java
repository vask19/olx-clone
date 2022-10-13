package com.vask.ysellbtoheroku.model;

import com.vask.ysellbtoheroku.model.enums.Locate;
import com.vask.ysellbtoheroku.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private int id;
    private String name;
    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Locate locate;
    private Double price;


    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "users_id", referencedColumnName = "users_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,
        mappedBy = "product")
    @ToString.Exclude
    @Fetch(FetchMode.SUBSELECT)
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dateOfCreated;
    private boolean deleted;

    @ManyToMany(mappedBy = "products",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Bucket> buckets;

    public Product(Integer productId) {
        this.id = productId;
    }

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
