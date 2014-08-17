grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

//grails.project.fork = [
//    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
//    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
//
//    // configure settings for the test-app JVM, uses the daemon by default
//    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
//    // configure settings for the run-app JVM
//    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
//    // configure settings for the run-war JVM
//    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
//    // configure settings for the Console UI JVM
//    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
//]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums false // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
		grailsPlugins()
		grailsHome()
		mavenLocal()
		grailsCentral()
		mavenCentral()		       

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
		mavenRepo "http://repo.grails.org/grails/core"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        runtime 'mysql:mysql-connector-java:5.1.27'
        // runtime 'org.postgresql:postgresql:9.3-1100-jdbc41'
        // runtime 'mysql:mysql-connector-java:5.1.25'
		
    }

    plugins {
		// plugins for the build system only
		build ":tomcat:7.0.50"

		// plugins for the compile step
		compile ":scaffolding:2.0.1"
		compile ':cache:1.1.1'

		// plugins needed at runtime but not for compilation
		runtime ":hibernate:3.6.10.7" // or ":hibernate4:4.1.11.6"
		runtime ":database-migration:1.3.8"
		runtime ":jquery:1.10.2.2"
		runtime ":resources:1.2.1"
		
		compile ':spring-security-core:1.2.7.3'
		compile ":spring-security-ui:0.2"
		compile ":famfamfam:1.0.1"
		compile ":mail:1.0.1"
		compile ":jquery-ui:1.10.3"
		compile ":grails-melody:1.49.0" //** compile ":grails-melody:1.45"
		compile ":attachmentable:0.3.0"
		compile ":audit-logging:0.5.5.3"
		//compile ":searchable:0.6.4"  	//v: 0.6.4+ for grails version: 2.2+
		compile ":searchable:0.6.5"  //v: 0.6.5+ for grails version: 2.3+ (0.6.6 was problematic)
		//compile ":rendering:0.4.4"
		compile ":export:1.5"
	//	compile ":weceem:1.2-M1"					//simply needs more testing - could not get it run-app
	//	compile ":weceem-spring-security:1.2-M1"	//simply needs more testing - could not get it run-app
		compile ":ckeditor:3.6.6.1.1"
    }
}
