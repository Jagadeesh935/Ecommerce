package com.jk.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public class CustomRepo {

    @Autowired
    private JdbcTemplate template;

    public List<Map<String,Object>> getAverageRatings(){
        String query = "select product_id, round(avg(stars),1) as avg_ratings from reviews group by product_id";
        System.out.println(query);
        return template.queryForList(query);
    }

    @Transactional
    public int addToCart(long productid, long userid) throws DataIntegrityViolationException {
        String query = "insert into cart (product_id, user_id) values (?, ?)";
        System.out.println(query);
        return template.update(query, productid, userid);
    }


    public List<Map<String, Object>> getProductDetails(long id){
        String query = "select p.id, p.product_name, p.price, p.seller_id from products p join cart c on p.id = c.product_id where c.user_id = ?";
        System.out.println(query);
        List<Map<String,Object>> result = template.queryForList(query, id);
        return result;
//        return Conversion.mapper(result, GetCartData.class);
    }


    public int removeProductFromCart(Long productId, Long userId) {
        System.out.println("inside the repo");
        String query = "delete from cart where product_id = ? and user_id = ?";
        System.out.println(query);
        return template.update(query, productId, userId);
    }

    public List<Map<String, Object>> getUserDetails(long id) {
        String query = "select username, mobile, door, street, city, district, state, country from users where id = ?";
        System.out.println(query);
        return template.queryForList(query, id);
    }

    public void placeOrder(long userid, long productId, int totalAmount, String orderStatus, String deliveryStatus, int quantity) {
        String query = "insert into orders (user_id, product_id, total_amount, delivery_status, order_status, quantity) values (?,?,?,?,?,?)";
        System.out.println(query);
        template.update(query, userid, productId, totalAmount, deliveryStatus, orderStatus, quantity);
    }

    public void deleteCartItems(long userid) {
        String query = "delete from cart where user_id = ?";
        System.out.println(query);
        template.update(query, userid);
    }

    public List<Map<String, Object>> getAllOrders(long userid) {
        String query = "select o.id, o.date_of_order, o.expected_delivery_date, p.product_name, o.quantity, o.total_amount, o.delivery_status from orders o join products p on o.product_id = p.id where user_id = ?";
        System.out.println(query);
        return template.queryForList(query, userid);
    }

    public void cancelOrder(Long id) {
        String query = "update orders set delivery_status = 'Cancelled', order_status = 'Cancelled' where id = ?";
        System.out.println(query);
        template.update(query, id);
    }

    public List<Map<String, Object>> getProductDetailsBySellerId(long id) {
        String query = "select * from products where seller_id = ?";
        System.out.println(query);
        return template.queryForList(query, id);
    }

    public List<Map<String, Object>> getOrdersBySellerId(long id) {
        String query = "select o.id, p.product_name, p.price, o.order_status from orders o join products p on o.product_id = p.id where p.seller_id = ?";
        System.out.println(query);
        return template.queryForList(query, id);
    }

    @Transactional
    @Modifying
    public void updatePersonalInfo(long id, String name, String value) {
        String query = "update users set "+name+" = ? where id = ?";
        System.out.println(query);
        template.update(query, value, id);
    }

    @Transactional
    @Modifying
    public void updateProductQuantity(long id, int qty) {
        String query = "update products set quantity = ? where id = ?";
        System.out.println(query);
        template.update(query, qty, id);
    }

    public void updateOrderStatus(long id) {
        String query = "update orders set order_status = 'Delevered', delivery_status = 'Delivered' where id = ? and order_status != 'Cancelled'";
        System.out.println(query);
        template.update(query, id);
    }

    public List<Map<String, Object>> getOldPassword(long id) {
        String query = "select password from users where id = ?";
        System.out.println(query);
        return template.queryForList(query, id);
    }

    public void updatePassword(long id, String neww) {
        String query = "update users set password = ? where id = ?";
        System.out.println(query);
        template.update(query, neww, id);
    }
}
