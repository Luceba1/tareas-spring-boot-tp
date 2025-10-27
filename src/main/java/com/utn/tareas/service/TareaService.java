package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService {

    private final TareaRepository repository;

    @Value("${app.nombre:Sistema de Tareas}")
    private String appNombre;

    @Value("${app.max-tareas:100}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas:true}")
    private boolean mostrarEstadisticas;

    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    public Tarea agregarNueva(String descripcion, Prioridad prioridad) {
        List<Tarea> todas = repository.obtenerTodas();
        if (todas.size() >= maxTareas) {
            throw new IllegalStateException("No se puede agregar más tareas: se alcanzó el límite (" + maxTareas + ").");
        }
        Tarea nueva = new Tarea(null, descripcion, false, prioridad);
        return repository.guardar(nueva);
    }

    public List<Tarea> listarTodas() {
        return repository.obtenerTodas();
    }

    public List<Tarea> listarPendientes() {
        return repository.obtenerTodas().stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }

    public List<Tarea> listarCompletadas() {
        return repository.obtenerTodas().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    public void marcarComoCompletada(Long id) {
        repository.buscarPorId(id).ifPresent(t -> {
            t.setCompletada(true);
            repository.guardar(t);
        });
    }

    public String obtenerEstadisticas() {
        if (!mostrarEstadisticas) {
            return "Estadísticas deshabilitadas por configuración.";
        }
        int total = repository.obtenerTodas().size();
        long comp = repository.obtenerTodas().stream().filter(Tarea::isCompletada).count();
        long pend = total - comp;
        return String.format("Total: %d | Completadas: %d | Pendientes: %d", total, comp, pend);
    }

    public void imprimirConfiguracion() {
        System.out.println("== CONFIGURACIÓN ACTUAL ==");
        System.out.println("app.nombre=" + appNombre);
        System.out.println("app.max-tareas=" + maxTareas);
        System.out.println("app.mostrar-estadisticas=" + mostrarEstadisticas);
    }
}
