package com.example.demo.service;


import com.example.demo.data.Cat;
import com.example.demo.service.request.CatCreateRequest;
import com.example.demo.service.response.CatDto;
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
