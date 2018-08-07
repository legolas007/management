package com.primeton.order.service.impl;

import com.primeton.order.dao.IOrderDao;
import com.primeton.order.dao.IOrderItemDao;
import com.primeton.order.entity.*;
import com.primeton.order.service.CartService;
import com.primeton.order.service.OrderService;
import com.primeton.order.util.StringUtil;
import com.primeton.order.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author: Usher
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IOrderItemDao itemDao;

    @Autowired
    private CartService cartService;

    @Autowired
    private IBookDao bookDao;


    @Override
    @Transactional
    public void addOrder(String username, String cartInfo) {
        // TODO Auto-generated method stub

        if(StringUtil.isNullOrEmpty(cartInfo)) {
            throw new RuntimeException("���ﳵΪ�գ�");
        }

        Cart cart = null;
        TUser buyer = null;

        try {
            cart = cartService.findCart(cartInfo);
            buyer = userDao.findByName(username);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }

        if(!cartService.checkCart(cart)) {
            throw new RuntimeException("��治��!");
        }

        TOrder order = new TOrder();
        order.setOrderNum(UUID.randomUUID().toString());
        order.setTotalPrice(cart.getTotalPrice());
        order.setCreateDate(new Date());
        order.setState(0);
        order.setBuyer(buyer);
        try {
            orderDao.add(order);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }

        List<CartItem> cartItems = cart.getCartItems();


        for (CartItem cartItem : cartItems) {
            TOrderItem orderItem = new TOrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(cartItem.getBook());
            orderItem.setNum(cartItem.getNum());
            try {
                //���¿��
                Integer newStock = orderItem.getBook().getStock() - orderItem.getNum();
                Map<String, Object> stockInfo = new HashMap<>();
                stockInfo.put("id", orderItem.getBook().getId());
                stockInfo.put("stock", newStock);
                bookDao.updateStock(stockInfo);
                itemDao.add(orderItem);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw e;
            }
        }

    }




    @Override
    public void takeBack(Integer orderId) {
        // TODO Auto-generated method stub

        try {
            Map<String, Object> statusInfo = new HashMap<>();
            statusInfo.put("id", orderId);
            statusInfo.put("status", IOrderStateImpl.TakingBack);
            orderDao.updateStatus(statusInfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }

    }




    @Override
    public PageBean<PageOrder> findOrderByIndex(String username, Integer page) {
        // TODO Auto-generated method stub

        PageBean<PageOrder> pageBean = new PageBean<>();
        TUser user = userDao.findByName(username);
        int totalcount = 0;
        try {
            totalcount = orderDao.countByBuyerId(user.getId());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
        pageBean.setCount(totalcount);

        int totalPage = 0;
        if(totalcount % pageBean.getSize() == 0) {
            totalPage = totalcount / pageBean.getSize();
        }else {
            totalPage = totalcount / pageBean.getSize() + 1;
        }
        pageBean.setTotalPage(totalPage);

        int currentPage = 0;
        if(page <= 1) {
            currentPage = 1;
        }else if(page >= totalPage) {
            currentPage = totalPage;
        }else {
            currentPage = page;
        }
        pageBean.setCurrentPage(currentPage);

        Map<String, Object> info = new HashMap<>();
        info.put("index", (currentPage - 1) * pageBean.getSize());
        info.put("size", pageBean.getSize());
        info.put("buyerId", user.getId());
        int count = orderDao.countPageOrderByBuyerId(info);

        //��ѯҳ����Ϣ
        Map<String, Object> map = new HashMap<>();
        map.put("index", (currentPage - 1) * pageBean.getSize());
        map.put("size", pageBean.getSize());
        map.put("username", username);
        map.put("buyerId", user.getId());
        List<TOrderItem> items = null;
        try {
            items = itemDao.findByIndex(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
        List<PageOrder> pageOrders = packItems(items, count);
        pageBean.setPageInfos(pageOrders);

        return pageBean;
    }


    public List<PageOrder> packItems(List<TOrderItem> orderItems, int count){
        List<PageOrder> pageOrders = new ArrayList<>();

        int cur = 0;
        for(int i = 0; i < count; i++) {
            PageOrder pageOrder = new PageOrder();
            List<TOrderItem> items = new ArrayList<>();
            pageOrder.setOrderItems(items);

            pageOrder.setId(orderItems.get(cur).getOrder().getId());
            pageOrder.setOrderNum(orderItems.get(cur).getOrder().getOrderNum());
            pageOrder.setTotalPrice(orderItems.get(cur).getOrder().getTotalPrice());
            pageOrder.setCreateDate(orderItems.get(cur).getOrder().getCreateDate());
            pageOrder.setState(orderItems.get(cur).getOrder().getState());
            pageOrder.setBuyer(orderItems.get(cur).getOrder().getBuyer());

            for(;cur < orderItems.size(); cur++) {
                if(!orderItems.get(cur).getOrder().getId().equals(pageOrder.getId())) {
                    break;
                }else {
                    items.add(orderItems.get(cur));
                }
            }

            pageOrders.add(pageOrder);
        }
        return pageOrders;

    }

    @Override
    public void receive(Integer orderId) {
        // TODO Auto-generated method stub

        try {
            Map<String, Object> statusInfo = new HashMap<>();
            statusInfo.put("id", orderId);
            statusInfo.put("status", IOrderStateImpl.Receive);
            orderDao.updateStatus(statusInfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }
    }
}
