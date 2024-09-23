package com.store.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.store.Model.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID>{
	@Query(value="select * from tabla_tareas where nombre_tarea=:nombre_tarea or mes_entrega=:mes_entrega",nativeQuery = true	
			)

	List<TaskEntity> consultarTarea(@Param("nombre_tarea")String nombre_usuario, @Param("mes_entrega") String correo_usuario);
}
