package org.questlab.motter.oclSolver;

//import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.uml2.uml.EnumerationLiteral;

import snt.oclsolver.datatypes.EnumerationValueTuple;
import snt.oclsolver.datatypes.PrimitiveValueTuple;
import snt.oclsolver.distance.ClassDiagramTestData;
import snt.oclsolver.distance.FitnessCalculator;
import snt.oclsolver.distance.Problem;
import snt.oclsolver.driver.SolverRunner;
import snt.oclsolver.multiplesolutiongenerationstrategies.ClauseCoverage;
import snt.oclsolver.multiplesolutiongenerationstrategies.CoverageStrategies;
import snt.oclsolver.multiplesolutiongenerationstrategies.PartitionCoverage;
import snt.oclsolver.reader.ObjScriptGenerator;
import snt.oclsolver.reader.PreprocessDataModel;
import snt.oclsolver.search.SearchAlgorithmEnum;
import snt.oclsolver.tuples.ClassifierTuple;
import snt.oclsolver.tuples.ClassifierValueTuple;
import snt.oclsolver.tuples.IPropertyTuple;
import snt.oclsolver.tuples.TempTuple;
import snt.oclsolver.tuples.ValueTuple;

public class OCLSolverTest {
	int noOfRuns = 1;
	int noOfIterations = 100;
	SearchAlgorithmEnum sa =SearchAlgorithmEnum.RANDOM;

	int noOfQueriesSolved = 0;
	private String individualType;
	private ArrayList<String> attributeNames=new ArrayList<String>();
	private String coverageCriteria;

	public ArrayList<ClassifierTuple> test(int noOfRuns, int noOfIterations, String query,
			SearchAlgorithmEnum sa, String umlFile,String objDiagramPath)
			{

		traceNewQuery();

		deleteAllFilesInDirectory(objDiagramPath);
		SolverRunner exp = new SolverRunner();	
//		readConfigFile();
		exp.setIndividualType("simple");
//		ClassDiagramTestData.umlFileURI=umlFile;
		ClassDiagramTestData.getInstance().reset(umlFile);
//		Problem.tempTuples=new ArrayList<TempTuple>();
//		Problem.ClassToPropMap=new HashMap<String, ArrayList<String>>();
//		PreprocessDataModel process = new PreprocessDataModel(umlFile);
//		String newFile = process.processUMLModel();/*"examples/ProcessedModel/modifiedModel.uml";*/
		try {
			ObjScriptGenerator driver =  new ObjScriptGenerator(umlFile);
			driver.generateScript();

			exp.setNoOfRuns(noOfRuns);
			exp.setObjDiagramPath(objDiagramPath);			
			ArrayList<ClassifierTuple> result = exp.getValues(query.trim(), noOfIterations,sa);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			//fail();

			return null;
		}

			}

	/**
	 * @param noOfRuns
	 * @param noOfIterations
	 * @param query
	 * @param sa
	 * @param umlFile
	 * @param objDiagramPath
	 * @param oldCTuples
	 * 
	 * {@code} An overloaded test method that takes old classifier tuples as extra argument
	 * 
	 * @return array list of classifier tuple
	 */
	public ArrayList<ClassifierTuple> test(int noOfRuns, int noOfIterations, String query,
			SearchAlgorithmEnum sa, String umlFile,String objDiagramPath,ArrayList<ClassifierTuple> oldCTuples)
			{

		traceNewQuery();

		deleteAllFilesInDirectory(objDiagramPath);
		SolverRunner exp = new SolverRunner();	
		readConfigFile();
		exp.setIndividualType(individualType);
		ClassDiagramTestData.getInstance().reset(umlFile);
		if(oldCTuples.size()==0)
		{
			Problem.tempTuples=new ArrayList<TempTuple>();
			Problem.ClassToPropMap=new HashMap<String, ArrayList<String>>();

		}
		try {
			ObjScriptGenerator driver =  new ObjScriptGenerator(umlFile);
			driver.generateScript();

			exp.setNoOfRuns(noOfRuns);
			exp.setObjDiagramPath(objDiagramPath);
			exp.setOldClassifierTuples(oldCTuples);
			ArrayList<ClassifierTuple> result = exp.getValues(query.trim(), noOfIterations,sa);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			//fail();

			return null;
		}

			}

