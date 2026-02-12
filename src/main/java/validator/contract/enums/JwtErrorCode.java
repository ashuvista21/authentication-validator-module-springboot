package validator.contract.enums;

public enum JwtErrorCode {
    INVALID_SIGNATURE(401),
    EXPIRED(401),
    NOT_YET_VALID(401),
    MALFORMED(400);

    private final int httpCode ;

    JwtErrorCode(int httpCode) {
        this.httpCode = httpCode ;
    }

    public int getHttpCode() {
        return httpCode ;
    }
}
