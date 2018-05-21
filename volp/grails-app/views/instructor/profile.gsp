<!DOCTYPE HTML>
<html>
<head>
  <meta charset="utf-8">
  <g:if test="${role == 'Instructor'}">
  <meta name="layout" content="instructorTemp">
</g:if>
<g:elseif test="${role == 'Learner'}">
<meta name="layout" content="blanklearner">
</g:elseif>
<g:else>
</g:else>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
body {font-family: Arial;}

/* Style the tab */
.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}
.form-control{
padding: 1px;
width: 100%;

}
}
/* Style the buttons inside the tab */
.tab button {
  background-color: inherit;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
  font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  display: none;
  margin: auto auto;
  width: 70%;
  font-size: 15px;
  padding: 6px 12px;
  border: 0px solid #ccc;
  border-top: none;
}
img{

height: 120px;
width: 120px;

}
@media only screen and (max-width: 600px) {
  .homeBtn {
    position: absolute;

     }
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">

<script>
  function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
  }
  function showImage(src, target) {
      var fr = new FileReader();
      fr.onload = function(){
          target.src = fr.result;
      }
      fr.readAsDataURL(src.files[0]);
  }
  function FillBilling(f) {
    if(f.billingtoo.checked == true) {

      f.altcountry.value = f.country.value;
      f.altstate.value = f.state.value;
      f.altstreet.value = f.street.value;
      f.altdistrict.value = f.district.value;
      f.altcity.value = f.city.value;
      f.altpin.value = f.pin.value;
    }
  }

  function putImage()
  {
        var str=""
    var src = document.getElementById("profilepicture");
    if (typeof (src.files) != "undefined") {
                 var size = parseFloat(src.files[0].size / 1024).toFixed(2);
                 if(size<50)
                     {
                        document.getElementById("message").innerHTML=str;
                       var target = document.getElementById("target");
                       showImage(src, target);
                       document.getElementById("submitBtnImg").disabled = false;
                     }
                     else
                     {

                      str = "**Your file size is larger than 50 please select another file.";
                       var result = str.fontcolor("red");
                       document.getElementById("message").innerHTML=result;

                        document.getElementById("submitBtnImg").disabled = true;
                     }
             }
  }
</script>
</head>
<body>
<div class="pull-right homeBtn bg-warning" style="position: relative; top: 22px; padding: 5px">
  <g:if test="${role == 'Instructor'}">
        <a href="${request.contextPath}/instructor/instructor"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
  </g:if>
  <g:elseif test="${role == 'Learner'}">
        <a href="${request.contextPath}/learner/learner"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
  </g:elseif>
  <g:else>
  </g:else>

</div>
<br>
  <div class="tab">
    <button class="tablinks" onclick="openCity(event, 'Personal Information')" >Personal Information</button>
    <button class="tablinks" onclick="openCity(event, 'Contact')">Contact</button>
    <button class="tablinks" onclick="openCity(event, 'Address')">Address</button>
    <button class="tablinks" onclick="openCity(event, 'Professional/Educational Information')">Professional/Educational Information</button>
    <button class="tablinks" onclick="openCity(event, 'Oraganization Information')">Oraganization Information</button>
  </div>
  <div id="Oraganization Information" class="tabcontent">
    <g:if test="${insData}">
    <g:if test="${insData.organization}">
    <b>Oraganization name </b><g:textField class="form-control" name="orgName" value="${insData.organization.organization_name}" /><br/>
  </g:if>
  <g:else>
  Oraganization Data is not found.
</g:else>
</g:if>
</div>
<div id="Professional/Educational Information" class="tabcontent">
  <g:form controller="instructor" action="storeInstructorProfessionalData" >
  <g:if test="${insData}">
   <g:if test="${role=='Organization'}">
         </g:if>
         <g:else>
             <g:if test="${insData.person}">
                 <b>Highest Qualification</b> <g:textField class="form-control" name="highest_qualification" value="${insData.person.highest_qualification}" /><br/>
                 <b>About You </b><g:textField class="form-control" name="short_description" value="${insData.person.short_description}" /><br/>
                 <b>User Skills</b> <g:textField class="form-control" name="skills" value="${insData.person.skills}" /><br>
             </g:if>
          </g:else>
     </g:if>
     <g:else>
         <g:if test="${role=='Organization'}">
         </g:if>
         <g:else>
         <b>Highest Qualification </b><g:textField class="form-control" name="highest_qualification" value="${highest_qualification}" /><br/>
         <b>About You </b><g:textField class="form-control" name="short_description" value="${short_description}" /><br/>
         <b>User Skills </b><g:textField class="form-control" name="skills" value="${skills}" /><br>
         </g:else>
     </g:else>
