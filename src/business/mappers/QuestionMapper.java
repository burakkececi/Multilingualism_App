package business.mappers;

import business.abstracts.IQuestionGenerator;
import business.abstracts.IRandomNumber;
import business.concretes.QuestionCreator;
import entities.*;
import errors.BusinessException;
import errors.ErrorType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static business.abstracts.IQuestionGenerator.generateRandomAudio;
import static business.abstracts.IQuestionGenerator.generateRandomString;

public class QuestionMapper implements CSVMapper {

    private final int index;

    private enum QuestionTypes {R, L, S, W}

    private List<Question> questionList;

    public QuestionMapper(int index) {
        this.index = index;
        this.questionList = new ArrayList<>();
    }

    private Question createQuestion(QuestionTypes questionType) {
        int MIN_DURATION = 2;
        int MAX_DURATION = 5;

        switch (questionType) {
            case R -> {
                return new ReadingQuestion(generateRandomString(), generateRandomString());
            }
            case L -> {
                return new ListeningQuestion(generateRandomString(), generateRandomAudio(MIN_DURATION, MAX_DURATION));
            }
            case S -> {
                return new SpeakingQuestion(generateRandomAudio(MIN_DURATION, MAX_DURATION), generateRandomAudio(MIN_DURATION, MAX_DURATION));
            }
            case W -> {
                Map<String, String> wordMatching = new HashMap<>();
                wordMatching.put(generateRandomString(), generateRandomString());
                return new WordMatchingQuestion(wordMatching);
            }
            default -> throw new BusinessException(ErrorType.INVALID_QUESTION_TYPE, "Invalid question type!");
        }
    }

    @Override
    public List<Question> map(String[] data) {
        String[] questions = data[index].strip().split(":");

        for (String token : questions) {
            for (QuestionTypes questionType : QuestionTypes.values()) {
                if (token.endsWith(questionType.name())) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(token.charAt(0))); i++) {
                        questionList.add(
                                createQuestion(questionType));
                    }
                }
            }
        }
        return questionList;
    }
}
