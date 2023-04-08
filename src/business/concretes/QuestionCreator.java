package business.concretes;

import business.abstracts.IQuestionService;
import business.abstracts.IRandomNumber;
import entities.*;
import errors.BusinessException;
import errors.ErrorType;

import java.util.HashMap;
import java.util.Map;

import static business.abstracts.IQuestionGenerator.generateRandomAudio;
import static business.abstracts.IQuestionGenerator.generateRandomString;

public class QuestionCreator implements IQuestionService {

    private enum QuestionTypes {READING, LISTENING, SPEAKING, WORD_MATCHING}

    @Override
    public Question createQuestion() {
        return generateQuestion(QuestionTypes.values()[IRandomNumber.generateRandomNumber(0, 3)]);
    }

    @Override
    public Question createQuestion(String questionType) {
        return generateQuestion(QuestionTypes.valueOf(questionType));
    }

    private Question generateQuestion(QuestionTypes questionType) {
        int MIN_DURATION = 2;
        int MAX_DURATION = 5;

        switch (questionType) {
            case READING -> {
                return new ReadingQuestion(generateRandomString(), generateRandomString());
            }
            case LISTENING -> {
                return new ListeningQuestion(generateRandomString(), generateRandomAudio(MIN_DURATION, MAX_DURATION));
            }
            case SPEAKING -> {
                return new SpeakingQuestion(generateRandomAudio(MIN_DURATION, MAX_DURATION), generateRandomAudio(MIN_DURATION, MAX_DURATION));
            }
            case WORD_MATCHING -> {
                Map<String, String> wordMatching = new HashMap<>();
                wordMatching.put(generateRandomString(), generateRandomString());
                return new WordMatchingQuestion(wordMatching);
            }
            default -> throw new BusinessException(ErrorType.INVALID_QUESTION_TYPE, "Invalid question type!");
        }
    }

}