	/**
	 * {@code} An overloaded test method that takes boolean parameter(genMultiSolutions) as extra argument
	 * 		 which decides whether to generate multiple solutions or not.
	 * 
	 * @param noOfRuns
	 * @param noOfIterations
	 * @param query
	 * @param sa
	 * @param umlFile
	 * @param objDiagramPath
	 * @param genMultiSolutions
	 * 
	 * @return array list of classifier tuple
	 */
	public ArrayList<ArrayList<ClassifierTuple>> test(int noOfRuns, int noOfIterations, String query,
			SearchAlgorithmEnum sa, String umlFile,String objDiagramPath,boolean genMultiSolutions)
			{

		traceNewQuery();

		ArrayList<ArrayList<ClassifierTuple>> result = new ArrayList<ArrayList<ClassifierTuple>>();
		deleteAllFilesInDirectory(objDiagramPath);
		ClassDiagramTestData.getInstance().reset(umlFile);
		Problem.tempTuples=new ArrayList<TempTuple>();
		Problem.ClassToPropMap=new HashMap<String, ArrayList<String>>();
		try {
			ObjScriptGenerator driver =  new ObjScriptGenerator(umlFile);
			readConfigFile();
			driver.generateScript();			
			if(genMultiSolutions){
				if(coverageCriteria.equals("clause")) {
					CoverageStrategies coverage = new ClauseCoverage(query.trim(), objDiagramPath, noOfRuns, noOfIterations, sa,attributeNames, individualType);
					result = coverage.generateMultipleSolutions();
				}
				else if(coverageCriteria.equals("partition")) {
					PartitionCoverage partition = new PartitionCoverage(query, objDiagramPath, noOfRuns, noOfIterations, sa, attributeNames,individualType);
					result = partition.generateMultipleSolutions();
				}
				//				MultipleSolutionGenerator msGen = new MultipleSolutionGenerator(query.trim(), objDiagramPath, noOfRuns, noOfIterations, sa,individualType);
				//				result = msGen.generateMultipleSolutions();
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			//fail();
			return null;
		}
			}

	private void traceNewQuery() {
		Logger logger = LogManager.getLogger();
		logger.info("\n********TEST******** Solving query No: "+noOfQueriesSolved+"\n");

		StackTraceElement callerStack = Thread.currentThread().getStackTrace()[3];
		logger.info("\n********TEST******** Calling method: "+callerStack.getFileName()+":"+callerStack.getLineNumber()+"\n");

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		logger.info("\n********TEST******** Time: "+dateFormat.format(date));

		noOfQueriesSolved++;
	}

	/*protected void test(int noOfRuns, int noOfIterations, String query,
			SearchAlgorithmEnum sa, String classDiagramFile, String objDiagramFile)
	{
	//	ClassDiagramTestData.setFileNames(classDiagramFile, objDiagramFile );
//		ClassDiagramTestData.getInstance().init();





		SolverRunner exp = new SolverRunner();
        exp.setNoOfRuns(noOfRuns);

           ArrayList<ClassifierTuple> result;
		try {
			result = exp.getValues(query, noOfIterations,sa);
		    if(result != null){

                for(int i=0; i<result.size(); i++){
                 //   String temp = result.get(i);
//                    if(temp.contains("NotOptimized")){
//                        System.out.println(temp);
//                    }
                }

            }
		} catch (Exception e) {
			e.printStackTrace();
			fail();

		}
	}*/


	protected void printResultTuple(ArrayList<ClassifierTuple> outputTuple) {
		ArrayList<ClassifierTuple> queryVariables = outputTuple;

		for (ClassifierTuple ct : queryVariables) {

			ArrayList<ClassifierValueTuple> cvts = ct.getObjectTuples();
			ArrayList<IPropertyTuple> propTup = ct.getValueTuples();
			for (ClassifierValueTuple cvt : cvts) {

				ArrayList<ValueTuple> vts = cvt.getAttributeValues();
				if (vts.size() == 0) {
					Object Class = ct.getClassName();
					Object obj = cvt.getObjectName();
					System.out.println(Class + " ");
					System.out.println(obj + " " + "\n");
					// System.out.println("\n");
				}
				for (ValueTuple vt : vts) {
					if (vt instanceof PrimitiveValueTuple) {
						Object val;
						if (propTup.contains(vt.getRelatedProperty())) {
							PrimitiveValueTuple ivt = (PrimitiveValueTuple) vt;
							if (ivt instanceof EnumerationValueTuple) {
								val = ((EnumerationLiteral) ivt.getValue()).getQualifiedName();
							} else {
								val = ivt.getValue();
							}
							Object attribute = ivt.getRelatedProperty().getRoleeName();
							Object type = ivt.getRelatedProperty().getType();
							Object obj = cvt.getObjectName();
							Object Class = ct.getClassName();

							System.out.println(Class + " ");
							System.out.println(obj + " ");
							System.out.println(type + " ");
							System.out.println(attribute + " ");
							System.out.println(val + " ");
							System.out.println("\n");
						}
					} else {

						if (propTup.contains(vt.getRelatedProperty())) {
							ClassifierValueTuple ivt = (ClassifierValueTuple) vt;
							Object val = ivt.getObjectName();
							Object attribute = ivt.getRelatedProperty().getRoleeName();
							Object type = ivt.getRelatedProperty().getType();
							Object obj = cvt.getObjectName();
							Object Class = ct.getClassName();

							System.out.println(Class + " ");
							System.out.println(obj + " ");
							System.out.println(type + " ");
							System.out.println(attribute + " ");
							System.out.println(val + " ");
							System.out.println("\n");

						}
					}
				}
			}
			System.out.println("\n");
		}
	}

	/**
	 * A Method to find out the solution of the query using ieos 
	 * object model by calling getResultfromValues function of FitnessCalculator.
	 * 
	 * 
	 * @param String query, contains the query specified in the test case.
	 * @param An ArrayList of Classifier Tuples; result, which contains the 
	 *        queryVariables.
	 * @return String str, it is either true or false depending
	 *         on the result we get after query evaluation
	 */


	public String verifyResult(String query,
			ArrayList<ClassifierTuple> result) {
		FitnessCalculator object = new FitnessCalculator(query, 1.1,result);
		String str = object.getResultfromValues();
		return str;
		// TODO Auto-generated method stub

	}

	/**
	 * {@code} A method that deletes previous object diagrams from directory.
	 * @param path
	 */
	private void deleteAllFilesInDirectory(String path){

		File file = new File(path);
		File[] files = file.listFiles(); 
		if(files != null && files.length > 0)
			for (File f:files) 
			{
				if (f.isFile() && f.exists()) 
				{ 
					f.delete();
					System.out.println("File successfully deleted");
				}else{
					System.out.println("Can not delete a file");
				}
			}
	}
	void readConfigFile() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			String currentPath=System.getProperty("user.dir");
			String filename = "/resources/app.config";
			input = new FileInputStream(currentPath+filename);


			// load a properties file from class path, inside static method
			prop.load(input);

			// get the property values
			Enumeration<?> e = prop.propertyNames();

			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				System.out.println(key + " -- " + prop.getProperty(key));
				if(key.equals("individual"))
				{
					individualType=prop.getProperty(key);
				}
				else if(key.contains("coverageType"))
				{
					coverageCriteria=prop.getProperty(key);
				}
				else if(key.contains("property"))
				{
					attributeNames.add(prop.getProperty(key));
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
