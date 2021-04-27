package com.saulo.webstore.controllers;

import com.saulo.webstore.dtos.ProdutoInsertDTO;
import com.saulo.webstore.dtos.converter.ProdutoInsertDTOConverter;
import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.models.Pedido;
import com.saulo.webstore.models.Produto;
import com.saulo.webstore.services.CategoriaService;
import com.saulo.webstore.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Produto> findById(@PathVariable Integer id) {
        Produto obj = produtoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@RequestBody ProdutoInsertDTO dto){

        Produto produto = ProdutoInsertDTOConverter.dtoToEntity(dto);
        produto = produtoService.insert(produto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping( method=RequestMethod.GET)
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> list = produtoService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ProdutoInsertDTO dto, @PathVariable Integer id ){
        Produto obj = ProdutoInsertDTOConverter.dtoToEntity(dto);
        obj.setId(id);
        obj = produtoService.update(obj);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
