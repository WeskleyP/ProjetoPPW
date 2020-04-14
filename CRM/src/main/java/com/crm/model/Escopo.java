package com.crm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TAB_ESCOPO")
@SequenceGenerator(name = "ESCOPO_SEQUENCE",sequenceName = "TAB_ESCOPO_SEQUENCE", initialValue = 1,allocationSize = 1)
public class Escopo {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name="ESCOPO_ID")
    private Long id;
    @NotBlank
    @NotNull
    @Column(name="ESCOPO_NOME",length=15,nullable=false)
    private String nome;
}
