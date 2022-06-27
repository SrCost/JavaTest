package com.sigabem.repositories;

import com.sigabem.entities.ConsulEncomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncomendaRepository extends JpaRepository<ConsulEncomenda, Long> {

}
