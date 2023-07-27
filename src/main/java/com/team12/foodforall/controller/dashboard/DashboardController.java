package com.team12.foodforall.controller.dashboard;

import com.team12.foodforall.service.project.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Heng Gao
 * @date: 02/05/2022 22:44
 **/

@Controller
public class DashboardController {

    private final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    ProjectService projectService;

    @RequestMapping("/dashboard-graphs")
    public String dashboardGraphs(Model model){
        DashboardData data = getData();

        model.addAttribute("dashboardData", data);

        return "dashboard-graphs";
    }


    @RequestMapping("/dashboard-projects")
    public String dashboardProjects(){
        return "dashboard-projects";
    }



    DashboardData getData(){
        DashboardData data = new DashboardData();

        data.setNumOfProjects(projectService.countProjects());
        data.setTotalRevenue(projectService.countTotalRevenue());
        data.setNumOfCompletedProjects(projectService.countCompletedProjectNumber());
        data.setRevenueList(projectService.getToalRevenueForAllProjects());

        logger.info("Counting projects:" + projectService.countProjects());

        return data;
    }

}
