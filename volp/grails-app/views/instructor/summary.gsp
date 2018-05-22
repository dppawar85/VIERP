 <asset:stylesheet src="menussummary.css" rel="stylesheet" />
 <style>
  ul {
  list-style-type: square;
  }
 ol {
  list-style-type: number;
  }

 </style>
<h5> Course Summary </h5>
<div class="container">
 	<ul class="mainmenu" >
 		<g:each var="obj" in="${menu}">

 		<li class="form-control"><h6>${obj[0]}: ${obj[1]}&nbsp;<i class="fa fa-sort-desc"></i></h6>

 			<div class="submenu" style="overflow-y:scroll;height:50%">
 				<ol>
 				<g:each var="sobj" in="${obj[2]}">
 					<li> <a href="#" > ${sobj}</a></li>
 					</g:each>
 				</ol>
 				</div>


 		</li>
 		</g:each>
	</ul>

</div>