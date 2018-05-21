<g:set var="i" value="${1}" />
<table class="table table-stripped col-sm-12" >
<tbody>
<tr class="bg-warning">
<th>S.No</th>
<th>Employee Code</th>
<th>First Name</th>
<th>Middle Name</th>
<th>Last Name</th>
<th>Designation</th>
<th>Program</th>
<th>Email</th>
</tr>
<g:each var="list" in="${instructorlist}">
<tr>
    <td>${i}</td>
    <td>${list.employee_code}</td>
    <g:if test="${list.person!=null}">
        <td>${list.person.firstName}</td>
        <td>${list.person.middleName}</td>
        <td>${list.person.lastName}</td>
    </g:if>
    <g:else>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </g:else>
    <g:if test="${list.designation!=null}">
        <td>${list.designation.name}</td>
    </g:if>
    <g:else>
        <td>-</td>
    </g:else>
    <g:if test="${list.program!=null}">
        <td>${list.program.name}</td>
    </g:if>
    <g:else>
        <td>-</td>
    </g:else>
    <td>${list.uid}</td>
</tr>
<g:set var="i" value="${i+1}" />
</g:each>
</tbody>
</table>