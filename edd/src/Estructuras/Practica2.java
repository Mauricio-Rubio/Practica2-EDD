package edd.src.Estructuras;

public class Practica2 {

    private static void imprimirTorres(Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){
	System.out.println("\n----INICIO----\n");
	System.out.println("Origen \n" +  origen.toString());
	  System.out.println("\nAux \n" + auxiliar.toString());
	   System.out.println("\nDestino \n" +destino.toString());
	   	System.out.println("\n----FIN----\n");

    }
    
    public static void torresHanoi(int cantidadDiscos,Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){

	
	if(cantidadDiscos <= 0){
	    System.out.println("Para las torres de hanoi se necesita al menos 1 disco");
	    return;
	}
	for(int i= cantidadDiscos; i>=1; i--){
	    origen.push(i);
	   
	}

	if(cantidadDiscos ==1){
	    imprimirTorres(origen, auxiliar, destino);
	    destino.push(origen.cabeza.elemento);
	    origen.pop();
	     imprimirTorres(origen, auxiliar, destino);
	}

		if(cantidadDiscos >=2){
		      imprimirTorres(origen, auxiliar, destino);
		        destino.push(origen.cabeza.elemento);
			origen.pop();
		      imprimirTorres(origen, auxiliar, destino);
		        auxiliar.push(origen.cabeza.elemento);
			origen.pop();
		      imprimirTorres(origen, auxiliar, destino);
		        origen.push(destino.cabeza.elemento);
			destino.pop();
		      imprimirTorres(origen, auxiliar, destino);
		        destino.push(auxiliar.cabeza.elemento);
		        auxiliar.pop();
		      imprimirTorres(origen, auxiliar, destino);
		       destino.push(origen.cabeza.elemento);
		       origen.pop();
		      imprimirTorres(origen, auxiliar, destino);
		}

	//	while(origen.cabeza.elemento!=null){
	
	   

	    //	}
	 
        // No olvides imprimir cada paso de la solución. 
    }

    public static void binarioColas(int N){

    }

    public static void main(String[] args) {
	Pila<Integer> duracell = new Pila<Integer>();
	Pila<Integer> sony = new Pila<Integer>();
	Pila<Integer> lg  = new Pila<Integer>();
	int discos = 3;

	torresHanoi(discos, duracell, sony, lg);


        // Escribe aqui tu codigo para probar los metodos anteriores. 
        // No olvides comentar tu codigo y escribir tu nombre en el readme. 
    }

    
}
