/**
 * this javascript file contains static and non-static methods to change the displaying properties of elements
 * in html page
 * Author:Ohad Cohen, ohadcn (at) cs.huji.ac.il
 * created for ex4 in OOP course
 */

/**
 * hides the element
 */
Element.prototype.hide = function(){
    this.style.display="none";
};

/** 
 * show the element
 */
Element.prototype.show = function(){
    this.style.display="";
};

/**
 * set the color of the element
 * @param color the new color of the element
 */
Element.prototype.setColor = function(color){
    this.style.color=color;
};

/**
 * set the background color of the element
 * @param color the new background color for the elemet
 */
Element.prototype.setBGColor=function(color){
    this.style.backgroundColor=color;
};

/**
 * hides all the elements of the same class
 * @param className the class to hide
 */
function hideClass(className){
	var list=document.getElementsByClassName(className);
	for(element in list)
		if(list[element].hide) //do not try to  run on the length element
			list[element].hide();
}

/**
 * make all the element of a class visible
 * objects that aren't visible because of their container will be kept unvisible
 * @param className the class to show
 */
function showClass(className){
	var list=document.getElementsByClassName(className);
	for(element in list)
		if(list[element].show)
			list[element].show();
}