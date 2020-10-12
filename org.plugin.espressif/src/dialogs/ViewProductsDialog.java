package dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import filters.ProductFilter;
import model.Product;
import utils.JsonParser;

public class ViewProductsDialog extends TitleAreaDialog {
	
	private TableViewer productTableViewer;
	private Table productTable;
	private Button searchButton;
	private Button isFullMatchCheckBox;
	private ProductFilter filter;
	private final String[] titles = { "Name", "Type", "Description"};
	
    private Text searchField;
	
	public ViewProductsDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void create() {
        super.create();
        setTitle("This is view dialog");
        setMessage("You can filter products by name, type or description", IMessageProvider.INFORMATION);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout layout = new GridLayout(4, false);
        container.setLayout(layout);
        createSearchField(container);
        createIsFullMatchCheckBox(container);
        createSearchButton(container);
        createProductViewer(container);
        filter = new ProductFilter();
        productTableViewer.addFilter(filter);
        return area;
    }
    
    private void createSearchButton(Composite container) {
    	 searchButton = new Button(container, SWT.PUSH);
         searchButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
                 false));
         searchButton.setText("Search");
         searchButton.addSelectionListener((SelectionListener) new SelectionAdapter() {
             @Override
             public void widgetSelected(SelectionEvent e) {
            	 filter.setSearchText(searchField.getText());
            	 filter.setFullMatch(isFullMatchCheckBox.getSelection());
                 productTableViewer.refresh();

             }
         });
    }
    
    private void createSearchField(Composite container) {
    	Label lbtName = new Label(container, SWT.NONE);
        lbtName.setText("Product filter");

        GridData dataName = new GridData();
        dataName.grabExcessHorizontalSpace = true;
        dataName.horizontalAlignment = SWT.FILL;

        searchField = new Text(container, SWT.BORDER);
        searchField.setTextLimit(100);
        searchField.setLayoutData(dataName);
    }
    
    
    
    private void createProductViewer(Composite container) {
    	productTableViewer = new TableViewer(container,SWT.MULTI | SWT.H_SCROLL
                			| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
    	createColumns(container);
    	productTable = productTableViewer.getTable();
    	productTable.setHeaderVisible(true);
    	productTable.setLinesVisible(true);
    	
    	productTableViewer.setContentProvider(new ArrayContentProvider());
    	productTableViewer.setInput(JsonParser.getAllProductsFromJson());
    	resizeAllColumns();
    	GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 2;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        productTableViewer.getControl().setLayoutData(gridData);
        
    }
    
    private void createIsFullMatchCheckBox(Composite container) {
    	isFullMatchCheckBox = new Button(container ,SWT.CHECK);
    	isFullMatchCheckBox.setText("Full match");
    }

    private void createColumns(Composite containter) {
    	
    	TableViewerColumn col = createTableViewerColumn(titles[0]);
    	col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                Product p = (Product) element;
                return p.getName();
            }
        });
    	col = createTableViewerColumn(titles[1]);
    	col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                Product p = (Product) element;
                return p.getType();
            }
        });
    	col = createTableViewerColumn(titles[2]);
    	col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                Product p = (Product) element;
                return p.getDescription();
            }
        });
    	
    }
    
    private TableViewerColumn createTableViewerColumn(String title) {
    	TableViewerColumn viewerColumn = new TableViewerColumn(productTableViewer, SWT.NONE);
    	TableColumn column = viewerColumn.getColumn();
    	column.setText(title);
    	column.setResizable(true);
    	column.setMoveable(true);
    	return viewerColumn;
    }
    
	private void resizeAllColumns() {
		for (TableColumn tc : productTableViewer.getTable().getColumns()) {
    		tc.pack();
    	}
	}
    
    @Override
    protected boolean isResizable() {
        return true;
    }
    
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("View products dialog");
    }

}
