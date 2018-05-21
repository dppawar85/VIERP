<!doctype html>
    <html>
    <head>
    <meta name="layout" content="blank">
    <style>
          table {
              border-collapse: collapse;

          }

          table, th, td {
              padding: 2px;
              border: 1px solid black;
              text-align: center;
          }

        </style>
    </head>
    <body>
    <div style="border-radius:5px; position: absolute; top: 50px; right: 80px;" class="bg-warning">
            <a class="btn btn-sm" style="color: black" href="/instructor/instructor"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
        </div>
        <br><hr />
<div class="container">
<g:form >
<g:select class="form-control" name="${at.id}" id="${at.id}" noSelection="${['null':'Select One...']}"
    from="${at.type}"
    value="${it}" optionKey="${id}" onchange= "select(this,${coffrid})" />
<!--<g:submitToRemote url="[controller:'assignment',action:'findassignmentbytype']" update="updateMe" value="Show" />-->
</g:form>
<br>
<div id="updateMe">

</div>
</div>
<script>
function select(at,coffrid)
{
        //alert("Parth Cartoon");
        var assignmenttype = document.getElementById("${at.id}").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("updateMe").innerHTML = this.responseText;
                    }
                };
       //         xmlhttp.open("GET", "xyz", true);
                xmlhttp.open("GET", "${request.contextPath}/assignment/findassignmentbytype?assignmenttype=" + assignmenttype +"&coffrid="+coffrid,true);
                xmlhttp.send();
        return(false)
  }
function editAssignent(id,ao)
{

        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("updateMe").innerHTML = this.responseText;
                    }
                };
       //         xmlhttp.open("GET", "xyz", true);
                xmlhttp.open("GET", "${request.contextPath}/assignment/editassignment?assid=" + id+"&ao="+ao ,true);
                xmlhttp.send();
        return(false)
  }

function check(id){

if(document.getElementById("iscorrect"+id).checked) {
document.getElementById('iscorrect1Hidden'+id).value = id;
//alert("check"+id)
    //document.getElementById('iscorrect1Hidden'+id).vaule = ;
}
if(!document.getElementById("iscorrect"+id).checked) {
document.getElementById('iscorrect1Hidden'+id).value = "0";
//alert("check"+id)
    //document.getElementById('iscorrect1Hidden'+id).vaule = ;
}
}



</script>
</body>
</html>
