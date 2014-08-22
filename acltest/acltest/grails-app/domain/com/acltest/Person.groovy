package com.acltest
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.FactoryUtils;
class Person {
	String name
	List phones
	static belongsTo = [office:Office]
	static hasMany = [phones:Phone]
    static constraints = {
		
    }
	static mapping = {
		phones cascade:"all-delete-orphan"
	}
	def getPhonesList() {
		return LazyList.decorate(
			  phones,
			  FactoryUtils.instantiateFactory(Phone.class))
	}
	String toString(){
		return name + " - " + office?.name
	}
}
