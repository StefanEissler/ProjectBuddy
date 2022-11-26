$("button").click(function(e) {
    e.preventDefault();
    const name=document.querySelector('#name')
    const password =document.querySelector("#password")
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/auth/signin",
        data: {
            "usernameOrEmail": name,
            "password": password
        },
        success: function(result) {
            alert('ok');
        },
        error: function(result) {
            alert('error');
        }
    });
});