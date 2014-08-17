<%@ page import="com.tester.Office" %>



<div class="fieldcontain ${hasErrors(bean: officeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="office.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${officeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: officeInstance, field: 'staff', 'error')} ">
	<label for="staff">
		<g:message code="office.staff.label" default="Staff" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${officeInstance?.staff?}" var="s">
    <li><g:link controller="person" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="person" action="create" params="['office.id': officeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'person.label', default: 'Person')])}</g:link>
</li>
</ul>

</div>

