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



            function editCourse(){
              var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "/instructor/getCourseDetails?cid="+${cid.id}, true);
xmlhttp.send();
}

function callCourseOutcome(crscodename){
alert("crscodename:"+crscodename)
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "/CourseOutcomes/addcourseoutcomes?coursecodecoursename="+crscodename, true);
xmlhttp.send();
}
function callCourseOutline(){
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "/CourseOutline/selectcourse", true);
xmlhttp.send();
}
function callCourseTopic(){
//alert("11");
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "/CourseTopic/selectcourse", true);
xmlhttp.send();
}

function callCourseMaterial(){
//alert("11");
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "/CourseMaterial/selectcourseanduploadcategory", true);
xmlhttp.send();
}
function callCourseVideo(){
//alert("11");
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "/CourseVideos/selectcourseanduploadcategory", true);
xmlhttp.send();
}

function callCourseOffering(){

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {

      document.getElementById("ajaxcall").innerHTML = this.responseText;

    }
  };
  xmlhttp.open("GET", "/CourseOffering/selectcourse", true);
  xmlhttp.send();
}
// fetch Video script code -----------------------------------------
function fetchvideo()
{
 var coursecodecoursename = document.getElementById("coursecodecoursename").value;
 var xmlhttp = new XMLHttpRequest();
 xmlhttp.onreadystatechange = function() {
   if (this.readyState == 4 && this.status == 200) {
     document.getElementById("material4").innerHTML = this.responseText;
   }
 };
 xmlhttp.open("GET", "/courseVideos/addeditcoursevideo?coursecodecoursename=" + coursecodecoursename, true);
 xmlhttp.send();
 return(false)
}
function deletevdo(id)
{

   // alert("delete");
    var xmlhttp = new XMLHttpRequest();
     xmlhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
         document.getElementById("ajaxcall").innerHTML = this.responseText;
       }
     };
     xmlhttp.open("GET", "/courseVideos/deletecoursevideo?id=" + id, true);
     xmlhttp.send();
}
function callme()
{
  var coursecodecoursename = document.getElementById("coursecodecoursename").value;
  var uploadcategory = document.getElementById("uploadcategory").value;
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("material1").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "/courseVideos/processcoursevideo?coursecodecoursename=" + coursecodecoursename +"&uploadcategory="+uploadcategory, true);
  xmlhttp.send();
  return(false)
}
function callmetoo()
{
  var courseoutline = document.getElementById("courseoutline").value;
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("material2").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "/courseVideos/furtherprocesscoursevideo?courseoutline=" + courseoutline, true);
  xmlhttp.send();
  return(false)
}
function callmetooagain()
{
  var coursetopic = document.getElementById("coursetopic").value;
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("material13").innerHTML = this.responseText;
    }
  };
             //   xmlhttp.open("GET", "/courseVideos/savecoursevideo?coursetopic=" + coursetopic, true);
             //   xmlhttp.send();
             return(false)
           }


// -------------End of Fetch Video Ajax Call-------------------------

 // -------------------------Assessment Grade Ajax Call---------------------------

 function getCourseOfferingForGrade(){
 ///alert("hi");

 var xmlhttp = new XMLHttpRequest();
 xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
                      //alert("1");
                      document.getElementById("ajaxcall").innerHTML = this.responseText;
                      var x = document.getElementById("editgrade");
                          // alert("x:"+x)
                          x.style.display = "none";
          //alert("1"+this.responseText);
        }
      };
      xmlhttp.open("GET", "/courseOffering/getCourseOfferingForGrade?cid="+${cid.id}, true);
      xmlhttp.send();
    }
    function setGrade(coffr){
     var xmlhttp = new XMLHttpRequest();
     xmlhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("ajaxcall").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "/assessmentGrade/setGrade?coffr="+coffr, true);
       xmlhttp.send();
     }
     function editGrade(ag,coffr){
      var x = document.getElementById("editgrade");
                         //  alert("x:"+x)
                         x.style.display = "block";
                         var xmlhttp = new XMLHttpRequest();
                         xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("editgrade").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "/assessmentGrade/editGrade?ag="+ag + "&coffr="+coffr, true);
       xmlhttp.send();
     }
// -------------------------End Assessment Grade Ajax Call---------------------------

// ------------------------- Week Ajax Call---------------------------
function callWeek(crscodename){
//alert("crscodename:"+crscodename);
                         var xmlhttp = new XMLHttpRequest();
                         xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("ajaxcall").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "/Week/processweek?coursecodecoursename="+crscodename, true);
       xmlhttp.send();
     }
// -------------------------End Week Ajax Call---------------------------
// ------------------------- Session Ajax Call---------------------------
function callSession(crscodename){

                         var xmlhttp = new XMLHttpRequest();
                         xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("ajaxcall").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "/Session/processession?coursecodecoursename="+crscodename, true);
       xmlhttp.send();
     }
// -------------------------End Session Ajax Call---------------------------
// ------------------------- Assignment Ajax Call---------------------------
function callAssigment(crscodename){

                         var xmlhttp = new XMLHttpRequest();
                         xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("ajaxcall").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "/Assignment/processassignment?coursecodecoursename="+crscodename, true);
       xmlhttp.send();
     }
// -------------------------End Assignment Ajax Call---------------------------
function fetchcourseofferingweek()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("material1").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "/Week/processweek?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}

function fetchcourseofferingsession()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("material1").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "/Session/processession?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}

function fetchcourseofferingassign()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("material1").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "/Assignment/processassignment?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}
function furtherprocessweek(id){
     var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                               document.getElementById("ajaxcall").innerHTML = this.responseText;
                           }
                       };
                       xmlhttp.open("GET", "/week/furtherprocessweek?id=" + id, true);
                       xmlhttp.send();
}
function updateweeks(id){
var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                               document.getElementById("ajaxcall").innerHTML = this.responseText;
                           }
                       };
                       xmlhttp.open("GET", "/week/updateweeks?id=" + id, true);
                       xmlhttp.send();
}
function furtherprocesssession(id){
var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                               document.getElementById("ajaxcall").innerHTML = this.responseText;
                           }
                       };
                       xmlhttp.open("GET", "/session/furtherprocesssession?id=" + id, true);
                       xmlhttp.send();
}
//-------------------Course Material Ajax Call ------------------------------
function fetchmaterial()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("material4").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "/courseMaterial/addeditcoursematerial?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}

function callmeCourseMaterial()
{
        var coursecodecoursename = document.getElementById("coursecodecoursename").value;
        var uploadcategory = document.getElementById("uploadcategory").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material1").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "/courseMaterial/processcoursematerial?coursecodecoursename=" + coursecodecoursename +"&uploadcategory="+uploadcategory, true);
                xmlhttp.send();
        return(false)
  }

function callmetooCourseMaterial()
{
        var courseoutline = document.getElementById("courseoutline").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material2").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "/courseMaterial/furtherprocesscoursematerial?courseoutline=" + courseoutline, true);
                xmlhttp.send();
        return(false)
  }
//--------------------------------------------------------------------------
</script>
