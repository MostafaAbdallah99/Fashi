<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <jsp:include page="header.jsp" />
    <!-- Header End -->

    <!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <a href="./home.jsp"><i class="fa fa-home"></i> Home</a>
                        <span>Contact</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Section Begin -->

    <!-- Map Section Begin -->
    <div class="map spad">
        <div class="container">
            <div class="map-inner">
<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d13811.221610388655!2d31.0210709!3d30.071112!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14585b979b7d1dd9%3A0x88f29d027c44f959!2sInformation%20Technology%20Institute!5e0!3m2!1sen!2seg!4v1711473821310!5m2!1sen!2seg" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                <div class="icon">
                    <i class="fa fa-map-marker"></i>
                </div>
            </div>
        </div>
    </div>
    <!-- Map Section Begin -->

    <!-- Contact Section Begin -->
    <section class="contact-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-5">
<div class="contact-title">
    <h4>Contact Us</h4>
    <p>Welcome to our online clothing store! We offer a wide range of fashionable and affordable clothing for all ages and sizes.
     Our team is dedicated to providing you with the best shopping experience.
    If you have any questions, suggestions, or concerns, please don't hesitate to reach out to us. We value your feedback and are here to assist you.</p>
</div>
                    <div class="contact-widget">
                        <div class="cw-item">
                            <div class="ci-icon">
                                <i class="ti-location-pin"></i>
                            </div>
                            <div class="ci-text">
                                <span>Address:</span>
                                <p>Smart Villages Company, by Cairo / Alexandria Desert, B148 KM 28, Kerdasa, Giza Governorate 12563</p>
                            </div>
                        </div>
                        <div class="cw-item">
                            <div class="ci-icon">
                                <i class="ti-mobile"></i>
                            </div>
                            <div class="ci-text">
                                <span>Phone:</span>
                                <p>+65 11.188.888</p>
                            </div>
                        </div>
                        <div class="cw-item">
                            <div class="ci-icon">
                                <i class="ti-email"></i>
                            </div>
                            <div class="ci-text">
                                <span>Email:</span>
                                <p>itijets@gmail.com</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 offset-lg-1">
                    <div class="contact-form">
                        <div class="leave-comment">
                            <h4>Leave A Comment</h4>
                            <p>Our staff will call back later and answer your questions.</p>
                            <form action="email" method="post" class="comment-form">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <input type="text" placeholder="Your name" name="name">
                                    </div>
                                    <div class="col-lg-6">
                                        <input type="text" placeholder="Your email" name="email" >
                                    </div>
                                    <div class="col-lg-12">
                                        <textarea placeholder="Your message" name="message"></textarea>
                                        <button type="submit" class="site-btn">Send message</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Contact Section End -->

    <!-- Footer Section Begin -->
    <jsp:include page="footer.jsp" />
    <script>
        var pageName = './contact.jsp';

        var navItems = $('.nav-menu ul li');
        navItems.removeClass('active');

        $('.nav-menu ul li a[href="' + pageName + '"]').parent().addClass('active');
    </script>
    <\body>
        <\html>