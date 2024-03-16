window.onload = async function() {
    let params = new URLSearchParams(window.location.search);
    let productID = params.get('productID');
    let action = params.get('action');
    let form = document.getElementById('updateform');
    let actionValue = "update";
    form.action = `admin/product?action=${actionValue}&productID=${productID}`;
    await fetchProduct(productID, action);
}


async function fetchProduct(productID, action) {
    try {
        fetch(`admin/product/?action=${action}&productID=${productID}`)
        .then(response => response.json())
        .then(product => {
            document.getElementById('productName').value = product.productName;
            document.getElementById('productDescription').value = product.productDescription;
            document.getElementById('productPrice').value = product.productPrice;
            document.getElementById('stockQuantity').value = product.stockQuantity;
            document.getElementById('category').value = product.category.categoryName;
            document.getElementById('tag').value = product.tag.tagName;
            document.getElementById('productSize').value = product.productSize;
            document.getElementById('previewImage').src = product.productImage;
        });
    } catch (error) {
        console.error('Error:', error);
    }
}


