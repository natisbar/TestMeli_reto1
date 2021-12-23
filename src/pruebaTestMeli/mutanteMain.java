/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaTestMeli;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jandr
 */
public class mutanteMain {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese las bases separadas por coma y sin comillas: ");
        //String[] dna = {"AAAAGA","CGGTGC","TTATGA","ATAGGC","CTGATA","TCATTG"};
        String dna_limpio = sc.nextLine().trim();
        String[] dna = dna_limpio.split(",");
        String baseNitrogenada = dna[0];
        boolean validarSecuencia = false;
        boolean validarBaseIndividual = false;
        mutanteBusqueda busqueda = new mutanteBusqueda();
       
        //Validación tamaño de todas las bases, que sean iguales para que se cumpla la matriz.
        for (String dna1 : dna) {
            if (baseNitrogenada.length() != dna1.length()) {
                validarSecuencia = true;
            }
        //Validación de que cada base tenga unicamente las letras: A, C, G o T
            for (int j = 0; j<baseNitrogenada.length(); j++) {
                if (!(dna1.charAt(j) == 'A' || dna1.charAt(j) == 'C' || dna1.charAt(j) == 'G' || dna1.charAt(j) == 'T')) {
                    validarBaseIndividual = true;
                }
            }
        }
        
        /*Si alguna de las dos validaciones resulta siendo true, entonces la secuencia es invalida
          y no se ejecuta la función isMutant.*/
        if (validarSecuencia || validarBaseIndividual){
            System.out.println(validarSecuencia+ " - " + validarBaseIndividual);
            System.out.println("Secuencia invalida");
        }
        else if(!validarSecuencia && !validarBaseIndividual){
            System.out.println("Secuencia valida");
            System.out.println(busqueda.isMutant(dna, baseNitrogenada));
        }
    }  
}
