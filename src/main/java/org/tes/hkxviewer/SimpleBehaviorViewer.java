package org.tes.hkxviewer;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.tes.hkx.model.files.HkBehaviorFile;
import org.tes.hkx.model.files.HkFilesFactory;
import org.tes.hkx.tree.HKXTree;

public class SimpleBehaviorViewer {
	
	public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        HkFilesFactory factory = new HkFilesFactory();
        
        File behaviorFilePath = null;
        
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "XML Converted Havok File", "xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           System.out.println("You chose to open this file: " +
                chooser.getSelectedFile().getName());
           behaviorFilePath = chooser.getSelectedFile();
        } else {
        	System.exit(0);
        }
        
        try {
            //This will be our readonly copy for reference
            HkBehaviorFile hkbReadOnlyFile = new HkFilesFactory().loadTypedFile(behaviorFilePath, HkBehaviorFile.class);
            hkbReadOnlyFile.setFileName(behaviorFilePath.getName());
            
            HKXTree rotree = new HKXTree(hkbReadOnlyFile.getRoot());
            rotree.addTreeSelectionListener(new HKXTreeSelectionListener(rotree));

            // The JTree can get big, so allow it to scroll
            JScrollPane scrollpane = new JScrollPane(rotree);

            // Display it all in a window and make the window appear
            JFrame frame = new JFrame("ORIGINAL: "+hkbReadOnlyFile.getFileName());
            frame.getContentPane().add(scrollpane, "Center");
            frame.setSize(400, 600);
            frame.setVisible(true);
            
            

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

	
}
