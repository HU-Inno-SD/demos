package nl.hu.ict.inno.hellogateways.courses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTests {

    @Autowired
    ObjectMapper mapper;

    private <T> T getResponseObject(MvcResult result, Class<T> tClass) throws Exception {
        return mapper.readValue(result.getResponse().getContentAsString(), tClass);
    }

    @Autowired
    private MockMvc mvc;

    @Test
    public void wontIncludeTransitivesInGet() throws Exception {
        MvcResult result = mvc.perform(get("/courses/INNO-SD")).andReturn();

        CourseController.CourseDTO response = getResponseObject(result, CourseController.CourseDTO.class);

        assertEquals(1, response.prereqs().size());
    }
}
