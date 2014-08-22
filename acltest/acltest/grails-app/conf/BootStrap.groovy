import com.acltest.*

import static org.springframework.security.acls.domain.BasePermission.ADMINISTRATION 
import static org.springframework.security.acls.domain.BasePermission.DELETE 
import static org.springframework.security.acls.domain.BasePermission.READ 
import static org.springframework.security.acls.domain.BasePermission.WRITE

import org.springframework.security.authentication. UsernamePasswordAuthenticationToken 
import org.springframework.security.core.authority.AuthorityUtils 
import org.springframework.security.core.context.SecurityContextHolder as SCH

/**
 * http://grails-plugins.github.io/grails-spring-security-acl/docs/manual/guide/single.html#tutorial
 * @author jason.dembaremba
 *
 */

class BootStrap {
	def aclService 
	def aclUtilService 
	def objectIdentityRetrievalStrategy 
	def sessionFactory
	
	def init = { servletContext -> createUsers() loginAsAdmin() grantPermissions() sessionFactory.currentSession.flush()

	// logout SCH.clearContext() 
	}

	private void loginAsAdmin() { 
	// have to be authenticated as an admin to create ACLs 
	SCH.context.authentication = new UsernamePasswordAuthenticationToken( 'admin', 'admin123', AuthorityUtils.createAuthorityList('ROLE_ADMIN')) 
	}

	private void createUsers() { 
		def roleAdmin = new Role(authority: 'ROLE_ADMIN').save() 
		def roleUser = new Role(authority: 'ROLE_USER').save()	
		
		3.times { 
			long id = it + 1 
			def user = new User(username: "user$id", enabled: true, password: "password$id").save() 
			UserRole.create user, roleUser 
			}
	
		def admin = new User(username: 'admin', enabled: true, password: 'admin123').save()
	
		UserRole.create admin, roleUser 
		UserRole.create admin, roleAdmin, true 
		}

 }