package com.example.demo.service;


import com.example.demo.data.Cat;
import com.example.demo.data.CatRepository;
import com.example.demo.service.response.CatDto;
import com.example.demo.service.CatMapper;
import com.example.demo.service.request.CatCreateRequest;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatService {

    private final CatRepository repository;
    private final CatMapper catMapper;

    public CatService(CatRepository repository, CatMapper catMapper) {
        this.repository = repository;
        this.catMapper = catMapper;
    }

    @Transactional
    public CatDto createCat(CatCreateRequest request) {
        return catMapper.toCatDto(repository.save(catMapper.toCat(request)));
    }

    @Transactional
    public CatDto updateCat(long id, CatCreateRequest request) {
        Cat cat = repository.getOne(id);
        catMapper.toCat(cat, request);
        cat = repository.save(cat);
        return catMapper.toCatDto(cat);
    }

    @Transactional(readOnly = true)
    public CatDto getCat(long id) {
        return catMapper.toCatDto(repository.getOne(id));
    }

    @Transactional(readOnly = true)
    public List<CatDto> findAllCats() {
        return catMapper.toCatDto(repository.findAll());
    }
}
