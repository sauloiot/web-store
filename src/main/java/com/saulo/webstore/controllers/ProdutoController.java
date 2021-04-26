package com.saulo.webstore.controllers;

import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.models.Pedido;
import com.saulo.webstore.models.Produto;
import com.saulo.webstore.services.CategoriaService;
import com.saulo.webstore.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Produto obj){
        obj = produtoService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping( method=RequestMethod.GET)
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> list = produtoService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Produto obj, @PathVariable Integer id ){
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
