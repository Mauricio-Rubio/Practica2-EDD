package edd.src.Automata;
import java.util.Random;
import edd.src.Estructuras.*;

public class juego extends AC{

		int[][] Maux2=new int[Imagen.numCells][Imagen.numCells];
		int[][] MauxCopia=new int [Imagen.numCells][Imagen.numCells];
		int[][] CopiaM =new int [Imagen.numCells][Imagen.numCells];


    //Pila<Integer> pila1 = new Pila<Integer>();
	 
   	/*
   	*Metodo que pinta una matriz de Blanco y le da valores aleatorios a las casillas.
   	*
   	*/
	 @Override
    public int[][] getAutomata() {
    	int aux1;
    	for (int i=0;i<Maux2.length ;i++ ) {
    		for (int j=0;j<Maux2.length ;j++ ) {
    		Maux2[i][j]=2;
    		//Maux2[0][39]=4;
    		MauxCopia[i][j]=2;
    		//Maux2[2][1]=2;  
    		//Maux2[2][2]=1;
    		//Maux2[2][3]=3;
    		}
    	}
 		   	//Maux2[2][1]=1;  Elementos que use como prueba del automata
 		   	//Maux2[2][2]=1;   Trazando una linea vertical en la matriz
 		   	//Maux2[2][3]=1;     para crear un Parpadeador o ´blinker´.
 		   	
 		   	Pila<Integer> pilaColor = new Pila<Integer>();
		

		//Modifico cada valor de la matriz Maux de forma aleatoria.
			for (int i=0;i<Maux2.length;i++) {
				for (int j=0;j<Maux2.length;j++) {

					aux1 = (int) ( Math.random() * 14 ); //Random del 0 al 12
					
					/*if(i<=19){
						Maux2[i][0] = 3;
						}else{
						Maux2[i][0] = 5;
						}
						
						if(i<=18){
						Maux2[i][1] = 5;
						}else{
						Maux2[i][1] = 3;
						}
						
						if(i<=17){
						Maux2[i][2] = 3;
						}else{
						Maux2[i][2] = 5;
						}
						
						if(i<=16){
						Maux2[i][3] = 5;
						}else{
						Maux2[i][3] = 3;
						}
						
						if(i<=15){
						Maux2[i][4] = 3;
						}else{
						Maux2[i][4] = 5;
						}
						
						if(i<=14){
						Maux2[i][5] = 5;
						}else{
						Maux2[i][5] = 3;
						}
						
						if(i<=13){
						Maux2[i][6] = 3;
						}else{
						Maux2[i][6] = 5;
						}*/
						
						Maux2[19][19]=5;
						
					if(i!=j){
					if (aux1<1) {
						
					}else if (aux1>3 && aux1<=5) {
						Maux2[i][j] = 5; // Azul
					}else if (aux1>5 && aux1<=7) {
						Maux2[i][j] = 3; 
					}else if (aux1>6 && aux1<=8) {
						Maux2[i][j] = 5; 
					}else {

						Maux2[i][j] = 0;
					}
				}
				
				if((i+j)%2==0){
				Maux2[i][j]=4;
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
	public void evoluciona(){
		
		// Se crea una matriz copia para reemplazar los Valores.
		int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];
		Pila<Integer> pilaPinta = new Pila<Integer>();
		Cola<Integer> colaPinta = new Cola<Integer>();
		// System.out.println("entre"); SOP que ayuda a verificar cuando se efectuaba un
		// evoluciona.

		// super.estado++; // Operacion que aumentaba el contador en una unidad.
		int rojos,verdes,azules,blancos,rosas,morados; // Contador de casillas vecindad vivas.
		int muertos; // Contador de casillas vecindad muertas.

		// For que escanea toda la matriz.
		for (int i = 0; i < Maux2.length; i++) {
			for (int j = 0; j < Maux2.length; j++) {
			    int f=0;
			    	f = (int) ( Math.random() * 5 );

				switch (f) {
                    case 0:
                        pilaPinta.push(0);
			colaPinta.push(5);
			break;
                    case 1:
                         pilaPinta.push(1);
			colaPinta.push(4);
			break;

			break;
                    case 2:
                        pilaPinta.push(2);
			colaPinta.push(3);
			break;

                    case 3:
                         pilaPinta.push(3);
			colaPinta.push(2);
			break;

                    case 4:
                         pilaPinta.push(4);
			colaPinta.push(1);
			break;

			
                    case 5: 
                         pilaPinta.push(5);
			colaPinta.push(0);
			break;

                    
                }
			    
			    
				rojos = 0; 
				verdes = 0;
				azules = 0;
				blancos = 0;
				rosas= 0;
				morados= 0;
				// System.out.println("Revisando " + i + "," + j ); SOP que ayuda a checar que
				// se realize correctamente el for.
				for (int k = i - 1; k <= i + 1; k++) {
					for (int l = j - 1; l <= j + 1; l++) {
						// Analisis de casillas vecindad.
						if (k >= 0 && l >= 0 && k < Maux2.length && l < Maux2.length && (k != i || l != j)) {
							// System.out.println(" Analizando " + k + "," + l + " --> " + Maux2[k][l] );
							// SOP que ayuda a checar los for.
							if (Maux2[k][l] == 3) {
								morados++;
							} else {
								rosas++;
							}
							
							if (Maux2[k][l] == 5) {
							    verdes++;
							} else {
							    rojos++;
							}
						}
					}
				}
				
				if (Maux2[i][j] == 5 || Maux2[i][j]== 3) { // Si la casilla esta viva,
					if (morados == 1 || morados == 2) { // Y tiene dos o tres vecinos vivos
					    CopiaM[i][j] = pilaPinta.pop(); // entonces la casilla vivira.
					} else if(morados == 3){
						CopiaM[i][j] = 3; // De otra forma muere.
					}else{
						CopiaM[i][j] = 0;
					}
				} else { // Si la casilla esta muerta,
					if (morados == 1 || morados == 3) { // Y tiene 3 vecinos vivos
						CopiaM[i][j] = 3; // La casilla muerta vivira.
					} else {
						CopiaM[i][j] = 2; // De otro modo seguira muerta.
					}
				}
				
				
				
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
