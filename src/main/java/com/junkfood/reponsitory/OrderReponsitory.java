package com.junkfood.reponsitory;

import com.junkfood.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderReponsitory extends JpaRepository<Order,Integer> {

    @Query("SELECT o FROM Order o WHERE o.account.username=?1")
    List<Order> findByUsername (String username);

}
