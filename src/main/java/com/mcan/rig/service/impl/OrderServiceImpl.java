package com.mcan.rig.service.impl;

import com.mcan.rig.exception.OrderBadCreateRequestException;
import com.mcan.rig.exception.OrderNotFoundException;
import com.mcan.rig.exception.UnacceptableQuantityForCreatingOrder;
import com.mcan.rig.persistance.entity.Book;
import com.mcan.rig.persistance.entity.Customer;
import com.mcan.rig.persistance.entity.Order;
import com.mcan.rig.persistance.repository.OrderRepository;
import com.mcan.rig.service.BookService;
import com.mcan.rig.service.CustomerService;
import com.mcan.rig.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final BookService bookService;

    public OrderServiceImpl (OrderRepository orderRepository, CustomerService customerService, BookService bookService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.bookService = bookService;
    }

    @Override
    public List<Order> findAll () {
        return orderRepository.findAll();
    }

    @Override
    public Order create (Long quantity, String username, Long bookId) {
        if (quantity == null || username == null || bookId == null) {
            throw new OrderBadCreateRequestException();
        }
        Customer customer = customerService.findByUsername(username);
        Book book = bookService.findById(bookId);

        if (book.getStock() - quantity < 0 || quantity < 1) {
            throw new UnacceptableQuantityForCreatingOrder();
        }
        Order order = new Order();
        order.setQuantity(quantity);
        order.setCustomer(customer);
        order.setBook(book);
        order.setTotalPrice(order.getQuantity() * order.getBook().getPrice());
        Order createdOrder = orderRepository.save(order);
        // updating stock
        bookService.update(book.getId(), book.getStock() - order.getQuantity());
        return createdOrder;
    }

    @Override
    public Order findById (Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public List<Order> findByDateInterval (Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        return orderRepository.findAllByCreationDateIsBetween(startDate, endDate);
    }
}
