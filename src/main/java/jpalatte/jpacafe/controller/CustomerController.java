package jpalatte.jpacafe.controller;
import jpalatte.jpacafe.domain.Customer;
import jpalatte.jpacafe.domain.Team;
import jpalatte.jpacafe.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CustomerController {

        private final CustomerService customerService;

        @RequestMapping(value = "/customers/new", method = RequestMethod.GET) //getMapping보다 RequestMapping 추천
        public String createForm(Model model) {
            model.addAttribute("customerForm", new CustomerForm());
            return "customers/createCustomerForm";
        }
        @RequestMapping(value = "/customers/new", method = RequestMethod.POST)
        public String create(@Valid CustomerForm form, BindingResult result) { //@Valid form 받아 form의 @NotEmpty등 validation 사용
            if (result.hasErrors()) { //validation의 bindingResult err 대한 처리
                return "customers/createCustomerForm";
            }
            Customer customer = new Customer();
            customer.setName(form.getName());
            customer.setTeam(new Team(form.getTeamName(), form.getFloor()));
            customerService.join(customer);
            return "redirect:/";
        }

        @RequestMapping(value = "/customers", method = RequestMethod.GET) //만약 조회 시 Entity 중 일부만이면 역시 Form이나 DTO, 우선 여기는 Entity 자체
        public String list(Model model){
            model.addAttribute("customers", customerService.findCustomers());
            return "customers/customerList";
        }
        }
