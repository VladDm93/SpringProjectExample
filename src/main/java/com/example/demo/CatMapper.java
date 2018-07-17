package com.example.demo;


import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CatMapper {

    CatDto toCatDto(Cat cat);

    List<CatDto> toCatDto(List<Cat> cats);

    void toCat(@MappingTarget Cat cat, CatCreateRequest request);

    Cat toCat(CatCreateRequest request);
}
