package com.kokcuemre.springboot.soap.soapwebservices.endpoint;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.baeldung.springsoap.gen.Country;
import com.baeldung.springsoap.gen.GetCountryRequest;
import com.baeldung.springsoap.gen.GetCountryResponse;
import com.kokcuemre.springboot.soap.soapwebservices.domain.CountryEntity;
import com.kokcuemre.springboot.soap.soapwebservices.repository.CountryEntityRepository;

@Endpoint
public class CountryEndpoint {
 
    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

    @Autowired
    private CountryEntityRepository countryEntityRepository;
    
    public CountryEndpoint(CountryEntityRepository countryEntityRepository) {
    	this.countryEntityRepository = countryEntityRepository;
	}
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
    	GetCountryResponse response = new GetCountryResponse();
    	Optional<CountryEntity> countryOption = countryEntityRepository.findByName(request.getName());
    	if(countryOption.isPresent()) {
    		Country country= new Country();
    		country.setName(countryOption.get().getName());
    		response.setCountry(country);
    	}
        return response;
    }
}