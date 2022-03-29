package edd.src.Estructuras;


public class Practica2 {

     static int movimientos=0;

    private static void mover(Pila<Integer> sale, Pila<Integer> entra){

	    entra.push(sale.cabeza.elemento);
	    sale.pop();
	    movimientos++;
    }
    
    private static void imprimirTorres(Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){
	System.out.println("\n----INICIO----\n");
	System.out.println("Origen \n" +  origen.toString());
	  System.out.println("\nAux \n" + auxiliar.toString());
	   System.out.println("\nDestino \n" +destino.toString());
	   	System.out.println("\n----MOVIMIENTOS: "+ movimientos + " ----\n");

    }
    
    public static void torresHanoi(int cantidadDiscos,Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){

	
	if(cantidadDiscos <= 0){
	    System.out.println("Para las torres de hanoi se necesita al menos 1 disco");
	    return;
	}
	
		for(int i= cantidadDiscos; i>=1; i--){
	    origen.push(i);
	   
	    	}
		 imprimirTorres(origen, auxiliar, destino);


		if(cantidadDiscos%2!=0){
		    boolean paro=false;
		   
		    Pila<Integer> caja = origen.clone();
		    
		    //INICIO PRIMERA PARTE
		    while(paro==false){
			 boolean a = false, b= false, c = false;
			//Paso 1
			if(!origen.isEmpty()){//Verifico que puedo sacar discos
			    if((destino.isEmpty())){//Si es vacía entra directo
			  	mover(origen, destino);//A-->C
				imprimirTorres(origen, auxiliar, destino);
				a = true;
				
			    }else{
			     	if(origen.cabeza.elemento < destino.cabeza.elemento){//Si no es vacía verifico que el elemento a meter es un disco más pequeño
				       	mover(origen, destino);//A-->C
						imprimirTorres(origen, auxiliar, destino);
					a= true;
					
					}
			    }
			}

			if((!destino.isEmpty())&&(a==false)){//Verifico que destino no es vacía
			     	mover(destino, origen);//C-->A //Muevo directo
			     imprimirTorres(origen, auxiliar, destino);
			     
				}

			
			if((!destino.isEmpty())&&  (caja.ultimo.elemento==destino.ultimo.elemento)){
				 break;
			}//Condición de cierre
			
			//Paso 2
			if(!origen.isEmpty()){//Verifico que origen tiene discps
			    if((auxiliar.isEmpty())){//Si aux es vacía se mueve directo
			  	mover(origen, auxiliar);//A-->B
				imprimirTorres(origen, auxiliar, destino);
				b=true;
				
			    }else{
				if(origen.cabeza.elemento < auxiliar.cabeza.elemento){//Verifico que el disco a mover es menor
				       	mover(origen, auxiliar);//A-->B
					imprimirTorres(origen, auxiliar, destino);
					b=true;
					}
			    }
			}


			if((!auxiliar.isEmpty())&&(b==false)){//Verifico que auxiliar no es vacia
			     	mover(auxiliar, origen);//B-->A
			     imprimirTorres(origen, auxiliar, destino);
				}
				if((!destino.isEmpty())&&  (caja.ultimo.elemento==destino.ultimo.elemento)){
				 break;
			     }

					//Paso 3
				if(!destino.isEmpty()){
			    if((auxiliar.isEmpty())){
			  	mover(destino, auxiliar);//C-->B
				imprimirTorres(origen, auxiliar, destino);
				c=true;
			    }else{
			        	if(destino.cabeza.elemento < auxiliar.cabeza.elemento){
					    
				       	mover(destino, auxiliar);//C-->B
						imprimirTorres(origen, auxiliar, destino);
					c=true;
					}
			    }
			}

				if((!auxiliar.isEmpty())&&(c==false)){
			     	mover(auxiliar, destino);//B-->C
			     imprimirTorres(origen, auxiliar, destino);
				}

		
			     

				if((!destino.isEmpty())&&  (caja.ultimo.elemento==destino.ultimo.elemento)){
				 break;
			     }
			     
		    }//FINAL PRIMERA PARTE



		    //INICIO SEGUNDA PARTE 
		    while(paro==false){
			 boolean a = false, b= false, c = false;
			 
			//Paso 1
			if(!auxiliar.isEmpty()){//Verifico que puedo sacar discos
			    if((origen.isEmpty())){//Si es vacía entra directo
			  	mover(auxiliar, origen);//B-->A
				imprimirTorres(origen, auxiliar, destino);
				b = true;
				
			    }else{
			     	if(auxiliar.cabeza.elemento < origen.cabeza.elemento){//Si no es vacía verifico que el elemento a meter es un disco más pequeño
				       	mover(auxiliar, origen);//B-->A
						imprimirTorres(origen, auxiliar, destino);
					b= true;
					
					}
			    }
			}

			if((!origen.isEmpty())&&(b==false)){//Verifico que destino no es vacía
			     	mover(origen, auxiliar);//A-->B //Muevo directo
			     imprimirTorres(origen, auxiliar, destino);
			     
				}

			
			if((!destino.isEmpty())&& (caja.equals(destino))){
				 break;
			}//Condición de cierre
			
			//Paso 2
			if(!auxiliar.isEmpty()){//Verifico que origen tiene discps
			    if((destino.isEmpty())){//Si aux es vacía se mueve directo
			  	mover(auxiliar, destino);//B-->C
				imprimirTorres(origen, auxiliar, destino);
				c=true;
				
			    }else{
				if(auxiliar.cabeza.elemento < destino.cabeza.elemento){//Verifico que el disco a mover es menor
				       	mover(auxiliar, destino);//B-->C
					imprimirTorres(origen, auxiliar, destino);
					c=true;
					}
			    }
			}


			if((!destino.isEmpty())&&(c==false)){//Verifico que auxiliar no es vacia
			     	mover(destino, auxiliar);//C-->B
			     imprimirTorres(origen, auxiliar, destino);
				}
			
				if((!destino.isEmpty())&&  (caja.equals(destino))){
				 break;
			     }

					//Paso 3
				if(!origen.isEmpty()){
			    if((destino.isEmpty())){
			  	mover(origen, destino);//A-->C
				imprimirTorres(origen, auxiliar, destino);
				a=true;
			    }else{
			        	if(origen.cabeza.elemento < destino.cabeza.elemento){
					    
				       	mover(origen, destino);//A-->C
						imprimirTorres(origen, auxiliar, destino);
					a=true;
					}
			    }
			}

				if((!destino.isEmpty())&&(a==false)){
			     	mover(destino, origen);//C-->A
			     imprimirTorres(origen, auxiliar, destino);
				}

		
			     

				if((!destino.isEmpty())&&  (caja.equals(destino))){
				 break;
			     }
			     
		    }//FINAL PRIMERA PARTE
		    
		}// FINAL PARA CASOS IMPARES


		if(cantidadDiscos%2==0){//CASOS PARES
		    boolean paro=false;
		   
		    Pila<Integer> caja = origen.clone();
		    
		    //INICIO PRIMERA PARTE
		    while(paro==false){
			 boolean a = false, b= false, c = false;
			//Paso 1
			if(!origen.isEmpty()){//Verifico que puedo sacar discos
			    if((auxiliar.isEmpty())){//Si es vacía entra directo
			  	mover(origen, auxiliar);//A-->B
				imprimirTorres(origen, auxiliar, destino);
				b = true;
				
			    }else{
			     	if(origen.cabeza.elemento < auxiliar.cabeza.elemento){//Si no es vacía verifico que el elemento a meter es un disco más pequeño
				       	mover(origen, auxiliar);//A-->B
						imprimirTorres(origen, auxiliar, destino);
					b= true;
					
					}
			    }
			}

			if((!auxiliar.isEmpty())&&(b==false)){//Verifico que destino no es vacía
			     	mover(auxiliar, origen);//B-->A //Muevo directo
			     imprimirTorres(origen, auxiliar, destino);
			     
				}

			
			if((!destino.isEmpty())&&  (caja.ultimo.elemento==destino.ultimo.elemento)){
				 break;
			}//Condición de cierre
			
			//Paso 2
			if(!origen.isEmpty()){//Verifico que origen tiene discps
			    if((destino.isEmpty())){//Si aux es vacía se mueve directo
			  	mover(origen, destino);//A-->C
				imprimirTorres(origen, auxiliar, destino);
				c=true;
				
			    }else{
				if(origen.cabeza.elemento < destino.cabeza.elemento){//Verifico que el disco a mover es menor
				       	mover(origen, destino);//A-->C
					imprimirTorres(origen, auxiliar, destino);
					c=true;
					}
			    }
			}


			if((!auxiliar.isEmpty())&&(c==false)){//Verifico que auxiliar no es vacia
			     	mover(destino, origen);//C-->A
			     imprimirTorres(origen, auxiliar, destino);
				}
				if((!destino.isEmpty())&&  (caja.ultimo.elemento==destino.ultimo.elemento)){
				 break;
			     }

					//Paso 3
				if(!auxiliar.isEmpty()){
			    if((destino.isEmpty())){
			  	mover(auxiliar, destino);//B-->C
				imprimirTorres(origen, auxiliar, destino);
				a=true;
			    }else{
			        	if(auxiliar.cabeza.elemento < destino.cabeza.elemento){
					    
				       	mover(auxiliar, destino);//B-->C
						imprimirTorres(origen, auxiliar, destino);
					a=true;
					}
			    }
			}

				if((!destino.isEmpty())&&(c==false)){
			     	mover(destino, auxiliar);//C-->B
			     imprimirTorres(origen, auxiliar, destino);
				}

		
			     

				if((!destino.isEmpty())&&  (caja.ultimo.elemento==destino.ultimo.elemento)){
				 break;
			     }
			     
		    }//FINAL PRIMERA PARTE



		    //INICIO SEGUNDA PARTE 
		    while(paro==false){
			 boolean a = false, b= false, c = false;
			 
			//Paso 1
			if(!auxiliar.isEmpty()){//Verifico que puedo sacar discos
			    if((destino.isEmpty())){//Si es vacía entra directo
			  	mover(auxiliar, destino);//B-->C
				imprimirTorres(origen, auxiliar, destino);
				c = true;
				
			    }else{
			     	if(auxiliar.cabeza.elemento < destino.cabeza.elemento){//Si no es vacía verifico que el elemento a meter es un disco más pequeño
				       	mover(auxiliar, destino);//B-->C
						imprimirTorres(origen, auxiliar, destino);
					c= true;
					
					}
			    }
			}

			if((!destino.isEmpty())&&(c==false)){//Verifico que destino no es vacía
			     	mover(destino, auxiliar);//C-->B //Muevo directo
			     imprimirTorres(origen, auxiliar, destino);
			     
				}

			
			if((!destino.isEmpty())&& (caja.equals(destino))){
				 break;
			}//Condición de cierre
			
			//Paso 2
			if(!auxiliar.isEmpty()){//Verifico que origen tiene discps
			    if((origen.isEmpty())){//Si aux es vacía se mueve directo
			  	mover(auxiliar, origen);//B-->A
				imprimirTorres(origen, auxiliar, destino);
				a=true;
				
			    }else{
				if(auxiliar.cabeza.elemento < origen.cabeza.elemento){//Verifico que el disco a mover es menor
				       	mover(auxiliar, origen);//B-->A
					imprimirTorres(origen, auxiliar, destino);
					a=true;
					}
			    }
			}


			if((!origen.isEmpty())&&(a==false)){//Verifico que auxiliar no es vacia
			     	mover(origen, auxiliar);//A-->B
			     imprimirTorres(origen, auxiliar, destino);
				}
			
				if((!destino.isEmpty())&&  (caja.equals(destino))){
				 break;
			     }

					//Paso 3
				if(!destino.isEmpty()){
			    if((origen.isEmpty())){
			  	mover(destino, origen);//C-->A
				imprimirTorres(origen, auxiliar, destino);
				b=true;
			    }else{
			        	if(destino.cabeza.elemento < origen.cabeza.elemento){
					    
				       	mover(destino, origen);//C-->A
						imprimirTorres(origen, auxiliar, destino);
					b=true;
					}
			    }
			}

				if((!origen.isEmpty())&&(b==false)){
			     	mover(origen, destino);//A-->C
			     imprimirTorres(origen, auxiliar, destino);
				}

		
			     

				if((!destino.isEmpty())&&  (caja.equals(destino))){
				 break;
			     }
			     
		    }//FINAL PRIMERA PARTE
		    
			}//FINAL PARA CASOS PARES
		


	 
        // No olvides imprimir cada paso de la solución. 
    }


    public static void binarioColas(int N){

    }

    public static void main(String[] args) {
	Pila<Integer> duracell = new Pila<Integer>();
	Pila<Integer> sony = new Pila<Integer>();
	Pila<Integer> lg  = new Pila<Integer>();
	int discos = 4;
	
			torresHanoi(discos, duracell, sony, lg);

	
		
        // Escribe aqui tu codigo para probar los metodos anteriores. 
        // No olvides comentar tu codigo y escribir tu nombre en el readme. 
    }

    
}
