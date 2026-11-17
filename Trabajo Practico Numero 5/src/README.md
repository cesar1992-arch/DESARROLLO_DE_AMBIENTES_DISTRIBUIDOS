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

---

# Modelo de Comunicación

La comunicación entre actores se realiza únicamente mediante mensajes inmutables.

ActorSensor





Actualización de Estado

---

# Diagrama de Secuencia









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
2. Captura del procesamiento de mensajes.
3. Captura del resultado final.
4. Captura del contador y promedio calculado.
