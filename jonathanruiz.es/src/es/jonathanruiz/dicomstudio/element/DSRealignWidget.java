/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jonathanruiz.dicomstudio.element;

import es.jonathanruiz.dicomstudio.controller.DicomStudioMain;
import es.jonathanruiz.dicomstudio.controller.ViewerController;
import vtk.vtkLineRepresentation;
import vtk.vtkLineWidget2;
import vtk.vtkMath;
import vtk.vtkSplineRepresentation;

/**
 *
 * @author jruiz
 */
public class DSRealignWidget extends vtkLineWidget2
{
   private ViewerController viewerController;
   private vtkLineRepresentation lineRepresentation;
   private double[] worldPosition  = {0,0,0};
   public DSRealignWidget(ViewerController vc)
   {      
       super();
       viewerController = vc; 
       
       CreateDefaultRepresentation();
       lineRepresentation = (vtkLineRepresentation) GetRepresentation();     
              
       
       AddObserver("EndInteractionEvent", this, "EndInteractionEvent");
       AddObserver("StartInteractionEvent", this, "StartInteractionEvent");  
       AddObserver("InteractionEvent", this, "InteractionEvent");
       
       
       lineRepresentation.AddObserver("NoEvent", this, "NoEvent");
       lineRepresentation.AddObserver("Select", this, "Select");
       lineRepresentation.AddObserver("EndSelect", this, "EndSelect");
       lineRepresentation.AddObserver("Delete", this, "Delete");
       lineRepresentation.AddObserver("Translate", this, "Translate");
       lineRepresentation.AddObserver("EndTranslate", this, "EndTranslate");
       lineRepresentation.AddObserver("Scale", this, "Scale");
       lineRepresentation.AddObserver("EndScale", this, "EndScale");
       lineRepresentation.AddObserver("Resize", this, "Resize");
       lineRepresentation.AddObserver("EndResize", this, "EndResize");
       lineRepresentation.AddObserver("EndRotate", this, "EndRotate");
       lineRepresentation.AddObserver("Move", this, "Move");
       lineRepresentation.AddObserver("SizeHandles", this, "SizeHandles");
       lineRepresentation.AddObserver("AddPoint", this, "AddPoint");       
       lineRepresentation.AddObserver("AddFinalPoint", this, "AddFinalPoint");
       lineRepresentation.AddObserver("Completed", this, "Completed");
       lineRepresentation.AddObserver("TimedOut", this, "TimedOut");
       lineRepresentation.AddObserver("ModifyEvent", this, "ModifyEvent");
       lineRepresentation.AddObserver("Reset", this, "Reset");
       lineRepresentation.AddObserver("Up", this, "Up");
       lineRepresentation.AddObserver("Down", this, "Down");       
       lineRepresentation.AddObserver("Left", this, "Left");
       lineRepresentation.AddObserver("Right", this, "Right"); 
       
       
       lineRepresentation.AddObserver("LeftButtonPressEvent", this, "LeftButtonPressEvent");
       
       lineRepresentation.AddObserver("MouseMoveEvent", this, "MouseMoveEvent");
       
       
   }

    public void setWorldPosition(double[] worldPosition) {
        this.worldPosition = worldPosition;
        lineRepresentation.SetPoint1WorldPosition(worldPosition);
        lineRepresentation.SetPoint2WorldPosition(worldPosition);        
    }
   
   
   public vtkLineRepresentation getLineRepresentation() {
        return lineRepresentation;
   }
   public void EndScale()
   {       
       System.out.println("EndScale" );       
         
   }
   public void InteractionEvent()
   {       
       System.out.println("InteractionEvent" );       
         
   }
    public void LeftButtonPressEvent()
   {       
       System.out.println("LeftButtonPressEvent" );       
         
   }
   public void MouseMoveEvent()
   {       
       System.out.println("MouseMoveEvent" );       
         
   }
   public void LeftButtonReleaseEvent()
   {       
       System.out.println("LeftButtonReleaseEvent" );       
         
   }
   
