package kurs2.kurs2;

import kurs2.kurs2.Question;

import java.util.List;

public interface ExaminerService {
    List<Question> getQuestions(int amount);
}