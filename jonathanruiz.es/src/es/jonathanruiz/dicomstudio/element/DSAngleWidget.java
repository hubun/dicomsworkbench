/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jonathanruiz.dicomstudio.element;

import es.jonathanruiz.dicomstudio.controller.ViewerController;
import vtk.vtkAngleRepresentation2D;
import vtk.vtkAngleWidget;
import vtk.vtkDistanceRepresentation2D;

/**
 *
 * @author jruiz
 */
public class DSAngleWidget extends vtkAngleWidget{
    ViewerController viewerController;
    vtkAngleRepresentation2D vtkAngleRepresentation2D = new vtkAngleRepresentation2D();
    public DSAngleWidget(ViewerController vc)
    {
        viewerController = vc;
        vtkAngleRepresentation2D angleRepresentation2D = new vtkAngleRepresentation2D();
        angleRepresentation2D.InstantiateHandleRepresentation();
        //angleRepresentation2D.SetRenderer(view.getRenderer());
      /*
                    angleRepresentation2D.SetLabelFormat("%-#7.3lf");

                    angleRepresentation2D.GetRay1().AutoLabelOn();
                    angleRepresentation2D.GetRay1().SetLabelFormat("%-#7.1lf");
                    angleRepresentation2D.GetRay2().AutoLabelOn();
                    angleRepresentation2D.GetRay2().SetLabelFormat("%-#7.1lf");

                    angleRepresentation2D.GetRay1().GetLabelTextProperty().SetColor(1, 0, 0);
                    angleRepresentation2D.GetRay1().GetLabelTextProperty().ItalicOff();
                    angleRepresentation2D.GetRay1().GetLabelTextProperty().ShadowOn();
                    angleRepresentation2D.GetRay2().GetLabelTextProperty().SetColor(1, 0, 0);
                    angleRepresentation2D.GetRay2().GetLabelTextProperty().ItalicOff();
                    angleRepresentation2D.GetRay2().GetLabelTextProperty().ShadowOn();

                    angleRepresentation2D.GetRay1().GetProperty().SetColor(0,0,1);
                    angleRepresentation2D.GetRay1().GetProperty().SetLineWidth(2);
                    angleRepresentation2D.GetRay2().GetProperty().SetColor(0,0,1);
                    angleRepresentation2D.GetRay2().GetProperty().SetLineWidth(2);

                    angleRepresentation2D.GetArc().GetProperty().SetColor(0,0,1);
                    angleRepresentation2D.GetArc().GetProperty().SetLineWidth(2);
                    */
        
        SetRepresentation(vtkAngleRepresentation2D);
        AddObserver("EndInteractionEvent", this, "checkWidgetState");
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
