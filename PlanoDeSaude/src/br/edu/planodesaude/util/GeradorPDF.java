package br.edu.planodesaude.util;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystemException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
public class GeradorPDF {
 
        private GeradorPDF() {
                ;
        }
 
        private static String geraCaminho() {
                JFileChooser file = new JFileChooser();
                file.setFileSelectionMode(JFileChooser.FILES_ONLY);
 
                int i = file.showSaveDialog(null);
                if (i == JFileChooser.CANCEL_OPTION)
                        return null;
                return file.getSelectedFile().getAbsolutePath();
        }
 
        public static File createDocument(String content)
                        throws FileSystemException {
                File f;
                int ans = JOptionPane.NO_OPTION;
                String caminho;
                do {
                        caminho = geraCaminho();
                        if(!caminho.endsWith(".pdf"))caminho += ".pdf";
                        f = new File(caminho);
                        if (f.exists()){
                                        ans = JOptionPane.showConfirmDialog(null, String.format(
                                                        "Deseja sobrescrever o arquivo \"%s\"?", caminho),
                                                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                                ans = JOptionPane.YES_OPTION;
                        }
 
                        if (ans == JOptionPane.CANCEL_OPTION)
                                return null;
                } while (ans != JOptionPane.YES_NO_OPTION);
                try {
                        FileOutputStream os = new FileOutputStream(f);
                        Document document = new Document();
                        PdfWriter pf = PdfWriter.getInstance(document, os);
                        document.open();
                        try{
                                Image img = Image.getInstance("horseIcon.jpg");
                                img.setAlignment(Image.RIGHT);
                                document.add(img);
                        }catch(IOException ioex){
                               
                        }
                        document.add(new Paragraph(content));
                        document.close();
                        pf.close();
                        return f;
                } catch (Exception ex) {
                        ex.printStackTrace();
                        throw new FileSystemException("Erro ao criar arquivo.");
                }
        }
}