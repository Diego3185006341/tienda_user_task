package com.tienda.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tienda.Model.TareasModel;

@Repository
public interface TareaRepository extends JpaRepository<TareasModel,String>{
	@Query(value="select * from tabla_tareas where nombre_tarea=:nombre_tarea or mes_entrega=:mes_entrega",nativeQuery = true	
			)

	List<TareasModel> consultarTarea(@Param("nombre_tarea")String nombre_usuario,@Param("mes_entrega") String correo_usuario);
}
