package com.cxy.redisclient;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import com.cxy.redisclient.dto.StringInfo;

public class NewStringDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_1;
	private Text text_2;
	private String server;
	private int db;
	private String key;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public NewStringDialog(Shell parent, int style, String server, int db, String key) {
		super(parent, style);
		setText("SWT Dialog");
		this.server = server;
		this.db = db;
		this.key = key == null?"":key;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(326, 201);
		shell.setText("Add String");
		
		Rectangle screenSize = shell.getParent().getBounds();
		Rectangle shellSize = shell.getBounds();
		shell.setLocation(screenSize.x + screenSize.width / 2 - shellSize.width / 2,
				screenSize.y + screenSize.height / 2 - shellSize.height / 2);
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 10, 300, 121);
		
		TabItem tbtmString = new TabItem(tabFolder, SWT.NONE);
		tbtmString.setText("String");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmString.setControl(composite);
		
		Label lblKey = new Label(composite, SWT.NONE);
		lblKey.setText("Server");
		lblKey.setBounds(10, 13, 45, 13);
		
		Label lblValue = new Label(composite, SWT.NONE);
		lblValue.setText("key");
		lblValue.setBounds(10, 41, 29, 13);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(62, 38, 220, 19);
		text_1.setText(key);
		text_1.selectAll();
		text_1.setFocus();
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Value");
		label.setBounds(10, 69, 29, 13);
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(62, 66, 220, 19);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(61, 13, 89, 13);
		label_1.setText(server);
		
		Label lblDatabase = new Label(composite, SWT.NONE);
		lblDatabase.setText("Database");
		lblDatabase.setBounds(156, 13, 45, 13);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBounds(223, 13, 45, 13);
		label_3.setText(String.valueOf(db));
		
		Button button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				String key = text_1.getText();
				String value = text_2.getText();
				
				if( key.length() == 0 || value.length() == 0){
					MessageDialog.openError(shell, "error","please input string information!");
				} else {
					result = new StringInfo(key, value);
					shell.dispose();
				}
			}
		});
		button.setText("OK");
		button.setBounds(61, 144, 68, 23);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
			}
		});
		button_1.setText("Cancel");
		button_1.setBounds(188, 144, 68, 23);

	}
}