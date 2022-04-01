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
					
					if(i<=19 && j<=16){
					    if((i+j)%2==0){
						Maux2[i][j]=5;
					    }

					}


						if(i<=19 && j>16){
					    if((i+j)%2==0){
						Maux2[i][j]=3;
					    }

					}

				      	if(i>19 && j>16){
					    if((i+j)%2==0){
						Maux2[i][j]=4;
					    }

					}

						if(i>19 && j<=16){
					    if((i+j)%2==0){
						Maux2[i][j]=0;
					    }

					}

				        
						
					//	Maux2[19][19]=5;
						
				
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
			Pila<Integer> Amverde = new Pila<Integer>();
		// System.out.println("entre"); SOP que ayuda a verificar cuando se efectuaba un
		// evoluciona.

		// super.estado++; // Operacion que aumentaba el contador en una unidad.
		int rojos,negros,azules,blancos,rosas,verdes,amarillos; // Contador de casillas vecindad vivas.
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
			Amverde.push(5);
			break;
                    case 1:
                         pilaPinta.push(1);
			colaPinta.push(4);
				Amverde.push(5);
			break;

		       
                    case 2:
                        pilaPinta.push(2);
			colaPinta.push(4);
				Amverde.push(5);
			break;

                    case 3:
                         pilaPinta.push(3);
			colaPinta.push(2);
				Amverde.push(0);
			break;

                    case 4:
                         pilaPinta.push(4);
			colaPinta.push(1);
				Amverde.push(0);
			break;

			
                    case 5: 
                         pilaPinta.push(5);
			colaPinta.push(0);
				Amverde.push(0);
			break;

                    
                }
			    
				amarillos=0;
				rojos = 0; 
				negros = 0;
				azules = 0;
				blancos = 0;
				rosas= 0;
				verdes= 0;
				// System.out.println("Revisando " + i + "," + j ); SOP que ayuda a checar que
				// se realize correctamente el for.
				for (int k = i - 1; k <= i + 1; k++) {
					for (int l = j - 1; l <= j + 1; l++) {
						// Analisis de casillas vecindad.
						if (k >= 0 && l >= 0 && k < Maux2.length && l < Maux2.length && (k != i || l != j)) {
							// System.out.println(" Analizando " + k + "," + l + " --> " + Maux2[k][l] );
							// SOP que ayuda a checar los for.
						    if (Maux2[k][l] == 0) {
								amarillos++;
							} 

						    if (Maux2[k][l] == 1) {
								azules++;
							}
						    
						     if (Maux2[k][l] == 2) {
								blancos++;
							}
						      

						    if (Maux2[k][l] == 3) {
								rojos++;
							}

						     if (Maux2[k][l] == 4) {
								negros++;
							} 
								
							
							if (Maux2[k][l] == 5) {
							    verdes++;
							} 
							    
							
						}
					}
				}
				
				if (Maux2[i][j]== 3) { // Si la casilla esta viva,
					if (rojos == 4 ||rojos==3|| rojos == 2) { // Y tiene dos o tres vecinos vivos
					    CopiaM[i][j] = 3; // pilaPinta.pop(); // entonces la casilla vivira.
					} else{
					    CopiaM[i][j] = pilaPinta.pop();
					}
				} else { // Si la casilla esta muerta,
					if (verdes==2) { // Y tiene 3 vecinos vivos
					    CopiaM[i][j] = 5; // La casilla muerta vivira.
					} else {
					    CopiaM[i][j] = 1;//colaPinta.pop(); // De otro modo seguira muerta.
					}
				}
				
				//BLANCOS

					if(Maux2[i][j]== 2){ // Si la casilla esta viva,
					if(verdes==4 ){ // Y tiene dos o tres vecinos vivos
					    CopiaM[i][j] = 5; // pilaPinta.pop(); // entonces la casilla vivira.
					} else if(rojos == 3){
					    CopiaM[i][j] = colaPinta.pop(); // De otra forma muere.
					}else{
					    CopiaM[i][j] = pilaPinta.pop();
					}
					}
					
							
					//ROSAS
					
				
					if(Maux2[i][j]== 2){ // Si la casilla esta viva,
					if(negros==4 ){ // Y tiene dos o tres vecinos vivos
					    CopiaM[i][j] = 4; // pilaPinta.pop(); // entonces la casilla vivira.
					} else if(amarillos == 3){
					    CopiaM[i][j] = 0; // De otra forma muere.
					}
					}

					//kmrkvm
						if(Maux2[i][j]== 3){ // Si la casilla esta viva,
					if(rojos==3 ){ // Y tiene dos o tres vecinos vivos
					    CopiaM[i][j] = 3; // pilaPinta.pop(); // entonces la casilla vivira.
					} else if(amarillos == 2 || amarillos==1){
					    CopiaM[i][j] = 0; // De otra forma muere.
					} else if(rojos==5){
					    CopiaM[i][j]= colaPinta.pop();
					}
					}

						if(Maux2[i][j]== 1){ // Si la casilla esta viva,
					if(azules==8 ){ // Y tiene dos o tres vecinos vivos
					    CopiaM[i][j] = pilaPinta.pop(); // pilaPinta.pop(); // entonces la casilla vivira.
					} else if(verdes == 3 && amarillos==3){
					    CopiaM[i][j] = 4; // De otra forma muere.
					}
					}

							if(Maux2[i][j]== 3){ // Si la casilla esta viva,
					if(rojos==3 || rojos== 5 || rojos ==8){ // Y tiene dos o tres vecinos vivos
					    CopiaM[i][j] = 3; // pilaPinta.pop(); // entonces la casilla vivira.
					} 
					    
					}

								if(Maux2[i][j]== 5){ // Si la casilla esta viva,
					if( verdes ==3 || verdes==2){ // Y tiene dos o tres vecinos vivos
					    CopiaM[i][j] = 0; // pilaPinta.pop(); // entonces la casilla vivira.
					}else if(verdes>=6){
					    Maux2[i][j]=pilaPinta.pop();
					}
					    
					}

								if(Maux2[i][j]== 1){ // Si la casilla esta viva,
					if( negros==2){ // Y tiene dos o tres vecinos vivos
					    CopiaM[i][j] = Amverde.pop(); // pilaPinta.pop(); // entonces la casilla vivira.
					}
								}

									if(Maux2[i][j]== 0){ // Si la casilla esta viva,
					if( amarillos==2 && azules==6){ // Y tiene dos o tres vecinos vivos
					    CopiaM[i][j] = colaPinta.pop(); // pilaPinta.pop(); // entonces la casilla vivira.
					}
								}

					//AZULES
					
				       if(Maux2[i][j]== 4){ // Si la casilla esta viva,
					if(negros==4 || negros==2 || negros ==5  ){ // Y tiene dos o tres vecinos vivos
					    CopiaM[i][j] = 4; // pilaPinta.pop(); // entonces la casilla vivira.
					} else if(negros == 1 || negros == 0){
					    CopiaM[i][j] = colaPinta.pop(); // De otra forma muere.
					}else{
					    CopiaM[i][j] = pilaPinta.pop();
					}
					}
					//Acaban colores
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

