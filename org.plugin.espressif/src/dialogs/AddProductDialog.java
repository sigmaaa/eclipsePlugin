package dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddProductDialog extends TitleAreaDialog{

	private final String[] productTypes = new String[] {"Devkit", "Module", "SoC's"};
	private Text productName;
    private Combo productType;
    private Text productDescription;

    private String name;
    private String type;
    private String description;
	
	public AddProductDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

    @Override
    public void create() {
        super.create();
        setTitle("This is adding product dialog");
        setMessage("By clicking OK, you will add a product to the 'products.json'. All fields are required", IMessageProvider.INFORMATION);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout layout = new GridLayout(2, false);
        container.setLayout(layout);
        
        createName(container);
        createType(container);
        createDescription(container);
        return area;
    }

    private void createDescription(Composite container) {
        Label lbtDescription = new Label(container, SWT.NONE);
        lbtDescription.setText("Description");
        productDescription = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
        productDescription.setTextLimit(300);
        productDescription.setLayoutData(new GridData(GridData.FILL_BOTH));
    }
    
    private void createName(Composite container) {
        Label lbtName = new Label(container, SWT.NONE);
        lbtName.setText("Product name");

        GridData dataName = new GridData();
        dataName.grabExcessHorizontalSpace = true;
        dataName.horizontalAlignment = GridData.FILL;

        productName = new Text(container, SWT.BORDER);
        productName.setTextLimit(100);
        productName.setLayoutData(dataName);
    }

    private void createType(Composite container) {
        Label lbtProductType = new Label(container, SWT.NONE);
        lbtProductType.setText("Product type");

        productType = new Combo(container, SWT.DROP_DOWN);
        productType.setItems(productTypes);
    }



    @Override
    protected boolean isResizable() {
        return true;
    }

    // save content of the Text fields because they get disposed
    // as soon as the Dialog closes
    private void saveInput() {
        name = productName.getText();
        type = productType.getText();
        description = productDescription.getText();
    }

    @Override
    protected void okPressed() {
        saveInput();
        super.okPressed();
    }

    public String getName() {
        return name;
    }
    
    public String getType() {
    	return type;
    }
    
    public String getDescription() {
    	return description;
    }
    
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Adding a new product dialog");
    }


}
