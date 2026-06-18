## Documentação Geral ABB
Vamos documentar todas as funcões aqui com imagems e explicações logicas para o codigo não ficar muito confuso

## Estrutura ABB

-*Arvore binaria de busca:*
-
é uma arvore onde cada no pode ter no entre 0 ou 2 filhos

os filhos do seus no são ditos por filhos a esquerda e filhos a direita.

os elementos da direita são menores que os elemntos da direita

Caso Médio: \(O(\log n)\) para busca, inserção e remoção. É extremamente eficiente para grandes volumes de dados.

Pior Caso: \(O(n)\) se a árvore se tornar "degenerada" (linear, parecendo uma lista encadeada), o que acontece quando os dados são inseridos já ordenados.

Classe ABB:Nela vc tem acesso a uma referencia para a Raiz da arvore

![img.png](img.png)

agora vamos para as prinicpais funcionalizades da classe ABB:

Criar uma arvore vazia

localizar um registro na arvore

inserir um novo registro na arvore

remover um registro na arvore

caminhar na arvore imprimindo os itens dos registros armazenados

--------------------

## Funcão vazia
public Boolean vazia() {
return (this.raiz == null);
}

retorna verdadeiro se a arvore estiver vazia e falso se estiver com alguma coisa

----------------

## Pesquisar
Por ser uma arvore binaria de busca o seu pesquisar e igual o da pesquisa binaria em que nos vamos descartando cadametade da arvore ao ir tomando conhecimento da arvore de maior em menor numero,utilizaremos recursão

Inicia-se a pesquisa pela raiz da arvore

Se a raiz atual for igual ao item procurado retornamos o item e concluimos que a pesquisa foi feita com sucesso

if (raizAtual.getItem == item)
// O item procurado foi encontrado.
return raizArvore.getItem();

--Caso contrario nos comparamos a raiz atual com o item procurado

-Se o item procurado for MAIOR que o numero da raiz,significa que temos que procurar na DIREITA,então seguimos a busca pela DIREITA e chamamos a função pesquisar so que passando o parametro ArvoreRaiz.getDireita(),ficaria assim:

else if (Item >raizAtual.getItem())
return pesquisar(raizArvore.getDireita(), procurado);

-Se o item procurado for MENOR que o numero da raiz,significa que temos que procurar na ESQUERDA,então seguimos a busca pela ESQUERDA,e chamamos a função pesquisar so que passando o parametro ArvoreRaiz.getEsquerda,ficaria assim:

else if (Item <raizAtual.getItem())
return pesquisar(raizArvore.getEsquerda(), procurado);

Caso o item não esteja na arvore nos vamos receber a referencia NULL e lançamos uma excessão

if (raizArvore == null)
// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então o item não foi encontrado.
throw new NoSuchElementException("O item não foi localizado na árvore!");

Então concluimos que uma hora por questões recursivas vamos ou achar o numero procurado e RETORNA ELE(ItemProcurado) ou vamos cair na EXCESSÃO.

Para melhor entendimento vamos  usar imagens

Pesquisar(10)

Primeiro começamos a busca pelo Raiz no caso seria o 30 verifcamos se o (30==ItemProcurado) no caso não é então verificamos se ele 10(Nosso elemento procurado) é MAIOR ou MENOR do que o 30,nesse caso e MENOR então continuamos nossa busca pela esquerda,e repetimos o processo


Comparamos o elemento procurado com a raiz se o 10==15 nesse caso não é então avançamos na nossa arvore,mas temos que saber se sera para a direita ou para edquerda então nos comparamos denovo se ele 10(Nosso elemento procurado) é MAIOR ou MENOR do que o 15,nesse caso e MENOR então continuamos nossa busca pela esquerda,e repetimos o processo

Comparamos novamente se o elemento procurado e igual a raiz se 10==10 nesse caso é verdadeiro e retornamos o elemento e concluimos uma busca com sucesso

![img.png](ABBSla.png)

Melhor Caso / Caso Médio: \(O(\log n)\)Se a árvore estiver bem balanceada (com os lados esquerdo e direito com alturas similares), o algoritmo corta metade das possibilidades a cada passo, funcionando de forma idêntica à pesquisa binária.

Pior Caso: \(O(n)\)Se os elementos forem inseridos em ordem (ex: 10, 20, 30, 40), a árvore vira uma linha reta para a direita. Nesse cenário, ela se comporta como uma lista encadeada simples, perdendo toda a vantagem da estrutura de árvore.

----------------------

## ADICIONAR

