# Trabajo Práctico N° 6 - Middleware y Java RMI

## 1. Objetivos
- Comprender el rol y funcionamiento de la capa de middleware en aplicaciones distribuidas.
- Implementar interfaces de servicios remotos para invocar cómputo distribuido sin acoplamiento a detalles de socket.
- Analizar el patrón Stub/Skeleton, la transparencia de localización y la gestión de excepciones remotas.

## 2. Descripción de la Solución
El sistema implementa una arquitectura distribuida utilizando Java RMI (Remote Method Invocation).
Se compone de un Servidor que publica un objeto remoto con tres métodos de cómputo, y un Cliente que consume dichos métodos a través de un menú interactivo por consola.

### Funcionalidades implementadas:
1. Procesamiento Matemático: Calcula promedio, máximo, mínimo y desviación estándar de un arreglo de números.
2. Validación de Criterios: Valida formatos de DNI (8 dígitos) y CUIT (11 dígitos).
3. Filtro de Contenido: Recibe un arreglo de textos y devuelve solo los que coinciden con un patrón de búsqueda.

# Ejercicio 2: Cuestionario Teórico y Análisis de Arquitectura

## 1. Abstracción del Middleware
Explica la diferencia entre realizar una llamada remota mediante sockets puros versus hacerlo mediante Java RMI / gRPC. ¿Qué tareas complejas abstrae el middleware al desarrollador?**

Al usar sockets puros, el desarrollador debe encargarse manualmente de tareas de bajo nivel: definir el protocolo de comunicación (TCP/UDP), abrir y cerrar conexiones, convertir los datos a arreglos de bytes (serialización), manejar los flujos de entrada/salida (`DataInputStream` / `DataOutputStream`) y gestionar los hilos para atender múltiples clientes.

Al usar un middleware como Java RMI, todo esto queda abstraído. El desarrollador simplemente define una interfaz (el "contrato") y llama a un método como si estuviera en su propia máquina (transparencia de localización). El middleware se encarga automáticamente de:
- Serializar (Marshalling) y deserializar (Unmarshalling) los objetos.
- Abrir y gestionar las conexiones de red.
- Localizar el servidor remoto (a través del RMI Registry).
- Capturar y propagar las excepciones de red al cliente (como `RemoteException`).

## 2. Ciclo de Vida y Stub/Skeleton
Describe qué es un Stub (o Proxy) en el lado del cliente y qué función cumple el Skeleton / Dispatcher en el lado del servidor durante la marshalling y unmarshalling de datos.**

Stub (Cliente): Es un objeto proxy generado automáticamente que vive en la máquina del cliente. Cuando el cliente invoca un método remoto, en realidad está llamando al Stub. Este se encarga de recibir los parámetros, empaquetarlos en un formato de bytes (Marshalling), enviarlos por la red al servidor y luego esperar la respuesta para devolverla al cliente.

Skeleton / Dispatcher (Servidor):** Es el componente en el lado del servidor que escucha las peticiones entrantes. Recibe los bytes de la red, los deserializa (Unmarshalling) convirtiéndolos nuevamente en objetos Java, e invoca al método real en la implementación del objeto remoto. Una vez que el método termina, el Skeleton empaqueta el resultado y lo envía de vuelta al Stub del cliente.

## 3. Manejo de Fallos Parciales
¿Qué ocurre con el cliente si el servidor remoto se interrumpe abruptamente durante la ejecución de un método? Identifica las excepciones específicas que se generan e indica cómo deben gestionarse en un entorno productivo.**

Si el servidor se interrumpe abruptamente (por ejemplo, si se cae la red o se apaga la máquina), la conexión TCP subyacente se pierde. El cliente no recibe la respuesta esperada y Java RMI lanza una excepción de tipo **`java.rmi.RemoteException`** (o subclases específicas como `java.rmi.ConnectException` si no puede conectar, o `java.rmi.UnmarshalException` si la conexión se corta a mitad de la transferencia).

En un entorno productivo, estas excepciones **nunca deben ignorarse. Deben gestionarse mediante bloques `try-catch` para:
1. Informar al usuario que el servicio no está disponible temporalmente.
2. Implementar mecanismos de reintento (Retry) con un límite de intentos.
3. Registrar el error en un archivo de log (Logging) para que el equipo de infraestructura pueda diagnosticar la caída del servidor.
4. En sistemas más avanzados, derivar la petición a un servidor de respaldo.

5. captura de pantalla

<img width="944" height="176" alt="Captura de pantalla 2026-11-17 143725" src="https://github.com/user-attachments/assets/6d56dab5-1e69-4f38-912f-9983e1ea3f26" />


<img width="948" height="229" alt="Captura de pantalla 2026-11-17 143922" src="https://github.com/user-attachments/assets/0cec26c4-d642-4cfb-9c95-28e5b234c267" />


<img width="932" height="218" alt="Captura de pantalla 2026-11-17 144121" src="https://github.com/user-attachments/assets/26a2722d-e2b6-42ee-8f7a-a185284fdeb4" />


<img width="959" height="437" alt="Captura de pantalla 2026-11-17 144248" src="https://github.com/user-attachments/assets/e4de8e32-3d4d-4b45-8b5e-665926229aa6" />


<img width="958" height="152" alt="Captura de pantalla 2026-11-17 144314" src="https://github.com/user-attachments/assets/f1f03559-5cda-4788-b08c-aa7ae59c8226" />



