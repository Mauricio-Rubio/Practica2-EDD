package edd.src.Estructuras;

import java.util.InputMismatchException;
import java.util.Scanner;

/** Práctica 2.- Estructuras de datos
 * @author Alcántara Estrada Kevin Isaac
 * @author Rubio Haro Mauricio
 */
public class Practica2 {

  //Variable global para contar los movimientos de torres de hanoi
  static int movimientos = 0;

  /** Método para mover un disco en las torres de hanoi
   * @author Alcántara Estrada Kevin Isaac
   * @param sale Pila de enteros que representa el pilar de donde sale el disco
   * @param entra PIla de enteros que representa el pilar a donde entra el disco
   */
  private static void mover(Pila<Integer> sale, Pila<Integer> entra) {
    entra.push(sale.cabeza.elemento);
    sale.pop();
    movimientos++;
  }

  /** Método para verificar si un disco se puede mover y realizarla, de lo contrario se intetará mover el disco en sentido inverso
   * @author Alcántara Estrada Kevin Isaac
   * @param x Pila de enteros que representa el pilar de donde se intenta sacar el disco
   * @param y Pila de enteros que representa el pilar a donde se quiere mover el pilar
   * @param condicion Variable booleana para verificar si los movimientos son válidos
   */
  private static void movida(
    Pila<Integer> x,
    Pila<Integer> y,
    boolean condicion
  ) {
    if (!x.isEmpty()) { //Verifico que puedo sacar discos
      if ((y.isEmpty())) { //Si la pila a la que queremos pasar el disco es vací, entonces entra directo
        mover(x, y); //X-->Y
        condicion = true;
      } else {
        if (x.cabeza.elemento < y.cabeza.elemento) { //Si no es vacía verifico que el elemento a meter es un disco más pequeño
          mover(x, y); //A-->C
          condicion = true;
        }
      }
    }

    if ((!y.isEmpty()) && (condicion == false)) { //Verifico que a donde se quería meter el disco originalmente no es vacía
      mover(y, x); //Y-->X //Muevo directo
    }
  }

  /** Método para imprimir las torres
   * @author Alcántara Estrada Kevin Isaac
   * @param origen Pila de enteros que representa el primer pilar
   * @param auxiliar Pila de enteros que representa el segundo pilar
   * @param destino Pila de enteros que representa el tercer pilar
   */
  private static void imprimirTorres(
    Pila<Integer> origen,
    Pila<Integer> auxiliar,
    Pila<Integer> destino
  ) {
    System.out.println("\n----INICIO----\n");
    System.out.println("Origen \n" + origen.toString());
    System.out.println("\nAux \n" + auxiliar.toString());
    System.out.println("\nDestino \n" + destino.toString());
    System.out.println("\n----MOVIMIENTOS: " + movimientos + " ----\n");
  }

