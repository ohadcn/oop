/**
* An additional JS file which should be handed in together with your exercise.
* OOP ex4
*/

/*
* The function finds the HTML element whose id is elementId, and sets its 
% value to be newValue.
*/
function setValue(elementId, newValue) {
	var element =  document.getElementById(elementId);
	if (element != null) {
		element.value = newValue;
		return true;
	}
	else {
		return false;
	}
}

/*
* The function sets the drop down list that matches elementId with the given 
* newValue. Returns true iff the assigment was perforemd successfully. 
*/
function setListValue(elementId, newValue) {

	var element =  document.getElementById(elementId);
	if (element == null) {
		return false;
	}
	
	var elemOptions  = element.options;
	for (var i = 0; i < elemOptions.length; i++) {
  		if (elemOptions[i].value == newValue) {
			element.value = newValue;
			return true;
		}
	}
	
	return false;
}

function setRadioValue(elementName, newValue) {
	var elementCollection = document.getElementsByName(elementName);
	if (elementCollection == null) {
		return false;
	}
	
	for (var i = 0; i < elementCollection.length; i++) {
		if(elementCollection[i].value == newValue) {
			elementCollection[i].checked = true;
			return true;
		} 
	}
	return false;
}

/*
* Should be called as a responst to clicking on "setExampleParameters" button.
*/
function setExampleParameters() {
	
	// set query sequence
	if(! setValue('inputSeq', 'AGCTTTTGGGGACACAGTAGTTGACTGGTATATCTCCTGTAAAAACTAGCTTGTATATACT') ) {
		return false;
	}
	
	// set junction position
	if(! setValue('junctionPos', '27') ) {
		return false;
	}
	
	// set enhancers list
	setValue('enhancerList', 'AGCT,GACA,taaaaac,atgtcctcta');
	
	// set silencers list
	setValue('silencerList', 'CTTTTG,GACA,TATATACT');
		
	// set enhancers score 
	if(! setValue('enhancerScore', '5') ) {
		return false;
	}
			
	// set silencers score 
	if(! setValue('silencerScore', '-7') ) {
		return false;
	}
		
	// set enhancers color to be red
	if(! setListValue('enhancersColor', 'red')) { // valid options are: green, blue, red
		return false;
	}	
	
	// set silencers color to be yellow
	if(! setListValue('silencersColor', 'yellow')) { // valid options are: violet, yellow, brown
		return false;
	}	

	// set  "Sequences to highlight" option to be "both"
	if(! setRadioValue('mark', 'both')){ // valid options (value field) are: enhancers, silencers, both
		return false;
	}
	
	// set check box to be checked
	var element =  document.getElementById('reverse');
	
	if (element == null) {
		return false;
	} else{ 
		element.checked = true;
	}
	
	// set message
	if(! setValue('errors', 'Input assignment was performed successfully!') ) {
		return false;
	}
	
	return true;
}
	