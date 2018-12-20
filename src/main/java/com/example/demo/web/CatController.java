package com.example.demo.web;


import com.example.demo.service.request.CatCreateRequest;
import com.example.demo.service.response.CatDto;
import com.example.demo.service.CatService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Cats controller", description = "get and create cats")
@RequestMapping("cats")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping
    public CatDto createCat(@RequestBody CatCreateRequest request) {
        return catService.createCat(request);
    }

    @PutMapping("{id}")
    public CatDto updateCat(@PathVariable("id") long id, @RequestBody CatCreateRequest request) {
        return catService.updateCat(id, request);
    }

    @GetMapping("{id}")
    public CatDto getById(@PathVariable("id") long id) {
        return catService.getCat(id);
    }

    @GetMapping
    public List<CatDto> findAllCats() {
        return catService.findAllCats();
    }

}
