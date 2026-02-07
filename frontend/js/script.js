
function registerUser() {

    const name = document.getElementById("name").value;
    const phoneNumber = document.getElementById("phone").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch("http://localhost:8080/users/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: name,
            phoneNumber: phoneNumber,
            email: email,
            password: password
        })
    })
    .then(response => response.json())
    .then(data => {

        document.getElementById("message").innerText = "Registration Successful!";
        document.getElementById("message").style.color = "lightgreen";

        setTimeout(() => {
            window.location.href = "login.html";
        }, 1500);

    })
    .catch(error => {
        document.getElementById("message").innerText = "Registration Failed!";
        document.getElementById("message").style.color = "red";
        console.error(error);
    });

}
