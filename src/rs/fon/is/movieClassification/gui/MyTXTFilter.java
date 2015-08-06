package rs.fon.is.movieClassification.gui;

import java.io.File;

class MyTXTFilter extends javax.swing.filechooser.FileFilter {
    @Override
    public boolean accept(File file) {
      
        return file.isDirectory() || file.getAbsolutePath().endsWith(".txt");
    }
    @Override
    public String getDescription() {
        
        return "Text documents (*.txt)";
    }
} 