Agora vamos adicionar um item na arvore,eh bem similar a busca binaria,SEMPRE VAMOS ADICIONAR ONDE A POSIÇÃO É *NULL* Então nos vamos rodar a arovore todas perguntando se o nosso intem que nos queremos adicionar é maior ou menor que o item que  esta no nó da arvore,caso for maior vamos pra direita,caso nosso item for menor vamos para esquerda

Nesse exemplo agora vamos adicionar o 13
   
![Gemini_Generated_Image_p2igpnp2igpnp2ig.png](Gemini_Generated_Image_p2igpnp2igpnp2ig.png)

percebe se que no começo procuramos o local certo para a inserção
achamos o local que é a esquerda do 15 ja que 13 é maior do que 10 e menor do que 15
agora nos verficamos se tem açguma coisa então temos que garantir que não tem nenhum filho a esquerda do 15 e inserimos o 13,para inserir fazemos um setItem no No dele

Inserção sempre nas Folhas: Um novo elemento sempre será inserido como um novo nó folha (no final de algum caminho da árvore). O algoritmo nunca substitui ou "empurra" um nó existente para baixo durante a inserção simples; ele apenas encontra o primeiro espaço vazio (null) válido.

Caso Médio / Melhor Caso: $O(\log n)$Se a árvore estiver balanceada (como na imagem), o tempo de busca é logarítmico. A cada decisão (esquerda ou direita), você descarta metade dos nós restantes.

Pior Caso: $O(n)$Se os dados forem inseridos já ordenados (ex: 1, 2, 3, 4, 5), a árvore se torna "degenerada" (parecida com uma lista encadeada). Nesse cenário, a árvore perde sua eficiência, pois a altura $h$ fica igual ao número de nós $n$.

----------------------------------

## CAMINHAMENTO
Para nos percorremos a arvore e printar ela(por completa)nos temos tres modos diferentes,caminhamentos pré ordem,em ordem e pós ordem
para a gente fazer esse caminhamentos vamos usar principalemnte a RECURSÃO
Formula para saber o numero de chamadas:2n+1
Temos que verificar se a raiz é diferente de NULL

**Caminhamento PRÉ ORDEM**
Nesse tipo de caminhamento voce começa imprimindo a raiz depois as diversas subarvores
O melhor caminhamneto para entender as posições da arvore
A raiz sempre vem primiero por isso chama PRÉ ordem,a raiz vem antes
Se voce salvar a saida e reconstruir a arvore na ordem que saiu vocé terá a arvore originl de volta

*Ordem do  caminhamneto pré ordem é nessa ordem:

-Raiz

-Esquerda

-Direita

**CODIGO**

public void caminhamentoPreOrdem(No <E> raizArvore){
//verificamos se esta vazia
if(raizArvore!=null){
   System.out.println(raizArvore.getItem());      //RAIZ
   caminhamentoPreOrdem(raizArvore.getEsquerda());//ESQUERDA
   caminhamentoPreOrdem(raizArvore.getDireita()); //DIREITA
 }
}

Para saber melhor esse CaminhamentoPreOrdem fiz um MACETE:primeiro emprimimos a raiz da arvore,depois todos os elementos a sua esquerda,e como se vc desse um getesquerda e ja printasse,e depois nesse mesma subarvore da esquerda quando acabar de imprimir toda a esquerda imprima os elementos da direita da subarvore da esquerda subindo,depois disso vamos para a subarvore da direita,nela vamos imprimir,todos os ekekmntos da esquerda dessa subarvore a direita da nossa raiz principal e depois imprimimos os da direta subindo

So que se caimos em uma subarvore,ao deslocar tanto pra direita quanto para esquerda nos repetimos o macete

EX:Uma arvore 10,5,15,3,8,13,16,2,4

            _______[10]_______
            /                  \
       __[5]__              _[15]_
      /       \            /      \
    _[3]_       [8]      [13]    [16]
    /   \
  [2]   [4]


Imprimimos a raiz:10


--Vamos para a subarvore a esquerda do 20--


Imprimimos toda a esquerda:5,3,2


Agora pegamos a direita subindo(De baixo para cima):4,8


--Agora vamos para a subarvore a direita do 10---


Imprimimos toda a sua esq(Maiz primiero imprimimos a subarvore dessa raiz(15)):15,13


Agora imprimimos os elementos da direita subindo(De baixo para cima):16

--Agora Juntamos tudo fica:--

10,5,3,2,4,8,15,13,16

