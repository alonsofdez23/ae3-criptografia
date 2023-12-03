# AE-3. Criptografía

## Objetivos

Aprender a manejar la criptografía simétrica y asimétrica, así como los algoritmos hash.

## Pautas de elaboración

**Requerimiento 1**

Se pide hacer una aplicación que encripte frases introducidas por el usuario de manera simétrica.

La aplicación mostrará el siguiente menú

- Salir del programa
- Encriptar frase
- Desencriptar frase

Con la opción 1 el programa le pedirá al usuario una frase, la encriptará y la guardará en memoria.

Con la opción 2 el programa mostrará la frase desencriptándola.

Solo se guardará una frase al mismo tiempo. Se utilizará un método de encriptación simétrico.

**Requerimiento 2**

Se pide agregar seguridad a la aplicación para poder entrar. El programa arrancará con 3 objetos usuario que tendrá
su nombre de usuario y su contraseña “hasheada”. Los objetos permanecerán en memoria durante todo el programa.

Antes de mostrar el menú, el programa pedirá que se introduzca el nombre del usuario y su contraseña (sin “hashear”),
en caso de que sea correcto, se mostrará el menú y un mensaje de bienvenida al usuario con su nombre,
en caso contrario se le volverá a pedir hasta un máximo de tres veces. Si en tres intentos no se ha conseguido
introducir bien los datos de ningún usuario registrado, la aplicación se detendrá.

## Consideraciones

Para toda la actividad se valorará la claridad de código, la modularidad, la eficiencia de los algoritmos empleados
y comentarios explicativos sobre los puntos clave de la aplicación (JavaDoc o normales, lo que se considere
más apropiado). **En este ejercicio se valorará especialmente la modularidad, se deben hacer diferentes clases
agrupando la funcionalidad**.

Se recomienda el uso de GITHUB para realizar el trabajo de grupo. Se debe aclarar el trabajo aportado por cada
alumno en la actividad y la metodología seguida. En caso de aportar varias soluciones de diferentes autores, se
debe elegir por consenso cuál será la solución final escogida.

Para la entrega, se subirá el código fuente comprimido a la plataforma, **incluyendo un documento PDF con todo lo
necesario para demostrar el correcto funcionamiento de la actividad** (resultados, capturas de pantalla, ficheros,
fotos, etc.). No es necesario que el documento PDF sea muy extenso, pero SÍ que incluya, al menos, las capturas de
los resultados obtenidos y la explicación de los puntos clave de la actividad realizada. También puede ser válido
para la entrega subir el PDF con la URL de acceso al repositorio GITHUB sin necesidad de incluir el código fuente.