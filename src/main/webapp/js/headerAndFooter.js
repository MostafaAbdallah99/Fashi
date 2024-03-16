$(document).ready(function () {
    // Function to update the cart dropdown
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

    // Call the function when the page loads
    updateCartDropdown();

    // Call the function whenever the cart items in the local storage change
    $(window).on('storage', function (e) {
        if (e.originalEvent.key === 'cartItems') {
            updateCartDropdown();
        }
    });

    setTimeout(function() {
        $(window).on('cartUpdated', function (e) {
            console.log('Cart updated event received');
            updateCartDropdown();
        });
    }, 0);
});