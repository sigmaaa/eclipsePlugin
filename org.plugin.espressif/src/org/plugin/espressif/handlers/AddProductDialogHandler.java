package org.plugin.espressif.handlers;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import dialogs.AddProductDialog;
import utils.JsonFileWritter;

public class AddProductDialogHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		AddProductDialog dialog = new AddProductDialog(window.getShell());
		if (dialog.open() == Window.OK) {
            String productName = dialog.getName();
            String productType = dialog.getType();
            String description = dialog.getDescription();
            if (productName.isBlank() || productType.isBlank() || description.isBlank()) {
            	MessageBox messageDialog = new MessageBox(window.getShell(), SWT.ICON_ERROR | SWT.OK | SWT.CANCEL);
            	messageDialog.setText("Adding error");
            	messageDialog.setMessage("You left some fields blank. Adding a product is not possible");
            	messageDialog.open();
            	return dialog;
            }
            try {
				JsonFileWritter.saveProductToFile(productName, productType, description);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		return dialog;
	}
}