  /** Método para resolver las torres de hanoi en minimo de pasos
   * @author Alcántara Estrada Kevin Isaac
   * @param cantidadDiscos Variable entera que representa el numero de discos
   * @param origen Pila de enteros que representa el primer pilar
   * @param auxiliar Pila de enteros que representa el segundo pilar
   * @param destino Pila de enteros que representa el tercer pilar
   */
  public static void torresHanoi(
    int cantidadDiscos,
    Pila<Integer> origen,
    Pila<Integer> auxiliar,
    Pila<Integer> destino
  ) {
    //Caso donde los discos son 0 o menos
    if (cantidadDiscos <= 0) {
      System.out.println(
        "Para las torres de hanoi se necesita al menos 1 disco"
      );
      return;
    }

    //Se añaden los discos al primer pilar
    for (int i = cantidadDiscos; i >= 1; i--) {
      origen.push(i);
    }
    imprimirTorres(origen, auxiliar, destino); //Imprimir torres

    if (cantidadDiscos % 2 != 0) { //Si el numero de discos es impar
      boolean paro = false;

      Pila<Integer> caja = origen.clone(); //Hacemos una copia de la torre inicial

      //INICIO PRIMERA PARTE
      while (paro == false) {
        boolean condicion = false;
        //Paso 1
        movida(origen, destino, condicion); //Movemos de Origen --> Destino || Destino-->Origen
        imprimirTorres(origen, auxiliar, destino); //Imprimir torres

        if (
          (!destino.isEmpty()) &&
          (caja.ultimo.elemento == destino.ultimo.elemento)
        ) {
          break;
        } //Condición para frenar el ciclo

        //Paso 2
        movida(origen, auxiliar, condicion); //Movemos de Origen --> Auxiliar || Auxiliar-->Origen
        imprimirTorres(origen, auxiliar, destino); //Imprimir torres

        if (
          (!destino.isEmpty()) &&
          (caja.ultimo.elemento == destino.ultimo.elemento)
        ) {
          break;
        } //Condición para frenar el ciclo

        //Paso 3
        movida(destino, auxiliar, condicion); //Movemos de Destino--> Auxiliar || Auxiliar --> Destino
        imprimirTorres(origen, auxiliar, destino); //Imprimir torres

        if (
          (!destino.isEmpty()) &&
          (caja.ultimo.elemento == destino.ultimo.elemento)
        ) {
          break;
        } //Condicion para frenar el ciclo
      } //FINAL PRIMERA PART cuando los discos son impares

      //INICIO SEGUNDA PARTE
      while (paro == false) {
        boolean condicion = false;

        //Paso 1

        movida(auxiliar, origen, condicion); //Movemos Auxiliar --> Origen || Origen --> Auxiliar
        imprimirTorres(origen, auxiliar, destino); // Imprimir torres

        if ((!destino.isEmpty()) && (caja.equals(destino))) {
          break;
        } //Condición para frenar el ciclo

        //Paso 2

        movida(auxiliar, destino, condicion); //Movemos Auxiliar --> Destino || Destino --> Auxiliar
        imprimirTorres(origen, auxiliar, destino); //Imprimir torres
        if ((!destino.isEmpty()) && (caja.equals(destino))) {
          break;
        } //Caso para frenar el ciclo

        //Paso 3

        movida(origen, destino, condicion); //Movemos Origen --> Destino || Destino --> Origen
        imprimirTorres(origen, auxiliar, destino);

        if ((!destino.isEmpty()) && (caja.equals(destino))) {
          break;
        } //Condicion de frenado
      } //FINAL PRIMERA PARTE
    } // FINAL PARA CASOS IMPARES

    //El caso de discos pares es analogo, solo cambia hacia donde se mueven los discos
    if (cantidadDiscos % 2 == 0) { //CASOS PARES
      boolean paro = false;

      Pila<Integer> caja = origen.clone();

      //INICIO PRIMERA PARTE
      while (paro == false) {
        boolean condicion = false;
        //Paso 1

        movida(origen, auxiliar, condicion); //Movemos Origen --> Auxiliar || Auxiliar --> Origen
        imprimirTorres(origen, auxiliar, destino);

        if (
          (!destino.isEmpty()) &&
          (caja.ultimo.elemento == destino.ultimo.elemento)
        ) {
          break;
        } //Condición de cierre

        //Paso 2

        movida(origen, destino, condicion); //Movemos Origen --> Destino || Destino --> Origen
        imprimirTorres(origen, auxiliar, destino);

        if (
          (!destino.isEmpty()) &&
          (caja.ultimo.elemento == destino.ultimo.elemento)
        ) {
          break;
        }

        //Paso 3

        movida(auxiliar, destino, condicion); //Movemos Auxiliar --> Destino || Destino --> Auxiliar
        imprimirTorres(origen, auxiliar, destino);

        if (
          (!destino.isEmpty()) &&
          (caja.ultimo.elemento == destino.ultimo.elemento)
        ) {
          break;
        }
      } //FINAL PRIMERA PARTE para casos pares

      //INICIO SEGUNDA PARTE
      while (paro == false) {
        boolean condicion = false;

        //Paso 1

        movida(auxiliar, destino, condicion); //Movemos Auxiliar --> Destino || Destino --> Auxiliar
        imprimirTorres(origen, auxiliar, destino);

        if ((!destino.isEmpty()) && (caja.equals(destino))) {
          break;
        } //Condición de cierre

        //Paso 2

        movida(auxiliar, origen, condicion); //Movemos Auxiliar --> Origen || Origen --> Auxiliar
        imprimirTorres(origen, auxiliar, destino);
        if ((!destino.isEmpty()) && (caja.equals(destino))) {
          break;
        }

        //Paso 3

        movida(destino, origen, condicion); //Movemos Destino --> Origen || Origen --> Destino
        imprimirTorres(origen, auxiliar, destino);

        if ((!destino.isEmpty()) && (caja.equals(destino))) {
          break;
        }
      } //FINAL PRIMERA PARTE
    } //FINAL PARA CASOS PARES
    // No olvides imprimir cada paso de la solución.
  }

  public static void binarioColas(int N) {
    Cola<String> cola = new Cola<>();
    if (N < 0) {
      throw new IllegalArgumentException("");
    }
    if (N == 0) {
      cola.push("0");
      System.out.println(cola);
    }
    if (N >= 1) {
      cola.push("1");
      System.out.println("---------------------");
      while ((N--) > 0) {
        String s1 = cola.peek();
        cola.pop();
        System.out.println(s1);
        String s2 = s1;
        cola.push(s1 + "0");
        cola.push(s2 + "1");
      }
      System.out.println("---------------------");
    }
  }

  /** Método main para hacer las pruebas del resto de métodos
   * @author Alcántara Estrada Kevin Isaac
   * @author Rubio Haro Mauricio
   * @param args
   */
  public static void main(String[] args) {
    //PRUEBAS HANOI
    Pila<Integer> duracell = new Pila<Integer>();
    Pila<Integer> sony = new Pila<Integer>();
    Pila<Integer> lg = new Pila<Integer>();
    int discos = 0;
    int elecc = 0;
    int n = 0;
    Scanner escaner = new Scanner(System.in);
    boolean listo = true;
    do {
      listo = false;
      try {
        System.out.println("1.- Prueba Binarios 2.- Prueba Hanoi");
        elecc = escaner.nextInt();
        if (elecc <= 1) {
          elecc = 1;
          System.out.println("Ingresa un numero natural");
          n = escaner.nextInt();
          if (n <= 0) {
            throw new InputMismatchException("");
          }
        } else {
          elecc = 2;
          System.out.println("PRUEBA HANOI\n¿Cuántos discos quieres acomodar?");
          discos = escaner.nextInt();
        }
      } catch (InputMismatchException e) {
        listo = true;
        escaner.next();
      }
    } while (listo);

    if (elecc == 1) {
      binarioColas(n);
    } else if (elecc == 2) {
      torresHanoi(discos, duracell, sony, lg);
    }
    //FIN PRUEBAS HANOI

    // Escribe aqui tu codigo para probar los metodos anteriores.
    // No olvides comentar tu codigo y escribir tu nombre en el readme.
  }
}
