package kurs2.kurs2;

import kurs2.kurs2.Question;
import kurs2.kurs2.QuestionService;
import kurs2.kurs2.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {
    private QuestionService questionService;

    @BeforeEach
    public void setup() {
        questionService = new JavaQuestionService();
    }

    @Test
    public void testAddQuestion() {
        Question question = new Question("What is Java?", "Java is a programming language");
        questionService.addQuestion(question);

        List<Question> questions = questionService.getAllQuestions();
        assertTrue(questions.contains(question));
    }

    @Test
    public void testRemoveQuestion() {
        Question question = new Question("What is Java?", "Java is a programming language");
        questionService.addQuestion(question);

        questionService.removeQuestion(question);

        List<Question> questions = questionService.getAllQuestions();
        assertFalse(questions.contains(question));
    }

    @Test
    public void testGetRandomQuestion() {
        Question question1 = new Question("What is Java?", "Java is a programming language");
        Question question2 = new Question("What is OOP?", "OOP stands for Object-Oriented Programming");

        questionService.addQuestion(question1);
        questionService.addQuestion(question2);

        Question randomQuestion = questionService.getRandomQuestion();

        assertNotNull(randomQuestion);
    }
}