<br><br><center><g:actionSubmit class="btn btn-primary" value="Save" id="submitBtn" action="storeInstructorProfessionalData"/></center>
</g:form>
</div>
<div id="Personal Information" class="tabcontent" style="display:block">
  <br/>

  <g:form controller="instructor" action="storeInstructorData" enctype="multipart/form-data">
  <g:if test="${role == 'Instructor'}">
  <g:if test="${insData}">


<b>FirstName</b> <g:textField class="form-control"  name="firstName" value="${insData.person.firstName}" /><br/>
<b>MiddleName </b><g:textField class="form-control"  name="middleName" value="${insData.person.middleName}" /><br/>
<b>LastName </b><g:textField class="form-control"  name="lastName" value="${insData.person.lastName}" /><br>
<g:if test="${insData.person.gender}">
<b>Gender </b><g:select class="form-control" optionKey="id" name="gender" from="${genderInfo}" value="${insData?.person.gender.id}" optionValue="${{it.type}}" ></g:select>
</g:if>
<g:else>
<b>Gender </b><g:select class="form-control" optionKey="id" name="gender" from="${genderInfo}" optionValue="${{it.type}}" noSelection="['null':'select gender']" required="true"></g:select>
</g:else>
</br><b>Date_Of_Birth: </b><input class="form-control" type="text"  id="datepicker"  name="date_of_birth" value="${insData.person.date_of_birth}"></p>
<g:if test="${insData.person.logos.logo_path}">
<img src="${createLink(controller:'instructor',action:'renderImage',id:"${insData.person.id}")}" params:'"${insData}"'></br>
</g:if>
<g:else>
</br><img id="target"/>
</br><b>Select Profile Picture: </b><input type="file" id="profilepicture" name="profilePic" onchange="putImage()"/>
<br><label id="message"></label>
</g:else>
 <g:if test="${insData.person.logos}">
                      <div id="changePic">
                            <br/>
                            <g:hiddenField  name="changePicture" value="pictureChange"/>
                            <span  style="font-weight: bold; ">change profile picture</span>
                             <input type="file" id="profilepicture" name="profilePic" onchange="putImage()"/>

                            <br><label id="message"></label>
                       </div>
                    </g:if>
