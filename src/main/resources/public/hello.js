$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/api/hello/greeting"
    }).then(function(data) {
       $('.msg').append(data.msg);
    });
});