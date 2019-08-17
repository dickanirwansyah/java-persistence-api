package com.persistence.examplejpastoreprocedure.enity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produk")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "produks", procedureName = "produkJoinCategory")
})
public class Produk implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    private int stock;
    private Long price;
}
