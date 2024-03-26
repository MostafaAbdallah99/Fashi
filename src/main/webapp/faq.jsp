<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <jsp:include page="header.jsp" />
    <!-- Header End -->
    <!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <a href="#"><i class="fa fa-home"></i> Home</a>
                        <span>FAQs</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Section Begin -->

    <!-- Faq Section Begin -->
    <div class="faq-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="faq-accordin">
                        <div class="accordion" id="accordionExample">
                            <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseFour">
                                        What is your return policy?
                                    </a>
                                </div>
                                <div id="collapseFour" class="collapse" data-parent="#accordionExample">
                                    <div class="card-body">
                                        <p>We accept returns within 30 days of the purchase date. Items must be in new, unworn condition with all original tags attached.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseFive">
                                        How long does shipping take?
                                    </a>
                                </div>
                                <div id="collapseFive" class="collapse" data-parent="#accordionExample">
                                    <div class="card-body">
                                        <p>Standard shipping typically takes 5-7 business days. We also offer expedited shipping options at checkout.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseSix">
                                        How do I know what size to order?
                                    </a>
                                </div>
                                <div id="collapseSix" class="collapse" data-parent="#accordionExample">
                                    <div class="card-body">
                                        <p>We provide a detailed size guide on each product page. If you're still unsure, our customer service team would be happy to assist you.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseSeven">
                                        Can I change or cancel my order?
                                    </a>
                                </div>
                                <div id="collapseSeven" class="collapse" data-parent="#accordionExample">
                                    <div class="card-body">
                                        <p>Orders can be changed or cancelled within 1 hour of placing the order by contacting our customer service. After this time, we cannot guarantee any changes or cancellations as the order may have already been processed.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Faq Section End -->

    <!-- Footer Section Begin -->
    <jsp:include page="footer.jsp" />
    <script>
        var pageName = './faq.jsp';

        var navItems = $('.nav-menu ul li');
        navItems.removeClass('active');

        $('.nav-menu ul li a[href="' + pageName + '"]').parent().addClass('active');

        $(document).ready(function() {
            $('.card-heading a').click(function() {
                var target = $(this).attr('data-target');
                $(target).collapse('toggle');
            });
        });
    </script>

    </body>

    </html>