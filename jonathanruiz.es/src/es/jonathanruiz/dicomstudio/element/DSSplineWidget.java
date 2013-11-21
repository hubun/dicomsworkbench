/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jonathanruiz.dicomstudio.element;

import es.jonathanruiz.dicomstudio.controller.ViewerController;
import vtk.vtkCommand;
import vtk.vtkDistanceRepresentation2D;
import vtk.vtkDistanceWidget;
import vtk.vtkSplineRepresentation;
import vtk.vtkSplineWidget2;
import vtk.vtkWidgetEventTranslator;

/**
 *
 * @author jruiz
 */
public class DSSplineWidget extends vtkSplineWidget2{
   private ViewerController viewerController;
   private vtkSplineRepresentation splineRepresentation2D;
   public DSSplineWidget(ViewerController vc)
   {      
       super();
       viewerController = vc; 
       CreateDefaultRepresentation();
       splineRepresentation2D = (vtkSplineRepresentation) this.GetRepresentation();
       splineRepresentation2D.SetNumberOfHandles(10);
              
       //this.SetRepresentation(splineRepresentation2D);
       AddObserver("EndInteractionEvent", this, "checkWidgetState");
   }

    public vtkSplineRepresentation getSplineRepresentation2D() {
        return splineRepresentation2D;
    }
   
   public void checkWidgetState()
   {
       
       System.out.println("EndInteractionEvent" );       
       viewerController.endOfWidgetIteration();
       
   }
       

}
