window.onload = async function() {
    await fetchProducts();

 /*   document.querySelectorAll('#deleteIcon').forEach((deleteIcon) => {
        deleteIcon.addEventListener('click', async (event) => {
            let row = event.target.closest('tr');

            let productID = row.querySelector('.product-id').textContent;

            let response = await fetch(`product?action=deleteProduct&productID=${productID}`, {
                method: 'POST',
            });

            if (response.ok) {
                row.remove();
            } else {
                alert("Error deleting product");
            }
        });
    });
*/
    document.querySelectorAll('.update').forEach(function(icon) {
        icon.addEventListener('click', function(event) {
            event.preventDefault();
            let row = event.target.closest('tr');
            let productID = row.querySelector('.product-id').textContent;
            window.location.href = `updateProduct?productID=${productID}`;
        });
    });
}

async function fetchProducts() {
    try {
        let response = await fetch(`product`, {
                method: 'POST',
        });
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const products = await response.json();
        const tableBody = document.querySelector('.tm-product-table tbody');
        products.forEach(product => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td class="product-id">${product.id}</td>
                <td class="tm-product-name">${product.productName}</td>
                <td>${product.productDescription}</td>
                <td>${product.productPrice}</td>
                <td>${product.stockQuantity}</td>
                <td>${product.categoryName}</td>
                <td>${product.tag.tagName}</td>
                <td>${product.productSize}</td>
                <td>${product.isDeleted}</td>
                <td>
                    <a id="updateIcon" class="tm-product-delete-link update">
                      <i class="fas fa-pen tm-product-delete-icon"></i>
                    </a>
                </td>
            `;
            tableBody.appendChild(row);

        });
    } catch (error) {
        console.error('Error:', error);
    }
}