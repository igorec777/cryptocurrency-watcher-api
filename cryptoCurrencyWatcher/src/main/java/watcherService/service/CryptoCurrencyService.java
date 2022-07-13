package watcherService.service;

import watcherService.domain.dto.CryptoCurrencyActualPriceDto;
import watcherService.domain.model.CryptoCurrency;


public interface CryptoCurrencyService {

    CryptoCurrency findBySymbol(String symbol);

    CryptoCurrency findByCurrencyId(Integer currencyId);

    void updatePriceData(CryptoCurrencyActualPriceDto.GeneralData generalData);

    void notify(String symbol, String username);
}
