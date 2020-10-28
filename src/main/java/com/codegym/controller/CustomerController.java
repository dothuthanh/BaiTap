package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.CustomerForm;
import com.codegym.model.Province;
import com.codegym.service.CustomerService;
import com.codegym.service.ProvinceService;
import com.codegym.service.exption.DuplicationExption;
import com.codegym.service.exption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
//@SessionAttributes("customer")
public class CustomerController {
    @Autowired
    private Environment environment;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces() {
        return provinceService.finAll();
    }

    //    @ModelAttribute("customer")
//    public Customer setUpcounter(){
//        return new Customer();
//    }
    @GetMapping("/customers")
    public ModelAndView listCustomers() {
        Iterable<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

        @GetMapping("/create-customer")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new CustomerForm());
        return modelAndView;
    }

        @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") CustomerForm customerForm) throws DuplicationExption {
        Customer customer = new Customer(customerForm.getName(),customerForm.getOld(), customerForm.getAddress(), customerForm.getDetail(),customerForm.getPhoneNumber(),customerForm.getProvince());
        MultipartFile file = customerForm.getImg();
        String image = file.getOriginalFilename();
        customer.setImg(image);
        String fileUpload = environment.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        customerService.save(customer);
        return new ModelAndView("/customer/create", "customer", new CustomerForm());

    }
    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) throws NotFoundException {
        Customer customer = customerService.findById(id);
        if(customer != null) {
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer);
            return modelAndView;

        }else {
//            ModelAndView modelAndView = new ModelAndView("/error.404");
            ModelAndView modelAndView = new ModelAndView("/customer/exption");
            return modelAndView;
        }
    }

    @PostMapping("/edit-customer")
    public ModelAndView updateCustomer(@ModelAttribute("customer") CustomerForm customerForm) throws DuplicationExption {
        Customer customer = new Customer(customerForm.getName(),customerForm.getOld(), customerForm.getAddress(), customerForm.getDetail(),customerForm.getPhoneNumber());
        customer.setId(customerForm.getId());
        customer.setProvince(customerForm.getProvince());
        MultipartFile file = customerForm.getImg();
        String image = file.getOriginalFilename();
        customer.setImg(image);
        String fileUpload = environment.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        customerService.save(customer);
        return new ModelAndView("/customer/edit", "customer", new CustomerForm());

    }
    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) throws Exception {
        Customer customer = customerService.findById(id);
        if(customer != null) {
            ModelAndView modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer", customer);
            return modelAndView;

        }else {
//            ModelAndView modelAndView = new ModelAndView("/error.404");
            ModelAndView modelAndView = new ModelAndView("/customer/exption");
            return modelAndView;
        }
    }

    @PostMapping("/delete-customer")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.remove(customer.getId());
        return "redirect:customers";
    }
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/customer/exption");
    }

//    @PostMapping("/")
//    public String checkValidation (@Valid @ModelAttribute("phoneNunber")Customer customer, BindingResult bindingResult, Model model){
//        new Customer().validate(customer, bindingResult);
//        if (bindingResult.hasFieldErrors()){
//            return "/customer/create";
//        }
//        else {
//            model.addAttribute("customer", customer.getPhoneNumber());
//            return "/customer/create";
//        }
//    }
}
