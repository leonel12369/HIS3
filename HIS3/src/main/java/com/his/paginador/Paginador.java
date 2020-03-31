package com.his.paginador;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.his.model.Rol;

public interface Paginador extends PagingAndSortingRepository<Rol, Integer>{


	
}
