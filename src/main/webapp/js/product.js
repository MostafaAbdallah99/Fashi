
$(document).ready(function () {
    // Get the product ID from the URL parameters
    var urlParams = new URLSearchParams(window.location.search);
    var productId = urlParams.get('product');
    console.log(productId);

    // Make the AJAX call to fetch the product data
    $.ajax({
        url: 'product',
        type: 'POST',
        dataType: 'json',
        data: {
            productId: productId
        },
        success: function (product) {
            console.log(product);
            showProduct(product);

        },
        error: function (error) {
            console.log('Error: ', error);
        }
    });

    // Add the product to the cart
    $('.pd-cart').on('click', function (event) {
        // Prevent the default action
        event.preventDefault();

        // Get the product ID
        var productId = urlParams.get('product');
        var quantity = $('.pro-qty input').val();
        console.log(productId);
        console.log(quantity);

        // Call the new function
        addToCart(productId, quantity);
    });

});

function showProduct(product) {
    // Set the product image
    $('.product-big-img').attr('src', product.productImage);
    $('.pt').attr('data-imgbigurl', product.productImage);
    $('.pt img').attr('src', product.productImage);
    $('.pd-title span').text(product.categoryName);

    $('.product-pic-zoom').trigger('zoom.destroy'); // remove the zoom effect
    $('.product-pic-zoom').zoom(); // add the zoom effect back

    // Set the product title
    $('.pd-title h3').text(product.productName);

    if (product.stockQuantity <= 0) {
        $('.pd-stock').text('Out of stock');
        $('.pd-desc h4').html("<span style='color:red;'>Out of stock</span>");
    } else {
        $('.pd-desc h4').text('$' + product.productPrice);
    }



    // Set the product description
    $('.pd-desc p').text(product.productDescription);
    $('#tab-1 .product-content .row .col-lg-13 h5:contains("Introduction")').next('p').text(product.productDescription);


    $('.pd-size-choose .sc-item label').text(product.productSize);

    // Set the product quantity in cart ? if the user already has the product in the cart this might need some work
    //    $('.pro-qty input').val(product.stockQuantity);

    // Set the product category
    $('.pd-tags li:first-child').text('CATEGORIES: ' + product.categoryName);

    // Set the product tags
    $('.pd-tags li:last-child').text('TAGS: ' + product.tag.tagName);


    $('.p-price').text('$' + product.productPrice);
    $('.p-stock').text(product.stockQuantity + ' in stock');
    //    $('.p-weight').text(product.weight);
    $('.p-size').text(product.productSize);
    //    $('.p-code').text(product.sku);

}