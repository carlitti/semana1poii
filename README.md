---

# Semana 7

## Conectando aplicaciones Java con bases de datos mediante JDBC

Durante la Semana 7 se incorporó persistencia de datos al sistema SpeedFast mediante una conexión entre la aplicación Java y una base de datos MySQL utilizando JDBC.

A diferencia de las semanas anteriores, donde los datos se almacenaban temporalmente en memoria, los pedidos, repartidores y entregas ahora pueden almacenarse de forma persistente en una base de datos.

---

## Base de datos

Se creó la base de datos:

```text
speedfast_db
```

La base de datos contiene las siguientes tablas:

```text
pedido
repartidor
entrega
```

### Tabla pedido

Almacena:

- ID del pedido.
- Dirección de entrega.
- Tipo de pedido.
- Estado del pedido.

Los tipos utilizados son:

```text
COMIDA
ENCOMIENDA
EXPRESS
```

Los pedidos se registran inicialmente con estado:

```text
PENDIENTE
```

### Tabla repartidor

Almacena:

- ID del repartidor.
- Nombre del repartidor.

### Tabla entrega

Permite relacionar un pedido con un repartidor.

Almacena:

- ID de la entrega.
- ID del pedido.
- ID del repartidor.
- Fecha.
- Hora.

Se implementaron claves foráneas para mantener la relación entre las tablas `pedido`, `repartidor` y `entrega`.

---

## Conexión JDBC

Se agregó MySQL Connector/J al proyecto para permitir la comunicación entre Java y MySQL.

La conexión se administra mediante la clase:

```java
ConexionBD
```

La aplicación establece conexión con:

```text
jdbc:mysql://localhost:3306/speedfast_db
```

Para abrir las conexiones se utiliza:

```java
DriverManager
```

También se implementó manejo de excepciones mediante:

```java
SQLException
```

y cierre automático de recursos utilizando `try-with-resources`.

---

## Patrón DAO

Para separar la lógica de acceso a datos del resto de la aplicación se implementaron las siguientes clases:

```text
PedidoDAO
RepartidorDAO
EntregaDAO
```

### PedidoDAO

Permite:

- Guardar pedidos en MySQL.
- Consultar pedidos registrados.
- Recuperar información para mostrarla en la interfaz gráfica.

Las operaciones utilizan:

```java
PreparedStatement
ResultSet
```

### RepartidorDAO

Permite:

- Registrar repartidores.
- Consultar los repartidores almacenados en la base de datos.

La información obtenida desde MySQL es transformada nuevamente en objetos Java.

### EntregaDAO

Permite registrar una entrega relacionando:

```text
Pedido
   +
Repartidor
   +
Fecha
   +
Hora
```

La operación utiliza los identificadores correspondientes del pedido y del repartidor.

---

## Integración con Java Swing

La interfaz gráfica desarrollada durante la Semana 6 fue integrada con la base de datos MySQL.

El flujo de registro funciona de la siguiente manera:

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

Al presionar **Guardar Pedido**, la información ingresada en el formulario se almacena directamente en la tabla `pedido`.

---

## Listado de pedidos desde MySQL

La ventana `VentanaListaPedidos` consulta directamente los pedidos almacenados en la base de datos.

El flujo utilizado es:

```text
MySQL
  |
  v
PedidoDAO
  |
  v
ResultSet
  |
  v
List<Pedido>
  |
  v
JTable
```

El `JTable` muestra:

- ID.
- Dirección.
- Tipo.
- Tiempo estimado.

El botón de actualización permite consultar nuevamente la información almacenada en MySQL.

---

## Registro de repartidores y entregas

Desde la ventana principal es posible seleccionar un pedido registrado y asignarle un repartidor.

Cuando se inicia una entrega:

1. Se selecciona un pedido almacenado en MySQL.
2. Se ingresa el nombre del repartidor.
3. El repartidor se registra en la tabla `repartidor`.
4. MySQL genera el ID del repartidor.
5. Se registra la relación entre el pedido y el repartidor en la tabla `entrega`.
6. Se almacena la fecha y hora de la operación.
7. Se muestra una confirmación al usuario.

---

## Persistencia de datos

Se comprobó el funcionamiento de la persistencia cerrando completamente la aplicación y ejecutándola nuevamente.

Los pedidos registrados anteriormente continuaron disponibles al consultar la base de datos desde la ventana de listado.

Esto demuestra que la información ya no depende únicamente de una colección almacenada temporalmente en memoria.

---

## Componentes utilizados

Durante la Semana 7 se utilizaron:

- MySQL
- JDBC
- MySQL Connector/J
- DriverManager
- Connection
- PreparedStatement
- ResultSet
- SQLException
- Patrón DAO
- Java Swing
- JTable
- JOptionPane
- Claves primarias
- Claves foráneas
- Persistencia de datos

---

## Estructura de acceso a datos

Se incorporó el paquete:

```text
dao
```

con las clases:

```text
dao
├── ConexionBD.java
├── PedidoDAO.java
├── RepartidorDAO.java
└── EntregaDAO.java
```

Estas clases mantienen separada la lógica de acceso a MySQL de las clases correspondientes al modelo y la interfaz gráfica.

---

## Funcionalidades implementadas en Semana 7

- Conexión de Java con MySQL mediante JDBC.
- Creación de la base de datos `speedfast_db`.
- Creación de las tablas `pedido`, `repartidor` y `entrega`.
- Implementación de claves primarias y foráneas.
- Registro de pedidos desde Java Swing.
- Persistencia de pedidos en MySQL.
- Consulta de pedidos almacenados.
- Visualización de los pedidos mediante JTable.
- Registro de repartidores.
- Registro de entregas.
- Relación entre pedidos y repartidores.
- Manejo de errores SQL.
- Cierre automático de recursos JDBC.
- Persistencia de datos después de cerrar la aplicación.

---

# Estado del proyecto

✅ Semana 1: Herencia, sobrescritura y sobrecarga.

✅ Semana 2: Clases abstractas y cálculo de tiempos de entrega.

✅ Semana 3: Interfaces, despacho, cancelación e historial.

✅ Semana 4: Programación concurrente mediante Runnable y ExecutorService.

✅ Semana 5: Sincronización, estados y zona de carga compartida.

✅ Semana 6: Interfaz gráfica mediante Java Swing.

✅ Semana 7: Persistencia de datos mediante MySQL, JDBC y patrón DAO.

---

# Autor

Carlos Felipe González Cereceda

## Desarrollo Orientado a Objetos II

Proyecto académico SpeedFast.