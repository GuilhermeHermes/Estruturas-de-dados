package modelo;

public class BinaryTree {
	private int value;
	private BinaryTree left_tree;
	private BinaryTree right_tree;
	private BinaryTree father;
	private int level;
	
	public BinaryTree(int value) {
		this.value = value;
		this.left_tree = null;
		this.right_tree = null;
		this.father = null;
		this.setLevel(1);
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public BinaryTree getLeft_tree() {
		return left_tree;
	}
	
	public void setLeft_tree(BinaryTree left_tree) {
		this.left_tree = left_tree;
	}
	
	public BinaryTree getRight_tree() {
		return right_tree;
	}
	
	public void setRight_tree(BinaryTree right_tree) {
		this.right_tree = right_tree;
	}
	
	public BinaryTree getFather() {
		return father;
	}

	public void setFather(BinaryTree father) {
		this.father = father;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void insertion(int value) {
		// Caso o valor seja menor que a raiz checa o nó a esquerda
		if(value < this.value) {
			// Caso o nó seja vazio cria uma árvore
			if(this.left_tree == null) {
				left_tree = new BinaryTree(value);
				left_tree.setFather(this);
				left_tree.level = level+1;
			}
			// Caso já exista um nó faz a chamada recursiva
			else {
				left_tree.insertion(value);
			}
		}
		// Caso o valor seja maior que a raiz checa o nó a direita
		else if(value > this.value) {
			// Caso o nó seja vazio cria uma árvore
			if(this.getRight_tree() == null) {
				right_tree = new BinaryTree(value);
				right_tree.setFather(this);
				right_tree.level = level+1;
			}
			// Caso já exista um nó faz a chamada recursiva
			else {
				right_tree.insertion(value);
			}
		}
	}
	
// Não funciona como deveria 
	public boolean search(int value) {
		//???
	}
	
	public void remove(int value) {
		if(value == this.value) {
			//caso 1: não tem filhos
			if(left_tree == null && right_tree == null) {
				// Identificando qual nó deve ser eliminado 
				 if(father.value > value) {
					 father.left_tree = null;
				 }
				 else if(father.value < value) {
					 father.right_tree = null;
				 }
			}
			
			// caso 2: tem um filho
			if(left_tree != null && right_tree == null) {
				// Avô vira pai
				left_tree.father = father;
				// Caso o valor do nó a esquerda seja menor que o valor do pai o nó fica a esquerda
				if(left_tree.value < father.value) {
					father.left_tree = left_tree;
				}
				// Caso o valor do nó a esquerda seja maior que o valor do pai o nó fica a direita
				else if(left_tree.value > father.value) {
					father.right_tree = left_tree;
				}
				
			}
			if(left_tree == null && right_tree != null) {
				// Avô vira pai
				right_tree.father = father;
				// Caso o valor do nó a direita seja menor que o valor do pai o nó fica a esquerda
				if(right_tree.value < father.value) {
					father.left_tree = right_tree;
				}
				// Caso o valor do nó a direita seja maior que o valor do pai o nó fica a direita
				else if(left_tree.value > father.value) {
					father.right_tree = right_tree;
				}
			}
			// caso 3: tem dois filhos
			if(left_tree != null && right_tree != null) {
				//???
			}
		}
		else { 
			// Chamada da função recursiva
			if(left_tree != null) {
				left_tree.remove(value);
			}
			// Chamada da função recursiva
			if(right_tree != null) {
				right_tree.remove(value);
			}
		}
	}
	
	public void printTree() {
		// Imprime o valor do nó
		System.out.println("valor: "+ value + " level: " + level);
		// Caso o nó a esquerda não seja vazio chama a função recursivamente
		if(left_tree != null) {
			left_tree.printTree();
		}
		// Caso o nó a direita não seja vazio chama a função recursivamente
		if(right_tree != null) { 
			right_tree.printTree();
		}
		
	}
	
	public static void main(String[] args) {
		BinaryTree abb = new BinaryTree(10);

		// árvore 
		abb.insertion(8);
		abb.insertion(15);
		abb.insertion(11);
		abb.insertion(20);
		abb.insertion(3);
		abb.insertion(10);
		abb.insertion(17);
		abb.insertion(29);
		abb.insertion(1);;
		System.out.println("ARVORE 1");
		abb.printTree();
		System.out.println();
		//abb.remove(20);
		//abb.remove(1);
		//abb.remove(11);
		System.out.println("ARVORE 2");
		abb.printTree();
		System.out.println();
		
		
		/*if(abb.search(11)) {
			System.out.println("main: Encontrado!!!");
		}
		else {
			System.out.println("main: Não encontrado!!!");
		}
		*/
		
	}
	
}

