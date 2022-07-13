package watcherService.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
public class CryptoCurrencyInfoDto {

    List<Data> data;

    @Getter
    @Setter
    private static class Data {
        private Integer id;
        private String symbol;
        private String name;
        private String nameid;
        private Integer rank;
        private BigDecimal price_usd;
        private double percent_change_24h;
        private double percent_change_1h;
        private double percent_change_7d;
        private BigDecimal price_btc;
        private BigDecimal market_cap_usd;
        private BigDecimal volume24;
        private BigDecimal volume24a;
        private String csupply;
        private String tsupply;
        private String msupply;
    }
}
