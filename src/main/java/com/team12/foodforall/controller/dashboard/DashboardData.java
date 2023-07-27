package com.team12.foodforall.controller.dashboard;

import com.team12.foodforall.domain.Project;
import lombok.Data;

import java.util.List;

/**
 * @author: Heng Gao
 * @date: 03/05/2022 00:21
 **/
@Data
public class DashboardData {
    String totalRevenue;
    String numOfProjects;
    String numOfCompletedProjects;
    List<Project> revenueList;
}

