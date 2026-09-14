# SpeedFast Semana 5

Proyecto desarrollado para la actividad **Sincronizando procesos en sistemas concurrentes** de la asignatura Desarrollo Orientado a Objetos II.

## Descripción

El programa simula una zona de carga compartida en la que tres repartidores procesan pedidos de manera concurrente. La operación de retiro está sincronizada para garantizar que cada pedido sea asignado a un único repartidor y evitar condiciones de carrera.

El ciclo de cada pedido es:

`PENDIENTE → EN_REPARTO → ENTREGADO`

## Tecnologías utilizadas

- Java 17
- Maven
- `ExecutorService`
- Implementación de `Runnable`
- Métodos `synchronized`

## Estructura del proyecto

- `Pedido`: contiene los datos y el estado de cada encomienda.
- `EstadoPedido`: define los estados válidos mediante un enum.
- `ZonaDeCarga`: administra de forma sincronizada el recurso compartido.
- `Repartidor`: representa la tarea ejecutada por cada hilo.
- `Main`: carga los pedidos, inicia tres hilos y espera su finalización.

## Ejecución en IntelliJ IDEA

1. Abrir IntelliJ IDEA.
2. Seleccionar **Open**.
3. Elegir la carpeta `SpeedFast`.
4. Esperar que Maven termine de cargar el proyecto.
5. Abrir `src/main/java/cl/duoc/speedfast/Main.java`.
6. Presionar el botón verde junto al método `main` y elegir **Run 'Main.main()'**.

## Resultado esperado

La consola muestra qué repartidor retira y entrega cada pedido. El orden puede cambiar en cada ejecución debido al comportamiento concurrente. Al finalizar debe aparecer:

```text
Todos los pedidos han sido entregados correctamente
```



