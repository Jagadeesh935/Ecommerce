<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile - E-Commerce Website</title>
    <link rel="stylesheet" href="/css/profile.css">
</head>
<body>

    <!-- Navbar Section -->
    <section id="navbar">
        <div onclick="window.location.href='/home'" class="navitem">Home</div>
                <div onclick="window.location.href='/orders'" class="navitem">Orders</div>
                <div onclick="window.location.href='/cart'" class="navitem">Cart</div>
                <div onclick="window.location.href='/sellproduct'" class="navitem">Sell Item</div>
        		<div onclick="window.location.href='/profile'" class="navitem">${username}</div>
        		<div class="profilepicture"><img src="${pic}"></div>
    </section>

    <div class="profilebody">
        <div class="leftpanel">
            <div onclick="viewPresonalInfo()" class="item">Personal info</div>
            <div onclick="viewSoldItems()" class="item">Manage sold items</div>
            <div onclick="viewManageOrdes()" class="item">Manage Orders</div>
            <div onclick="viewAccountSeciurity()" class="item">Account Security</div>
            <div onclick="Logout()" class="item">Logout</div>
        </div>
        <div class="rightbody">
            <div class="section personalinfo" id="personalinfo">
                <div class="field">
                    <label for="username">Username</label>
                    <input type="text" oninput="personalInfoChange(this)" name="username" value="${userdetails.username}">
                </div>
                <div class="field">
                    <label for="username">Mobile</label>
                    <input type="text" oninput="personalInfoChange(this)" name="mobile" value="${userdetails.mobile}">
                </div>
                <div class="field">
                    <label for="door">Door</label>
                    <input type="text" oninput="personalInfoChange(this)" name="door" value="${userdetails.door}">
                </div>
                <div class="field">
                    <label for="street">Street</label>
                    <input type="text" oninput="personalInfoChange(this)" name="street" value="${userdetails.street}">
                </div>
                <div class="field">
                    <label for="city">city</label>
                    <input type="text" oninput="personalInfoChange(this)" name="city" value="${userdetails.city}">
                </div>
                <div class="field">
                    <label for="district">district</label>
                    <select name="district" onchange="personalInfoChange(this)">
                        <option value="Coimbatore" ${userdetails.district == 'Coimbatore' ? 'selected' : ''}>Coimbatore</option>
                        <option value="Tiruppur" ${userdetails.district == 'Tiruppur' ? 'selected' : ''}>Tiruppur</option>
                        <option value="Madurai" ${userdetails.district == 'Madurai' ? 'selected' : ''}>Madurai</option>
                        <option value="Chennai" ${userdetails.district == 'Chennai' ? 'selected' : ''}>Chennai</option>
                    </select>
                </div>
                <div class="field">
                    <label for="state">state</label>
                    <select name="state" onchange="personalInfoChange(this)">
                        <option value="Tamil Nadu" ${userdetails.state == 'Tamil Nadu' ? 'selected' : ''}>Tamil Nadu</option>
                        <option value="Kerala" ${userdetails.state == 'Kerala' ? 'selected' : ''}>Kerala</option>
                    </select>
                </div>
                <div class="field">
                    <label for="country">country</label>
                    <select name="country" onchange="personalInfoChange(this)" value="${userdetails.country}">
                        <option value="India">India</option>
                    </select>
                </div>
            </div>

            <div style="display: none;" class="section managesolditems">
                <h2>Manage Sold Items</h2>

                <!-- Filter Options -->
                <div class="filters">
                    <label for="filterfields">Id:</label>
                    <input type="text" oninput="findProductId(this)">
                    <label for="filtervalues">Name:</label>
                    <input type="text" oninput="findProductName(this)">
                </div>

                <!-- Sold Items Table -->
                <div id="scrolldiv">
                <table>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Product Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${products}">
                        <tr>
                            <td class="solditemsearchid">${item.id}</td>
                            <td class="solditemsearchname">${item.product_name}</td>
                            <td>${item.price}</td>
                            <td>
                                <button onclick="decreaseqty(this)">-</button>
                                <span class="${item.id}">${item.quantity}</span>
                                <button onclick="increaseqty(this)">+</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
            </div>

            <div style="display: none;" class="section manageorders">
                            <h2>Manage Orders</h2>

                            <!-- Filter Options -->
                            <div class="filters">
                                <label for="order-filter">Filter By:</label>
                                <select id="order-filter" name="order-filter" onchange="filterOrders()">
                                    <option value="all">All Orders</option>
                                </select>
                            </div>

                            <!-- Orders Table -->
                            <div id="scrolldiv">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Order ID</th>
                                        <th>Product Name</th>
                                        <th>Price</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody id="orders-table-body">
                                <c:forEach var="item" items="${orders}">
                                    <tr>
                                        <td class="ordersid">${item.id}</td>
                                        <td>${item.product_name}</td>
                                        <td>Rs. ${item.price}</td>
                                        <td class="ordersstatus">${item.order_status}</td>
                                        <td>
                                            <button onclick="updateOrderStatus(this)">Mark as Delivered</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            </div>
                        </div>


            <div style="display: none;" class="section accountsecurity">
                <h2>Change Password</h2>
                <form action="/changepassword" method="post" id="change-password-form">
                    <div class="field">
                        <label for="current-password">Current Password</label>
                        <input type="password" id="current-password" name="oldpassword" required>
                    </div>
                    <div class="field">
                        <label for="new-password">New Password</label>
                        <input type="password" id="new-password" name="newpassword" required>
                    </div>
                    <div class="field">
                        <label for="confirm-password">Confirm New Password</label>
                        <input type="password" id="confirm-password" name="confirmpassword" required>
                    </div>
                    <button type="submit" class="submit-btn">Change Password</button>
                </form>
            </div>

            <div style="display: none" class="section logout-container">
                <button class="logout-btn" onclick="logout()">Logout</button>
            </div>






        </div>
    </div>

    <script>

        function populateStatus(){
            var status = []
            document.querySelectorAll(".ordersstatus").forEach(item => {
                if (!(status.includes(item.innerHTML))){
                    status.push(item.innerHTML);
                }
            })
            console.log(status)

            status.forEach(item => {
                var option = document.createElement("option")
                option.setAttribute("value", item);
                option.innerHTML = item
                document.querySelector("#order-filter").append(option)
            })
        }


        populateStatus()

        function viewPresonalInfo(){
            document.querySelectorAll(".section").forEach(element => element.style.display = "none")
            document.querySelector(".personalinfo").style.display = "grid"
        }

        function viewSoldItems(){
            document.querySelectorAll(".section").forEach(element => element.style.display = "none")
            document.querySelector(".managesolditems").style.display = "grid"
        }

        function viewManageOrdes(){
                    document.querySelectorAll(".section").forEach(element => element.style.display = "none")
                    document.querySelector(".manageorders").style.display = "grid"
                }

        function viewAccountSeciurity(){
            document.querySelectorAll(".section").forEach(element => element.style.display = "none")
            document.querySelector(".accountsecurity").style.display = "grid"
        }

        function Logout(){
            document.querySelectorAll(".section").forEach(element => element.style.display = "none")
            document.querySelector(".logout-container").style.display = "block"
        }


        async function updatepersonalinfo(element){
            console.log(element.name);
            console.log(element.value);
            var response = await fetch("/updatepersonalinfo",{
                method : "post",
                headers : {
                    "Content-Type" : "application/json"
                },
                body : JSON.stringify({"name": element.name, "value" : element.value})
            })

            var result = await response.text();
            console.log(result);
        }


        async function updateQuantity(element) {
            var id = element.parentElement.querySelector("span").classList[0];
            var value = element.parentElement.querySelector("span").innerHTML;
            console.log(id)
            console.log(value)
            var response = await fetch("/updatequantity",{
                method : "post",
                headers : {
                    "Content-Type" : "application/json"
                },
                body : JSON.stringify({"id": id, "value" : value})
            })

            var result = await response.text();
            console.log(result);
        }

        var changeList = [];
        
        async function personalInfoChange(element){
            changeList.forEach(item => clearTimeout(item))
            var fun = setTimeout(async () => {
                await updatepersonalinfo(element)
            }, 1000);
            
            changeList.push(fun)

        }


        async function changeQty(element){
            changeList.forEach(item=> clearInterval(item))
            var fun = setTimeout(async () => {
                await updateQuantity(element);
            }, 1000);
            changeList.push(fun)
        }

        function increaseqty(element){
            console.log("increasing quantity")
            var span = element.parentElement.querySelector("span")
            span.innerHTML = parseInt(span.innerHTML) + 1;
            changeQty(element);
        }

        function decreaseqty(element){
            var span = element.parentElement.querySelector("span")
            if (span.innerHTML != 0){
                span.innerHTML = parseInt(span.innerHTML) - 1;
                changeQty(element);
            }
        }

        function findProductId(element){
            var scrolled = false;
            document.querySelectorAll(".solditemsearchid").forEach(item => {
                console.log(item)
                console.log(item.innerHTML.includes(element.value))
                if (item.innerHTML.includes(element.value)){
                    if (!scrolled){
                        item.scrollIntoView({behavior : "smooth"});
                        scrolled = true;
                    }
                }
            })
        }

        function findProductName(element){
            var scrolled = false
            document.querySelectorAll(".solditemsearchname").forEach(item => {
                if (item.innerHTML.includes(element.value)){
                    if (!scrolled){
                        item.scrollIntoView({behavior:"smooth"});
                        scrolled = true;
                    }
                }
            })
        }

        async function updateOrderStatus(element) {
            var id = element.parentElement.parentElement.querySelector(".ordersid").innerHTML;
            var response = await fetch("/updateorderstatus",{
                method: "post",
                headers: {
                    "Content-Type": "application/json"
                },
                body : JSON.stringify({"id":id})
            })
            if (await response.text() == "success"){
                element.parentElement.parentElement.querySelector(".ordersstatus").innerHTML = "Delivered"
                populateStatus()
            }
        }

        function logout(){
            window.location.href = "/logout";
        }

    </script>
</body>
</html>
