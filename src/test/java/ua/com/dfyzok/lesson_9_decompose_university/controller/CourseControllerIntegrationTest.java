package ua.com.dfyzok.lesson_9_decompose_university.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ua.com.dfyzok.lesson_9_decompose_university.configuration.SpringConfig;
import ua.com.dfyzok.lesson_9_decompose_university.configuration.TestProfileConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestProfileConfig.class })
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestProfileConfig.class, SpringConfig.class })
@WebAppConfiguration
@ActiveProfiles("test")
public class CourseControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void webAppContextTest() throws Exception {
        assertTrue(webApplicationContext.getServletContext() instanceof MockServletContext);
    }

    @Test
    public void allCoursesFromDataBaseAreAvailableOnWeb() throws Exception {
        this.mockMvc.perform(get("/course/list").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk()).andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    public void givenWacWhenServletContextThenItProvidesCourseController() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("courseController"));
    }
}
