import java.io.File; 
import javax.swing.*; 
import javax.swing.filechooser.*;  
import java.io.File; 
import javax.swing.ImageIcon;   

public class ImageFilter extends FileFilter { 
    public static String getExtension(File f) {         
        String ext = null;         
        String s = f.getName();         
        int i = s.lastIndexOf('.');           
        if (i > 0 &&  i < s.length() - 1) {             
            ext = s.substring(i+1).toLowerCase();         
        }         
        return ext;     
    }             
//Accept all directories and all gif, jpg, tiff, or png files.     
    public boolean accept(File f) {         
        if (f.isDirectory()) {             
            return true;      
        } 
        String extension = getExtension(f); 
        if (extension != null) { 
            if (extension.equals("jpeg") || extension.equals("jpg") ||
                extension.equals("gif") || extension.equals("tiff") || 
                extension.equals("png") || extension.equals("tif")) { 
                return true; 
            } else { 
                return false; 
            }
        } 
        return false;
    }         
    public String getDescription() {         
         return "Just Images";     
    } 
    protected static ImageIcon createImageIcon(String path) {         
        java.net.URL imgURL = ImageFilter.class.getResource(path);         
        if (imgURL != null) {             
            return new ImageIcon(imgURL);         
        } else {             
            System.err.println("Couldn't find file: " + path);             
            return null;         
        }     
    }
}