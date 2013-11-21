/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jonathanruiz.dicomstudio.element;

import es.jonathanruiz.dicomstudio.controller.ViewerController;
import vtk.vtkCommand;
import vtk.vtkDistanceRepresentation2D;
import vtk.vtkDistanceWidget;
import vtk.vtkWidgetEventTranslator;

/**
 *
 * @author jruiz
 */
public class DSdistanceWidget extends vtkDistanceWidget{
    ViewerController viewerController;
   vtkDistanceRepresentation2D distanceRepresentation2D = new vtkDistanceRepresentation2D();
   public DSdistanceWidget(ViewerController vc)
   {
       /*
        vtkWidgetEventTranslator eventTranslator = this.GetEventTranslator();
        eventTranslator.RemoveTranslation( new vtkCommand().GetEventIdFromString("EndInteractionEvent") );
        
        eventTranslator.SetTranslation(
      vtkCommand::KeyPressEvent,
      vtkEvent::NoModifier, 103, 0, "g",
      vtkWidgetEvent::AddFinalPoint );
       */
       
       viewerController = vc;
       distanceRepresentation2D.InstantiateHandleRepresentation();
       distanceRepresentation2D.SetLabelFormat("%-#7.3lf");
       this.SetRepresentation(distanceRepresentation2D);
       this.AddObserver("EndInteractionEvent", this, "checkWidgetState");
   } 
   public void checkWidgetState()
   {
       System.out.println("check widget state:" + GetWidgetState());       
       if(GetWidgetState()== 2)
       {
           viewerController.endOfWidgetIteration();
       }
   }
       

}
