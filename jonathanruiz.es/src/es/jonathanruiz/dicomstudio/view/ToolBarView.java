/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jonathanruiz.dicomstudio.view;

import es.jonathanruiz.dicomstudio.controller.DicomStudioMain;
import es.jonathanruiz.dicomstudio.controller.ToolBarController;
import java.awt.Color;
import javax.swing.ButtonGroup;

/**
 *
 * @author jruiz
 */
public class ToolBarView extends javax.swing.JPanel {
    
    /**
     * Creates new form ToolBarView
     */
    public ToolBarView() {
        initComponents();
    }

    public ButtonGroup getViewportActionGroup() {
        return viewportActionGroup;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewportActionGroup = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        openImage = new javax.swing.JButton();
        saveImage = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        realign = new javax.swing.JToggleButton();
        splineWidget = new javax.swing.JToggleButton();
        angleWidget = new javax.swing.JToggleButton();
        measureDistance = new javax.swing.JToggleButton();
        newOrigin = new javax.swing.JToggleButton();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMaximumSize(new java.awt.Dimension(32767, 45));
        setMinimumSize(new java.awt.Dimension(0, 45));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(32767, 45));
        setRequestFocusEnabled(false);

        jToolBar1.setFloatable(false);
        jToolBar1.setMaximumSize(new java.awt.Dimension(32767, 45));
        jToolBar1.setMinimumSize(new java.awt.Dimension(145, 45));
        jToolBar1.setName(""); // NOI18N
        jToolBar1.setPreferredSize(new java.awt.Dimension(10000, 45));
        jToolBar1.setVerifyInputWhenFocusTarget(false);

