
import java.util.NoSuchElementException;

public class Pilha<E> {

    private Celula<E> topo;
    private Celula<E> fundo;

    public Pilha() {
        Celula<E> sentinela = new Celula<E>();
        fundo = sentinela;
        topo = sentinela;
    }

    public boolean vazia() {
        return fundo == topo;
    }

    public void empilhar(E item) {
        topo = new Celula<E>(item, topo);
    }

    public E desempilhar() {
        E desempilhado = consultarTopo();
        topo = topo.getProximo();
        return desempilhado;
    }

    public E consultarTopo() {
        if (vazia()) {
            throw new NoSuchElementException("Nao há nenhum item na pilha!");
        }
        return topo.getItem();
    }

    public int somaItem() {
        int somaItem = 0;
        Celula<E> atual = topo;
        //.getItem
        //.getProximo
        if (vazia()) {
            return somaItem;
        }
        while (atual != fundo) {
            somaItem +=  atual.getItem();
            atual = atual.getProximo();
        }
        return somaItem;
    }
    public int numPratos(){
        Celula<E> atual = topo;
        int numPratos = 0;
        while (atual != fundo) {
            numPratos++;
            atual = atual.getProximo();
        }
        return numPratos;
    }

    public Pilha<E> concatenar(Pilha<E> pilha) {
        Pilha<E> pilhaconcatenada = new Pilha<>();
        if (vazia()) {
            return pilha;
        }

        Pilha<E> aux1 = new Pilha<>();
        Celula<E> atual = pilha.topo;
        Pilha<E> aux2 = new Pilha<>();

        while (atual != pilha.fundo) {
            aux1.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        atual = this.topo;
        while (atual != this.fundo) {
            aux2.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        Celula<E> c1 = aux1.topo;
        Celula<E> c2 = aux2.topo;

        while (c1 != aux1.fundo || c2 != aux2.fundo) {
            if (c1 != aux1.fundo) {
                pilhaconcatenada.empilhar(c1.getItem());
                c1 = c1.getProximo();
            }
            if (c2 != aux2.fundo) {
                pilhaconcatenada.empilhar(c2.getItem());
                c2 = c2.getProximo();
            }
        }
        return pilhaconcatenada;
    }

    public int obterNumeroItens() {
        int numItems = 0;
        Celula<E> atual = topo;
        while (atual != fundo) {
            numItems++;
            atual = atual.getProximo();
        }
        return numItems;
    }

    public void inverter() {
        Celula<E> atual = topo;
        Pilha<E> aux1 = new Pilha<>();
        while (atual != fundo) {
            aux1.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
    }

    public boolean isPalindromo(String palindromo) {
        Pilha<Character> pilha = new Pilha<>();
        String limpa = palindromo.toLowerCase().replaceAll("[^a-z]", "");
        for (int i = 0; i < limpa.length(); i++) {
            pilha.empilhar(limpa.charAt(i));
        }
        for (int i = 0; i < limpa.length(); i++) {
            char letraDoInicio = limpa.charAt(i);
            char letraDoFim = (char) pilha.desempilhar();
            if (letraDoInicio != letraDoFim) {
                return false;
            }
        }
        return true;
    }

    public Pilha<E> colocarPilhaEmCima(Pilha<E> pilha) {
        Pilha<E> resultado = new Pilha<>();
        Pilha<E> aux1 = new Pilha<>();
        Pilha<E> aux2 = new Pilha<>();

        Celula<E> atual = this.topo;
        while (atual != this.fundo) {
            aux1.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        atual = pilha.topo;
        while (atual != pilha.fundo) {
            aux2.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        atual = aux1.topo;
        while (atual != aux1.fundo) {
            resultado.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        atual = aux2.topo;
        while (atual != aux2.fundo) {
            resultado.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        return resultado;
    }

    public void imprimir() {
        Celula<E> atual = topo;
        while (atual != fundo) {
            System.out.print(atual.getItem());
            atual = atual.getProximo();
        }
    }

    public Pilha<E> copiar() {
        Pilha<E> aux = new Pilha<>();
        Celula<E> atual = this.topo;
        while (atual != fundo) {
            aux.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        Pilha<E> pilhaCopia = new Pilha<>();
        atual = aux.topo;
        while (atual != aux.fundo) {
            pilhaCopia.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        return pilhaCopia;
    }

    public Pilha<E> copiarNormal() {
        Pilha<E> aux = new Pilha<>();
        Pilha<E> pilha = new Pilha<>();
        Celula<E> atual = this.topo;
        while (atual != fundo) {
            aux.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        atual = aux.topo;
        while (atual != aux.fundo) {
            pilha.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        return pilha;
    }

    public boolean contem(E item) {
        if (vazia()) {
            throw new NoSuchElementException("Nenhum item consultado!");
        }
        Celula<E> atual = this.topo;
        while (atual != fundo) {
            if (item.equals(atual.getItem())) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    public void remover(E item) {
        if (vazia()) {
            throw new NoSuchElementException("Nenhum item consultado!");
        }
        Celula<E> atual = this.topo;
        Celula<E> anterior = this.fundo;
        while (atual != fundo) {
            if (item.equals(atual.getItem())) {
                if (atual == topo) {
                    topo = topo.getProximo();
                }
                anterior.setProximo(atual.getProximo());
                return;
            }
            anterior = atual;
            atual = atual.getProximo();
        }
    }

    public Pilha<E> alternarordemitemstatico(E item) {
        if (vazia()) {
            throw new NoSuchElementException("Nenhum item consultado!");
        }
        Pilha<E> auxesq = new Pilha<>();
        Pilha<E> auxdir = new Pilha<>();
        Celula<E> atual = this.topo;
        while (atual != fundo) {
            if (item.equals(atual.getItem())) {
                break;
            }
            auxesq.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        atual = atual.getProximo();
        while (atual != fundo) {
            auxdir.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        Pilha<E> auxesqinv = new Pilha<>();
        Pilha<E> auxdirinv = new Pilha<>();

        atual = auxesq.topo;
        while (atual != auxesq.fundo) {
            auxesqinv.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        atual = auxdir.topo;
        while (atual != auxdir.fundo) {
            auxdirinv.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        Pilha<E> result = new Pilha<E>();
        atual = auxesqinv.topo;
        while (atual != auxesqinv.fundo) {
            result.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        result.empilhar(item);
        atual = auxdirinv.topo;
        while (atual != auxdirinv.fundo) {
            result.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        return result;
    }

    public Pilha<E> ordenar() {
        Pilha<E> resultado = new Pilha<>();
        int tamanho = this.obterNumeroItens();

        for (int i = 0; i < tamanho; i++) {
            Celula<E> atual = this.topo;
            E menor = atual.getItem();
            while (atual != fundo) {
                atual = atual.getProximo();
            }
        }
        return resultado;
    }

    // CORRIGIDO: cast para Integer para comparar com >
    public void removerMenor() {
        Pilha<E> aux = new Pilha<>();
        Celula<E> atual = this.topo;
        E menor = atual.getItem();
        while (atual != this.fundo) {
            if (atual.getProximo() != fundo) {
                if ((Integer) menor > (Integer) atual.getProximo().getItem()) {
                    menor = atual.getProximo().getItem();
                }
            }
            aux.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        Pilha<E> auxx = new Pilha<>();
        atual = aux.topo;
        while (atual != aux.fundo) {
            auxx.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        auxx.remover(menor);
    }

    // CORRIGIDO: cast para Integer para comparar com >
    public int encontrarMaior() {
        Celula<E> atual = this.topo;
        int maior = (Integer) atual.getItem();
        while (atual != fundo) {
            if ((Integer) atual.getItem() > maior) {
                maior = (Integer) atual.getItem();
            }
            atual = atual.getProximo();
        }
        return maior;
    }

    public int encontrarMaioroutro() {
        Celula<E> atual = this.topo;
        int maior = (Integer) atual.getItem();
        while (atual != fundo) {
            if ((Integer) atual.getItem() > maior) {
                maior = (Integer) atual.getItem();
            }
            atual = atual.getProximo();
        }
        return maior;
    }

    // CORRIGIDO: cast para Integer + return adicionado
    public int contarElemento(int valorBuscado) {
        Celula<E> atual = this.topo;
        int contador = 0;
        while (atual != fundo) {
            if ((Integer) atual.getItem() == valorBuscado) {
                contador++;
            }
            atual = atual.getProximo();
        }
        return contador; // CORRIGIDO: faltava o return
    }

    public void removerFundo() {
        Celula<E> atual = this.topo;
        Pilha<E> aux = new Pilha<>();
        Pilha<E> resposta = new Pilha<>();
        while (atual != fundo) {
            aux.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        atual = aux.topo;
        aux.remover(atual.getItem());

        atual = aux.topo;
        while (atual != aux.fundo) {
            resposta.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
    }

    public void removerTodos(E itemBuscado) {
        Celula<E> atual = this.topo;
        Pilha<E> aux = new Pilha<>();
        while (atual != fundo) {
            aux.empilhar(atual.getItem());
            if (atual.getItem().equals(itemBuscado)) {
                aux.remover(atual.getItem());
            }
            atual = atual.getProximo();
        }
        Pilha<E> pilha = new Pilha<>();
        atual = aux.topo;
        while (atual != aux.fundo) {
            pilha.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
    }

    public void removerTodosGemini(E itemBuscado) {
        if (vazia()) {
            return;
        }
        Pilha<E> aux = new Pilha<>();
        while (!this.vazia()) {
            E itemAtual = this.desempilhar();
            if (!itemAtual.equals(itemBuscado)) {
                aux.empilhar(itemAtual);
            }
        }
        while (!aux.vazia()) {
            this.empilhar(aux.desempilhar());
        }
    }

    public boolean ehIgual(Pilha<E> outraPilha) {
        Celula<E> c2 = outraPilha.topo;
        Celula<E> c1 = this.topo;
        while (c1 != this.fundo && c2 != outraPilha.fundo) {
            if (!c1.getItem().equals(c2.getItem())) {
                return false;
            }
            c1 = c1.getProximo();
            c2 = c2.getProximo();
        }
        return (c1 == this.fundo && c2 == outraPilha.fundo);
    }

    // CORRIGIDO: cast para Integer para usar %
    public void separarParesEImpares() {
        Celula<E> atual = this.topo;
        Pilha<E> impar = new Pilha<>();
        Pilha<E> par = new Pilha<>();
        while (atual != fundo) {
            if ((Integer) atual.getItem() % 2 == 0) {
                par.empilhar(atual.getItem());
            } else {
                impar.empilhar(atual.getItem());
            }
            atual = atual.getProximo();
        }
    }

    public void inverterBase(int k) {
        int tamanho = 0;
        if (vazia() || k <= 1) {
            throw new RuntimeException("Vazia");
        }

        Pilha<E> aux1 = new Pilha<>();
        Pilha<E> aux2 = new Pilha<>();
        Pilha<E> aux = new Pilha<>();

        Celula<E> atual = this.topo;
        while (atual != fundo) {
            tamanho++;
            atual = atual.getProximo();
        }

        if (k > tamanho) k = tamanho;
        int elementosDoTopo = tamanho - k;

        atual = this.topo;
        int contador = 0;
        while (contador < elementosDoTopo) {
            aux.empilhar(atual.getItem());
            atual = atual.getProximo();
            contador++;
        }

        while (atual != fundo) {
            aux1.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        this.topo = fundo;

        atual = aux1.topo;
        while (atual != aux1.fundo) {
            aux2.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        atual = aux2.topo;
        while (atual != aux2.fundo) {
            this.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        atual = aux.topo;
        while (atual != aux.fundo) {
            this.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
    }

    public void rotacionarFundo(int n) {
        Pilha<E> aux = new Pilha<>();
        Pilha<E> aux1 = new Pilha<>();
        while (!this.vazia()) {
            aux.empilhar(this.desempilhar());
        }
        for (int i = 0; i < n; i++) {
            aux1.empilhar(aux.desempilhar());
        }
        while (!aux.vazia()) {
            this.empilhar(aux.desempilhar());
        }
        while (!aux1.vazia()) {
            this.empilhar(aux1.desempilhar());
        }
    }

    public void rotacionarTopo(int n) {
        Pilha<E> aux1 = new Pilha<>();
        for (int i = 0; i < n; i++) {
            aux1.empilhar(this.desempilhar());
        }
        Pilha<E> aux2 = new Pilha<>();
        while (!this.vazia()) {
            aux2.empilhar(this.desempilhar());
        }
        while (!aux2.vazia()) {
            this.empilhar(aux2.desempilhar());
        }
        while (!aux1.vazia()) {
            this.empilhar(aux1.desempilhar());
        }
    }

    // CORRIGIDO: loop usava this.vazia() mas deveria avançar o ponteiro
    public int contarElemento(E itemProcurado) {
        Celula<E> aux = this.topo;
        int contador = 0;
        while (aux != fundo) {
            if (aux.getItem().equals(itemProcurado)) {
                contador++;
            }
            aux = aux.getProximo();
        }
        return contador;
    }

    public void intercalar(Pilha<E> outraPilha) {
        Pilha<E> aux1 = new Pilha<>();
        Pilha<E> aux2 = new Pilha<>();
        Pilha<E> aux3 = new Pilha<>();
        while (!this.vazia()) {
            aux1.empilhar(this.desempilhar());
        }
        while (!outraPilha.vazia()) {
            aux2.empilhar(outraPilha.desempilhar());
        }
        while (!aux1.vazia() || !aux2.vazia()) {
            if (!aux1.vazia()) {
                aux3.empilhar(aux1.desempilhar());
            }
            if (!aux2.vazia()) {
                aux3.empilhar(aux2.desempilhar());
            }
        }
    }
}