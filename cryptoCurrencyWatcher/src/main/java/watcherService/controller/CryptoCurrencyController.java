package watcherService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import watcherService.domain.dto.CryptoCurrencySavedPriceDto;
import watcherService.domain.dto.CryptoCurrencyInfoDto;


@Tag(name = "Crypto currency controller", description = "Crypto currency watcher API")
public interface CryptoCurrencyController {

    @Operation(summary = "Get crypto currencies list", description = "Allows to get info about crypto currencies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get info about crypto currencies",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "522", description = "Connection timed out")})
    CryptoCurrencyInfoDto getCryptoCurrenciesList();

    @Operation(summary = "Update price of crypto currency",
            description = "Each 1 minute update price of crypto currency")
    void updatePriceOfCryptoCurrency();

    @Operation(summary = "Register user to currency",
            description = "Register user to a certain currency by username and symbol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get info about crypto currencies"),
            @ApiResponse(responseCode = "522", description = "Connection timed out"),
            @ApiResponse(responseCode = "400", description = "Request param 'username' or 'symbol' is wrong")})
    void notify(String symbol, String username);

    @Operation(summary = "Get actual crypto currency price",
            description = "Allows to get actual price of certain crypto currency")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get info about certain crypto currency",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Wrong path variable 'currencyId'"),
            @ApiResponse(responseCode = "522", description = "Connection timed out")})
    CryptoCurrencySavedPriceDto getActualPriceOfCertainCryptoCurrency(Integer currencyId);
}
