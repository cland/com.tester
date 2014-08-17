<%@ page import="com.tester.Person" %>



<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="person.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${personInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'office', 'error')} required">
	<label for="office">
		<g:message code="person.office.label" default="Office" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="office" name="office.id" from="${com.tester.Office.list()}" optionKey="id" required="" value="${personInstance?.office?.id}" class="many-to-one"/>
</div>

<table class="dialog">
	<tr class="prop">
	<td valign="top" class="name">
	<label for="phones"><g:message code="person.phones.label" default="Phones List" /></label>
	</td>
	<td valign="top" class="value ${hasErrors(bean: personInstance, field: 'phones', 'errors')}">
	<!-- Render the phones template (_phones.gsp) here -->
	<g:render template="phones" model="['personInstance':personInstance]" />
	<!-- Render the phones template (_phones.gsp) here -->
	</td>
	</tr>
	</tbody>
	</table>