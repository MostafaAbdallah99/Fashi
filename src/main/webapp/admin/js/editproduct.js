window.onload = async function() {
    let params = new URLSearchParams(window.location.search);
    let productID = params.get('productID');
    let form = document.getElementById('updateform');
    form.action = `updateProduct?productID=${productID}`;
    await fetchProduct(productID);
}


async function fetchProduct(productID) {
    try {
        fetch(`updateProduct?action=fetchProduct&productID=${productID}`,
       {
            method: 'POST',
        }).then(response => response.json())
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


