<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />

<div class="breacrumb-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb-text">
                    <a href="#"><i class="fa fa-home"></i> Home</a>
                    <span>Register</span>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="register-login-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <div class="register-form">
                    <h2>Register</h2>
                    <form action="register" method="post">
                        <div class="group-input">
                            <div class="group-input">
                                <label for="customerName">User Name *</label>
                                <input type="text" id="customerName" name="customerName">
                            </div>

                            <label for="username">Email address *</label>
                            <input type="text" id="username" name="email">
                        </div>
                        <div class="group-input">
                            <label for="pass">Password *</label>
                            <input type="password" id="pass" name="password">
                        </div>
                        <div class="group-input">
                            <label for="con-pass">Confirm Password *</label>
                            <input type="password" id="con-pass" name="confirmPassword">
                        </div>
                        <div class="group-input">
                            <label for="birthday">Birthday *</label>
                            <input type="date" id="birthday" name="birthday">
                        </div>
                        <div class="group-input">
                            <label for="job">Job *</label>
                            <input type="text" id="job" name="job">
                        </div>
                        <div class="group-input">
                            <label for="city">City *</label>
                            <input type="text" id="city" name="city">
                        </div>
                        <div class="group-input">
                            <label for="country">Country *</label>
                            <input type="text" id="country" name="country">
                        </div>
                        <div class="group-input">
                            <label for="streetNo">Street Number *</label>
                            <input type="text" id="streetNo" name="streetNo">
                        </div>
                        <div class="group-input">
                            <label for="streetName">Street Name *</label>
                            <input type="text" id="streetName" name="streetName">
                        </div>
                        <div class="group-input">
                            <label for="interests">Interests *</label>
                            <input type="text" id="interests" name="interests">
                        </div>
                        <button type="submit" class="site-btn register-btn">REGISTER</button>
                    </form>
                    <div class="switch-login">
                        <a href="login.jsp" class="or-login">Or Login</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Register Form Section End -->

<!-- Footer Section Begin -->
<jsp:include page="footer.jsp"/>
</body>
</html>