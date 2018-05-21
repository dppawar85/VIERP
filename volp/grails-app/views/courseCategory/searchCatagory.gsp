<!DOCTYPE html>
<html>
    <head>
        <g:set var="entityName" value="${message(code: 'courseCategory.label', default: 'CourseCategory')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
 <asset:stylesheet href="jquery-ui.css"/>
  <asset:javascript src="jquery-2.2.0.min.js"/>
  <asset:javascript src="jquery.js"/>
  <asset:javascript src="jquery-ui.js" />
  <asset:javascript src="bootstrap.js"/>
    </head>
    <body>

                <label >Search for course:</label>
                <input type="text" name="txt" id="autocomplete" onChange="showAllCourse(this.value)">
   <!-- <g:select name="mainCourse" from="${mainCourse.toList()}" optionKey="id" optionValue="name" noSelection="['':'-Choose your course Category-']" onchange="showUser(this.value)"/> -->

    <div id="subcat"></div>
    <!--<div id="course"></div>-->
<script>
        var count = 0;
        function showUser(str) {
                count++;
                var courseId = document.getElementById('mainCourse').value;
               // alert(count);
                if (window.XMLHttpRequest) {
                    // code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp = new XMLHttpRequest();
                } else {
                    // code for IE6, IE5
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("subcat").innerHTML = this.responseText;
                    }
                };

                xmlhttp.open("GET","${request.contextPath}/courseCategory/findSubCourses?cid="+courseId,true);
                xmlhttp.send();
            }
         function showCourse(str) {
                        var courseId = document.getElementById('subCourses').value;
                        alert(courseId);
                        if (window.XMLHttpRequest) {
                            // code for IE7+, Firefox, Chrome, Opera, Safari
                            xmlhttp = new XMLHttpRequest();
                        } else {
                            // code for IE6, IE5
                            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                        }
                        xmlhttp.onreadystatechange = function() {
                            if (this.readyState == 4 && this.status == 200) {
                                document.getElementById("subcat").innerHTML = this.responseText;
                            }
                        };

                        xmlhttp.open("GET","${request.contextPath}/courseCategory/findSubCourses?cid="+courseId,true);
                        xmlhttp.send();
                    }
                    function showAllCourse(val)
                    {

                         //alert(val);
                         if (window.XMLHttpRequest)
                         {
                         // code for IE7+, Firefox, Chrome, Opera, Safari
                         xmlhttp = new XMLHttpRequest();
                         } else {
                         // code for IE6, IE5
                         xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                         }
                         xmlhttp.onreadystatechange = function() {
                         if (this.readyState == 4 && this.status == 200) {
                         document.getElementById("course").innerHTML = this.responseText;
                         }
                         };

                         xmlhttp.open("GET","${request.contextPath}/courseCategory/findCourses?searchTxt="+val,true);
                         xmlhttp.send();

                        // alert(val)
                    }
        </script>
<script>
$(document).ready(function(){
    $('#autocomplete').autocomplete({
                     source: '<g:createLink controller='courseCategory' action='ajaxCourseFinder'/>'
                   });
});
</script>

    </body>

</html>