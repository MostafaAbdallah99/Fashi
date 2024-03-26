<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="header.jsp" />

<!-- Header End -->

<!-- Breadcrumb Section Begin -->
<div class="breacrumb-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb-text">
                    <a href="./home.jsp"><i class="fa fa-home"></i> Home</a>
                    <span>Login</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb Form Section Begin -->

<!-- Register Section Begin -->
<div class="register-login-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <div class="login-form">
                    <h2>Login</h2>

                    <form action="login" method="post">
                        <div class="group-input">
                            <label for="email">Email address *</label>
                            <input type="text" id="email" name="email">
                        </div>
                        <div class="group-input">
                            <label for="password">Password *</label>
                            <input type="password" id="password" name="password">
                        </div>
                        <div class="group-input gi-check">
                            <div class="gi-more">
                                <label for="save-pass">
                                    Save Password
                                    <input type="checkbox" id="save-pass">
                                    <span class="checkmark"></span>
                                </label>
                                <a href="./reset-password-request.jsp" class="forget-pass">Forgot your Password ?</a>
                            </div>
                        </div>
                        <button type="submit" class="site-btn login-btn">Sign In</button>
                    </form>
                    <c:if test="${param.message == 'wrong'}">
                        <div id="errorMessage" style="color: red; text-align: center; font-weight: bold; font-size: medium">Wrong credentials. Please try again.</div>
                    </c:if>
                    <div class="switch-login">
                        <a href="./register.jsp" class="or-login">Or Create An Account</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Register Form Section End -->

<!-- Footer Section Begin -->
<jsp:include page="footer.jsp" />
</body>

</html>