$(document).ready(function() {
 
 $.ajax({
  
  url: "https://api.github.com/users",
  success: function(data) {
   
   console.log(data);

   var contents = "";
   $.each(data, function(idx) {
    
    contents +=  "<tr>";
    contents += "<td>" + data[idx].id + "</td>";
    contents += "<td class='login' id='login" + idx + "'>" + data[idx].login + "</td>";
    contents += "<td class='photos' id='photos" + idx + "'></td>";
    contents += "</tr>";
   });
   
   // console.log(contents);
   $("#usersTable").append(contents);
   
  }
 });
 
});

// click the login, show the photos
$(document).ready(function() {


 
 $(document).on("click", ".login", function(event) {
  
  var login = event.target.innerText;
  var loginid = event.target.id;
  var photosid = loginid.replace("login", "photos");
  
  var ajaxRequestUrl = "https://api.github.com/users/" + login + "/followers";
  
  
  $.ajax({
   type: "GET",
   url: ajaxRequestUrl,
   dataType: "json",
   success: function(data) {
      for (var i = 0; i < data.length; i ++){
       $("#" + photosid).append("< img src='" + data[i].avatar_url + "' width=25 height=25 />")
      }
     }
  });
 });
 
});