/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jonathanruiz.dicomstudio.controller;

import es.jonathanruiz.dicomstudio.element.DSAngleWidget;
import es.jonathanruiz.dicomstudio.element.DSRealignWidget;
import es.jonathanruiz.dicomstudio.element.DSSplineWidget;
import es.jonathanruiz.dicomstudio.element.DSdistanceWidget;

import es.jonathanruiz.dicomstudio.view.ViewerView;
import java.awt.event.MouseEvent;
import vtk.vtkAngleRepresentation2D;
import vtk.vtkAngleWidget;
import vtk.vtkCellPicker;
import vtk.vtkDistanceRepresentation2D;
import vtk.vtkDistanceWidget;
import vtk.vtkSplineRepresentation;
import vtk.vtkSplineWidget2;

/**
 *
 * @author jruiz
 */
public class ViewerController {
    public ViewerView view ;
    private boolean widgetIteration = false;
    private boolean ignoreMouseEvents = false;
    /**
     *
     * @param v
     */
    public ViewerController(ViewerView v)
    {
        view = v;
    }
    public void listenMouseEventsOn()
    {
        ignoreMouseEvents = false;
    }
    public void listenMouseEventsOff()
    {
        ignoreMouseEvents = true;
    }
    public void startOfWidgetIteration()
    {
        widgetIteration = true;
    }
    public void endOfWidgetIteration()
    {
        widgetIteration = false;
    }
    
    
    public void mouseClicked(MouseEvent e) {     
        
        if (ignoreMouseEvents)
        {
            System.out.println("[mouse events ignored]");
        }
        else
        {
            System.out.println("[mouse events catched]");
            startOfWidgetIteration();            
            int [] position = view.getRenderer().GetRenderWindow().GetInteractor().GetEventPosition();
            vtkCellPicker vtkCellPicker = new vtkCellPicker();
            vtkCellPicker.SetTolerance(0.0005);  
            vtkCellPicker.Pick(position[0], position[1], 0, view.getRenderer());
            double [] worldPosition = vtkCellPicker.GetPickPosition();
            if(DicomStudioMain.toolbarController.getViewerActiveActionCommand()!=null)
            {
                switch(DicomStudioMain.toolbarController.getViewerActiveActionCommand())
                {
                    case "angleWidget":                    
                        DSAngleWidget angleWidget = new DSAngleWidget(this);
                        angleWidget.SetInteractor(view.getRenderer().GetRenderWindow().GetInteractor());                    
                        //((vtkAngleRepresentation2D) angleWidget.GetRepresentation()).BuildRepresentation();
                        angleWidget.On();

                        //angleWidget.InvokeEvent("PlacePointEvent");
                        //InvokeEvent("AddPoint");
                    break;
                    case "distanceWidget":                  

                            DSdistanceWidget distanceWidget = new DSdistanceWidget(this);
                            distanceWidget.SetInteractor(view.getRenderer().GetRenderWindow().GetInteractor());
                            distanceWidget.On();                               

                    break;
                    case "realignWidget":                  

                            DSRealignWidget realignWidget = new DSRealignWidget(this);
                            realignWidget.SetInteractor(view.getRenderer().GetRenderWindow().GetInteractor());
                            realignWidget.setWorldPosition(worldPosition);
                            realignWidget.On();  
                    break;
                    case "newOriginWidget":                  
                        DicomStudioMain.imageModel.SetOriginTranslation(worldPosition);
                        endOfWidgetIteration();
                    break;
                    case "splineWidget":  
                        System.out.println("splineWidget");         

                        DSSplineWidget splineWidget = new DSSplineWidget(this);                    
                        splineWidget.SetInteractor(view.getRenderer().GetRenderWindow().GetInteractor());
                        splineWidget.getSplineRepresentation2D().SetHandlePosition(0, worldPosition[0] - 50 ,worldPosition[1],worldPosition[2]);
                        splineWidget.getSplineRepresentation2D().SetHandlePosition(1, worldPosition[0] - 40 ,worldPosition[1],worldPosition[2]);
                        splineWidget.getSplineRepresentation2D().SetHandlePosition(2, worldPosition[0] - 30 ,worldPosition[1],worldPosition[2]);
                        splineWidget.getSplineRepresentation2D().SetHandlePosition(3, worldPosition[0] - 20 ,worldPosition[1],worldPosition[2]);
                        splineWidget.getSplineRepresentation2D().SetHandlePosition(4, worldPosition[0] - 10 ,worldPosition[1],worldPosition[2]);
                        splineWidget.getSplineRepresentation2D().SetHandlePosition(5, worldPosition[0] ,worldPosition[1],worldPosition[2]);
                        splineWidget.getSplineRepresentation2D().SetHandlePosition(6, worldPosition[0] + 10 ,worldPosition[1],worldPosition[2]);
                        splineWidget.getSplineRepresentation2D().SetHandlePosition(7, worldPosition[0] + 20 ,worldPosition[1],worldPosition[2]);
                        splineWidget.getSplineRepresentation2D().SetHandlePosition(8, worldPosition[0] + 30 ,worldPosition[1],worldPosition[2]);
                        splineWidget.getSplineRepresentation2D().SetHandlePosition(9, worldPosition[0] + 40 ,worldPosition[1],worldPosition[2]);
                        splineWidget.On();                    

                    break;
                    default:
                      // Get the location of the click (in window coordinates)

                        System.out.println("Cell id is: " + vtkCellPicker.GetCellId());
                        System.out.println("World position: " + worldPosition[0] + " " +  worldPosition[1] + " " + worldPosition[2]);

                        //PointView point = new PointView();
                        //point.setPosition(worldPosition);
                        //DicomStudioMain.containerView.addActor(point.getPointActor());   

                }
            }
        }
        if (widgetIteration)
        {
            listenMouseEventsOff();
        }
        else
        {
            listenMouseEventsOn();
        }
       
        
    }       
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    } 
}
