<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
    <link rel="stylesheet" href="/css/cart.css">
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
    <section id="cartbody">
        <h1>Cart</h1>
        <p>${pros.size()} items in your cart</p>
        <div class="itemlist">
            <div class="title">
                <h3>Product</h3>
                <h3>Price</h3>
                <h3>Quantity</h3>
                <h3>Total</h3>
            </div>
            <c:forEach var="pro" items="${pros}">
                <div class="item" id="${pro.id}">
                    <div class="product">
                        <div class="productimg">
                            <img src="${pro.url}" alt="">
                        </div>
                        <div class="nameaction">
                            <div class="productname">
                                <h2>${pro.product_name}</h2>
                            </div>
                            <div class="actionbuttons">
                                <button onclick="increaseQuantity(this)">Increase Qty</button>
                                <button onclick="decreaseQuantity(this)">Decrease Qty</button>
                                <button onclick="removeProduct(this)">Remove Product</button>
                            </div>
                        </div>
                    </div>
                    <div class="price">
                        ${pro.price}
                    </div>
                    <div class="quantity">1</div>
                    <div class="total"><span>Rs.</span><span class="totalproductamount">100000</span></div>
                </div>
            </c:forEach>
        </div>
        <div class="total">
            <div class="h3">Total Amount</div>
            <div id="amount" class="amount">0</div>
        </div>
        <div class="buttons">
            <button onclick="checkout()">Checkout</button>
        </div>
    </section>
    <script>
        function calculateProductPrice(){
            document.querySelectorAll(".item").forEach(item => {
                var price = parseInt(item.querySelector(".price").innerHTML);
                var quantity = parseInt(item.querySelector(".quantity").innerHTML);
                item.querySelector(".totalproductamount").innerHTML = price * quantity;
            });
            document.getElementById("amount").innerHTML = 0;
            document.querySelectorAll(".totalproductamount").forEach(item => {
                var amount = document.getElementById("amount");
                amount.innerHTML = parseInt(amount.innerHTML) + parseInt(item.innerHTML);
            })

        }
        calculateProductPrice();

        function increaseQuantity(element){
            var qty = element.parentElement.parentElement.parentElement.parentElement.querySelector(".quantity");
            qty.innerHTML = parseInt(qty.innerHTML) + 1;
            calculateProductPrice();
        }

        function decreaseQuantity(element){
            var qty = element.parentElement.parentElement.parentElement.parentElement.querySelector(".quantity");
            if (parseInt(qty.innerHTML) > 1){
                qty.innerHTML = parseInt(qty.innerHTML) - 1;
                calculateProductPrice();
            }

        }

        async function removeProduct(element){
            var id = element.parentElement.parentElement.parentElement.parentElement.id
            var userid = "${userid}";
            var response = await fetch("/removefromcart",{
            method: "post",
            headers : {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({"productId": id, "userId": userid})
            });
            console.log(response);
            response = await response.text();
            console.log(response);
            if (response == "success") window.location.href = "/cart";
        }

        async function checkout(){
            var qtlist = []
            document.querySelectorAll(".item").forEach(item => {
                var id = item.id;
                var quantity = item.querySelector(".quantity").innerHTML;
                console.log(id)
                console.log(quantity)
                qtlist.push(id + "-" + quantity)
            })

            var response = await fetch("/checkout",{
                method : "post",
                headers : {
                    "Content-Type" : "application/json"
                },
                body : JSON.stringify({"quantities": qtlist})
            })

            var response = await response.text();
            if (response == "success") window.location.href = "/confirmorderpage";
        }

    </script>
</body>
</html>