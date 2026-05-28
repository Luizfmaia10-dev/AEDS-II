import java.util.NoSuchElementException;

public class Fila<E> {

    private Celula<E> frente;
    private Celula<E> tras;

    Fila() {

        Celula<E> sentinela = new Celula<E>();
        frente = tras = sentinela;
    }

    public boolean vazia() {

        return (frente == tras);
    }

    public void enfileirar(E item) {

        Celula<E> novaCelula = new Celula<E>(item);

        tras.setProximo(novaCelula);
        tras = tras.getProximo();
    }

    public E desenfileirar() {

        E item = null;
        Celula<E> primeiro;

        item = consultarPrimeiro();

        primeiro = frente.getProximo();
        frente.setProximo(primeiro.getProximo());

        primeiro.setProximo(null);

        // Caso o item desenfileirado seja também o último da fila.
        if (primeiro == tras)
            tras = frente;

        return item;
    }

    public E consultarPrimeiro() {

        if (vazia()) {
            throw new NoSuchElementException("Nao há nenhum item na fila!");
        }

        return frente.getProximo().getItem();

    }

    public void imprimir() {

        Celula<E> aux;

        if (vazia())
            System.out.println("A fila está vazia!");
        else {
            aux = this.frente.getProximo();
            while (aux != null) {
                System.out.println(aux.getItem());
                aux = aux.getProximo();
            }
        }
    }
    public int obterNumeroItens(){
        Celula<E> atual=frente.getProximo();
        int cont=0;
        while (atual!=null){
            cont++;
            atual=atual.getProximo();
        }
        return cont;
    }
    public boolean contem(E item){
        Celula<E> aux=frente.getProximo();
        while (aux!=null){
            if (aux.getItem().equals(item)){
                return true;
            }
            aux=aux.getProximo();
        }
        return false;
    }
    public void remover(E item) {
        // 1. trata fila vazia
        if (vazia()){
            throw new RuntimeException("Fila vazia!");
        }
        // 2. declara anterior e atual
        Celula<E> anterior = frente; // sentinela
        Celula<E> atual = frente.getProximo();

        // 3. percorre até achar o item
        while (atual != null) {
            if (atual.getItem().equals(item)) {
                // 4. religar o anterior ao proximo do atual
                anterior.setProximo(atual.getProximo());
                atual.setProximo(null);
                // 5. caso especial: e se atual == tras?
                if (atual == tras) {
                    //atualizando a referencia de tras caso o atual seja = a tras
                    tras = anterior;
                }
                return;
            }
            anterior = atual;
            atual = atual.getProximo();
        }
    }
    public void concatenar(Fila<E> fila){
        Celula<E> aux=frente.getProximo();
        while (aux!=null){
            fila.enfileirar(aux.getItem());
            aux = aux.getProximo();
        }
    }
    public void concatenarAocontrario(Fila<E> fila){
        Celula<E> aux=frente.getProximo();
        while (aux!=null){
            this.enfileirar(aux.getItem());
            aux = aux.getProximo();
        }
    }
    public void concatenarSemDesemfileirar(Fila<E> fila) {
        this.tras.setProximo(fila.frente.getProximo());
        this.tras = fila.tras;
    }
    public Fila<E> inverter(){
        Pilha <E> pilha= new Pilha<>();
        Fila<E> fila = new Fila<>();
        Celula<E> aux=frente.getProximo();
        while (aux!=null){
            pilha.empilhar(aux.getItem());
            aux=aux.getProximo();
        }
        while (!pilha.vazia()){
            fila.enfileirar(pilha.desempilhar());
        }
        return fila;
    }
    public int obterNumItensAFrente(E item){
        Celula<E> aux=frente.getProximo();
        int cont=0,itemcont=0;
        while (aux!=null){
            if(cont>0){
                itemcont++;
            }
            if (aux.getItem().equals(item)){
                cont++;
            }
            aux = aux.getProximo();
        }
        return itemcont;
    }
    public Fila<E> copiar(){
        Fila<E> filacopiada = new Fila<>();
        Celula<E> aux=frente.getProximo();
        if(vazia()){
            throw new RuntimeException("Fila vazia!");
        }
        while (aux!=null){
            filacopiada.enfileirar(aux.getItem());
            aux = aux.getProximo();
        }
        return filacopiada;
    }
    public Fila<E> dividir(){
        Fila<E> filadivida = new Fila<>();
        Fila <E> impar = new Fila<>();
        Fila <E>  par = new Fila <>();
        Celula<E> aux=frente.getProximo();
        while (aux!=null){
            if(aux.getItem()%2==0){
                par.enfileirar(aux.getItem());
            }else if ((aux.getItem()%2)!=0) {
                impar.enfileirar(aux.getItem());
            }
            aux=aux.getProximo();
        }
        filadivida.frente = impar.frente;       // começa com impar
        filadivida.tras = impar.tras;           // tras aponta para fim do impar
        filadivida.tras.setProximo(par.frente.getProximo());
        filadivida.tras = par.tras;
        return filadivida;
    }

