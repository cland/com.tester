modules = {
    application {
		dependsOn: 'jquery'
        resource url:'js/application.js'
    }
	
	jquery {
		resource url: 'js/jquery/jquery-1.11.1.js'
	}
}