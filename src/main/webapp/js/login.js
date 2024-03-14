function validateForm() {
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

    if (!emailRegex.test(email)) {
        alert('Please enter a valid email address.');
        return false;
    }

    if (!passwordRegex.test(password)) {
        alert('Password must be at least 8 characters, contain at least one letter and one number.');
        return false;
    }

    return true;
}