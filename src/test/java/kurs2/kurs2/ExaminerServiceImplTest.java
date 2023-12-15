package kurs2.kurs2;

import kurs2.kurs2.ExaminerService;
import kurs2.kurs2.Question;
import kurs2.kurs2.QuestionService;
import kurs2.kurs2.service.ExaminerServiceImpl;
import kurs2.kurs2.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExaminerServiceImplTest {
    private ExaminerService examinerService;
    private QuestionService questionService;

    @BeforeEach
    public void setup() {
        questionService = new JavaQuestionService();
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    public void testGetQuestions() {
        Question question1 = new Question("What is Java?", "Java is a programming language");
        Question question2 = new Question("What is OOP?", "OOP stands for Object-Oriented Programming");

        questionService.addQuestion(question1);
        questionService.addQuestion(question2);

        List<Question> questions = examinerService.getQuestions(2);

        assertNotNull(questions);
        assertEquals(2, questions.size());
    }

    @Test
    public void testGetQuestions_InvalidAmount() {
        Question question1 = new Question("What is Java?", "Java is a programming language");
        Question question2 = new Question("What is OOP?", "OOP stands for Object-Oriented Programming");

        questionService.addQuestion(question1);
        questionService.addQuestion(question2);

        assertThrows(IllegalArgumentException.class, () -> {
            examinerService.getQuestions(3);
        });
    }
}
