# Trabajo Práctico N° 5 \- Concurrencia y Sincronización con el Modelo de Actores

**Materia: Desarrollo de Aplicaciones para Ambientes Distribuidos

# Objetivo

Implementar un sistema concurrente basado en el Modelo de Actores para comprender los principios de concurrencia y sincronización mediante el intercambio de mensajes asincrónicos.

El sistema elimina el uso de memoria compartida y mecanismos tradicionales de sincronización como synchronized o ReentrantLock, delegando la coordinación exclusivamente al paso de mensajes entre actores.


# Arquitectura Implementada

El sistema está compuesto por dos tipos principales de actores:

## ActorSensor

Representa un dispositivo sensor encargado de generar lecturas métricas.

Responsabilidades:

- Crear mensajes de lectura.
- Enviar mensajes de forma asincrónica.
- No almacenar estado compartido.

## ActorProcesador

Representa el nodo central del sistema.

Responsabilidades:

- Recibir mensajes desde los sensores.
- Procesar los mensajes en una cola FIFO (Mailbox).
- Mantener de forma privada el contador de lecturas recibidas.
- Calcular el promedio acumulado de los valores procesados.
- 
# Diagrama de Secuencia

<img width="1536" height="1024" alt="Designer (8)" src="https://github.com/user-attachments/assets/688fb4a3-47db-47c4-a461-d5520db5daff" />


# Operaciones del Modelo de Actores

## 1\. Spawn (Crear)

Se crean dinámicamente los actores necesarios para la ejecución.

Ejemplo:

- ActorSensor
- ActorProcesador

Cada actor posee su propia cola de mensajes (Mailbox).

---

## 2\. Send (Enviar)

Los sensores generan y envían una ráfaga de 500 mensajes de lectura hacia el ActorProcesador.

Cada mensaje contiene un valor métrico que será procesado posteriormente.

---

## 3\. State Change (Cambio de Estado)

Cuando el ActorProcesador recibe un mensaje:

1. Extrae el mensaje de su buzón.
2. Incrementa el contador interno.
3. Actualiza la suma acumulada.
4. Recalcula el promedio.
5. Queda preparado para procesar el siguiente mensaje.

Todo el estado permanece encapsulado dentro del actor.



# Estructura del Proyecto

TP5/

│

├── src/
    ├── Actor.java

    ├── ActorSensor.java
    ├── ActorProcesador.java

    ├── Mensaje.java

     ├── LecturaMensaje.java

     └── Main.java

├── README.md


└── capturas/

     ├── ejecucion1.png

    └── ejecucion2.png

# Resultados Obtenidos

Durante la ejecución se enviaron 500 mensajes concurrentes al ActorProcesador.

La cola FIFO garantizó que las lecturas fueran procesadas secuencialmente, evitando condiciones de carrera y manteniendo la consistencia del estado interno.

Al finalizar la ejecución se mostró:

- Cantidad total de mensajes procesados.
- Promedio acumulado de las lecturas recibidas.

Los resultados obtenidos demuestran el correcto funcionamiento del Modelo de Actores para la gestión segura de concurrencia sin mecanismos explícitos de bloqueo.

CAPTURAS 

1. Captura del inicio de ejecución.

<img width="959" height="478" alt="Captura de pantalla 2026-11-17 202846" src="https://github.com/user-attachments/assets/926bf397-1322-4d87-aad4-ecf97b9a4259" />

2. Captura del procesamiento de mensajes.

<img width="958" height="454" alt="Captura de pantalla 2026-11-17 202857" src="https://github.com/user-attachments/assets/5d5fa04d-18c4-435d-8cb1-edc1a84a23c1" />
 
4. Captura del resultado final.
5. Captura del contador y promedio calculado.
<img width="956" height="487" alt="Captura de pantalla 2026-11-17 202916" src="https://github.com/user-attachments/assets/6840e6fb-c308-43b4-959a-f10263eab987" />
