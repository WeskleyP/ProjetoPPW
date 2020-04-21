package com.crm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class RolePermissaoId implements Serializable {
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    @Column(name = "ROLE_ID", insertable = false, updatable = false, nullable = false)
    private Long roleId;
    @EqualsAndHashCode.Include
    @Column(name = "PERMISSAO_ID", insertable = false, updatable = false, nullable = false)
    private Long permissaoId;
    @EqualsAndHashCode.Include
    @Column(name = "ESCOPO_ID", insertable = false, updatable = false, nullable = false)
    private Long escopoId;
}
