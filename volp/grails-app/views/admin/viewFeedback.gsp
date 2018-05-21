<div class="container">
<h5> Feedback From Learners</h5>
<table class="table table-striped">
<th> username</th>
<th> First Name</th>
<th> Feedback</th>
<th>Delete feedback</th>

<g:each var ="f" in="${learner}">
<tr>
<td>${f[0].uid}</td>
<td>${f[0].person.firstName}</td>
<td>${f[1]}</td>
<td> <a href="#" onclick="deletefeedback(${f[0].uid});"><i class="fa fa-trash-o fa-2x"></i></a></td>

</tr>
</g:each>
</table>
<br>
<h5> Feedback From Instructor</h5>
<table class="table table-striped">
<th> username</th>
<th> First Name</th>
<th> Feedback</th>
<g:each var ="f" in="${instructor}">
<tr>
<td>${f[0].uid}</td>
<td>${f[0].person.firstName}</td>
<td>${f[1]}</td>
</tr>
</g:each>
</table>
</div>