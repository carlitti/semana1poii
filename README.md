# SpeedFast - Semana 8

## Desarrollo Orientado a Objetos II

Durante la Semana 8 se completó el ciclo funcional del sistema **SpeedFast**, integrando las operaciones CRUD con la interfaz gráfica Java Swing y la base de datos MySQL mediante JDBC.

El sistema permite actualmente gestionar de forma persistente:

- Pedidos.
- Repartidores.
- Entregas.

A diferencia de etapas anteriores, ahora es posible **registrar, consultar, editar y eliminar información directamente desde la interfaz gráfica**, manteniendo los datos almacenados en MySQL.

---

# Tecnologías utilizadas

El proyecto utiliza:

- Java.
- IntelliJ IDEA.
- Java Swing.
- MySQL.
- JDBC.
- MySQL Connector/J.
- Patrón DAO.
- Programación Orientada a Objetos.
- Git y GitHub.

---

# Base de datos

La aplicación utiliza la base de datos:

```text
speedfast_db
```

La base contiene las siguientes tablas:

```text
pedido
repartidor
entrega
```

---

## Tabla `pedido`

Almacena la información de los pedidos realizados en el sistema.

Campos principales:

- `id`
- `direccion`
- `tipo`
- `estado`

El campo `id` es generado automáticamente mediante `AUTO_INCREMENT`.

Los tipos de pedido utilizados son:

```text
COMIDA
ENCOMIENDA
EXPRESS
```

Los estados disponibles son:

```text
PENDIENTE
EN_REPARTO
ENTREGADO
```

---

## Tabla `repartidor`

Almacena los repartidores registrados en SpeedFast.

Campos:

- `id`
- `nombre`

El identificador se genera automáticamente mediante MySQL.

---

## Tabla `entrega`

Relaciona un pedido con un repartidor.

Campos:

- `id`
- `id_pedido`
- `id_repartidor`
- `fecha`
- `hora`

`id_pedido` e `id_repartidor` permiten relacionar cada entrega con los registros correspondientes de las tablas `pedido` y `repartidor`.

Se utilizan claves foráneas para mantener la integridad de las relaciones entre las tablas.

---

# Conexión con MySQL mediante JDBC

La conexión entre Java y MySQL se administra mediante:

```java
ConexionBD
```

La aplicación se conecta a:

```text
jdbc:mysql://localhost:3306/speedfast_db
```

Para establecer la conexión se utiliza:

```java
DriverManager
```

y objetos JDBC como:

```java
Connection
PreparedStatement
ResultSet
```

También se utilizan bloques `try-with-resources` para cerrar automáticamente las conexiones y recursos utilizados.

Las excepciones relacionadas con la base de datos son controladas mediante:

```java
SQLException
```

---

# Patrón DAO

Para separar la lógica de acceso a datos del modelo y de la interfaz gráfica se utilizan clases DAO.

El paquete:

```text
dao
```

contiene principalmente:

```text
ConexionBD.java
PedidoDAO.java
RepartidorDAO.java
EntregaDAO.java
```

Cada DAO contiene las operaciones necesarias para trabajar con su entidad correspondiente.

---

## RepartidorDAO

Permite realizar las operaciones:

```text
CREATE  → guardar()
READ    → listarTodos()
UPDATE  → actualizar()
DELETE  → eliminar()
```

Funciones disponibles:

- Registrar nuevos repartidores.
- Obtener todos los repartidores almacenados.
- Editar el nombre de un repartidor.
- Eliminar repartidores.

---

## PedidoDAO

Implementa las operaciones CRUD para los pedidos:

```text
CREATE  → guardar()
READ    → listarTodos()
UPDATE  → actualizar()
DELETE  → eliminar()
```

Permite:

- Registrar pedidos.
- Consultar pedidos almacenados.
- Modificar dirección, tipo y estado.
- Eliminar pedidos.
- Recuperar los datos desde MySQL para mostrarlos en la interfaz.

Cuando se registra un pedido, MySQL genera automáticamente su identificador.

---

## EntregaDAO

Implementa las operaciones CRUD correspondientes a las entregas:

```text
CREATE  → guardar()
READ    → listarTodos()
UPDATE  → actualizar()
DELETE  → eliminar()
```

Además incluye consultas adicionales:

```java
listarPorPedido()
listarPorRepartidor()
```

