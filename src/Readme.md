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

Agora vamos adicionar um item na arvore 






