package com.junkfood.reponsitory;

import com.junkfood.entity.Account;
import com.junkfood.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityReponsitory extends JpaRepository<Authority,Integer> {
    @Query("SELECT DISTINCT a FROM Authority a WHERE a.accountAuth IN ?1")
    List<Authority> authoritiesOf(List<Account> accounts);
}
