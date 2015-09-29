package tk.jviewer.dialog;

import tk.jviewer.model.Question;
import tk.jviewer.model.Test;
import tk.jviewer.service.QuizService;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

import static java.lang.Long.parseLong;

/**
 * Serves "create quiz" use case.
 */
public class CreateQuizDialog implements Serializable {

    private static final long serialVersionUID = -2334735949960107078L;

    private QuizService quizService;

    private Test quiz;

    private Question editingQuestion;

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

    public String cancelQuizCreation() {
        return "main?faces-redirect=true";
    }

    public void onQuestionSelected() {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final ExternalContext externalContext = facesContext.getExternalContext();
        final Map<String, String> params = externalContext.getRequestParameterMap();
        final String id = params.get("id");
        editingQuestion = quizService.getQuestion(parseLong(id));
    }

    //
    // Dependency Injection
    //

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

}
