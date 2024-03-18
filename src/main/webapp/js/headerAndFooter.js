$(document).ready(function () {


    // Call the function when the page loads
    updateCartDropdown();

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


function addToCart(productId, quantity) {
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
                            // If it does, increase its quantity by the given quantity
                            existingCartItem.quantity += quantity;
                        } else {

                            cartItems.push(response);
                        }
            var event = new Event('cartUpdated');
            window.dispatchEvent(event);
            localStorage.setItem('cartItems', JSON.stringify(cartItems));
            updateCartDropdown();
        },
        error: function (error) {
            console.log('Error adding product to cart:', error);
        }
    });
}