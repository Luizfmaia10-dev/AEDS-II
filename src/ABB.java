import java.util.NoSuchElementException;

public class ABB<E extends Comparable<E>> {

    private No<E> raiz; // referência à raiz da árvore.

    /**
     * Construtor da classe.
     * Esse construtor cria uma nova árvore binária de busca vazia. Para isso, esse método atribui null à raiz da árvore.
     */
    public ABB() {
        raiz = null;
    }

    /**
     * Método booleano que indica se a árvore está vazia ou não.
     * @return
     * verdadeiro: se a raiz da árvore for null, o que significa que a árvore está vazia.
     * falso: se a raiz da árvore não for null, o que significa que a árvore não está vazia.
     */
    public Boolean vazia() {
        return (this.raiz == null);
    }

    private E pesquisar(No<E> raizArvore, E procurado) {

        int comparacao;

        if (raizArvore == null)
        // Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então o item não foi encontrado.
            throw new NoSuchElementException("O item não foi localizado na árvore!");

        comparacao = procurado.compareTo(raizArvore.getItem());

        //Funcao compareTo:
        //retorna < 0  → item é MENOR  que o nó RAIZ atual  → vai para esquerda
        //retorna = 0  → item é IGUAL  ao nó RAIZ atual     → O Item foi encontrado!
        //retorna > 0  → item é MAIOR  que o nó RAIZ atual  → vai para direita

        if (comparacao == 0)
        // O item procurado foi encontrado.
            return raizArvore.getItem();
        else if (comparacao < 0)
        // Se o item procurado for menor do que o item armazenado na raiz da árvore:
        // pesquise esse item na sub-árvore esquerda.
            return pesquisar(raizArvore.getEsquerda(), procurado);
        else
        // Se o item procurado for maior do que o item armazenado na raiz da árvore:
        // pesquise esse item na sub-árvore direita.
            return pesquisar(raizArvore.getDireita(), procurado);
    }

    public E pesquisar(E procurado) {
        return pesquisar(this.raiz, procurado);
    }

    /**
     * Método recursivo responsável por adicionar um item à árvore.
     * @param raizArvore: raiz da árvore ou sub-árvore em que o item será adicionado.
     * @param item: item que deverá ser adicionado à árvore.
     * @return a raiz atualizada da árvore ou sub-árvore em que o item foi adicionado.
     */
    protected No<E> adicionar(No<E> raizArvore, E item) {

        int comparacao;

        //Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então um novo item é inserido.
        if (raizArvore == null)
            raizArvore = new No<>(item);
        else {
            comparacao = item.compareTo(raizArvore.getItem());

            //Funcao compareTo:
            //retorna < 0  → item é MENOR  que o nó atual  → vai para esquerda
            //retorna = 0  → item é IGUAL  ao nó atual     → lança exceção
            //retorna > 0  → item é MAIOR  que o nó atual  → vai para direita

            if (comparacao < 0)
            //Item é menor que o nó atual → desce pela esquerda recursivamente. O setEsquerda atualiza o ponteiro com o retorno da recursão.
            // Se o item que deverá ser inserido na árvore for menor do que o item armazenado na raiz da árvore:
            // adicione esse novo item à sub-árvore esquerda; e atualize a referência para a sub-árvore esquerda modificada.
                raizArvore.setEsquerda(adicionar(raizArvore.getEsquerda(), item));
            else if (comparacao > 0)
            //Item é maior que o nó atual → desce pela direita recursivamente. O setDireita atualiza o ponteiro com o retorno da recursão.
            // Se o item que deverá ser inserido na árvore for maior do que o item armazenado na raiz da árvore:
            // adicione esse novo item à sub-árvore direita; e atualize a referência para a sub-árvore direita modificada.
                raizArvore.setDireita(adicionar(raizArvore.getDireita(), item));
            else
            // significa que o item já existe na árvore. ABB não permite duplicatas, então lança exceção.
            // O item armazenado na raiz da árvore é igual ao novo item que deveria ser inserido na árvore.
                throw new RuntimeException("O item já foi inserido anteriormente na árvore.");
        }

        // Retorna a raiz atualizada da árvore ou sub-árvore em que o item foi adicionado.
        return raizArvore;

        //Inserindo 3 na árvore:
        //      10
        //     /
        //    5


        //1º adicionar(nó10, 3)
        //│   3 < 10 → setEsquerda(adicionar(nó5, 3))
        //│
        //└─► 2º adicionar(nó5, 3)
        //    │   3 < 5 → setEsquerda(adicionar(null, 3))
        //    │
        //    └─► 3º adicionar(null, 3)
        //            cria nó3
        //            return nó3

        //                        ▲
        //                        │
        //    nó5.setEsquerda(nó3)│  ← conecta nó3 ao nó5
        //    return nó5          ▲
        //                        │
        //nó10.setEsquerda(nó5)   │  ← confirma que nó5 ainda é filho de nó10
        //return nó10


        //
        //Resultado:
        //      10
        //     /
        //    5
        //   /
        //  3
    }

