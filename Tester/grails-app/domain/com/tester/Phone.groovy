package com.tester

/**
 * Phone
 * A domain class describes the data object and it's mapping to the database
 */
class Phone {

	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	
	/* Automatic timestamping of GORM */
//	Date	dateCreated
//	Date	lastUpdated
	
//	static	belongsTo	= []	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
//	static	hasOne		= []	// tells GORM to associate another domain object as an owner in a 1-1 mapping
//	static	hasMany		= []	// tells GORM to associate other domain objects for a 1-n or n-m mapping
//	static	mappedBy	= []	// specifies which property should be used in a mapping 
	int index
	String number
	PhoneType type
	boolean deleted
	static transients = [ 'deleted' ]
	static belongsTo = [ person:Person]
	
	static constraints = {
        index(blank:false, min:0)
        number(blank:false)
        type(blank:false, inList:PhoneType.list(), minSize:1, maxSize:1)
    }
 
    static mapping = {
        index column:"phone_index"
    }
 
  
 
    def String toString() {
        return "($index) ${number} - ${type.value}"
    }
}
