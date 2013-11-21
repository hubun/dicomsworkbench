/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jonathanruiz.dicomstudio.model;

import vtk.vtkImageData;
import vtk.vtkImageProperty;
import vtk.vtkImageResliceMapper;
import vtk.vtkImageSlice;

/**
 *
 * @author jruiz
 */
public class ImageReslice {
    
    vtkImageResliceMapper im = new vtkImageResliceMapper();
    vtkImageSlice imageSlice = new vtkImageSlice();
    vtkImageProperty imageProperty =new vtkImageProperty();

    public vtkImageSlice getImageSlice() {
        return imageSlice;
    }
    
    public ImageReslice(vtkImageData image)
    {
        im.SetInput(image);
        im.SliceFacesCameraOn();
        im.SliceAtFocalPointOn(); 
        im.BorderOn();   
        
        
        imageProperty.SetColorWindow(3000); 
        imageProperty.SetColorLevel(900); 
        imageProperty.SetAmbient(0.0); 
        imageProperty.SetDiffuse(1.0); 
        imageProperty.SetOpacity(1.0); 
        imageProperty.SetInterpolationTypeToLinear();        
       
        imageSlice.SetMapper(im); 
        imageSlice.SetProperty(imageProperty); 
        
        
        /*
        int[] extent;
        double[] spacing;
        double[] origin;
        extent = image.GetWholeExtent();
        spacing = image.GetSpacing();
        origin = image.GetOrigin();
        // Matrices for axial, coronal, sagittal view orientations
           double[][] elements = new double[][]{{
           1, 0, 0, 0,
           0, 1, 0, 0,
           0, 0, 1, 0,
           0, 0, 0, 1 },{
           1, 0, 0, 0,
           0, 0, 1, 0,
           0,-1, 0, 0,
           0, 0, 0, 1 },{
           0, 0,-1, 0,
           1, 0, 0, 0,
           0,-1, 0, 0,
           0, 0, 0, 1 }};

        double[] center = new double[3];
        center[0] = origin[0] + spacing[0] * 0.5 * (extent[0] + extent[1]);
        center[1] = origin[1] + spacing[1] * 0.5 * (extent[2] + extent[3]);
        center[2] = origin[2] + spacing[2] * 0.5 * (extent[4] + extent[5]);
        
        
        for (int i=0; i<3; i++)
        {

     // Set the slice orientation
     vtkMatrix4x4 resliceAxes = new vtkMatrix4x4();
     resliceAxes.DeepCopy(elements[i]);
     // Set the point through which to slice
     resliceAxes.SetElement(0, 3, center[0]);
     resliceAxes.SetElement(1, 3, center[1]);
     resliceAxes.SetElement(2, 3, center[2]);


     reslicer.SetOutputDimensionality(2);
     reslicer.SetInterpolationModeToLinear();
     reslicer.SetInput( image );
     reslicer.SetResliceAxes(resliceAxes);

     im.SetInput(image);
     im.set
     sliceFixedActor.SetMapper(im);

    sliceRenderer[i]->AddActor(sliceFixedActor[i]);
     sliceRenderer[i]->GetActiveCamera()->SetViewUp( 0, -1, 0 );
     sliceRenderer[i]->ResetCamera();
     sliceRenderWin[i]->Render();
    sliceRenderWindowInteractor[i]->Initialize();
   }*/
    }
    
  
}