    /**
     * Método que encapsula a adição recursiva de itens à árvore.
     * @param item: item que deverá ser inserido na árvore.
     */
    public void adicionar(E item) {
        // Chama o método recursivo "adicionar", que será responsável por adicionar, o item passado como parâmetro, à árvore.
        // O método "adicionar" recursivo receberá, como primeiro parâmetro, a raiz atual da árvore; e, como segundo parâmetro,
        // o item que deverá ser adicionado à árvore.
        // Por fim, a raiz atual da árvore é atualizada, com a raiz retornada pelo método "adicionar" recursivo.
        this.raiz = adicionar(this.raiz, item);
    }

    public void caminhamentoEmOrdem() {

        if (vazia())
            throw new IllegalStateException("A árvore está vazia!");

        caminhamentoEmOrdem(this.raiz);
    }

    private void caminhamentoEmOrdem(No<E> raizArvore) {
        if (raizArvore != null) {
            caminhamentoEmOrdem(raizArvore.getEsquerda());
            System.out.println(raizArvore.getItem());
            caminhamentoEmOrdem(raizArvore.getDireita());
        }
    }

    /**
     * Método recursivo responsável por localizar na árvore ou sub-árvore o antecessor do nó que deverá ser retirado.
     * O antecessor do nó que deverá ser retirado da árvore corresponde
     * ao nó que armazena o item que é o maior,
     * dentre os itens menores do que o item que deverá ser retirado.
     * Depois de ser localizado na árvore ou sub-árvore,
     * o antecessor do nó que deverá ser retirado da árvore o substitui.
     * Adicionalmente, a árvore ou sub-árvore é atualizada com a remoção do antecessor.
     * @param itemRetirar: referência ao nó que armazena o item que deverá ser retirado da árvore.
     * @param raizArvore: raiz da árvore ou sub-árvore em que o antecessor do nó que deverá ser retirado deverá ser localizado.
     * @return a raiz atualizada da árvore ou sub-árvore após a remoção do antecessor do nó que foi retirado da árvore.
     */
    protected No<E> removerNoAntecessor(No<E> itemRetirar, No<E> raizArvore) {
        // Se o antecessor do nó que deverá ser retirado da árvore ainda não foi encontrado...
        if (raizArvore.getDireita() != null)
        // Pesquise o antecessor na sub-árvore direita.
            raizArvore.setDireita(removerNoAntecessor(itemRetirar, raizArvore.getDireita()));
        else {
            // O antecessor do nó que deverá ser retirado da árvore foi encontrado e deverá substitui-lo.
            itemRetirar.setItem(raizArvore.getItem());
            // A raiz da árvore ou sub-árvore é atualizada com os descendentes à esquerda do antecessor.
            // Ou seja, retira-se o antecessor da árvore.
            raizArvore = raizArvore.getEsquerda();
        }
        return raizArvore;
    }

    /**
     * Método recursivo responsável por localizar um item na árvore e retirá-lo da árvore.
     * @param raizArvore: raiz da árvore ou sub-árvore da qual o item será retirado.
     * @param itemRemover: item que deverá ser localizado e removido da árvore.
     * @return a raiz atualizada da árvore ou sub-árvore da qual o item foi retirado.
     */
    protected No<E> remover(No<E> raizArvore, E itemRemover) {

        int comparacao;

        // Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e o item, que deveria ser retirado dessa árvore, não foi encontrado.
        // Nesse caso, deve-se lançar uma exceção.
        if (raizArvore == null)
            throw new NoSuchElementException("O item a ser removido não foi localizado na árvore!");

        comparacao = itemRemover.compareTo(raizArvore.getItem());

        if (comparacao == 0) {
            //O item armazenado na raiz da árvore corresponde ao item que deve ser retirado dessa árvore.
            // Ou seja, o item que deve ser retirado da árvore foi encontrado.
            if (raizArvore.getDireita() == null)
            // O nó da árvore que será retirado não possui descendentes à direita.
            // Nesse caso, os descendentes à esquerda do nó que está sendo retirado da árvore passarão a ser descendentes do nó-pai do nó que está sendo retirado.
                raizArvore = raizArvore.getEsquerda();
            else if (raizArvore.getEsquerda() == null)
            // O nó da árvore que será retirado não possui descendentes à esquerda.
            // Nesse caso, os descendentes à direita do nó que está sendo retirado da árvore passarão a ser descendentes do nó-pai do nó que está sendo retirado.
                raizArvore = raizArvore.getDireita();
            else
            //O nó que está sendo retirado da árvore possui descendentes à esquerda e à direita.
            //Nesse caso, o antecessor do nó que está sendo retirado é localizado na sub-árvore esquerda desse nó.
            //O antecessor do nó que está sendo retirado da árvore corresponde
            //ao nó que armazena o item que é o maior,
            //dentre os itens menores do que o item do nó que está sendo retirado.
            //Depois de ser localizado na sub-árvore esquerda do nó que está sendo retirado,
            //o antecessor desse nó o substitui.
            //A sub-árvore esquerda do nó que foi retirado é atualizada com a remoção do antecessor.
                raizArvore.setEsquerda(removerNoAntecessor(raizArvore, raizArvore.getEsquerda()));
        } else if (comparacao < 0)
        // Se o item que deverá ser localizado e retirado da árvore for menor do que o item armazenado na raiz da árvore:
        // pesquise e retire esse item da sub-árvore esquerda.
            raizArvore.setEsquerda(remover(raizArvore.getEsquerda(), itemRemover));
        else
        // Se o item que deverá ser localizado e retirado da árvore for maior do que o item armazenado na raiz da árvore:
        // pesquise e retire esse item da sub-árvore direita.
            raizArvore.setDireita(remover(raizArvore.getDireita(), itemRemover));

        // Retorna a raiz atualizada da árvore ou sub-árvore da qual o item foi retirado.
        return raizArvore;
    }

