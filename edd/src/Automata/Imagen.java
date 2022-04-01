package edd.src.Automata;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.applet.Applet;
import javax.swing.JPanel;

/**
 * Clase que representa una imagen en donde se va a pintar el automata celular.
 * @author Manuel
 */
public class Imagen extends JPanel {

  /**
   * Tamanio de la celda para cada celula.
   */
  public static final int sizeCell = 20;
  /**
   * Numero de celdas que contendra la malla. Este valor se aplica tanto para altura como para anchura.
   * Es decir la malla tendra numCellsxnumCell numero de celdas.
   */
  public static final int numCells = 40;
  /**
   * Tamanio real que debe de tener la malla considerando una linea divisoria entre las celdas, y el taminio de cada una.
   */
  private static final int tam = numCells * sizeCell + numCells + 1;
  /**
   * Objeto en donde se va a pintar.
   */
  private BufferedImage imagen;

  /**
   * Constructor de la clase.
   */
  public Imagen() {
    setSize(tam, tam);
    imagen = new BufferedImage(tam, tam, BufferedImage.TYPE_INT_RGB);
    createGrid();
  }

  /**
   * Metodo que dibuja las lineas en la imagen en color gris, para dar la apariencia de que es un entramado.
   */
  private void createGrid() {
    Graphics2D gc = imagen.createGraphics();
    //Rectangulo Blanco POR VALOR DE PENCIL DEFAULT.
    gc.fillRect(0, 0, tam, tam);
    gc.setColor(Color.GRAY);
    for (int i = 0; i <= numCells; i++) {
      gc.drawLine((sizeCell * i) + i, 0, (sizeCell * i) + i, tam);
      gc.drawLine(0, (sizeCell * i) + i, tam, (sizeCell * i) + i);
    }
  }

    /**
     * Metodo que mapea la matriz del automata a su representacion grafica. Este metodo considera
     * el tamanio de cada celda para poderla pintar. Se le pasa como parametro un arreglo de colores, 
     * para que cada vez que vea un valor en la matriz este lo busque en el arreglo de colores y pinte la celda de ese color.
     * Es necesario que la longitud del arreglo sea igual al maximo de los valores que se encuentran en la matriz.
     * @param matriz Representa la malla del automata con sus posibles estados. 
     * @param colores Se mapean a cada estado de la matriz para pintarlo de su respectivo color.
     */
    public void pinta(int[][] matriz, Color[] colores) {
    //PARA CUADROROJOCUNIANEGRA
    	Color azulito = new Color(66, 227, 245);
    	Color verdecito = new Color(108, 245, 66);
    	Color amarillito = new Color(245, 242, 66);
    	
    	this.setForeground(azulito);
    	this.setForeground(verdecito);
    	this.setForeground(amarillito);
    	
    	
	   Graphics2D gc = imagen.createGraphics();
    for (int i=1;i<matriz.length+1;i++) {
            for (int j=1;j<matriz.length+1;j++) {
                int aux1i = (sizeCell*(i-1)+i);
                int aux1j = (sizeCell*(j-1)+j);
               switch (matriz [i-1][j-1]) {
                    case 0:
                        gc.setColor(amarillito); break;
                    case 1:
                        gc.setColor(azulito); break;
                    case 2:
                        gc.setColor(Color.WHITE); break;
                    case 3:
                        gc.setColor(Color.RED); break;
                    case 4:
                        gc.setColor(Color.BLACK); break;
                    case 5: 
                        gc.setColor(verdecito); break;
                    
                }
                //AQUI ACABA
                gc.fillRect(aux1i,aux1j,sizeCell,sizeCell);
            }
       }       
        updateUI();
  public void pinta(int[][] matriz, Color[] colores) {
    Graphics2D gc = imagen.createGraphics();
    for (int i = 1; i < matriz.length + 1; i++) {
      for (int j = 1; j < matriz.length + 1; j++) {
        int aux1i = (sizeCell * (i - 1) + i);
        int aux1j = (sizeCell * (j - 1) + j);
        Color VIOLET = new Color(53, 23, 49);
        Color MARRON = new Color(164, 115, 111);
        Color CREMA = new Color(255, 234, 199);
        Color MEXPINK = new Color(233, 94, 115);
        Color BLUE = new Color(55, 4, 174);
        //this.setForeground(miColor);
        switch (matriz[i - 1][j - 1]) {
          case 0:
            gc.setColor(CREMA);
            break;
          case 1:
            gc.setColor(MEXPINK);
            break;
          case 2:
            gc.setColor(VIOLET);
            break;
          case 3:
            gc.setColor(MARRON);
            break;
          case 4:
            gc.setColor(BLUE);
            break;
          case 5:
            gc.setColor(Color.GREEN);
            break;
            default:
            gc.setColor(Color.DARK_GRAY);
        }
        gc.fillRect(aux1i, aux1j, sizeCell, sizeCell);
      }
    }
    updateUI();
  }

  @Override
  public void paint(Graphics g) {
    try {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.drawImage(imagen, null, 0, 0);
    } catch (NullPointerException e) {}
  }
}
