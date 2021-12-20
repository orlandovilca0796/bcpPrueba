package com.reto.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "currency")
@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class Currency {
    @Id
    @Column(name = "currency_id")
    @NotEmpty(message = "No puede tener el identificador vacio.")
    @Size(min = 3, max = 5, message = "El nombre solo puede tener 3 a 5 caracteres.")
    private String currencyId;

    @NotEmpty(message = "No puede tener la descripcion vacia.")
    @Size(max = 50, message = "La descripcion tiene un maximo de 50 caracteres.")
    @Column(name = "currency_desc")
    private String currencyDescription;

    @JoinColumn(name = "creation_user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private User creationUser;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "audit_user_id")
    private String auditUserId;

    @Column(name = "audit_date")
    private Date auditDate;
}