Esto permite filtrar las entregas según:

- Pedido.
- Repartidor.

Cada entrega almacena:

```text
Pedido
   +
Repartidor
   +
Fecha
   +
Hora
```

---

# Interfaz gráfica

La aplicación utiliza **Java Swing**.

Entre los componentes utilizados se encuentran:

```text
JFrame
JPanel
JTable
JTextField
JComboBox
JButton
JOptionPane
```

La ventana principal funciona como menú del sistema.

Actualmente permite acceder a:

```text
Registrar Pedido
Gestionar Pedidos
Gestionar Repartidores
Gestionar Entregas
```

---

# Registro de pedidos

La ventana:

```java
VentanaRegistroPedido
```

permite ingresar:

- Dirección.
- Tipo de pedido.
- Estado.

El identificador no debe ser ingresado manualmente porque es generado automáticamente por MySQL.

El flujo general es:

```text
VentanaRegistroPedido
        |
        v
    PedidoDAO
        |
        v
       JDBC
        |
        v
      MySQL
```

Antes de registrar un pedido se validan los datos ingresados.

---

# Gestión de pedidos

La ventana:

```java
VentanaListaPedidos
```

permite visualizar los pedidos almacenados en una `JTable`.

La tabla muestra:

- ID.
- Dirección.
- Tipo.
- Estado.
- Tiempo estimado.

También permite:

- Actualizar la tabla.
- Editar pedidos.
- Eliminar pedidos.
- Filtrar por tipo.
- Filtrar por estado.

Ejemplo de filtros disponibles:

```text
Tipo:
TODOS
COMIDA
ENCOMIENDA
EXPRESS

Estado:
TODOS
PENDIENTE
EN_REPARTO
ENTREGADO
```

---

# Gestión de repartidores

La ventana:

```java
VentanaRepartidores
```

permite:

- Registrar repartidores.
- Listar repartidores.
- Editar repartidores.
- Eliminar repartidores.

Los datos son mostrados mediante una tabla:

```text
ID | Nombre
```

La aplicación valida que el nombre del repartidor no se encuentre vacío antes de registrar o actualizar información.

---

# Gestión de entregas

La ventana:

```java
VentanaEntregas
```

permite administrar completamente las entregas.

Para registrar una entrega se selecciona:

```text
Pedido
Repartidor
Fecha
Hora
```

Los pedidos y repartidores se cargan automáticamente desde MySQL utilizando `JComboBox`.

Por ejemplo:

```text
Pedido:
#101 - Comida - sucre

Repartidor:
1 - carlos
```

Aunque el usuario observa información legible en los combos, internamente la aplicación conserva los identificadores necesarios para almacenar la relación en MySQL.

El flujo utilizado es:

```text
PedidoDAO ──────┐
                |
                v
          VentanaEntregas
                ^
                |
RepartidorDAO ──┘
                |
                v
          EntregaDAO
                |
                v
              MySQL
```

---

## Funciones de la gestión de entregas

La ventana permite:

- Registrar entregas.
- Listar entregas.
- Editar entregas.
- Eliminar entregas.
- Filtrar por pedido.
- Filtrar por repartidor.
- Combinar ambos filtros.

La tabla muestra:

```text
ID | Pedido | Repartidor | Fecha | Hora
```

---

# Validaciones

Antes de ejecutar operaciones sobre la base de datos se realizan distintas validaciones.

Entre ellas:

- Campos obligatorios.
- Nombre de repartidor no vacío.
- Dirección de pedido no vacía.
- Longitud válida de textos.
- Selección de pedido.
- Selección de repartidor.
- Fecha obligatoria.
- Hora obligatoria.
- Formato válido de fecha.
- Formato válido de hora.
- Selección de un registro antes de editar.
- Selección de un registro antes de eliminar.

Ejemplo de fecha válida:

```text
2026-10-02
```

Ejemplo de hora válida:

```text
18:30
```

---

# Manejo de errores

Los DAO capturan errores producidos durante las operaciones SQL mediante:

```java
try {
    // operación
} catch (SQLException e) {
    // manejo del error
}
```

La interfaz entrega retroalimentación al usuario utilizando:

```java
JOptionPane
```

Se muestran mensajes para indicar:

- Operaciones exitosas.
- Datos incompletos.
- Formatos incorrectos.
- Errores durante registros.
- Errores durante modificaciones.
- Errores durante eliminaciones.

