package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TareaRepository {

    private final List<Tarea> tareas = new ArrayList<>();
    private final AtomicLong secuencia = new AtomicLong(0);

    public TareaRepository() {
        // Datos iniciales
        guardar(new Tarea(null, "Configurar proyecto base", false, Prioridad.MEDIA));
        guardar(new Tarea(null, "Implementar repositorio", false, Prioridad.ALTA));
        guardar(new Tarea(null, "Escribir README", false, Prioridad.BAJA));
    }

    public Tarea guardar(Tarea tarea) {
        if (tarea.getId() == null) {
            tarea.setId(secuencia.incrementAndGet());
        }
        // eliminar si ya existe con el mismo id y reinsertar (actualización simple)
        tareas.removeIf(t -> t.getId().equals(tarea.getId()));
        tareas.add(tarea);
        return tarea;
    }

    public List<Tarea> obtenerTodas() {
        return new ArrayList<>(tareas);
    }

    public Optional<Tarea> buscarPorId(Long id) {
        return tareas.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public boolean eliminarPorId(Long id) {
        return tareas.removeIf(t -> t.getId().equals(id));
    }
}
