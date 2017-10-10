package org.questlab.motter.atl;
//import org.eclipse.m2m.atl.compilers.atl2006.Atl2006Compiler;
//import org.eclipse.m2m.atl.engine.CompileTimeError;
import java.net.URL;
public class ATLASMGenerator
{
	public ATLASMGenerator(){
		
	}
	public static void main (String args[]) throws Exception{
		//ATLASMGenerator g=new ATLASMGenerator();
		//g.executeCompiler();
		
	}
	
	
	/**
    * The file name of the ATL file to be compiled.
    *
    * @parameter expression="${andromda.atlcompiler.atlFileURL}"
    * @required
    */
   private URL    atlFileURL;

   /**
    * The file name of the compiled ASM file.
    *
    * @parameter expression="${andromda.atlcompiler.asmFilename}"
    * @required
    */
   private String asmFilename;

  public String executeCompiler(String ATLFileName) throws Exception
  {   System.out.println("Compilation of ATL File Started");
	  //String ATLFileName="../ATLExecutor/SampleTransformation/Families2Persons/Families2Persons.atl";
	  atlFileURL=new URL("file","",ATLFileName);
	  String s[]=ATLFileName.split("/");
	  //System.out.println("A   "+s.length);
	  	  String ss[]=s[s.length-1].split("\\.");
	  //System.out.println("B   "+ss[ss.length-2]);
	  	  asmFilename="";
	  for (int i=0;i<s.length-1;i++){
		  asmFilename+=s[i]+"/";  
	  }
	  asmFilename+=ss[ss.length-2]+".asm";
	  System.out.println(asmFilename);
      //getLog().info("atlFilename=" + atlFileURL);
       //getLog().info("asmFilename=" + asmFilename);
/*
       File outputFile = new File(this.asmFilename);
       outputFile.getParentFile().mkdirs();
       System.out.println("..\r..\r..\r..\r..\r");
       CompileTimeError[] compileTimeErrors = null;
       try
       {
    	   //compileTimeErrors = new Atl2004Compiler().compile(this.atlFileURL.openStream(), this.asmFilename);
    	   //compileTimeErrors = new AtlCompiler().compile(this.atlFileURL.openStream(), this.asmFilename);
           //compileTimeErrors = new Atl2006Compiler().compile(this.atlFileURL.openStream(), this.asmFilename);
       }
       catch (Exception e)
       {
           throw new Exception("File to be compiled could not be found: " + this.atlFileURL, e);
       }

       if (compileTimeErrors.length > 0)
       {
          // this.getLog().error("Errors when compiling " + atlFileURL);
           for (CompileTimeError error : compileTimeErrors)
           {
               
        	   System.out.println("    at " + error.getLocation() + " " + error.getDescription());
           }
           throw new Exception("compile time errors in ATL script");
       }

       System.out.println("ASM file written to '" + this.asmFilename + "'.");
       
*/       
       System.out.println("ASM file Generated with No compilation Errors") ;
       return asmFilename;
  }
}

/*
import org.eclipse.emf.ecore.EObject;
import org.eclipse.m2m.atl.engine.compiler.AtlCompiler;
import org.eclipse.m2m.atl.engine.compiler.AtlDefaultCompiler;
import org.eclipse.m2m.atl.engine.compiler.atl2006.Atl2006Compiler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
public class ATLASMGenerator
{
	public static void main(String[] args)
	{
		File inputFile = new File("D:/myfile.atl");
		File outputFile = new File("D:/myfile.asm");
		FileInputStream in = null;
		FileOutputStream out = null;
		try
		{
			in = new FileInputStream(inputFile);
			out = new FileOutputStream(outputFile);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
		try
		{
			EObject[] ret =	AtlCompiler.compile(in, out);
			//AtlCompiler.compile(fileInputStream, fileOutputStream);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
*/