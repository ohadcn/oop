/**
 * this javascript file contains methods for String manipulation
 * the methods in this file are added to String prototype
 * Author:Ohad Cohen, ohadcn (at) cs.huji.ac.il
 * created for ex4 in OOP course
 */

/**
 * returns a reversed version of the string
 * @return {String} reversed version of the string
 */
String.prototype.reverse=function(){
	return this.split("").reverse().join("");
};

/**
 * returns the string for printing with each letter sorounded by it's own tag and id
 * the id name is <i>idConversion</i> and the index number of the tag in the string
 * @param {String} tag the tag to use, default:span
 * @param {String} idConversion the id for each letter
 * @returns {String} this string, ready for printing with tag and id for each letter
 */
String.prototype.addTags=function(idConversion,tag){
	if(!tag)
		tag="span";
	var arr=this.split("");
	var result="";
	for(index in arr)
		result+="<"+tag+" id=\""+idConversion+index+"\">"+arr[index]+"</"+tag+">";
	return result;
};
