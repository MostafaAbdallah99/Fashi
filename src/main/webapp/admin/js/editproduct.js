window.onload = async function() {
    let params = new URLSearchParams(window.location.search);
    let productID = params.get('productID');
    let form = document.getElementById('updateform');
    form.action = `updateProduct?productID=${productID}`;
    await fetchProduct(productID);
}

async function fetchProduct(productID) {
    try {
        let response = await fetch(`updateProduct?action=fetchProduct&productID=${productID}`, { method: 'POST' });

        let contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
            let product = await response.json();
            document.getElementById('productName').value = product.productName;
            document.getElementById('productDescription').value = product.productDescription;
            document.getElementById('productPrice').value = product.productPrice;
            document.getElementById('stockQuantity').value = product.stockQuantity;
            document.getElementById('category').value = product.category.categoryName;
            document.getElementById('tag').value = product.tag.tagName;
            document.getElementById('productSize').value = product.productSize;
            document.getElementById('previewImage').src = product.productImage;
            if(product.isDeleted == 1){
                document.getElementById('isDeleted').checked = true;
            } else {
                document.getElementById('isDeleted').checked = false;
            }
        } else {
            window.location.href = 'product';
        }
    } catch (error) {
        console.log('Error:', error);
    }
}

