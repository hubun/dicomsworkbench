/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jonathanruiz.dicomstudio.model;

import vtk.*;

/**
 *
 * @author jruiz
 */
public class DicomImageMPR{
    
    protected vtkImagePlaneWidget imagePlaneWidgetAxial = new vtkImagePlaneWidget(); 
    protected vtkImagePlaneWidget imagePlaneWidgetSagittal = new vtkImagePlaneWidget(); 
    protected vtkImagePlaneWidget imagePlaneWidgetCoronal = new vtkImagePlaneWidget();
    
    double window = 3000;
    double level = 900;   
    
    public DicomImageMPR(){}
    
    public void setInput(vtkImageData input)
    { 
        //imagePlaneWidgetAxial.RestrictPlaneToVolumeOn();            
        imagePlaneWidgetAxial.SetInput(input);               
        imagePlaneWidgetAxial.SetPlaneOrientationToZAxes();    
        imagePlaneWidgetAxial.SetSliceIndex(input.GetDimensions()[0] / 2);       
        imagePlaneWidgetAxial.SetWindowLevel(window, level, 0);
        imagePlaneWidgetAxial.TextureInterpolateOn();
        imagePlaneWidgetAxial.SetResliceInterpolateToNearestNeighbour();
        imagePlaneWidgetAxial.DisplayTextOn();
        imagePlaneWidgetAxial.GetPlaneProperty().SetColor(1,0,0);
        imagePlaneWidgetAxial.TextureVisibilityOn();
                
        //imagePlaneWidgetSagittal.RestrictPlaneToVolumeOn();            
        imagePlaneWidgetSagittal.SetInput(input);
        imagePlaneWidgetSagittal.SetPlaneOrientationToXAxes();    
        imagePlaneWidgetSagittal.SetSliceIndex(input.GetDimensions()[0] / 2);
        imagePlaneWidgetSagittal.SetWindowLevel(window, level, 0);
        imagePlaneWidgetSagittal.TextureInterpolateOn();
        imagePlaneWidgetSagittal.SetResliceInterpolateToNearestNeighbour();
        imagePlaneWidgetSagittal.DisplayTextOn();
        imagePlaneWidgetSagittal.GetPlaneProperty().SetColor(0,1,0);
        imagePlaneWidgetAxial.TextureVisibilityOn();
        
        
        //imagePlaneWidgetCoronal.RestrictPlaneToVolumeOn();            
        imagePlaneWidgetCoronal.SetInput(input);
        imagePlaneWidgetCoronal.SetPlaneOrientationToYAxes();    
        imagePlaneWidgetCoronal.SetSliceIndex(input.GetDimensions()[0] / 2);
        imagePlaneWidgetCoronal.SetWindowLevel(window, level, 0);
        imagePlaneWidgetCoronal.TextureInterpolateOn();
        imagePlaneWidgetCoronal.SetResliceInterpolateToNearestNeighbour();
        imagePlaneWidgetCoronal.DisplayTextOn();
        imagePlaneWidgetCoronal.GetPlaneProperty().SetColor(0,0,1);
        imagePlaneWidgetAxial.TextureVisibilityOn();
    }           
    public vtkImagePlaneWidget getImagePlaneWidgetAxial() {
        return imagePlaneWidgetAxial;
    }
    public vtkImagePlaneWidget getImagePlaneWidgetCoronal() {
        return imagePlaneWidgetCoronal;
    }
    public vtkImagePlaneWidget getImagePlaneWidgetSagittal() {
        return imagePlaneWidgetSagittal;
    }    
}
