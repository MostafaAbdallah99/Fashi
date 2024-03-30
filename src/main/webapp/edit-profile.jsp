<%@ page import="persistence.entities.Customer" %>
<%@ page import="persistence.dto.CustomerDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="header.jsp" />
<%
    // Check if the session exists and the "customer" attribute is not null
    if (session == null || session.getAttribute("customer") == null) {
        // If not, redirect the user to the login page
        response.sendRedirect("login.jsp");
        System.out.println("OK");
        return;
    } else {
        // If the session exists and the "customer" attribute is not null, get the customer object
        CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");        // If the customer object is null, redirect the user to the login page
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

    }
%>

<div class="breacrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <a href="./home.jsp"><i class="fa fa-home"></i> Home</a>
                        <span>Profile</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

<div class="container light-style flex-grow-1 container-p-y">
    <div class="card overflow-hidden">
        <div class="row no-gutters row-bordered row-border-light">
            <div class="col-md-3 pt-0">
                <div class="list-group list-group-flush account-settings-links">
                    <a class="list-group-item list-group-item-action active" data-toggle="list"
                       href="#account-general">General</a>
                    <a class="list-group-item list-group-item-action" data-toggle="list"
                       href="#account-change-password">Change password</a>
                    <a class="list-group-item list-group-item-action" data-toggle="list"
                       href="#customer-address">Address</a>
                </div>
            </div>
            <div class="col-md-9">
                <div class="tab-content">
                    <div class="tab-pane fade active show" id="account-general">
                        <hr class="border-light m-0">
                        <div class="card-body">

                            <form action="edit-profile" method="post">
                                <div class="form-group">
                                    <label class="form-label">Username</label>
                                    <input type="text" class="form-control mb-1" name="customerName" value="${sessionScope.customer.customerName()}">
                                    <label class="form-label">${usernameExists}</label>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Job</label>
                                    <input type="text" class="form-control" name="job" value="${sessionScope.customer.job()}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">E-mail</label>
                                    <input type="text" class="form-control mb-1" name="email" value="${sessionScope.customer.email()}">
                                    <label class="form-label">${emailExists}</label>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Date</label>
                                    <input type="date" class="form-control" name="birthday" value="${sessionScope.customer.birthday()}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Credit Limit</label>
                                    <input type="number" step="0.01" class="form-control" name="creditLimit" value="${sessionScope.customer.creditLimit()}">
                                </div>
                                <button type="submit" class="btn btn-primary">Update General</button>
                            </form>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="account-change-password">
                        <div class="card-body pb-2">
                            <form action="password-change" method="post">
                                <div class="form-group">
                                    <label class="form-label">Current Password</label>
                                    <input type="password" class="form-control" name="oldPassword">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">New Password</label>
                                    <input type="password" class="form-control" name="newPassword">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Confirm New Password</label>
                                    <input type="password" class="form-control" name="confirmPassword">
                                </div>
                                <button type="submit" class="btn btn-primary">Change Password</button>
                            </form>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="customer-address">
                        <div class="card-body pb-2">
                            <form action="edit-profile" method="post">
                                <!-- ... other fields ... -->
                                <div class="form-group">
                                    <label class="form-label">City</label>
                                    <input type="text" class="form-control" name="city" value="${sessionScope.customer.city()}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Country</label>
                                    <input type="text" class="form-control" name="country" value="${sessionScope.customer.country()}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Street No</label>
                                    <input type="text" class="form-control" name="streetNo" value="${sessionScope.customer.streetNo()}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Street Name</label>
                                    <input type="text" class="form-control" name="streetName" value="${sessionScope.customer.streetName()}">
                                </div>
                                <button type="submit" class="btn btn-primary">Update Address</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">

</script>

<jsp:include page="footer.jsp" />
</body>

</html>