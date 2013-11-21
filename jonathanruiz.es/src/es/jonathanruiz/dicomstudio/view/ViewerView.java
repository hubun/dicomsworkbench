/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jonathanruiz.dicomstudio.view;

import es.jonathanruiz.dicomstudio.controller.DicomStudioMain;
import es.jonathanruiz.dicomstudio.controller.ViewerController;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vtk.vtk3DWidget;
import vtk.vtkAbstractWidget;
import vtk.vtkActor;
import vtk.vtkCamera;
import vtk.vtkCanvas;
import vtk.vtkInteractorStyleImage;
import vtk.vtkInteractorStyleTrackballActor;
import vtk.vtkMath;
import vtk.vtkProp;
import vtk.vtkRenderer;

/**
 *
 * @author jruiz
 */
public class ViewerView extends javax.swing.JPanel {

    /**
     * Creates new form vtkViewer
     */
    private vtkCanvas renWin = new vtkCanvas();
    
    private vtkInteractorStyleImage interactorStyleImage = new vtkInteractorStyleImage();
    private vtkInteractorStyleTrackballActor interactorStyleTrackballActor = new vtkInteractorStyleTrackballActor();
    private ViewerController viewerController = new ViewerController(this);
    private String viewerViewDescription;

    public String getViewerViewDescription() {
        return viewerViewDescription;
    }
    
