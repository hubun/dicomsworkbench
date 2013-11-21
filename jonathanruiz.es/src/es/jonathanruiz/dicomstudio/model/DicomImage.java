package es.jonathanruiz.dicomstudio.model;

import gdcm.FilenamesType;
import gdcm.IPPSorter;
import gdcm.PosixEmulation;
import java.io.File;
import vtk.*;


/**
 *
 * @author jruiz
 */
public class DicomImage {
    
    private FilenamesType fns;
    private vtkGDCMImageReader reader;    
    private vtkImageChangeInformation normalized = new vtkImageChangeInformation();
    
    private double[] dataCenter;    
    
    private int [] imageDimensions = new int [3];
    private double [] imageSize = new double [3];
    private double [] imageOrigin = new double [3];
    
    //vtkLookupTable colorTable = new vtkLookupTable();
     
    private String directoryName;
    private vtkImageData output;
   
    public DicomImage(){
    
    }
        
    public void setInput(String dirname) throws Exception
    {        
        directoryName = dirname;
        fns = new FilenamesType();
        reader = new vtkGDCMImageReader();   
        
        
        // Check if is directory or file...
        if( PosixEmulation.FileIsDirectory( directoryName ) )
        {
            System.out.println("DicomImage");
            read(directoryName);
            
            output = normalized.GetOutput();            
        }
        else
        {            
            throw new Exception("Provided dicom directory is not a directory");
        }
        System.out.println("OutputSpacing: " + output.GetSpacing()[0] + " "  + output.GetSpacing()[1] + " " + output.GetSpacing()[2] + " ");
    }
    
    public void setInput(vtkImageData input)
    {
        
        normalized.RemoveAllInputs();        
        normalized.SetInput(input);
        normalized.SetInformationInput(input);        
        normalized.CenterImageOn();
        normalized.Update();
        output = normalized.GetOutput();
        output.SetSpacing(input.GetSpacing());
        
        System.out.println("OutputSpacing: " + output.GetSpacing()[0] + " "  + output.GetSpacing()[1] + " " + output.GetSpacing()[2] + " ");
        
        imageDimensions[0] = input.GetExtent()[1] - input.GetExtent()[0];
        imageDimensions[1] = input.GetExtent()[3] - input.GetExtent()[2];
        imageDimensions[2] = input.GetExtent()[5] - input.GetExtent()[4];        
    }
    
    private void read(String dirname) throws Exception
    {        
       
        File dir = new File(dirname);        
        visitAllFiles(dir);

        IPPSorter ipp = new IPPSorter();
        ipp.SetComputeZSpacing( true );
        ipp.SetZSpacingTolerance( 1e-3 );
        boolean b = ipp.Sort( fns );
        if(!b)
        {
        //throw new Exception("Could not scan");
        }
        double ippzspacing = ipp.GetZSpacing();

        FilenamesType sorted = ipp.GetFilenames();
        vtkStringArray files = new vtkStringArray();
        long nfiles = sorted.size();
        //for( String f : sorted )
        for (int i = 1; i <= nfiles; i++) {
            String f = sorted.get((int)nfiles - i);
            files.InsertNextValue( f );
        }
        
        reader.SetFileNames( files );        
        reader.Update(); 
        
        double[] spacing = reader.GetOutput().GetSpacing();
        
        System.out.println("ReaderSpacing: " + spacing[0] + " "  + spacing[1] + " " + spacing[2] + " ");
        
        normalized.SetInputConnection( reader.GetOutputPort() );        
        
        imageSize[0] =  reader.GetOutput().GetDimensions()[0] * spacing[0];
        imageSize[1] =  reader.GetOutput().GetDimensions()[1] * spacing[1];
        imageSize[2] =  reader.GetOutput().GetDimensions()[2] * ippzspacing;
        imageDimensions = reader.GetOutput().GetDimensions();
        
        normalized.SetOutputSpacing( spacing[0], spacing[1], ippzspacing );       
        normalized.CenterImageOn();        
        normalized.Update();        
        imageOrigin = normalized.GetOutputOrigin();
        System.out.println("origin:" + imageOrigin[0] + " " +imageOrigin[1]+ " " +imageOrigin[2]);
        
    }
    
