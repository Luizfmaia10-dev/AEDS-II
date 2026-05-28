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
        if(vazia()){
            return somaItem;
        }
        while (atual != fundo) {
            somaItem += (Integer) atual.getItem();
            atual = atual.getProximo();
        }
        return somaItem;
    }
    public Pilha<E> concatenar (Pilha<E> pilha){
        Pilha<E> pilhaconcatenada= new Pilha<>();
        if(vazia()){
            return pilha;
        }

        Pilha<E> aux1 = new Pilha<>();
        Celula<E> atual=pilha.topo;
        Pilha<E> aux2 = new Pilha<>();

        while(atual!=pilha.fundo){
            aux1.empilhar(atual.getItem());
            atual=atual.getProximo();
            //proximo da pilha recebida
            //aux1 vai ser a pilha recebida invertida

        }

        atual = this.topo; // ✅ reseta para o topo da pilha ORIGINAL
        while(atual!=this.fundo){
            aux2.empilhar(atual.getItem());
            atual=atual.getProximo();
            //aux2 vai ser a pilha original invertida

        }
        //Duas celulas começando do topo para percorrer as pílhas aux1 e aux2
        Celula<E> c1 = aux1.topo; // ✅ Celula para percorrer aux1
        Celula<E> c2 = aux2.topo; // ✅ Celula para percorrer aux2

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
    public int obterNumeroItens(){
        int numItems = 0;
        Celula<E> atual = topo;
        while (atual!=fundo){
            numItems++;
            atual = atual.getProximo();
        }
        return numItems;
    }
    public void inverter(){
        Celula<E> atual = topo;
        Pilha<E> aux1 = new Pilha<>();
        while (atual != fundo) {
            aux1.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
    }
    public boolean isPalindromo(String palindromo){
        Pilha<Character> pilha = new Pilha<>();

        // Limpa a String
        String limpa = palindromo.toLowerCase().replaceAll("[^a-z]", "");

        // Empilha cada letra
        for (int i = 0; i < limpa.length(); i++) {
            pilha.empilhar(limpa.charAt(i));
        }
        for (int i = 0; i < limpa.length(); i++) {
            char letraDoInicio = limpa.charAt(i);        // letra da frente
            char letraDoFim = (char) pilha.desempilhar(); // letra de trás
            if (letraDoInicio != letraDoFim) {
                return false; // letras diferentes → não é palíndromo
            }
        }
        return true;
    }
    public Pilha<E> colocarPilhaEmCima(Pilha<E> pilha) {
        Pilha<E> resultado = new Pilha<>();
        Pilha<E> aux1 = new Pilha<>();
        Pilha<E> aux2 = new Pilha<>();

        // Inverte a pilha original em aux1

        Celula<E> atual = this.topo;
        while (atual != this.fundo) {
            aux1.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        // Inverte a pilha recebida em aux2

        atual = pilha.topo;
        while (atual != pilha.fundo) {
            aux2.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        // Empilha aux1 no resultado (original na ordem certa)
        atual = aux1.topo;
        while (atual != aux1.fundo) {
            resultado.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        // Empilha aux2 por cima (recebida na ordem certa)
        atual = aux2.topo;
        while (atual != aux2.fundo) {
            resultado.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        return resultado;
    }
    public void imprimir(){
        Celula<E> atual = topo;
        while (atual!=fundo){
            System.out.print(atual.getItem());
            atual = atual.getProximo();
        }
    }
    public Pilha<E> copiar(){
        Pilha<E> pilha = new Pilha<>();
        Celula<E> atual = this.topo;
        while (atual!=fundo){
            pilha.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        return pilha;
    }
    public Pilha<E> copiarNormal(){
        //Vmaos copiar a pilha sem inverter ela,no outro o resultado ficou invertido
        //criando uma aux
        Pilha<E> aux = new Pilha<>();
        //Criando a nova pilha
        Pilha<E> pilha = new Pilha<>();
        //Criando a celula
        Celula<E> atual = this.topo;
        //pasando todos os itens da celula original pra aux
        //mas passamos ao contrario,por se tratar de um pilha
        //ex:atual=[1,2,3,4] aux=[4,3,2,1]
        while (atual!=fundo){
            aux.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        //atualizamos a referencia da celula
        atual= aux.topo;
        //agora que a pilha ja esta invertida basta colocar novamente em uma nova,seria a inversa da inversa
        //ex:aux=[4,3,2,1] pilha=[1,2,3,4]
        //atualizamos tambem a referencia do fundo no while
        while (atual!=aux.fundo){
            pilha.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        return pilha;
    }
    public boolean contem(E item){
        if(vazia()){
            throw new NoSuchElementException("Nenhum item consultado!");
        }
        Celula<E> atual = this.topo;
        while (atual!=fundo){
            if(item.equals(atual.getItem())){
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }
    public void remover(E item){
        if(vazia()){
            throw new NoSuchElementException("Nenhum item consultado!");
        }
        Celula<E> atual = this.topo;
        Celula<E> anterior = this.fundo;
        while (atual!=fundo){
            if(item.equals(atual.getItem())){
                if(atual==topo){
                    //caso for igual ao topo a referencia de topo tem que ser atualizada para o proximo
                    topo =  topo.getProximo();
                }
                //estou atualizando a referencia do anterior.proximo caso o atual for diferente de topo
                //e como se o atual fosse o item,o anterior fosse o anterior dele e o atual.getproximo fosse o proximo do item
                //e como se estivesse falando para o anterior do item que o seu proximo vai ser o item da proxima celula do item
                //pilha: [X3] ──► [X2] ──► [X1] ──► sentinela

                //anterior = sentinela
                //atual    = X3

                //anterior = X3    ← recebeu o atual
                //atual    = X2    ← avançou

                //anterior = X3
                //atual    = X2
                //atual.getProximo() = X1
                //
                //anterior.setProximo(atual.getProximo())
                //X3.proximo = X1 ✅

                //[X3] ──► [X1] ──► sentinela
                //X2 foi removido!


                anterior.setProximo(atual.getProximo());
                return;

            }
            //nao posso deixar anterior=anterior.proximo pois ele começa do fundo,nao tem proximo
            anterior = atual;               // anterior COPIA a posição do atual
            atual = atual.getProximo();     // só o atual avança
        }
    }
    public Pilha<E> copiarr(){
        Pilha<E> pilha = new Pilha<>();
        Pilha<E> aux = new Pilha<>();
        Celula<E> atual = this.topo;
        while (atual!=fundo){
            aux.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        atual = aux.topo;
        while (atual!=aux.fundo){
            pilha.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        return pilha;
    }
    public Pilha<E> alternarordemitemstatico(E item){
        int dir=0;
        if(vazia()){
            throw new NoSuchElementException("Nenhum item consultado!");
        }
        Pilha<E> auxesq = new Pilha<>();
        Pilha<E> auxdir = new Pilha<>();
        Celula<E> atual = this.topo;
        while (atual!=fundo){
            if(item.equals(atual.getItem())){
                break;
            }
            auxesq.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        atual=atual.getProximo();
        while (atual!=fundo){
            auxdir.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        Pilha<E> auxesqinv = new Pilha<>();
        Pilha<E> auxdirinv = new Pilha<>();

        atual = auxesq.topo;
        while (atual!=auxesq.fundo){
            auxesqinv.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        atual = auxdir.topo;
        while (atual!=auxdir.fundo){
            auxdirinv.empilhar(atual.getItem());
            atual = atual.getProximo();
        }


        Pilha<E> result = new Pilha<E>();
        atual = auxesqinv.topo;
        while (atual!=auxesqinv.fundo){
            result.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        result.empilhar(item);
        atual = auxdirinv.topo;
        while (atual!=auxdirinv.fundo){
            result.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        return result;

    }
    public Pilha<E> ordenar() {
        Pilha<E> resultado = new Pilha<>();
        int tamanho = this.obterNumeroItens();

        for (int i = 0; i < tamanho; i++) {

            // Passo 1 — achar o menor elemento
            Celula<E> atual = this.topo;
            E menor = (E) atual.getItem();
            while (atual != fundo) {
                // como comparar para saber se é menor?
                // dica: Comparable!
                atual = atual.getProximo();
            }

            // Passo 2 — remover o menor da pilha original
            // você já tem esse método!

            // Passo 3 — empilhar o menor no resultado

        }
        return resultado;
    }
    public void removerMenor(){
        Pilha<E> aux = new Pilha<>();
        Celula<E> atual = this.topo;
        E menor = (E) atual.getItem();
        while (atual!=this.fundo){
            if(menor>atual.getProximo().getItem()){
                menor = atual.getProximo().getItem();
            }
            aux.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        Pilha<E> auxx= new Pilha<>();
        atual = aux.topo;
        while(atual!=aux.fundo){
            auxx.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        auxx.remover(menor);
    }
    public int encontrarMaior() {
        Celula<E> atual = this.topo;
        int maior = atual.getItem();
        Celula<E> anterior = this.fundo;
        while (atual!=fundo){
            if(anterior.getItem()>atual.getItem()){
                maior=anterior.getItem();
            }else{
                maior=atual.getItem();
            }
            atual=anterior;
            atual = atual.getProximo();
        }
        return maior;
    }
    public int encontrarMaioroutro() {
        Celula<E> atual = this.topo;
        int maior = atual.getItem();
        while (atual!=fundo){
            if(atual.getItem()>maior){
                maior=atual.getItem();
            }
            atual = atual.getProximo();
        }
        return maior;
    }
    public int contarElemento(int valorBuscado) {
        Celula<E> atual = this.topo;
        int contador = 0;
        while (atual!=fundo){
            if(atual.getItem()==valorBuscado){
                contador++;
            }
            atual = atual.getProximo();
        }
    }
    public void removerFundo(){
        Celula<E> atual = this.topo;
        Pilha<E> aux = new Pilha<>();
        Pilha<E> resposta = new Pilha<>();
        while (atual!=fundo){
            aux.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        atual=aux.topo;
        aux.remover(atual.getItem());

        atual=aux.topo;
        while (atual!=aux.fundo){
            resposta.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
    }
    public void removerTodos(E itemBuscado) {
        Celula<E> atual = this.topo;
        Pilha<E> pilha = new Pilha<>();
        Pilha<E> aux = new Pilha<>();
        while (atual!=fundo){
            aux.empilhar(atual.getItem());
            if(atual.getItem()==itemBuscado){
               aux.remover(atual.getItem());
            }
            atual = atual.getProximo();
        }
        atual=pilha.topo;
        while(atual!=pilha.fundo){
            pilha.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
    }
        public void removerTodosGemini(E itemBuscado) {
            if (vazia()) {
                return; // Se a pilha estiver vazia, não há o que remover
            }

            Pilha<E> aux = new Pilha<>();

            // 1. Esvaziamos a pilha original retirando os elementos um por um
            while (!this.vazia()) {
                E itemAtual = this.desempilhar(); // Remove e nos dá o elemento do topo

                // Usamos .equals() porque estamos trabalhando com objetos genéricos (E)
                if (!itemAtual.equals(itemBuscado)) {
                    // Se NÃO for o item que queremos deletar, salvamos na pilha auxiliar
                    aux.empilhar(itemAtual);
                }
                // Se for IGUAL, o 'if' ignora e o item simplesmente some da memória!
            }

            // 2. Agora que a pilha original está vazia e a 'aux' tem apenas os sobreviventes,
            // nós devolvemos todos eles para a pilha original.
            // Como desempilhar de 'aux' e empilhar em 'this' inverte de novo, a ordem original se mantém!
            while (!aux.vazia()) {
                this.empilhar(aux.desempilhar());
            }
        }
        public Pilha<E> copiar() {
        Pilha<E> aux = new Pilha<>();
        Celula<E> atual = this.topo;
        while (atual!=fundo){
            aux.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        Pilha<E> pilhaCopia = new Pilha<>();
        atual=aux.topo;
        while (atual!=aux.fundo){
            pilhaCopia.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
        return pilhaCopia;
        }
        public boolean ehIgual(Pilha<E> outraPilha) {
        Celula<E> c2 = outraPilha.topo;
        Celula<E> c1 = this.topo;
        while (c1!=this.fundo && c2!=outraPilha.fundo){
            if(c1.getItem()!=c2.getItem()){
                return false;
            }
            c1 = c1.getProximo();
            c2= c2.getProximo();

        }
        return (c1==this.fundo && c2==outraPilha.fundo);

        }
    public void separarParesEImpares() {
        Celula<E> atual = this.topo;
        Pilha <E> impar = new Pilha<>();
        Pilha <E> par = new Pilha<>();
        while (atual!=fundo){
            if(atual.getItem()%2==0){
                par.empilhar(atual.getItem());
            }else{
                impar.empilhar(atual.getItem());
            }
            atual=atual.getProximo();
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

        // 1. CONTA O TAMANHO (Igual ao seu)
        Celula<E> atual = this.topo;
        while (atual != fundo) {
            tamanho++;
            atual = atual.getProximo();
        }

        if (k > tamanho) k = tamanho;
        int elementosDoTopo = tamanho - k;

        // 2. ISOLA O TOPO EM 'AUX'
        // Caminhamos com o ponteiro 'atual' redefinido para o topo
        atual = this.topo;
        int contador = 0;
        while (contador < elementosDoTopo) {
            aux.empilhar(atual.getItem());
            atual = atual.getProximo();
            contador++;
        }

        // 3. COPIA O RESTO (A BASE) PARA 'AUX1'
        // O ponteiro 'atual' já parou exatamente onde a base começa!
        while (atual != fundo) {
            aux1.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        // 4. ESVAZIA A PILHA ORIGINAL (THIS)
        // Como vamos reconstruir a pilha original do zero, precisamos resetar o topo dela
        this.topo = fundo;

        // 5. DESINVERTE A BASE JOGANDO EM 'AUX2'
        atual = aux1.topo;
        while (atual != aux1.fundo) {
            aux2.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        // 6. DEVOLVE A BASE JÁ INVERTIDA PARA A PILHA ORIGINAL (THIS)
        atual = aux2.topo;
        while (atual != aux2.fundo) {
            this.empilhar(atual.getItem());
            atual = atual.getProximo();
        }

        // 7. DESINVERTE O TOPO JOGANDO DE VOLTA EM 'THIS'
        atual = aux.topo;
        while (atual != aux.fundo) {
            this.empilhar(atual.getItem());
            atual = atual.getProximo();
        }
    }
    public void rotacionarFundo(int n){
        Pilha <E> aux = new Pilha<>();
        Pilha <E> aux1 = new Pilha<>();
        while (!this.vazia()) {
            aux.empilhar(this.desempilhar()); // ✅ remove da original e coloca em aux
        }
        for(int i=0;i<n;i++){
            aux1.empilhar(aux.desempilhar());
        }
        while (!aux.vazia()) {
            this.empilhar(aux.desempilhar());
        }
        while (!aux1.vazia()){
            this.empilhar(aux1.desempilhar());
        }
    }
    public void rotacionarTopo(int n){
        Pilha <E> aux1 = new Pilha<>();
        for(int i=0;i<n;i++){
            aux1.empilhar(this.desempilhar());
        }
        Pilha <E> aux2 = new Pilha<>();
        while (!this.vazia()) {
            aux2.empilhar(this.desempilhar());
        }
        while (!aux2.vazia()){
            this.empilhar(aux2.desempilhar());
        }
        while (!aux1.vazia()){
            this.empilhar(aux1.desempilhar());
        }
    }
    public int contarElemento(E itemProcurado){
        Celula<E> aux = this.topo;
        int contador = 0;
        while(!this.vazia()){
            if(aux.getItem().equals(itemProcurado){
                contador++;
            }
            aux=aux.getProximo();
        }
        return contador;
    }
    public void intercalar(Pilha<E> outraPilha) {
        Celula<E> aux = this.topo;
        Pilha<E> aux1 = new Pilha<>();
        Pilha<E> aux2 = new Pilha<>();
        Pilha<E> aux3 = new Pilha<>();
        while(!this.vazia()){
            aux1.empilhar(this.desempilhar());
            aux=aux.getProximo();
        }
        while (!outraPilha.vazia()){
            aux2.empilhar(outraPilha.desempilhar());
        }
        while(!aux1.vazia() || !aux2.vazia()){
            if (!aux1.vazia()) {
                aux3.empilhar(aux1.desempilhar());
            }
            if (!aux2.vazia()) {
                aux3.empilhar(aux2.desempilhar());
            }
        }
    }

}
