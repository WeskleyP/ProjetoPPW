package com.crm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TAB_ROLE_PERMISSAO")
public class RolePermissao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private RolePermissaoId id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
    private Role roleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERMISSAO_ID", insertable = false, updatable = false)
    private Permissao permissaoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ESCOPO_ID",insertable=false, updatable=false)
    private Escopo escopoId; // CREATE, READ, UPDATE, DELETE
}
