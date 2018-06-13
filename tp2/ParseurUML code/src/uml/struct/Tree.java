package uml.struct;

import java.util.ArrayList;

/**
 * 
 * @author Charbel Kassis
 * 
 * Classe qui represente un arbre
 */
public class Tree<T> {
	
	/**
	 * 
	 * @author Charbel Kassis
	 *
	 * @param <T2> Le type d'objet a stocker dans l'arbre
	 */
	public class TreeNode<T2> {
		
		private T2 object;
		private ArrayList<TreeNode<T2>> sons;
		
		
		private TreeNode(T2 object) {
			
			this.object = object;
			sons = new ArrayList<>();
		}
		
		/**
		 * 
		 * @return la valeur stocker dans le noeud
		 */
		public T2 get() {
			
			return this.object;
		}
		
		/**
		 * 
		 * @return les fils du noeud dans une ArrayList
		 */
		public ArrayList<TreeNode<T2>> getSons() {
			
			return this.sons;
		}
		
		/**
		 * 
		 * @param son ajouter un fils
		 */
		public void addSon(T2 son) {
			
			this.sons.add(new TreeNode<T2>(son));
		}
		
		/**
		 * la profondeur maximum est 0 si le noeud n'a pas de fils, sinon
		 * 1 + le maximum de la profondeur de ses fils
		 * 
		 * @return la profondeur maximum a partir de ce noeud
		 */
		public int getDepth() {
			
			if(this.sons.isEmpty())
				
				return 0;
			
			else {
				
				int[] deptsOfSons = new int[this.sons.size()];
				
				for(int i : deptsOfSons)
					
					this.sons.get(i).getDepth();
				
				return 1 + max(deptsOfSons);
					
			}
			
		}
		
		/**
		 * le nombre de noeud a partir d'un noeud est 0 si le noeud n'a pas de fils, sinon
		 * c'est egale au nombre de noeud a partir de ces fils + le nombre de fils
		 * 
		 * @return le nombre de noeud a partir de ce noeud
		 */
		public int getNodeCount() {
			
			if(this.sons.isEmpty())
				
				return 0;
			
			else {
				
				int count = 0;
				
				for(Tree<T>.TreeNode<T2> son : this.getSons())
					
					count += son.getNodeCount();
				
				return count+this.getSons().size();
				
			}
					
		}

	}
	
	private TreeNode<T> root;

	public TreeNode<T> getRoot() {
		
		return this.root;
	}
	
	public void setRoot(T root) {
		
		this.root = new TreeNode<T>(root);
	}
	
	/**
	 * 
	 * @return la profondeur maximum de l'arbre
	 */
	public int getMaximumDepth() {
		
		return this.root.getDepth();
	}
	
	/** le nombre de noeud de l'arbre est egale au nombre de noeud a partir de 
	 * la racine + 1
	 * 
	 * @return le nombre de noeud de l'arbre 
	 */
	public int size() {
		
		return 1 + this.root.getNodeCount();
	}
	
	
	/**
	 * 
	 * @param list une liste ou bien une enumeration de chiffre
	 * @return la valeur maximum de la liste ou de l'enumeration
	 */
	private static int max(int ... list) {
		
		int max = list[0];
		
		for(int i=1;i<list.length;i++) {
			
			if(list[i] > max)
				
				max = list[i];
		}
		
		return max;
	}
	
}
