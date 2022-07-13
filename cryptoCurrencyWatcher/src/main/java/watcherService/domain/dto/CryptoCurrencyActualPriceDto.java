package watcherService.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
public class CryptoCurrencyActualPriceDto {

    List<GeneralData> data;

    @Getter
    @Setter
    public static class GeneralData {
        private Integer id;
        private String symbol;
        private BigDecimal price_usd;
    }
}
