<!DOCTYPE html>

<head></head>
<body>
<table>
  <tr>
    <td>mesasge
      <p>${mesasge}</p>
      <p>${bundle.resource_msg}</p>
      <p>${bundle('msg_parameterized', '${prno}')}</p>

      <#if condition==1>
         <p>inside if condition</p>
      <#elseif condition==2>
        <p>inside else if condition</p>
      <#else>
        <p>inside else condition</p>
      </#if>
    </td>
  </tr>
</table>

<#list listItems as listItem>
  ${listItem_index} - ${listItem}
</#list>

<#include "includes/content.ftl">
</body>
</html>