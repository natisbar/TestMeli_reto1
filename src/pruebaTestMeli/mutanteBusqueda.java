/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaTestMeli;
//import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;


public class mutanteBusqueda {
    //Atributos

    //Método constructor
    mutanteBusqueda(){
    }
        
	 //Función para validar bases horizontalmente
    public static int horizontal(String [] dna){
        int conteo = 0;
        char letra = ' ';
        int numHorizontal = 0;
        for (String baseNitrogenada : dna) {
            /*Solo se validan las bases que tengan mas de 3 letras, teniendo en cuenta que a está
            función llega el arreglo de diagonales.*/
            if (baseNitrogenada.length()>3) {
                for (int j = 0; j<baseNitrogenada.length(); j++) {
                    if (letra == baseNitrogenada.charAt(j)) {
                        conteo++;
                        if(conteo == 3){
                            numHorizontal++;
//                            System.out.println(dna[i]);
                        }
                    } else {
                        conteo = 0;
                        letra = baseNitrogenada.charAt(j);
                    }
                }
            }
        }
        return numHorizontal;
    }

    //Función para validar bases verticalmente
    public static int vertical(String [] dna, String baseNitrogenada){
        int conteo = 0;
        char letra = ' ';
        int numVertical = 0;
        for(int j=0; j<baseNitrogenada.length(); j++){
            for (String dna1 : dna) {
                if (letra == dna1.charAt(j)) {
                    conteo++;
                    if(conteo == 3){
                        numVertical++;
                    }
                } else {
                    conteo = 0;
                    letra = dna1.charAt(j);
                }
            }
        }
        return numVertical;
    }

    //Función para validar bases diagonales negativas
    public static int diagonal1(String[] dna, String baseNitrogenada){
        //se crea arrayList con el numero de bases posibles en diagonal
        ArrayList<String> diagonal1 = new ArrayList<>();
        for(int i=0; i<(dna.length+baseNitrogenada.length()-1); i++){
            diagonal1.add("");
        }
        String dataPosition;
        //se empiezan a organizar las bases diagonales teniendo en cuenta relación de i con j (relación con resta)
        for(int j=0; j<baseNitrogenada.length(); j++){
            for(int i=0; i<dna.length; i++){
                if(i==j){
                    dataPosition = diagonal1.get(0);
                    diagonal1.set(0, dataPosition+dna[i].charAt(j));
                }
                else if(i>j){
                    dataPosition = diagonal1.get(i-j);
                    diagonal1.set(i-j, dataPosition+dna[i].charAt(j));
                }
                else if(j>i){
                    dataPosition = diagonal1.get((dna.length-1)+(j-i));
                    diagonal1.set((dna.length-1)+(j-i), dataPosition+dna[i].charAt(j));
                }
            }
        }

        /*se convierte el arrayList en un array regular y se envía a la función horizontal, la 
        cual retorna el número de consecutivos encontrados*/
        String[] diagonal = diagonal1.toArray(new String[0]);
        return horizontal(diagonal);
    }
    
    //Función para validar bases diagonales positivas
    public static int diagonal2(String[] dna, String baseNitrogenada){
        //se crea arrayList con el numero de bases posibles en diagonal
        ArrayList<String> diagonal1 = new ArrayList<>();
        for(int i=0; i<(dna.length+baseNitrogenada.length()-1); i++){
            diagonal1.add("");
        }
        String dataPosition;
        //se empiezan a organizar las bases diagonales teniendo en cuenta relación de i con j (relación con suma)
        for(int j=baseNitrogenada.length()-1; j>=0; j--){
            for(int i=0; i<dna.length; i++){
                if(i+j == baseNitrogenada.length()-1){
                    dataPosition = diagonal1.get(i+j);
                    diagonal1.set(i+j, dataPosition+dna[i].charAt(j));
                }
                else if(i+j > baseNitrogenada.length()-1){
                    dataPosition = diagonal1.get(i+j);
                    diagonal1.set(i+j, dataPosition+dna[i].charAt(j));
                }
                else if(i+j < baseNitrogenada.length()-1){
                    dataPosition = diagonal1.get(i+j);
                    diagonal1.set(i+j, dataPosition+dna[i].charAt(j));
                }
            }
        }
        
        /*se convierte el arrayList en un array regular y se envía a la función horizontal, la 
        cual retorna el número de consecutivos encontrados*/
        String[] diagonal = diagonal1.toArray(new String[0]);
        return horizontal(diagonal);
    }

    //Función para validar si la secuencia de dna es de un mutante o un humano.
    public static boolean isMutant(String[] dna, String baseNitrogenada){
        boolean mutant;
        /*desde aquí se llaman las funciones horizontal, vertical y diagonal positiva y negativa, las
        cuales retornan el número de consecutivos.*/
        int horizontal = horizontal(dna);
        int vertical = vertical(dna, baseNitrogenada);
        int diagonal1 = diagonal1(dna, baseNitrogenada);
        int diagonal2 = diagonal2(dna, baseNitrogenada);
        int sumaConsecutivos = horizontal + vertical + diagonal1 + diagonal2;
        
        mutant = sumaConsecutivos>=2;
        
        return mutant;
    }
	
}
