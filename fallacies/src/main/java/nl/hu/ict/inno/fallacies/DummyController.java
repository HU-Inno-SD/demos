package nl.hu.ict.inno.fallacies;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("test")
public class DummyController {


    @GetMapping
    public String helloWorld(@RequestParam(required = false, defaultValue = "0") int size) {
        if (size == 0) {
            return "Hello World!";
        } else {
            StringBuilder sb = new StringBuilder("H");
            for (int i = 1; i < size; i++) {
                sb.append("i");
            }
            return sb.toString();
        }
    }

    @PostMapping
    public String acceptInput(HttpServletRequest req) throws IOException {
        int bytes = 0;
        while(req.getInputStream().read() != -1){
            bytes++;
        }
        return String.valueOf(bytes);
    }


}


