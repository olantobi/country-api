package com.liferon.countryapi.resource;

import com.liferon.countryapi.domain.Country;
import com.liferon.countryapi.exception.InternalServerErrorException;
import com.liferon.countryapi.exception.InvalidRequestParameterException;
import com.liferon.countryapi.exception.ResourceNotFoundException;
import com.liferon.countryapi.model.CountryModel;
import com.liferon.countryapi.service.CountryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountryResource {

    private final CountryService countryService;

    @GetMapping
    @ApiOperation(nickname = "/countries", value = "Get all countries")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful")
    })
    public ResponseEntity<?> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();

        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{id}")
    @ApiOperation(nickname = "/countries/id", value = "Get country by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Country does not exist")
    })
    public ResponseEntity<?> getCountry(@PathVariable("id") long id) {
        Optional<Country> countryOption = countryService.getCountry(id);

        if (!countryOption.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country with id "+id+" does not exist");

        return ResponseEntity.ok(countryOption.get());
    }

    @PostMapping
    @ApiOperation(nickname = "/country", value = "Add a country")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid request parameters")
    })
    public ResponseEntity<?> addCountry(@Valid @RequestBody CountryModel country,
                                           BindingResult result, UriComponentsBuilder ucBuilder) throws InvalidRequestParameterException {

        if (result.hasFieldErrors()) {
            String errors = result.getFieldErrors().stream()
                    .map(p -> p.getDefaultMessage()).collect(Collectors.joining("\n"));
            throw new InvalidRequestParameterException("Bad Request", errors);
        }

        Country createdCountry = countryService.addCountry(country);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/countries/{id}").buildAndExpand(createdCountry.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(nickname = "/country/id", value = "Update country by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid request parameters"),
            @ApiResponse(code = 404, message = "Country does not exist")
    })
    public ResponseEntity<?> updateCountry(@PathVariable("id") long id, @Valid @RequestBody CountryModel country,
                       BindingResult result) throws InvalidRequestParameterException, ResourceNotFoundException, InternalServerErrorException {

        if (result.hasFieldErrors()) {
            String errors = result.getFieldErrors().stream()
                    .map(p -> p.getDefaultMessage()).collect(Collectors.joining("\n"));
            throw new InvalidRequestParameterException("Bad Request", errors);
        }

        Optional<Country> countryOption = countryService.getCountry(id);
        if (!countryOption.isPresent())
            throw new ResourceNotFoundException("Country with id "+id+" does not exist");

        country.setId(id);

        boolean countryCreated = countryService.updateCountry(country);
        if (countryCreated)
            return ResponseEntity.ok("Country updated successfully");
        else
            throw new InternalServerErrorException("An error has occurred", "Unable to update country");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(nickname = "/countries/id", value = "Delete country by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Country deleted successfully"),
            @ApiResponse(code = 404, message = "Country does not exist")
    })
    public ResponseEntity<?> deleteCountry(@PathVariable("id") long id) throws ResourceNotFoundException, InternalServerErrorException {
        Optional<Country> countryOption = countryService.getCountry(id);

        if (!countryOption.isPresent())
            throw new ResourceNotFoundException("Country with id "+id+" does not exist");

        boolean countryDeleted = countryService.deleteCountry(id);
        if (countryDeleted)
            return ResponseEntity.ok(countryOption.get());
        else
            throw new InternalServerErrorException("An error has occurred", "Unable to delete country");
    }
}
