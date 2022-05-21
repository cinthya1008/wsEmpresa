package com.spring.empresa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.empresa.entity.Empresa;

public interface EmpresaDao extends JpaRepository<Empresa, Long>{
	
	@Query(
			value = "select e.id_empresa, e.ruc, e.razon_social, e.direccion, e.estado"
					+ " from empresa e"
					+ " order by e.id_empresa desc"
					+ " limit 3"
			, nativeQuery = true)
	List<Empresa> getLastThreeRecords();

}