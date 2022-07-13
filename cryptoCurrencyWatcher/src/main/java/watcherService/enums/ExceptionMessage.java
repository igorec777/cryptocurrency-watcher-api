package watcherService.enums;

import lombok.Getter;


public enum ExceptionMessage {

    CRYPTO_CURRENCY_WITH_SYMBOL_NOT_FOUND("Cryptocurrency with symbol '%s' not found"),

    CRYPTO_CURRENCY_WITH_CURRENCY_ID_NOT_FOUND("Cryptocurrency with currencyId '%s' not found"),

    USER_NOTICE_ALREADY_REGISTERED("User '%s' already registered cryptocurrency '%s'");

    @Getter
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
