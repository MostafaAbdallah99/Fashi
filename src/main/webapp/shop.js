$(document).ready(function() {
    $.ajax({
        url: 'shop', // replace with the path to your JSON file
        type: 'POST',
        dataType: 'json',
        data: {
            json: 'true'
        },
        success: function(data) {
            var productHTML = '';
            var count =0;
            console.log(data); // add this line
            $.each(data.products, function(key, item) {
                console.log(item); // add this line
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
                productHTML += '<li class="quick-view"><a href="#">+ Quick View</a></li>';
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

        },
        error: function(error) {
            console.log('Error: ', error);
        }
    });
});