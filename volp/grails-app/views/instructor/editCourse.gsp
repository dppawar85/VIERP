<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Instructor</title>
    <script type="text/javascript">
    function add_feed()
        {
        	var div1 = document.createElement('div');
        	// Get template data
        	div1.innerHTML = document.getElementById('newlinktpl').innerHTML;
        	// append to our form, so that template data
        	//become part of form
        	document.getElementById('newlink').appendChild(div1);
        }
        function add_feeddes()
                {
                	var div1 = document.createElement('div');
                	// Get template data
                	div1.innerHTML = document.getElementById('newlinktpldes').innerHTML;
                	// append to our form, so that template data
                	//become part of form
                	document.getElementById('newlinkdes').appendChild(div1);
                }
     function add_feedurl()
             {
             	var div1 = document.createElement('div');
             	// Get template data
             	div1.innerHTML = document.getElementById('newlinktplurl').innerHTML;
             	// append to our form, so that template data
             	//become part of form
             	document.getElementById('newlinkurl').appendChild(div1);
             }
     function add_feedpre()
             {
             	var div1 = document.createElement('div');
             	// Get template data
             	div1.innerHTML = document.getElementById('newlinktplpre').innerHTML;
             	// append to our form, so that template data
             	//become part of form
             	document.getElementById('newlinkpre').appendChild(div1);
             }
    </script>
    <script type="text/javascript">
        function getCourse(cid) {
            //alert("first")

            var msgflg=0
            if (crs.length == 0) {
                    document.getElementById("show").innerHTML = "";
                    return;
                } else {
                    var xmlhttp = new XMLHttpRequest();
                    xmlhttp.onreadystatechange = function() {
                        if (this.readyState == 4 && this.status == 200) {
                            //alert(cat)
                            document.getElementById("show").innerHTML = this.responseText;
                        }
                    };
                    xmlhttp.open("GET", "${request.contextPath}/instructor/getCourseDetails?cid=" + cid, true);
                    xmlhttp.send();
                }
        }
    </script>

</head>
<body>

<g:select optionKey="id" from="${crs}" optionValue="${{it.course_name}}" name="crs" onchange="getCourse(this.value)" noSelection="${['null':'Select One...']}"  />
<div id="show"></div>
</body>

</html>