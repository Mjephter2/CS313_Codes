package lecture12;

import java.util.Comparator;

import lecture4.List;

public abstract class AbstractList<E> implements List<E> {

	public void sort(Comparator<? super E> comp) {
		
		for (int i = 1; i < this.size(); i++) {
			E curr = this.get(i);
			int j = i;
			while(j > 0 && comp.compare(this.get(j-1), curr) > 0) {
				this.set(j, this.get(j-1));
				j--;
			}
			this.set(j, curr);			
		}
		
	}
	
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	public String toString() {
		if (this.size() == 0) return "[]";
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < this.size() - 1; i++) {
			sb.append(this.get(i) + ", ");
		}
		sb.append(this.get(this.size()-1) + "]");
		return sb.toString();
	}
	
}
