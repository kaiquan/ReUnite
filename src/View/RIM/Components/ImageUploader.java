package View.RIM.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import Controller.RIM.Utils.IOHelper;
import Images.RIM.ImageHelper;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class ImageUploader extends JPanel {

	public JList<Object> list;
	public DefaultListModel<Object> model;

	public File currentDir;
	public DropTarget target;

	JButton addButton, removeButton, uploadButton;

	public ImageUploader() {
		setSize(600, 600);
		setLocation(100, 100);

		Action uploadAction = new UploadAction("Upload",
				ImageHelper.loadImageIcon("uploadIcon.png", "", 32, 32, 5));
		Action browseAction = new BrowseAction("Browse...",
				ImageHelper.loadImageIcon("browseIcon.png", "", 32, 32, 5));
		Action removeAction = new RemoveAction("Remove",
				ImageHelper.loadImageIcon("deleteIcon.png", "", 32, 32, 5));
		Action selectAllAction = new SelectAllAction("Select All");

		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout());

		// JMenuBar menuBar = new JMenuBar();
		// JMenu fileMenu = new JMenu("File");
		// JMenu editMenu = new JMenu("Edit");
		// JMenuItem item;
		// int metaKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		// fileMenu.add(item = new JMenuItem(browseAction));
		// item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, metaKey));
		// fileMenu.add(item = new JMenuItem(uploadAction));
		// editMenu.add(item = new JMenuItem(selectAllAction));
		// item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, metaKey));
		// editMenu.add(item = new JMenuItem(removeAction));
		// menuBar.add(fileMenu);
		// menuBar.add(editMenu);
		// setJMenuBar(menuBar);

		model = new DefaultListModel<Object>();

		list = new JList<Object>(model);
		list.getInputMap().put(KeyStroke.getKeyStroke(65, KeyEvent.META_MASK),
				"selectAllAction");
		list.getActionMap().put("selectAllAction", selectAllAction);
		list.getInputMap().put(KeyStroke.getKeyStroke(8, 0), "removeAction");
		list.getActionMap().put("removeAction", removeAction);
		list.setVisibleRowCount(12);
		list.setCellRenderer(new ImageCellRenderer());

		target = new DropTarget(list, new FileDropTargetListener());

		add(new JScrollPane(list), BorderLayout.CENTER);

		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));

		JLabel uploadLabel = new JLabel();
		uploadLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));

		uploadButton = new JButton(uploadAction);
		addButton = new JButton(browseAction);
		removeButton = new JButton(removeAction);

		buttons.add(addButton);
		buttons.add(removeButton);
		buttons.add(uploadButton);

		add(buttons, BorderLayout.SOUTH);

		currentDir = new File(System.getProperty("user.dir"));

	}

	public int addToListModel(ArrayList<String> filenames) {
		int count = 0;
		for (Iterator<String> i = filenames.iterator(); i.hasNext();) {
			String filename = (String) i.next();
			if (!model.contains(filename)) {
				model.addElement(filename);
				count++;
			}
		}
		// System.out.println(count + " added. Contains:\n" + model.toString());
		return count;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setContentPane(new ImageUploader());
		frame.setSize(500, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	class BrowseAction extends AbstractAction {
		public BrowseAction(String text, Icon icon) {
			super(text, icon);
		}

		public void actionPerformed(ActionEvent evt) {
			JFileChooser chooser = new JFileChooser(currentDir);
			chooser.addChoosableFileFilter(new ImageFileFilter());
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setMultiSelectionEnabled(true);
			chooser.setDragEnabled(true);
			chooser.showOpenDialog(null);

			currentDir = chooser.getCurrentDirectory();

			File[] files = chooser.getSelectedFiles();
			ArrayList<String> filenames = new ArrayList<String>(files.length);
			for (int i = 0; i < files.length; i++)
				filenames.add(files[i].getAbsolutePath());
			addToListModel(filenames);
		}
	}

	class ImageFileFilter extends javax.swing.filechooser.FileFilter {
		public boolean accept(File file) {
			if (file.isDirectory())
				return false;
			String name = file.getName().toLowerCase();
			return (name.endsWith(".jpg") || name.endsWith(".png"));
		}

		public String getDescription() {
			return "Images ( *.jpg, *.png )";
		}
	}

	class UploadAction extends AbstractAction {
		public UploadAction(String text, Icon icon) {
			super(text, icon);
		}

		public void actionPerformed(ActionEvent evt) {
			System.out.println(evt.getActionCommand());
			// HTTP Client Networking Code called here.
			File[] files = new File[model.size()];
			for (int i = 0; i < model.size(); i++) {
				files[i] = new File(model.get(i).toString());
			}

			IOHelper.uploadFiles(files, "Images/3");
		}
	}

	class RemoveAction extends AbstractAction {
		public RemoveAction(String text, Icon icon) {
			super(text, icon);
		}

		public void actionPerformed(ActionEvent evt) {
			int selected[] = list.getSelectedIndices();
			if (selected.length == 0)
				return;
			for (int i = selected.length - 1; i >= 0; i--)
				model.removeElementAt(selected[i]);
		}
	}

	class SelectAllAction extends AbstractAction {
		public SelectAllAction(String text) {
			super(text);
		}

		public void actionPerformed(ActionEvent evt) {
			int num = model.getSize();
			int[] intArray = new int[num];
			for (int i = 0; i < num; i++)
				intArray[i] = i;
			list.setSelectedIndices(intArray);
		}
	}

	class ImageCellRenderer extends JLabel implements ListCellRenderer<Object> {
		public ImageCellRenderer() {
			setOpaque(true);
			setIconTextGap(12);
		}

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {

			File f = new File(value.toString());

			Border empty = BorderFactory.createEmptyBorder(3, 3, 3, 3);
			Border matte = BorderFactory.createMatteBorder(0, 0, 1, 0,
					Color.white);
			setBorder(BorderFactory.createCompoundBorder(matte, empty));

			setText(f.getName());

			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image icon = toolkit.getImage(f.getPath());
			Image scaledIcon = icon.getScaledInstance(40, 40, Image.SCALE_FAST);
			setIcon(new ImageIcon(scaledIcon));

			if (isSelected) {
				setBackground(new Color(61, 128, 223));
				setForeground(Color.white);
			} else if (index % 2 == 0) {
				setBackground(new Color(237, 243, 254));
				setForeground(Color.darkGray);
			} else {
				setBackground(Color.white);
				setForeground(Color.darkGray);
			}
			return this;
		}
	}

	class FileDropTargetListener extends DropTargetAdapter {
		public void dragEnter(DropTargetDragEvent evt) {
			requestFocusInWindow();
		}

		public void drop(DropTargetDropEvent evt) {
			try {
				Transferable tr = evt.getTransferable();
				DataFlavor[] flavors = tr.getTransferDataFlavors();
				for (int i = 0; i < flavors.length; i++) {
					if (flavors[i].isFlavorJavaFileListType()) {
						evt.acceptDrop(DnDConstants.ACTION_COPY);
						List list2 = (List) tr.getTransferData(flavors[i]);
						ArrayList filenames = new ArrayList();
						for (int j = 0; j < list2.size(); j++) {
							String path = list2.get(j).toString();
							if (((new ImageFileFilter()).accept(new File(path)))) {
								filenames.add(path);
							}
						}
						if (filenames.size() > 0) {
							@SuppressWarnings("unused")
							int numAdded = addToListModel(filenames);
							evt.dropComplete(true);
							return;
						}
					}
					JOptionPane
							.showMessageDialog(
									null,
									"You may only drag and drop\nimage files of type JPEG or PNG\nto the Uploader for uploading.",
									"Invalid File Type",
									JOptionPane.WARNING_MESSAGE);
					evt.rejectDrop();
				}

			} catch (Exception e) {
				e.printStackTrace();
				evt.rejectDrop();
			}
		}
	}

}
