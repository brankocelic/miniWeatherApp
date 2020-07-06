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
	
	manipulateImage("low_clouds_departure" , "low_clouds_departure_img");
	manipulateImage("medium_cluds_departure" , "medium_cluds_departure_img");
	manipulateImage("high_clouds_departure" , "high_clouds_departure_img");
	
}

function checkPositionOfImagesDestination() {
	
	manipulateImage("low_clouds_destination" , "low_clouds_destination_img");
	manipulateImage("medium_clouds_destination" , "medium_cluds_destination_img");
	manipulateImage("high_clouds_destination" , "high_clouds_destination_img");

}

function manipulateImage(pecrentValue , idImage)
{
	var cloudPercent = document.getElementById(pecrentValue).textContent;
	var coudImage = document.getElementById(idImage);
	
	if (Math.floor(cloudPercent.substring(0,cloudPercent.length - 1)) > 20 && Math.floor(cloudPercent.substring(0,cloudPercent.length - 1)) < 50)
		coudImage.style.opacity = "0.6";
	else if (Math.floor(cloudPercent.substring(0,cloudPercent.length - 1)) >= 50)
		coudImage.style.opacity = "1";
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


