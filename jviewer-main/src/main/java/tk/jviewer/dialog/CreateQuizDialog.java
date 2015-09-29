package tk.jviewer.dialog;

import tk.jviewer.model.Answer;
import tk.jviewer.model.Question;
import tk.jviewer.model.Test;
import tk.jviewer.service.QuizService;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

import static java.lang.Long.parseLong;
import static org.springframework.util.Assert.hasText;
import static tk.jviewer.model.AnswerType.RADIO_BUTTON;

/**
 * Serves "create quiz" use case.
 */
public class CreateQuizDialog implements Serializable {

    private static final long serialVersionUID = -2334735949960107078L;

    private QuizService quizService;

    private Test quiz;

    private Question editingQuestion;

    private String newQuestionText;
    private String newAnswerText;

    @PostConstruct
    public void init() {
        quiz = quizService.getQuiz();
        editingQuestion = quiz.getCurrentQuestion();
    }

    public Test getQuiz() {
        return quiz;
    }

    public Question getEditingQuestion() {
        return editingQuestion;
    }

    public String getNewQuestionText() {
        return newQuestionText;
    }

    public void setNewQuestionText(String newQuestionText) {
        this.newQuestionText = newQuestionText;
    }

    public String getNewAnswerText() {
        return newAnswerText;
    }

    public void setNewAnswerText(String newAnswerText) {
        this.newAnswerText = newAnswerText;
    }

    public String cancelQuizCreation() {
        quizService.saveQuiz(quiz);
        return "main?faces-redirect=true";
    }

    public void onEditingQuestionChanged() {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final ExternalContext externalContext = facesContext.getExternalContext();
        final Map<String, String> params = externalContext.getRequestParameterMap();
        final String id = params.get("id");
        editingQuestion = quizService.getQuestion(parseLong(id));
    }

    public void onAddNewQuestionPressed() {
        hasText(newQuestionText);
        final Question question = new Question(RADIO_BUTTON);
        question.setText(newQuestionText);
        quizService.addQuestion(quiz, question);
    }

    public void onAddNewAnswerPressed() {
        hasText(newAnswerText);
        editingQuestion.addAnswer(new Answer(newAnswerText, editingQuestion.getTypeOfAnswers()));
    }

    //
    // Dependency Injection
    //

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

}
