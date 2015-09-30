package tk.jviewer.service.impl;

import tk.jviewer.converter.TestConverter;
import tk.jviewer.dao.quiz.AnswerDao;
import tk.jviewer.dao.quiz.QuestionDao;
import tk.jviewer.dao.quiz.QuizDao;
import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;
import tk.jviewer.model.Test;
import tk.jviewer.service.QuizService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static tk.jviewer.model.AnswerType.CHECK_BOX;
import static tk.jviewer.model.AnswerType.RADIO_BUTTON;
import static tk.jviewer.model.AnswerType.TEXT_FIELD;

/**
 * See {@link QuizService}.
 */
public class QuizServiceImpl implements QuizService {

    private static final String DEFAULT_QUIZ_NAME = "New quiz";

    private static final int DEFAULT_QUESTIONS_TO_ANSWER_TO_PASS = 0;

    private static final String DEFAULT_QUESTION_TEXT = "Who lives in a pineapple under the sea?";

    private static final AnswerType DEFAULT_ANSWERS_TYPE = RADIO_BUTTON;

    private QuizDao quizDao;
    private QuestionDao questionDao;
    private AnswerDao answerDao;

    @Override
    public Test createQuiz() {
        final long quizId = quizDao.createQuiz(DEFAULT_QUIZ_NAME, DEFAULT_QUESTIONS_TO_ANSWER_TO_PASS);
        final Question question = new Question(DEFAULT_QUESTION_TEXT, DEFAULT_ANSWERS_TYPE);
        final long questionId = questionDao.createQuestion(quizId, DEFAULT_ANSWERS_TYPE, DEFAULT_QUESTION_TEXT);
        question.setId(questionId);

        return new Test(quizId, DEFAULT_QUIZ_NAME, new ArrayList<>(singletonList(question)), DEFAULT_QUESTIONS_TO_ANSWER_TO_PASS);
    }

    @Override
    public List<Test> findQuizzes() {
        return quizDao.findQuizzes();
    }

    @Override
    public void updateQuiz(final Test quiz) {
        quizDao.updateQuiz(quiz);
    }

    @Override
    public List<Question> findQuestionsForQuiz(final long quizId) {
        return questionDao.findQuestions(quizId);
    }

    @Override
    public Question findQuestion(final long id) {
        return questionDao.findQuestion(id);
    }

    @Override
    public void createQuestion(final Test quiz, final String text) {
        final Question question = new Question(text, RADIO_BUTTON);
        final long id = questionDao.createQuestion(quiz.getId(), question.getTypeOfAnswers(), question.getText());
        question.setId(id);
        quiz.addQuestion(question);
    }

    @Override
    public void createAnswer(final Question question, final Answer answer) {
        long id = answerDao.createAnswer(question.getId(), answer.getText());
        answer.setId(id);
        question.addAnswer(answer);
    }

    @Override
    public void removeAnswer(final Question question, final long answerId) {
        answerDao.removeAnswer(answerId);
        question.removeAnswer(answerId);
    }

    /**
     * Returns dummy test. It should be removed after real implementation. Also please take care about {@link TestConverter}.
     *
     * @return see description.
     */
    private Test fillDummyTest() {
        Question question1 = new Question(RADIO_BUTTON);
        question1.setId(1);
        question1.setTopic("That is the question");
        question1.setText("To be or not to be?");
        Answer answer1 = new Answer(0, "To be");
        Answer answer2 = new Answer(1, "Not to be");
        question1.setAnswers(new ArrayList<>(asList(answer1, answer2)));
        question1.setCorrectSingleChoiceAnswer(0L);

        Question question2 = new Question(CHECK_BOX);
        question2.setId(2);
        question2.setTopic("Random test");
        question2.setText("Who lives in a pineapple under the sea?");
        Answer answer21 = new Answer(0, "Barmaley");
        Answer answer22 = new Answer(1, "Sponge Bob Square Pants");
        Answer answer23 = new Answer(2, "Earthworm Jim");
        Answer answer24 = new Answer(3, "Princess Nesmeyana");
        question2.setAnswers(new ArrayList<>(asList(answer21, answer22, answer23, answer24)));
        question2.setCorrectMultipleChoiceAnswers(new ArrayList<>(asList("1", "2")));

        Question question3 = new Question(TEXT_FIELD);
        question3.setId(3);
        question3.setTopic("Arithmetical question");
        question3.setText("2 + 2 = ?");
        question3.setCorrectTextualAnswer("4");

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        return new Test(0, "Test", questions, 2);
    }

    //
    // Dependency Injection
    //

    public void setQuizDao(QuizDao quizDao) {
        this.quizDao = quizDao;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public void setAnswerDao(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

}
