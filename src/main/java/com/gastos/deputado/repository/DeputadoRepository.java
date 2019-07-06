package com.gastos.deputado.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gastos.deputado.dto.Deputado;

@Repository
public interface DeputadoRepository extends CrudRepository<Deputado, Integer>{
	
}
