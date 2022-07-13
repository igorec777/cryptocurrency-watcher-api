package watcherService.exception;

import watcherService.enums.ExceptionMessage;


public class UserNoticeAlreadyExistException extends RuntimeException {

    public UserNoticeAlreadyExistException(String username, String symbol) {
        super(String.format(ExceptionMessage.USER_NOTICE_ALREADY_REGISTERED.getMessage(), username, symbol));
    }
}
