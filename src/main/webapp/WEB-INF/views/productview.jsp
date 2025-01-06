<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Product view</title>
    <link rel="stylesheet" href="/css/productview.css" />
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
    <section id="productbody">
      <div class="product">
        <div class="ial">
          <div class="mainimg">
            <img src="${imgurls[0]}" alt="" />
          </div>
          <div class="imglist">
            <c:forEach var="img" items="${imgurls}">
              <img onclick="updateimage(this)" src="${img}" alt="" />
            </c:forEach>
          </div>
        </div>
        <div class="buttons">
          <button class="buy">Buy Now</button>
          <button onclick="addtocart('${product.id}')" class="add">Add to cart</button>
        </div>
      </div>
      <div class="details">
        <h1 class="productname">${product.productName}</h1>
        <div class="ratereview">
          <div class="rating">
            <span>${avgratings}</span>
            <span
              ><img id="blackstar" src="/images/blackstar.png" alt=""
            /></span>
          </div>
          <div class="reviews">${totalratings} Ratings and ${totalreviews} Reviews</div>
        </div>
        <div class="price">Special price</div>
        <div class="pricevalue">Rs. ${product.price}</div>

        <h3 class="highlights">Highlights</h3>
        <ul class="highlightslist ullist">
          <c:forEach var="highlight" items="${highlights}">
            <li>${highlight}</li>
          </c:forEach>
        </ul>

        <h3 class="highlights">Seller</h3>
        <ul class="ullist">
          <li>${product.user.username}</li>
          <li>Ph : ${product.user.mobile}</li>
          <li>Place : ${product.user.district}</li>
        </ul>

        <div class="ratingreviewinfo">
          <h3 class="title">Ratings & Reviews</h3>
          <div class="totalbox">
            <div class="rating">
              <span>${avgratings}</span>
              <span
                ><img id="blackstar" src="/images/blackstar.png" alt=""
              /></span>
            </div>
            <div class="totalratings">${totalratings} Ratings</div>
            <div class="totalreviews">${totalreviews} Reviews</div>
          </div>
          <c:forEach var="review" items="${reviews}">
          <div class="review">
            <div class="blackstartitle">
              <div class="rating">
                <span>${review.stars}</span
                ><span
                  ><img id="blackstar" src="/images/blackstar.png" alt=""
                /></span>
              </div>
              <h4>${review.reviewTitle}</h4>
            </div>
            <p>${review.description}</p>
          </div>
        </c:forEach>
          <div class="addreview">
            <form action="/addreview" method="post">
              <h3>Add your Review</h3>
              <input type="hidden" name="productId" value="${product.id}">
              <div class="ratinginput">
                <img
                  onclick="rate(this)"
                  id="blackstar"
                  class="1"
                  src="/images/blackstar.png"
                  alt=""
                />
                <img
                  onclick="rate(this)"
                  id="blackstar"
                  class="2"
                  src="/images/blackstar.png"
                  alt=""
                />
                <img
                  onclick="rate(this)"
                  id="blackstar"
                  class="3"
                  src="/images/blackstar.png"
                  alt=""
                />
                <img
                  onclick="rate(this)"
                  id="blackstar"
                  class="4"
                  src="/images/blackstar.png"
                  alt=""
                />
                <img
                  onclick="rate(this)"
                  id="blackstar"
                  class="5"
                  src="/images/blackstar.png"
                  alt=""
                />
              </div>
              <div class="textinputreview">
                <input
                  type="text"
                  placeholder="Review Title"
                  name="reviewTitle"
                />
                <textarea
                  name="description"
                  rows="5"
                  placeholder="Desceiption"
                ></textarea>
              </div>
              <div class="formsubmit">
                <button type="submit">Submit</button>
                <button>Clear</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
    <script>
      function rate(element) {
        var val = parseInt(element.classList[0]);
        console.log(val)
        element.parentElement.querySelectorAll("img").forEach((star) => {
          if (val >= parseInt(star.classList[0])) {
            star.src = "/images/goldstar.png";
          } else {
            star.src = "/images/blackstar.png";
          }
        });
        var input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", "stars");
        input.setAttribute("value", val);

        var hiddeninput = document.querySelector(".textinputreview input[type='hidden']");
        console.log(hiddeninput)
        if (hiddeninput == null) {
          document.querySelector(".textinputreview").appendChild(input);
        } else {
          hiddeninput.value = val;
        }
      }

      function updateimage(element){
        document.querySelector(".mainimg > img").src = element.src.substring(21, element.src.length);
        
      }


      async function addtocart(id, userid){
        console.log("${userid}")
        var response = await fetch("/addtocart",{
          method: "POST",
          headers : {
            "Content-Type": "application/json"
          },
          body : JSON.stringify({"productid": id, "userid": "${userid}"})
        })

        var result = await response.text();
        console.log(result);
        if (result == "success"){
          alert("Added Successfully")
        } else {
          alert("Problem")
        }
      }

    </script>
  </body>
</html>
