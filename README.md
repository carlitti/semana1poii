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
- Reutilización de código
- Abstracción

---

# Estructura del Proyecto

## Clase Base

- Pedido

## Clases Derivadas

- PedidoComida
- PedidoEncomienda
- PedidoExpress

## Clase Principal

- Main

---

# Semana 1
## Explorando la sobrecarga y sobreescritura en clases derivadas

Durante esta etapa se implementó una jerarquía de clases para representar distintos tipos de pedidos de la empresa SpeedFast.

### Funcionalidades

#### Pedido Comida
- Verificación de mochila térmica.
- Asignación de repartidor.

#### Pedido Encomienda
- Validación de peso y embalaje.
- Asignación de repartidor.

#### Pedido Express
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