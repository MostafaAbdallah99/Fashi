    function validateForm() {
    var userName = document.getElementById('customerName').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('pass').value;
    var confirmPassword = document.getElementById('con-pass').value;
    var cardLimit = document.getElementById('cardLimit').value;
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    var passwordRegex = /^(?=.*[A-Za-z]).{10,}$/;

    var errorMessages = [];

    if (!emailRegex.test(email)) {
    errorMessages.push('Please enter a valid email address');
}

    if (password !== confirmPassword) {
    errorMessages.push('Passwords do not match');
}

    if (!passwordRegex.test(password)) {
    errorMessages.push('Password must contain at least one character and be at least 10 characters long');
}

    if (cardLimit < 300) {
    errorMessages.push('Card limit must be at least 300');
}

    if (userName.length < 5) {
    errorMessages.push('Username must be at least 5 characters');
}

    document.getElementById('emailError').innerHTML = '';
    document.getElementById('confirmPasswordError').innerHTML = '';
    document.getElementById('passwordError').innerHTML = '';
    document.getElementById('cardLimitError').innerHTML = '';
    document.getElementById('usernameError').innerHTML = '';

    errorMessages.forEach(function (errorMessage) {
    if (errorMessage.includes('valid email')) {
    document.getElementById('emailError').innerHTML = errorMessage + '<br>';
} else if (errorMessage.includes('Passwords do not match')) {
    document.getElementById('confirmPasswordError').innerHTML = errorMessage + '<br>';
} else if (errorMessage.includes('Password must contain at least one character')) {
    document.getElementById('passwordError').innerHTML = errorMessage + '<br>';
} else if (errorMessage.includes('Card limit')) {
    document.getElementById('cardLimitError').innerHTML = errorMessage + '<br>';
} else if (errorMessage.includes('Username')) {
    document.getElementById('usernameError').innerHTML = errorMessage + '<br>';
}
});

    return errorMessages.length === 0;
}

