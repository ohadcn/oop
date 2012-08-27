/**
 * this javascript file contain functions for checking the legality of the input
 * author:ohad cohen, ohadcn (at) cs.huji.ac.il
 * created for ex4 in OOP course
 */
 
 //holds all the error messages and input validation functions
	const elementsErrors={
	"inputSeq":{"empty":"You must enter a sequence!","valFunc":isDNASeq},
	"junctionPos":{"empty":"You should enter a junction position","valFunc":placeValidator("inputSeq",
						"Junction position may contain only valid numbers","Junction position should be in range 1..")},
						
	"enhancerList":{"empty":"You should enter enhancer(s)",
						"valFunc":groupsValidator("Invalid enhancer sequence. You entered: ")},
						
	"silencerList":{"empty":"You should enter silencer(s)",
						"valFunc":groupsValidator("Invalid silencer sequence. You entered: ")},
						
	"enhancerScore":{"empty":"You should enter an enhancer score",
						"valFunc":matcher([[/^-?[\d]+(\.[\d]+)?$/,"The enhancer score should contain a valid number"],
						[/^[\d]+(\.[\d]+)?$/,"The enhancer score should be positive or 0"]])},
						
	"silencerScore":{"empty":"You should enter a silencer score",
						"valFunc":matcher([[/^-?[\d]+(\.[\d]+)?$/,"The silencer score should contain a valid number"],
						[/^((-[\d]+(\.[\d]+)?)|0)$/,"The silencer score should be negative or 0"]])}};
 
 //holds the message to show on success
 	const successMessage="Validation completed successfully!";

/**
 * validate the legality of the input, and set the relevant error message
 * @return {Boolean}true if the input is legal, and not otherwise
 */
function validateInput(){

		for(element in elementsErrors){
			if(isEmpty(element)){
				printError(elementsErrors[element]["empty"]);
				return false;
			};
		}


		for(element in elementsErrors){
			var err=elementsErrors[element]["valFunc"](document.getElementById(element).value);
			if(err){
				printError(err);
				return false;
			};
		}
		
		printError(successMessage);
		return true;
}

/**
 * set the message displayed in the error section
 * @param {String} message the message to display in the error section
 */
function printError(message){
	document.getElementById("out").hide();
	document.getElementById("errorsSection").show();
	document.getElementById("errors").value=message;
}

/**
 * returns true if the given textarea or input[type text] is empty or contains only white spaces
 * @param {String} id the id of the element to test
 * @return {Boolean} true if the given textarea or input[type text] is empty or contains only white spaces
 */
function isEmpty(id){
	return val=document.getElementById(id).value.match(/^ *$/);
}

/**
 * returns true if the given String is a legal DNA sequence
 * @param {String} str the String to text
 * @returns {String} an error message to display if the given String is not a legal DNA sequence
 * null if it's a legal sequence
 */
function isDNASeq(str){
	if(!str.match(/^[acgt]+$/i))
		return "Invalid input sequence - must contain only a,g,c or t";
}

/**
 * returns the first illegal DNA sequence in an array of String, null if all the string contain legal sequences
 * @param {Array} gruop an array of hopefully DNA sequences
 * @returns {String} the first non-DNA sequence in the array, null if all the Strings in the array are legal sequences
 */
function isDNAGroup(gruop){
	var sequences=gruop.toLowerCase().split(",");
	for(seq in sequences)
		if(isDNASeq(sequences[seq]))
			return sequences[seq];
}

/**
 * returns a function that tests arrays of string to be a valid DNA sequence
 * @param {String} errStr the error string to display if a sequence is not a legal DNA sequence
 * @returns {Function} a function that tests arrays of string to be a valid DNA sequence
 */
function groupsValidator(errStr){
	function validate(group){
		var seq=isDNAGroup(group);
		if(seq)
			return errStr+seq;
	}
	return validate;
}

/**
 * returns a function that test a (hopefully) number to be in a range of the size of another string
 * @param {String} object the object to test number against
 * @param {String} NaNErrMsg an error message to return if the input string is not a number
 * @param {String} errMsg error message to display if the given number is not in the range
 * @returns {Function} test a (hopefully) number to be in a range of the size of another string
 */
function placeValidator(object,NaNErrMsg,errMsg){
	function validate(num){
		if(isNaN(num))
			return NaNErrMsg;
		var val=new Number(num);
		if(val>document.getElementById(object).value.length)
			return errMsg + document.getElementById(object).value.length;
	}
	return validate;
}

/**
 * returns a function that test Strings to match a given regular  expression
 * @param regExps the regular expression to test the strings against
 * @returns {Function} a function that test Strings to match a given regular  expression
 */
function matcher(regExps){
	function validate(str){
		for(regExp in regExps)
			if(!str.match(regExps[regExp][0]))
				return regExps[regExp][1];	
	}
	return validate;
}