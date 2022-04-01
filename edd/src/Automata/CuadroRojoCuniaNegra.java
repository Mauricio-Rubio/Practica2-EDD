/**
 * @author Mauricio Rubio Haro
 * @author Kevin Isaac Alcantara Estrada
 */
package edd.src.Automata;

import edd.src.Estructuras.*;
import java.util.Random;

/** Automata que forma cuadros rojos y cunias negras mientras los colores verdes y amarillo se mueven alrededor, con color azul de fondo.
 *A mayor velocidad en las evoluciones, más se aprecia la obra.
 * @author Alcántara Estrada Kevin Isaac
 * @version 4.0
 */
public class CuadroRojoCuniaNegra extends AC {

  int[][] Maux2 = new int[Imagen.numCells][Imagen.numCells];
  int[][] MauxCopia = new int[Imagen.numCells][Imagen.numCells];
  int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];

  /*
   *Metodo que pinta una matriz de Blanco y le da valores aleatorios a las casillas.
   *
   */
  @Override
  public int[][] getAutomata() {
    int aux1;
    for (int i = 0; i < Maux2.length; i++) {
      for (int j = 0; j < Maux2.length; j++) {
        Maux2[i][j] = 2;
        MauxCopia[i][j] = 2;
      }
    }

    //Modifico cada valor de la matriz para pintarlo de una manera específica
    for (int i = 0; i < Maux2.length; i++) {
      for (int j = 0; j < Maux2.length; j++) {
        //Pintamos los cuadros impares del cuadrante superior izquierdo
        if (i <= 19 && j <= 16) {
          if ((i + j) % 2 == 0) {
            Maux2[i][j] = 5; //Se pintan de color verde
          }
        }

        //Pintamos los cuadros impares del cuadrante inferior izquierdo
        if (i <= 19 && j > 16) {
          if ((i + j) % 2 == 0) {
            Maux2[i][j] = 3; // Se pintan de color rojo
          }
        }

        //Pintamos los cuadros impares del cuadrante inferior derecho
        if (i > 19 && j > 16) {
          if ((i + j) % 2 == 0) {
            Maux2[i][j] = 4; // Se pintan de color negro
          }
        }

        //Pintamos los cuadros impares del cuadrante superior  derecho
        if (i > 19 && j <= 16) {
          if ((i + j) % 2 == 0) {
            Maux2[i][j] = 0; //Se pintan de amarillo
          }
        }
      }
    }
    return Maux2;
  }

  /*
   *Metodo para evolucionar el automata.
   *
   */
  @Override
  public void evoluciona() {
    // Se crea una matriz copia para reemplazar los Valores.
    int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];
    //Se crea pila para almacenar colores
    Pila<Integer> pilaPinta = new Pila<Integer>();
    //Se crea cola para almacenar colores, excepto el rojo
    Cola<Integer> colaPinta = new Cola<Integer>();
    //Pila para almacenar colores, solo amarillo y verde
    Pila<Integer> Amverde = new Pila<Integer>();
    // System.out.println("entre"); SOP que ayuda a verificar cuando se efectuaba un
    // evoluciona.

    // super.estado++; // Operacion que aumentaba el contador en una unidad.
    int rojos, negros, azules, blancos, verdes, amarillos; // Contador de casillas de colores

    // For que escanea toda la matriz.
    for (int i = 0; i < Maux2.length; i++) {
      for (int j = 0; j < Maux2.length; j++) {
        //Se declara una varibable de tipo entero
        int f = 0;
        //Se le asignan valores aleatorios entre 0 y 5 a la variable f
        f = (int) (Math.random() * 5);

        //Según el valor de la variable, se agregan ciertos valores numéricos a la pila y la cola creada, estos numeros servirán para mantener vivos los diferentes colores de la composición
        switch (f) {
          case 0:
            pilaPinta.push(0); //Se agrega el color amarillo a la pila
            colaPinta.push(5); //Se agrega el color verde a la cola
            Amverde.push(5); //Se agrega el color verde a la pila
            break;
          case 1:
            pilaPinta.push(1); //Se agrega el color azul a la pila
            colaPinta.push(4); //Se agrega el color negro a la cola
            Amverde.push(5); //Se agrega el color verde a la pila
            break;
          case 2:
            pilaPinta.push(2); //Se agrega el color blanco a la pila
            colaPinta.push(4); //Se agrega el color negro a la cola
            Amverde.push(5); //Se agrega el color amarillo a la pila
            break;
          case 3:
            pilaPinta.push(3); //Se agrega el color rojo a la pila
            colaPinta.push(2); //Se agrega el color blanco a la cola
            Amverde.push(0); //Se agrega el color amarillo a la pila
            break;
          case 4:
            pilaPinta.push(4); //Se agrega el color negro a la pila
            colaPinta.push(1); //Se agrega el color azul a la cola
            Amverde.push(0); //Se agrega el color amarillo a la pila
            break;
          case 5:
            pilaPinta.push(5); //Se agrega el color verde a la pila
            colaPinta.push(0); //Se agrega el color amarillo a la cola
            Amverde.push(0); //Se agrega el color amarillo a la pila
            break;
        }

        //Se reinician las variables que cuentan las casillas vecinas segun el color
        amarillos = 0;
        rojos = 0;
        negros = 0;
        azules = 0;
        blancos = 0;
        verdes = 0;
        // System.out.println("Revisando " + i + "," + j ); SOP que ayuda a checar que
        // se realize correctamente el for.
        for (int k = i - 1; k <= i + 1; k++) {
          for (int l = j - 1; l <= j + 1; l++) {
            // Analisis de casillas vecindad.
            //Mantuvimos la vecindad de Moore para el análisis de las casillas
            if (
              k >= 0 &&
              l >= 0 &&
              k < Maux2.length &&
              l < Maux2.length &&
              (k != i || l != j)
            ) {
              // System.out.println(" Analizando " + k + "," + l + " --> " + Maux2[k][l] );
              // SOP que ayuda a checar los for.

              //Si la casilla es amarilla, aumentamos el contador de amarillos
              if (Maux2[k][l] == 0) {
                amarillos++;
              }
              //Si la casilla es azul, aumentamos el contador de azules
              if (Maux2[k][l] == 1) {
                azules++;
              }

              //Si la casilla es blanca, aumentamos el contador de blancos
              if (Maux2[k][l] == 2) {
                blancos++;
              }

              //Si la casilla es roja, aumentamos el contador de rojos
              if (Maux2[k][l] == 3) {
                rojos++;
              }

              //Si la casilla es negra, aumentamos el contador de negros
              if (Maux2[k][l] == 4) {
                negros++;
              }

              //Si la casilla es verde, aumentamos el contador de verdes
              if (Maux2[k][l] == 5) {
                verdes++;
              }
            }
          }
        }

        //Casilla roja
        if (Maux2[i][j] == 3) { // Si la casilla es roja
          if (rojos == 4 || rojos == 3 || rojos == 2) { // Y tiene cuatro, tres o dos vecinos rojos
            CopiaM[i][j] = 3; // entonces la casilla vivira
          } else {
            CopiaM[i][j] = pilaPinta.pop(); // si no, se pinta con el primer elemento de la pila
          }
        } else { // Si no es roja
          if (verdes == 2) { // Y tiene 2 vecinos verdes
            CopiaM[i][j] = 5; // La casilla se pintara de verde
          } else {
            CopiaM[i][j] = 1; //la casilla se pinta en azul
          }
        }

        //Casilla blanca

        if (Maux2[i][j] == 2) { // Si la casilla es blanca
          if (verdes == 4) { // Y tiene cuatro vecinos verdes
            CopiaM[i][j] = 5; //la casilla se pintara de verde
          } else if (rojos == 3) { // si tiene tres vecinos rojos
            CopiaM[i][j] = colaPinta.pop(); // Se pinta del color del primer elemento de la cola
          } else {
            CopiaM[i][j] = pilaPinta.pop(); //Se pinta del color del primer elemento de la pila
          }
        }

        //Si casilla blanca

        if (Maux2[i][j] == 2) { // Si la casilla esta viva,
          if (negros == 4) { // Y tiene dos o tres vecinos vivos
            CopiaM[i][j] = 4; // pilaPinta.pop(); // entonces la casilla vivira.
          } else if (amarillos == 3) {
            CopiaM[i][j] = 0; // De otra forma muere.
          }
        }

        //Si casilla rpja
        if (Maux2[i][j] == 3) { // Si la casilla es roja
          if (rojos == 3) { // Y tiene tres vecinos rojos
            CopiaM[i][j] = 3; //se mantien roja (Es lo que forma los cuadrados)
          } else if (amarillos == 2 || amarillos == 1) { //Si tiene dos o un vecino amarillo
            CopiaM[i][j] = 0; // Se pinta de amarillo
          } else if (rojos == 5) { //Si tiene cinco vecinos rojos
            CopiaM[i][j] = colaPinta.pop(); //Se pinta del primer elemento de la cola
          }
        }

        //Si la casilla es azul
        if (Maux2[i][j] == 1) { // Si la casilla es azul
          if (azules == 8) { // Y tiene ocho vecinos azules
            CopiaM[i][j] = pilaPinta.pop(); //se pinta del primer elemento de la pila
          } else if (verdes == 3 && amarillos == 3) { //Si tiene 3 vecinos verdes o amarillos
            CopiaM[i][j] = 4; // Se pinta de color negro
          }
        }

        //Si la casilla es roja
        if (Maux2[i][j] == 3) { // Si la casilla esta viva,
          if (rojos == 3 || rojos == 5 || rojos == 8) { // Y tiene tres, cinco u ocho vecinos rojos
            CopiaM[i][j] = 3; //Se mantiene roja (Mantiene los cuadrados rojos)
          }
        }

        //Casilla verde
        if (Maux2[i][j] == 5) { // Si la casilla esta viva,
          if (verdes == 3 || verdes == 2) { // Y tiene dos o tres vecinos verdes
            CopiaM[i][j] = 0; //Se pinta de amarillo
          } else if (verdes >= 6) { //Si tiene 6 o mas vecinos verdes
            Maux2[i][j] = pilaPinta.pop(); //Se pinta del primer color que esta en la pila
          }
        }

        //Casilla azul
        if (Maux2[i][j] == 1) { // Si la azul
          if (negros == 2) { // Y tiene dos vecinos negros
            CopiaM[i][j] = Amverde.pop(); //la casilla se pinta del color del primer elemento d ela pila (Evita que la composicion se plague de cuñas negras y extinga el resto de colores)
          }
        }

        //Si casilla amarilla
        if (Maux2[i][j] == 0) { // Si la casilla es amarilla
          if (amarillos == 2 && azules == 6) { // Y tiene dos vecinos amarillos o seis vecinos azules
            CopiaM[i][j] = colaPinta.pop(); //Se pinta del color de la cabeza de la cola
          }
        }

        //Si casilla negra
        if (Maux2[i][j] == 4) { // Si la casilla e negra
          if (negros == 4 || negros == 2 || negros == 5) { // Y tiene cuatro, dos o cinco vecinos negros
            CopiaM[i][j] = 4; //La casilla se mantiene negra (Genera las cunias)
          } else if (negros == 1 || negros == 0) { //Si tiene un vecino negro o menos
            CopiaM[i][j] = colaPinta.pop(); // Se pinta la casilla del color de la cabeza  de la cola
          } else {
            CopiaM[i][j] = pilaPinta.pop(); // Se pinta del color de la pila de la cabeza de la pila
          }
        }
        //ACABAN LAS EVOLUCIONES DE LOS COLORES
      }
    }
    for (int i = 0; i < Maux2.length; i++) { // Fors que arreglan la matriz a regresar en la copia.
      for (int j = 0; j < Maux2.length; j++) {
        Maux2[i][j] = CopiaM[i][j];
      }
    }
    //System.out.println("Termine");//SOP que ayuda a saber cuando acaba una evolucion.
  }

  public int[][] getAutomata2() {
    return Maux2;
  }
}
