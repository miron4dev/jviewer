package tk.jviewer.dialog;

import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;
import tk.jviewer.model.QuizManagedBean;
import tk.jviewer.model.Test;
import tk.jviewer.service.QuizService;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

import static java.lang.Long.parseLong;
import static javax.faces.context.FacesContext.getCurrentInstance;
import static org.springframework.util.Assert.hasText;
import static tk.jviewer.model.AnswerType.RADIO_BUTTON;

/**
 * Serves "create quiz" use case.
 */
public class CreateQuizDialog implements Serializable {

    private static final long serialVersionUID = -2334735949960107078L;

    private QuizService quizService;

    private QuizManagedBean quizManagedBean;

    private Test quiz;

    private String newQuestionText;
    private String newAnswerText;

    @PostConstruct
    public void init() {
        final Test quizFromSession = quizManagedBean.getCurrentQuiz();
        quiz = quizFromSession == null ? quizService.createQuiz() : quizFromSession;
        quizManagedBean.setEditingQuestion(quiz.getCurrentQuestion());
    }

    public Test getQuiz() {
        return quiz;
    }

    public Question getEditingQuestion() {
        return quizManagedBean.getEditingQuestion();
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
        return "main?faces-redirect=true";
    }

    public void onEditingQuestionChanged() {
        final long id = getIdFromRequest();
        quizManagedBean.setEditingQuestion(quizService.findQuestion(id));
    }

    public void onAddNewQuestionPressed() {
        hasText(newQuestionText);
        quizService.createQuestion(quiz, newQuestionText);
    }

    public void onDeleteQuestionPressed() {
        final long id = getIdFromRequest();
        quizService.removeQuestion(quiz, id);
    }

    public void onAddNewAnswerPressed() {
        hasText(newAnswerText);
        final Answer answer = new Answer(newAnswerText);
        final Question editingQuestion = quizManagedBean.getEditingQuestion();
        quizService.createAnswer(editingQuestion, answer);
        final AnswerType typeOfAnswers = editingQuestion.getTypeOfAnswers();
        if (typeOfAnswers == RADIO_BUTTON && editingQuestion.getCorrectSingleChoiceAnswer() == null) {
            editingQuestion.setCorrectSingleChoiceAnswer(answer.getId());
        }
    }

    public void onDeleteAnswerPressed() {
        final long answerId = getIdFromRequest();
        quizService.removeAnswer(quizManagedBean.getEditingQuestion(), answerId);
    }

    public void onCorrectAnswerChanged() {
        updateEditingQuestion();
    }

    public void onQuizChanged() {
        quizService.updateQuiz(quiz);
    }

    public void onQuestionUpdated() {
        updateEditingQuestion();
    }

    public void onTextualAnswerChanged() {
        updateEditingQuestion();
    }

    //
    // Dependency Injection
    //

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    public void setQuizManagedBean(QuizManagedBean quizManagedBean) {
        this.quizManagedBean = quizManagedBean;
    }

    //
    // Helper Methods
    //

    private long getIdFromRequest() {
        final FacesContext facesContext = getCurrentInstance();
        final ExternalContext externalContext = facesContext.getExternalContext();
        final Map<String, String> params = externalContext.getRequestParameterMap();

        return parseLong(params.get("id"));
    }

    private void updateEditingQuestion() {
        quizService.updateQuestion(quizManagedBean.getEditingQuestion());
    }

}
