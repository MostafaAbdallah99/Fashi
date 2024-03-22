document.querySelector('form').addEventListener('submit', function (event) {
    // Create a FormData object from the form
    var formData = new FormData(event.target);

    // Log each key-value pair in the FormData object
    for (var pair of formData.entries()) {
        console.log(pair[0] + ': ' + pair[1]);
    }

    // Prevent the form from submitting
    event.preventDefault();

    var cartItems = localStorage.getItem('cartItems');
    var cartItems = JSON.parse(cartItems?cartItems:'[]');
    // Convert cartItems to a JSON string
    var cartItemsJson = JSON.stringify(cartItems);

    // Append cartItemsJson to formData
    formData.append('cartItems', cartItemsJson);
    console.log(formData.get('cartItems'));
    console.log(formData.get('email'));
        var queryString = Array.from(formData.entries()).map(function(entry) {
            return encodeURIComponent(entry[0]) + '=' + encodeURIComponent(entry[1]);
        }).join('&');
    // Send the form data to the server
 $.ajax({
     url: 'register',
     type: 'POST',
     data: queryString,
     success: function(response) {
         // Handle the response
         window.location.href = 'home.jsp';
     },
     error: function(error) {
         // Handle the error
     }
 });
});


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



