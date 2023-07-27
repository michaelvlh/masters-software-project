package com.team12.foodforall.controller.donation;

import com.team12.foodforall.domain.Project;
import com.team12.foodforall.paypal.Order;
import com.team12.foodforall.paypal.CreatePayment;

import com.team12.foodforall.service.project.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;


@Controller
public class DonateController {

    @Autowired
    CreatePayment service;

    @Autowired
    private ProjectService projectService;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    public Long ID;

    @GetMapping("/donate")
    public String getID(@RequestParam("id") String id, Model model) {
        try{
            Long number = Long.parseLong(id);
            System.out.println(number);
            ID = number;
            Project project = projectService.findById(number).get();
            model.addAttribute("projects", project);
            model.addAttribute("pTitle", project.getTitle());
            model.addAttribute("pDesc", project.getContent());
            model.addAttribute("pPrice", project.getPrice());
            model.addAttribute("pCurr", project.getCurrency());
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        return "donate";
    }


    @RequestMapping(value="/pay")
    public String payment(@ModelAttribute("order") Order order) {
        try {
            Payment payment = service.createPayment(order.getQuantity(), order.getDescription(), order.getCurrency(), order.getPrice(), "http://localhost:8000/" + CANCEL_URL,
                    "http://localhost:8000/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    projectService.updateProjectProgress(ID, order.getQuantity());
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "index";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, Model model) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                model.addAttribute("paymentID", paymentId);
                return "paymentSuccess";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/email")
    public String share(){
        return "email";
    }


}
