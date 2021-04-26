package com.saulo.webstore.dtos.converter;

import com.saulo.webstore.dtos.CategoriaDTO;
import com.saulo.webstore.models.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDTOConverter {

    public static CategoriaDTO entityToDTO(Categoria categoria){
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());

        return dto;
    }

    public static List<CategoriaDTO> entityToDTOList(List<Categoria> category){

        return category.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());
    }

    public static Categoria dtoToEntity(CategoriaDTO categoriaDTO){
        Categoria entity = new Categoria();
        entity.setId(categoriaDTO.getId());
        entity.setNome(categoriaDTO.getNome());

        return entity;
    }

    public static List<Categoria> dtoToEntityList(List<CategoriaDTO> categoryWSubCatDTO){
        return categoryWSubCatDTO.stream().map(x ->  dtoToEntity(x)).collect(Collectors.toList());
    }
}
