package watcherService.domain.mapper;

import org.mapstruct.Mapper;
import watcherService.domain.dto.CryptoCurrencySavedPriceDto;
import watcherService.domain.model.CryptoCurrency;

@Mapper(componentModel = "spring")
public interface CryptoCurrencyMapper {
    CryptoCurrencySavedPriceDto toCryptoCurrencyActualPriceDto(CryptoCurrency cryptoCurrency);
}
