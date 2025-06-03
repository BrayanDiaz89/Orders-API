# 📦 Orders API

Una API RESTful desarrollada en **Java + Spring Boot** para procesar pedidos con lógica de negocio específica para Colombia (ciudades, estrato socioeconómico, descuentos y recargos personalizados).

---

## 🚀 Reto Técnico

**Desafío recibido:**

> Desarrolle un endpoint de API RESTful que:
> - Reciba un payload JSON con una lista de productos (precio y cantidad).
> - Calcule el costo total del pedido, incluyendo cargo por envío.
> - Aplique descuentos basados en el monto total del pedido, si corresponde.
> - Devuelva una respuesta JSON con el costo total y el descuento aplicado.
> - Considere el sistema de estrato colombiano y si el envío varía según este valor.

---

## 🧠 Reglas de negocio implementadas

### 💲 Subtotal del pedido
- Calculado multiplicando el precio unitario por la cantidad de cada producto.

### 📦 Costo de envío base
- Basado en el **peso total del pedido en libras**:
  | Peso (lb)       | Costo envío |
  |-----------------|-------------|
  | < 40            | $25.000     |
  | 40 - 100        | $50.000     |
  | > 100           | $100.000    |

### 🏙️ Recargo por ciudad y estrato
- Se aplica **solo si**:
  - El subtotal es menor o igual a $250.000
  - El estrato NO es 1 o 2

| Ciudad           | Recargo por producto |
|------------------|----------------------|
| Bogotá, Ibagué   | $4.000               |
| Cali, Medellín   | $5.000               |
| Eje cafetero, B/manga | $6.000         |
| Barranquilla     | $7.000               |

### 🎁 Descuentos por monto de compra (sobre subtotal)
| Subtotal ($COP)       | Descuento |
|------------------------|-----------|
| >= $100.000 y < $150.000 | 10%      |
| >= $150.000 y <= $250.000 | 20%     |
| > $250.000              | 25%      |

---

## ✅ Ejemplo de respuesta JSON

```json
{
	"idOrder": "ORD00004",
	"ciudadDestino": "MEDELLIN",
	"idClient": "CLIENT004",
	"precioTotalSinDescuento": 120000,
	"precioDeEnvio": 25000,
	"precioDeRecargoProductoPorEnvio": 0,
	"descuento": true,
	"porcentajeDescontado": 0.1,
	"valorTotalADescontar": 12000,
	"precioTotalAPagar": 133000,
	"pesoDelPedidoEnKg": 0.91
}
