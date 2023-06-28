package online.shop.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.shop.domain.item.Book;
import online.shop.dto.BookForm;
import online.shop.service.ItemService;
import online.shop.validation.ItemValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemValidator itemValidator;

    @GetMapping("items/new")
    public String createForm(Model model){
        model.addAttribute("bookForm",new BookForm());
        return "item-form";
    }

//    @InitBinder
//    public void init(WebDataBinder webDataBinder) {
//        webDataBinder.addValidators(itemValidator);
//    }

    @PostMapping("items/new")
    public String create(@Validated @ModelAttribute BookForm bookForm, BindingResult bindingResult, Model model){


        //itemValidator.validate(bookForm, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            return "/item-form2";
        }

        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());
        itemService.saveItem(book);
        return "redirect:/";
    }
}

