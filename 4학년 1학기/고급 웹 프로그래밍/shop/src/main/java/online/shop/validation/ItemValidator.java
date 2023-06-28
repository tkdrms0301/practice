package online.shop.validation;

import online.shop.dto.BookForm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookForm bookForm = (BookForm)target;

        if (!StringUtils.hasText(bookForm.getName())) {
            errors.rejectValue("name","required");
        }
        if (bookForm.getPrice() == null || bookForm.getPrice() < 1000 || bookForm.getPrice() > 1000000) {
            errors.rejectValue("price","range", new Object[]{1000,1000000},null);
        }
        if (bookForm.getStockQuantity() == null || bookForm.getStockQuantity() >= 9999) {
            errors.rejectValue("stockQuantity","max", new Object[]{9999}, null);
        }
        if (bookForm.getPrice() != null && bookForm.getStockQuantity() != null) {
            int resultPrice = bookForm.getPrice() * bookForm.getStockQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }

}
