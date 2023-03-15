package nl.hu.ict.inno.hellogateways.students;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {

    @Autowired
    private MockMvc mvc;


    @Test
    public void canFetchEnrollments() throws Exception {

        MvcResult result = mvc.perform(get("/students/1/enrollments")).andReturn();
        String resultString = result.getResponse().getContentAsString();
        assertTrue(resultString.contains("BEP1"));
    }
}
