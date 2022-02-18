package ua.com.dfyzok.lesson_9_decompose_university.restcontroller;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import ua.com.dfyzok.lesson_9_decompose_university.configuration.SpringConfig;
import ua.com.dfyzok.lesson_9_decompose_university.configuration.TestProfileConfig;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Group;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestProfileConfig.class, SpringConfig.class })
@ActiveProfiles("test")
@SpringBootTest(classes = TestProfileConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentRestControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void testCreateAndUpdateGroup() {
		Long id = 1L;
		HttpHeaders headers = new HttpHeaders();
		Group groupForAdd = new Group();
		groupForAdd.setCapacity(25);
		groupForAdd.setGroupId(id);
		groupForAdd.setGroupName("AB-12");
		groupForAdd.setLessons(new HashSet<>());
		groupForAdd.setStudents(new HashSet<>());
		ResponseEntity<Group> postResponse = restTemplate.postForEntity(getRootUrl() + "/groups",
				new HttpEntity<>(groupForAdd, headers), Group.class);
		Assert.assertEquals(200, postResponse.getStatusCodeValue());
		Group groupFromDB = restTemplate.getForObject(getRootUrl() + "/groups/" + id, Group.class);
		groupFromDB.setGroupName("DC-12");
		ResponseEntity<Group> updateResponse = restTemplate.exchange(getRootUrl() + "/groups/" + id, HttpMethod.PUT,
				new HttpEntity<>(groupFromDB, headers), Group.class, id);
		Assert.assertEquals(200, updateResponse.getStatusCodeValue());
	}

	@Test
	public void testDeleteGroup() {
		Long id = 4L;
		HttpHeaders headers = new HttpHeaders();
		Group group = new Group();
		group.setCapacity(21);
		group.setGroupId(id);
		group.setGroupName("JU-88");
		group.setLessons(new HashSet<>());
		group.setStudents(new HashSet<>());
		ResponseEntity<Group> postResponse = restTemplate.postForEntity(getRootUrl() + "/groups",
				new HttpEntity<>(group, headers), Group.class);
		Assert.assertEquals(200, postResponse.getStatusCodeValue());
		restTemplate.delete(getRootUrl() + "/groups/" + id);
		ResponseEntity<Group> getResponse = restTemplate.getForEntity(getRootUrl() + "/groups/" + id, Group.class);
		assertNotNull(getResponse);
		Assert.assertEquals(500, getResponse.getStatusCodeValue());
	}
}
