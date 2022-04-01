package edd.src.Automata;

import edd.src.Estructuras.*;
import java.util.Random;

public class Space extends AC {

  int[][] Maux2 = new int[Imagen.numCells][Imagen.numCells];
  int[][] MauxCopia = new int[Imagen.numCells][Imagen.numCells];
  int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];

  Pila<Integer> pila1 = new Pila<Integer>();

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
    int x = 1;
    int longi = Imagen.numCells;
    int alpha = 5;
    int beta = 10;
    int gama = 15;
    int delta = 20;
    while (x <= (longi)) {
      for (int i = 0; i < longi; i++) {
        for (int r = 0; r < longi; r++) {
          if (
            r == 0 ||
            r == longi - (9 + x) ||
            i == 0 ||
            i == longi - x ||
            (i >= 0 && i <= 20)
          ) {
            aux1 = (int) (Math.random() * (100)); //Random del 0 al 100
            if (x <= alpha) {
              if (aux1 > 3) {
                Maux2[i][r] = 2; // VIOLET COLOR
              } else if (aux1 > 1) {
                Maux2[i][r] = 0; // CREMA COLOR
              } else if (aux1 > 0) {
                Maux2[i][r] = 4; // BLUE COLOR
              } else if (aux1 > -2) {
                //   Maux2[i][r] = 1; // MEXPINK COLOR
              }
            } else if (x <= beta) {
              if (aux1 > 10) {
                Maux2[i][r] = 2; // VIOLET COLOR
              } else if (aux1 > 5) {
                Maux2[i][r] = 0; // CREMA COLOR
              } else if (aux1 > 3) {
                Maux2[i][r] = 4; // BLUE COLOR
              } else if (aux1 > 2) {
                Maux2[i][r] = 1; // MEXPINK COLOR
              } else if (aux1 > 13) {
                //Maux2[i][j] = 3; // MARRON COLOR
              }
            } else if (x <= gama) {
              if (aux1 > 30) {
                Maux2[i][r] = 2; // VIOLET COLOR
              } else if (aux1 > 20) {
                Maux2[i][r] = 0; // CREMA COLOR
              } else if (aux1 > 10) {
                Maux2[i][r] = 4; // BLUE COLOR
              } else if (aux1 > 3) {
                // Maux2[i][j] = 3; // MARRON COLOR
              } else if (aux1 > 1) {
                Maux2[i][r] = 1; // MEXPINK COLOR
              }
            } else if (x <= delta) {
              if (aux1 > 30) {
                Maux2[i][r] = 2; // VIOLET COLOR
              } else if (aux1 > 20) {
                Maux2[i][r] = 0; // CREMA COLOR
              } else if (aux1 > 10) {
                Maux2[i][r] = 4; // BLUE COLOR
              } else if (aux1 > 3) {
                Maux2[i][r] = 3; // MARRON COLOR
              } else if (aux1 > 1) {
                Maux2[i][r] = 1; // MEXPINK COLOR
              }
            }
          }
        }
      }
      x++;
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
    // System.out.println("entre"); SOP que ayuda a verificar cuando se efectuaba un
    // evoluciona.
    int aux1;
    Pila<Integer> pila = new Pila<Integer>();
    Cola<Integer> cola = new Cola<Integer>();
    int crema = 0;
    int rosa = 0;
    int violet = 0;
    int marron = 0;
    int blue = 0;
    int verde = 0;
    int negro = 0;
    for (int i = 0; i < Maux2.length; i++) {
      for (int j = 0; j < Maux2.length; j++) {
        //cola = new Cola<>();
        //pila = new Pila<>();
        for (int k = i - 1; k <= i + 1; k++) {
          for (int l = j - 1; l <= j + 1; l++) {
            //Analisis de casillas vecindad.
            if (
              k >= 0 &&
              l >= 0 &&
              k < Maux2.length &&
              l < Maux2.length &&
              (k != i || l != j)
            ) {
              switch (Maux2[k][l]) {
                case 0:
                  crema++;
                  break;
                case 1:
                  rosa++;
                  break;
                case 2:
                  violet++;
                  break;
                case 3:
                  marron++;
                  break;
                case 4:
                  blue++;
                  break;
                case 5:
                  verde++;
                  break;
                default:
                  negro++;
                  break;
              }
              cola.push(Maux2[k][l]);
            }
          }
        }
        if (crema > 8) {
          do {
            aux1 = (int) (Math.random() * (6-0)+0);
          } while (aux1 == crema);
          cola.push(aux1);
          cola.push(2);
        } else if (rosa > 8) {
          do {
            aux1 = (int) (Math.random() * (6-0)+0);
          } while (aux1 == rosa);
          cola.push(aux1);
          cola.push(2);
        } else if (violet > 10) {
          do {
            aux1 = (int) (Math.random() * (6-0)+0);
          } while (aux1 == violet);
          cola.push(aux1);
        } else if (marron > 10) {
          do {
            aux1 = (int) (Math.random() * (6-0)+0);
          } while (aux1 != marron);
          cola.push(aux1);
        } else if (blue > 10) {
          do {
            aux1 = (int) (Math.random() * (6-0)+0);
          } while (aux1 == blue);
          cola.push(aux1);
        } else if (verde > 10) {
          do {
            aux1 = (int) (Math.random() * (6-0)+0);
          } while (aux1 == verde);
          cola.push(aux1);
        } else {
          do {
            aux1 = (int) (Math.random() * (6-0)+0);
          } while (aux1 == negro);
          cola.push(aux1);
        }
        if (cola.peek() == 2) {
          //cola.push(cola.peek());
          cola.pop();
          if (cola.peek() != 2) {
            CopiaM[i][j] = cola.peek();
            cola.pop();
          } else {
            CopiaM[i][j] = 2;
            cola.pop();
            cola.push(cola.peek());
          }
        } else {
          CopiaM[i][j] = cola.peek();
          cola.push(cola.peek());
          cola.pop();
          cola.push(2);
          cola.pop();
        }
        /*else{ 							//Si la casilla esta muerta,
					if ( pila.peek() != 2 ) {			//Y tiene 3 vecinos vivos
						CopiaM[i][j]=pila.pop()+1-cola.peek(); 		//La casilla muerta vivira.
					} 
					else {
						CopiaM[i][j]=cola.pop()+2-cola.peek();			//De otro modo seguira muerta.
					}
				}*/

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
