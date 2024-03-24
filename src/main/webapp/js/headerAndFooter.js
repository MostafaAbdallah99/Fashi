$(document).ready(function () {


    updateCartDropdown();

    if (getCookie('user_login') === 'true') {
        // If it is, change the text and href of the login link
        $('#login-link span').text('Logout');
        $('#login-link').attr('href', '#'); // Change href to '#' to prevent page navigation

        $('#login-link').on('click', function (e) {
            e.preventDefault(); // Prevent the default action (navigation)
            logout();


        });

    }

    $('.cart-hover .select-items').on('click', '.si-close', function () {
        // Get the table row for the product
        var row = $(this).closest('tr');

        // Get the product ID from a data attribute
        var productId = row.data('product-id');

        // Call the removeProduct function
        removeProduct(productId, row);
    });

    // Call the function whenever the cart items in the local storage change
    $(window).on('storage', function (e) {
        if (e.originalEvent.key === 'cartItems') {
            updateCartDropdown();
        }
    });

    setTimeout(function () {
        $(window).on('cartUpdated', function (e) {
            console.log('Cart updated event received');
            updateCartDropdown();
        });
    }, 0);


});



function getCartItems() {
    // Send a GET request to the server-side endpoint that returns the CustomerDTO from the session
    $.ajax({
        url: 'home',
        type: 'GET',
        dataType: 'json',
        success: function (customerDTO) {
            // Check if the CustomerDTO is not null

            if (customerDTO) {
                $.ajax({
                    url: 'home',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        cartId: customerDTO.id
                    },
                    success: function (cartDTO) {
                        // Check if the CartDTO is not null
                        if (cartDTO) {
                            // Update the cart items in the local storage
                            console.log(cartDTO);
                            localStorage.setItem('cartItems', JSON.stringify(cartDTO));
                            sessionStorage.setItem('cart_retrieved', 'true');
                            updateCartDropdown();
                        }
                    }
                });

            }
        }
    });
}


function updateCartDropdown() {
    // Clear the current items in the cart dropdown
    $('.cart-hover .select-items tbody').empty();

    // Retrieve the cart items from the local storage
    var cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];

    // Initialize totalQuantity and totalPrice
    var totalQuantity = 0;
    var totalPrice = 0.0;

    // Loop through each cart item
    cartItems.forEach(function (item) {
        // Create the HTML elements for the cart item
        var cartItem = $('<tr>' +
            '<td class="si-pic"><img src="' + item.product.productImage + '" alt=""></td>' +
            '<td class="si-text">' +
            '<div class="product-selected">' +
            '<p>$' + item.product.productPrice + ' x ' + item.quantity + '</p>' +
            '<h6>' + item.product.productName + '</h6>' +
            '</div>' +
            '</td>' +
            '<td class="si-close">' +
            '<i class="ti-close"></i>' +
            '</td>' +
            '</tr>');

        // Append the created HTML elements to the cart dropdown
        cartItem.data('product-id', item.product.id);
        $('.cart-hover .select-items tbody').append(cartItem);

        // Update totalQuantity and totalPrice
        totalQuantity += item.quantity;
        totalPrice += item.product.productPrice * item.quantity;
    });

    // Update the span that contains the number of items and the total
    $('#quant').text(totalQuantity);
    $('.cart-icon .select-total h5').text('$' + totalPrice.toFixed(2));
    $('.cart-price').text(totalPrice.toFixed(2));
}


function addToCart(productId, quantity,isnew=true) {
    // Make the AJAX POST request to the cart servlet
    $.ajax({
        url: 'cart',  // Update this to the URL of your cart servlet
        type: 'POST',
        data: {
            productId: productId,
            quantity: quantity
        },
        success: function (response) {

            console.log('Product added to cart successfully:', response);
            var cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
            // Check if a cart item with the given product ID already exists in the cart items
            var parts = productId.split("-");
            var i = parts[1];
            var existingCartItem = cartItems.find(function (item) {
                console.log(item.id.productId);
                return item.id.productId == i;
            });

            if (existingCartItem) {
                var quan =parseInt(existingCartItem.quantity)+ parseInt(quantity);
                existingCartItem.quantity = quan;
            } else {

                cartItems.push(response);
            }
            var event = new Event('cartUpdated');
            window.dispatchEvent(event);
            if(isnew){
            $('#popup').html("Added to cart Successfully").fadeIn().delay(3000).fadeOut();
            }
            localStorage.setItem('cartItems', JSON.stringify(cartItems));
            updateCartDropdown();
        },
        error: function (error) {
            console.log('Error adding product to cart:', error);
        }
    });
}




function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}


function removeProduct(productId, row) {
    // Remove the product from the cart in local storage
    var cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
    cartItems = cartItems.filter(function (item) {
        return item.product.id !== productId;
    });
    localStorage.setItem('cartItems', JSON.stringify(cartItems));

    // Remove the product row from the table
    row.remove();

    // Send an AJAX request to remove the product from the cart in the database
    $.ajax({
        url: 'remove-from-cart',  // Update this to the URL of your server-side script
        method: 'POST',
        data: {
            productId: productId
        },
        success: function (response) {
            // Handle the response from the server
            updateCartDropdown();
            console.log(response);
        }
    });
}

function logout() {
    // Send a GET request to the logout servlet
    $.ajax({
        url: 'logout',
        type: 'GET',
        success: function () {
            deleteCookie('user_login');
            localStorage.removeItem('cartItems');
            updateCartDropdown();
            sessionStorage.setItem('cart_retrieved', 'false');
            location.reload();
        }
    });
}

function deleteCookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}