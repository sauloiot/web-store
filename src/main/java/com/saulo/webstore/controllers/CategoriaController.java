package com.saulo.webstore.controllers;

import com.saulo.webstore.dtos.CategoriaDTO;
import com.saulo.webstore.dtos.converter.CategoriaDTOConverter;
import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping( method=RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaDTO> listDto = CategoriaDTOConverter.entityToDTOList(list);
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        Categoria obj = categoriaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO){
//        Categoria obj = categoriaService.fromDTO(objDTO);
//        obj = categoriaService.insert(obj);
//        URI uri = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(obj.getId())
//                .toUri();
//        return ResponseEntity.created(uri).build();
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id ){
//        Categoria obj = categoriaService.fromDTO(objDto);
//        obj.setId(id);
//        obj = categoriaService.update(obj);
//        return ResponseEntity.noContent().build();
//
//    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();

    }


}
