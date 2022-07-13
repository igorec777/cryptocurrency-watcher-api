package watcherService.exception;

import watcherService.enums.ExceptionMessage;


public class CryptoCurrencyNotFoundException extends RuntimeException {

    public CryptoCurrencyNotFoundException(String symbol) {
        super(String.format(ExceptionMessage.CRYPTO_CURRENCY_WITH_SYMBOL_NOT_FOUND.getMessage(), symbol));
    }
    public CryptoCurrencyNotFoundException(Integer currencyId) {
        super(String.format(ExceptionMessage.CRYPTO_CURRENCY_WITH_CURRENCY_ID_NOT_FOUND.getMessage(), currencyId));
    }
}
