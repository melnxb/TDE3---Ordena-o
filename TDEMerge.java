/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tdemerge;
import java.util.Random;


public class TDEMerge {
    //{50, 500, 1000, 5000, 10000};
    static int numItineracoes = 0;
    static int numTrocas = 0;

    public static void main(String[] args) {
        int tamanho = 10000;
        int[] vetor = numRandom(10000);
        
        //imprimir(vetor,50);
        System.out.println("aNTES DO MERGE");
        imprimir(vetor, tamanho);
        
        long tempoInicial = System.nanoTime(); //funcao para calculo de tempo
        mergeSort(vetor, 0, tamanho-1);//chamada da funcao Merge
        long tempoFinal = System.nanoTime();
        long tempoTotal = (tempoFinal - tempoInicial); //para milissegundoa
        
        System.out.println("DEPOIS DO MERGE");
        imprimir(vetor, tamanho);
        
        System.out.println("Tempo em milisseg. para a execucao: ");
        System.out.printf("%.6f ms%n", (tempoTotal) / 1000000.0); //convertendo p/ milissegundos
 
        System.out.println("Num. trocas: "+numTrocas);
        System.out.println("Num. iteracoes: "+numItineracoes);
    }
    
    private static void mergeSort(int[] vetor, int esquerda, int direita) {
        //a funcao divide o vetor e chama a si mesma até que vetor[]=1
        if (esquerda < direita) {
            int meio = (esquerda + direita) / 2;
            mergeSort(vetor, esquerda, meio);
            mergeSort(vetor, meio + 1, direita);
            //ao fim chama merge
            merge(vetor, esquerda, meio, direita);
        }
    }
    //Funcao para fazer emsclagem do vetores separados por mergeSort()
    private static void merge(int[] vetor, int esquerda, int meio, int direita){
        int aux1 = meio - esquerda+1; //caclucla o tamanho do vetor pelo parâm.
        int aux2 = direita - meio;    //ex/meio=5+1
        
        int[] vetorEsquerda = new int[aux1];//vetor com os tamanhos calculados em aux..
        int[] vetorDireita = new int[aux2];
        
        for (int i = 0; i < aux1; i++) //inserindo elementos de esquerda+1 até o meio
            vetorEsquerda[i] = vetor[esquerda + i];
        for (int j = 0; j < aux2; j++)//inserindo atpe tamanho de aux2
            vetorDireita[j] = vetor[meio + 1 + j];
        int i = 0, j = 0;
        int k = esquerda; //iniciando 'mesclagem entre vetores'->vetor original
        while (i < aux1 && j < aux2) {
            numItineracoes++; //se elemneto da Esq. < que o da Dir. add to Vetor
            if (vetorEsquerda[i] <= vetorDireita[j]) {
                vetor[k] = vetorEsquerda[i];
                i++;
            } else { //ou add o elemento da direita
                vetor[k] = vetorDireita[j];
                j++;
                numTrocas++;
            }
            k++;
        }   //sendo vetores de tamanho diferentes aplica
           //tambem outra consicoes para que nao falte algum numero
        while (i < aux1) {
            vetor[k] = vetorEsquerda[i];
            i++;
            k++;
        }
        while (j < aux2) {
            vetor[k] = vetorDireita[j];
            j++;
            k++;
        }
    }
    
    //gerando num randomicos entre 0 e 1000+ add to vetor
    private static int[] numRandom(int tam) {
        Random rand = new Random();//
        int[] vetor = new int[tam]; //passa X numeros aleatorios para dentro do vetor
        for (int i = 0; i < tam; i++) {
            vetor[i] = rand.nextInt(1000);
        }
        return vetor;
    }
    
    //funcao para imprimir vetores /pulalinha
    public static void imprimir(int[] vetor, int tamanho){
        for (int i=0; i<tamanho; i++){
        System.out.print(vetor[i]);
        if (i < tamanho - 1) {
            System.out.print(", ");
        }        
        } System.out.println();   
    }
    
    //static mergeSort{
    
}
    