Las conexiones JDBC utilizan `try-with-resources`, permitiendo cerrar automáticamente:

```text
Connection
PreparedStatement
ResultSet
```

---

# Persistencia de datos

Toda la información registrada permanece almacenada en MySQL después de cerrar la aplicación.

Por ejemplo:

```text
Java Swing
    |
    v
DAO
    |
    v
JDBC
    |
    v
MySQL
```

Al volver a ejecutar el programa, las ventanas consultan nuevamente la base de datos y muestran los registros almacenados.

---

# Organización del proyecto

La aplicación mantiene separación entre las distintas responsabilidades.

Una estructura simplificada es:

```text
src
│
├── controlador
│
├── dao
│   ├── ConexionBD.java
│   ├── PedidoDAO.java
│   ├── RepartidorDAO.java
│   └── EntregaDAO.java
│
├── interfaces
│
├── main
│   └── Main.java
│
├── modelo
│   ├── Pedido.java
│   ├── PedidoComida.java
│   ├── PedidoEncomienda.java
│   ├── PedidoExpress.java
│   ├── EstadoPedido.java
│   ├── Repartidor.java
│   ├── Entrega.java
│   └── ZonaDeCarga.java
│
└── vista
    ├── VentanaPrincipal.java
    ├── VentanaRegistroPedido.java
    ├── VentanaListaPedidos.java
    ├── VentanaRepartidores.java
    └── VentanaEntregas.java
```

Esta estructura permite mantener separadas:

```text
Modelo
  ↓
DAO
  ↓
Base de datos

Vista
  ↓
DAO
```

---

# Funcionalidades implementadas en Semana 8

- CRUD completo de repartidores.
- CRUD completo de pedidos.
- CRUD completo de entregas.
- Registro persistente mediante MySQL.
- Uso de JDBC.
- Uso de `PreparedStatement`.
- Uso de `ResultSet`.
- Recuperación de IDs generados mediante `AUTO_INCREMENT`.
- Interfaz gráfica con Java Swing.
- Visualización de datos mediante `JTable`.
- Selección mediante `JComboBox`.
- Validación de campos.
- Validación de fecha y hora.
- Mensajes mediante `JOptionPane`.
- Filtros de pedidos por tipo.
- Filtros de pedidos por estado.
- Filtros de entregas por pedido.
- Filtros de entregas por repartidor.
- Edición de registros desde la interfaz.
- Eliminación de registros desde la interfaz.
- Cierre automático de recursos JDBC.
- Manejo de excepciones SQL.
- Separación por capas.
- Persistencia de datos.

---

# Flujo general de la aplicación

```text
                    ┌─────────────────────┐
                    │  Ventana Principal  │
                    └──────────┬──────────┘
                               |
          ┌────────────────────┼────────────────────┐
          |                    |                    |
          v                    v                    v
      Pedidos             Repartidores          Entregas
          |                    |                    |
          v                    v                    v
     PedidoDAO          RepartidorDAO          EntregaDAO
          |                    |                    |
          └────────────────────┼────────────────────┘
                               |
                               v
                             JDBC
                               |
                               v
                            MySQL
```

---

# Ejecución

Para ejecutar el proyecto se debe contar con:

1. Java instalado.
2. IntelliJ IDEA.
3. MySQL en ejecución.
4. Base de datos `speedfast_db`.
5. MySQL Connector/J configurado en el proyecto.

La aplicación se inicia desde:

```text
src/main/Main.java
```

---

# Estado del proyecto

✅ Semana 1: Herencia, sobrescritura y sobrecarga.

✅ Semana 2: Clases abstractas y cálculo de tiempos de entrega.

✅ Semana 3: Interfaces, despacho, cancelación e historial.

✅ Semana 4: Programación concurrente mediante `Runnable` y `ExecutorService`.

✅ Semana 5: Sincronización, estados y zona de carga compartida.

✅ Semana 6: Interfaz gráfica mediante Java Swing.

✅ Semana 7: Persistencia mediante MySQL, JDBC y patrón DAO.

✅ **Semana 8: Operaciones CRUD completas, validaciones y gestión integral mediante Java Swing y MySQL.**

---

# Autor

**Carlos Felipe González Cereceda**

## Desarrollo Orientado a Objetos II

Proyecto académico **SpeedFast**.