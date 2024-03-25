
var pageName = 'shop'; // replace 'shop' with the name of your page

var navItems = $('.nav-menu ul li');
navItems.removeClass('active');

$('.nav-menu ul li a[href="' + pageName + '"]').parent().addClass('active');
var filtering = false;
var currentPage = 1;



$(document).ready(function () {
    $('#filter-btn').click(function (e) {
        e.preventDefault();

        // Get the selected filters
        var selectedCategory = $('input[name="category"]:checked').next().text();
        var selectedPriceMin = $('#minamount').val();
        var selectedPriceMax = $('#maxamount').val();
        var selectedTag = $('input[name="tag"]:checked').next().text();
        console.log(selectedCategory + "this is the selected category");
        filtering = true;
        filter(selectedCategory, selectedPriceMin, selectedPriceMax, selectedTag, 1);
    });

    var urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('category')) {
        var category = urlParams.get('category');
        filter(category, '$0', '$5000', '', 1);
        filtering = true;
        var specificCategoryInput = $('input[name="category"][value="' + category + '"]');
        specificCategoryInput.prop('checked', true);
    }
    else if(urlParams.has('search')) {
        var search = urlParams.get('search');
        filter('', '$0', '$5000', '', 1, search);
        filtering = true;
    }
    else {
        getProducts(1, 6);
    }



    document.getElementById('clear-filter').addEventListener('click', function () {
        // Clear category filter
        var categoryInputs = document.querySelectorAll('input[name="category"]');
        for (var i = 0; i < categoryInputs.length; i++) {
            categoryInputs[i].checked = false;
        }

        // Clear price range filter
        document.getElementById('minamount').value = '$0';
        document.getElementById('maxamount').value = '$5000';

        var rangeSlider = $(".price-range"),
        		minamount = $("#minamount"),
        		maxamount = $("#maxamount"),
        		minPrice = rangeSlider.data('min'),
        		maxPrice = rangeSlider.data('max');
        	    rangeSlider.slider({
        		range: true,
        		min: minPrice,
                max: maxPrice,
        		values: [minPrice, maxPrice],
        		slide: function (event, ui) {
        			minamount.val('$' + ui.values[0]);
        			maxamount.val('$' + ui.values[1]);
        		}
        	});
        	minamount.val('$' + rangeSlider.slider("values", 0));
            maxamount.val('$' + rangeSlider.slider("values", 1));

        // Clear tag filter
        var tagInputs = document.querySelectorAll('input[name="tag"]');
        for (var i = 0; i < tagInputs.length; i++) {
            tagInputs[i].checked = false;
        }

        // Reload products
        //        filter('', 0, 5000, '');
        filtering = false;
        getProducts(1, 6);
    });
});






//loading products on html
function loadProducts(data) {
    var productHTML = '';
    var count = 0;
    console.log(data);
    length = data.products.length;
    $('#NumberOfProducts').text("Show 01- " + length + " Of " + (data.totalPagesCount.totalCount) + " Products");
    $.each(data.products, function (key, item) {
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
        productHTML += '<ul><li class="w-icon active"><a href="#" onclick="handleBagIconClick(' + item.id + '); return false;"><i class="icon_bag_alt"></i></a></li>';
        productHTML += '<li class="quick-view"><a href="product?product=prdct-' + item.id + '" id="quick-view-' + item.id + '">+ Quick View</a></li>';
        console.log(item.id);
        productHTML += '<li class="w-icon"><a href="#"><i class="fa fa-random"></i></a></li></ul>';
        productHTML += '</div>';
        productHTML += '<div class="pi-text">';
        productHTML += '<div class="catagory-name">' + tagName + '</div>';
        productHTML += '<a href="#"><h5>' + item.productName + '</h5></a>';
        if (item.stockQuantity <= 0) {
            productHTML += '<div class="product-price" style="color:red;">Out of Stock</div>';
        }
        else {
            productHTML += '<div class="product-price">$' + item.productPrice + '</div>';
        }
        productHTML += '</div></div></div>';
        count++;
    });
    $('.product-list .row').html(productHTML);
}







function filter(selectedCategory, selectedPriceMin, selectedPriceMax, selectedTag, pages, searchQuery) {
    $.ajax({
        url: 'shop', // replace with the path to your JSON file
        type: 'POST',
        dataType: 'json',
        data: {
            category: selectedCategory,
            priceMin: selectedPriceMin,
            priceMax: selectedPriceMax,
            tag: selectedTag,
            page: pages,
            size: 6,
            searchQuery: searchQuery

        },
        success: function (data) {
            loadProducts(data);
            updatePagination(data.totalPagesCount.totalPages);
        },
        error: function (error) {
            console.log('Error in filtering:  ', error);
        }
    });
}


function handleBagIconClick(productId) {
    var quantity = 1;
    product_id = "prdct-" + productId;
    addToCart(product_id, quantity);
}

function getProducts(pages, n) {
    $.ajax({
        url: 'shop',
        type: 'POST',
        dataType: 'json',
        data: {
            json: 'true',
            page: pages,
            size: n
        },
        success: function (products) {
            loadProducts(products);
            updatePagination(products.totalPagesCount.totalPages);
        },
        error: function (error) {
            console.log('Error: ', error);

        }
    });
}


function updatePagination(totalPages) {
    var paginationDiv = $('.pagination');
    paginationDiv.empty(); // clear the existing pagination links

    // Add the 'Previous' link
    paginationDiv.append('<a href="#">&laquo; Previous</a>');

    // Add the page links
    for (var i = 1; i <= totalPages; i++) {
        if (i === currentPage) {
            paginationDiv.append('<a href="#" class="active">' + i + '</a>');
        } else {
            paginationDiv.append('<a href="#">' + i + '</a>');
        }
    }

    // Add the 'Next' link
    paginationDiv.append('<a href="#">Next &raquo;</a>');

    // Add click event listener to the page links
    paginationDiv.find('a').click(function (event) {
        event.preventDefault();
        var pageNumberText = $(this).text();
        var selectedCategory = $('input[name="category"]:checked').next().text();
        var selectedPriceMin = $('#minamount').val();
        var selectedPriceMax = $('#maxamount').val();
        var selectedTag = $('input[name="tag"]:checked').next().text();
        if (pageNumberText === 'Next »') {
            if (currentPage < totalPages) {
                currentPage++;
                if (filtering) {
                    filter(selectedCategory, selectedPriceMin, selectedPriceMax, selectedTag, currentPage);
                }
                else {
                    getProducts(currentPage, 6);
                }
            }
        } else if (pageNumberText === '« Previous') {
            if (currentPage > 1) {
                currentPage--;
                if (filtering) {
                    filter(selectedCategory, selectedPriceMin, selectedPriceMax, selectedTag, currentPage);
                }
                else {
                    getProducts(currentPage, 6);
                }
            }
        } else if (!isNaN(pageNumberText)) { // Check if the text is numeric
            var pageNumber = parseInt(pageNumberText);
            if (filtering) {
                filter(selectedCategory, selectedPriceMin, selectedPriceMax, selectedTag, currentPage);
            }
            else {
                getProducts(currentPage, 6);
            }
            currentPage = pageNumber;
        }
    });
}