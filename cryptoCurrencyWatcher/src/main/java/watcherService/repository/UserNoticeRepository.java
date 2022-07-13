package watcherService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import watcherService.domain.model.UserNotice;

import java.util.List;


public interface UserNoticeRepository extends JpaRepository<UserNotice, Long> {

    @Query("SELECT u FROM UserNotice u JOIN CryptoCurrency c ON u.cryptoCurrency.id = c.id " +
            "WHERE c.symbol = :symbol AND u.username = :username")
    UserNotice findByUsernameAndSymbol(@Param("username") String username, @Param("symbol") String symbol);

    List<UserNotice> findByCryptoCurrencyId(Long cryptoCurrencyId);
}
