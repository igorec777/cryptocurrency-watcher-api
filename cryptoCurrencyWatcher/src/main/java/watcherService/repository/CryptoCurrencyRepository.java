package watcherService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import watcherService.domain.model.CryptoCurrency;

import java.util.Optional;


public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {

    Optional<CryptoCurrency> findBySymbol(String symbol);

    Optional<CryptoCurrency> findByCurrencyId(Integer currencyId);
}
