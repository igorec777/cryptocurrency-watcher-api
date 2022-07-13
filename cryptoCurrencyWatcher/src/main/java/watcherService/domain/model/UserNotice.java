package watcherService.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "USER_NOTICE")
@Getter
@Setter
@NoArgsConstructor
public class UserNotice extends CryptoCurrencyPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "CRYPTO_CURRENCY_ID")
    private CryptoCurrency cryptoCurrency;

    @Builder
    public UserNotice(String username, BigDecimal priceUSD, CryptoCurrency cryptoCurrency) {
        super(priceUSD);
        this.username = username;
        this.cryptoCurrency = cryptoCurrency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNotice that = (UserNotice) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username)
                && Objects.equals(cryptoCurrency, that.cryptoCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, cryptoCurrency);
    }
}
