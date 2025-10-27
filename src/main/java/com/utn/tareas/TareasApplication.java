package com.utn.tareas;

import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

    private final TareaService tareaService;
    private final MensajeService mensajeService;

    public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
        this.tareaService = tareaService;
        this.mensajeService = mensajeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TareasApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // 1) Bienvenida
        mensajeService.mostrarBienvenida();

        // 2) Mostrar configuración actual
        tareaService.imprimirConfiguracion();

        // 3) Listar tareas iniciales
        System.out.println("\n== TAREAS INICIALES ==");
        tareaService.listarTodas().forEach(System.out::println);

        // 4) Agregar una nueva tarea
        System.out.println("\n== AGREGAR TAREA ==");
        tareaService.agregarNueva("Preparar entrega TP Spring Boot", com.utn.tareas.model.Prioridad.ALTA);

        // 5) Listar pendientes
        System.out.println("\n== TAREAS PENDIENTES ==");
        tareaService.listarPendientes().forEach(System.out::println);

        // 6) Marcar una tarea como completada (la primera de la lista si existe)
        tareaService.listarTodas().stream().findFirst().ifPresent(t -> {
            tareaService.marcarComoCompletada(t.getId());
            System.out.println("\nMarcada como completada: " + t.getId());
        });

        // 7) Mostrar estadísticas
        System.out.println("\n== ESTADÍSTICAS ==");
        System.out.println(tareaService.obtenerEstadisticas());

        // 8) Listar completadas
        System.out.println("\n== TAREAS COMPLETADAS ==");
        tareaService.listarCompletadas().forEach(System.out::println);

        // 9) Despedida
        mensajeService.mostrarDespedida();
    }
}
