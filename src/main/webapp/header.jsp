<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Fashi Template">
        <meta name="keywords" content="Fashi, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Fashi | Where Fashion Meets Convenience!</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap"
            rel="stylesheet">

        <!-- Css Styles -->

        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/themify-icons.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>

    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Header Section Begin -->
        <header class="header-section">
            <div class="header-top">



                <div class="container">
                    <div class="ht-left">
                        <div class="mail-service">
                            <i class="fa fa-globe"> Where Fashion Meets Convenience</i>
                        </div>

                    </div>
                    <div class="ht-right">
                        <a href="./login.jsp" class="login-panel" id="login-link"><i class="fa fa-user"></i><span>Login</span></a>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="inner-header">
                    <div class="row">
                        <div class="col-lg-2 col-md-2">
                            <div class="logo">
                                <a href="home.jsp">
                                    <img src="img/logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-7 col-md-7">
                            <div class="advanced-search">
                            <button type="button" class="category-btn">All Categories</button>
                                <div class="input-group">
                                    <input type="text" id="advanced_search" placeholder="What do you need?">
                                    <button type="button"><i class="ti-search"></i></button>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 text-right col-md-3">
                            <ul class="nav-right">
                                <li class="cart-icon">
                                    <a href="shopping-cart.jsp">
                                        <i class="icon_bag_alt"></i>
                                        <span id="quant">3</span>
                                    </a>
                                    <div class="cart-hover">
                                        <div class="select-items">
                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td class="si-pic"><img src="img/select-product-1.jpg" alt="">
                                                        </td>
                                                        <td class="si-text">
                                                            <div class="product-selected">
                                                                <p>$60.00 x 1</p>
                                                                <h6>Kabino Bedside Table</h6>
                                                            </div>
                                                        </td>
                                                        <td class="si-close">
                                                            <i class="ti-close"></i>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="si-pic"><img src="img/select-product-2.jpg" alt="">
                                                        </td>
                                                        <td class="si-text">
                                                            <div class="product-selected">
                                                                <p>$60.00 x 1</p>
                                                                <h6>Kabino Bedside Table</h6>
                                                            </div>
                                                        </td>
                                                        <td class="si-close">
                                                            <i class="ti-close"></i>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="select-total">
                                            <span>total:</span>
                                            <h5>$120.00</h5>
                                        </div>
                                        <div class="select-button">
                                            <a href="shopping-cart.jsp" class="primary-btn view-card">VIEW CART</a>
                                            <a href="./check-out.jsp" class="primary-btn checkout-btn">CHECK OUT</a>
                                        </div>
                                    </div>
                                </li>
                                <li class="cart-price">$150.00</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="nav-item">
                <div class="container">
                    <div class="nav-depart">
                        <div class="depart-btn">
                            <i class="ti-menu"></i>
                            <span>All departments</span>
                            <ul class="depart-hover">
                                <li class="active"><a href="./shop.jsp?category=Women">Women's Clothing</a></li>
                                <li><a href="./shop.jsp?category=Men">Men’s Clothing</a></li>
                                <li><a href="./shop.jsp?category=Kids">Kid's Clothing</a></li>
                            </ul>
                        </div>
                    </div>
                    <nav class="nav-menu mobile-menu">
                        <ul>
                            <li class="active"><a href="./home.jsp">Home</a></li>
                            <li><a href="shop">Shop</a></li>
                            <li><a href="./contact.jsp">Contact Us</a></li>
                            <li><a href="edit-profile">Profile</a>
                            </li>
                            <li><a href="./faq.jsp">FAQ</a></li>
                        </ul>
                    </nav>
                    <div id="mobile-menu-wrap"></div>
                </div>
            </div>
        </header>
        <div id="popup" style="display: none;">Item added to cart Successfully!</div>
        <!-- Header End -->