    /**
     * Método que encapsula a remoção recursiva de um item da árvore.
     * @param itemRemover: item que deverá ser localizado e removido da árvore.
     */
    public void remover(E itemRemover) {
        // Chama o método recursivo "remover", que será responsável por pesquisar o item passado como parâmetro na árvore e retirá-lo da árvore.
        // O método "remover" recursivo receberá, como primeiro parâmetro, a raiz atual da árvore;
        // e, como segundo parâmetro, o item que deverá ser localizado e retirado dessa árvore.
        // Por fim, a raiz atual da árvore é atualizada, com a raiz retornada pelo método "remover" recursivo.
        this.raiz = remover(this.raiz, itemRemover);
    }

    public int quantidadeDeNos(No<E> raizArvore) {
        if(vazia())
            return 0;
        else 
            return  1+quantidadeDeNos(raizArvore.getEsquerda())+quantidadeDeNos(raizArvore.getDireita());
    }
    public int quantidadeDeNosFolha(No<E> raizArvore) {
        //A recursão vai e volta então percorre todos os nos
        //Arvore vazia
        if(vazia())
            return 0;
        //caso base
        //else if de verificação de folha
        else if (raizArvore.getDireita()== null && raizArvore.getEsquerda() == null ) {
            //nao tem o pq colocar quantidadeDeNosFolha(raizArvore.getEsquerda())+quantidadeDeNosFolha(raizArvore.getDireita());
            //pq a chamada vai ser NULL,mas para eu entener melhor eu prefiro assim
            return 1+quantidadeDeNosFolha(raizArvore.getEsquerda())+quantidadeDeNosFolha(raizArvore.getDireita());
        }
        else {
            return quantidadeDeNosFolha(raizArvore.getEsquerda())+quantidadeDeNosFolha(raizArvore.getDireita());
        }
    }

    public int SomarItens(No<E> raizArvore){
        int soma = 0;
        // 1. Caso base: se o nó atual for nulo, a soma dele é 0
        if (raizArvore == null) {
            return 0;
        }
        soma += SomarItens(raizArvore.getEsquerda()); // Soma o lado esquerdo
        soma += (int) raizArvore.getItem();          // Soma o nó atual
        soma += SomarItens(raizArvore.getDireita());  // Soma o lado direito

        return soma;
    }
    public int ProdutoItens(No <E> raizArvore){
        int produto = 0;
        if (raizArvore == null) {
            return 0;
        }
        produto *= ProdutoItens(raizArvore.getEsquerda());
        produto *= (int) raizArvore.getItem();
        produto *=  ProdutoItens(raizArvore.getDireita());

        return produto;

    }
    public int SomarNOFolha(No <E> raizArvore){
        if (raizArvore == null) {
            return 0;
        }
        if(raizArvore.getDireita()== null &&  raizArvore.getEsquerda() == null){
            /*
            soma+=(int) raizArvore.getItem();
            return soma;
            Mesma coisa do return a baixo
            */
            return (int) raizArvore.getItem();
        }
        int soma =0;
        soma += SomarItens(raizArvore.getEsquerda());
        soma += SomarItens(raizArvore.getDireita());

        return soma;

    }

    ///Nós internos (Pais): Funcionam apenas como "carteiros"
    // Eles recebem as somas das folhas vindas de baixo e repassam para cima.

    ///Nós folhas: São as "fábricas".
    //São os únicos que realmente pegam o getItem() e enviam o número para os carteiros levarem.
    public int ProdutoNOFolha(No <E> raizArvore){
        if (raizArvore == null) {
            return 0;
        }
        if(raizArvore.getEsquerda()== null &&  raizArvore.getDireita() == null){
            return (int) raizArvore.getItem();
        }
        int produto =0;
        produto *= ProdutoItens(raizArvore.getEsquerda());
        produto *= ProdutoItens(raizArvore.getDireita());
        return produto;
    }



