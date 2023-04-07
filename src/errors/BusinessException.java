package errors;

public class BusinessException extends RuntimeException {

    private final ErrorType errorType;
    private final String message;

    public BusinessException(ErrorType errorType, String message) {
        this.errorType = errorType;
        this.message = message;
    }


    public String getErrorType() {
        return errorType.name();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
