package com.saulo.webstore.dtos.converter;

import com.saulo.webstore.dtos.ProdutoDTO;
import com.saulo.webstore.dtos.ProdutoDTO;
import com.saulo.webstore.models.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDTOConverter {
    public static ProdutoDTO entityToDTO(Produto produto){
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        dto.setDescricao(produto.getDescricao());
        dto.setDescontoPromocao(produto.getDescontoPromocao());
        dto.setCategoria(CategoriaDTOConverter.entityToDTO(produto.getCategoria()));

        return dto;
    }

    public static List<ProdutoDTO> entityToDTOList(List<Produto> category){

        return category.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());
    }

    public static Produto dtoToEntity(ProdutoDTO dto){
        Produto entity = new Produto();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setPreco(dto.getPreco());
        entity.setDescricao(dto.getDescricao());
        entity.setDescontoPromocao(dto.getDescontoPromocao());
        entity.setCategoria(CategoriaDTOConverter.dtoToEntity(dto.getCategoria()));

        return entity;
    }

    public static List<Produto> dtoToEntityList(List<ProdutoDTO> categoryWSubCatDTO){
        return categoryWSubCatDTO.stream().map(x ->  dtoToEntity(x)).collect(Collectors.toList());
    }
}
