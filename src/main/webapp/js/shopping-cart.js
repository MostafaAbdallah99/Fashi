$(document).ready(function() {
    // Retrieve the cart items from local storage
    var cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];

    // Get the table body where the cart items will be displayed
    var tableBody = $('.cart-table tbody');

    // Clear the table body
    tableBody.empty();

    // Iterate over the cart items
    $.each(cartItems, function(index, item) {
        // Create a new table row
        var row = $('<tr></tr>');

        // Create and append the table data cells
        var imgCell = $('<td class="cart-pic"><img src="' + item.product.productImage + '" alt=""></td>');
        row.append(imgCell);

        var nameCell = $('<td class="cart-title"><h5>' + item.product.productName + '</h5></td>');
        row.append(nameCell);

        var priceCell = $('<td class="p-price">$' + item.product.productPrice + '</td>');
        row.append(priceCell);

        var quantityCell = $('<td class="qua-col"><div class="quantity"><div class="pro-qty"><span class="dec qtybtn">-</span><input type="text" value="' + item.quantity + '"><span class="inc qtybtn">+</span></div></div></td>');
        row.append(quantityCell);

        var totalCell = $('<td class="total-price">$' + (item.product.productPrice * item.quantity) + '</td>');
        row.append(totalCell);

        var closeCell = $('<td class="close-td"><i class="ti-close"></i></td>');
        row.append(closeCell);

        // Append the row to the table body
        tableBody.append(row);
    });
});