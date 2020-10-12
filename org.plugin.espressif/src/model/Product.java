package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Product {
	private String name;
	private String type;
	private String description;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	public Product() {
	}
	
	public Product(String name, String type, String description) {
		this.setName(name);
		this.setType(type);
		this.setDescription(description);
	}
	
	public void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
