<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="/css/orderconfirmation.css">
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
    <section id="orderConfirmationBody">
        <h1>Order Confirmation</h1>
        <div class="orderSummary">
            <h2>Order Summary</h2>
            <div class="summaryDetails">
                <div class="productsummarytitle">
                    <div class="summarytitle">Product Name</div>
                    <div class="summarytitle">Quantity</div>
                    <div class="summarytitle">Price Per item</div>
                    <div class="summarytitle">Total Price</div>
                </div>
                <div class="productsummarydata">
                    <c:forEach var="product" items="${products}">
                        <div class="dataitems">
                            <div class="item">${product.product_name}</div>
                            <div class="item">${product.quantity}</div>
                            <div class="item">${product.price}</div>
                            <div class="item">${product.price * product.quantity}</div>
                        </div>
                    </c:forEach>
                </div>
            </div>

        <div class="billingAddress">
            <h2>Billing Address</h2>
            <p><strong>Name:</strong> ${userdetails.username}</p>
            <p><strong>Address:</strong> ${userdetails.door} ${userdetails.street} ${userdetails.city} ${userdetails.district} ${userdetails.state}</p>
            <p><strong>Contact:</strong> ${userdetails.mobile}</p>
            <button onclick="editAddress()">Edit Address</button>
        </div>
        <div class="deliveryMethod">
            <h2>Delivery Method</h2>
            <p><strong>Delivery Type:</strong> Standard Delivery</p>
            <p><strong>Estimated Delivery Date:</strong> 12th December 2024</p>
        </div>
        <div class="paymentDetails">
            <h2>Payment Details</h2>
            <p><strong>Payment Method:</strong> Credit Card</p>
            <p><strong>Card Number:</strong> **** **** **** 1234</p>
        </div>
        <div class="confirmationButtons">
            <button onclick="window.location.href='/placeorder'">Confirm Order</button>
            <button onclick="window.location.href='/cart'">Edit Cart</button>
        </div>
    </section>
</body>
</html>
