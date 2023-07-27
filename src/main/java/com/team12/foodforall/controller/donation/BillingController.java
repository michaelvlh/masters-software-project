package com.team12.foodforall.controller.donation;

import com.team12.foodforall.domain.Project;
import com.team12.foodforall.paypal.CreateProduct;
import com.team12.foodforall.paypal.Subscription;
import com.team12.foodforall.paypal.CreatePlan;

import com.team12.foodforall.service.project.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.paypal.base.rest.PayPalRESTException;

import java.io.IOException;

@Controller
public class BillingController {


    @Autowired
    CreatePlan service;
    @Autowired
    CreateProduct product;
    @Autowired
    private ProjectService projectService;

    public static final String CANCEL_URL = "billing/cancel/redir/index";
    public String planID;
    public String subId;
    public Long ID;

    @GetMapping("/billing")
    public String getBilling(@RequestParam("id") String id, Model model){
        try{
            Long number = Long.parseLong(id);
            System.out.println(number);
            ID = number;
            Project project = projectService.findById(number).get();
            model.addAttribute("projects", project);
            model.addAttribute("pID", number);
            model.addAttribute("pTitle", project.getTitle());
            model.addAttribute("pDesc", project.getContent());
            model.addAttribute("pPrice", project.getPrice());
            model.addAttribute("pCurr", project.getCurrency());
            String newProduct = product.createProduct(project.getId(), project.getTitle(), project.getContent());
            model.addAttribute("productID", newProduct);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return "subscribe";
    }

    @PostMapping("/billing/subscribe")
    public String billing(@ModelAttribute("subscribe") Subscription sub) {
        try {
            switch (sub.getFrequency()){
                case "Monthly":
                    String monthly = service.makeMonthly(sub.getProjectID(), sub.getProductID(), sub.getCurrency(), sub.getPrice(), sub.getName());
                    planID = monthly;
                    break;
                case "Quarterly":
                    String quarterly = service.makeQuarterly(sub.getProjectID(), sub.getProductID(), sub.getCurrency(), sub.getPrice(), sub.getName());
                    planID = quarterly;
                    break;
                case "Yearly":
                    String yearly = service.makePlan(sub.getProjectID(), sub.getProductID(), sub.getCurrency(), sub.getPrice(), sub.getName());
                    planID = yearly;
                    break;
            }
            System.out.println(planID);

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/finalise";
    }

    @GetMapping("/finalise")
    public String finalise(Model newModel){
        newModel.addAttribute("planId", planID);
        return "finaliseSub";
    }

    @PostMapping("/bill/summary")
    public String summary(@ModelAttribute("activeSub") String subID, Model newModel){
        subId = subID;
        projectService.updateProjectProgressSub(ID);
        newModel.addAttribute("subId", subId);
        return "subscriptionSuccess";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelBill() {
        return "index";
    }


}
