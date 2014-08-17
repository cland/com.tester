package com.tester

class Person {
	String name
	static belongsTo = [office:Office]
    static constraints = {
		
    }
	
	String toString(){
		return name + " - " + office?.name
	}
}