        openImage.setBackground(javax.swing.UIManager.getDefaults().getColor("ToolBar.background"));
        openImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/jonathanruiz/dicomstudio/images/icons/carpeta.png"))); // NOI18N
        openImage.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        openImage.setBorderPainted(false);
        openImage.setFocusable(false);
        openImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openImage.setMaximumSize(new java.awt.Dimension(42, 42));
        openImage.setMinimumSize(new java.awt.Dimension(42, 42));
        openImage.setPreferredSize(new java.awt.Dimension(42, 42));
        openImage.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openImageMouseClicked(evt);
            }
        });
        openImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openImageActionPerformed(evt);
            }
        });
        jToolBar1.add(openImage);

        saveImage.setBackground(javax.swing.UIManager.getDefaults().getColor("ToolBar.background"));
        saveImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/jonathanruiz/dicomstudio/images/icons/disk-media-icone-3883-128.png"))); // NOI18N
        saveImage.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        saveImage.setBorderPainted(false);
        saveImage.setFocusable(false);
        saveImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveImage.setMaximumSize(new java.awt.Dimension(42, 42));
        saveImage.setMinimumSize(new java.awt.Dimension(42, 42));
        saveImage.setPreferredSize(new java.awt.Dimension(42, 42));
        saveImage.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveImageActionPerformed(evt);
            }
        });
        jToolBar1.add(saveImage);
        jToolBar1.add(jSeparator1);

        realign.setBackground(javax.swing.UIManager.getDefaults().getColor("ToolBar.background"));
        viewportActionGroup.add(realign);
        realign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/jonathanruiz/dicomstudio/images/icons/realinear.png"))); // NOI18N
        realign.setSelected(true);
        realign.setActionCommand("realignWidget");
        realign.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        realign.setBorderPainted(false);
        realign.setFocusable(false);
        realign.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        realign.setMaximumSize(new java.awt.Dimension(42, 42));
        realign.setMinimumSize(new java.awt.Dimension(42, 42));
        realign.setPreferredSize(new java.awt.Dimension(42, 42));
        realign.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        realign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realignActionPerformed(evt);
            }
        });
        jToolBar1.add(realign);

        splineWidget.setBackground(javax.swing.UIManager.getDefaults().getColor("ToolBar.background"));
        viewportActionGroup.add(splineWidget);
        splineWidget.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/jonathanruiz/dicomstudio/images/icons/trazar curva.png"))); // NOI18N
        splineWidget.setActionCommand("splineWidget");
        splineWidget.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        splineWidget.setBorderPainted(false);
        splineWidget.setFocusable(false);
        splineWidget.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        splineWidget.setMaximumSize(new java.awt.Dimension(42, 42));
        splineWidget.setMinimumSize(new java.awt.Dimension(42, 42));
        splineWidget.setPreferredSize(new java.awt.Dimension(42, 42));
        splineWidget.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        splineWidget.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                splineWidgetMouseClicked(evt);
            }
        });
        jToolBar1.add(splineWidget);

        angleWidget.setBackground(javax.swing.UIManager.getDefaults().getColor("ToolBar.background"));
        viewportActionGroup.add(angleWidget);
        angleWidget.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/jonathanruiz/dicomstudio/images/icons/angulos.png"))); // NOI18N
        angleWidget.setActionCommand("angleWidget");
        angleWidget.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        angleWidget.setBorderPainted(false);
        angleWidget.setFocusable(false);
        angleWidget.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        angleWidget.setMaximumSize(new java.awt.Dimension(42, 42));
        angleWidget.setMinimumSize(new java.awt.Dimension(42, 42));
        angleWidget.setPreferredSize(new java.awt.Dimension(42, 42));
        angleWidget.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        angleWidget.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                angleWidgetMousePressed(evt);
            }
        });
        jToolBar1.add(angleWidget);

        measureDistance.setBackground(javax.swing.UIManager.getDefaults().getColor("ToolBar.background"));
        viewportActionGroup.add(measureDistance);
        measureDistance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/jonathanruiz/dicomstudio/images/icons/medir.png"))); // NOI18N
        measureDistance.setActionCommand("distanceWidget");
        measureDistance.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        measureDistance.setBorderPainted(false);
        measureDistance.setFocusable(false);
        measureDistance.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        measureDistance.setMaximumSize(new java.awt.Dimension(42, 42));
        measureDistance.setMinimumSize(new java.awt.Dimension(42, 42));
        measureDistance.setPreferredSize(new java.awt.Dimension(42, 42));
        measureDistance.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(measureDistance);

        newOrigin.setBackground(javax.swing.UIManager.getDefaults().getColor("ToolBar.background"));
        viewportActionGroup.add(newOrigin);
        newOrigin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/jonathanruiz/dicomstudio/images/icons/flecha 2.png"))); // NOI18N
        newOrigin.setActionCommand("newOriginWidget");
        newOrigin.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        newOrigin.setBorderPainted(false);
        newOrigin.setFocusable(false);
        newOrigin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newOrigin.setMaximumSize(new java.awt.Dimension(42, 42));
        newOrigin.setMinimumSize(new java.awt.Dimension(42, 42));
        newOrigin.setPreferredSize(new java.awt.Dimension(42, 42));
        newOrigin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(newOrigin);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void realignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realignActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_realignActionPerformed

    private void openImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openImageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_openImageActionPerformed

    private void saveImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveImageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveImageActionPerformed

    private void openImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openImageMouseClicked
        DicomStudioMain.toolbarController.openImageDialog();        // TODO add your handling code here:
        
    }//GEN-LAST:event_openImageMouseClicked

    private void angleWidgetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_angleWidgetMousePressed
        
    }//GEN-LAST:event_angleWidgetMousePressed

    private void splineWidgetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_splineWidgetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_splineWidgetMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton angleWidget;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToggleButton measureDistance;
    private javax.swing.JToggleButton newOrigin;
    private javax.swing.JButton openImage;
    private javax.swing.JToggleButton realign;
    private javax.swing.JButton saveImage;
    private javax.swing.JToggleButton splineWidget;
    private javax.swing.ButtonGroup viewportActionGroup;
    // End of variables declaration//GEN-END:variables
}
