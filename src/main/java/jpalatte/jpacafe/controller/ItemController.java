package jpalatte.jpacafe.controller;

import jpalatte.jpacafe.domain.item.Coffee;
import jpalatte.jpacafe.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @RequestMapping(value = "/items/new", method = RequestMethod.GET)
    public String createCoffeeForm(Model model) {
        model.addAttribute("form", new CoffeeForm());
        return "items/createItemForm";
    }

    @RequestMapping(value = "/items/new", method = RequestMethod.POST)
    public String create(CoffeeForm form) {
        Coffee coffee = new Coffee();
        coffee.setName(form.getName());
        coffee.setPrice(form.getPrice());
        coffee.setStockQuantity(form.getStockQuantity());
        coffee.setSizeUp(form.isSizeUp());
        coffee.setTopping(form.getTopping());
        itemService.saveItem(coffee);
        return "redirect:/";
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("items", itemService.findItems());
        return "/items/itemList";
    }

    @RequestMapping(value ="/items/{itemId}/edit", method = RequestMethod.GET)
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Coffee item = (Coffee) itemService.findOne(itemId);

        CoffeeForm form = new CoffeeForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setSizeUp(item.isSizeUp());
        form.setTopping(item.getTopping());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @RequestMapping(value = "/items/{itemId}/edit", method = RequestMethod.POST)
    public String updateItem(@PathVariable String itemId, @ModelAttribute("form") CoffeeForm form) {  //updateItemForm ${form}

        Coffee coffee = new Coffee();
        coffee.setId(form.getId());
        coffee.setName(form.getName());
        coffee.setPrice(form.getPrice());
        coffee.setStockQuantity(form.getStockQuantity());
        coffee.setSizeUp(form.isSizeUp());
        coffee.setTopping(form.getTopping());

        itemService.saveItem(coffee);
        return "redirect:/items";
    }


}
