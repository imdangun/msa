package com.msa.license.domanin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Getter @Setter
@Entity
@Table(name="licenses")
public class License {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long licenseId;
    @Column
    private String licenseName;
    @Column
    private LocalDate createdDate;
}
