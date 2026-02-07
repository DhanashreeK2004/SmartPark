async function loginUser() {

    const identifier = document.getElementById("identifier").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch(
            `http://localhost:8080/users/login?identifier=${identifier}&password=${password}`,
            {
                method: "POST"
            }
        );

        if (!response.ok) {
            throw new Error("Invalid credentials");
        }

        const data = await response.json();

        // Save userId for dashboard usage
        localStorage.setItem("userId", data.id);
        localStorage.setItem("userName",data.name);
        // Redirect to dashboard
        window.location.href = "dashboard.html";

    } catch (error) {
        document.getElementById("message").innerText = "Invalid Email or Password";
    }
}