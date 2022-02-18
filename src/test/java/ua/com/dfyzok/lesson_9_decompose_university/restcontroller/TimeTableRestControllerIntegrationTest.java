package ua.com.dfyzok.lesson_9_decompose_university.restcontroller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
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

import com.fasterxml.jackson.core.JsonProcessingException;

import ua.com.dfyzok.lesson_9_decompose_university.configuration.SpringConfig;
import ua.com.dfyzok.lesson_9_decompose_university.configuration.TestProfileConfig;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Group;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Lesson;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Student;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Teacher;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestProfileConfig.class, SpringConfig.class })
@ActiveProfiles("test")
@SpringBootTest(classes = TestProfileConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimeTableRestControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    HttpHeaders headers = new HttpHeaders();

    static String TESTDATABEGIIN = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date(1));
    static String TESTDATALESSON = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date(2));
    static String TESTDATAEND = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date(3));
    Long teacherId = 0L;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testTimeTable() throws JsonProcessingException {
        Group group = new Group();
        Teacher teacher = new Teacher();
        teacher.setTeacherId(0L);
        Lesson lesson = new Lesson();
        lesson.setLessonId(0L);
        lesson.setCourseName("Astronomy");
        lesson.setGroup(group);
        lesson.setLessonId(0L);
        lesson.setLessonName("1st lesson");
        lesson.setTeacher(teacher);
        lesson.setTime(TESTDATALESSON);
        ResponseEntity<Lesson> postResponse = restTemplate.postForEntity(getRootUrl() + "/lessons", lesson,
                Lesson.class);
        System.out.println(postResponse);
        System.out.println(postResponse.getBody());
        Long studentId = 0L;
        String timeTable = restTemplate.getForObject(getRootUrl() + "/timetable/searchtimetablestudent/" + studentId
                + "/" + TESTDATABEGIIN + "/" + TESTDATAEND, String.class);
        System.out.println(timeTable);
        assertNotNull(timeTable);
    }

}
