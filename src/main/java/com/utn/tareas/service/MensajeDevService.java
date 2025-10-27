package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        System.out.println("👋 [DEV] Bienvenido/a al sistema de tareas (perfil DEV). ¡Vamos a construir y probar!");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("✅ [DEV] Fin de la ejecución. Revisá logs y estadísticas para seguir iterando.");
    }
}
