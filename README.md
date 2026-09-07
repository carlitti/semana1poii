# Proyecto SpeedFast
## Desarrollo Orientado a Objetos II

### Autor
Carlos Felipe González Cereceda

---

# Descripción

Este proyecto corresponde al desarrollo progresivo de las actividades formativas de la asignatura Desarrollo Orientado a Objetos II.

La aplicación modela el sistema de gestión de pedidos de la empresa de reparto **SpeedFast**, aplicando conceptos fundamentales de Programación Orientada a Objetos en Java.

---

# Tecnologías Utilizadas

- Java JDK 25
- IntelliJ IDEA
- Git
- GitHub

---

# Conceptos Aplicados

- Encapsulamiento
- Herencia
- Polimorfismo
- Sobrescritura de métodos (Override)
- Sobrecarga de métodos (Overload)
- Clases abstractas
- Interfaces
- Abstracción
- Reutilización de código
- ArrayList
- Desacoplamiento de responsabilidades
- Programación concurrente
- Runnable
- ExecutorService
- Manejo de excepciones

---

# Estructura del Proyecto

## Clase Base

- model.Pedido

## Clases Derivadas

- model.PedidoComida
- model.PedidoEncomienda
- model.PedidoExpress

## Interfaces

- interfaces.Despachable
- interfaces.Cancelable
- interfaces.Rastreable

## Clases de Apoyo

- controlador.ControladorDeEnvios
- model.Repartidor

## Clase Principal

- Main

---

# Semana 1
## Explorando la sobrecarga y sobreescritura en clases derivadas

Durante esta etapa se implementó una jerarquía de clases para representar distintos tipos de pedidos de la empresa SpeedFast.

### Funcionalidades

#### PedidoComida
- Verificación de mochila térmica.
- Asignación de repartidor.

#### PedidoEncomienda
- Validación de peso y embalaje.
- Asignación de repartidor.

#### PedidoExpress
- Búsqueda de repartidor más cercano.
- Asignación de repartidor.

### Conceptos Aplicados

- Herencia
- Sobrescritura de métodos
- Sobrecarga de métodos
- Polimorfismo

---

# Semana 2
## Definiendo una clase abstracta y su jerarquía

Durante esta etapa el proyecto fue extendido mediante la implementación de una clase abstracta llamada `Pedido`, incorporando el cálculo de tiempos estimados de entrega para cada tipo de pedido.

### Método Implementado

```java
mostrarResumen()
```

Permite visualizar la información básica de cada pedido.

### Método Abstracto

```java
calcularTiempoEntrega()
```

Es implementado de manera diferente por cada subclase.

### Lógica de Tiempo de Entrega

#### PedidoComida

```text
15 minutos + 2 minutos por cada kilómetro
```

#### PedidoEncomienda

```text
20 minutos + 1.5 minutos por kilómetro
```

#### PedidoExpress

```text
10 minutos base + 5 minutos extra si supera los 5 km
```

### Conceptos Aplicados

- Clases abstractas
- Abstracción
- Herencia
- Polimorfismo
- Reutilización de código

---

# Semana 3
## Diseñando un sistema orientado a objetos con clases abstractas, polimorfismo e interfaces

Durante esta etapa se integraron todos los conceptos desarrollados anteriormente, incorporando interfaces para desacoplar responsabilidades y mejorar la organización del sistema.

### Interfaces Implementadas

#### Despachable

```java
despachar()
```

Permite gestionar el despacho de pedidos.

#### Cancelable

```java
cancelar()
```

Permite cancelar pedidos o envíos.

#### Rastreable

```java
verHistorial()
```

Permite visualizar el historial de entregas.

### Clase ControladorDeEnvios

Se creó una clase encargada de:

- Gestionar despachos.
- Gestionar cancelaciones.
- Mantener historial de entregas.
- Centralizar operaciones del sistema.

### Funcionalidades Implementadas

- Asignación automática de repartidores.
- Asignación manual de repartidores.
- Cálculo de tiempos de entrega.
- Despacho de pedidos.
- Cancelación de envíos.
- Historial de entregas mediante ArrayList.

### Conceptos Aplicados

- Interfaces
- Polimorfismo
- Sobrescritura
- Sobrecarga
- Abstracción
- ArrayList
- Desacoplamiento de responsabilidades

---

# Semana 4
## Ejecutando tareas en paralelo con hilos en Java

Durante esta etapa se incorporó programación concurrente al sistema SpeedFast, permitiendo simular múltiples repartidores realizando entregas de forma simultánea.

### Clase Repartidor

Se implementó una clase llamada:

```java
Repartidor
```

La cual implementa:

```java
Runnable
```

Cada repartidor posee:

- Nombre del repartidor.
- Lista de pedidos asignados.
- Ejecución independiente mediante hilos.

### Concurrencia Implementada

Se utilizó:

```java
ExecutorService
```

para ejecutar múltiples repartidores en paralelo.

Cada repartidor:

- Recorre sus pedidos.
- Informa avances por consola.
- Simula tiempos de entrega mediante:

```java
Thread.sleep()
```

- Finaliza sus entregas de forma independiente.

### Funcionalidades Implementadas

- Ejecución simultánea de repartidores.
- Simulación de entregas concurrentes.
- Procesamiento paralelo de pedidos.
- Gestión de múltiples entregas.
- Finalización controlada de los hilos.

### Manejo de Excepciones

Se implementó manejo de:

```java
InterruptedException
```

para garantizar la continuidad y estabilidad de la ejecución concurrente.

### Ejemplo de Salida

```text
[Repartidor: Luis] Entregando PedidoExpress #103
[Repartidor: Daniela] Entregando PedidoEncomienda #105
[Repartidor: Camila] Entregando PedidoComida #101

[Repartidor: Luis] Pedido #103 entregado.
[Repartidor: Daniela] Pedido #105 entregado.
[Repartidor: Camila] Pedido #101 entregado.

===== SIMULACIÓN FINALIZADA =====
```

### Conceptos Aplicados

- Runnable
- Programación concurrente
- Hilos (Threads)
- ExecutorService
- Thread.sleep()
- Manejo de excepciones
- Paralelismo
- Reutilización de clases

---

# Estado del Proyecto

✅ Semana 1: Herencia, sobrescritura y sobrecarga.

✅ Semana 2: Clase abstracta y cálculo de tiempos de entrega.

✅ Semana 3: Interfaces, despacho, cancelación e historial de pedidos.

✅ Semana 4: Programación concurrente mediante hilos y ExecutorService.

---

# Repositorio

El proyecto se encuentra versionado mediante Git y alojado en GitHub para evidenciar el control de versiones y el desarrollo incremental de las actividades del curso.