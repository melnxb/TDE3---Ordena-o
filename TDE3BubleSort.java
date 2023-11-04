/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tde3bublesort;

import java.util.Random;

public class TDE3BubleSort {
    //{50, 500, 1000, 5000, 10000};
    static int numIteracoes = 0;//inicializando variaveis->contador
    static int numTrocas = 0;

    public static void main(String[] args) {
        int tamanho = 10000;
        int[] vetor = numRandom(10000);
        
        System.out.println("Antes da Ordenacao com Bubel:");
        imprimir(vetor, tamanho);
        
        long tempoInicial = System.nanoTime();
        bubbleSort(vetor, tamanho);
        long tempoFinal = System.nanoTime();
        long tempoTotal = (tempoFinal - tempoInicial); //para milissegundoa
        
        System.out.println("Depois da Ordenacao com buble Sort:");
        imprimir(vetor, tamanho);
        
        //calculando tempo de execucao em milissigundos
        //(final-inicial) do tempo apos chamar funcao de ordenacao
        System.out.println("Tempo em milisseg. para a execucao: ");
        System.out.printf("%.6f ms%n", (tempoTotal) / 1000000.0); // == conversao para milissegundos
 
        System.out.println("Num. trocas: "+numTrocas);
        System.out.println("Num. iteracoes: "+numIteracoes);
    }
    
    
    public static void bubbleSort(int[] vetor, int tamanho){
        //int tam=0;
        int aux;
        
        //para quantas vezes sera feita ordenacao
        for (int i = 0; i < tamanho; i++){ //maior elemento ja vai ser jogado para o final
            for(int j = 1; j<tamanho-i; j++){   //para comparacao
                numIteracoes++;
                if(vetor[j-1]>vetor[j]){
                aux = vetor[j-1];
                vetor[j-1]=vetor[j];
                vetor[j] = aux;
                numTrocas++;
                }
            }
        }
    }
    
    //funcao que retorna valores aleatorios dentro de um array
    private static int[] numRandom(int tam) {
        Random rand = new Random();//
        int[] vetor = new int[tam];
        for (int i = 0; i < tam; i++) {
            vetor[i] = rand.nextInt(100000);
        }
        return vetor;
    }


    //funcao para imprimir vetor/pula linha
    public static void imprimir(int[] vetor, int tamanho){
        for (int i=0; i<tamanho; i++){
        System.out.print(vetor[i]);
        if (i < tamanho - 1) {     //0-..
            System.out.print(", ");
        }        
        } System.out.println();   
    }
    
    
    
}
