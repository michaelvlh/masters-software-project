package com.team12.foodforall.controller.project;

import com.team12.foodforall.domain.Project;
import com.team12.foodforall.domain.User;
import com.team12.foodforall.repository.ProjectRepository;
import com.team12.foodforall.service.project.ProjectService;
import com.team12.foodforall.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

/**
 * @author: Heng Gao
 * @date: 08/03/2022 :15:13
 **/
@Controller
public class ProjectController {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectRepository projectRepo;

    @RequestMapping("/")
    public String index(HttpSession session, Model model){

        ArrayList<Project> projectList = (ArrayList<Project>) projectRepo.findAll();


        model.addAttribute("projects", projectList);

        // the string tell which page to go, by deafult its under /resource and /resource/template and /resource/public and /rouserce/static
        return "index";
    }

    // router for return the projects views
    @GetMapping("/projects")
    public String projects(Model model){
        // retrie ve all data from
        ArrayList<Project> projects = (ArrayList<Project>) projectRepo.findAll();

        
        model.addAttribute("projects", projects);
        return "projects";
    }



    @GetMapping("/projects/add")
    public String getAddProjectPage(Project project, Model model){
        return "addProject";
    }


    // router for return the projects views
    @PostMapping("/projects")
    public String saveProject(Model model, @Valid Project project,
                              BindingResult error, @RequestParam("img") MultipartFile file) {

        // V2 retrieve all data from
        User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Project savedProject = projectService.addProject(project, file, user);

        System.out.println(savedProject);
        System.out.println("saving project");
        System.out.println(project);
        return "redirect:/projects";
    }


    //Get a project detail
    @GetMapping("/projects/{id}")
    public String projectDetail(@PathVariable("id") Long id, Model model){
        Project project = projectService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project Id:" + id));

        model.addAttribute("project", project);
        return "project_detail";
    }

    //Delete a project detail
    @DeleteMapping("/projects/{id}")
    public String deleteDetail(@PathVariable("id") Long id, Model model){
        Project project = projectService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project Id:" + id));

        model.addAttribute("project", project);
        projectRepo.delete(project);

        return "redirect:/projects";
    }

    //Update a project detail
    @GetMapping("/projects/update/{id}")
    public String updateProject(@PathVariable("id") Long id, Model model){
        Project project = projectService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project Id:" + id));

        model.addAttribute("project", project);
        return "editProject";
    }
}


