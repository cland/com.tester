<div id="phone${i}" class="phone-div" <g:if test="${hidden}">style="display:none;"</g:if>>
<g:hiddenField name='phones[${i}].id' value='${phone?.id}'/>
<g:hiddenField name='phones[${i}].deleted' value='false'/>
<g:hiddenField name='phones[${i}].index' value='${phone?.index == null?i:phone?.index}'/>
<g:hiddenField name='phones[${i}].new' value="${phone?.id == null?'true':'false'}"/>
<g:textField name='phones[${i}].number' value='${phone?.number}' />
<g:select name="phones[${i}].type"
from="${com.tester.PhoneType.values()}"
optionKey="key"
optionValue="value"
value = "${phone?.type?.key}"/>
<span class="del-phone">
<img src="${resource(dir:'images/skin', file:'icon_delete.png')}"
style="vertical-align:middle;"/>
</span>
</div>