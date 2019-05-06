$(document).ready(function() {
    
    $("#quoteForm").submit(function (e) {
	e.preventDefault();
	var validVehicles = ["bicycle", "motorbike", "parcel_car", "small_van", "large_van"];
	if(validVehicles.indexOf(('#vehicleField').val()) == -1) {
	    $('#errorMsg').html("Please choose a valid vehicle.");
	    return false;
	}
	
	var addFriend = $.ajax({
	    type: 'POST',
	    url: "/index",
	    th:action="@{/quote}",
	    data: { pickup_postcode: $('pickup_postcode').val(),
		    delivery_postcode: $('delivery_postcode').val(),
		    vehicle: $('vehicle').val() },
	    dataType: "text",
	    success: function(resultData) {
		var result = JSON.parse(resultData);
		$('queryResults').html("Cost: " + result.price);
	    }
	});
	return false;
    });

});