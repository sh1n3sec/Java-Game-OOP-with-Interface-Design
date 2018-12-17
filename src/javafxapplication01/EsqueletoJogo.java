package javafxapplication01;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EsqueletoJogo {

    private int array[][] = new int[8][8];
    private int arrayAux[][] = new int[8][8];
    int[] valores = {1, 2, 3, 4, 5, 6, 7};
    private int tabuleiro = 0;
    Random gerador = new Random();
    public int score = 0;

    public int ajuda = 0;
    int ajudacol = 0;
    int ajudalinha = 0;
    int arrayNovo[] = new int[2];
    public ArrayList valores1 = new ArrayList();
    public int valorRandom;
    public int posi2 = 0;
    public int hintz = 5;

    EsqueletoJogo() {

        inicializaTabuleiro();

    }

    public void inicializaTabuleiro() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int valorRandom = gerador.nextInt(7);
                array[i][j] = valores[valorRandom];
                arrayAux[i][j] = valores[valorRandom];

            }
        }

    }

    public void setScore(int score) {
        this.score = score;
    }

    public void imprime() {

        System.out.println("====================================================================");
        System.out.println("|Colunas|  1      2       3       4       5       6       7       8|");
        System.out.println("========|===========================================================");
        System.out.println("|Linhas |");
        System.out.println("========|");
        for (int i = 0; i < 8; i++) {
            System.out.print("|   " + (i + 1) + "   |");
            for (int j = 0; j < 8; j++) {

                System.out.print("  " + array[i][j] + "\t");

            }
            System.out.println();
        }
        System.out.println("====================================================================");
    }

    public void imprimeAux() {
        System.out.println("");
        System.out.println("Array Aux");
        System.out.println("");
        System.out.println("====================================================================");
        System.out.println("|Colunas|  1      2       3       4       5       6       7       8|");
        System.out.println("========|===========================================================");
        System.out.println("|Linhas |");
        System.out.println("========|");

        for (int i = 0; i < 8; i++) {
            System.out.print("|   " + (i + 1) + "   |");

            for (int j = 0; j < 8; j++) {

                System.out.print("  " + arrayAux[i][j] + "\t");

            }
            System.out.println();
        }

    }

    public void copyArrayToArrayAux() {
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                arrayAux[i][j] = array[i][j];
            }

        }

    }

    public void copyArrayAuxToArray() {
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                array[i][j] = arrayAux[i][j];
            }

        }

    }

    public void setValores(int[] valores) {
        this.valores = valores;
    }

    public int[] getValores() {
        return valores;
    }

    public int getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(int tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public int getPeca(int n) {

        return this.arrayAux[n / 8][n % 8];

    }

    public int getArrayCord(int linhas, int colunas) {

        return arrayAux[linhas][colunas];
    }

    public void setArrayCord(int linhas, int colunas, int valor) {

        array[linhas][colunas] = valor;
        arrayAux[linhas][colunas] = valor;

    }

    public int getScore() {

        return score;
    }

    public boolean verificarLinha() {

        boolean r = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 6; j++) {
                int valor = arrayAux[i][j];
                int contaReps = 1;
                int jj = j + 1;

                while (jj < 8 && arrayAux[i][jj] == valor) {
                    contaReps++;
                    jj++;
                }
                if (contaReps >= 3) {
                    r = true;
                    if (contaReps == 3) {
                        score += (contaReps * 100);
                    }
                    if (contaReps == 4) {
                        score += (contaReps * 200);
                    }
                    if (contaReps == 5) {
                        score += (contaReps * 400);
                    }
                    if (contaReps == 6) {
                        score += (contaReps * 700);
                    }
                    if (contaReps == 7) {
                        score += (contaReps * 1000);
                    }

                    //System.out.println("|      Valor " + valor + " encontrado na Linha <" + (i + 1) + "> e na Coluna <" + (j + 1) + "> " + contaReps + " vezes     |");
                    substituirValorLinha(i, j, contaReps);
                    descerValoresLinhas();
                    reporValores();
                    j += contaReps;

                }
            }
        }

        return r;
    }

    public boolean verificarColuna() {

        boolean r = false;

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 6; i++) {
                int valor = arrayAux[i][j];

                int contaReps = 1;
                int ii = i + 1;

                while (ii < 8 && arrayAux[ii][j] == valor) {
                    contaReps++;
                    ii++;
                }
                if (contaReps >= 3) { //caso o conta reps some 3 ou mais valores iguais seguidos, este if corre. 
                    r = true;

                    if (contaReps == 3) {       //Sistema de combos, por cada numero igual seguido, multiplica por x pontos. 
                        score += (contaReps * 100); //Quanto maior for a sequencia maior é o multiplicador, para encorajar o jogador a fazer sequencias maiores.
                    }
                    if (contaReps == 4) {
                        score += (contaReps * 200);
                    }
                    if (contaReps == 5) {
                        score += (contaReps * 400);
                    }
                    if (contaReps == 6) {
                        score += (contaReps * 700);
                    }
                    if (contaReps == 7) {
                        score += (contaReps * 1000);
                    }
                    if (contaReps == 8) {
                        score += (contaReps * 2000);
                    }

                    //System.out.println("|      Valor " + valor + " encontrado na Linha <" + (i + 1) + "> e na Coluna <" + (j + 1) + "> " + contaReps + " vezes     |");
                    substituirValorColuna(i, j, contaReps); //Se encontrar 3 valores ou mais iguais seguidos corre este método.
                    descerValoresColunas(contaReps); //Se encontrar 3 valores ou mais iguais seguidos corre este método.
                    reporValores();
                    i += contaReps;

                }
            }
        }

        return r;
    }

    public void substituirValorLinha(int i, int j, int contaReps) {

        for (int coluna = j; coluna < (j + contaReps); coluna++) { //O valor do j passado do for anterior é somado com o contaReps do for passado e todos esses valores são substituidos por 0. 
            if (coluna < (j + contaReps)) {
                arrayAux[i][coluna] = 0;
            }
        }

    }

    public void substituirValorColuna(int i, int j, int contaReps) { //O valor do i passado do for anterior é somado com o contaReps do for passado e todos esses valores são substituidos por 0.
        for (int linha = i; linha < (i + contaReps); linha++) {
            if (linha < (i + contaReps)) {
                arrayAux[linha][j] = 0;
            }
        }

    }

    public void descerValoresLinhas() {  //Encontra o valor 0 no array e basicamente verificando de baixo para cima vai igualando esse valor 
        //ao de cima e puxar o 0 para cima (basicamente subindo o 0 até o máximo possivel)
        for (int j = 0; j < 8; j++) {
            for (int i = 7; i > 0; i--) {

                if (arrayAux[i][j] == 0) {
                    arrayAux[i][j] = arrayAux[(i - 1)][j];
                    arrayAux[(i - 1)][j] = 0;

                }
            }
        }

    }

    public void descerValoresColunas(int contaReps) { //Encontra o valor 0 no array e basicamente verificando de baixo para cima vai igualando esse valor 
        //ao de cima e puxar o 0 para cima (basicamente subindo o 0 até o máximo possivel)
        for (int j = 0; j < 8; j++) {
            for (int i = 7; i >= contaReps; i--) { //convem ser o i até maior ou igual ao contaReps pois assim sei de certeza que 
                if (arrayAux[i][j] == 0) {              //ele não verifica de mais para cima e não dá erro por ultrapassar o máximo.
                    arrayAux[i][j] = arrayAux[(i - contaReps)][j];
                    arrayAux[(i - contaReps)][j] = 0;

                }
            }
        }

    }

    public void reporValores() {  //Sempre que encontra um 0 no array substitui esse valor por um valor random.

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (arrayAux[i][j] == 0) {
                    int valorRandom = gerador.nextInt(7);
                    arrayAux[i][j] = valores[valorRandom];

                }

            }

        }

    }

   

    public void verificarNova() {

        while (verificarLinha() || verificarColuna());

    }

    public boolean verificarNova2() {
        boolean k = false;

        if ((verificarLinha() || verificarColuna()) == true) {
            k = true;
        }
        return k;
    }

    public boolean verificarNova3() {

        boolean valor = false;

        while (verificarLinha() || verificarColuna()) {
            valor = true;
        }

        return valor;
    }

    public void hints() {
        ajudalinha = 0;
        ajudacol = 0;
        System.out.println();

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 5; j++) {

                int jj = j + 1;

                if (arrayAux[i][j] == arrayAux[i][jj] && arrayAux[i][jj] == arrayAux[i][(jj + 2)]) { //pelo menos 2 elementos repetidos numa linha e o 3º está à direita.

                    arrayNovo[0] = (i);
                    arrayNovo[1] = (jj + 2);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);

                    //"Mexa a peça [" + (i + 1) + "][" + (jj + 3) + "]");
                    //Ideal passa por substituir diretamente no array valores1 (ArrayList) já pelo numero que vai aparecer na posi.. e depois quando é escolhido
                    //aleatoriamente no fim das hints um valores1 esse vai ser o numero que vai aparecer no nosso posi e depois nas hints no button handle action.
                    ajudalinha++;

                }

                if (arrayAux[i][j] == arrayAux[i][(jj + 1)] && arrayAux[i][(jj + 1)] == arrayAux[i][(jj + 2)]) {//pelo menos 2 elementos repetidos numa linha e o 3º está à esquerda.

                    //valores.add("PISTA: mova uma peça na linha <" + (i + 1) + ">");
                    arrayNovo[0] = (i);
                    arrayNovo[1] = (j);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);

                    //valores1.add("Mexa a peça [" + (i + 1) + "][" + jj + "]");
                    ajudalinha++;

                }

            }
        }

        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 6; j++) {

                int jj = j + 1;

                if (arrayAux[(i + 1)][j] == arrayAux[i][jj] && arrayAux[i][jj] == arrayAux[(i + 1)][(jj + 1)]) {
                    /*      5
                          5   5
                     */

                    //valores.add("PISTA: mova uma peça na linha <" + (i + 1) + ">");
                    //valores1.add("Mexa a peça [" + (i + 1) + "][" + (jj + 1) + "]");
                    arrayNovo[0] = (i);
                    arrayNovo[1] = (jj);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);
                    ajudalinha++;

                }
                if (arrayAux[i][j] == arrayAux[(i + 1)][jj] && arrayAux[(i + 1)][jj] == arrayAux[i][(jj + 1)]) {
                    /*     5   5
                             5
                     
                     */

                    //valores.add("PISTA: mova uma peça na linha <" + (i + 2) + ">");
                    //valores1.add("Mexa a peça [" + (i + 2) + "][" + (jj + 1) + "]");
                    arrayNovo[0] = (i + 1);
                    arrayNovo[1] = (jj);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);
                    ajudalinha++;

                }

                if (arrayAux[(i + 1)][j] == arrayAux[(i + 1)][jj] && arrayAux[(i + 1)][jj] == arrayAux[i][(jj + 1)]) {//pelo menos 2 elementos repetidos numa linha e o 3º está à direita na linha acima.

                    //valores.add("PISTA: mova uma peça na linha <" + (i + 1) + ">");
                    //valores1.add("Mexa a peça [" + (i + 1) + "][" + (jj + 2) + "]");
                    arrayNovo[0] = (i);
                    arrayNovo[1] = (jj + 1);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);
                    ajudalinha++;

                }
                if (arrayAux[i][j] == arrayAux[(i + 1)][jj] && arrayAux[(i + 1)][jj] == arrayAux[(i + 1)][(jj + 1)]) {//pelo menos 2 elementos repetidos numa linha e o 3º está à esquerda na linha acima.

                    //valores.add("PISTA: mova uma peça na linha <" + (i + 1) + ">");
                    //valores1.add("Mexa a peça [" + (i + 1) + "][" + jj + "]");
                    arrayNovo[0] = (i);
                    arrayNovo[1] = (j);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);
                    ajudalinha++;

                }
                if (arrayAux[i][j] == arrayAux[i][jj] && arrayAux[i][jj] == arrayAux[(i + 1)][(jj + 1)]) { //pelo menos 2 elementos repetidos numa linha e o 3º está à direita na linha abaixo.

                    //valores.add("PISTA: mova uma peça na linha <" + (i + 2) + ">");
                    //valores1.add("Mexa a peça [" + (i + 2) + "][" + (jj + 2) + "]");
                    arrayNovo[0] = (i + 1);
                    arrayNovo[1] = (jj + 1);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);
                    ajudalinha++;

                }

                if (arrayAux[(i + 1)][j] == arrayAux[i][jj] && arrayAux[i][jj] == arrayAux[i][(jj + 1)]) { //pelo menos 2 elementos repetidos numa linha e o 3º está à esquerda na linha abaixo.

                    //valores.add("PISTA: mova uma peça na linha <" + (i + 2) + ">");
                    //valores1.add("Mexa a peça [" + (i + 2) + "][" + jj + "]");
                    arrayNovo[0] = (i + 1);
                    arrayNovo[1] = (j);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);
                    ajudalinha++;

                }
            }
        }

        for (int j = 0; j < 8; j++) { //pista para as colunas

            for (int i = 0; i < 5; i++) {

                int ii = i + 1;

                if (arrayAux[i][j] == arrayAux[ii][j] && arrayAux[ii][j] == arrayAux[(ii + 2)][j]) { //2 elementos repetidos na mesma coluna e o 3º está na mesma coluna e em baixo.

                    //valores.add("PISTA: mova uma peça na coluna <" + (j + 1) + ">");
                    //valores1.add("Mexa a peça [" + (ii + 3) + "][" + (j + 1) + "]");
                    arrayNovo[0] = (ii + 2);
                    arrayNovo[1] = (j);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);

                    ajudacol++;

                }
                if (arrayAux[i][j] == arrayAux[(ii + 1)][j] && arrayAux[(ii + 1)][j] == arrayAux[(ii + 2)][j]) { //2 elementos repetidos na mesma coluna e o 3º está na mesma coluna e em cima.

                    //valores.add("PISTA: mova uma peça na coluna <" + (j + 1) + ">");
                    //valores1.add("Mexa a peça [" + ii + "][" + (j + 1) + "]");
                    arrayNovo[0] = (i);
                    arrayNovo[1] = (j);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);
                    ajudacol++;

                }
            }
        }

        for (int j = 0; j < 7; j++) {

            for (int i = 0; i < 6; i++) {

                int ii = i + 1;

                if (arrayAux[i][j] == arrayAux[ii][(j + 1)] && arrayAux[ii][(j + 1)] == arrayAux[(ii + 1)][j]) {
                    /*   5
                           5
                         5      
                     */

                    //valores.add("PISTA: mova uma peça na coluna <" + (j + 2) + ">");
                    //valores1.add("Mexa a peça [" + (ii + 1) + "][" + (j + 2) + "]");
                    arrayNovo[0] = (ii);
                    arrayNovo[1] = (j + 1);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);
                    ajudacol++;

                }

                if (arrayAux[i][(j + 1)] == arrayAux[ii][j] && arrayAux[ii][j] == arrayAux[(ii + 1)][(j + 1)]) {
                    /*    5
                        5  
                          5      
                     */

                    //valores.add("PISTA: mova uma peça na coluna <" + (j + 1) + ">");
                    //valores1.add("Mexa a peça [" + (ii + 1) + "][" + (j + 1) + "]");
                    arrayNovo[0] = (ii);
                    arrayNovo[1] = (j);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);
                    ajudacol++;

                }

                if (arrayAux[i][j] == arrayAux[ii][j] && arrayAux[ii][j] == arrayAux[(ii + 1)][(j + 1)]) { //2 elementos repetidos na mesma coluna e o 3º está na coluna à direita em baixo.

                    //valores.add("PISTA: mova uma peça na coluna <" + (j + 2) + ">");
                    //valores1.add("Mexa a peça [" + (ii + 2) + "][" + (j + 2) + "]");
                    arrayNovo[0] = (ii + 1);
                    arrayNovo[1] = (j + 1);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);
                    ajudacol++;

                }
                if (arrayAux[i][(j + 1)] == arrayAux[ii][j] && arrayAux[ii][j] == arrayAux[(ii + 1)][j]) { //2 elementos repetidos na mesma coluna e o 3º está na coluna à direita em cima.

                    //valores.add("PISTA: mova uma peça na coluna <" + (j + 2) + ">");
                    //valores1.add("Mexa a peça [" + ii + "][" + (j + 2) + "]");
                    arrayNovo[0] = (i);
                    arrayNovo[1] = (j + 1);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);
                    ajudacol++;

                }

                if (arrayAux[i][j] == arrayAux[ii][(j + 1)] && arrayAux[ii][(j + 1)] == arrayAux[(ii + 1)][(j + 1)]) {

                    //valores.add("PISTA: mova uma peça na coluna <" + (j + 1) + ">");//2 elementos repetidos na mesma coluna e o 3º está na coluna à esquerda em cima.
                    //valores1.add("Mexa a peça [" + ii + "][" + (j + 1) + "]");
                    arrayNovo[0] = (i);
                    arrayNovo[1] = (j);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);
                    ajudacol++;

                }
                if (arrayAux[i][(j + 1)] == arrayAux[ii][(j + 1)] && arrayAux[ii][(j + 1)] == arrayAux[(ii + 1)][j]) {

                    //valores.add("PISTA: mova uma peça na coluna <" + (j + 1) + ">");//2 elementos repetidos na mesma coluna e o 3º está na coluna à esquerda em baixo.
                    //valores1.add("Mexa a peça [" + (ii + 2) + "][" + (j + 1) + "]");
                    arrayNovo[0] = (ii + 1);
                    arrayNovo[1] = (j);
                    valores1.add((arrayNovo[0] * 8) + arrayNovo[1]);
                    ajudacol++;

                }
            }

        }

        ajuda = (ajudalinha + ajudacol);

        if (ajuda == 0) {
            
            posi2 = 0;
            return;

        } else {
            
            valorRandom = gerador.nextInt(valores1.size());
            
            posi2 = (int) valores1.get(valorRandom);
        }

        //System.out.println(posi2);
        //System.out.println(valores1);
       
        FXMLDocumentController.hintButtonPressed = true;
        

        return;

    }

    /*public void recomeçar() {

        System.out.println("");
        System.out.println("====================================================================");
        System.out.println("||----------------------------Bejules-----------------------------||");
        score = 0;
        ajuda = 0;
        ajudacol = 0;
        ajudalinha = 0;
        arraydenovo();
        getValores();
        copyArrayToArrayAux();
        verificarLinha();
        verificarColuna();
        verificarNova();
        trocaValor();
        verificarNova();

    }*/

   /* public void arraydenovo() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int valorRandom = gerador.nextInt(7);
                array[i][j] = valores[valorRandom];
                arrayAux[i][j] = valores[valorRandom];

            }
        }
    }*/

}