![Gemini_Generated_Image_xk15t4xk15t4xk15.png](Gemini_Generated_Image_xk15t4xk15t4xk15.png)

**CAMINHAMENTO EM ORDEM**
Impressão dos elementos da arvore do menor para o menor
Nesse tipo de caminhamento começamos pela Subarvore da esquerda e depois emprimos a raiz e depois a subarvore da diretia

Então sua orde de caminhamento é essa:

-Visita SubArvore da esquerda;

-Printa a raiz;

-Visita SubArvore da direita;

**CODIGO**

public void caminhamentoEmOrdem(No <E> raizArvore){
caminhamnetoEmOrdem(raizArvore.getEsquerda());
System.out.println(raizArvore.getItem());
caminhamentoEmOrdem(raizArvore.getDireita());
}

//  Caminhamento Em-Ordem: Esquerda -> Raiz -> Direita
  Resultado esperado: [2, 3, 4, 5, 8, 10, 13, 15, 16]

            _______[10]_______           <- 6° (Visita a Raiz Geral)
           /                  \
       __[5]__              _[15]_       
      /       \            /      \
    [3]       [8]        [13]    [16]    
    / \       /           /        \     
   [2] [4]   (Fim)       (Fim)    (Fim) 

2 na esquerda do 3 

4 na direita do 3

//  Ordem exata dos passos:

//  1º: [2] -> 2º: [3] -> 3º: [4] -> 4º: [5] -> 5º: [8] -> 6º: [10] -> 7º: [13] -> 8º: [15] -> 9º: [16]

![Gemini_Generated_Image_3y68ud3y68ud3y68.png](Gemini_Generated_Image_3y68ud3y68ud3y68.png)

DICA:Caso quiser trocar a ordem de impressão,ou seja imprimir os maiores e depois os menores e so trocar no codigo as chamas então fica:

public void caminhamentoEmOrdem(No <E> raizArvore){
caminhamentoEmOrdem(raizArvore.getDireita());
System.out.println(raizArvore.getItem());
caminhamnetoEmOrdem(raizArvore.getEsquerda());
}

**CAMINHAMENTO PÓS ORDEM**
Nesse caminhamento nos visitamos a raiz por ultimo
primeiro vamos pea esquerda na sua raiz mais profunda,depois a direita mais profunda tbm e depois subimos até a raiz

![CAMINHAMENTOPOSORDEMIMG](Screenshot_1.png)

nesse exemplo veja o macete que eu desenvolvi:
primeiro emprimimos a esquerda e a direita da arvoer começando de baixo para cima e quando chega na raiz principal da arvore pulamos ela e vamos para o ourto lado repetindo o processo so que descendo agora,ent emprimimos a esquerda descendo na subarvoer das direita e depois os elementos da direita subindo

acompanhe no exemplo fica:5,15,10---ate agora pegamos esq(5),15(dir),10 raiz

agora nos pulamos a raiz dessa nossa subarvore e pegamos o elemnto mais a esquerda,como n tem nos vamos pelo elelemntos da direita começando de baixo para cima

entao fica:35,30,25

agora que ja emprimimos toda a subarvoer da esq menos a raiz podemos emprimir  a raiz

raiz,subarovore esq-20

agora vamos para a subarvore da direita mas aqui a gente da uma mudada,nos emprimimos os elemntros da esquerda descendo 

entao fica:45,55

depois por a gente ja esta no fundo emprimimos os elelmntos da direito do fundo para o topo

entao fica:75,70,60

agora a subarover raiz da direita 50

e por ultimo a raiz da subarvore 40

resultado:5,15,10,35,30,25,20,45,55,75,70,60,50,40

**CODIGO**

public void caminhamentoPreOrdem(No <E> raizArvore){
caminhamnetoPreOrdem(raizArvore.getEsquerda());
caminhamentoPreOrdem(raizArvore.getDireita());
System.out.println(raizArvore.getItem());
}

## EXCLUSÃO
Agora vamos iniciar a exclusão de um nó em uma abb

Nos temos 3 casos para remolçao de um no na abb:

-Caso o nó for folha

-Caso o nó tiver 1 filho

-Caso o nó tiver 2 filhos

**Nó Folha**
Caso o no for folha e mais facil,nos so atualizamos os valores do seus pais e filhos

Basta ir no nó pai e mudar o ponteiro que apontava para o filho (setEsquerda ou setDireita) para null

voce simplemnte deixa seu pai orfã

*CODIGO*


// Método público

