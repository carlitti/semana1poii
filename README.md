# Proyecto SpeedFast
## Desarrollo Orientado a Objetos II

### Autor
Carlos Felipe González Cereceda

---

# Descripción

Este proyecto corresponde al desarrollo progresivo de las actividades de la asignatura Desarrollo Orientado a Objetos II.

La aplicación modela el sistema de gestión de pedidos de la empresa **SpeedFast**, incorporando progresivamente conceptos de Programación Orientada a Objetos, concurrencia, sincronización e interfaces gráficas desarrolladas con Java Swing.

---

# Tecnologías utilizadas

- Java JDK 25
- IntelliJ IDEA
- Java Swing
- Git
- GitHub

---

# Semana 1

## Herencia, sobrescritura y sobrecarga

Durante la Semana 1 se desarrolló una jerarquía de clases para representar diferentes tipos de pedidos de SpeedFast.

### Clases principales

- Pedido
- PedidoComida
- PedidoEncomienda
- PedidoExpress

### Funcionalidades

- Asignación de repartidores.
- Validación de mochila térmica para pedidos de comida.
- Validación de peso y embalaje para encomiendas.
- Búsqueda del repartidor más cercano para pedidos express.

### Conceptos aplicados

- Encapsulamiento
- Herencia
- Polimorfismo
- Sobrescritura de métodos
- Sobrecarga de métodos

---

# Semana 2

## Clase abstracta y cálculo de tiempos de entrega

Durante la Semana 2 se modificó la clase `Pedido` para convertirla en una clase abstracta.

Se incorporó el método:

```java
calcularTiempoEntrega()
```

Cada tipo de pedido implementa su propia lógica para calcular el tiempo estimado.

### PedidoComida

Tiempo de entrega:

```text
15 minutos + 2 minutos por kilómetro
```

### PedidoEncomienda

Tiempo de entrega:

```text
20 minutos + 1.5 minutos por kilómetro
```

### PedidoExpress

Tiempo de entrega:

```text
10 minutos base + 5 minutos adicionales si la distancia supera los 5 km
```

### Conceptos aplicados

- Clases abstractas
- Métodos abstractos
- Herencia
- Polimorfismo
- Reutilización de código

---

# Semana 3

## Interfaces y gestión de envíos

Durante la Semana 3 se incorporaron interfaces para separar las responsabilidades del sistema.

### Interfaces implementadas

- Despachable
- Cancelable
- Rastreable

### Clase ControladorDeEnvios

Se incorporó una clase encargada de gestionar diferentes operaciones relacionadas con los pedidos.

### Funcionalidades

- Despacho de pedidos.
- Cancelación de pedidos.
- Registro del historial de entregas.
- Asignación automática de repartidores.
- Asignación manual de repartidores.

### Conceptos aplicados

- Interfaces
- Polimorfismo
- Sobrescritura
- Sobrecarga
- Abstracción
- ArrayList
- Separación de responsabilidades

---

# Semana 4

## Programación concurrente

Durante la Semana 4 se incorporó programación concurrente para simular múltiples repartidores realizando entregas simultáneamente.

### Clase Repartidor

La clase `Repartidor` implementa:

```java
Runnable
```

Cada repartidor puede procesar pedidos de manera independiente.

### Herramientas utilizadas

```java
Runnable
Thread.sleep()
ExecutorService
```

### Funcionalidades

- Ejecución simultánea de repartidores.
- Procesamiento paralelo de pedidos.
- Simulación de tiempos de entrega.
- Manejo de excepciones.
- Finalización controlada mediante ExecutorService.

### Conceptos aplicados

- Programación concurrente
- Hilos
- Runnable
- ExecutorService
- Thread.sleep()
- Manejo de InterruptedException

---

# Semana 5

## Sincronizando procesos en sistemas concurrentes

Durante la Semana 5 se implementó una zona de carga compartida protegida mediante sincronización para evitar que múltiples repartidores retiren el mismo pedido.

### Componentes

- PedidoSimple
- EstadoPedido
- ZonaDeCarga
- Repartidor

### Estados de los pedidos

Se utilizó un `enum` denominado `EstadoPedido` con los siguientes estados:

```text
PENDIENTE
EN_REPARTO
ENTREGADO
```

### ZonaDeCarga

La clase `ZonaDeCarga` representa un recurso compartido entre los diferentes repartidores.

Se utilizó:

```java
synchronized
```

para controlar el acceso concurrente a los pedidos.

### Funcionalidades

- Retiro seguro de pedidos.
- Entrega concurrente.
- Actualización del estado de los pedidos.
- Prevención de condiciones de carrera.
- Control de acceso al recurso compartido.

### Conceptos aplicados

- Synchronization
- synchronized
- Runnable
- ExecutorService
- Thread.sleep()
- Enum
- Recursos compartidos
- Control de concurrencia
- Prevención de condiciones de carrera

---

# Semana 6

## Diseñando interfaces gráficas para aplicaciones en Java

Durante la Semana 6 se incorporó una interfaz gráfica de escritorio al sistema SpeedFast utilizando **Java Swing**.

La aplicación permite realizar las principales operaciones de gestión de pedidos mediante ventanas, formularios, botones y tablas.

Los pedidos se mantienen temporalmente en memoria durante la ejecución de la aplicación.

---

## Organización de Semana 6

Para separar las responsabilidades de la aplicación se utilizaron los siguientes paquetes:

```text
modelo
vista
main
```

### Paquete modelo

Contiene las clases relacionadas con los pedidos y la gestión de la información.

Entre ellas:

- Pedido
- PedidoComida
- PedidoEncomienda
- PedidoExpress
- GestorPedidos

### Paquete vista

Contiene las interfaces gráficas desarrolladas utilizando Java Swing.

- VentanaPrincipal
- VentanaRegistroPedido
- VentanaListaPedidos

### Paquete main

Contiene la clase:

- Main

que inicia la aplicación gráfica.

---

# Ventana Principal

La clase `VentanaPrincipal` representa el menú principal del sistema.

Permite acceder a:

- Registrar Pedido.
- Listar Pedidos.
- Asignar Repartidor / Iniciar Entrega.

La ventana fue implementada utilizando `JFrame`.

---

# Registro de Pedidos

La clase `VentanaRegistroPedido` permite registrar nuevos pedidos.

El formulario incluye:

- ID del pedido.
- Dirección de entrega.
- Tipo de pedido.
- Botón Guardar Pedido.

El tipo de pedido se selecciona utilizando un:

```java
JComboBox
```

con las opciones:

```text
Comida
Encomienda