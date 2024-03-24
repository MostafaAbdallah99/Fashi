<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="header.jsp" />

<!-- Header End -->

<!-- Breadcrumb Section Begin -->
<%--<div class="breacrumb-section">--%>
<%--    <div class="container">--%>
<%--        <div class="row">--%>
<%--            <div class="col-lg-12">--%>
<%--                <div class="breadcrumb-text">--%>
<%--                    <a href="#"><i class="fa fa-home"></i> Home</a>--%>
<%--                    <span>Login</span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<!-- Breadcrumb Form Section Begin -->

<!-- Register Section Begin -->
<div class="register-login-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <div class="login-form">
                    <h2>Reset Password</h2>
                    <form action="reset-password" method="post">
                          <input type="hidden" id="token" name="token" value="${param.token}">
                          <div class="group-input">
                            <label for="newPassword">New Password *</label>
                            <input type="password" id="newPassword" name="newPassword" required>
                         </div>
                        <button type="submit" class="site-btn login-btn">Reset Password</button>
                    </form>
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