package cl.duoc.speedfast;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Tarea concurrente encargada de retirar y entregar pedidos.
 */
public class Repartidor implements Runnable {
    private final String nombre;
    private final ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.zonaDeCarga = Objects.requireNonNull(zonaDeCarga,
                "La zona de carga no puede ser nula");
    }

    @Override
    public void run() {
        try {
            Pedido pedido;

            while ((pedido = zonaDeCarga.retirarPedido()) != null) {
                System.out.printf("[%s] retiró el pedido %d. Estado: %s. Destino: %s%n",
                        nombre,
                        pedido.getId(),
                        pedido.getEstado(),
                        pedido.getDireccionEntrega());

                // Tiempo variable para hacer visible la ejecución simultánea.
                int tiempoEntrega = ThreadLocalRandom.current().nextInt(800, 1_501);
                Thread.sleep(tiempoEntrega);

                pedido.setEstado(EstadoPedido.ENTREGADO);
                System.out.printf("[%s] entregó el pedido %d. Estado: %s%n",
                        nombre,
                        pedido.getId(),
                        pedido.getEstado());
            }

            System.out.printf("[%s] finalizó: no quedan pedidos pendientes.%n", nombre);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.printf("[%s] fue interrumpido durante una entrega.%n", nombre);
        }
    }
}
