package com.team12.foodforall;

import com.team12.foodforall.domain.Project;
import com.team12.foodforall.domain.User;
import com.team12.foodforall.repository.ProjectRepository;
import com.team12.foodforall.repository.UserRepository;
import com.team12.foodforall.service.project.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author: Heng Gao
 * @date: 29/03/2022 17:02
 **/
@SpringBootTest
public class ProjectRepoTest {

	@Autowired
	ProjectRepository projectRepo;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProjectService projectService;

	@Autowired
	PasswordEncoder encoder;


	public final static Random RANDOM = new Random(System.currentTimeMillis());

	public final static int MAX_TARGET_MEALS = 200;

	@Test
	void TestFindAll() {
		assertThat(projectRepo).isNotNull();

		List<Project> projectLists = new ArrayList<>();


		// search
		projectLists = projectRepo.findAll();
		assertThat(projectLists).isNotNull();
		System.out.println("[check db] found " + projectLists.size() + " projects before test");

		// create a fake user
		System.out.println("[create a user] create a user before test");
		String emailToCreate = "a@a.com";
		User owner = new User();
		User result = userRepository.findByEmail(emailToCreate);
		if (null == result) { // not found, so create
			owner.setFirstName("jack");
			owner.setLastName("westwood");
			owner.setEmail("a@a.com");// this needs to be unique
			owner.setPassword(encoder.encode("asdasdasd"));
			owner.setCharityName("Jack's Charity");
		} else { // found , then use it
			owner = result;
		}
		System.out.println("[result] owner is created :" + owner.getEmail());
		userRepository.save(owner);


		// generate fake projects in batch
		System.out.println("[start test]start create projects");
		Project p;
		for (int i = 0; i < 6; i++) {
			p = createRandomProject();
			p.setUser(owner);
			System.out.println("[progress]in proect_create progress :generting new project: " + p.getTitle());
			System.out.println("[progress]project price " + p.getPrice());

			// projectService throw exception on duplicate project
			System.out.println("saving project-" + i + " into database");
			projectService.addProject(p);
			projectService.updateProjectProgress(p.getId(), RANDOM.nextInt(MAX_TARGET_MEALS - 1));

			// projectRepo can save duplicate project
			// projectRepo.addProject(p);

		}

		// search again
		projectLists = projectRepo.findAll();
		assertThat(projectLists.size()).isNotEqualTo(0);
		System.out.println("[result] " + projectLists.size() + " projects found in database");

	}

	// Create a project with random projectId and userId
	private Project createRandomProject() {
		Project p = new Project();
		p.setTitle("test_p_" + RANDOM.nextInt());
		p.setContent("this is a good project");
		p.setTargetmeals(MAX_TARGET_MEALS);
		Float price = (float) Math.round(new Random().nextFloat() * 2500) / 100 ;
		p.setPrice(price);
		p.setCurrency("USD");
		p.setPositionName("UK");
		p.setImg(getImage());
		return p;
	}

	// return an image under /resources/static
	private String getImage() {
		String imgBase64 = "";
		try {
			File resource = new ClassPathResource("/static/Foodforall.jpeg").getFile();
			byte[] img = Files.readAllBytes(resource.toPath());
			imgBase64 = Base64.getEncoder().encodeToString(img);
			// project.setImg(imgBase64); // img in String
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgBase64;
	}


}
