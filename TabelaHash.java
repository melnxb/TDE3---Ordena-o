/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tabela.hash;

import java.util.Random;

public class TabelaHash {
    Registro[] tabela;
    static int comparacoes=0;
    static int contador=0; //verifica se nao passou do tamanho max da tabela
    static int contadorParaColisao=0;
    static int tamanhoTabela;
    
    public static void main(String[] args) {
        int tamanhoTabela = 100; //instancia
        TabelaHash tabelaHash = new TabelaHash(tamanhoTabela);
        
        //100 mil, 500 mil, 1 milhão e 5 milhões
        int tamanhoConjunto = 700000; //10 elementos esse conjunto
        long seed = 100;
        Registro[] conjunto = gerarConjunto(tamanhoConjunto, seed);
        //long tempoInicial = System.nanoTime();
        //long tempoFinal = System.nanoTime();
        //long tempoTotal = (tempoFinal - tempoInicial); //para milissegundoa
        
        long inicioInsersao = System.nanoTime();
        for (int i = 0; i < tamanhoConjunto; i++) { 
            Registro registro = conjunto[i];
            tabelaHash.insere_hashing(registro);        
        }
        long fimInsercao = System.nanoTime();
        long tempoTotalInsercao = (fimInsercao - inicioInsersao); //para milissegundoa
        System.out.println("Tempo em milisseg. para a Insercao: ");
        System.out.printf("%.3f ms%n", (tempoTotalInsercao) / 1000000.0); 
        //tabelaHash.imprimirTabelaHash();
        
        long inicioBusca = System.nanoTime();
        for (int i = 0; i < tamanhoConjunto; i++) { 
            Registro registro = conjunto[i];
            tabelaHash.busca_hashing(registro.getCodigoRegistro());        
        }
        long fimBusca = System.nanoTime();
        long tempoTotalBusca = (fimBusca - inicioBusca); //para milissegundoa
        
        
        //calculando tempo de execucao em milissigundos
        //(final-inicial) do tempo apos chamar funcao de ordenacao
        System.out.println("Tempo em milisseg. para a Busca: ");
        System.out.printf("%.3f ms%n", (tempoTotalBusca) / 1000000.0); // == conversao para milissegundos
        
        System.out.println("Cont colisoes: " + contadorParaColisao);
        System.out.println("Coparacoes: " + comparacoes);
    }
    
    public TabelaHash(int tamanhoTabela) {
        tabela = new Registro[tamanhoTabela];
        this.tamanhoTabela = tamanhoTabela;
        contador = 0;
    }
    
    public void imprimirTabelaHash() {
        for (int i = 0; i < tamanhoTabela; i++) {
            if (tabela[i] != null) {
                System.out.println("Indice: " + i + ", CodigoRegistro: " + tabela[i].getCodigoRegistro());
            } else {
                System.out.println("Indice: " + i + ", CodigoRegistro: vazio");
            }
        }
    }

    private int r(int i) {
        return (i + 1) % tamanhoTabela;
    }

    public void insere_hashing(Registro registro) {
        /*if (contador == tamanhoTabela) {
            System.out.println("Tabela Hash está cheia");
            return;
        } */
        //int i = hashMultiplicacao(chave);
        //int i = hashDobramento(chave);
        int i = hashRestoDivisao(registro.getCodigoRegistro()); // ou qualquer função hash que você escolher
        if (tabela[i] == null) {
            tabela[i] = registro;
        } else {
            Registro atual = tabela[i];
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
                contadorParaColisao++;
        }
        atual.setProximo(registro);
    }
        /* while (tabela[i] != null) {
            i = r(i);
            contadorParaColisao++;
        } 
        tabela[i] = registro; */
        contador++;
    }

    public int busca_hashing(int chave) {
        int i = hashRestoDivisao(chave); // ou qualquer função hash que você escolher
        //int i = hashMultiplicacao(chave);
        //int i = hashDobramento(chave);
        //i=h(chave)
        Registro atual = tabela[i];
        while (atual != null && atual.getCodigoRegistro() != chave) {
            atual = atual.getProximo();
            comparacoes++;
    }
        if (atual == null) {
            return -1;
        } else {
            return i;
        }
        /*while (tabela[i] != null && tabela[i].getCodigoRegistro() != chave) {
            i = r(i);
            comparacoes++;
        }
        if (tabela[i] == null) {
            return -1;
        } else {
            return i;
        } */
    }
    //funcao hash Resto da Divisõa
    static int hashRestoDivisao(int chave) {
        int resto = chave % tamanhoTabela;
        //System.out.println("div: " + resto);
        return resto;
    }
       //funcao Hash Multiplicacao 
    static int hashMultiplicacao(int chave) {
    double A = 0.6180339887; // constante 0 < A < 1
    double valorConst = (chave * A) % 1;
    return (int) (tamanhoTabela * valorConst);
    }
    //funcao Hash Dobramento
    static int hashDobramento(int chave) {
        int parte1 = chave / 1000000;
         System.out.println("parte 1:: " + parte1);
        int parte2 = (chave / 1000) % 1000; 
        System.out.println("parte 2:: " + parte2);
        int parte3 = chave % 1000; 
        System.out.println("parte 3:: " + parte3);
        return (parte1 + parte2 + parte3) % tamanhoTabela;
    } 

    //gerar conjuntos aleatorios 
    private static Registro[] gerarConjunto(int tamanhoConjunto, long seed) {
        Random random = new Random(seed);//semente definida para manter padrao
        Registro[] conjunto = new Registro[tamanhoConjunto];
        for (int i = 0; i < tamanhoConjunto; i++) { // gera número de 9 dígitos
            int codigoRegistro = 100000000 + random.nextInt(900000000);
            conjunto[i] = new Registro(codigoRegistro);
        }
        return conjunto;
    }
}

class Registro {
    private int codigoRegistro;
    private Registro proximo;

    public Registro(int codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
        this.proximo = null;
    }
    public int getCodigoRegistro() {
        return codigoRegistro;
    }
    public void setCodigoRegistro(int codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }
    public static void imprimir(Registro[] vetor, int tamanho){
        for (int i=0; i<tamanho; i++){
        System.out.print(vetor[i]);
        if (i < tamanho - 1) {     //0-..
            System.out.print(", ");
        }        
        } System.out.println();   
    }
    public Registro getProximo() {
        return proximo;
    }
    public void setProximo(Registro proximo) {
        this.proximo = proximo;
    }
    
}

