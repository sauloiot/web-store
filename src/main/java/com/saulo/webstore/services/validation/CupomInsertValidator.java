package com.saulo.webstore.services.validation;

import com.saulo.webstore.controllers.exceptions.FieldMessage;
import com.saulo.webstore.dtos.CupomDTO;
import com.saulo.webstore.models.Cupom;
import com.saulo.webstore.repositories.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CupomInsertValidator implements ConstraintValidator<CupomInsert, CupomDTO> {
    @Autowired
    private CupomRepository cupomRepository;

    @Override
    public void initialize(CupomInsert ann) {
    }

    @Override
    public boolean isValid(CupomDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Cupom cupom = cupomRepository.findByCodigo(dto.getCodigo());
        if (cupom != null) {
            list.add( new FieldMessage("codigo", "Este código ja está cadastrado no sistema"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}