    public ViewerView() {
        initComponents();
        setLayout(new BorderLayout());
        vtkCamera camera = renWin.GetRenderer().GetActiveCamera();
        camera.ParallelProjectionOn();
        interactorStyleImage.SetInteractionModeToImageSlicing();
        renWin.getRenderWindowInteractor().SetInteractorStyle(interactorStyleImage); 
        renWin.GetRenderer().SetBackground(1,1,1);
        add(renWin, BorderLayout.CENTER);        
        
        renWin.addMouseListener(new MouseListener() 
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                renWin.lock();
                viewerController.mouseClicked(e);
                renWin.unlock();
                renWin.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                renWin.lock();
                viewerController.mousePressed(e);
                renWin.unlock();        
                renWin.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                renWin.lock();
                viewerController.mouseReleased(e);
                renWin.unlock();
                renWin.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                renWin.lock();
                viewerController.mouseEntered(e);
                renWin.unlock();
                renWin.repaint();
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
                renWin.lock();
                viewerController.mouseExited(e);
                renWin.unlock();
                renWin.repaint();
                 
            }
                    
        });  
        
        /*
        cone.SetResolution(8);
        
        coneMapper.SetInput(cone.GetOutput());

        
        coneActor.SetMapper(coneMapper);

        renWin.GetRenderer().AddActor(coneActor);*/
        //AxesActor aa = new AxesActor(renWin.GetRenderer());
        //renWin.GetRenderer().AddActor(aa);
        renWin.getRenderWindowInteractor().AddObserver("EndInteractionEvent", this, "EndInteractionEvent");
        renWin.getRenderWindowInteractor().AddObserver("StartInteractionEvent", this, "StartInteractionEvent");  
        //renWin.getRenderWindowInteractor().AddObserver("InteractionEvent", this, "InteractionEvent");
       
       
        renWin.getRenderWindowInteractor().AddObserver("NoEvent", this, "NoEvent");
        renWin.getRenderWindowInteractor().AddObserver("Select", this, "Select");
        renWin.getRenderWindowInteractor().AddObserver("EndSelect", this, "EndSelect");
        renWin.getRenderWindowInteractor().AddObserver("Delete", this, "Delete");
        renWin.getRenderWindowInteractor().AddObserver("Translate", this, "Translate");
        renWin.getRenderWindowInteractor().AddObserver("EndTranslate", this, "EndTranslate");
        renWin.getRenderWindowInteractor().AddObserver("Scale", this, "Scale");
        renWin.getRenderWindowInteractor().AddObserver("EndScale", this, "EndScale");
        renWin.getRenderWindowInteractor().AddObserver("Resize", this, "Resize");
        renWin.getRenderWindowInteractor().AddObserver("EndResize", this, "EndResize");
        renWin.getRenderWindowInteractor().AddObserver("EndRotate", this, "EndRotate");
        renWin.getRenderWindowInteractor().AddObserver("Move", this, "Move");
        renWin.getRenderWindowInteractor().AddObserver("SizeHandles", this, "SizeHandles");
        renWin.getRenderWindowInteractor().AddObserver("AddPoint", this, "AddPoint");       
        renWin.getRenderWindowInteractor().AddObserver("AddFinalPoint", this, "AddFinalPoint");
        renWin.getRenderWindowInteractor().AddObserver("Completed", this, "Completed");
        renWin.getRenderWindowInteractor().AddObserver("TimedOut", this, "TimedOut");
        renWin.getRenderWindowInteractor().AddObserver("ModifyEvent", this, "ModifyEvent");
        renWin.getRenderWindowInteractor().AddObserver("Reset", this, "Reset");
        renWin.getRenderWindowInteractor().AddObserver("Up", this, "Up");
        renWin.getRenderWindowInteractor().AddObserver("Down", this, "Down");       
        renWin.getRenderWindowInteractor().AddObserver("Left", this, "Left");
        renWin.getRenderWindowInteractor().AddObserver("Right", this, "Right"); 


        renWin.getRenderWindowInteractor().AddObserver("LeftButtonPressEvent", this, "LeftButtonPressEvent");

        //renWin.getRenderWindowInteractor().AddObserver("MouseMoveEvent", this, "MouseMoveEvent");
    }
    
    public void addActor(vtkActor vtk_actor )
    {  
        renWin.lock();
        renWin.GetRenderer().AddActor(vtk_actor);
        renWin.unlock();
        renWin.repaint();
    }
    public void addViewProp(vtkProp vtk_prop )
    {  
        renWin.lock();
        renWin.GetRenderer().AddViewProp(vtk_prop);
        renWin.unlock();
        renWin.repaint();
        
    }
    public void add3DWidget(vtk3DWidget vtk_3d_widget )
    {  
        renWin.lock();
        vtk_3d_widget.SetDefaultRenderer(renWin.GetRenderer());
        vtk_3d_widget.SetInteractor(renWin.getRenderWindowInteractor());        
        //vtk_3d_widget.SetTexturePlaneProperty(ipwProp);			                
        vtk_3d_widget.On();
        //vtk_3d_widget.InteractionOff();        
        renWin.unlock();
    }
    public void addAbstractWidget(vtkAbstractWidget vtk_3d_widget )
    {  
        renWin.lock();
              
        renWin.unlock();
    }
    public vtkRenderer getRenderer()
    {
        return renWin.GetRenderer();
    }
    public void setAxialView()
    {
        viewerViewDescription = DicomStudioMain.AXIAL;
        vtkCamera camera = renWin.GetRenderer().GetActiveCamera();
        camera.SetPosition(0,0,1); 
        camera.SetViewUp(0, 1, 0);
        
        renWin.GetRenderer().ResetCamera();
    }
    public void setSagittalView()
    {
        viewerViewDescription = DicomStudioMain.SAGITTAL;
        vtkCamera camera = renWin.GetRenderer().GetActiveCamera();
        camera.SetPosition(1,0,0);
        camera.SetViewUp(0, 0, -1);
        renWin.GetRenderer().ResetCamera();
        
    }
    public void setCoronalView()
    {
        viewerViewDescription = DicomStudioMain.CORONAL;
        vtkCamera camera = renWin.GetRenderer().GetActiveCamera();
        camera.SetPosition(0,1,0);
        camera.SetViewUp(0, 0, -1);
        renWin.GetRenderer().ResetCamera();
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
  
    //public VtkPoint getPointerPosition(){}
    //public Vtk3DPoint get3DPointerPosition(){};
    //public void addWidget(Widget widget){};
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 150, 0), 2));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
