package com.tester



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PersonController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Person.list(params), model:[personInstanceCount: Person.count()]
    }

    def show(Person personInstance) {
        respond personInstance
    }

    def create() {
        respond new Person(params)
    }

    @Transactional
    def save(Person personInstance) {
		println(params)
        if (personInstance == null) {
            notFound()
            return
        }

		//** ATTEMPT TO SAVE THE PHONES BUT NOT WORKING **//
		
		println("Manually binding the phones ?? could be a better way")
		personInstance.phones.clear()
		int index = 0
		int cnt = 0
		def pEntry = params.get('phones[' + index + ']')
		while(pEntry != null){
			println(pEntry)
			Phone p = new Phone(pEntry)
			if(pEntry?.deleted=='false'){
				p?.index = cnt
				personInstance?.addToPhones(p)
				cnt++
			}
			//next p
			index++
			pEntry = params.get('phones[' + index + ']')
		}
		//** END ATTEMPT ** //
		
		println("Saving instance...")
        personInstance.save //flush:true
		if(personInstance.hasErrors()){
			println(personInstance.errors)
			respond personInstance.errors, view:'create'
			return
		}
		


        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'personInstance.label', default: 'Person'), personInstance.id])
                redirect personInstance
            }
            '*' { respond personInstance, [status: CREATED] }
        }
    }

    def edit(Person personInstance) {
        respond personInstance
    }

    @Transactional
    def update(Person personInstance) {
        if (personInstance == null) {
            notFound()
            return
        }

        if (personInstance.hasErrors()) {
            respond personInstance.errors, view:'edit'
            return
        }
		println("Manually binding the phones ?? could be a better way")
		personInstance.phones.clear()
		int index = 0
		int cnt = 0
		def pEntry = params.get('phones[' + index + ']')
		while(pEntry != null){
			println(pEntry)
			Phone p = new Phone(pEntry)
			if(pEntry?.deleted=='false'){
				p?.index = cnt
				personInstance?.addToPhones(p)
				cnt++
			}
			//next p
			index++
			pEntry = params.get('phones[' + index + ']')
		}
        personInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Person.label', default: 'Person'), personInstance.id])
                redirect personInstance
            }
            '*'{ respond personInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Person personInstance) {

        if (personInstance == null) {
            notFound()
            return
        }

        personInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Person.label', default: 'Person'), personInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'personInstance.label', default: 'Person'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