    public Fila<E> dividirPosicao() {
        Fila<E> par = new Fila<>();
        Fila<E> impar = new Fila<>();
        Celula<E> aux = frente.getProximo();
        int posicao = 0;

        while (aux != null) {
            if (posicao % 2 == 0) {
                par.enfileirar(aux.getItem());
            } else {
                impar.enfileirar(aux.getItem());
            }
            posicao++;
            aux = aux.getProximo();
        }

        // atualiza a fila original com os ímpares
        this.frente = impar.frente;
        this.tras = impar.tras;

        // retorna os pares
        return par;
    }
    public Fila<E> intercalar(Fila<E> fila){
        Celula<E> aux=frente.getProximo();
        Celula<E> aux2=fila.frente.getProximo();
        Fila<E> filaIntercalada = new Fila<>();
        /*
        while (aux!=null){                 //ja nesse aqui se uma terminava o while ja acabava
            filaIntercalada.enfileirar(aux.getItem());
            filaIntercalada.enfileirar(aux2.getItem());
            aux=aux.getProximo();
            aux2=aux2.getProximo();
        }
        return filaIntercalada;
        */

        while (aux != null || aux2 != null) {  //aqui nesse while mesmo se parar uma fila ele imfileira o resto
            if (aux != null) {
                filaIntercalada.enfileirar(aux.getItem());
            }
            if (aux2 != null) {
                filaIntercalada.enfileirar(aux2.getItem());
            }
            aux = aux.getProximo();
            aux2 = aux2.getProximo();
        }
        return filaIntercalada;
    }
    public void moverParaOFinal(E item){
        int posicao=0;
        Celula<E> atual=frente.getProximo();
        while (atual!=null){
            if (atual.getItem().equals(item)){
                this.remover(item);
            }
            posicao++;
            atual=atual.getProximo();
        }
        this.enfileirar(item);
    }
    public void moverParaOFinalSemRemoverSemenfileirar(E item){
        int posicao=0;
        Celula<E> celulaDoItem=new Celula<>(item,null);
        Celula<E> atual=frente.getProximo();
        Celula<E> aux=frente;
        while (atual!=null){
            if (atual.getItem().equals(item)){
                aux.setProximo(atual.getProximo());
                atual.setProximo(null);
                if(atual==tras){
                    tras = aux;
                }
            }
            posicao++;
            aux=atual;
            atual=atual.getProximo();
        }
        this.tras.setProximo(celulaDoItem);
        this.tras = celulaDoItem;
    }
    public boolean equals(Fila<E> fila){
       Celula<E> aux=frente.getProximo();
       Celula<E> aux2=fila.frente.getProximo();
        while (aux != null || aux2 != null) {
            if (aux == null || aux2 == null) {
                return false; // tamanhos diferentes ❌
            }
            if (aux.getItem() != aux2.getItem()) {
                return false;
            }
            aux=aux.getProximo();
            aux2=aux2.getProximo();

        }
        return true;
    }
    public void removerDuplicatas(){
        Celula<E> atual = frente.getProximo();
        while (atual != null) {
            Celula<E> verificador = frente.getProximo();
            while (verificador != atual) {
                if (verificador.getItem().equals(atual.getItem())) {
                    Celula<E> proximo = atual.getProximo();
                   remover(atual.getItem());
                    atual = proximo;
                    break;
                }
                verificador = verificador.getProximo();
            }
            atual = atual.getProximo();
        }
    }
    public void removerKElementos(int k){
        if(vazia())
            return;
        if(k<0 || k>this.obterNumeroItens()){
            throw new IllegalArgumentException();
        }
        for(int i=0;i<k;i++){
            desenfileirar();
        }
    }

}