/*
import java.util.NoSuchElementException;

public class Lista<E> {

    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public Lista() {

        Celula<E> sentinela = new Celula<>();

        this.primeiro = this.ultimo = sentinela;
        this.tamanho = 0;
    }

    public boolean vazia() {

        return (this.primeiro == this.ultimo);
    }

    public void inserir(E novo, int posicao) {

        Celula<E> anterior, novaCelula, proximaCelula;

        if ((posicao < 0) || (posicao > this.tamanho))
            throw new IndexOutOfBoundsException("Não foi possível inserir o item na lista: "
                    + "a posição informada é inválida!");

        anterior = this.primeiro;
        for (int i = 0; i < posicao; i++)
            anterior = anterior.getProximo();

        novaCelula = new Celula<>(novo);

        proximaCelula = anterior.getProximo();

        anterior.setProximo(novaCelula);
        novaCelula.setProximo(proximaCelula);

        if (posicao == this.tamanho)  // a inserção ocorreu na última posição da lista
            this.ultimo = novaCelula;

        this.tamanho++;
    }

    public E remover(int posicao) {

        Celula<E> anterior, celulaRemovida, proximaCelula;

        if (vazia())
            throw new IllegalStateException("Não foi possível remover o item da lista: "
                    + "a lista está vazia!");

        if ((posicao < 0) || (posicao >= this.tamanho ))
            throw new IndexOutOfBoundsException("Não foi possível remover o item da lista: "
                    + "a posição informada é inválida!");

        anterior = this.primeiro;
        for (int i = 0; i < posicao; i++)
            anterior = anterior.getProximo();

        celulaRemovida = anterior.getProximo();

        proximaCelula = celulaRemovida.getProximo();

        anterior.setProximo(proximaCelula);
        celulaRemovida.setProximo(null);

        if (celulaRemovida == this.ultimo)
            this.ultimo = anterior;

        this.tamanho--;

        return (celulaRemovida.getItem());
    }
    public void inserirInicio(E item){
        Celula<E> aux=primeiro;
        Celula<E> itemnovo=new Celula<>(item,aux.getProximo());
        aux.setProximo(itemnovo);
    }
    public void inserirFinal(E item){
        Celula<E> aux=primeiro;
        Celula<E> itemnovo=new Celula<>(item,null);
        while(aux.getProximo()!=null){
            aux=aux.getProximo();
        }
        aux.setProximo(itemnovo);
    }
    public void inserirPosicao(E item, int posicao){
        Celula<E> aux=primeiro;
        int i=0;
        while(i!=posicao){
            aux=aux.getProximo();
            i++;
        }
        Celula<E> itemnovo=new Celula<>(item,aux.getProximo());
        aux.setProximo(itemnovo);
    }
    public E removerInicio() {
        if (vazia()) {
            throw new IllegalStateException("Lista vazia!");
        }
        Celula<E> celulaRemovida = primeiro.getProximo(); // ✅ pega o primeiro real
        primeiro.setProximo(celulaRemovida.getProximo()); // ✅ religa a sentinela
        celulaRemovida.setProximo(null);                  // ✅ desliga o removido

        if (celulaRemovida == ultimo)  // caso especial — era o único elemento
            ultimo = primeiro;

        tamanho--;
        return celulaRemovida.getItem(); // ✅ retorna o item
    }
    public E removerFinal() {
        if (vazia()) {
            throw new IllegalStateException("Lista vazia!");
        }
        Celula<E> atual = primeiro.getProximo();
        for(int i=0;i<this.tamanho-1;i++){
            atual=atual.getProximo();
        }
        Celula<E> celulaRemovida = atual.getProximo();
        atual.setProximo(null);
        celulaRemovida.setProximo(null);
        ultimo.setProximo(null);
        ultimo = atual;
        tamanho--;
        return (celulaRemovida.getItem());
    }
    public E removerProcurado(E itemProcurado){
        Celula<E> aux=primeiro;
        Celula<E> celulaRemovida = null; // ✅ declara antes
        if(vazia()){
            throw new IllegalStateException("Lista vazia!");
        }
        for(int i=0;i<this.tamanho;i++){
            if(aux.getProximo().getItem().equals(itemProcurado)){
                if(aux.getProximo().getProximo()==null){
                    celulaRemovida = aux.getProximo();
                    aux.setProximo(celulaRemovida.getProximo());
                    celulaRemovida.setProximo(null);
                    ultimo = aux;
                    tamanho--;
                    break;
                }
                celulaRemovida = aux.getProximo();
                aux.setProximo(aux.getProximo().getProximo());
                celulaRemovida.setProximo(null);
                tamanho--;
                break;
            }
            aux=aux.getProximo();
        }
        return(celulaRemovida.getItem());
    }
    public int obterNumeroItens(){
        int itens=0;
        if(vazia()){
            throw new IllegalStateException("Lista vazia!");
        }
        Celula<E> atual=primeiro.getProximo();
        for(int i=0;i<this.tamanho;i++){
            itens++;
            atual=atual.getProximo();
        }
        return itens;
    }
    public int obterNumeroItensSimplificado(){
        return this.tamanho;
    }
    public E localizar(int posição){
        if(vazia()){
            throw new IllegalStateException("Lista vazia!");
        }
        Celula<E> aux=primeiro;
        for(int i=0;i<posição;i++){
            aux=aux.getProximo();
        }
        return aux.getProximo().getItem();
    }
    public void trocar(E itemX, E itemY){
        if(vazia()){
            throw new IllegalStateException("Lista vazia!");
        }
        Celula<E> celulaX = null;
        Celula<E> aux = primeiro.getProximo();
        while (aux != null) {
            if (aux.getItem().equals(itemX)) {
                celulaX = aux;
                break;
            }
            aux = aux.getProximo();
        }
        Celula<E> aux2 = primeiro.getProximo();
        Celula<E> celulaY = null;
        while (aux2 != null) {
            if (aux2.getItem().equals(itemY)) {
                celulaY = aux2;
                break;
            }
            aux2 = aux2.getProximo();
            }
        if (celulaX == null || celulaY == null) {
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        E item = celulaX.getItem();
        E item2 = celulaY.getItem();
        celulaX.setItem(item2);
        celulaY.setItem(item);
    }
    public void inverter(){
        Pilha<E> pilha = new Pilha<>();
        Celula<E> atual = primeiro.getProximo();
        while(atual!= null){
            pilha.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        primeiro.setProximo(null);
        ultimo = primeiro;
        this.tamanho=0;

        while(!pilha.vazia()){
            this.inserirFinal(pilha.desempilhar());
        }
    }
    public void inverterSemFuncInserir(){
        Pilha<E> pilha = new Pilha<>();
        Celula<E> atual = primeiro.getProximo();
        while(atual!= null){
            pilha.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        primeiro.setProximo(null);
        ultimo = primeiro;
        this.tamanho=0;

        while(!pilha.vazia()){
            atual.setItem(pilha.desempilhar());
            atual = atual.getProximo();
        }
    }
    public void concatenar(Lista<E> lista){
        this.ultimo = lista.primeiro.getProximo();//dei getproximo pq o primeiro é a sentinela
        this.tamanho = lista.tamanho + this.tamanho;
    }
    public void duplicarItens(){
        Celula<E> atual = primeiro.getProximo();
        while(atual!= null){
            Celula<E> aux = new Celula<>(atual.getItem(),atual.getProximo());
            atual.setProximo(aux);
            tamanho++;
            if (aux.getProximo() == null) {
                ultimo = aux;
            }
            atual = atual.getProximo().getProximo();
        }
    }
}
*/
//deixei comentado para quando rodar o Main n dar erro