    public int somaNoFolhaPt2(No <E> raizArvore){
        if (raizArvore == null) {
            return 1;
        }
        if(raizArvore.getEsquerda()== null &&  raizArvore.getDireita() == null){
            return (int) raizArvore.getItem();
        }
        return somaNoFolhaPt2(raizArvore.getEsquerda())+ somaNoFolhaPt2(raizArvore.getDireita());
    }
    //        10
    //       /  \
    //      5    8
    //     / \    \
    //    2   3    4

    // folhas 2, 3, 4

    //Chamamos:ProdutoNOFolhaPt2(10) //começamos pela raiz da Arvore mesmo

    //Não é null.
    //
    //Não é folha.

    //Executa:
    //
    //ProdutoNOFolhaPt2(5)
    //*
    //ProdutoNOFolhaPt2(8)


    public int ProdutoNOFolhaPt2(No <E> raizArvore){
        if (raizArvore == null) {
            return 1;
        }
        //Verificando se é folha
        if(raizArvore.getEsquerda()== null &&  raizArvore.getDireita() == null){
            return (int) raizArvore.getItem();
        }
        return ProdutoNOFolhaPt2(raizArvore.getEsquerda()) * ProdutoNOFolhaPt2(raizArvore.getDireita());
    }
    public void caminhamentoPreOrdem(No <E> raizArvore){
        caminhamentoPreOrdem(raizArvore.getEsquerda());
        caminhamentoPreOrdem(raizArvore.getDireita());
        System.out.println(raizArvore.getItem());
    }
    public void caminhamentoPosOrdem(No <E> raizArvore){
        System.out.println(raizArvore.getItem());
        caminhamentoPosOrdem(raizArvore.getEsquerda());
        caminhamentoPosOrdem(raizArvore.getDireita());
    }
    public void caminhamentoDecrescente(No <E> raizArvore){
        caminhamentoDecrescente(raizArvore.getDireita());
        System.out.println(raizArvore.getItem());
        caminhamentoDecrescente(raizArvore.getEsquerda());
    }
    public E obterMenorValor(No <E> raizArvore){
        if (raizArvore == null) {
            return null;
        }
        if(raizArvore.getEsquerda()==null){
            return raizArvore.getItem();
        }
        raizArvore = raizArvore.getEsquerda();
        return obterMenorValor(raizArvore);
    }
    public E obterMaiorValor(No <E> raizArvore){
        if (raizArvore == null) {
            return null;
        }
        if(raizArvore.getDireita()==null){
            return raizArvore.getItem();
        }
        raizArvore = raizArvore.getDireita();
        return obterMaiorValor(raizArvore);
    }
    public ABB<E> clone(){
        ABB<E> clone = new ABB<>();
        clonar(this.raiz,clone);
        return clone;
    }
    public void clonar(No <E> raizArvore, ABB<E> clone){
        if (raizArvore == null) {
            return;
        }
        clone.adicionar(raizArvore.getItem());
        clonar(raizArvore.getEsquerda(), clone);
        clonar(raizArvore.getDireita(), clone);
    }
    //mplemente a função public ABB<E> obterSubconjuntoMaiores(E item),
    //capaz de criar e retornar um subconjunto da árvore binária de busca formado apenas pelos
    //elementos da ABB que são maiores ou iguais ao item passado como parâmetro para esse
    //método. A determinação dos elementos da árvore binária de busca que são maiores ou
    //iguais ao item informado como parâmetro para o método deve basear-se no(s) critério(s)
    //empregado(s) na implementação do método public int compareTo(E
    //outroItem) do item armazenado na ABB.
    //Se não for encontrado, na árvore binária de busca, nenhum elemento correspondente ao
    //passado como parâmetro para esse método, uma exceção deve ser lançada.
    //Sugestão: empregue os métodos clone, do nó e da árvore binária de busca,
    //implementados anteriormente.
    public ABB<E> obterSubconjuntoMaiores(E item){
        ABB<E> subconjunto = new ABB<>();
        subconjuntomaioreigual(this.raiz,subconjunto,item);
        if (subconjunto.vazia())
            throw new NoSuchElementException("Nenhum elemento maior ou igual encontrado!");
        return subconjunto;
    }
    public ABB<E> subconjuntomaioreigual(No <E> raizArvore,ABB <E> subconj,E item){
        if (raizArvore == null) {
            return subconj;
        }
        if(raizArvore.getItem()>=item){
            subconj.adicionar(raizArvore.getItem());
        }
        subconjuntomaioreigual(raizArvore.getEsquerda(),subconj,item);
        subconjuntomaioreigual(raizArvore.getDireita(),subconj,item);
    }
    public boolean ehRaiz(E item){
        if(this.raiz==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        if(this.raiz.getItem().equals(item)){
            return true;
        }
        return false;
    }
    //Implemente o método public E obterAntecessor(E item), capaz de recuperar
    //e retornar o maior elemento armazenado na árvore binária de busca que seja menor do que
    //o item informado como parâmetro para esse método. A determinação dos elementos da
    //árvore binária de busca que são menores ou iguais ao item passado como parâmetro para o
    //método deve basear-se no(s) critério(s) empregado(s) na implementação do método
    //public int compareTo(E outroItem) do item armazenado na ABB.
    //Se não for encontrado, na árvore binária de busca, nenhum elemento correspondente ao
    //passado como parâmetro para esse método, ou o item informado como parâmetro não
    //apresentar antecessor na árvore binária de busca, uma exceção deve ser lançada.

    // O ANTECESSOR de um nó é o maior elemento MENOR que ele
// Ou seja, o vizinho imediato à esquerda na sequência em ordem crescente
//
//        10
//       /  \
//      5    15
//     / \
//    3   8
//         \
//          9
//
// Sequência em ordem: 3 → 5 → 8 → 9 → 10 → 15
//                                    ↑
//                         antecessor do 10 = 9
//
// Caso 1 — nó TEM sub-árvore esquerda:
//   antecessor = nó mais à DIREITA da sub-árvore esquerda
//   ex: antecessor do 10 → desce para 5 → vai para 8 → vai para 9 → para (sem direita)
//
// Caso 2 — nó NÃO TEM sub-árvore esquerda:
//   antecessor = ancestral mais próximo pelo qual desceu à direita
//   ex: antecessor do 15 → 10 (pois 15 não tem sub-árvore esquerda)

    public E obterAntecessor(E item){
        if(this.raiz==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        E valor = null;
        valor = obterAnt(valor,this.raiz,item);
        if(valor == null)
            throw new NoSuchElementException("Antecessor não encontrado!");
        return valor;
    }
    public E obterAnt(E valor, No <E> raizArvore, E item){
        /// CASO BASE:Valor não encontrado
        if(raizArvore==null){
            return valor;
        }
        //Busca Binaria pela esquerda,ou seja o item e menor doq a raiz
        if(raizArvore.getItem()>item){
            return obterAnt(valor,raizArvore.getEsquerda(),item);
        }
        //Busca Binaria pela direita,ou seja o item e maior doq a raiz
        if(raizArvore.getItem()<item){
            //Caso 1 → nó TEM sub-árvore esquerda
            //  antecessor = mais à direita da sub-árvore esquerda
            //
            //Caso 2 → nó NÃO TEM sub-árvore esquerda
            //  antecessor = ancestral mais próximo pelo qual
            //               você desceu pela direita
            valor=raizArvore.getItem();
            return obterAnt(valor,raizArvore.getDireita(),item);
        }
        //Achamos o item então aplicamos o anteccessor nele
        //a funcao procurarvalor que vai procurar o antecessor mesmo,ate agr so foi pesquisa binaria
        //pelo antecesso ser o elemento mais a direit na subarvore da esquerda ja passamos a subarvore da esquerda ja que o codigo é recursivo
        if(raizArvore.getItem().equals(item)){
            valor=procuraValor(valor,raizArvore.getEsquerda(),item);
            return valor;
        }
    }
    public E procuraValor(E valor, No <E> raizArvore, E item){
        if(raizArvore==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        //vamos até a direita infinito(ate encontrar null)
        //pois queremos o filho mais a direita
        if(raizArvore.getDireita()!=null){
            return procuraValor(valor,raizArvore.getDireita(),item);
        }
        valor=raizArvore.getItem();
        return valor;
    }
    //        10
    //       /  \
    //      5    15
    //     / \
    //    3   8
    //         \
    //          9
    //
    //Antecessor do 10 = 9
    //(maior elemento menor que 10 → desce sub-árvore esquerda → mais à direita)
    /// ===============================================================================================
    /// ===============================================================================================
    /// ===============================================================================================
    ///
    ///
    // O SUCESSOR de um nó é o menor elemento MAIOR que ele
// Ou seja, o vizinho imediato à direita na sequência em ordem crescente
//
//        10
//       /  \
//      5    15
//           /
//          12
//           \
//            13
//
// Sequência em ordem: 5 → 10 → 12 → 13 → 15
//                          ↑
//                  sucessor do 10 = 12
//
// Caso 1 — nó TEM sub-árvore direita:
//   sucessor = nó mais à ESQUERDA da sub-árvore direita
//   ex: sucessor do 10 → desce para 15 → vai para 12 → para (sem esquerda)
//
// Caso 2 — nó NÃO TEM sub-árvore direita:
//   sucessor = ancestral mais próximo pelo qual desceu à esquerda
//   ex: sucessor do 13 → 15 (pois 13 não tem sub-árvore direita)
    public E obterSucessor(E item){
        if(this.raiz==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        E valor = null;
        valor = obterSuc(valor,this.raiz,item);
        if(valor == null){
            throw new NoSuchElementException("Sucessor nao encontrado!");
        }
        return valor;
    }
    public E obterSuc(E valor, No <E> raizArvore, E item){
        /// CASO BASE:Valor não encontrado
        if(raizArvore==null){
            return valor;
        }
        //Busca Binaria pela esquerda,ou seja o item e menor doq a raiz
        if(raizArvore.getItem()>item){
            return obterSuc(valor,raizArvore.getEsquerda(),item);
        }
        //Busca Binaria pela direita,ou seja o item e maior doq a raiz
        if(raizArvore.getItem()<item){
            //Caso 1 → nó TEM sub-árvore esquerda
            //  antecessor = mais à direita da sub-árvore esquerda
            //
            //Caso 2 → nó NÃO TEM sub-árvore esquerda
            //  antecessor = ancestral mais próximo pelo qual
            //               você desceu pela direita
            valor=raizArvore.getItem();
            return obterSuc(valor,raizArvore.getDireita(),item);
        }
        //Achamos o item então aplicamos o sucessor nele
        //a funcao procurarvalorsuc que vai procurar o sucessor mesmo,ate agr so foi pesquisa binaria
        //pelo sucessor ser o elemento mais a esquerda na subarvore da direita ja passamos a subarvore da direita ja que o codigo é recursivo
        if(raizArvore.getItem().equals(item)){
            valor=procuraValorSuc(valor,raizArvore.getDireita(),item);
            return valor;
        }
    }
    public E procuraValorSuc(E valor, No <E> raizArvore, E item){
        if(raizArvore==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        //vamos a esuqerda infinito ja que o sucessor sempre e o elemento mais a esauerda da sub arvore da direita
        if(raizArvore.getEsquerda()!=null){
            return obterSuc(valor,raizArvore.getEsquerda(),item);
        }
        valor=raizArvore.getItem();
        return valor;
    }
    //        10
    //       /  \
    //      5    15
    //           /
    //          12
    //           \
    //            13
    //
    //Sucessor do 10 = 12
    //(menor elemento maior que 10 → desce sub-árvore direita → mais à esquerda)

    public int obterAltura(){
        if(this.raiz==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        No <E> subarvoreesq=this.raiz.getEsquerda();
        No <E> subarvoredir=this.raiz.getDireita();
        return 1+Math.max(subarvoredir.getAltura(),subarvoreesq.getAltura());
    }
    public int obterNivel(No<E> raizArvore,E item){
        if(raizArvore==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        //vamos para direita
        if(raizArvore.getItem()<item){
            return 1+obterNivel(raizArvore.getDireita(),item);
        }
        if(raizArvore.getItem()>item){
            return 1+obterNivel(raizArvore.getEsquerda(),item);
        }
        if(raizArvore.getItem().equals(item)){
            return 0;
        }
    }
    public int obterGrau(E item){
        if(this.raiz==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        int grau=0;
        grau=obterGrauu(this.raiz,item);
        return grau;
    }
    public int obterGrauu(No<E> raizArvore, E item){
        if(raizArvore==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        if(raizArvore.getItem()<item){
            return obterGrauu(raizArvore.getDireita(),item);
        }
        if(raizArvore.getItem()>item){
            return obterGrauu(raizArvore.getEsquerda(),item);
        }
        //ele pode ter grau 0,1 ou 2
        if(raizArvore.getItem().equals(item)){
            //so tem filho esq
            if(raizArvore.getEsquerda()!=null && raizArvore.getDireita()==null){
                return 1;
            }
            //so tem filho dir
            if(raizArvore.getDireita()!=null && raizArvore.getEsquerda()==null){
                return 1;
            }
            //n tem filho nem na esq e nem na dir
            if(raizArvore.getEsquerda()==null && raizArvore.getDireita()==null){
                return 0;
            }
            //filho a esq e a dir
            if(raizArvore.getDireita()!=null && raizArvore.getEsquerda()!=null){
                return 2;
            }
        }
    }
    public boolean ehFolha(E item){
        if(this.raiz==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        return ehFolhaaux(this.raiz,item);
    }
    public boolean ehFolhaaux(No<E> raizArvore,E item){
        if(raizArvore.getItem()<item){
            return ehFolhaaux(raizArvore.getDireita(),item);

        }
        if(raizArvore.getItem()>item){
            return ehFolhaaux(raizArvore.getEsquerda(),item);

        }
        if(raizArvore.getItem().equals(item)){
            if(raizArvore.getEsquerda()==null && raizArvore.getDireita()==null){
                return true;
            }

        }
        return false;

    }
    // Um nó é ancestral de outro se ele está no caminho da raiz até esse nó
// Exemplo:
//
//        10          ← ancestral de 3, 5, 2, 4
//       /  \
//      5    15       ← 5 é ancestral de 3, 8, 2, 4
//     / \
//    3   8
//   / \
//  2   4             ← 2 e 4 não têm ancestrais além de 10, 5, 3
//
// Para verificar se X é ancestral de Y:
// basta achar X na árvore e verificar se Y está na sub-árvore de X
//
// Ancestrais do nó 2: 3 → 5 → 10 (do mais próximo ao mais distante)
// Ancestrais do nó 8: 5 → 10
// Ancestrais do nó 10: nenhum (é a raiz)
    public boolean ehAncestral(E item, E ancestral){
        if(this.raiz==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        if(ancestral==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        return ehAncestralAux(item,ancestral,this.raiz);

    }
    public boolean ehAncestralAux(E item, E ancestral,No <E> raizArvore){
        if(raizArvore==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        if(raizArvore.getItem()>ancestral){
            return ehAncestralAux(item,ancestral,raizArvore.getEsquerda());

        }
        if(raizArvore.getItem()<ancestral){
            return ehAncestralAux(item,ancestral,raizArvore.getDireita());
        }
        if(raizArvore.getItem().equals(ancestral)){
            return ehAncestralAux2(item,ancestral,raizArvore);
        }
        return false;
    }
    public boolean ehAncestralAux2(E item, E ancestral, No <E> raizArvore){
        if(raizArvore==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        if(ancestral==null){
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        }
        if(raizArvore.getItem().equals(item)){
            return true;
        }
        //o ancestral e inutil nesse funcão toda
        if(ehAncestralAux2(item,ancestral, raizArvore.getEsquerda()) == true){
            return true;
        }
        if(ehAncestralAux2(item,ancestral, raizArvore.getDireita()) == true){
            return true;
        }
        return false;

    }
    public int obterSoma(){
        if(vazia())
            throw new NoSuchElementException("Nenhum elemento encontrado!");
        int soma = 0;
        soma=obterSomaAUX(this.raiz);
        return soma;
    }
    public int obterSomaAUX(No<E> raizArvore){
        if(raizArvore==null)
            return 0;
        return raizArvore.getItem()+obterSomaAUX(raizArvore.getDireita()) + obterSomaAUX(raizArvore.getEsquerda());
    }
    public boolean contemSubarvore(ABB<E> outra){
        if(outra==null)
            return false;
        No <E> outraraiz=outra.raiz;
        return contemSubarvoreAUX(outra,this.raiz,outraraiz);
    }
    public boolean contemSubarvoreAUX(ABB<E> outra, No<E> raizArvore, No<E> raizArvore2){
        if(raizArvore2 == null)
            return true;
        if(raizArvore == null)
            return false;
        //significa que chegou no fim da árvore atual sem encontrar o elemento.

        if(raizArvore2.getItem().compareTo(raizArvore.getItem()) < 0)
            return contemSubarvoreAUX(outra, raizArvore.getEsquerda(), raizArvore2);
        if(raizArvore2.getItem().compareTo(raizArvore.getItem()) > 0)
            return contemSubarvoreAUX(outra, raizArvore.getDireita(), raizArvore2);
    }
    public int obterQuantidadeNiveis(){
        if(vazia())
            return 0;
        int quantidade = 0;
        quantidade=obterQuantidadeNiveisAUX(this.raiz);
        return quantidade;
    }
    public int obterQuantidadeNiveisAUX(No<E> raiz){
        if(raiz==null)
            return 0;
        if(raiz.getItem()==null)
            return 0;
        return 1+Math.max(obterQuantidadeNiveisAUX(raiz.getEsquerda()),obterQuantidadeNiveisAUX(raiz.getDireita()));
    }
    /// NAO PODEMOS USAR ISSO
    // return 1+obterQuantidadeNiveisAUX(raiz.getEsquerda())+obterQuantidadeNiveisAUX(raiz.getDireita());
    //Você está somando os níveis da esquerda e da direita, mas deveria pegar o maior entre os dois.
    //    10
    //   /  \
    //  5    15
    // /
    //3
    //esquerda do 10 → altura 2
    //direita do 10  → altura 1
    //Se você somar, dá 3 — mas o número de níveis é 3 apenas pelo lado esquerdo
    //vamos usar o math.max que é  método que pega o maior entre dois valores
    ///Uma árvore é completa quando todos os nós internos têm dois filhos e todas as folhas estão no mesmo nível.
    /// ================================================================
    ///Uma árvore é completa quando todos os nós internos têm dois filhos e todas as folhas estão no mesmo nível.
    //Completa ✅          Não completa ❌
    //        10                10
    //       /  \              /  \
    //      5    15           5    15
    //     / \   / \         /
    //    3   8 13  20      3
    public boolean ehCompleta(){
        if(vazia())
            return false;
        boolean statusfilho=false;
        boolean statusnivel=false;
        statusfilho=ehCompletaAUXFILHO(this.raiz);
        statusnivel=ehCompletaAUXNIVEL(this.raiz);
        return(statusfilho && statusNivel);

    }
    public boolean ehCompletaAUXFILHO(No<E> raiz){
        if(raiz==null)
            return true;
        if(raiz.getEsquerda()==null && raiz.getDireita()!=null){
            return false;
        }
        if(raiz.getEsquerda()!=null && raiz.getDireita()==null){
            return false;
        }
        if(raiz.getEsquerda()==null && raiz.getDireita()==null){
            return

        }
        return ehCompletaAUXFILHO(raiz.getEsquerda()) && ehCompletaAUXFILHO(raiz.getDireita());

    }
    public boolean ehCompletaAUXNIVEL(No<E> raiz){
        if(raiz.getEsquerda() == null && raiz.getDireita() == null){
            if()
        }

    }
    public int contarFolhas(No<E> raiz){
        if(raiz==null)
            return 0;
        if(raiz.getEsquerda()==null && raiz.getDireita()==null){
            return 1;
        }
        return contarFolhas(raiz.getEsquerda())+contarFolhas(raiz.getDireita());
    }
    public ABB<E> obterSubconjuntoMaiores(E item){
        ABB<E> subconjuntoMaiores = new ABB<>();
       return obterSubconjuntoMaioresAUX(item,this.raiz,subconjuntoMaiores);
    }
    public ABB<E> obterSubconjuntoMaioresAUX(E item, No<E> raiz,ABB<E> subconjuntoMaiores){
        if(raiz==null)
            return null;
        if(raiz.getItem()>item){
            subconjuntoMaiores.adicionar(raiz.getItem());
        }
        obterSubconjuntoMaioresAUX(item,raiz.getEsquerda(),subconjuntoMaiores);
        obterSubconjuntoMaioresAUX(item,raiz.getDireita(),subconjuntoMaiores);
        return subconjuntoMaiores;
    }
    //       40
    //      /  \
    //    20    50
    //   /  \
    //  10  [30]  <-- Se quisermos trocar o 30, o novo valor DEVE estar entre 20 e 40!
    //problema que pode ocorrer
    public void substituirItem(E itemAntigo, E itemNovo){
        if(itemNovo==null)
            return;
        if(itemNovo.equals(itemAntigo))
            throw new IllegalArgumentException("Elemento igual");
        if(substituirItemPROCURA(E itemAntigo,this.raiz)){
            //adicionamos o item nesse abb
        }
    }
    public boolean substituirItemPROCURA(E itemAntigo, No<E> raiz){
        if(itemAntigo==null)
            return;
        if(raiz.getItem().equals(itemAntigo)){
            raiz.setItem(null);
            return true;
        }
        if(itemAntigo>raiz.getItem()){
            substituirItemPROCURA(itemAntigo,raiz.getDireita());
        }
        if(itemAntigo<raiz.getItem()){
            substituirItemPROCURA(itemAntigo,raiz.getEsquerda());
        }


    }
    public int contarNosAncestrais(E item){
        if(item==null)
            throw new IllegalArgumentException("erro");

        if(item.equals(this.raiz))
            return 0;
        int valor=0;
        //podemos tambem procurar se esse no existe na arvore;
        valor=contarNosAncestraisAUX(item,this.raiz);
        return valor;
    }
    public int contarNosAncestraisAUX(E item, No<E> raiz){
        if(item==null)
            throw new IllegalArgumentException("item não encontrado");
        if(item>raiz.getItem())
            return 1+contarNosAncestraisAUX(item,raiz.getDireita());
        if(item<raiz.getItem()){
            return 1+contarNosAncestraisAUX(item,raiz.getEsquerda());
        }
    }
    public void removerFolhas(){
        removerFolhasAUX(this.raiz);
    }
    public void removerFolhasAUX(No<E> raiz){
        if(raiz==null)
            return;
        //esse primiero if eu verifico que eu entrei em um no valido
        if (raiz.getEsquerda() != null) {
            if (raiz.getEsquerda().getEsquerda() == null && raiz.getEsquerda().getDireita() == null) {
                raiz.setEsquerda(null); // Remove a folha esquerda com segurança!
            }
        }
        //esse primiero if eu verifico que eu entrei em um no valido
        if (raiz.getDireita() != null) {
            if (raiz.getDireita().getEsquerda() == null && raiz.getDireita().getDireita() == null) {
                raiz.setDireita(null); // Remove a folha direita com segurança!
            }
        }
        removerFolhasAUX(raiz.getEsquerda());
        removerFolhasAUX(raiz.getDireita());
    }

}