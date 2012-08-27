package oop.ex1.filters;

/**
 * This enum contain all the filters known by this package.
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * @author Yaakov Pinsky, ypinsk48@cs.huji.ac.il
 * exercise : OOP ex1 2011-2012 
 */
enum Filters {
	//using lowwercase letters cause of the ex. specs.
	before,
	after,
	greater_than,
	smaller_than,
	file,
	prefix,
	suffix,
	writable,
	executable,
	hidden;
	
}
