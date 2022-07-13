package watcherService.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import watcherService.domain.dto.CryptoCurrencyActualPriceDto;
import watcherService.domain.model.CryptoCurrency;
import watcherService.domain.model.UserNotice;
import watcherService.exception.CryptoCurrencyNotFoundException;
import watcherService.exception.UserNoticeAlreadyExistException;
import watcherService.repository.CryptoCurrencyRepository;
import watcherService.repository.UserNoticeRepository;
import watcherService.service.CryptoCurrencyService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final UserNoticeRepository userNoticeRepository;

    private static final Double PERCENT_DIFFERENCE = 1.0;
    private static final Integer PRICE_SCALE = 5;
    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Override
    public CryptoCurrency findBySymbol(String symbol) {
        return cryptoCurrencyRepository.findBySymbol(symbol)
                .orElseThrow(() -> new CryptoCurrencyNotFoundException(symbol));
    }

    @Override
    public CryptoCurrency findByCurrencyId(Integer currencyId) {
        return cryptoCurrencyRepository.findByCurrencyId(currencyId)
                .orElseThrow(() -> new CryptoCurrencyNotFoundException(currencyId));
    }

    @Override
    public void updatePriceData(CryptoCurrencyActualPriceDto.GeneralData generalData) {
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        try {
            cryptoCurrency = findBySymbol(generalData.getSymbol());
            cryptoCurrency.setPriceUSD(generalData.getPrice_usd());
        } catch (CryptoCurrencyNotFoundException ex) {
            cryptoCurrency = CryptoCurrency.builder()
                    .symbol(generalData.getSymbol())
                    .currencyId(generalData.getId())
                    .priceUSD(generalData.getPrice_usd())
                    .build();
        } finally {
            cryptoCurrency = cryptoCurrencyRepository.save(cryptoCurrency);
            tryLogPriceDifference(cryptoCurrency);
        }

    }

    @Override
    public void notify(String symbol, String username) {

        CryptoCurrency cryptoCurrency = findBySymbol(symbol);
        UserNotice userNotice = userNoticeRepository.findByUsernameAndSymbol(username, symbol);

        if (userNotice != null) {
            throw new UserNoticeAlreadyExistException(username, symbol);
        }
        userNoticeRepository.save(UserNotice.builder()
                .username(username)
                .priceUSD(cryptoCurrency.getPriceUSD())
                .cryptoCurrency(cryptoCurrency)
                .build());
    }

    private void tryLogPriceDifference(CryptoCurrency cryptoCurrency) {
        List<UserNotice> userNotices = userNoticeRepository.findByCryptoCurrencyId(cryptoCurrency.getId());
        userNotices.forEach(userNotice -> {
            Double percentDifferenceUSD =
                    getPercentOfPriceDifference(userNotice.getPriceUSD(), cryptoCurrency.getPriceUSD());
            if (Math.abs(percentDifferenceUSD) > PERCENT_DIFFERENCE) {
                log.warn(String.format("Message for %s. Price of %s changed by %f percents",
                    userNotice.getUsername(), userNotice.getCryptoCurrency().getSymbol(),
                    percentDifferenceUSD));
            }
        });
    }

    private Double getPercentOfPriceDifference(BigDecimal priceSource, BigDecimal priceTarget) {
        return priceTarget
                .divide(priceSource, PRICE_SCALE, RoundingMode.HALF_UP)
                .multiply(ONE_HUNDRED)
                .subtract(ONE_HUNDRED).doubleValue();
    }
}
