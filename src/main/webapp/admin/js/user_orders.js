document.addEventListener('DOMContentLoaded', (event) => {
    var userSelect = document.getElementById('custom-select');
    if(userSelect) {
        userSelect.addEventListener('change', function() {
            var userId = this.value;
            if(userId != "0") {
                fetch(`order_history?userId=${userId}`, {
                    method: 'POST',
                })
                .then(response => response.json())
                .then(data => {
                    const tableBody = document.querySelector('.table tbody');
                    const row = document.createElement('tr');
                    row.innerHTML = `
                                    <td>${product.id}</td>
                                    <td>${product.productDescription}</td>
                                    <td>${product.productPrice}</td>
                                `;
                   tableBody.appendChild(row);
                    document.getElementById('username').value = data.customerName;
                    document.getElementById('email').value = data.email;
                    document.getElementById('creditLimit').value = data.creditLimit;

                    var date = new Date(data.birthday);
                    var year = date.getFullYear();
                    var month = ("0" + (date.getMonth() + 1)).slice(-2);
                    var day = ("0" + date.getDate()).slice(-2);
                    var formattedDate = year + '-' + month + '-' + day;
                    document.getElementById('birthday').value = formattedDate;

                    document.getElementById('job').value = data.job;
                    document.getElementById('country').value = data.country;
                    document.getElementById('address').value = document.getElementById('address').value = data.streetNo + ' ' + data.streetName + ', ' + data.city;
                })
                .catch(error => {
                    console.error('Error:', error);
                });
            }
        });
    }
});