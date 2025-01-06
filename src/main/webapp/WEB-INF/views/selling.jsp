<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Selling</title>
    <link rel="stylesheet" href="css/selling.css">
</head>
<body> 
    <section id="navbar">
        <div onclick="window.location.href='/home'" class="navitem">Home</div>
                <div onclick="window.location.href='/orders'" class="navitem">Orders</div>
                <div onclick="window.location.href='/cart'" class="navitem">Cart</div>
                <div onclick="window.location.href='/sellproduct'" class="navitem">Sell Item</div>
        		<div onclick="window.location.href='/profile'" class="navitem">${username}</div>
        		<div class="profilepicture"><img src="${pic}"></div>
    </section>
	<h1 style="margin-top: 10px; color: green;">${msg}</h1>
    <form action="/sell" method="post" enctype="multipart/form-data">
        <h1>Add Product Details</h1>
		<input type="hidden" value="${userid}" name="sellerId">
        <div class="field">
            <label for="productName">Product Name</label>
            <input type="text" name="productName">
        </div>
        <div class="field">
            <label for="price">Product Price</label>
            <input type="number" name="price">
        </div>
        <div class="field">
            <label for="category">Category</label>
            <select name="category">
                <option value="Electronics">Electronics</option>
                <option value="Fashion">Fashion</option>
                <option value="Home & Furniture">Home & Furniture</option>
                <option value="Beauty & Personal Care">Beauty & Personal Care</option>
                <option value="Sports">Sports</option>
                <option value="Entertainment">Entertainment</option>
                <option value="Toys">Toys</option>
                <option value="Automotives">Automotives</option>
                <option value="Groceries">Groceries</option>
                <option value="Others">Others</option>
            </select>
        </div>
        <div class="field">
            <label for="highlights">Highlights</label>
            <div id="highlightinputs">
                <input type="text" name="highlight">
            </div>
            <button type="button" onclick="addhighlight()">+</button>
        </div>
        <div class="field">
            <label for="image">Product image</label>
            <div id="imageinputs">
                <input type="file" name="images[]" accept="image/*">
            </div>
            <button type="button" onclick="addimage()">+</button>
        </div>
        <div class="buttons">
            <input type="submit" value="Submit">
            <input type="reset" value="Clear">
        </div>
    </form>
    <script>
        function addhighlight(){
            var inputfield = document.createElement("input")
            inputfield.setAttribute("type", "text")
            inputfield.setAttribute("name", "highlight")
            document.getElementById("highlightinputs").appendChild(inputfield)
        }
        function addimage(){
            var inputfield = document.createElement("input")
            inputfield.setAttribute("type", "file")
            inputfield.setAttribute("name", "images[]")
            inputfield.setAttribute("accept", "image/**")
            document.getElementById("imageinputs").appendChild(inputfield)
        }
    </script>
</body>
</html>