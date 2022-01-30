package jpalatte.jpacafe.controller;

import jpalatte.jpacafe.domain.Customer;
import jpalatte.jpacafe.domain.item.Item;
import jpalatte.jpacafe.repository.OrderSearch;
import jpalatte.jpacafe.service.CustomerService;
import jpalatte.jpacafe.service.ItemService;
import jpalatte.jpacafe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ItemService itemService;

    @RequestMapping(value = "/orders/new", method = RequestMethod.GET)
    public String createForm(Model model) {

        List<Customer> customers = customerService.findCustomers();
        List<Item> items = itemService.findItems();

        model.addAttribute("customers", customers);
        model.addAttribute("items", items);

        return "orders/createOrderForm";
    }

    @RequestMapping(value = "/orders/new", method = RequestMethod.POST)
    public String order(@RequestParam("customerId") Long customerId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {

        orderService.order(customerId, itemId, count);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        model.addAttribute("orders", orderService.findOrdersFromSearch(orderSearch));
        return "orders/orderList";
    }

    @RequestMapping(value = "/orders/{orderId}/cancel", method = RequestMethod.POST) //orderList.html script url
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
