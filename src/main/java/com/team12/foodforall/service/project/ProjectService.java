package com.team12.foodforall.service.project;

import com.team12.foodforall.domain.Project;
import com.team12.foodforall.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * @author: Heng Gao
 * @date: 29/03/2022 21:24
 **/
@Service
public interface ProjectService {

    Project addProject(Project project);

    Project addProject(Project project, MultipartFile image, User user);


    Optional<Project> findById(Long id);

    String countProjects();

    String countTotalRevenue();

    String countCompletedProjectNumber();

    List<Project> getToalRevenueForAllProjects();

    Project updateProjectProgress(Long id, Integer amt);

    Project updateProjectProgressSub(Long id);


}
