package net.milkpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.milkpoint.model.Retirada;

@Repository
public interface RetiradaRepository extends JpaRepository<Retirada, Long> {

}
