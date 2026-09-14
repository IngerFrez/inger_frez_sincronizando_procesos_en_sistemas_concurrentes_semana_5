package cl.duoc.speedfast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        List<Pedido> pedidos = List.of(
                new Pedido(1, "Avenida Libertad 850, Viña del Mar"),
                new Pedido(2, "Los Carrera 1200, Quilpué"),
                new Pedido(3, "Álvarez 640, Viña del Mar"),
                new Pedido(4, "Avenida Valparaíso 1050, Villa Alemana"),
                new Pedido(5, "Camino Internacional 2150, Viña del Mar"),
                new Pedido(6, "Blanco Encalada 720, Valparaíso")
        );

        pedidos.forEach(zonaDeCarga::agregarPedido);
        System.out.printf("SpeedFast inicia el despacho de %d pedidos.%n%n",
                zonaDeCarga.cantidadPedidosPendientes());

        ExecutorService ejecutor = Executors.newFixedThreadPool(3);
        ejecutor.execute(new Repartidor("Camila", zonaDeCarga));
        ejecutor.execute(new Repartidor("Diego", zonaDeCarga));
        ejecutor.execute(new Repartidor("Valentina", zonaDeCarga));
        ejecutor.shutdown();

        try {
            boolean finalizaron = ejecutor.awaitTermination(1, TimeUnit.MINUTES);

            if (!finalizaron) {
                System.err.println("El tiempo de espera se agotó. Se detendrán las tareas pendientes.");
                ejecutor.shutdownNow();
                return;
            }

            boolean todosEntregados = pedidos.stream()
                    .allMatch(pedido -> pedido.getEstado() == EstadoPedido.ENTREGADO);

            if (todosEntregados) {
                System.out.println("\nTodos los pedidos han sido entregados correctamente");
            } else {
                System.err.println("No fue posible completar todos los pedidos.");
            }
        } catch (InterruptedException e) {
            ejecutor.shutdownNow();
            Thread.currentThread().interrupt();
            System.err.println("El proceso principal fue interrumpido.");
        }
    }
}
