package com.team12.foodforall.service.project;

import com.team12.foodforall.domain.Project;
import com.team12.foodforall.domain.User;
import com.team12.foodforall.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

/**
 * @author: Heng Gao
 * @date: 29/03/2022 21:26
 **/
@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Project addProject(Project project) {

        // validate title duplication
        if(projectRepository.existsByTitle(project.getTitle())) {
            throw new RuntimeException("project exist");
        }
        project.setPrice( (float)Math.round(project.getPrice()*100)  / 100);
        return projectRepository.save(project);
    }

    @Override
    public Project addProject(Project project, MultipartFile img, User user) {

        project.setUser(user);

        // validate title duplication
        if(projectRepository.existsByTitle(project.getTitle())) {
            throw new RuntimeException("project exist");
        }

        try {
            String imgBase64 = Base64.getEncoder().encodeToString(img.getBytes());
            project.setImg(imgBase64); // img in String
        }catch (IOException e){
            e.printStackTrace();
        }

        project.setPrice( (float)Math.round(project.getPrice()*100)  / 100);

        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public String countProjects() {
        return String.valueOf(projectRepository.findAll().size());
    }

    @Override
    public String countTotalRevenue() {
        double sum = projectRepository.findAll().stream().mapToDouble(Project::getCurrentRevenue).sum();
        return String.format("%.2f", sum);
    }

    @Override
    public String countCompletedProjectNumber() {
        return String.valueOf(projectRepository.findAll().stream().filter(Project::isCompleted).count());
    }

    @Override
    public List<Project> getToalRevenueForAllProjects() {
        return projectRepository.findAll();
    }

    public Project updateProjectProgress(Long id, Integer amt){
        Project update = projectRepository.findById(id).get();
        Integer current = update.getAchievedmeals();
        Integer goal = update.getTargetmeals();

        Float prog = (current.floatValue()+amt)*100/goal.floatValue();
        update.setAchievedmeals(current+amt);
        update.setCurrentRevenue((float) (update.getAchievedmeals()* (float)Math.round(update.getPrice()*100)/100));
        update.setProgress((float)Math.round(prog*100) / 100);
        return projectRepository.save(update);
    }

    @Override
    public Project updateProjectProgressSub(Long id){
        Project update = projectRepository.findById(id).get();
        Integer current = update.getAchievedmeals();
        Integer goal = update.getTargetmeals();
        Float prog = (current.floatValue()+1) * 100 /goal.floatValue();
        update.setAchievedmeals(current+1);
        update.setCurrentRevenue((float) (update.getAchievedmeals()* (float)Math.round(update.getPrice()*100)/100));
        update.setProgress((float)Math.round(prog*100) / 100);
        return projectRepository.getById(id);//projectRepository.save(update);
    }
}
