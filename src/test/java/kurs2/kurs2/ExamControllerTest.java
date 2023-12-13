package kurs2.kurs2;

import kurs2.kurs2.Controller.ExamController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ExamController.class)
public class ExamControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExaminerService examinerService;

    @Test
    public void testGetQuestions() throws Exception {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is Java?", "Java is a programming language."));
        questions.add(new Question("What is Spring?", "Spring is a Java framework."));

        Mockito.when(examinerService.getQuestions(Mockito.anyInt())).thenReturn(questions);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/getQuestions")
                        .param("amount", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].question").value("What is Java?"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].answer").value("Java is a programming language."))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].question").value("What is Spring?"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].answer").value("Spring is a Java framework."));
    }
}