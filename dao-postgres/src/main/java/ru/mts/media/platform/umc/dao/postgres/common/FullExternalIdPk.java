package ru.mts.media.platform.umc.dao.postgres.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FullExternalIdPk implements Serializable {
    @Column(name = "brand")
    private String brand;
    @Column(name = "provider")
    private String provider;
    @Column(name = "external_id")
    private String externalId;
}
