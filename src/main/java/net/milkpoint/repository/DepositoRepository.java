package net.milkpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.milkpoint.model.Deposito;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {

}
