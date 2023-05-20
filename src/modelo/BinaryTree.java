package modelo;


public class BinaryTree {
	private int value;
	private BinaryTree left_tree;
	private BinaryTree right_tree;
	
	public BinaryTree(int value) {
		this.value = value;
		this.left_tree = null;
		this.right_tree = null;
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
	
	public void insertion(int value) {
		if(value < this.value) {
			if(this.getLeft_tree() == null) {
				left_tree = new BinaryTree(value);
			}
			else {
				left_tree.insertion(value);
			}
		}
		else if(value > this.value) {
			if(this.getRight_tree() == null) {
				right_tree = new BinaryTree(value);
			}
			else {
				right_tree.insertion(value);
			}
		}
	}
	
	public void printTree() {
			System.out.println("root: " + getValue());
			if(left_tree != null) {
				left_tree.printTree();
			}
			if(right_tree != null) { 
				right_tree.printTree();
			}
			
	}
	
	// Não funciona como deveria 
	public boolean search(int value) {
		boolean found = false;
		//System.out.println("Valor: " + getValue());
		if(getValue() == value) {
			System.out.println("metodo: Valor encontrado!!!");
			found = true;
		}
		else {
			if(left_tree != null) {
				left_tree.search(value);
			}
			if(right_tree != null) {
				right_tree.search(value);
			}
		}
		
		return found;

	}
	
	public static void main(String[] args) {
		BinaryTree abb1 = new BinaryTree(10);

		// árvore 
		abb1.insertion(8);
		abb1.insertion(15);
		abb1.insertion(11);
		abb1.insertion(20);
		abb1.insertion(3);
		abb1.insertion(10);
		abb1.insertion(17);
		abb1.insertion(29);
		abb1.insertion(1);
	
		//abb1.printTree();
		
		if(abb1.search(11)) {
			System.out.println("main: Encontrado!!!");
		}
		else {
			System.out.println("main: Não encontrado!!!");
		}
		
		
		
	}
		
}

