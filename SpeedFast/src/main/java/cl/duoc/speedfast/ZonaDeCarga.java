package cl.duoc.speedfast;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * Recurso compartido por todos los repartidores.
 * Sus operaciones críticas están sincronizadas para evitar condiciones de carrera.
 */
public class ZonaDeCarga {
    private final Deque<Pedido> pedidosPendientes = new ArrayDeque<>();

    public synchronized void agregarPedido(Pedido pedido) {
        Objects.requireNonNull(pedido, "El pedido no puede ser nulo");

        if (pedido.getEstado() != EstadoPedido.PENDIENTE) {
            throw new IllegalArgumentException("Solo se pueden agregar pedidos pendientes");
        }

        pedidosPendientes.offerLast(pedido);
    }

    /**
     * Retira un único pedido y cambia su estado dentro del mismo bloqueo.
     * De esta forma, ningún otro hilo puede retirar el mismo pedido.
     */
    public synchronized Pedido retirarPedido() {
        Pedido pedido = pedidosPendientes.pollFirst();

        if (pedido != null) {
            pedido.setEstado(EstadoPedido.EN_REPARTO);
        }

        return pedido;
    }

    public synchronized int cantidadPedidosPendientes() {
        return pedidosPendientes.size();
    }
}
