package echo.exam.exam.Util.Handler;

public class ErrorResponse {
    private String message;

    /**
     * ErrorResponse 생성자
     * @param message 에러 메시지
     */
    public ErrorResponse(String message) {
        this.message = message;
    }

    /**
     * 에러 메시지 반환
     * @return 에러 메시지
     */
    public String getMessage() {
        return message;
    }

    /**
     * 에러 메시지 설정
     * @param message 에러 메시지
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