   public void EndInteractionEvent()
   {       
        System.out.println("EndInteractionEvent" ); 
        double [] point_1 = getLineRepresentation().GetPoint1WorldPosition();
        double [] point_2 = getLineRepresentation().GetPoint2WorldPosition();
        double [] line_vector = point_2;
        line_vector[0] = line_vector[0]-point_1[0];
        line_vector[1] = line_vector[1]-point_1[1];
        line_vector[2] = line_vector[2]-point_1[2];
        
        new vtkMath().Normalize( line_vector );
        double [] unit_vector = new double [3];
        double proyection[] = new double [3];
        double angle;
        switch(viewerController.view.getViewerViewDescription())
        {
            case DicomStudioMain.AXIAL:
                System.out.println("Axial");
                unit_vector[0] = 1;
                unit_vector[1] = 0;
                unit_vector[2] = 0;
               
                //Get rotation over X axis of new Z axis
                proyection[0] = line_vector[0];
                proyection[1] = line_vector[1];
                proyection[2] = 0;
                new vtkMath().Normalize( proyection );
                angle = (Math.toDegrees(Math.acos(new vtkMath().Dot(unit_vector, proyection ))));                
                DicomStudioMain.imageModel.rotate(0, 0, -angle);
            break;
            case DicomStudioMain.SAGITTAL:
                System.out.println("Sagittal");
                unit_vector[0] = 0;
                unit_vector[1] = 1;
                unit_vector[2] = 0;                         
                
                //Get rotation over X axis of new Z axis
                proyection[0] = 0;
                proyection[1] = line_vector[1];
                proyection[2] = line_vector[2];
                new vtkMath().Normalize( proyection );
                angle = (Math.toDegrees(Math.acos(new vtkMath().Dot(unit_vector, proyection ))));                
                DicomStudioMain.imageModel.rotate(angle, 0, 0);
                
           break;
           case DicomStudioMain.CORONAL:
               System.out.println("Coronal");
                unit_vector[0] = 1;
                unit_vector[1] = 0;
                unit_vector[2] = 0;                         

                //Get rotation over X axis of new Z axis
                proyection[0] = line_vector[0];
                proyection[1] = 0;
                proyection[2] = line_vector[2];
                new vtkMath().Normalize( proyection );
                angle = (Math.toDegrees(Math.acos(new vtkMath().Dot(unit_vector, proyection ))));                
                DicomStudioMain.imageModel.rotate(0, angle, 0);
           break;
       }
        this.Off();
        viewerController.endOfWidgetIteration();
   }
  
   public void StartInteractionEvent()
   {       
       System.out.println("StartInteractionEvent" );       
         
   }
    public void NoEvent()
   {       
       System.out.println("NoEvent" );       
         
   }
     public void Select()
   {       
       System.out.println("Select" );       
         
   }
      public void EndSelect()
   {       
       System.out.println("EndSelect" );       
         
   }
       public void Delete()
   {       
       System.out.println("Delete" );       
         
   }
        public void Translate()
   {       
       System.out.println("Translate" );       
         
   }
         public void EndTranslate()
   {       
       System.out.println("EndTranslate" );       
         
   }
          public void Scale()
   {       
       System.out.println("Scale" );       
         
   }
           public void Resize()
   {       
       System.out.println("Resize" );       
         
   }
            public void EndResize()
   {       
       System.out.println("EndResize" );       
         
   }
             public void EndRotate()
   {       
       System.out.println("EndRotate" );       
         
   }
              public void Move()
   {       
       System.out.println("Move" );       
         
   }
               public void SizeHandles()
   {       
       System.out.println("SizeHandles" );       
         
   }
                public void AddPoint()
   {       
       System.out.println("AddPoint" );       
         
   }
                public void AddFinalPoint()
   {       
       System.out.println("AddFinalPoint" );       
         
   }
                public void Completed()
   {       
       System.out.println("Completed" );       
         
   }
                public void TimedOut()
   {       
       System.out.println("TimedOut" );       
         
   }public void ModifyEvent()
   {       
       System.out.println("ModifyEvent" );       
         
   }
   public void Reset()
   {       
       System.out.println("Reset" );       
         
   }public void Up()
   {       
       System.out.println("Up" );       
         
   }
   public void Down()
   {       
       System.out.println("Down" );       
         
   }
   public void Left()
   {       
       System.out.println("Left" );       
         
   }
   public void Right()
   {       
       System.out.println("Right" );       
         
   }
  
   
   
                
}