</g:if>
<g:else>
<b>FirstName </b><g:textField class="form-control"  name="firstName" value="${firstName}" /><br/>
<b>MiddleName </b><g:textField class="form-control"  name="middleName" value="${middleName}" /><br/>
<b>LastName </b><g:textField class="form-control"  name="lastName" value="${lastName}" /><br>
<b>Gender </b><g:select optionKey="id" name="gender" from="${genderInfo}" optionValue="${{it.type}}" noSelection="['null':'select gender']"></g:select>
</br><b>Date_Of_Birth: </b><input class="form-control" type="text"  id="datepicker" name="date_of_birth"></p>
</br><img id="target"/>
</br><b>Select Profile Picture: </b><input type="file" id="profilepicture" name="profilePic" onchange="putImage()"/>
<br><label id="message"></label>
</g:else>
</g:if>
<g:if test="${role == 'Organization'}">
                   <g:if test="${insData}">
                    <g:if test="${insData.logos.logo_path}">
                              <img src="${createLink(controller:'instructor',action:'renderImage',id:"${insData.id}")}" params:'"${insData}"'></br>
                    </g:if>
                          <b> organization_name</b>  <g:textField class="form-control" name="organization_name" value="${insData.organization_name}" /><br/>
                          <b> organization_code </b> <g:textField class="form-control" name="organization_code" value="${insData.organization_code}" /><br/>
                          <b> registration_number</b>  <g:textField class="form-control" name="registration_number" value="${insData.registration_number}" /><br>
                          <b> display_name </b> <g:textField class="form-control" name="display_name" value="${insData.display_name}" /><br>
                          <b> email </b> <g:textField class="form-control" name="email" value="${insData.email}" /><br>
                          <g:if test="${insData.organizationtype}">
                          <b> Oraganization Type</b>  <g:select class="form-control" optionKey="id" name="orgtype" from="${orgType}"  value="${insData?.organizationtype.id}" optionValue="${{it.name}}" noSelection="['null':'select Org Type']" onchange="displayUniver(this.value)"></g:select>
                          </g:if>
                           <g:if test="${insData.organizationtype}">
                              <b> Oraganization Type </b> <g:select class="form-control" optionKey="id" name="orgtype" from="${orgType}" optionValue="${{it.name}}" noSelection="['null':'select Org Type']" onchange="displayUniver(this.value)"></g:select>
                           </g:if>
                          <g:if test="${insData.organization}">
                          <br><b> Belongs to University : </b> ${insData.organization.display_name}
                          </g:if>
                          <div id="uni">
                                                    </div>
                          <g:if test="${insData.logos}">
                                            <div id="changePic">
                                                  </br>
                                                  <g:hiddenField  name="changePicture" value="pictureChange"/>
                                                      </br><b> Change Profile Picture: </b> <input class="form-control" type="file" id="profilepicture" name="profilePic" onchange="putImage()"/>
                                                  <br><label id="message"></label>
                                                  </div>
                                            </g:if>

                                            <g:else>
                                            </br><img id="target"/>
                                                              </br><b> Select Profile Picture:</b>  <input class="form-control" type="file" id="profilepicture" name="profilePic" onchange="putImage()"/>
                                                              <br><label id="message"></label>
                                            </g:else>
                    </g:if>
                    <g:else>
                         <b> organization_name</b>  <g:textField class="form-control" name="organization_name" value="${organization_name}" /><br/>
                          <b> organization_code</b>  <g:textField class="form-control" name="organization_code" value="${organization_code}" /><br/>
                          <b> registration_number</b>  <g:textField class="form-control" name="registration_number" value="${registration_number}" /><br>
                          <b> display_name </b> <g:textField class="form-control" name="display_name" value="${display_name}" /><br>
                          <b> email </b> <g:textField class="form-control"  name="email" value="${email}" /><br>
                          <br><b> Oraganization Type </b> <g:select class="form-control" id="UniCode" optionKey="id" name="orgtype" from="${orgType}" optionValue="${{it.name}}" noSelection="['null':'select Org Type']" onchange="displayUniver(this.value)"></g:select>
                          <div id="uni">
                          </div>
                          </br><img id="target"/>
                          </br><b> Select Profile Picture:</b> <input class="form-control" type="file" id="profilepicture" name="profilePic" onchange="putImage()"/>
                          <br><label id="message"></label>
                    </g:else>
        </g:if>
<g:if test="${role == 'Learner'}">
<g:if test="${insData}">

<b> FirstName </b> <g:textField class="form-control"  name="firstName" value="${insData.person.firstName}" /><br/>
<b> MiddleName </b> <g:textField class="form-control"  name="middleName" value="${insData.person.middleName}" /><br/>
<b> LastName </b> <g:textField class="form-control"  name="lastName" value="${insData.person.lastName}" /><br>
<b> Registration Number </b> <g:textField class="form-control"  name="registration_number" value="${insData.registration_number}" /><br>
<g:if test="${insData.current_year}">
   <b>  Year </b> <g:select optionKey="id" name="year" from="${yearInfo}" optionValue="${{insData.current_year?.year}}" ></g:select>
 </g:if>
 <g:else>
       <b>  Year </b> <g:select optionKey="id" name="year" from="${yearInfo}" optionValue="${{it.year}}" ></g:select>
 </g:else>
