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
@Table(name = "tiket")
/** call store procedure in JPA **/
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "allTikets", procedureName = "getAllTiket"),
        @NamedStoredProcedureQuery(name = "tiketByCategory", procedureName = "getTiketByCategory",
                parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "sCategory", type = String.class)})
})
public class Tiket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int amount;
    private String category;
}
