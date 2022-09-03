package com.papeleria.v1.papeleria_v1.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.papeleria.v1.papeleria_v1.model.VentaModel;
@Repository
public interface VentaRepository extends CrudRepository<VentaModel, Integer>{
    
}
