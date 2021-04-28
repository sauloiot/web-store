package com.saulo.webstore.dtos.converter;

import com.saulo.webstore.dtos.ProdutoInsertDTO;
import com.saulo.webstore.models.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoInsertDTOConverter {
    public static ProdutoInsertDTO entityToDTO(Produto produto){
        ProdutoInsertDTO dto = new ProdutoInsertDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        dto.setDescricao(produto.getDescricao());
        dto.setDescontoPromocao(produto.getDescontoPromocao());
        dto.setImagemURL(produto.getImagemURL());
        dto.setIdCategoria(produto.getCategoria().getId());

        return dto;
    }

    public static List<ProdutoInsertDTO> entityToDTOList(List<Produto> category){

        return category.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());
    }

    public static Produto dtoToEntity(ProdutoInsertDTO dto){
        Produto entity = new Produto();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setPreco(dto.getPreco());
        entity.setDescricao(dto.getDescricao());
        entity.setDescontoPromocao(dto.getDescontoPromocao());
        entity.setImagemURL(dto.getImagemURL());
        entity.getCategoria().setId(dto.getIdCategoria());

        return entity;
    }

    public static List<Produto> dtoToEntityList(List<ProdutoInsertDTO> categoryWSubCatDTO){
        return categoryWSubCatDTO.stream().map(x ->  dtoToEntity(x)).collect(Collectors.toList());
    }
}
