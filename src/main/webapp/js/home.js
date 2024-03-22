$(document).ready(function () {
    loadCategory('Women');
    loadCategory('Men');
    if(getCookie('user_login') === 'true' && localStorage.getItem('cart_retrieved') !== 'true') {
        getCartItems();
    } else {
        // User is not logged in, do not retrieve the cart
    }

});

function loadCategory(category) {
    $.ajax({
        url: 'shop',
        type: 'POST',
        dataType: 'json',
        data: {
            category: category,
            priceMin: '$0',
            priceMax: '$5000',
            tag: '',
        },
        success: function (data) {
            loadProducts(data, category);
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });
}



function loadProducts(data, category) {
    console.log(data);
    var productHTML = '';
    $.each(data, function (key, item) {
        productHTML += '<div class="product-item">';
        productHTML += '<div class="pi-pic">';
        productHTML += '<img src="' + item.productImage + '" alt="">';
        productHTML += '<div class="icon"><i class="icon_heart_alt"></i></div>';
        productHTML += '<ul><li class="w-icon active"><a href="#"><i class="icon_bag_alt"></i></a></li>';
        productHTML += '<li class="quick-view"><a href="#">+ Quick View</a></li>';
        productHTML += '<li class="w-icon"><a href="#"><i class="fa fa-random"></i></a></li></ul>';
        productHTML += '</div>';
        productHTML += '<div class="pi-text">';
        productHTML += '<div class="catagory-name">' + item.tag.tagName + '</div>';
        productHTML += '<a href="#"><h5>' + item.productName + '</h5></a>';
        productHTML += '<div class="product-price">$' + item.productPrice + '</div>';
        productHTML += '</div></div>';
    });
    $('#' + category).html(productHTML);
    // Initialize Owl Carousel after adding the items
    $('#' + category).trigger('destroy.owl.carousel');
    console.log("Owl Carousel");
    setTimeout(function () {
        $('#' + category).owlCarousel({
            loop: true,
            margin: 25,
            nav: true,
            items: 4,
            dots: true,
            navText: ['<i class="ti-angle-left"></i>', '<i class="ti-angle-right"></i>'],
            smartSpeed: 1200,
            autoHeight: false,
            autoplay: true,
            responsive: {
                0: {
                    items: 1,
                },
                576: {
                    items: 2,
                },
                992: {
                    items: 2,
                },
                1200: {
                    items: 3,
                }
            }
        });
    }, 500);

}

