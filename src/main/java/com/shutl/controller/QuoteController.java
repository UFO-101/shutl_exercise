package com.shutl.controller;

import com.shutl.model.Quote;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class QuoteController {

    @RequestMapping(value = "/quote", method = POST)
    public @ResponseBody Quote quote(@RequestBody Quote quote) {
        Long price = Math.abs((Long.valueOf(quote.getDeliveryPostcode(), 36) - Long.valueOf(quote.getPickupPostcode(), 36))/100000000);
        String vehicle = quote.getVehicle();
        switch(vehicle) {
	    case "bicycle":
		price = price + ((10*price)/100);
		break;
	    case "motorbike":
		price = price + ((15*price)/100);
		break;
	    case "parcel_car":
		price = price + ((20*price)/100);
		break;
	    case "small_van":
		price = price + ((30*price)/100);
		break;
	    case "large_van":
		price = price + ((40*price)/100);
		break;
	    default:
		throw new IllegalArgumentException();
        }
        
        return new Quote(quote.getPickupPostcode(), quote.getDeliveryPostcode(), vehicle, price);
    }
}

