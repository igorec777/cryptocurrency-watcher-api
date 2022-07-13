package watcherService.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import watcherService.controller.CryptoCurrencyController;
import watcherService.domain.dto.CryptoCurrencyActualPriceDto;
import watcherService.domain.dto.CryptoCurrencySavedPriceDto;
import watcherService.domain.dto.CryptoCurrencyInfoDto;
import watcherService.domain.mapper.CryptoCurrencyMapper;
import watcherService.domain.model.CryptoCurrency;
import watcherService.service.CryptoCurrencyService;

import java.util.concurrent.TimeUnit;


@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto-api")
public class CryptoCurrencyControllerImpl implements CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;
    private final CryptoCurrencyMapper cryptoCurrencyMapper;

    @Value("${crypto-api.url.all-tickers}")
    private String allTickersUrl;

    @Override
    @GetMapping("/currencies")
    public CryptoCurrencyInfoDto getCryptoCurrenciesList() {
        WebClient webClient = WebClient.create(allTickersUrl);
        return webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CryptoCurrencyInfoDto.class)
                .share()
                .block();
    }

    @Scheduled(fixedRate = 1L, timeUnit = TimeUnit.MINUTES)
    public void updatePriceOfCryptoCurrency() {
        WebClient webClient = WebClient.create(allTickersUrl);
        CryptoCurrencyActualPriceDto actualPriceDto = webClient
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CryptoCurrencyActualPriceDto.class)
                .share()
                .block();

        actualPriceDto
            .getData()
            .parallelStream()
            .forEach(cryptoCurrencyService::updatePriceData);
    }

    @Override
    @PutMapping("/notify")
    public void notify(@RequestParam("username") String username, @RequestParam("symbol") String symbol) {
        cryptoCurrencyService.notify(symbol, username);
    }

    @Override
    @GetMapping("/currency/{currencyId}")
    public CryptoCurrencySavedPriceDto getActualPriceOfCertainCryptoCurrency(
            @PathVariable("currencyId") Integer currencyId) {

        CryptoCurrency cryptoCurrency = cryptoCurrencyService.findByCurrencyId(currencyId);
        return cryptoCurrencyMapper.toCryptoCurrencyActualPriceDto(cryptoCurrency);
    }
}
