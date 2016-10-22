# AgenteInteligenteAutonomoRestos

Ejemplo del funcionamiento de un sistema inteligente autónomo para la búsqueda de reglas. Se ejemplifican la implementación de una Heuristica 

## Descripción del problema


Este ejemplo muestra como utilizar estos conceptos para un problema cotidiano y bastante alejado del típico ejemplo del robot que busca salir del laberinto como figura en el libro.

El problema que escogí se resume en la siguiente pregunta: ¿Debo esperar por una mesa en un restaurante o debo irme a otro lado? 
Vamos a encontrar una serie de reglas para resolver esta pregunta utilizando un Agente Inteligente Autónomo... o casi. 

Se tienen las siguientes variables, relacionadas con el Restaurante a evaluar:

01. Alternativa: si existe un restaurante adecuado alternativo cerca.
02. Bar: si el restaurante tiene un área de bar confortable en la que se pueda esperar.
03. Viernes-Sábado: si es viernes o sábado. 
04. Hambriento: si estamos hambrientos.
05. Clientes: cuánta gente hay en el restaurante, puede tomar los valores: Ninguno, Algunos o Lleno. 
06. Precio: el rango de precios del restaurante: barato, normal, caro
07. Lloviendo: si está lloviendo afuera.
08. Reserva: si se ha hecho una reserva
09. Tipo: la clase de restaurante: francés, italiano, chino, hamburguesería
10. TiempoEsperaEstimado: el tiempo de espera estimado por el dueño, puede ser: 0-10 minutos, 10-30 minutos, 30-60 minutos, más de 60 minutos

La definición de este problema la pueden encontrar en [1], pero no la solución que implementé.

Se cuenta con un archivo de 20 mil casos, de tipo CSV, con distintos valores para cada una de las variables mencionadas (en el orden anterior) y un último valor de tipo: true/false que indica si debo esperar por una mesa o no.

## Algunas consideraciones


1. Un Agente Inteligente realiza acciones, mediante sus actuadores. En el caso del robot esas podían ser: Avanzar, Girar, etc. Acá no hay acción posible ya que vamos a leer linea por linea un archivo CSV. Así que si ven el diagrama en [2] (capitulo que subí al grupo) toda la parte del plan de acción, etc. no se aplica en este ejemplo.

2.  Vamos a ir "olvidando" las teorías viejas cuando encontremos una teoría más general, ya que nos interesa encontrar aquellas reglas más generales. Esto es útil para este ejemplo, podría no ser siempre así.

3. Cuando una teoría falle una vez, dejaremos de actualizarla. (La olvidamos)

## Resultado


Si compilan y corren el ejemplo podrán ver que arroja este resultado: [resultados.txt] son 13 reglas que generan todos los casos.
Para obtener estas reglas simplemente mostramos las teorías exitosas (las que nunca fallaron) y que fueron aplicadas al menos 100 veces. 
Si bien esto es útil en este ejemplo, dependiendo del problema y la complejidad, a veces tendrán que contar con teorías que tienen casos desfavorables o bien que fueron usadas pocas veces.

Si observan las reglas encontradas, podrán ver que hay variables que no se consideran: precio y tipo y esto es correcto ya que los 20 mil casos fueron generados a partir del siguiente árbol: [docs/condicionesRestaurnte.png]. Las mismas reglas pueden verlas en la clase: GeneradorEjemplosRestaurante utilizada para generar los ejemplos.

## Referencias

1. Stuart Russell, Peter Norving. Inteligencia Artificial: Un Enfoque Moderno. 2da edición. Perason Prentice Hall Capitulo 18, pagina 745. 2006. 

2. García Martínez, Servente, Pasquini. Sistemas Inteligentes. La Nueva Librería. Capitulo 4, página 256. 2003