$(document).ready(function () {
if (getCookie('user_login') === 'true') {
    var cartItems = JSON.parse(sessionStorage.getItem('cartItems')) || [];
}
else {
    var cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
}
    // Get the table body where the cart items will be displayed
    var tableBody = $('.cart-table tbody');

    // Clear the table body
    tableBody.empty();

    var totalPrice = 0.0;
    $.each(cartItems, function (index, item) {
        // Create a new table row
        var row = $('<tr></tr>');
        row.data('product-id', item.product.id);
        console.log(item.product.id + " " + row.data('product-id') + "_________");

        // Create and append the table data cells
        var imgCell = $('<td class="cart-pic"><img src="' + item.product.productImage + '" alt=""></td>');
        row.append(imgCell);

        var nameCell = $('<td class="cart-title"><h5>' + item.product.productName + '</h5></td>');
        row.append(nameCell);

        var priceCell = $('<td class="p-price">$' + item.product.productPrice + '</td>');
        row.append(priceCell);
        totalPrice += item.product.productPrice * item.quantity;

        var quantityCell = $('<td class="qua-col"><div class="quantity"><div class="pro-qty"><span class="dec qtybtn">-</span><input type="text" value="' + item.quantity + '"><span class="inc qtybtn">+</span></div></div></td>');
        row.append(quantityCell);

        var totalCell = $('<td class="total-price">$' + (item.product.productPrice * item.quantity) + '</td>');
        row.append(totalCell);

        var closeCell = $('<td class="close-td"><i class="ti-close"></i></td>');
        row.append(closeCell);

        // Append the row to the table body
        tableBody.append(row);


    });
    $(".cart-total span").text('$' + totalPrice.toFixed(2));

    // If you want to display the same total cost in the element with class `subtotal span`
    $(".subtotal span").text('$' + totalPrice.toFixed(2));

    // Add click event handler for the "X" sign
    $('.ti-close').on('click', function () {
        // Get the table row for the product
        var row = $(this).closest('tr');

        // Get the product ID from a data attribute
        var productId = row.data('product-id');
        console.log(productId + " " + row);

        // Call the removeProduct function
        removeProduct(productId, row);
    });



    var proQty = $('.pro-qty');
    proQty.on('click', '.qtybtn', function () {
        var $button = $(this);
        var oldValue = $button.parent().find('input').val();
        var newVal;
        // Get the product ID from a data attribute
        var productId = $button.closest('tr').data('product-id');
        var priceCell = $button.closest('tr').find('.p-price');
        var totalCell = $button.closest('tr').find('.total-price');
        var cartTotal = $('.cart-total span');
        var subtotal = $('.subtotal span');
        var totalPrice = parseFloat(cartTotal.text().replace('$', ''));

        if ($button.hasClass('inc')) {
            newVal = parseFloat(oldValue) + 1;
            $button.parent().find('input').val(newVal);
            addToCart("prdct-" + productId, 1, false);
            var price = parseFloat(priceCell.text().replace('$', ''));
            totalCell.text('$' + (price * newVal).toFixed(2));
            totalPrice += price;
            cartTotal.text('$' + totalPrice.toFixed(2));
            subtotal.text('$' + totalPrice.toFixed(2));

        } else {
            // Don't allow decrementing below zero
            if (oldValue > 0) {
                newVal = parseFloat(oldValue) - 1;
                $button.parent().find('input').val(newVal);
                addToCart("prdct-" + productId, -1, false);
                var price = parseFloat(priceCell.text().replace('$', ''));
                totalCell.text('$' + (price * newVal).toFixed(2));
                totalPrice -= price;
                cartTotal.text('$' + totalPrice.toFixed(2));
                subtotal.text('$' + totalPrice.toFixed(2));


            } else {
                var row = $(this).closest('tr');
                newVal = 0;
                totalPrice -= parseFloat(totalCell.text().replace('$', ''));
                removeProduct(productId, row);

                cartTotal.text('$' + totalPrice.toFixed(2));

            }


        }


    });

});
