package ru.mts.media.platform.umc.dao.postgres.venue;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mts.media.platform.umc.dao.postgres.common.FullExternalIdPk;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalIdInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface VenuePgMapper {
    @Mapping(target = "externalId.brandId", source = "id.brand")
    @Mapping(target = "externalId.providerId", source = "id.provider")
    @Mapping(target = "externalId.externalId", source = "id.externalId")
    @Mapping(target = "id", source = "referenceId")
    @Mapping(target = "event", ignore = true)
    Venue asModel(VenuePgEntity venuePg);

    @Mapping(target = "referenceId", source = "id")
    @Mapping(target = "id.brand", source = "externalId.brandId")
    @Mapping(target = "id.provider", source = "externalId.providerId")
    @Mapping(target = "id.externalId", source = "externalId.externalId")
    @Mapping(target = "event", ignore = true)
    VenuePgEntity asEntity(Venue venue);

    @Mapping(target = "brand", source = "brandId")
    @Mapping(target = "provider", source = "providerId")
    @Mapping(target = "externalId", source = "externalId")
    public abstract FullExternalIdPk asPk(FullExternalIdInput fullExternalId);
}
