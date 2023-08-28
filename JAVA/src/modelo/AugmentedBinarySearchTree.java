package modelo;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    int count;
    int leftCount;
    TreeNode left;
    TreeNode right;

    /**
     * Cria um novo nó da árvore com o valor fornecido.
     *
     * @param val o valor a ser armazenado no nó.
     */
    public TreeNode(int val) {
        this.val = val;
        count = 1;
        leftCount = 0;
        left = null;
        right = null;
    }
}

public class AugmentedBinarySearchTree {
    private TreeNode root;

    /**
     * Insere um valor na árvore.
     *
     * @param val o valor a ser inserido na árvore.
     */
    public void insert(int val) {
        root = insertNode(root, val);
    }

    /**
     * Função auxiliar para inserir um valor na árvore.
     *
     * @param node o nó atual.
     * @param val o valor a ser inserido na árvore.
     * @return o nó atualizado.
     */
    private TreeNode insertNode(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }

        if (val < node.val) {
            node.left = insertNode(node.left, val);
            node.leftCount++;
        } else if (val > node.val) {
            node.right = insertNode(node.right, val);
        } else {
            System.out.println(val + " já está na árvore, não pode ser inserido novamente.");
        }
        
        return node;
    }

    /**
     * Remove um valor da árvore.
     *
     * @param val o valor a ser removido da árvore.
     */
    public void remove(int val) {
        root = removeNode(root, val);
    }

    /**
     * Função auxiliar para remover um valor da árvore.
     *
     * @param node o nó atual.
     * @param val o valor a ser removido da árvore.
     * @return o nó atualizado.
     */
    private TreeNode removeNode(TreeNode node, int val) {
        if (node == null) {
            return null;
        }
        
        if (val < node.val) {
            node.leftCount--;
            node.left = removeNode(node.left, val);
        } else if (val > node.val) {
            node.right = removeNode(node.right, val);
        } else {
            if (node.count > 1) {
                node.count--;
            } else {
                if (node.left == null && node.right == null) {
                    node = null;
                } else if (node.left == null) {
                    node = node.right;
                } else if (node.right == null) {
                    node = node.left;
                } else {
                    TreeNode minNode = findMinNode(node.right);
                    node.val = minNode.val;
                    node.count = minNode.count;
                    node.right = removeNode(node.right, minNode.val);
                }
            }
        }

        return node;
    }

    /**
     * Encontra o nó com o menor valor na árvore.
     *
     * @param node o nó atual.
     * @return o nó com o menor valor.
     */
    private TreeNode findMinNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
    * Verifica se a árvore é uma árvore cheia.
    *
    * @param node o nó atual.
    * @return true se a árvore for cheia, caso contrário, false.
    */
private boolean isFullTree(TreeNode node) {
if (node == null) {
return true;
}    if (node.left == null && node.right == null) {
    return true;
}

if (node.left != null && node.right != null) {
    return isFullTree(node.left) && isFullTree(node.right);
}

return false;
}

/**
* Verifica se a árvore é uma árvore cheia.
*
* @return true se a árvore for cheia, caso contrário, false.
*/
public boolean isFull() {
return isFullTree(root);
}

/**
* Verifica se a árvore é uma árvore completa.
*
* @return true se a árvore for completa, caso contrário, false.
*/
public boolean isComplete() {
int nodeCount = getNodeCount(root);
return isCompleteTree(root, 0, nodeCount);
}

/**
* Verifica se a árvore é uma árvore completa.
*
* @param root o nó raiz da árvore.
* @param index o índice atual.
* @param numberNodes o número total de nós na árvore.
* @return true se a árvore for completa, caso contrário, false.
*/
boolean isCompleteTree(TreeNode root, int index, int numberNodes) {
// Check if the tree is empty
if (root == null)
    return true;

if (index > numberNodes)
    return false;

return (isCompleteTree(root.left, 2 * index + 1, numberNodes)
        && isCompleteTree(root.right, 2 * index + 2, numberNodes));
}

/**
* Obtém a contagem total de nós na árvore.
*
* @param node o nó atual.
* @return o número total de nós na árvore.
*/
private int getNodeCount(TreeNode node) {
if (node == null) {
    return 0;
}
return node.count + getNodeCount(node.left) + getNodeCount(node.right);
}

/**
* Retorna o n-ésimo elemento da árvore.
*
* @param n o índice do elemento desejado.
* @return o n-ésimo elemento da árvore.
* @throws IllegalArgumentException se o índice for inválido.
*/
public int nthElement(int n) {
if (n < 1 || n > getNodeCount(root)) {
    throw new IllegalArgumentException("Índice inválido");
}
return findNthElement(root, n);
}

/**
* Função auxiliar para encontrar o n-ésimo elemento da árvore.
*
* @param node o nó atual.
* @param n o índice do elemento desejado.
* @return o n-ésimo elemento da árvore.
*/
private int findNthElement(TreeNode node, int n) {
int leftCount = node.leftCount + node.count;

if (n <= node.leftCount) {
    return findNthElement(node.left, n);
} else if (n > leftCount) {
    return findNthElement(node.right, n - leftCount);
} else {
return node.val;
}
}
/**
 * Retorna a posição do elemento na árvore.
 *
 * @param x o elemento a ser procurado.
 * @return a posição do elemento na árvore. Retorna -1 se o elemento não for encontrado.
 */
public int position(int x) {
    return getPosition(root, x);
}

