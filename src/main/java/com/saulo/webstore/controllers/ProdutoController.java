package com.saulo.webstore.controllers;

import com.saulo.webstore.dtos.ProdutoDTO;
import com.saulo.webstore.dtos.ProdutoInsertDTO;
import com.saulo.webstore.dtos.converter.ProdutoDTOConverter;
import com.saulo.webstore.dtos.converter.ProdutoInsertDTOConverter;
import com.saulo.webstore.models.Cupom;
import com.saulo.webstore.models.Produto;
import com.saulo.webstore.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Integer id) {
        Produto obj = produtoService.findById(id);
        ProdutoDTO dto = ProdutoDTOConverter.entityToDTO(obj);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ProdutoInsertDTO dto, @PathVariable Integer id ){
        Produto obj = ProdutoInsertDTOConverter.dtoToEntity(dto);
        obj.setId(id);
        obj = produtoService.update(obj);
        return ResponseEntity.noContent().build();

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/codigo/{codigo}", method=RequestMethod.GET)
    public ResponseEntity<Produto> findByCodigo(@PathVariable String codigo) {
        Produto obj = produtoService.findByCodigo(codigo);

        return ResponseEntity.ok().body(obj);
    }

}