<g:if test="${insData.person.gender}">
<b> Gender </b> <g:select optionKey="id" name="gender" from="${genderInfo}" value="${insData?.person.gender.id}" optionValue="${{it.type}}" ></g:select>
</g:if>
<g:else>
<b> Gender </b> <g:select optionKey="id" name="gender" from="${genderInfo}" optionValue="${{it.type}}" noSelection="['null':'select gender']" required="true"></g:select>
</g:else>
</br><b> Date_Of_Birth: </b> <input class="form-control" type="text"  id="datepicker" name="date_of_birth" value="${insData.person.date_of_birth}"></p>
<g:if test="${insData.person.logos.logo_path}">
<img src="${createLink(controller:'instructor',action:'renderImage',id:"${insData.person.id}")}" params:'"${insData}"'></br>
</g:if>
<g:else>
<br><img id="target"/>
<br><b> Select Profile Picture: </b> <input type="file" id="profilepicture" name="profilePic" onchange="putImage()"/>
<br><label id="message"></label>
</g:else>
<g:if test="${insData.person.logos}">
                      <div id="changePic">
                            </br>
                            <g:hiddenField  name="changePicture" value="pictureChange"/>
                                <b> Change Profile Picture:</b>  <input type="file" id="profilepicture" name="profilePic" onchange="putImage()"/>
                            <br><label id="message"></label>
                       </div>
                    </g:if>
</g:if>
<g:else>
<b> FirstName </b> <g:textField class="form-control"  name="firstName" value="${firstName}" /><br/>
<b> MiddleName </b> <g:textField class="form-control"  name="middleName" value="${middleName}" /><br/>
<b> LastName </b> <g:textField class="form-control"  name="lastName" value="${lastName}" /><br>
<b> Registration Number </b> <g:textField class="form-control"  name="registration_number" value="${registration_number}" /><br>
<b> Year </b> <g:select class="form-control" optionKey="id" name="year" from="${yearInfo}" optionValue="${{it.year}}" noSelection="['null':'select Year']"></g:select>
<b> Gender </b> <g:select class="form-control" optionKey="id" name="gender" from="${genderInfo}" optionValue="${{it.type}}" noSelection="['null':'select gender']"></g:select>
<br><b> Date_Of_Birth:</b>  <input class="form-control" type="text"  id="datepicker" name="date_of_birth"></p>
<br><img id="target"/>
<br><b> Select Profile Picture: </b> <input class="form-control" type="file" id="profilepicture" name="profilePic" onchange="putImage()"/>
<br><label id="message"></label>
</g:else>
</g:if>
<br><br><center><g:actionSubmit class="btn btn-primary" value="Save" id="submitBtnImg" action="storeInstructorData"/></center>
</g:form>
</div>
<div id="Contact" class="tabcontent">
  <g:form controller="instructor" action="storeInstructorContactData" enctype="multipart/form-data">
  <g:if test="${flash.message}">
  Please fill up personal information first..
</g:if>
</br>
<g:if test="${contactInfo}">
<b>mobile_no </b><g:textField class="form-control"  name="mobile_no" value="${contactInfo.mobile_no}" /><br/>
<b>Alternate_mobile_no </b><g:textField class="form-control"  name="ulternate_mobile_no" value="${contactInfo.ulternate_mobile_no}" /><br/>
<b>alternate_email </b><g:textField class="form-control"  name="alternate_email" value="${contactInfo.alternate_email}" /><br>
<br><b>telephone_no </b><input class="form-control" type="text"  id="telephone_no" name="telephone_no" value="${contactInfo.telephone_no}">
<br><b>fax </b><input class="form-control" type="text"  id="fax" name="fax" value="${contactInfo.fax}">
<br><b>website_url </b><input class="form-control" type="text"  id="website_url"  name="website_url" value="${contactInfo.website_url}">
</g:if>
<g:else>
<b>mobile_no </b><g:textField class="form-control"  name="mobile_no" value="${mobile_no}" /><br/>
<b>Alternate_mobile_no </b><g:textField class="form-control"  name="ulternate_mobile_no" value="${ulternate_mobile_no}" /><br/>
<b>alternate_email </b><g:textField class="form-control"  name="alternate_email" value="${alternate_email}" /><br>
</br><b>telephone_no </b><input class="form-control" type="text"  id="telephone_no" name="telephone_no">
<br><b>fax </b><input class="form-control" type="text"  id="fax" name="fax">
<br><b>website_url </b><input class="form-control" type="text"  id="website_url" name="website_url">
</g:else>
<br><br><center><g:actionSubmit class="btn btn-primary" value="Save" id="submitBtn" action="storeInstructorContactData"/></center>
</g:form>
</div>
<div id="Address" class="tabcontent">
  <g:form controller="instructor" action="storeInstructorAddressData" enctype="multipart/form-data">
  <g:if test="${flash.message}">
  Please fill up personal information first..
