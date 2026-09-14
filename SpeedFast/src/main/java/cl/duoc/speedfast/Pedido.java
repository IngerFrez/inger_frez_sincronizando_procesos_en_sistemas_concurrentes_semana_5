package cl.duoc.speedfast;

import java.util.Objects;

/**
 * Representa una encomienda que debe ser entregada por SpeedFast.
 */
public class Pedido {
    private int id;
    private String direccionEntrega;
    private EstadoPedido estado;

    public Pedido(int id, String direccionEntrega) {
        this.id = id;
        this.direccionEntrega = Objects.requireNonNull(direccionEntrega,
                "La dirección de entrega no puede ser nula");
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = Objects.requireNonNull(direccionEntrega,
                "La dirección de entrega no puede ser nula");
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido nuevoEstado) {
        this.estado = Objects.requireNonNull(nuevoEstado,
                "El estado no puede ser nulo");
    }

    /**
     * Sobrecarga solicitada en la guía para actualizar el estado desde texto.
     * El enum evita estados inválidos o errores de escritura.
     */
    public void setEstado(String nuevoEstado) {
        setEstado(EstadoPedido.valueOf(nuevoEstado.toUpperCase()));
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", direccionEntrega='" + direccionEntrega + '\'' +
                ", estado=" + estado +
                '}';
    }
}
