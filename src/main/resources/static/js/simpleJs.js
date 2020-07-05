var element = document.getElementById("search");
var lat , long;
function getLocation() {
		if (navigator.geolocation){
			navigator.geolocation.getCurrentPosition(showPosition);
			}
		else
			element.innerHTML = "Geolocation is not supported by this browser.";
}


function showPosition(position) {	
	lat=position.coords.latitude;
	long = position.coords.longitude;
}

function getCity(){
	if (element.value != "") 
		loadJSON('https://api.opencagedata.com/geocode/v1/geojson?q='+lat+'+'+long+'&key=409a78640d1a47edaef255d3ed789e95' , getData);
	else 
	{
		 element.style.border = "2px solid red";
		 element.placeholder = "Please enter an adress!";
	}
}


function getData(data)
{
	var city = data.features[0].properties.components.city;
	
	window.location.href = "/processLocation?search="
	+ document.getElementById("search").value + "&curretnGeolocation="
		 + lat + " " +
		 long+"&currentCity="+city;
}
           
function activatePlacesSearch() {
	var autocomplete = new google.maps.places.Autocomplete(element);
}

function checkPositionOfImagesDeparture() {
	var low_clouds_departure = document.getElementById("low_clouds_departure").textContent;
	var low_clouds_departure_img = document.getElementById("low_clouds_departure_img");
	
	if (Math.floor(low_clouds_departure.substring(0,low_clouds_departure.length - 1)) > 20 && Math.floor(low_clouds_departure.substring(0,low_clouds_departure.length - 1)) < 50)
		low_clouds_departure_img.style.opacity = "0.6";
	else if (Math.floor(low_clouds_departure.substring(0,low_clouds_departure.length - 1)) >= 50)
		low_clouds_departure_img.style.opacity = "1";

	var medium_cluds_departure = document.getElementById("medium_cluds_departure").textContent;
	var medium_cluds_departure_img = document.getElementById("medium_cluds_departure_img");
	
	if (Math.floor(medium_cluds_departure.substring(0,medium_cluds_departure.length - 1)) > 20 && Math.floor(medium_cluds_departure.substring(0,medium_cluds_departure.length - 1)) < 50)
		medium_cluds_departure_img.style.opacity = "0.6";
	else if (Math.floor(medium_cluds_departure.substring(0,medium_cluds_departure.length - 1)) >= 50)
		medium_cluds_departure_img.style.opacity = "1";

	var high_clouds_departure = document.getElementById("high_clouds_departure").textContent;
	var high_clouds_departure_img = document.getElementById("high_clouds_departure_img");
	if (Math.floor(high_clouds_departure.substring(0,high_clouds_departure.length - 1)) > 20 && Math.floor(high_clouds_departure.substring(0,high_clouds_departure.length - 1)) < 50)
		high_clouds_departure_img.style.opacity = "0.6";
	else if (Math.floor(high_clouds_departure.substring(0,high_clouds_departure.length - 1)) >= 50)
		high_clouds_departure_img.style.opacity = "1";
}

function checkPositionOfImagesDestination() {
	var low_clouds_destination = document.getElementById("low_clouds_destination").textContent;
	var low_clouds_destination_img = document.getElementById("low_clouds_destination_img");
	
	if (Math.floor(low_clouds_destination.substring(0,low_clouds_destination.length - 1)) > 20 && Math.floor(low_clouds_destination.substring(0,low_clouds_destination.length - 1)) < 50)
		low_clouds_destination_img.style.opacity = "0.6";
	else if (Math.floor(low_clouds_destination.substring(0,low_clouds_destination.length - 1)) >= 50)
		low_clouds_destination_img.style.opacity = "1";

	var medium_cluds_destination = document.getElementById("medium_clouds_destination").textContent;
	var medium_cluds_destination_img = document.getElementById("medium_cluds_destination_img");
	if (Math.floor(medium_cluds_destination.substring(0,medium_cluds_destination.length - 1)) > 20 && Math.floor(medium_cluds_destination.substring(0,medium_cluds_destination.length - 1)) < 50)
		medium_cluds_destination_img.style.opacity = "0.6";
	else if (Math.floor(medium_cluds_destination.substring(0,medium_cluds_destination.length - 1)) >= 50)
		medium_cluds_destination_img.style.opacity = "1";

	var high_clouds_destination = document.getElementById("high_clouds_destination").textContent;
	var high_clouds_destination_img = document.getElementById("high_clouds_destination_img");
	
	if (Math.floor(high_clouds_destination.substring(0,high_clouds_destination.length - 1)) > 20 && Math.floor(high_clouds_destination.substring(0,high_clouds_destination.length - 1)) < 50)
		high_clouds_destination_img.style.opacity = "0.6";
	else if (Math.floor(high_clouds_destination.substring(0,high_clouds_destination.length - 1)) >= 50)
		high_clouds_destination_img.style.opacity = "1";
}

function checkingCorrectSearch(){
	var input_field_departure = document.getElementById("input_field_departure").value;
	var input_field_destination = document.getElementById("input_field_destination");
	
	
	if(input_field_departure && !input_field_destination.value){
		 element.style.border = "2px solid red";
		 element.placeholder = "Please enter an valid adress!";
		 
		 input_field_destination.value = "Invalid search!";
	}
}

function setup()
{
	getLocation();
	checkPositionOfImagesDeparture();
	checkPositionOfImagesDestination();
	checkingCorrectSearch();
	
	document.querySelector('#search').addEventListener('keypress', function (e) {
	    if (e.key === 'Enter') {
	    	getCity();
	    }
	});
}


