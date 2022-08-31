package nl.hu.sd.inno.basicboot.counter.presentation;

import nl.hu.sd.inno.basicboot.counter.domain.Counter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class CounterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        Counter.getInstance().reset();
    }


    @Test
    public void getCounter() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/counter");

        Counter.getInstance().increment();

        mockMvc.perform(req)
                .andExpect(
                        MockMvcResultMatchers.jsonPath("value", Matchers.equalTo(1)));
    }

    @Test
    public void incrementCounter() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.post("/counter/increment");
        mockMvc.perform(req).andReturn();

        assertEquals(1, Counter.getInstance().getCurrentValue());
    }
}
