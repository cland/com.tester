
import com.tester.*
import grails.util.*
import org.springframework.web.context.support.*;
import org.codehaus.groovy.grails.commons.*;
import groovy.ui.Console;


class BootStrap {

	def init = { servletContext ->
		/**
		 * Launch the console to allow us to run scripts etc while site is running
		 */

		boolean showGroovyConsole = false

		if (Environment.getCurrent() == Environment.DEVELOPMENT && showGroovyConsole) {

			def appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)

			def grailsApp = appCtx.getBean(GrailsApplication.APPLICATION_ID);

			Binding b = new Binding();

			b.setVariable("ctx", appCtx);

			def console = new Console(grailsApp.classLoader, b);

			console.run()

		}

		println "Bootstrap > environment: " + Environment.getCurrent()
		/*
		 * if in need to run this with creating records whose domains uses audit-trail plugin
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

// set up a default user, if one doesn't already exist
def defaultUser = User.findByUsername('default') ?: new User(username:'default').save()

// run the following code as if that user were logged in
SpringSecurityUtils.doWithAuth('default') {
  new Note(name:'Testing').save()
}
		 */
		def appName = grails.util.Metadata.current.'app.name'
		println (">> Bootstrapping: ${appName} on OS >> " + System.properties["os.name"] )
		boolean doBootStrap = false
//		def userlist = User.list()
//		if(userlist?.size() < 1){
//			println("BootStrap >> ON!")
//			doBootStrap = true
//		}else{
//		println("BootStrap >> off!")
//		}
		switch(Environment.getCurrent()){
			case "DEVELOPMENT":
				if(doBootStrap){
				
					def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true) 
					def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
					def workerRole = new Role(authority: 'ROLE_CWO').save(flush: true)
					
					def adminGroup = new RoleGroup(authority:'GROUP_ADMIN')
					def userGroup = new RoleGroup(authority:'GROUP_USER')
					def workerGroup = new RoleGroup(authority:'GROUP_CWO')
					
					RoleGroupRole.create adminRole, adminGroup, true
					RoleGroupRole.create userRole, userGroup, true
					RoleGroupRole.create workerRole, workerGroup, true
					
					def testUser = new User(username: 'me', password: 'password') testUser.save(flush: true)					
					UserRoleGroup.create testUser, adminGroup, true
					
					testUser = new User(username: 'user', password: 'password') testUser.save(flush: true)
					UserRoleGroup.create testUser, userGroup, true
					
					testUser = new User(username: 'user', password: 'password') testUser.save(flush: true)
					UserRole.create testUser, workerRole, true
					
				} //end if doBootStrap
				break
			case "PRODUCTION" :
				if(doBootStrap) {
					
					
				} //end doBootStrap
				
				break
		}
	} //end init


	def destroy = {
	}
} //end class
