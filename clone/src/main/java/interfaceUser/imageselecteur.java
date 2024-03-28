/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaceUser;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import javax.swing.ImageIcon;

/**
 *
 * @author Jules_D
 */
public class imageselecteur extends javax.swing.JFrame {
 private Vector<String> datephoto;
 private Sql_handler s;
 Map<String, ArrayList<byte[]>> valueMap;
 ImageIcon image=null;
 final int premiereImage=1;
 int selection;
 String user;
 String idPatient;
 String key;
 int deplacementpixel=0;
 int y=0;
 int seuil=2;// en pixel
int imageselecteur=0;
         
    /**
     * Creates new form imageselecteur
     */
    public imageselecteur(String user, String idPatient) {
        this.idPatient=idPatient;
        this.user=user;
        initComponents();
         s = new Sql_handler();
        datephoto = new Vector<String>();
          valueMap = s.GetAllImages();// le premier element de chaque objet de la map est la date
         for(int i=0 ; i<valueMap.size();i++){
                String date = new String(valueMap.get(valueMap.keySet().toArray()[i]).get(0), StandardCharsets.UTF_8);
                  
                    
             datephoto.add(date);
         }
         listdatephoto.setListData(datephoto);
         
    }

    /**
     * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        photopreview = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listdatephoto = new javax.swing.JList<>();
        select = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        photopreview.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                photopreviewMouseDragged(evt);
            }
        });

        listdatephoto.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listdatephoto.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listdatephotoValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listdatephoto);

        select.setText("select");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(select, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(photopreview, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(select))
                    .addComponent(photopreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        // TODO add your handling code here:
        
        s.AddImages(idPatient, key);// marche
         new hub_medecin_et_observateur(true, user).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_selectActionPerformed

    private void listdatephotoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listdatephotoValueChanged
        // TODO add your handling code here:
        selection = listdatephoto.getSelectedIndex();
        key = valueMap.keySet().toArray()[selection].toString();
       
         image= s.TransformeImage(valueMap.get(key).get(premiereImage));
        photopreview.setIcon(image);
        
    }//GEN-LAST:event_listdatephotoValueChanged

    private void photopreviewMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_photopreviewMouseDragged
       deplacementpixel=y-evt.getYOnScreen();
      
           if(deplacementpixel<-1 & imageselecteur>0){
               imageselecteur--;
   
           }else{
              
               if(deplacementpixel>1 & imageselecteur<valueMap.get(key).size()-2){
                   imageselecteur++;
               }
           }
       
           // on monte ou descent dans les images selon le signe de temp
        image= s.TransformeImage(valueMap.get(key).get(premiereImage+imageselecteur));
        photopreview.setIcon(image);
   
        
        y=evt.getYOnScreen();
    }//GEN-LAST:event_photopreviewMouseDragged

    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listdatephoto;
    private javax.swing.JLabel photopreview;
    private javax.swing.JButton select;
    // End of variables declaration//GEN-END:variables
}
