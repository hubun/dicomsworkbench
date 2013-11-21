/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jonathanruiz.dicomstudio.model;

import vtk.vtkHandleRepresentation;
import vtk.vtkHandleWidget;
import vtk.vtkPointHandleRepresentation3D;
import vtk.vtkRenderWindowInteractor;
import vtk.vtkRenderWindowPanel;
import vtk.vtkSphereHandleRepresentation;

/**
 *
 * @author jruiz
 */
abstract class ViewerWidget 
{
    

    public ViewerWidget() 
    {}
    public void setRenderWindows(vtkRenderWindowPanel i_0, vtkRenderWindowPanel i_1, vtkRenderWindowPanel i_2, vtkRenderWindowPanel i_3)
    {}
    public void On(){}
    

    
}
