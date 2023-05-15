package gateway.com.example.order.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import gateway.com.example.order.demo.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}
