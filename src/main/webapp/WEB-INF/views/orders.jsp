<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders Page</title>
    <link rel="stylesheet" href="/css/orders.css">
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
<section id="orderbody">
    <h1>Your Orders</h1>
    <p>Order History</p>
    <div class="orderlist">
        <div class="title">
            <h3>Order ID</h3>
            <h3>Date</h3>
            <h3>Items</h3>
            <h3>Total</h3>
            <h3>Status</h3>
            <h3>Actions</h3>
        </div>
        <c:forEach var="orderdetail" items="${orderdetails}">
            <div class="order">
                <div class="orderid">${orderdetail.id}</div>
                <div class="date">${orderdetail.date_of_order}</div>
                <div class="items">
                    <div class="item">
                        <div class="itemname">${orderdetail.product_name}</div>
                        <div class="itemquantity">Quantity: ${orderdetail.quantity}</div>
                    </div>
                </div>
                <div class="total"><span>Rs.</span><span>${orderdetail.total_amount}</span></div>
                <div class="status">${orderdetail.delivery_status}</div>
                <div class="cancel"><button onclick="cancelorder(this)">Cancel Order</button></div>
            </div>
        </c:forEach>
    </div>
</section>
<script>
    async function cancelorder(element) {
            try {
                // Get the order ID
                const id = element.closest(".order").querySelector(".orderid").textContent.trim();

                // Send the request to cancel the order
                const response = await fetch("/cancelorder", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({ "id": id })
                });

                // Handle the response
                if (response.ok) {
                    const result = await response.text();
                    if (result === "success") {
                        window.location.href = "/orders";
                    } else {
                        alert("Failed to cancel the order.");
                    }
                } else {
                    alert("Error in canceling the order.");
                }
            } catch (error) {
                console.error("Error:", error);
                alert("An unexpected error occurred.");
            }
        }
</script>
</body>
</html>