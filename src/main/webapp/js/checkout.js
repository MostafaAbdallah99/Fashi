$(document).ready(function () {

    var cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];

    // Initialize totalQuantity and totalPrice
    var totalQuantity = 0;
    var totalPrice = 0.0;

    // Clear the current items in the checkout
    $('.order-table').empty();

    // Add header to the order table
    $('.order-table').append('<li>Product <span>Total</span></li>');

    // Loop through each cart item
    cartItems.forEach(function (item) {
        // Create the HTML elements for the cart item
        var cartItem = '<li class="fw-normal" data-product-id="' + item.product.id + '">' + item.product.productName + ' x ' + item.quantity +
            '<span>$' + (item.product.productPrice * item.quantity).toFixed(2) + '</span></li>';
        // Append the created HTML elements to the checkout
        $('.order-table').append(cartItem);

        // Update totalQuantity and totalPrice
        totalQuantity += item.quantity;
        totalPrice += item.product.productPrice * item.quantity;
    });

    // Add subtotal to the order table
    $('.order-table').append('<li class="fw-normal">Subtotal <span>$' + totalPrice.toFixed(2) + '</span></li>');

    // Add total to the order table
    $('.order-table').append('<li class="total-price">Total <span>$' + totalPrice.toFixed(2) + '</span></li>');

    $('.place-btn').click(function (event) {
        event.preventDefault();
        checkout();

    });
});

function checkout() {
    $.ajax({
        url: 'checkout',
        type: 'POST',
        data: {},
        dataType: 'json',
        success: function (response) {
            console.log('Order placed successfully.');
            $('.place-order').empty();
            $('.checkout-content').append('<p>Order Placed Successfully!</p>');
            localStorage.removeItem('cartItems');
            updateCartDropdown();

        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('An error occurred: ' + textStatus, errorThrown);
            var response = JSON.parse(jqXHR.responseText);
            if (jqXHR.status === 401) {  // 401 is the status code for Unauthorized
                var errorResponse = JSON.parse(jqXHR.responseText);
                if (errorResponse === "You are not logged in") {
                    window.location.href = 'register.jsp';  // Redirect to the register page
                }
            }
            else if (response.type === 'OutOfStockError') {
                handleOutOfStock(response);
            }
            else if (response.type === 'InsufficientCreditError') {
                handleInsufficientCredit(response);
            }

        }
    });
}

function handleOutOfStock(response) {
    var outOfStockProducts = response.outOfStockProducts;

    outOfStockProducts.forEach(function (productId) {
        // Find the corresponding item in the cart
        var tableItem = $('.order-table li').filter(function () {
            return $(this).data('product-id') == productId;
        });

        tableItem.append('<span style="color: red; font-size: 12px;">out of stock, remove from the cart to proceed.   </span>');
    });
    $('.place-btn').hide();

}

function handleInsufficientCredit(response) {
    var creditAvailable = response.creditAvailable;
    $('.order-table').append('<li class="fw-normal">Insufficient credit, remove some items from the cart to proceed. Credit Available: $' + creditAvailable + '</li>');
    $('.place-btn').hide();
}