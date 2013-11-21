/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jonathanruiz.dicomstudio.controller;

import es.jonathanruiz.dicomstudio.model.DicomImage;
import es.jonathanruiz.dicomstudio.model.ImageReslice;
import es.jonathanruiz.dicomstudio.view.ToolBarView;
import es.jonathanruiz.dicomstudio.view.ViewerView;
import javax.swing.JFileChooser;

/**
 *
 * @author jruiz
 */
public class ToolBarController {
    ToolBarView toolBarView;
    //recoge eventos ocurridos en la barra de herramientas y los evalua
    public ToolBarController(ToolBarView toolbarview)
    {
        toolBarView=toolbarview;
    }
    public void openImageDialog()
    {
        DicomStudioMain.imageModel = new DicomImage();
        
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fileChooser.showOpenDialog(toolBarView);
        
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            
            try {
                System.out.println("Opening: " + fileChooser.getSelectedFile().getAbsolutePath() );  
                DicomStudioMain.imageModel.setInput(fileChooser.getSelectedFile().getAbsolutePath());
                
                /*
                DicomImageMPR dicom_image_mpr = new DicomImageMPR();
                dicom_image_mpr.setInput(DicomStudioMain.imageModel.getOutput());
                
                vtkConeSource cone = new vtkConeSource();
                cone.SetResolution(8);
                vtkPolyDataMapper coneMapper = new vtkPolyDataMapper();
                coneMapper.SetInput(cone.GetOutput());

                vtkActor coneActor = new vtkActor();
                coneActor.SetMapper(coneMapper);
                */
                                
                int viewSwicther = 0;
                for (ViewerView viewer_view : DicomStudioMain.containerView.getViewerList())
                {
                    ImageReslice image_reslice = new ImageReslice(DicomStudioMain.imageModel.getOutput());
                    viewer_view.addViewProp( image_reslice.getImageSlice() );
                    if ((viewSwicther % 3) == 0)
                    {
                        System.out.println("Debug: Axial");
                        viewer_view.setAxialView();
                    }
                    else if((viewSwicther % 3) == 1)
                    {
                        System.out.println("Debug: sagittal");
                        viewer_view.setSagittalView();
                    }
                    else if((viewSwicther % 3) == 2)
                    {
                        System.out.println("Debug: Coronal");
                        viewer_view.setCoronalView();
                    }
                    viewSwicther+=1;
                }                   
                
                //DicomStudioMain.containerView.add3DWidget(dicom_image_mpr.getImagePlaneWidgetAxial());
                //DicomStudioMain.containerView.add3DWidget(dicom_image_mpr.getImagePlaneWidgetCoronal());
                //DicomStudioMain.containerView.add3DWidget(dicom_image_mpr.getImagePlaneWidgetSagittal());
                
            } catch (Exception ex) {
                System.out.println(ex);
                //Logger.getLogger(DicomStudioMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        } else {
            System.out.println("Open command cancelled by user.");            
        }                 
    }
    
    public String getViewerActiveActionCommand() {
        return toolBarView.getViewportActionGroup().getSelection().getActionCommand();
    }
}
