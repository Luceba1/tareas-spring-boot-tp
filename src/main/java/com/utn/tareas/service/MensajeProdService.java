package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class MensajeProdService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        System.out.println("👋 Bienvenido/a al sistema de tareas.");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("👋 Gracias por usar el sistema de tareas.");
    }
}
