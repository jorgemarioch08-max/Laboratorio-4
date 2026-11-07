# Laboratorio-4

### Integrantes
- Jorge Mario Chinchilla Sagastume - 22611259
- Kendrick Joad Gutierrez Maldonado - 22611069
- Sergio David Pineda Mendoza - 22611269

### Reglas de validación

### UtilTexto
- No se distinguen mayúsculas de minúsculas: todo se pasa a mayúsculas.
- Se ignoran las tildes: Á es igual a A, y Ü es igual a U.
- La Ñ es una letra distinta de la N.
- Las letras válidas son de la A a la Z, más la Ñ.
- Una palabra es válida si tiene entre 3 y 15 letras, sin espacios, números ni símbolos.

### Uso de Serializable

Serializable es una interfaz marcadora: no tiene métodos, solo indica que los objetos de
esa clase se pueden convertir a bytes.

- Cada clase lleva su propio serialVersionUID, porque este campo no se hereda.
- AdministradorPalabras es serializable porque JuegoAlAzar` lo usa como atributo.
- No declaramos el Random como transient porque Random ya es serializable. Si fuera
  transient, quedaría en null al deserializar el objeto.

### Excepciones

- AhorcadoException es abstracta, porque nunca se lanza directamente. Solo sirve como
  clase padre de las demás.
- LetraRepetidaException guarda la letra repetida y la devuelve con `getLetra()`.