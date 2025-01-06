package com.jk.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import ch.qos.logback.classic.joran.ModelClassToModelHandlerLinker;
import com.jk.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jk.service.CustomService;
import com.jk.service.ProductService;
import com.jk.service.ReviewService;
import com.jk.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private CustomService cs;
	
	private String staticFolderPath = "C:\\Users\\Jagadeeshkumar\\Documents\\Intellij\\Ecommerce\\src\\main\\resources\\static\\";
	
	
	@GetMapping("/loginpage")
	public String loginpage() {
		System.out.println("Login page controller");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("loginpage");
		return "loginpage";
	}
	
	@GetMapping("/registerpage")
	public ModelAndView registerpage() {
		System.out.println("Register page controller");
		return new ModelAndView("registerpage");
	}
	
	public static String getfilename(String name) {
		File file = new File("C:\\Users\\Jagadeeshkumar\\Documents\\Intellij\\Ecommerce\\src\\main\\resources\\static\\profile-pictures\\" + name + ".png");
		if (file.exists()) {
			System.out.println("png format exist");
			return "/profile-pictures/" + name + ".png";
		}
		file = new File("C:\\Users\\Jagadeeshkumar\\Documents\\Intellij\\Ecommerce\\src\\main\\resources\\static\\profile-pictures\\" + name + ".jpg");
		if (file.exists()) {
			System.out.println("jpg format exist");
			return "/profile-pictures/" + name + ".jpg";
		}
		return null;
	}



	public List<String> getAllProductImageUrls(Long productId, Long sellerId){
		String path = staticFolderPath + "product-images\\" + sellerId + "\\" + productId;
		File file = new File(path);
//		FilenameFilter filter = (dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png");
		System.out.println(path);
		System.out.println(file.listFiles()[0].getName());
		List<String> imglist = new ArrayList<>();
		for (File img : file.listFiles()){
			imglist.add("/product-images/" + sellerId + "/" + productId + "/" + img.getName());
		}

		return imglist;
	}
	
	public String getproductImageUrl(Long productId, Long sellerId){
		String path = staticFolderPath + "product-images\\" + sellerId + "\\" + productId;
		File file = new File(path);
//		FilenameFilter filter = (dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png");
		System.out.println(path);
		System.out.println(file.listFiles()[0].getName());
		return "/product-images/" + sellerId + "/" + productId + "/" + file.listFiles()[0].getName();
	}


	public double getRatings(long id, List<Map<String, Object>> ratings) {
        for (Map<String, Object> map : ratings) {
            if (map.get("product_id") != null && ((Long) map.get("product_id")) == id) {
                return ((BigDecimal) map.get("avg_ratings")).doubleValue();
            }
        }
        return 0.0; // Default return value if no match is found
    }

	@GetMapping("/home")
	public ModelAndView homepage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		mv.addObject("userid", session.getAttribute("userid"));
		mv.addObject("username", session.getAttribute("username"));
		mv.addObject("pic", session.getAttribute("pic"));
		List<ProductDTO> products = productService.getRandomProducts();
		List<Map<String,Object>> ratings =  cs.getAverageRatings();
		System.out.println(products);
		for (ProductDTO p : products){
			long id = p.getId();
			long seller = p.getSellerId();
			p.setImageurl(getproductImageUrl(id,seller));
			p.setRating(getRatings(id, ratings) + "");
		}
		mv.addObject("products", products);
		mv.addObject("ratings", ratings);
		List<AvgRatings> avgratings = reviewService.getAllProductsAvgRatings();
		

		return mv;
	}

	@PostMapping("/login")
	public ModelAndView login(String username, String password, HttpSession session) {
		System.out.println("login controller");
		System.out.println(username);
		System.out.println(password);
		if(userService.login(username, password)) {
			System.out.println("login condition satisfied");
			User u = userService.getUserByUsername(username);
			ModelAndView mv = new ModelAndView("redirect:/home");
			String path = getfilename(u.getId() + "");
			session.setAttribute("userid", u.getId());
			session.setAttribute("username", u.getUsername());
			session.setAttribute("pic", path);
			return mv;
		}
		ModelAndView mv = new ModelAndView("loginpage");
		mv.addObject("error", "Username and password Invalid");
		return mv;
	}
	
	@PostMapping("/register")
	public ModelAndView register(@Valid User user, BindingResult br, @RequestParam("profilepic") MultipartFile profilePic, HttpServletRequest request) throws IllegalStateException, IOException {
		System.out.println("Has error : " + br.hasErrors());
		System.out.println("Has field error : " + br.hasFieldErrors());
		System.out.println(br.getFieldError());
		System.out.println(user);
		System.out.println("file:" + profilePic);
		
		User u = userService.saveUser(user);
		String originalFileName = profilePic.getOriginalFilename();
		String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
		System.out.println("printing the context");
		

		profilePic.transferTo(new File("C:\\Users\\Jagadeeshkumar\\Documents\\Intellij\\Ecommerce\\src\\main\\resources\\static\\profile-pictures\\" + u.getId() + fileExtension));
		
		System.out.println("Saved user : " + u);
		return new ModelAndView("loginpage");
	}
	
	@GetMapping("/sellproduct")
	public ModelAndView sellingPage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("selling");
		mv.addObject("userid",session.getAttribute("userid"));
		mv.addObject("username", session.getAttribute("username"));
		mv.addObject("pic", session.getAttribute("pic"));
		return mv;
	}
	
	
	@PostMapping("/sell")
	public ModelAndView sellproduct(Product product, String[] highlight, String sellerId,@RequestParam("images[]") MultipartFile[] images) throws IllegalStateException, IOException {
		System.out.println(product);
		for (String h : highlight) {
			System.out.println(h);
		}
		System.out.println("seller id : " + sellerId);
		System.out.println(images);
		File directory = new File(staticFolderPath + "product-images\\" + sellerId);
		if (!directory.exists()) {
			directory.mkdir();
		}

		
		product.setUser(userService.getUserById(Long.parseLong(sellerId)));
		
		Product addedproduct = productService.saveProduct(product);
		File nf = new File(staticFolderPath + "product-images\\" + sellerId + "\\" + addedproduct.getId());
		if(!nf.exists()){
			nf.mkdir();
		}
		for (MultipartFile image : images) {
			System.out.println("image:" + image);
			String filename = image.getOriginalFilename();
			String extinsion = filename.substring(filename.lastIndexOf("."));
			image.transferTo(new File(staticFolderPath + "product-images\\" + sellerId + "\\" + addedproduct.getId() + "\\" + filename));
		}
		System.out.println("Added product : " + addedproduct);
		productService.addHighlights(addedproduct.getId(), highlight);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("selling");
		mv.addObject("msg","Product Added Successfully");
		return mv;
	}

	@GetMapping("/productview/{id}")
	public ModelAndView getProductViewPage(@PathVariable("id") long id){
		System.out.println("product veiw controller");
		System.out.println("product");
		System.out.println(id);
		Product product = productService.getProductById(id);
		ModelAndView mv = new ModelAndView();
		if (product != null){
			mv.addObject("product", product);
		}
		System.out.println(product);
		
		List<String> highlights = productService.getHighlightsById(id);
		System.out.println(highlights);
		mv.addObject("highlights",highlights);

		List<Review> reviews = reviewService.getReviewByProductId(id);
		double totalStarRatings = 0;
		int totalratings = 0;
		int totalreviews = 0;
		for (Review r : reviews){
			totalStarRatings += r.getStars();
			totalratings += 1;
			if (!r.getReviewTitle().equals("") & !r.getDescription().equals("")){
				totalreviews+=1;
			}
		}
		mv.addObject("totalreviews", totalreviews);
		double rt = Math.round(totalStarRatings/totalratings * 10);
		rt = rt/10;
		System.out.println("Ratings avg: " + rt);
		mv.addObject("imgurls", getAllProductImageUrls(product.getId(), product.getUser().getId()));
		mv.addObject("avgratings", rt);
		mv.addObject("totalratings",totalratings);
		reviews = reviews.stream().filter(r -> !r.getReviewTitle().equals("") && !r.getDescription().equals("")).toList();
		System.out.println(reviews);
		mv.addObject("reviews", reviews);
		mv.setViewName("productview");
		return mv;
	}


	@PostMapping("/addreview")
	public ModelAndView addReview(Review review) {
		reviewService.addReview(review);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/productview/" + review.getProductId());
		return mv;
	}
	

	@PostMapping("/addtocart")
	@ResponseBody
	public String addToCart(@RequestBody Map<String, Long> addtocartrequest){
		System.out.println(addtocartrequest);
		System.out.println(addtocartrequest.get("productid"));
		System.out.println(addtocartrequest.get("userid"));
		boolean result = cs.addToCart(addtocartrequest.get("productid"), addtocartrequest.get("userid"));
		if (result) return "success";
		return "failure";
	}

	class Temp {

	}

	@GetMapping("/cart")
	public ModelAndView viewCartPage(HttpSession session) throws Exception {
		System.out.println("Cart page controller");
		System.out.println(session.getAttribute("userid"));
		System.out.println(session.getAttribute("username"));
		List<Map<String, Object>> result = cs.productDetails((long) session.getAttribute("userid"));
		System.out.println("Below are the products for : " + session.getAttribute("username"));

		for (Map<String, Object> data : result){
			long seller = (long) data.get("seller_id");
			long product = (long) data.get("id");
			String url = getproductImageUrl(product, seller);
			data.put("url",url);
		}
		System.out.println(result);
		ModelAndView mv = new ModelAndView("cart");
		mv.addObject("pros", result);
		return mv;
	}


	@PostMapping("/removefromcart")
	@ResponseBody
	public String removeFromCart(@RequestBody Map<String, Long> request){
		System.out.println(request.get("productId"));
		System.out.println(request.get("userId"));
		return cs.removeProductFromCart(request.get("productId"), request.get("userId"));
	}

	public List<Map<String, Object>> addQuantityToProductList(List<Map<String, Object>> list, List<String> quantities){
		quantities.forEach(value -> {
			long id = Long.parseLong(value.split("-")[0]);
			int quantity = Integer.parseInt(value.split("-")[1]);
			for (Map<String, Object> product : list){
				if ((long)product.get("id") == id){
					product.put("quantity", quantity);
				}
			}
		});
		return list;
	}

	@PostMapping("/checkout")
	@ResponseBody
	public String checkout(@RequestBody Map<String, List<String>> quantities, HttpSession session){
		System.out.println(quantities);
		long id = (long) session.getAttribute("userid");
		List<Map<String,Object>> products = cs.productDetails(id);
		System.out.println(products);
		products = addQuantityToProductList(products, quantities.get("quantities"));
		System.out.println(products);
		session.setAttribute("products", products);
		return "success";
	}

	@GetMapping("/confirmorderpage")
	public ModelAndView orderConfirmation(HttpSession session){
		List<Map<String,Object>> products = (List<Map<String, Object>>) session.getAttribute("products");
		Map<String, Object> usersDetails = cs.getUserDetails((long) session.getAttribute("userid"));
		System.out.println("order confirmation");
		System.out.println(products);
		ModelAndView mv = new ModelAndView("orderconfirmation");
		mv.addObject("products", products);
		mv.addObject("userdetails", usersDetails);
		return mv;
	}

	@GetMapping("/placeorder")
	public ModelAndView placeOrder(HttpSession session){
		List<Map<String, Object>> products = (List<Map<String, Object>>) session.getAttribute("products");
		session.removeAttribute("products");
		cs.placeOrder((long) session.getAttribute("userid"), products);
		return new ModelAndView("redirect:/orders");
	}

	@GetMapping("/orders")
	public ModelAndView ordersPage(HttpSession session){
		List<Map<String, Object>> orderdetails = cs.getAllOrders((long) session.getAttribute("userid"));
		ModelAndView mv = new ModelAndView("orders");
		mv.addObject("orderdetails", orderdetails);
		return mv;
	}

	@PostMapping("/cancelorder")
	@ResponseBody
	public String cancelOrder(@RequestBody Map<String,Object> request){
		System.out.println(request.get("id"));

		cs.cancelOrder(Long.parseLong((String) request.get("id")));
		return "success";
	}

	@GetMapping("/profile")
	public ModelAndView viewProfilePage(HttpSession session){
		ModelAndView mv = new ModelAndView("profile");
		long id = (long) session.getAttribute("userid");
		Map<String, Object> userDetails = cs.getUserDetails(id);
		mv.addObject("userdetails", userDetails);
		List<Map<String, Object>> products = cs.getProductDetailsBySellerId(id);
		mv.addObject("products", products);
		List<Map<String, Object>> orders = cs.getOrdersBySellerId(id);
		mv.addObject("orders", orders);
		return mv;
	}

	@PostMapping("/updatepersonalinfo")
	@ResponseBody
	public String personalInfoChange(@RequestBody Map<String, Object> request, HttpSession session){
		String name = (String) request.get("name");
		String value = (String) request.get("value");
		long id = (long) session.getAttribute("userid");
		cs.updatePersonalInfo(id, name, value);
		return "done";
	}

	@PostMapping("/updatequantity")
	@ResponseBody
	public String updateQuantity(@RequestBody Map<String, Object> request){
		System.out.println(request.get("id"));
		System.out.println(request.get("value"));
		long id = Long.parseLong((String) request.get("id"));
		int qty = Integer.parseInt((String) request.get("value"));
		cs.updateProductQuantity(id, qty);
		return "done";
	}

	@PostMapping("/updateorderstatus")
	@ResponseBody
	public String updateOrderStatus(@RequestBody Map<String, Object> request) {
		//TODO: process POST request
		System.out.println(request.get("id"));
		long id = Long.parseLong((String) request.get("id"));
		cs.updateOrderStatus(id);
		return "done";
	}

	@PostMapping("changepassword")
	@ResponseBody
	public String changePassword(String oldpassword, String newpassword, HttpSession session){
//		String old = (String) request.get("oldpassword");
//		String neww = (String) request.get("newpassword");
		long id = (long) session.getAttribute("userid");
		if (!cs.changePassword(id, oldpassword, newpassword)){
			return "Invalid old password";
		}
		return "password changed";

	}



}
