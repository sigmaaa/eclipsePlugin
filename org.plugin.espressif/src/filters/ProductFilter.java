package filters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import model.Product;

public class ProductFilter extends ViewerFilter{
	
	private String searchString;
	private boolean isFullMatch;
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (searchString == null || searchString.length() == 0) {
            return true;
        }
		Product product = (Product) element;
		
		if(isFullMatch) {
			return fullMatchCheck(product);
		} 
		
		return containsCheck(product);
	}

	private boolean fullMatchCheck(Product product) {
		if (product.getName().matches(searchString) 
				|| product.getType().matches(searchString) 
				|| product.getDescription().matches(searchString)) {
			return true;
		}
		return false;
	}
	
	private boolean containsCheck(Product product) {
		if (product.getName().contains(searchString) 
				|| product.getType().contains(searchString) 
				|| product.getDescription().contains(searchString)) {
			return true;
		}
		return false;
	}
	
	public void setSearchText(String regex) {
		this.searchString = regex;
	}
	
	public void setFullMatch(boolean isFullMatch) {
		this.isFullMatch = isFullMatch;
	}
}
