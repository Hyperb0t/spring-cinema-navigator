document.addEventListener("DOMContentLoaded", addListeners);

function addListeners() {
    document.getElementById("submitButton").addEventListener("click", checkRegForm);
}

function checkRegForm() {
    var errorMessages = [];
    var emailRegexp = new RegExp("[a-z, 0-9]+@[a-z]+.[a-z]+");
    if(!emailRegexp.test(document.getElementById("email").value)) {
        errorMessages.push("wrong email");
    }
    if(document.getElementById("password").value !== document.getElementById("repassword").value) {
        errorMessages.push("passwords don't match");
    }
    if(document.getElementById("password").value.length < 5) {
        errorMessages.push("password is too short (less than 5 symbols)");
    }
    if(!document.getElementById("accept").checked) {
        errorMessages.push("please, accept the terms");
    }
    return errorMessages;
}

function checkSubmit() {
    var errors = checkRegForm();
    if(errors.length !== 0) {
        event.preventDefault();
        document.getElementById( "errors").textContent = errors[0];
    }
}