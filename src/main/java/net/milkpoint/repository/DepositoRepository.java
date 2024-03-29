package net.milkpoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import net.milkpoint.model.Deposito;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {
	@Query(value="select * from deposito d where d.excluido = 0 and d.confirmacao = 0", nativeQuery=true)
	public List<Deposito> buscaPendentes();
	
}
