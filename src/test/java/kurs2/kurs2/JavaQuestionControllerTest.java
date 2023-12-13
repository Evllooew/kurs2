package kurs2.kurs2;



import com.fasterxml.jackson.databind.ObjectMapper;
import kurs2.kurs2.Controller.JavaQuestionController;
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

@WebMvcTest(JavaQuestionController.class)
public class JavaQuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @Test
    public void testAddQuestion() throws Exception {
        Question question = new Question("What is Java?", "Java is a programming language.");

        mockMvc.perform(MockMvcRequestBuilders.post("/exam/java/add")
                        .content(asJsonString(question))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(questionService).addQuestion(Mockito.any(Question.class));
    }

    @Test
    public void testRemoveQuestion() throws Exception {
        Question question = new Question("What is Java?", "Java is a programming language.");

        mockMvc.perform(MockMvcRequestBuilders.post("/exam/java/remove")
                        .content(asJsonString(question))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(questionService).removeQuestion(Mockito.any(Question.class));
    }

    @Test
    public void testGetAllQuestions() throws Exception {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is Java?", "Java is a programming language."));
        questions.add(new Question("What is Spring?", "Spring is a Java framework."));

        Mockito.when(questionService.getAllQuestions()).thenReturn(questions);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/java/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].question").value("What is Java?"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].answer").value("Java is a programming language."))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].question").value("What is Spring?"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].answer").value("Spring is a Java framework."));
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
