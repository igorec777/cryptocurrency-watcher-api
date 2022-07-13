package watcherService.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class CryptoCurrencySavedPriceDto {
    private BigDecimal priceUSD;
}
