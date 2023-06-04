package echo.exam.exam.Util.Exception;

public class NotFoundException extends RuntimeException{
    /**
     * NotFoundException 생성자
     * @param message 예외 메시지
     */
    public NotFoundException(String message) {
        super(message);
    }
}
