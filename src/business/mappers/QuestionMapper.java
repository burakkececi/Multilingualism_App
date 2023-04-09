package business.mappers;

import business.abstracts.IQuestionService;
import business.concretes.QuestionCreator;
import entities.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionMapper implements CSVMapper {

    private enum QuestionTypes {READING, LISTENING, SPEAKING, WORD_MATCHING}

    private final int index;
    private final List<Question> questionList;

    public QuestionMapper(int index) {
        this.index = index;
        this.questionList = new ArrayList<>();
    }

    private Question createQuestion(QuestionTypes questionType) {
        IQuestionService iQuestionService = new QuestionCreator();
        return iQuestionService.createQuestion(questionType.name());
    }

    private void addQuestion(Question question) {
        questionList.add(question);
    }

    private List<Question> getQuestionList() {
        return questionList;
    }

    @Override
    public List<Question> map(String[] data) {
        String[] questions = data[index].strip().split(":");

        for (String token : questions) {
            for (QuestionTypes questionType : QuestionTypes.values()) {
                if (token.endsWith(questionType.name().substring(0, 1))) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(token.charAt(0))); i++) {
                        addQuestion(createQuestion(questionType));
                    }
                }
            }
        }
        return getQuestionList();
    }
}
