package tk.jviewer.dialog;

import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.EditQuizManagedBean;
import tk.jviewer.model.Question;
import tk.jviewer.model.Quiz;
import tk.jviewer.service.QuizService;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static javax.faces.context.FacesContext.getCurrentInstance;
import static org.springframework.util.Assert.hasText;
import static tk.jviewer.model.AnswerType.RADIO_BUTTON;

/**
 * Serves "edit quiz" use case.
 */
public class EditQuizDialog implements Serializable {

    private static final long serialVersionUID = -2334735949960107078L;

    private QuizService quizService;
    private EditQuizManagedBean editQuizManagedBean;

    private boolean quizJustCreated = false;

    private String newQuestionText;
    private String newAnswerText;

    @PostConstruct
    public void init() {
        if (getQuiz() == null) {
            editQuizManagedBean.setCurrentQuiz(quizService.createQuiz());
            quizJustCreated = true;
        }
        editQuizManagedBean.setEditingQuestion(getQuiz().getCurrentQuestion());
    }

    public Quiz getQuiz() {
        return editQuizManagedBean.getCurrentQuiz();
    }

    public Question getEditingQuestion() {
        return editQuizManagedBean.getEditingQuestion();
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

    public boolean isQuizJustCreated() {
        return quizJustCreated;
    }

    public void onEditingQuestionChanged() {
        final int id = getIdFromRequest();
        final Question question = lookupQuestionById(getQuiz().getQuestions(), id);
        editQuizManagedBean.setEditingQuestion(question);
    }

    public void onAddNewQuestionPressed() {
        hasText(newQuestionText);
        final Question question = quizService.createQuestion(getQuiz(), newQuestionText);
        editQuizManagedBean.setEditingQuestion(question);
    }

    public void onDeleteQuestionPressed() {
        final Quiz quiz = getQuiz();
        final List<Question> questions = quiz.getQuestions();
        final Question questionToRemove = lookupQuestionById(questions, getIdFromRequest());
        quizService.removeQuestion(quiz, questionToRemove);
        if (questionToRemove.equals(editQuizManagedBean.getEditingQuestion())) {
            editQuizManagedBean.setEditingQuestion(questions.isEmpty() ? null : questions.get(questions.size() - 1));
        }
    }

    public void onAddNewAnswerPressed() {
        hasText(newAnswerText);
        final Answer answer = new Answer(newAnswerText);
        final Question editingQuestion = editQuizManagedBean.getEditingQuestion();
        quizService.createAnswer(editingQuestion, answer);
        final AnswerType typeOfAnswers = editingQuestion.getTypeOfAnswers();
        if (typeOfAnswers == RADIO_BUTTON && editingQuestion.getCorrectSingleChoiceAnswer() == null) {
            editingQuestion.setCorrectSingleChoiceAnswer(answer.getId());
            updateEditingQuestion();
        }
    }

    public void onDeleteAnswerPressed() {
        final int answerId = getIdFromRequest();
        quizService.removeAnswer(editQuizManagedBean.getEditingQuestion(), lookupAnswerById(getEditingQuestion().getAnswers(), answerId));
    }

    public void onCorrectAnswerChanged() {
        updateEditingQuestion();
    }

    public void onQuizChanged() {
        quizService.updateQuiz(getQuiz());
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

    public void setEditQuizManagedBean(EditQuizManagedBean editQuizManagedBean) {
        this.editQuizManagedBean = editQuizManagedBean;
    }

    //
    // Helper Methods
    //

    private int getIdFromRequest() {
        final FacesContext facesContext = getCurrentInstance();
        final ExternalContext externalContext = facesContext.getExternalContext();
        final Map<String, String> params = externalContext.getRequestParameterMap();

        return parseInt(params.get("id"));
    }

    private void updateEditingQuestion() {
        quizService.updateQuestion(editQuizManagedBean.getEditingQuestion());
    }

    private static Question lookupQuestionById(final List<Question> questions, final Integer id) {
        for (final Question question : questions) {
            if (question.getId().equals(id)) {
                return question;
            }
        }

        throw new RuntimeException("No question with id " + id + " found");
    }

    private static Answer lookupAnswerById(final List<Answer> answers, final Integer id) {
        for (final Answer answer : answers) {
            if (answer.getId().equals(id)) {
                return answer;
            }
        }

        throw new RuntimeException("No answer with id " + id + " found");
    }

}
