package edd.src.Estructuras;

public class Practica2 {

    public static void torresHanoi(int cantidadDiscos, Pila<Integer> origen, Pila<Integer> auxiliar,
            Pila<Integer> destino) {
        // No olvides imprimir cada paso de la solución.
    }

    public static Cola<String> binarioColas(int N) {
        Cola <String> cola = new Cola<>(); 
        if (N < 0) {
            throw new IllegalArgumentException("");
        }
        if(N == 0){
            cola.push("0");
        }
        if (N >= 1) {
            cola.push("0");
            while( (N-1) > 0){
            String s1 = cola.peek();
            cola.pop();
            System.out.println(s1);
            String s2 = s1;
            cola.push(s1+"0");
            cola.push(s2 + "1");
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Escribe aqui tu codigo para probar los metodos anteriores.
        // No olvides comentar tu codigo y escribir tu nombre en el readme.
        binarioColas(10);
    }

}
