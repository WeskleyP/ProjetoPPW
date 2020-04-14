package com.crm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TAB_PERMISSAO")
@SequenceGenerator(name = "PERMISSAO_SEQUENCE",sequenceName = "TAB_PERMISSAO_SEQUENCE", initialValue = 1,allocationSize = 1)
public class Permissao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PERMISSAO_ID")
    private Long id;
    @NotNull(message = "O nome da permissão é obrigatório")
    @NotBlank(message = "O nome da permissão é obrigatório")
    @Column(name = "PERMISSAO_NOME")
    private String nome;
}
