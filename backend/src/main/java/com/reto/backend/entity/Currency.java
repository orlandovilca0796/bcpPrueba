package com.reto.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "currency")
@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class Currency {
    @Id
    @Column(name = "currency_id")
    private String currencyId;

    @Column(name = "currency_name")
    private String currencyName;

    @Column(name = "currency_desc")
    private String currencyDescription;

    @JoinColumn(name = "creation_user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User creationUser;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "audit_user_id")
    private String auditUserId;

    @Column(name = "audit_date")
    private Date auditDate;
}
