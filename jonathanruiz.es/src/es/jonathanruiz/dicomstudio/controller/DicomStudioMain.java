/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jonathanruiz.dicomstudio.controller;

import es.jonathanruiz.dicomstudio.model.DicomImage;
import es.jonathanruiz.dicomstudio.view.ContainerView;
import es.jonathanruiz.dicomstudio.view.MainFrame;
import es.jonathanruiz.dicomstudio.view.ToolBarView;
import es.jonathanruiz.dicomstudio.view.ViewerView;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;
import javax.swing.UIManager.*;
/**
 *
 * @author jruiz
 */
public class DicomStudioMain {
    public static final String AXIAL = "Axial";
    public static final String SAGITTAL = "Sagittal";
    public static final String CORONAL = "Coronal";
    public static final String ISOMETRIC = "Isometric 3D";
    public static final String TITLE = "Dicom Studio";
    public static final String VERSION = "0.1";
    public static ToolBarView toolbarView = new ToolBarView();
    public static ToolBarController toolbarController = new ToolBarController(toolbarView);
    //public static final DicomStudioMain controller = new DicomStudioMain();
    public static JFrame view = new MainFrame();;
    
    public static DicomImage imageModel;
    
    
    public static ContainerView containerView = new ContainerView();
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    static {
        // VTK
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
        System.loadLibrary("vtkHybridJava");
        System.loadLibrary("vtkWidgetsJava");
        // VTK-GDCM
        System.loadLibrary("vtkgdcmJava");
        System.loadLibrary("gdcmjni");
    }

    public DicomStudioMain()
    {
    }  
    public static void main(String[] args) throws InterruptedException, InvocationTargetException
    {  
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        
        
        
        try
        {
          javax.swing.SwingUtilities.invokeLater(new Runnable()
          {
            @Override
            public void run()
            {
              //toolbarView =  new ToolBarView();
              //containerView = new ContainerView();
              //toolbarController = new ToolBarController(toolbarView);
                              
              JPopupMenu.setDefaultLightWeightPopupEnabled(false);
              ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);

              //view = new MainFrame();
              view.setLayout(new BorderLayout()); 
              
              toolbarView.setVisible(true);
              containerView = new ContainerView();
              containerView.setVisible(true);
              containerView.setLayout(new GridLayout(2,2));
              
              containerView.addViewer(new ViewerView());
              containerView.addViewer(new ViewerView());
              containerView.addViewer(new ViewerView());
              containerView.addViewer(new ViewerView());
              
              view.add(toolbarView,BorderLayout.NORTH);
              view.add(containerView,BorderLayout.CENTER);
             
              
              view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

              view.setTitle(TITLE + " " + VERSION);
              view.setSize(1024,768);
              view.setResizable(true);
              view.pack();
              view.setVisible(true);
             
            }
          });    	
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
    }
    
}
