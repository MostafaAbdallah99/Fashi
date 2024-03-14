    $.ajax({
        url: 'shop',
        type: 'POST',
        dataType: 'json',
        data: {
            json: 'true'
        },
        success: loadProducts,
        error: function(error) {
            console.log('Error: ', error);

        }
    });
$(document).ready(function() {
$('#filter-btn').click(function(e) {
        e.preventDefault();

        // Get the selected filters
        var selectedCategory = $('input[name="category"]:checked').next().text();
        var selectedPriceMin = $('#minamount').val();
        var selectedPriceMax = $('#maxamount').val();
        var selectedTag = $('input[name="tag"]:checked').next().text();
        console.log(selectedCategory+"this is the selected category");
        filter(selectedCategory, selectedPriceMin, selectedPriceMax, selectedTag);
        });
    $.ajax({
        url: 'shop',
        type: 'POST',
        dataType: 'json',
        data: {
            json: 'true'
        },
        success: loadProducts,

        error: function(error) {
            console.log('Error: ', error);

        }
    });
        $('.quick-view').click(function(e) {
            e.preventDefault(); // Prevent the default action (navigation)
            var productId = $(this).data('product-id'); // Get the product ID from a data attribute
            $.post('product', { productId: productId }, function(response) {
                console.log(response);
            });
        });
    document.getElementById('clear-filter').addEventListener('click', function() {
        // Clear category filter
        var categoryInputs = document.querySelectorAll('input[name="category"]');
        for (var i = 0; i < categoryInputs.length; i++) {
            categoryInputs[i].checked = false;
        }

        // Clear price range filter
        document.getElementById('minamount').value = '$0';
        document.getElementById('maxamount').value = '$5000';

        // Clear tag filter
        var tagInputs = document.querySelectorAll('input[name="tag"]');
        for (var i = 0; i < tagInputs.length; i++) {
            tagInputs[i].checked = false;
        }

        // Reload products
        filter('', 0, 5000, '');

    });
});

function loadProducts(data) {
    var productHTML = '';
    var count = 0;
    console.log(data);
    length = data.length;
    $('#NumberOfProducts').text("Show 01- " + length+" Of "+length+" Products");
    $.each(data, function(key, item) {
        console.log(item);
        console.log(count);
        var tagName = item.tag ? item.tag.tagName : '';
        var categoryName = item.category ? item.category.categoryName : '';
        productHTML += '<div class="col-lg-4 col-sm-6">';
        productHTML += '<div class="product-item">';
        productHTML += '<div class="pi-pic">';
        productHTML += '<img src="' + item.productImage + '" alt="">';
        console.log(item.productImage);
        productHTML += '<div class="icon"><i class="icon_heart_alt"></i></div>';
        productHTML += '<ul><li class="w-icon active"><a href="#"><i class="icon_bag_alt"></i></a></li>';
        productHTML += '<li class="quick-view"><a href="" data-product-id="' + item.id + '">+ Quick View</a></li>';
        productHTML += '<li class="w-icon"><a href="#"><i class="fa fa-random"></i></a></li></ul>';
        productHTML += '</div>';
        productHTML += '<div class="pi-text">';
        productHTML += '<div class="catagory-name">' + tagName+ '</div>';
        productHTML += '<a href="#"><h5>' + item.productName + '</h5></a>';
        productHTML += '<div class="product-price">$' + item.productPrice + '</div>';
        productHTML += '</div></div></div>';
        count++;
    });
    $('.product-list .row').html(productHTML);
}

function filter(selectedCategory, selectedPriceMin, selectedPriceMax, selectedTag) {
    $.ajax({
        url: 'shop', // replace with the path to your JSON file
        type: 'POST',
        dataType: 'json',
        data: {
            category: selectedCategory,
            priceMin: selectedPriceMin,
            priceMax: selectedPriceMax,
            tag: selectedTag

        },
        success: loadProducts,
        error: function(error) {
            console.log('Error in filtering:  ', error);
        }
    });
}