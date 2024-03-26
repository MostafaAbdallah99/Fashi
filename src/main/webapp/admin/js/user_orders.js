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
                            data.orders.forEach(order => {
                                const row = document.createElement('tr');
                                row.innerHTML = `
                                    <td>${order.orderId}</td>
                                    <td>${order.orderedAt}</td>
                                    <td>${order.totalOrderAmount}</td>
                                `;
                                tableBody.appendChild(row);
                                });

                    document.getElementById('username').value = data.customer.customerName;
                    document.getElementById('email').value = data.customer.email;
                    document.getElementById('creditLimit').value = data.customer.creditLimit;

                    var date = new Date(data.customer.birthday);
                    var year = date.getFullYear();
                    var month = ("0" + (date.getMonth() + 1)).slice(-2);
                    var day = ("0" + date.getDate()).slice(-2);
                    var formattedDate = year + '-' + month + '-' + day;
                    document.getElementById('birthday').value = formattedDate;

                    document.getElementById('job').value = data.customer.job;
                    document.getElementById('country').value = data.customer.country;
                    document.getElementById('address').value = document.getElementById('address').value = data.customer.streetNo + ' ' + data.customer.streetName + ', ' + data.customer.city;
                })
                .catch(error => {
                    console.error('Error:', error);
                });
            }
        });
    }
});