public void removerCaso1(E item) {

if (vazia()) {

throw new IllegalStateException("Erro: A árvore está vazia.");

}

if (item == null) {

return;

}

    // Caso especial: o item está na raiz e a raiz é uma folha
    if (this.raiz.getItem().equals(item)) {
        if (this.raiz.getEsquerda() == null && this.raiz.getDireita() == null) {
            this.raiz = null; // A árvore agora fica vazia
            return;
        } else {
            throw new IllegalArgumentException("Erro: O item é a raiz, mas ela não é uma folha!");
        }
    }

    // Se não for a raiz, chama o auxiliar para buscar a partir do topo
    removerCaso1AUX(item, this.raiz);
}

// Método auxiliar (percorre a árvore olhando para os filhos)
private void removerCaso1AUX(E item, No<E> raiz) {
if (raiz == null) {
return; // Caso base: chegou ao fim do caminho e não achou
}

    // 1. OLHA PARA O FILHO DA ESQUERDA
    if (raiz.getEsquerda() != null && raiz.getEsquerda().getItem().equals(item)) {
        // Encontrou o item! Agora valida se ele é REALMENTE uma folha
        if (raiz.getEsquerda().getEsquerda() == null && raiz.getEsquerda().getDireita() == null) {
            raiz.setEsquerda(null); // Pai limpa o ponteiro da esquerda! ✅
            return; // Remoção concluída com sucesso
        } else {
            throw new IllegalArgumentException("Erro: O item foi encontrado, mas não é uma folha!");
        }
    }

    // 2. OLHA PARA O FILHO DA DIREITA
    if (raiz.getDireita() != null && raiz.getDireita().getItem().equals(item)) {
        // Encontrou o item! Agora valida se ele é REALMENTE uma folha
        if (raiz.getDireita().getEsquerda() == null && raiz.getDireita().getDireita() == null) {
            raiz.setDireita(null); // Pai limpa o ponteiro da direita! ✅
            return; // Remoção concluída com sucesso
        } else {
            throw new IllegalArgumentException("Erro: O item foi encontrado, mas não é uma folha!");
        }
    }

    // 3. SE NÃO ENCONTROU NOS FILHOS DIRETOS, DIRECIONA A BUSCA (Propriedade da ABB)
    if (item.compareTo(raiz.getItem()) < 0) {
        removerCaso1AUX(item, raiz.getEsquerda()); // Procura na subárvore esquerda
    } else {
        removerCaso1AUX(item, raiz.getDireita());  // Procura na subárvore direita
    }
}

Agora Vamos para o caso 2 
No caso 2 nos temos que excluir um no com 1 filho

primeiramente nos verificamnos se o no que queremos remover realmente tem um filho

mas nos fazemos essa verificação tanto para filho da direita quanto para filho da esquerda

É como se o avo adotasse o neto

### 🔄 Remoção - Caso 2: Nó com apenas 1 filho

Nesta etapa, realizamos a verificação para identificar se o nó possui exatamente um filho (seja na direita ou na esquerda). O comportamento lógico dessa remoção funciona como se o **avô adotasse o neto**, mantendo a estrutura da árvore conectada de forma contínua.

#### 📝 Lógica de Verificação
* **Apenas filho à esquerda:** Se a direita for nula e a esquerda possuir um nó válido.
* **Apenas filho à direita:** Se a direita possuir um nó válido e a esquerda for nula.

---

#### 💻 Implementação do Trecho de Código

```java
// Primeiro, verificamos se o nó realmente tem apenas um filho
if (raiz.getDireita() == null && raiz.getEsquerda() != null) {
    // Nesse caso, ele só tem filho à esquerda
    filho = raiz.getEsquerda();
    raiz.setItem(filho.getItem());
    raiz.setEsquerda(null);
} 
else if (raiz.getDireita() != null && raiz.getEsquerda() == null) {
    // Nesse caso, ele só tem filho à direita
    filho = raiz.getDireita();
    raiz.setItem(filho.getItem());
    raiz.setDireita(null);
}
```
### 🔄 Remoção - Caso 3: Nó com 2 filhos

Esta é a etapa mais complexa da remoção em uma Árvore Binária de Busca (ABB). Quando um nó possui ambos os filhos válidos (esquerda e direita), não podemos simplesmente excluí-lo ou alterar os ponteiros diretos, pois isso fragmentaria a árvore.

