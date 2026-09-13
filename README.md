# Semana 5

## Sincronizando procesos en sistemas concurrentes

Se implementó una zona de carga compartida protegida mediante sincronización para evitar que múltiples repartidores retiren el mismo pedido.

### Conceptos aplicados

- Synchronization (`synchronized`)
- Runnable
- ExecutorService
- Thread.sleep()
- Estados mediante enum
- Recurso compartido
- Control de concurrencia

### Componentes

- PedidoSimple
- EstadoPedido
- ZonaDeCarga
- Repartidor

### Funcionalidades

- Retiro seguro de pedidos
- Entrega concurrente
- Actualización de estados
- Prevención de condiciones de carrera