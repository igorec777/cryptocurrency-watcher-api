package watcherService.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "CRYPTO_CURRENCY")
@Getter
@Setter
@NoArgsConstructor
public class CryptoCurrency extends CryptoCurrencyPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CURRENCY_ID")
    private Integer currencyId;

    @Column(name = "SYMBOL")
    private String symbol;

    @Builder
    public CryptoCurrency(String symbol, Integer currencyId, BigDecimal priceUSD) {
        super(priceUSD);
        this.symbol = symbol;
        this.currencyId = currencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoCurrency that = (CryptoCurrency) o;
        return Objects.equals(id, that.id) && Objects.equals(currencyId, that.currencyId)
                && Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyId, symbol);
    }
}