</g:if>
<br>
<g:if test="${addressInfo}">

<g:each var="address" in="${addressInfo}" status="counter">

<g:if test="${counter == 1}">
<br><b>***Permenent Addres is***</b></br>
</g:if>
<g:else>
<br><b>***Local Addres is***</b></br>
</g:else>
<b>country </b><g:textField class="form-control"  name="country" value="${address.country}" /><br/>
<b>state </b><g:textField class="form-control"  name="state" value="${address.state}" /><br/>
<b>district </b><g:textField class="form-control"  name="district" value="${address.district}" /><br>
<br><b>city </b><input class="form-control" type="text"  id="city" name="city" value="${address.city}">
<br><b>street </b><input class="form-control" type="text"  id="street" name="street" value="${address.street}">
</br><b>pin </b><input class="form-control" type="text"  id="pin"  name="pin" value="${address.pin}">
<g:set var="x" value="${counter}"/>
</g:each>
<g:if test="${x == 0}">
</br><input class="form-control" type="checkbox" name="billingtoo" onclick="FillBilling(this.form)">
<em><b>Check this box if Permanent Address and Local Address are the same.<b></em>
</br><b>country </b><g:textField class="form-control"  name="altcountry" value="${country}" /><br/>
<b>state </b><g:textField class="form-control"  name="altstate" value="${state}" /><br/>
<b>district </b><g:textField class="form-control"  name="altdistrict" value="${district}" /><br>
</br><b>city </b><input class="form-control" type="text"   name="altcity" value="${city}">
</br><b>street </b><input class="form-control" type="text"   name="altstreet" value="${street}">
</br><b>pin </b><input class="form-control" type="text"  name="altpin" value="${pin}">
</g:if>

</g:if>

<g:else>
<b>country </b><g:textField class="form-control"  name="country" value="${country}" /><br/>
<b>state </b><g:textField class="form-control"  name="state" value="${state}" /><br/>
<b>district </b><g:textField class="form-control"  name="district" value="${district}" /><br>
</br><b>city </b><input class="form-control" type="text"  id="city" name="city" value="${city}">
</br><b>street</b> <input class="form-control" type="text"  id="street" name="street" value="${street}">
</br><b>pin </b><input class="form-control" type="text"  id="pin"  name="pin" value="${pin}">
</br><input type="checkbox" name="billingtoo" onclick="FillBilling(this.form)">
<em><b>Check this box if Permanent Address and Local Address are the same.</b></em>
</br><b>country </b><g:textField class="form-control"  name="altcountry" value="${country}" /><br/>
<b>state </b><g:textField class="form-control"  name="altstate" value="${state}" /><br/>
<b>district </b><g:textField class="form-control"  name="altdistrict" value="${district}" /><br>
</br><b>city </b><input class="form-control" type="text"   name="altcity" value="${city}">
</br><b>street </b><input class="form-control" type="text"   name="altstreet" value="${street}">
</br><b>pin </b><input class="form-control" type="text"  name="altpin" value="${pin}">
</g:else>
<br><br><center><g:actionSubmit class="btn btn-primary" value="Save" id="submitBtn" action="storeInstructorAddressData"/></center>
</g:form>

</div>

<script>

  $(document).ready(function(){
   $( "#datepicker" ).datepicker({
    changeMonth: true,
    changeYear: true,
    yearRange:'-100:+0',
    dateFormat: 'dd/mm/yy'
  });


 });
</script>
</body>
</html>