    public void process(String path)
    {
        fns.add( path );
    }
  // Process only files under dir
    public void visitAllFiles(File dir)
    {
        if (dir.isDirectory())
        {
        String[] children = dir.list();
        for (int i=0; i<children.length; i++)
            {
            visitAllFiles(new File(dir, children[i]));
            }
        }
        else
        {
        process(dir.getPath());
        }
    }
    /*
    public vtkImageChangeInformation getNormalized() {
        return normalized;
    }
    */
    public double[] getDataCenter()
    {
        return dataCenter;
    }   

    public double[] getImageOrigin() {
        return imageOrigin;
    }

    public double[] getImageSize() {
        return imageSize;
    }
    
    public vtkActor getSplineActor()
    {
        
        vtkPolyDataMapper mapper = new vtkPolyDataMapper();
        //mapper.SetInput(spline.GetOutput());
        vtkActor actor = new vtkActor();
        actor.SetMapper(mapper);
        
        return actor;
    }
    
    public int[] getImageDimensions() {
        return imageDimensions;
    }
    public DicomImage copy() throws CloneNotSupportedException
    {
        return (DicomImage)this.clone();
    }
    public vtkImageData getOutput()
    {
        return output;
        //return normalized.GetOutput();
    }
    
    public void SetOriginTranslation(double [] origin)
    {
        normalized.SetOriginTranslation(origin);
        normalized.Update();
    }
    
    public void rotate(double x, double y, double z) 
    {
        vtkTransform transformation = new vtkTransform();
        //transformation.PostMultiply();
        transformation.RotateX(x);
        transformation.RotateY(y);
        transformation.RotateZ(z);
       
        double image_spacing[] = normalized.GetOutputSpacing();
        double [] transformed_image_spacing = new double [3];
        //System.out.println(image_spacing[0] + " " + image_spacing[1] + " " +image_spacing[2] );
        
        transformed_image_spacing[0] = image_spacing[0];
        transformed_image_spacing[1] = image_spacing[1];
        transformed_image_spacing[2] = image_spacing[2];
              
        if(z != 360 && z!=0)
        {
            transformed_image_spacing[0] = Math.cos(Math.toRadians(z)) * image_spacing[0];
            transformed_image_spacing[1] = Math.cos(Math.toRadians(z)) * image_spacing[1];
        }
        if(y != 360 && y!=0)
        {
            transformed_image_spacing[0] = Math.cos(Math.toRadians(y)) * image_spacing[0];
            transformed_image_spacing[2] = Math.cos(Math.toRadians(y)) * image_spacing[2];
        }
        if(x != 360 && x!=0)
        {
            transformed_image_spacing[1] = Math.cos(Math.toRadians(x)) * image_spacing[1];
            transformed_image_spacing[2] = Math.cos(Math.toRadians(x)) * image_spacing[2];
        }
        
        //System.out.println(transformed_image_spacing[0] + " " + transformed_image_spacing[1] + " " +transformed_image_spacing[2] );
        
        vtkImageChangeInformation tmp_normalized = new vtkImageChangeInformation();
        tmp_normalized.SetInput(normalized.GetInput());
        tmp_normalized.SetOutputSpacing(transformed_image_spacing);
        tmp_normalized.SetInformationInput(normalized.GetInformationInput());
        tmp_normalized.CenterImageOn();
        tmp_normalized.Update();
        
        vtkImageReslice reslice = new vtkImageReslice();
        reslice.SetInput(tmp_normalized.GetOutput());
        reslice.SetInformationInput(tmp_normalized.GetOutput());
        reslice.SetResliceTransform(transformation);
        reslice.SetInterpolationModeToLinear();
        reslice.Update();
        
        setInput(reslice.GetOutput());
         
    }
    public vtkGDCMImageReader getReader() {
        return reader;
    }
    /*
    protected void finalize() throws Throwable {
        // Invoke the finalizer of our superclass
        // We haven't discussed superclasses or this syntax yet
        super.finalize();
        output.Delete();        
        reader.Delete();
        normalized.Delete();
       
    }*/
}





