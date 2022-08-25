package com.junkfood.reponsitory;

import com.junkfood.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountReponsitory extends JpaRepository<Account,String> {

    Optional<Account> findByUsername(String username);

    @Query("SELECT DISTINCT ar.accountAuth FROM Authority ar WHERE ar.role.id IN ('DIRE','STAF')")
    List<Account> getAdminstrators();

}