#### 📝 Lógica de Substituição
Para resolver esse caso, aplicamos a estratégia de **substituição de valor**:
1. Encontramos o **antecessor direto** do nó (o maior elemento contido na sua subárvore esquerda).
2. Substituímos o item do nó que queremos remover pelo item desse elemento encontrado,ou seja substtuimos ele pelo antecessor.
3. Descemos recursivamente na subárvore para remover o nó que foi copiado (que agora se tornou uma folha ou possui apenas 1 filho, caindo nos casos 1 ou 2).
4. O no antecessor sempre tera no maximo um filho á esquerda ai seria como se a gente usasse o remover com um nó,ja visto.

---

#### 💻 Implementação do Trecho de Código

```java
// Caso o nó possua dois filhos válidos
if (raiz.getEsquerda() != null && raiz.getDireita() != null) {
    // 1. Encontra o antecessor (maior elemento do lado esquerdo)
    No<E> antecessor = encontrarMaiorEsquerda(raiz.getEsquerda());
    
    // 2. Copia o valor do antecessor para o nó atual
    raiz.setItem(antecessor.getItem());
    
    // 3. Remove o antecessor original que ficou duplicado na subárvore esquerda
    raiz.setEsquerda(removerAUX(antecessor.getItem(), raiz.getEsquerda()));
}
```
### Balanceamento
Se nos formos adicionar elementos em ordem crescente ou descrencente em uma arvore caimos no pior caso toda hora e ai teremos uma estrutura parecida com a de um vetor simples,então a ABB sempre vai cair no seu pior caso .

É para garatir que isso não aconteça vamos dar inicio ao balanceamento,,

Vamos minimizar o tempo medio de pesquisa,para isso vamos balancear a arvore usando com base a ALTURA da arvore

-Para cada um do seus nós as alturas de suas subarvores esquerda e da direita diferem em no maximo uma unidade

-Fator de balanceamento=Altura do nó da esquerda - Altura do nó da direita;Nos vazios terão altura = -1 ;

-Verificamos o balanceamento em cada exclusão e adição de nós na arvore 

-Se o fator de balanceamento for -1 quer dizer que a arvore tem mais filho á direita se o fator de balanceamento for 1 quer dizer que a arvore tem mais filho á esquerda;

-Então para saber se uma arvore esta balanceada ou n o FATOR DE BALANCEMANTO de todos os seus nós deve ter valores de [-1,0,1],qualquer valor de um FATOR DE BALANCEMANETO diferente desse significa que a arvore esta desbalanceada e nos precisamos fazer algumas alterações para balancear ela

### ⚖️ Tabela de Diretrizes de Balanceamento (Árvore AVL)

Quando a diferença de altura entre a subárvore esquerda e direita de um nó resulta em um **Fator de Balanceamento (FB) igual a -2 ou 2**, o nó está desbalanceado. Para corrigir isso, olhamos o **FB do seu filho** para decidir qual rotação aplicar.

> 📌 **Nota:** A fórmula utilizada para o cálculo do Fator de Balanceamento neste projeto é:  
> `FB = altura(subárvore_esquerda) - altura(subárvore_direita)`

| FB do Nó Desbalanceado | Filho a Analisar | FB do Filho | Caso Clássico | Ação / Rotação Necessária |
| :---: | :---: | :---: | :---: | :--- |
| **+2** (Pende para Esq.) | Esquerdo | **+1** ou **0** | Esquerda-Esquerda (EE) | **Rotação Simples à Direita** |
| **+2** (Pende para Esq.) | Esquerdo | **-1** | Esquerda-Direita (ED) | **Rotação Dupla à Direita** *(Esq. dps Dir.)* |
| **-2** (Pende para Dir.) | Direito | **-1** ou **0** | Direita-Direita (DD) | **Rotação Simples à Esquerda** |
| **-2** (Pende para Dir.) | Direito | **+1** | Direita-Esquerda (DE) | **Rotação Dupla à Esquerda** *(Dir. dps Esq.)* |

---

#### 💡 Resumo Visual Prático para os `if/else` do Código:

* **Sinais Iguais = Rotação Simples:** Se o nó está positivo (+2) e o filho também está positivo/neutro (+1 ou 0), ou se ambos estão negativos, resolve-se com apenas uma rotação na direção oposta.
* **Sinais Inversos = Rotação Dupla:** Se o nó está positivo (+2) e o filho está negativo (-1), os sinais cruzados "entortam" a árvore (formando um joelho ou zigue-zague). É necessário rotacionar o filho primeiro para alinhar os sinais e depois rotacionar o pai.

![Foto slide prof aeds 2,tabela de balancenamento](Captura%20de%20tela%202026-06-15%20225501.png)

Sinal TROCADO=Rotação dupla;



