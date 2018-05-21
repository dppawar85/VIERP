<g:set var="i" value="${1}" />
<table class="table table-stripped col-sm-12" >
<tbody>
<tr class="bg-warning">
<th>S.No</th>
<th>GR Number</th>
<th>First Name</th>
<th>Middle Name</th>
<th>Last Name</th>
<th>Program</th>
<th>Year</th>
<th>Email</th>
</tr>
<g:each var="list" in="${learnerlist}">
<tr>
    <td>${i}</td>
    <td>${list.registration_number}</td>
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
    <g:if test="${list.program!=null}">
        <td>${list.program.name}</td>
    </g:if>
    <g:else>
        <td>-</td>
    </g:else>
    <g:if test="${list.current_year!=null}">
        <td>${list.current_year.year}</td>
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