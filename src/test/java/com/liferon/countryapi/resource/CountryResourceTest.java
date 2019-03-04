package com.liferon.countryapi.resource;

import com.liferon.countryapi.domain.Country;
import com.liferon.countryapi.service.CountryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CountryResource.class)
public class CountryResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllCountries_Test() throws Exception {

        CountryService countryService = mock(CountryService.class);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/countries").accept(MediaType.APPLICATION_JSON);

        List<Country> countryList = new ArrayList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");

        Country nigeria = new Country();
        nigeria.setId(1L);
        nigeria.setName("Nigeria");
        nigeria.setContinent("Africa");
        nigeria.setCreatedDate(dateFormat.parse("2019-03-01 20:29:03.370+0100"));

        Country ghana = new Country();
        ghana.setId(2L);
        ghana.setName("Ghana");
        ghana.setContinent("Africa");
        ghana.setCreatedDate(dateFormat.parse("2019-03-01 20:31:22.147+0100"));

        countryList.add(nigeria);
        countryList.add(ghana);

        when(countryService.getAllCountries()).thenReturn(countryList);
        String expectedResonse = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Nigeria\",\n" +
                "        \"continent\": \"Africa\",\n" +
                "        \"created\": \"2019-03-01 20:29:03.370+0100\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Ghana\",\n" +
                "        \"continent\": \"Africa\",\n" +
                "        \"created\": \"2019-03-01 20:31:22.147+0100\"\n" +
                "    }\n" +
                "]";

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResonse))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());

        assertEquals(expectedResonse, result.getResponse().toString());

    }

    @Test
    public void getCountry() {
    }

    @Test
    public void addCountry() {
    }

    @Test
    public void updateCountry() {
    }

    @Test
    public void deleteCountry() {
    }
}