<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>home</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
	<input type="hidden" id="userid" value="${userid}">
    <section id="navbar">
        <div onclick="window.location.href='/home'" class="navitem">Home</div>
        <div onclick="window.location.href='/orders'" class="navitem">Orders</div>
        <div onclick="window.location.href='/cart'" class="navitem">Cart</div>
        <div onclick="window.location.href='/sellproduct'" class="navitem">Sell Item</div>
		<div onclick="window.location.href='/profile'" class="navitem">${username}</div>
		<div class="profilepicture"><img src="${pic}"></div>
    </section>
    <section id="homebody">
        <div class="leftpanel">
            <h3>Category</h3>
            <div class="item">Grooming</div>
            <div class="item">Clothes</div>
            <div class="item">Food</div>
            <div class="item">Snacks</div>
            <h3>Brand</h3>
            <div class="item">LG</div>
            <div class="item">HP</div>
            <div class="item">Dell</div>
            <div class="item">Acer</div>
        </div>
        <div class="rightbody">
            <div class="searchfield">
                <input type="search">
                <button><img src="./images/searchicon.png" alt=""></button>
            </div>
            <div class="itemslist">
				<c:forEach var="product" items="${products}">
				    <div class="card">
				        <div class="cardimgcontainer"><img class="cardimg" src="${product.imageurl}" alt=""></div>
				        <div class="name"><a href="/productview/${product.id}">${product.productName}</a></div> <!-- Display product name -->
				        <div class="price">Rs. ${product.price}</div> <!-- Display price -->
				        <div class="rating"><span>${product.rating}</span> <img src="./images/blackstar.png" alt=""></div>
				    </div>
				</c:forEach>
            </div>
        </div>
    </section>
    <footer>
        <div class="copyrights">@JK 2024</div>
    </footer>
</body>
</html>