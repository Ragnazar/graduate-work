package ru.skypro.homework.model.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.model.dto.AdsDto;
import ru.skypro.homework.model.dto.CreateAdsDto;
import ru.skypro.homework.model.dto.FullAdsDto;
import ru.skypro.homework.model.entity.Ads;

import java.util.Collection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdsMapper {
    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    AdsDto adsToAdsDto(Ads Ads);

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "author.id", source = "author")
    Ads adsDtoToAds(AdsDto adsDto);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    FullAdsDto toFullAdsDto(Ads ads);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Ads createAdsToAds(CreateAdsDto createAdsDto);

    @BeanMapping (nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
      void partialUpdate(CreateAdsDto createAdsDto, @MappingTarget Ads ads);

    Collection<AdsDto> adsCollectionToAdsDto(Collection<Ads> adsCollection);

}
