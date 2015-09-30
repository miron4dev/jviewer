package tk.jviewer.dialog;

import org.springframework.util.Assert;
import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;
import tk.jviewer.model.Test;
import tk.jviewer.service.QuizService;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import static java.lang.Long.parseLong;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;
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
        quiz = quizService.createQuiz();
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
        final long id = parseLong(getIdFromRequest());
        editingQuestion = quizService.getQuestion(id);
    }

    public void onAddNewQuestionPressed() {
        hasText(newQuestionText);
        final Question question = new Question(RADIO_BUTTON);
        question.setText(newQuestionText);
        quizService.addQuestion(quiz, question);
    }

    public void onDeleteQuestionPressed() {
        final long id = parseLong(getIdFromRequest());
        final Question question = quizService.getQuestion(id);
        quiz.removeQuestion(question);
    }

    public void onAddNewAnswerPressed() {
        hasText(newAnswerText);
        final AnswerType typeOfAnswers = editingQuestion.getTypeOfAnswers();
        final Answer answer = new Answer(UUID.randomUUID().toString(), newAnswerText, typeOfAnswers);
        editingQuestion.addAnswer(answer);
        if (typeOfAnswers == RADIO_BUTTON && isBlank(editingQuestion.getCorrectSingleChoiceAnswer())) {
            editingQuestion.setCorrectSingleChoiceAnswer(answer.getId());
        }
    }

    public void onDeleteAnswerPressed() {
        final String answerId = getIdFromRequest();
        Answer answerToDelete = null;
        for (final Answer answer : editingQuestion.getAnswers()) {
            if (answer.getId().equals(answerId)) {
                answerToDelete = answer;
                break;
            }
        }

        notNull(answerToDelete);
        editingQuestion.removeAnswer(answerToDelete);
    }

    //
    // Dependency Injection
    //

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    //
    // Helper Methods
    //

    private String getIdFromRequest() {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final ExternalContext externalContext = facesContext.getExternalContext();
        final Map<String, String> params = externalContext.getRequestParameterMap();

        return params.get("id");
    }

}
