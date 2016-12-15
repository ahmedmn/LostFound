package com.lostfound.mvc.forms;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.LostFound.dto.ItemCreateDTO;

/**
 * The place for validation checks. Useful for checks involving multiple properties at once.
 * This SpringMVC validation is <b>additional</b> to the JSR-303 validation specified by annotations in ItemCreateDTO.
 *
 * @author ahmed
 */
public class ItemCreateDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ItemCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	ItemCreateDTO itemCreateDTO = (ItemCreateDTO) target;
        if (itemCreateDTO.getName() == null) return;
        if (itemCreateDTO.getDescription() == null) return;
        if (itemCreateDTO.getCategoryId() == null)
            errors.rejectValue("Category", "ItemCreateDTOValidator.Category.null");
    }
}
