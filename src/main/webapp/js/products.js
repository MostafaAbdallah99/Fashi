window.onload = async function() {
    await fetchProducts();

    document.querySelectorAll('#deleteIcon').forEach((deleteIcon) => {
        deleteIcon.addEventListener('click', async (event) => {
            let row = event.target.closest('tr');

            let productID = row.querySelector('.product-id').textContent;

            let response = await fetch(`admin/product/?productID=${productID}`, {
                method: 'GET',
            });

            if (response.ok) {
                row.remove();
            } else {
                alert("HTTP-Error: " + response.status);
            }
        });
    });

    document.querySelectorAll('.update').forEach(function(icon) {
        icon.addEventListener('click', function(event) {
            event.preventDefault();
            let row = event.target.closest('tr');
            let productID = row.querySelector('.product-id').textContent;
            window.location.href = `admin?action=update&productID=${productID}`;
        });
    });
}

async function fetchProducts() {
    try {
        const response = await fetch('admin/product');
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
                <td>
                  <a id="deleteIcon" class="tm-product-delete-link">
                    <i class="far fa-trash-alt tm-product-delete-icon"></i>
                  </a>
                </td>
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