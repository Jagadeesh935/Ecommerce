package com.jk.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jk.repository.CustomRepo;

@Service
public class CustomService {
    
    @Autowired
    private CustomRepo repo;

    public List<Map<String,Object>> getAverageRatings(){
        return repo.getAverageRatings();
    }


    public boolean addToCart(long productid, long userid){
        int result;
        try{
            result = repo.addToCart(productid, userid);
        } catch (DataIntegrityViolationException  e){
            return true;
        }
        if (result > 0){
            return true;
        }
        return false;
    }


    public List<Map<String, Object>> productDetails(long id){
        return repo.getProductDetails(id);
    }


    public String removeProductFromCart(Long productId, Long userId) {
        System.out.println("inside the service");
        int res = repo.removeProductFromCart(productId, userId);
        if (res > 0) return "success";
        return "failure";
    }

    public Map<String,Object> getUserDetails(long id) {
        return repo.getUserDetails(id).get(0);
    }

    public void placeOrder(long userid, List<Map<String, Object>> products) {
        for (Map<String, Object> pro : products){
            long product_id = (long) pro.get("id");
            int totalAmount = (int) pro.get("price") * (int) pro.get("quantity");
            String orderStatus = "Ordered";
            String deliveryStatus = "Pending";
            int quantity = (int) pro.get("quantity");
            repo.placeOrder(userid, product_id, totalAmount, orderStatus, deliveryStatus, quantity);
        }
        repo.deleteCartItems(userid);
    }

    public List<Map<String, Object>> getAllOrders(long userid) {
        return repo.getAllOrders(userid);
    }

    public void cancelOrder(Long id) {
        repo.cancelOrder(id);
    }

    public List<Map<String, Object>> getProductDetailsBySellerId(long id) {
        return repo.getProductDetailsBySellerId(id);
    }

    public List<Map<String, Object>> getOrdersBySellerId(long id){
        return  repo.getOrdersBySellerId(id);
    }


    public void updatePersonalInfo(long id, String name, String value) {
        repo.updatePersonalInfo(id, name, value);
    }

    public void updateProductQuantity(long id, int qty) {
        repo.updateProductQuantity(id, qty);
    }

    public void updateOrderStatus(long id) {
        repo.updateOrderStatus(id);
    }

    public boolean changePassword(long id, String old, String neww) {
        String oldpassword = (String) repo.getOldPassword(id).get(0).get("password");
        if (oldpassword.equals(old)){
            repo.updatePassword(id, neww);
            return true;
        }

        return false;
    }
}