/**
 * Função auxiliar para obter a posição do elemento na árvore.
 *
 * @param node o nó atual.
 * @param x o elemento a ser procurado.
 * @return a posição do elemento na árvore. Retorna -1 se o elemento não for encontrado.
 */
private int getPosition(TreeNode node, int x) {
    if (node == null) {
        return -1;
    }

    if (x < node.val) {
        return getPosition(node.left, x);
    } else if (x > node.val) {
        return getPosition(node.right, x);
    } else {
        int leftCount = getNodeCount(node.left);
        return leftCount + 1;
    }
}

/**
 * Retorna a mediana da árvore.
 *
 * @return a mediana da árvore.
 */
public int median() {
    int totalCount = getNodeCount(root);
    if (totalCount % 2 == 0) {
        int midIndex1 = totalCount / 2;
        int midIndex2 = midIndex1 + 1;
        int val1 = findNthElement(root, midIndex1);
        int val2 = findNthElement(root, midIndex2);
        return Math.min(val1, val2);
    } else {
        int midIndex = (totalCount + 1) / 2;
        return findNthElement(root, midIndex);
    }
}

/**
 * Calcula a média dos nós da árvore, considerando um novo elemento x.
 *
 * @param x o novo elemento a ser adicionado para cálculo da média.
 * @return a média dos nós da árvore.
 */
public double average(int x) {
    int sum = getNodeSum(root);
    int count = getNodeCount(root);
    return (double) (sum + x) / (count + 1);
}

/**
 * Obtém a soma total dos valores dos nós na árvore.
 *
 * @param node o nó atual.
 * @return a soma total dos valores dos nós na árvore.
 */
private int getNodeSum(TreeNode node) {
    if (node == null) {
        return 0;
    }
    return node.val * node.count + getNodeSum(node.left) + getNodeSum(node.right);
}

/**
 * Imprime a travessia pré-ordem da árvore.
 */
public void printPreOrder() {
    List<Integer> preOrderList = new ArrayList<>();
    preOrderTraversal(root, preOrderList);
    System.out.println(preOrderList.toString());
}

/**
 * Função auxiliar para realizar a travessia pré-ordem da árvore.
 *
 * @param node o nó atual.
 * @param preOrderList a lista para armazenar a travessia pré-ordem.
 */
private void preOrderTraversal(TreeNode node, List<Integer> preOrderList) {
    if (node == null) {
        return;
    }

    preOrderList.add(node.val);
    preOrderTraversal(node.left, preOrderList);
    preOrderTraversal(node.right, preOrderList);
}

/**
 * Imprime a árvore em um formato específico.
 *
 * @param format o formato de impressão da árvore. 1 para formato 1, 2 para formato 2.
 * @throws IllegalArgumentException se o formato especificado for inválido.
 */
public void printTree(int format) {
    if (format == 1) {
        printTreeFormat1(root, "");
    } else if (format == 2) {
        printTreeFormat2(root);
    } else {
        throw new IllegalArgumentException("Invalid format");
    }
}

/**
 * Imprime a árvore no formato 1, com identação para representar a estrutura hierárquica.
 *
 * @param node o nó atual.
 * @param prefix o prefixo de identação.
 */
private void printTreeFormat1(TreeNode node, String prefix) {
    if (node == null) {
        return;
    }

    System.out.println(prefix + node.val);
    printTreeFormat1(node.left, prefix + "|--");
    printTreeFormat1(node.right, prefix + "`--");
}

/**
 * Imprime a árvore no formato 2, utilizando parênteses para representar a estrutura hierárquica.
 *
 * @param node o nó atual.
 */
private void printTreeFormat2(TreeNode node) {
    if (node == null) {
        return;
    }

    System.out.print("(" + node.val + " ");
    printTreeFormat2(node.left);
    printTreeFormat2(node.right);
    System.out.print(")");
}

/**
 * Método principal para execução do programa.
 *
 * @param args os argumentos de linha de comando.
 */
public static void main(String[] args) {
    AugmentedBinarySearchTree abb = new AugmentedBinarySearchTree();

    // Inserir os valores da entrada
    abb.insert(32);
    abb.insert(13);
    abb.insert(5);
    abb.insert(41);
    abb.insert(20);
    abb.insert(60);

    // Executar as operações solicitadas
    System.out.println("A árvore é cheia: " + abb.isFull());
    System.out.println("A árvore é completa: " + abb.isComplete());
    System.out.println("Elemento na posição 3: " + abb.nthElement(3));
    abb.insert(36);
    System.out.println("A árvore é cheia: " + abb.isFull());
    System.out.print("Pré-ordem da árvore: ");
    abb.printPreOrder();
    System.out.println("Imprimir árvore no formato 1:");
    abb.printTree(1);
    System.out.println("Imprimir árvore no formato 2:");
    abb.printTree(2);
    abb.remove(50);
    abb.insert(15);
    abb.insert(39);
    abb.remove(32);
    System.out.println("Posição do elemento 15: " + abb.position(15));
    abb.insert(39);
    System.out.println("Elemento na posição 5: " + abb.nthElement(5));
    System.out.println("Mediana da árvore: " + abb.median());
    System.out.println("Média dos nós da árvore com raiz 20: " + abb.average(20));
    System.out.println("Chave encontrada: " + (abb.position(36) != -1));
    abb.insert(25);
    System.out.println("Mediana da árvvore: " + abb.median());
    	 }
     }



