TRABAJO PRÁCTICO N° 3
Asignatura: Desarrollo de Aplicaciones para Ambientes Distribuidos
Unidad 2: Comunicación entre Procesos y Concurrencia
Tema: Sockets TCP/UDP con API java.net y Servidores Multihilo
Docente: Lic. Gabriel Artaza

3. Cuestionario Teórico
   Responder de forma concisa y fundamentada las siguientes preguntas:
1. Capa de Transporte: Explique las diferencias estructurales y operativas entre una
   comunicación orientada a conexión (TCP) y una sin conexión (UDP) en el contexto
   del paso de mensajes.

La capa de transporte de TCP (conexión) establece una conexión previa desde el emisor
al receptor esto garantiza una entrega confiable, ordenada y sin duplicados. 
Implementa control de flujo y congestión que garantizan la comunicación estable y confiable. 
ideal para aplicaciones donde la integridad de los datos es crítica ej transferencia de archivos, 
HTTP.

UDP (sin conexión) no requiere establecer conexión previa, cada mensaje es independiente no garantiza
la entrega ni orden, suelen existir pérdidas o duplicados como ventaja es más rápido y ligero, 
con, menor sobrecarga se usan en diferentes aplicaciones de tiempo real  por ejemplo en juegos 
online, streaming.


2. API java.net: ¿Cuál es la función del método ServerSocket.accept()?
   Justifique por qué es indispensable usar un hilo dedicado por cada cliente aceptado
   en un servidor TCP.


API java.net: ServerSocket.accept
este método bloque la ejecución hasta que un cliente solicita la conexión y devuelve un objeto 
socket que representa la conexión establecida con ese cliente se usa un hilo dedicado por cliente, 
cada cliente necesita ser atendido de forma independiente, si el servidor usará un único hilo, 
quedaría bloqueado atendiendo a un cliente y no podrá aceptar otros. con el multithreading, el servidor 
puede manejar múltiples clientes en paralelo, garantizando escalabilidad y respuesta inmediata.




3. Manejo de Errores y Resiliencia: ¿Qué sucede si el paquete UDP enviado por el
   emisor se pierde en la red? ¿Cómo lo detecta el receptor con setSoTimeout()?

Manejo de errores y resiliencia en UDP
Si un paquete UDP se pierde, el receptor nunca lo recibe porque UDP no tiene mecanismos de retransmisión
el receptor puede usar setSoTimeout() en socket esto define un tiempo máximo de espera para recibir un paquete.
Si el tiempo expira sin recibir nada, se lanza una excepción (SocketTimeoutException). 
Esto permite detectar la ausencia de respuesta y aplicar estrategias de resiliencia ejemplo en reintentar 
en vivo, notificar errores.




4. Criterios de Evaluación y Entregables
   ● Repositorio GitHub: Estructura limpia de carpetas (src/), commits significativos y
   código funcional.
   ● Archivo README.md: Debe contener las respuestas al cuestionario teórico, las
   instrucciones paso a paso para compilar y ejecutar los programas, y capturas
   demostrativas de al menos 3 clientes conectados simultáneamente al servidor TCP.
   ● Entrega en Classroom: Adjuntar únicamente la URL del repositorio


Ejercicio 1 - Chat Multihilo TCP
Ejecutar el servidor
Ejecutar:

ChatServer.java
El servidor escucha conexiones en el puerto 5000.


Ejecutar clientes
Abrir tres instancias de:

ChatClient.java
Cada cliente puede enviar mensajes que serán recibidos por los demás clientes conectados.




Ejercicio 2 - Comunicación UDP
Ejecutar receptor
Ejecutar:

UDPReceiver.java
El receptor queda escuchando datagramas en el puerto 6000.

Ejecutar emisor
Ejecutar:

UDPEmitter.java
El emisor envía periódicamente alertas UDP.



capturas de pantalla 
<img width="905" height="199" alt="Captura de pantalla 2026-10-29 173835" src="https://github.com/user-attachments/assets/6e0dd74a-070a-4309-b305-a72326953485" />

<img width="911" height="213" alt="Captura de pantalla 2026-10-29 180307" src="https://github.com/user-attachments/assets/45cebbab-e4fa-46e8-9b91-217000ae9933" />

<img width="930" height="209" alt="Captura de pantalla 2026-10-29 180319" src="https://github.com/user-attachments/assets/0d6ef62b-0e4d-4930-b79c-b6d27dddc51b" />

<img width="934" height="212" alt="Captura de pantalla 2026-10-29 180336" src="https://github.com/user-attachments/assets/00265877-9aa7-46d3-ac02-09e3124bcfc8" />

<img width="940" height="215" alt="Captura de pantalla 2026-10-29 180657" src="https://github.com/user-attachments/assets/81caecc1-e4d1-4ff3-8edb-0abf6e13deab" />

<img width="929" height="193" alt="Captura de pantalla 2026-10-29 180729" src="https://github.com/user-attachments/assets/84380d25-bcda-4b72-a52b-3658cc1a2623" />

<img width="935" height="212" alt="Captura de pantalla 2026-10-29 180808" src="https://github.com/user-attachments/assets/a31a1817-d8f1-4860-9df0-b0f2d342ca66" />

<img width="908" height="188" alt="Captura de pantalla 2026-10-29 180833" src="https://github.com/user-attachments/assets/56a9f27b-bf00-474a-b2eb-2049ee874d01" />
<img width="929" height="197" alt="Captura de pantalla 2026-10-29 183403" src="https://github.com/user-attachments/assets/e5bbe3e2-d8d8-4713-b929-b9d599796cd2" />







