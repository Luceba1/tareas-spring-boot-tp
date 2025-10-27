# Sistema de Gestión de Tareas — Fundamentos de Spring Boot

Proyecto integrador del **Trabajo Práctico** de la cátedra **Programación III — UTN FRM**.

> Objetivo: aplicar **IoC / DI**, **estereotipos**, **configuración con properties** y **profiles** para construir una app profesional.  
> Basado 100% en la guía del TP y en la serie "Java con Spring Boot de 0 a Pro".

---

## 🧩 Tecnologías
- Java **17**
- Spring Boot **3.3.x**
- Maven
- Lombok (recomendado)
- DevTools (DX en desarrollo)

## 📦 Estructura de paquetes
```
com.utn.tareas
├─ model
│  ├─ Prioridad.java
│  └─ Tarea.java
├─ repository
│  └─ TareaRepository.java
├─ service
│  ├─ MensajeService.java
│  ├─ MensajeDevService.java (@Profile("dev"))
│  └─ MensajeProdService.java (@Profile("prod"))
└─ TareasApplication.java (CommandLineRunner)
```
Las tareas se almacenan **en memoria** (lista interna).

## 🚀 Ejecución

### 1) Requisitos
- JDK **17+**
- Maven 3.9+ (o `./mvnw`)

### 2) Correr en **DEV** (por defecto)
```bash
./mvnw spring-boot:run
# o
mvn spring-boot:run
```

### 3) Cambiar a **PROD**
```bash
# Opción A: editar src/main/resources/application.properties
# spring.profiles.active=prod

# Opción B: variable de entorno
export SPRING_PROFILES_ACTIVE=prod   # Linux/Mac
set SPRING_PROFILES_ACTIVE=prod      # Windows (cmd)
$env:SPRING_PROFILES_ACTIVE="prod" # PowerShell

# Opción C: parámetro por CLI
mvn -Dspring-boot.run.profiles=prod spring-boot:run
java -jar target/tareas-1.0.0.jar --spring.profiles.active=prod
```

## 🧪 Qué verás al ejecutar
- Mensajes de bienvenida/despedida **según el profile** (`dev` vs `prod`)
- Configuración actual impresa: `app.nombre`, `app.max-tareas`, `app.mostrar-estadisticas`
- CRUD simple de tareas en memoria
- **Estadísticas** (total, completadas, pendientes)
- En PROD, las estadísticas pueden estar **deshabilitadas** según properties

## ⚙️ Properties y Profiles
- `application.properties` → `spring.profiles.active=dev`
- `application-dev.properties` → `app.max-tareas=10`, `app.mostrar-estadisticas=true`
- `application-prod.properties` → `app.max-tareas=1000`, `app.mostrar-estadisticas=false`

## 📸 Capturas requeridas
Incluí en el repositorio (en `/docs/` o en el README):
- Consola con **DEV**
- Consola con **PROD**  
*(Se observan diferencias de mensajes y límites)*

## 🧭 Cómo está alineado al TP
- DI por **constructor** en `TareaService`
- Estereotipos: `@Repository`, `@Service`
- `@Profile("dev")` y `@Profile("prod")` para beans condicionales de mensajes
- Inyección de valores con `@Value("${...}")`
- Límite por `app.max-tareas` y flag `app.mostrar-estadisticas`

## 👤 Autor
- **Nombre:** Lucas Pujada
- **Legajo:** 52736
- **Cátedra:** Programación III — UTN FRM

## 📚 Fuente del TP
Guía: _Trabajo Práctico - Fundamentos de Spring Boot_ (UTN).

---

> Tip: realizá **commits descriptivos** por cada parte del TP (P1/P2/P3...).
