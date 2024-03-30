
<div align="center">

  <img src="src/main/webapp/img/logo.png" alt="logo" width="200" height="auto" />
  <h1>Fashi E-commerce</h1>

  <p>
    An E-commerce web application that  .
  </p>


<!-- Badges -->
<p>
  <a href="https://github.com/mahernasser/iti_ecommerce_app/graphs/contributors">
    <img src="https://img.shields.io/github/contributors/mahernasser/iti_ecommerce_app" alt="contributors" />
  </a>

  <a href="https://github.com/mahernasser/iti_ecommerce_app/stargazers">
    <img src="https://img.shields.io/github/stars/mahernasser/iti_ecommerce_app" alt="stars" />
  </a>
</p>

</div>

<br />

<!-- Table of Contents -->
# :notebook_with_decorative_cover: Table of Contents

- [About the Project](#star2-about-the-project)
    * [Screenshots](#camera-screenshots)
    * [Tech Stack](#space_invader-tech-stack)
    * [Features](#dart-features)
    * [Color Reference](#art-color-reference)
    * [Environment Variables](#key-environment-variables)
- [Getting Started](#toolbox-getting-started)
    * [Prerequisites](#bangbang-prerequisites)
    * [Installation](#gear-installation)
    * [Run Locally](#running-run-locally)
- [Contributing](#wave-contributing)
- [Contact](#handshake-contact)
- [Acknowledgements](#gem-acknowledgements)



<!-- About the Project -->
## :star2: About the Project


<!-- Screenshots -->
### :camera: Screenshots

<div align="center"> 
  <img src="https://placehold.co/600x400?text=Your+Screenshot+here" alt="screenshot" />
</div>


<!-- TechStack -->
### :space_invader: Tech Stack

<details>
  <summary>Frontend</summary>
  <ul>
    <li><a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript">JavaScript</a></li>
    <li><a href="https://en.wikipedia.org/wiki/HTML5">HTML5</a></li>
    <li><a href="https://en.wikipedia.org/wiki/CSS">CSS</a></li>
    <li><a href="https://jquery.com/">jQuery</a></li>
    <li><a href="https://getbootstrap.com/">Bootstrap</a></li>
  </ul>
</details>

<details>
  <summary>Backend</summary>
  <ul>
    <li><a href="https://www.java.com/en/">Java</a></li>
    <li><a href="https://hibernate.org/">Hibernate</a></li>
    <li><a href="https://www.tutorialspoint.com/jsp/index.htm">JSP</a></li>
    <li><a href="https://en.wikipedia.org/wiki/Jakarta_Servlet">Servlet</a></li>
    <li><a href="https://www.digitalocean.com/community/tutorials/javamail-example-send-mail-in-java-smtp">Jakarta Mail</a></li>
    <li><a href="https://mapstruct.org/">Map Struct</a></li>
    <li><a href="https://firebase.google.com/">Firebase</a></li>
  </ul>
</details>

<details>
<summary>Database</summary>
  <ul>
    <li><a href="https://www.mysql.com/">MySQL</a></li>
  </ul>
</details>

<!-- Features -->
### :dart: Features
Admin Features:
- Adding products
- Updating products
- Deleting products
- Review customer's profile
- Review customer's orders history
  
Customer Features:
- Searching for products
- Filtering products by price, category & tags
- Updating customer's profile
- Adding products to cart
- Removing products from cart
- Placing an order
- Updating cart items
- Navigate shop offline
- Sign up & Sign in
- Reset password
- Contact us


<!-- Color Reference -->
### :art: Color Reference

| Color           | Hex                                                              |
|-----------------|------------------------------------------------------------------|
| Primary Color   | ![#323232](https://via.placeholder.com/10/323232?text=+) #323232 |
| Secondary Color | ![#353535](https://via.placeholder.com/10/353535?text=+) #353535 |
| Text Color      | ![#EEEEEE](https://via.placeholder.com/10/EEEEEE?text=+) #EEEEEE |


<!-- Getting Started -->
## 	:toolbox: Getting Started

<!-- Prerequisites -->
### :bangbang: Prerequisites

This project requires Java JDK 17. Make sure you have installed it on your system. You can check by running the following command in your terminal:

```bash
java -version
```

This project uses Maven as a build tool and package manager. Make sure you have it installed on your system. You can check by running the following command in your terminal:

```bash
mvn -v
```

This project requires Apache Tomcat 10. Make sure you have it installed on your system. You can check by running the following command in your terminal:

Please note that the `catalina version` command will only work if the `CATALINA_HOME` environment variable is set to the directory where Tomcat is installed, and the `bin` directory of Tomcat is added to the `PATH` environment variable.

```bash
catalina version
```

<!-- Run Locally -->
### :running: Run Locally

Clone the project

```bash
  git clone https://github.com/mahernasser/iti_ecommerce_app
```

Go to the project directory

```bash
  cd iti_ecommerce_app
```

To deploy the application on Tomcat, first, you need to package your application as a WAR file. Navigate to the project directory and run the following command:

```bash
  mvn clean package
```

This command will create a WAR file in the `target` directory. You can deploy this WAR file on Tomcat by copying it to the `webapps` directory of Tomcat.

```bash
  cp target/iti_ecommerce_app.war $CATALINA_HOME/webapps/
```

To run the application, start the Tomcat server by running the following command:

```bash
  catalina run
```

The application will be available at `http://localhost:8080/iti_ecommerce_app/`.

To stop the Tomcat server, press `Ctrl + C` in the terminal where the server is running.

<!-- Contributing -->
## :wave: Contributing

<a href="https://github.com/mahernasser/iti_ecommerce_app/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=mahernasser/iti_ecommerce_app"  alt="Contributors"/>
</a>

<!-- Contact -->
## :handshake: Contact

- Maher Nasser - [LinkedIn]() -

- Nada Mahmoud - [LinkedIn]() -

- Mostafa Abdallah - [LinkedIn](https://www.linkedin.com/in/mostafa-abdallah-a35130151/) - mostafaabdallah009@gmail.com

Project Link: [https://github.com/mahernasser/iti_ecommerce_app]()
