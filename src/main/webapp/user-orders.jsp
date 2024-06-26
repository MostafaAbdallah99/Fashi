<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Order History</title>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
    />
    <!-- https://fonts.google.com/specimen/Roboto -->
    <link rel="stylesheet" href="css/afontawesome.min.css"/>
    <!-- https://fontawesome.com/ -->
    <link rel="stylesheet" href="css/abootstrap.min.css"/>
    <!-- https://getbootstrap.com/ -->
    <link rel="stylesheet" href="css/templatemo-style.css">
</head>

<body id="reportsPage">
<div class="" id="home">
    <nav class="navbar navbar-expand-xl">
        <div class="container h-100">
            <a class="navbar-brand" href="index.html">
                <h1 class="tm-site-title mb-0">Product Admin</h1>
            </a>
            <button
                    class="navbar-toggler ml-auto mr-0"
                    type="button"
                    data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
            >
                <i class="fas fa-bars tm-nav-icon"></i>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mx-auto h-100">
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="order_history">--%>
<%--                            <i class="fas fa-tachometer-alt"></i> Dashboard--%>
<%--                            <span class="sr-only">(current)</span>--%>
<%--                        </a>--%>
<%--                    </li>--%>

                    <li class="nav-item">
                        <a class="nav-link" href="product">
                            <i class="fas fa-shopping-cart"></i> Products
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link active" href="order_history">
                            <i class="far fa-user"></i> Customers Profiles
                        </a>
                    </li>
<%--                    <li class="nav-item dropdown">--%>
<%--                        <a--%>
<%--                                class="nav-link dropdown-toggle"--%>
<%--                                href="#"--%>
<%--                                id="navbarDropdown"--%>
<%--                                role="button"--%>
<%--                                data-toggle="dropdown"--%>
<%--                                aria-haspopup="true"--%>
<%--                                aria-expanded="false"--%>
<%--                        >--%>
<%--                            <i class="fas fa-cog"></i>--%>
<%--                            <span> Settings <i class="fas fa-angle-down"></i> </span>--%>
<%--                        </a>--%>
<%--                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">--%>
<%--                            <a class="dropdown-item" href="#">Profile</a>--%>
<%--                            <a class="dropdown-item" href="#">Billing</a>--%>
<%--                            <a class="dropdown-item" href="#">Customize</a>--%>
<%--                        </div>--%>
<%--                    </li>--%>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link d-block" href="/iti_ecommerce_app/logout">
                            Admin, <b>Logout</b>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container mt-5">
        <div class="row tm-content-row">
            <div class="col-12 tm-block-col">
                <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                    <h2 class="tm-block-title">List of Users</h2>
                    <p class="text-white">Users</p>
                    <select class="custom-select" id="custom-select">
                        <option value="0">Select account</option>
                        <c:forEach var="user" items="${usersWithOrders}">
                            <option value="${user.id}">${user.customerName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <!-- row -->
        <div class="row tm-content-row" >
            <div class="tm-block-col tm-col-account-settings">
                <div class="tm-bg-primary-dark tm-block tm-block-settings">
                    <h2 class="tm-block-title">Account Settings</h2>
                    <form action="" class="tm-signup-form row">
                        <div class="form-group col-lg-6">
                            <label for="username">Account Name</label>
                            <input
                                    id="username"
                                    name="username"
                                    type="text"
                                    class="form-control validate"
                            />
                        </div>
                        <div class="form-group col-lg-6">
                            <label for="email">Account Email</label>
                            <input
                                    id="email"
                                    name="email"
                                    type="email"
                                    class="form-control validate"
                            />
                        </div>
                        <div class="form-group col-lg-6">
                            <label for="creditLimit">Credit Limit</label>
                            <input
                                    id="creditLimit"
                                    name="creditLimit"
                                    type="number"
                                    class="form-control validate"
                            />
                        </div>
                        <div class="form-group col-lg-6">
                            <label for="birthday">Birthday</label>
                            <input
                                    id="birthday"
                                    name="birthday"
                                    type="text"
                                    class="form-control validate"
                            />
                        </div>
                        <div class="form-group col-lg-6">
                            <label for="job">Job</label>
                            <input
                                    id="job"
                                    name="job"
                                    type="text"
                                    class="form-control validate"
                            />
                        </div>
                        <div class="form-group col-lg-6">
                            <label for="country">Country</label>
                            <input
                                    id="country"
                                    name="country"
                                    type="text"
                                    class="form-control validate"
                            />
                        </div>
                       <div class="form-group col-lg-6">
                            <label for="address">Address</label>
                            <input
                                    id="address"
                                    name="address"
                                    type="text"
                                    class="form-control validate"
                            />
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-12 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
                        <h2 class="tm-block-title">Orders List</h2>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">ORDER NO.</th>
                                    <th scope="col">ORDERED AT</th>
                                    <th scope="col">TOTAL AMOUNT</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
    </div>
    <footer class="tm-footer row tm-mt-small">
        <div class="col-12 font-weight-light">
            <p class="text-center text-white mb-0 px-4 small">
                Copyright &copy; <b>2018</b> All rights reserved.

                Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
            </p>
        </div>
    </footer>
</div>

<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/user_orders.js"></script>
<!-- https://jquery.com/download/ -->
<script src="js/bootstrap.min.js"></script>
<!-- https://getbootstrap.com/ -->
</body>
</html>
