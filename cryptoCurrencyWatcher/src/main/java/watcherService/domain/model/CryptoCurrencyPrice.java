package watcherService.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCurrencyPrice {
    @Column(name = "PRICE_USD")
    protected BigDecimal priceUSD;
}
