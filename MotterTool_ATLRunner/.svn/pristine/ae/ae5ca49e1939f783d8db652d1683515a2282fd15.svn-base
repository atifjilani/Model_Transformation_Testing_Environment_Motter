package questlab.motter.atl.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import questlab.motter.atl.parser.metamodel.CalledRule;
import questlab.motter.atl.parser.metamodel.Element;
import questlab.motter.atl.parser.metamodel.Helper;
import questlab.motter.atl.parser.metamodel.LazyMatchedRule;
import questlab.motter.atl.parser.metamodel.MatchedRule;
import questlab.motter.atl.parser.metamodel.Statement;

public class Generator {

	protected static String FilePath = null;

	public Generator(String FilePath) {
		Generator.FilePath = FilePath;
	}

	static String filterString(String line) {

		int offset = line.indexOf("--");

		if (-1 != offset) {
			line = line.substring(0, offset);
		}
		return line;
	}

	public static ArrayList<Helper> extractAllHelpers() throws Exception {

		ArrayList<Helper> HelperObjectsList = new ArrayList<Helper>();

		String line = null;
		boolean endFound = false;
		BufferedReader readerBuf = new BufferedReader(new FileReader(FilePath));
		String tempName = null;

		while ((line = readerBuf.readLine()) != null) {
			// skip comment and rules
			if (line.contains("--") || line.contains("rule"))
				continue;
			// read helper
			if (line.contains("helper")) {
				Helper helperObj = new Helper();
				if (line.contains(":")) {
					ArrayList<String> helperArgs = new ArrayList<String>();
					// extract name of helper
					if (line.substring(line.indexOf(":"), line.lastIndexOf(":")) != null) {
						tempName = line.substring(line.indexOf(":") + 1,
								line.lastIndexOf(":")).trim();
						helperObj.setHelperName(tempName);

						// System.out.println("\n\n\nHelper Name : "+tempName);
						int last = 0, start = 0;
						if (tempName.contains("(") && tempName.contains(")")) {
							start = tempName.indexOf("(");
							last = tempName.indexOf(",");
							if (last == -1)
								last = tempName.indexOf(")");
							while (start != last) {
								helperArgs.add(tempName.substring(start + 1,
										last).trim());
								start = last;
								last = tempName.indexOf(",");
								if (last == -1)
									last = tempName.indexOf(")");
							}

							helperObj.setHelperAruguments(helperArgs);
							// System.out.println("Arguments : ");
							for (@SuppressWarnings("unused") String argStr : helperArgs) {
								// System.out.println(argStr);
							}
						}
					}
					// extract return type of helper
					if (line.substring(line.lastIndexOf(":"),
							line.lastIndexOf("=")) != null) {
						String returnType = line.substring(
								line.lastIndexOf(":") + 1,
								line.lastIndexOf("=")).trim();
						helperObj.setHelperReturnType(returnType);
						// System.out.println("Return Type : "+returnType);
					}
					// extract context of helper
					if (line.contains("context")) {
						helperObj.setHelperContext(line.substring(
								line.indexOf("context") + 7,
								line.lastIndexOf("def")).trim());
					}
				}
				ArrayList<String> helperblock = new ArrayList<String>();
				if (line.contains("=")) {
					helperblock.add(line.substring(line.lastIndexOf(":") + 1)
							.trim());
				} else {
					helperblock.add(line);
				}

				if (line.contains(";") && !line.contains("iterate")) {
					helperObj.setHelperStatements(helperblock);
					HelperObjectsList.add(helperObj);
					continue;
				}
				do {
					endFound = false;
					line = readerBuf.readLine();
					// skip comment and rule
					if (line.contains("--") || line.contains("rule"))
						continue;

					if (line.contains(";") && !line.contains("iterate")) {
						endFound = true;
					}
					helperblock.add(line);

				} while (!endFound);

				helperObj.setHelperStatements(helperblock);
				HelperObjectsList.add(helperObj);
			}
		}
		return HelperObjectsList;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<MatchedRule> extractAllMatchedRules(Collection<EObject> roots)
			throws IOException {
		ArrayList<MatchedRule> myRulesArray = new ArrayList<MatchedRule>();

		int count = roots.size();
		Iterator<EObject> itr = roots.iterator();
		EObject retUnit = null;

		while (count > 0) {
			count--;
			retUnit = itr.next();

			if ("LazyMatchedRule".equals(retUnit.eClass().getName())) {

				continue;
			}
			// for input type

			// System.out.println("\n\n\nMatchedRule Name: "+eGet(retUnit,
			// "name").toString());
			MatchedRule ruleObj = new MatchedRule();

			String RuleName = eGet(retUnit, "name").toString();
			ruleObj.setName(RuleName);
			EObject inputPattern = (EObject) eGet(retUnit, "inPattern");

			ruleObj.setType("MatchedRule");

			EList<EObject> elements = (EList<EObject>) eGet(inputPattern, "elements");

			ArrayList<Element> InPatternElements = new ArrayList<Element>();
			InPatternParseMatchedRule(elements, InPatternElements);

			// for output type
			EObject outPattern = (EObject) eGet(retUnit, "outPattern");

			elements = (EList<EObject>) eGet(outPattern, "elements");

			ArrayList<Element> OutPatternElements = new ArrayList<Element>();
			OutPatternParseMatchedRule(elements, OutPatternElements);

			ruleObj.setElements(InPatternElements, OutPatternElements);
			ExtractMatchedRuleBody(InPatternElements, OutPatternElements,
					ruleObj);

			// get body of the actionBlock mean do block
			ArrayList<String> ActionBlockStatements = new ArrayList<String>();
			// ExtractActionBlockBody(ActionBlockStatements,RuleName);

			ruleObj.SetActionBlock(ActionBlockStatements);

			myRulesArray.add(ruleObj);
		}
		return myRulesArray;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<LazyMatchedRule> extractAllLazyMatchedRules(
			Collection<EObject> roots) throws IOException {

		ArrayList<LazyMatchedRule> myRulesArray = new ArrayList<LazyMatchedRule>();

		int count = roots.size();
		Iterator<EObject> itr = roots.iterator();
		EObject retUnit = null;

		while (count > 0) {

			count--;
			retUnit = itr.next();
			// for input type
			// System.out.println("\n\n\nLazyMatchedRule Name: "+eGet(retUnit,
			// "name").toString());

			LazyMatchedRule ruleObj = new LazyMatchedRule();

			ruleObj.setName(eGet(retUnit, "name").toString());

			EObject inputPattern = (EObject) eGet(retUnit, "inPattern");

			ruleObj.setType("LazyMatchedRule");
			EList<EObject> elements = (EList<EObject>) eGet(inputPattern, "elements");
			ArrayList<Element> InPatternElements = new ArrayList<Element>();
			InPatternParseLazyMatchedRule(elements, InPatternElements);

			EObject outPattern = (EObject) eGet(retUnit, "outPattern");
			elements = (EList<EObject>) eGet(outPattern, "elements");

			ArrayList<Element> OutPatternElements = new ArrayList<Element>();
			OutPatternParseLazyMatchedRule(elements, OutPatternElements);

			ruleObj.setElements(InPatternElements, OutPatternElements);
			ExtractLazyMatchedRuleBody(InPatternElements, OutPatternElements,
					ruleObj);

			// get body of the actionBlock mean do block
			ArrayList<String> ActionBlockStatements = new ArrayList<String>();
			// ExtractActionBlockBody(ActionBlockStatements,RuleName);

			ruleObj.SetActionBlock(ActionBlockStatements);

			myRulesArray.add(ruleObj);

		}

		return myRulesArray;
	}

	static boolean ExtractMatchedRuleBody(ArrayList<Element> In,
			ArrayList<Element> Out, MatchedRule ruleObj) throws IOException {
		// In Array for in pattern elements
		// Out Array for out pattern elements

		BufferedReader br = new BufferedReader(new FileReader(FilePath));
		String line;

		// FileWriter Rule = new FileWriter("../Rules.txt");
		// FileWriter From = new FileWriter("../InPatternElements.txt");
		// FileWriter To = new FileWriter("../OutPatternElements.txt");
		// System.out.println("\nMatched Rule name: " + ruleObj.name);
		int i = 0, k = 0;
		// i used for FROM
		// k used for TO
		int openBracket = 0;
		int closeBracket = 0;
		int openCurl = 0;
		int closeCurl = 0;
		boolean ruleStart = false;
		boolean inRuleStart = false;
		boolean outRuleStart = false;
		boolean fromStart = false;
		boolean toStart = false;
		while ((line = br.readLine()) != null) {
			// process the line.

			line = filterString(line);
			if (line.contains("{") && ruleStart) {
				openCurl++;
			}

			if (line.contains("}") && ruleStart) {
				closeCurl++;
			}

			if (line.contains("(") && ruleStart) {
				openBracket++;
			}

			if (line.contains(")") && ruleStart) {
				closeBracket++;
			}

			if (line.contains("from") && ruleStart) {
				fromStart = true;
			}

			if (line.contains("to") && ruleStart) {
				toStart = true;
				fromStart = false;
			}

			if (inRuleStart && ruleStart) {
				/*
				 * if(line.contains("using")) { usingFlag = true; }
				 */
				if ((line.contains(")") && openBracket == closeBracket && openCurl == closeCurl)
						|| line.contains("to")) {
					inRuleStart = false;
					fromStart = false;
					continue;
				}
				// From.write(line + newLine);

				if (i <= ruleObj.InPattern.size()) {
					Element obj = new Element();
					obj = ruleObj.InPattern.get(i - 1);
					obj.body.append(line + System.getProperty("line.separator"));
					Statement Stat = new Statement();
					Stat.value.append(line);
					Stat.isGeneral = true;
					obj.Statements.add(Stat);
					// System.out.println(line +
					// System.getProperty("line.separator"));
					ruleObj.InPattern.set(i - 1, obj);
				}

			}

			if (outRuleStart && ruleStart) {
				if (line.contains(")") && openBracket == closeBracket) {
					outRuleStart = false;
					continue;
				}

				// To.write(line + newLine);
				// System.out.println(ruleObj.OutPatternElements.size());
				if (k <= ruleObj.OutPatternElements.size()) {
					// System.out.println(k +
					// System.getProperty("line.separator"));
					Element obj;
					obj = ruleObj.OutPatternElements.get(k - 1);
					obj.body.append(line + System.getProperty("line.separator"));
					Statement Stat = new Statement();
					Stat.value.append(line);
					Stat.isGeneral = true;
					obj.Statements.add(Stat);
					// System.out.println(line +
					// System.getProperty("line.separator"));
					ruleObj.OutPatternElements.set(k - 1, obj);
				}
			}

			if (fromStart == true && i < In.size() && ruleStart) {
				String input = In.get(i).VariableName + " :";
				if (line.contains(input)) {
					inRuleStart = true;
					i++;
					continue;
				}
			}

			if (toStart == true && k < Out.size() && ruleStart) {
				String output = Out.get(k).VariableName + " :";
				if (line.contains(output)) {
					outRuleStart = true;
					k++;
					continue;
				}
			}

			if (line.contains("rule " + ruleObj.name)) {
				ruleStart = true;
				continue;
			}

		}

		br.close();

		return false;
	}

	static boolean ExtractCalledRuleBody(ArrayList<Element> Out,
			CalledRule ruleObj) throws IOException {
		// In Array for in pattern elements
		// Out Array for out pattern elements

		BufferedReader br = new BufferedReader(new FileReader(FilePath));
		String line;

		// FileWriter Rule = new FileWriter("../Rules.txt");
		// FileWriter From = new FileWriter("../InPatternElements.txt");
		// FileWriter To = new FileWriter("../OutPatternElements.txt");
		// System.out.println("\nMatched Rule name: " + ruleObj.name);
		int k = 0;
		// i used for FROM
		// k used for TO
		int openBracket = 0;
		int closeBracket = 0;
		int openCurl = 0;
		int closeCurl = 0;
		boolean ruleStart = false;
		boolean inRuleStart = false;
		boolean outRuleStart = false;
		boolean toStart = false;
		while ((line = br.readLine()) != null) {
			// process the line.

			line = filterString(line);

			if (line.contains("{") && ruleStart) {
				openCurl++;
			}

			if (line.contains("}") && ruleStart) {
				closeCurl++;
			}

			if (line.contains("(") && ruleStart) {
				openBracket++;
			}

			if (line.contains(")") && ruleStart) {
				closeBracket++;
			}

			if (line.contains("from") && ruleStart) {
			}

			if (line.contains("to") && ruleStart) {
				toStart = true;
			}

			if (inRuleStart && ruleStart) {
				/*
				 * if(line.contains("using")) { usingFlag = true; }
				 */
				if ((line.contains(")") && openBracket == closeBracket && openCurl == closeCurl)
						|| line.contains("to")) {
					inRuleStart = false;
					continue;
				}
				// From.write(line + newLine);

			}

			if (outRuleStart && ruleStart) {
				if (line.contains(")") && openBracket == closeBracket) {
					outRuleStart = false;
					continue;
				}

				// To.write(line + newLine);
				// System.out.println(ruleObj.OutPatternElements.size());
				if (k <= ruleObj.OutPatternElements.size()) {
					// System.out.println(k +
					// System.getProperty("line.separator"));
					Element obj;
					obj = ruleObj.OutPatternElements.get(k - 1);
					obj.body.append(line + System.getProperty("line.separator"));
					Statement Stat = new Statement();
					Stat.value.append(line);
					Stat.isGeneral = true;
					obj.Statements.add(Stat);
					// System.out.println(line +
					// System.getProperty("line.separator"));
					ruleObj.OutPatternElements.set(k - 1, obj);
				}
			}

			if (toStart == true && k < Out.size() && ruleStart) {
				String output = Out.get(k).VariableName + " :";
				if (line.contains(output)) {
					outRuleStart = true;
					k++;
					continue;
				}
			}

			if (line.contains("rule " + ruleObj.name)) {
				ruleStart = true;
				continue;
			}

		}

		br.close();

		return false;
	}

	static boolean InPatternParseMatchedRule(EList<EObject> elements,
			ArrayList<Element> array) {
		// System.out.print("In Patterns:\n");
		for (int i = 0; i < elements.size(); i++) {

			Element obj = new Element();
			// System.out.print("Variable name: ");

			obj.VariableName = (eGet(elements.get(i), "varName")).toString();
			EObject oclType = (EObject) eGet(elements.get(i), "type");
			// gives the name of the UML metamodel element
			// System.out.print("Variable type: ");
			// System.out.println(eGet(oclType, "name"));
			if ((eGet(oclType, "name")) != null) {
				obj.VariableType = (eGet(oclType, "name")).toString();
			}

			array.add(obj);
		}
		return false;
	}

	static boolean OutPatternParseMatchedRule(EList<EObject> elements,
			ArrayList<Element> array) {

		// System.out.print("Out Patterns:\n");
		for (int i = 0; i < elements.size(); i++) {
			Element obj = new Element();
			// System.out.print("Variable name: ");

			// System.out.println(eGet(elements.get(i), "varName"));
			obj.VariableName = (eGet(elements.get(i), "varName")).toString();
			EObject oclType = (EObject) eGet(elements.get(i), "type");
			// gives the name of the UML metamodel element
			// System.out.print("Variable type: ");
			// System.out.println(eGet(oclType, "name"));
			obj.VariableType = (eGet(oclType, "name")).toString();
			array.add(obj);
		}

		return false;
	}

	static boolean ExtractLazyMatchedRuleBody(ArrayList<Element> In,
			ArrayList<Element> Out, LazyMatchedRule ruleObj) throws IOException {
		// In Array for in pattern elements
		// Out Array for out pattern elements

		BufferedReader br = new BufferedReader(new FileReader(FilePath));
		String line;

		// FileWriter Rule = new FileWriter("../Rules.txt");
		// FileWriter From = new FileWriter("../InPatternElements.txt");
		// FileWriter To = new FileWriter("../OutPatternElements.txt");
		// System.out.println("\nLazy Matched Rule name: " + ruleObj.name);
		int i = 0, k = 0;
		// i used for FROM
		// k used for TO
		int openBracket = 0;
		int closeBracket = 0;
		int openCurl = 0;
		int closeCurl = 0;
		boolean ruleStart = false;
		boolean inRuleStart = false;
		boolean outRuleStart = false;
		boolean fromStart = false;
		boolean toStart = false;
		while ((line = br.readLine()) != null) {
			// process the line.

			line = filterString(line);

			if (line.contains("{") && ruleStart) {
				openCurl++;
			}

			if (line.contains("}") && ruleStart) {
				closeCurl++;
			}

			if (line.contains("(") && ruleStart) {
				openBracket++;
			}

			if (line.contains(")") && ruleStart) {
				closeBracket++;
			}

			if (line.contains("from") && ruleStart) {
				fromStart = true;
			}

			if (line.contains("to") && ruleStart) {
				toStart = true;
				fromStart = false;
			}

			if (inRuleStart && ruleStart) {
				/*
				 * if(line.contains("using")) { usingFlag = true; }
				 */
				if ((line.contains(")") && openBracket == closeBracket && openCurl == closeCurl)
						|| line.contains("to")) {
					inRuleStart = false;
					fromStart = false;
					continue;
				}
				// From.write(line + newLine);

				if (i <= ruleObj.InPatternElements.size()) {
					Element obj = new Element();
					obj = ruleObj.InPatternElements.get(i - 1);
					obj.body.append(line + System.getProperty("line.separator"));
					Statement Stat = new Statement();
					Stat.value.append(line);
					Stat.isGeneral = true;
					obj.Statements.add(Stat);
					// System.out.println(line +
					// System.getProperty("line.separator"));
					ruleObj.InPatternElements.set(i - 1, obj);
				}

			}

			if (outRuleStart && ruleStart) {
				if (line.contains(")") && openBracket == closeBracket) {
					outRuleStart = false;
					continue;
				}

				// To.write(line + newLine);
				// System.out.println(ruleObj.OutPatternElements.size());
				if (k <= ruleObj.OutPatternElements.size()) {
					// System.out.println(k +
					// System.getProperty("line.separator"));
					Element obj;
					obj = ruleObj.OutPatternElements.get(k - 1);
					obj.body.append(line + System.getProperty("line.separator"));
					Statement Stat = new Statement();
					Stat.value.append(line);
					Stat.isGeneral = true;
					obj.Statements.add(Stat);
					// System.out.println(line +
					// System.getProperty("line.separator"));
					ruleObj.OutPatternElements.set(k - 1, obj);
				}
			}

			if (fromStart == true && i < In.size() && ruleStart) {
				String input = In.get(i).VariableName + " :";
				if (line.contains(input)) {
					inRuleStart = true;
					i++;
					continue;
				}
			}

			if (toStart == true && k < Out.size() && ruleStart) {
				String output = Out.get(k).VariableName + " :";
				if (line.contains(output)) {
					outRuleStart = true;
					k++;
					continue;
				}
			}

			if (line.contains("lazy rule " + ruleObj.name)) {
				ruleStart = true;
				continue;
			}

		}

		br.close();

		return false;
	}

	static boolean InPatternParseLazyMatchedRule(EList<EObject> elements,
			ArrayList<Element> array) {
		// System.out.print("In Patterns:\n");
		for (int i = 0; i < elements.size(); i++) {

			Element obj = new Element();

			obj.VariableName = (eGet(elements.get(i), "varName")).toString();
			EObject oclType = (EObject) eGet(elements.get(i), "type");
			// gives the name of the UML metamodel element
			// System.out.print("Variable type: ");
			// System.out.println(eGet(oclType, "name"));
			if ((eGet(oclType, "name")) != null) {
				obj.VariableType = (eGet(oclType, "name")).toString();
			}
			//Object a = eGet(oclType, "name");
			array.add(obj);
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<CalledRule> extractAllCalledRules(Collection<EObject> roots)
			throws IOException {
		ArrayList<CalledRule> myRulesArray = new ArrayList<CalledRule>();
		int numOfRules = roots.size();

		Iterator<EObject> itr = roots.iterator();
		EObject retUnit = null;

		while (numOfRules > 0) {
			numOfRules--;

			retUnit = itr.next();
			// for input type
			CalledRule calledRuleObj = new CalledRule();
			String RuleName = eGet(retUnit, "name").toString();
			calledRuleObj.setName(RuleName);
			calledRuleObj.setType("CalledRule");

			EObject outPattern = (EObject) eGet(retUnit, "outPattern");
			EList<EObject> elements = (EList<EObject>) eGet(outPattern, "elements");

			if (elements != null) {
				ArrayList<Element> ParsedOutPatternElements = new ArrayList<Element>();

				OutPatternParseLazyMatchedRule(elements,
						ParsedOutPatternElements);

				calledRuleObj.SetOutPatterns(ParsedOutPatternElements);

				ExtractCalledRuleBody(ParsedOutPatternElements, calledRuleObj);
				System.out.print("");
			}
			// get body of the actionBlock mean do block
			ArrayList<String> ActionBlockStatements = new ArrayList<String>();
			// ExtractActionBlockBody(ActionBlockStatements,RuleName);

			calledRuleObj.SetActionBlock(ActionBlockStatements);

			myRulesArray.add(calledRuleObj);
		}

		return myRulesArray;
	}

	/*
	 * static boolean ExtractActionBlockBody(ArrayList<String>
	 * ActionBlockStatements, String RuleName) throws IOException {
	 * BufferedReader readerBuf = new BufferedReader( new FileReader(FilePath));
	 * String line = null;
	 * 
	 * boolean found = false; int bracketCount = 0;
	 * 
	 * while ( ( line = readerBuf.readLine()) != null ) {
	 * if(line.contains("rule "+RuleName)) { found = true;
	 * //System.out.println("in rule name : "+RuleName); line =
	 * readerBuf.readLine(); int DoBlockNum =0; do { if(line.contains("--")) {
	 * String temp[] = line.split("--"); line = temp[0]; //continue; }
	 * if(line.contains("rule") == true ) { break; }
	 * 
	 * if(line.contains("do")) {
	 * 
	 * DoBlockNum++; if(DoBlockNum == 2) { break; }
	 * 
	 * //System.out.println("Have Action bloack ");
	 * 
	 * // System.out.println(line); int cCount = 0;
	 * ActionBlockStatements.add(line); if(line.contains("{")) { cCount++;
	 * bracketCount++; } if(line.contains("}")) { bracketCount--; } while((line
	 * = readerBuf.readLine()) != null && bracketCount != 0 || cCount == 0) {
	 * if(line.contains("--")) { String temp[] = line.split("--"); line =
	 * temp[0]; //continue; } if(line.contains("rule") == true ) { break; }
	 * if(line.contains("{")) { bracketCount++; } if(line.contains("}")) {
	 * bracketCount--; } // System.out.println(line);
	 * ActionBlockStatements.add(line); } } }while((line = readerBuf.readLine())
	 * != null ); }
	 * 
	 * if(found) break; } readerBuf.close();
	 * 
	 * //System.out.println("Action Block Size : "+ActionBlockStatements.size());
	 * return true; }
	 */
	static boolean OutPatternParseLazyMatchedRule(EList<EObject> elements,
			ArrayList<Element> array) {

		// System.out.print("Out Patterns:\n");
		for (int i = 0; i < elements.size(); i++) {
			Element obj = new Element();
			// System.out.print("Variable name: ");
			// System.out.println(eGet(elements.get(i), "varName"));
			obj.VariableName = (eGet(elements.get(i), "varName")).toString();
			EObject oclType = (EObject) eGet(elements.get(i), "type");
			// gives the name of the UML metamodel element
			// System.out.print("Variable type: ");
			// System.out.println(eGet(oclType, "name"));
			obj.VariableType = (eGet(oclType, "name")).toString();
			array.add(obj);
		}

		return false;
	}

	/**
	 * Returns the value of a feature on an EObject.
	 * 
	 * @param self
	 *            the EObject
	 * @param featureName
	 *            the feature name
	 * @return the feature value
	 */
	private static Object eGet(EObject self, String featureName) {
		if (self != null) {
			EStructuralFeature feature = self.eClass().getEStructuralFeature(
					featureName);
			if (feature != null) {
				return self.eGet(feature);
			}
		}
		return null;
	}

	private static EList<?> eGetList(EObject self, String featureName) {
		if (self != null) {
			EStructuralFeature feature = self.eClass().getEStructuralFeature(
					featureName);
			if (feature != null) {
				return (EList<?>) self.eGet(feature);
			}
		}
		return null;
	}

	public Reference ExtractExpression(Collection<EObject> roots,
			ArrayList<CalledRule> calledRules,
			ArrayList<LazyMatchedRule> lazyMatchedRules,
			ArrayList<MatchedRule> matchedRules, String FilePath)
			throws Exception {

		//ArrayList<MatchedRule> myRulesArray = new ArrayList<MatchedRule>();

		int count = roots.size();
		System.out.println("Size of the all rules are : "+count);
		Iterator<EObject> itr = roots.iterator();
		EObject retUnit = null;
		Statement ActionBlockStatements[] = null;
		Statement InPatternBlockStatements[]=null;
		Statement OutPatternBlockStatements[]=null;
		while (count > 0) {
			count--;
			String location="";
			retUnit = itr.next();
			////////////////////////////////////////
			EObject inputPattern = (EObject) eGet(retUnit, "inPattern");
			
			if (inputPattern != null) {
				
			 EList<?> InPatternStatements = (EList<?>) eGet(inputPattern, "elements");
			 InPatternBlockStatements = new Statement[InPatternStatements.size()];

			int j = 0;

			//System.out.println(" \n\nsize of  In Pattern statements" + InPatternStatements.size()
				//	+ "\nRule type: " + retUnit.eClass().getName()
					//+ "\nrule name: " + eGet(retUnit, "name"));

			while (j< InPatternStatements.size()) {
				EObject Statement = (EObject) InPatternStatements.get(j);
				location = (String) eGet(Statement, "location");
			//	System.out.println(location);
			//	System.out.println(Statement);
				InPatternBlockStatements[j]=new Statement();
				InPatternBlockStatements[j].value=getaline(location, FilePath);
			//	System.out.println(Stat.value);
				InPatternBlockStatements[j].location = location;
				String StatType = getStatementType(location,FilePath);
				if(StatType=="if")
				{
					InPatternBlockStatements[j].isIf = true;
				}
				else if(StatType=="for")
				{
					InPatternBlockStatements[j].isFor = true;
				}
				else
				{
					InPatternBlockStatements[j].isGeneral = true;
				}
				j++;
			}
			j = 0;
			}
			////////////////////////////////////
			EObject outPattern = (EObject) eGet(retUnit, "outPattern");
			
			if (outPattern != null) {
					
			EList<?> OutPatternStatements = (EList<?>) eGet(outPattern, "elements");
			int k = 0;
			OutPatternBlockStatements = new Statement[OutPatternStatements.size()];

			/*System.out.println(" \n\nsize of Out Pattern statements" + OutPatternStatements.size()
					+ "\nRule type: " + retUnit.eClass().getName()
					+ "\nrule name: " + eGet(retUnit, "name"));
*/
			while (k< OutPatternStatements.size()) {
				EObject Statement = (EObject) OutPatternStatements.get(k);
				location = (String) eGet(Statement, "location");
			//	System.out.println(location);
			//	System.out.println(Statement);
				OutPatternBlockStatements[k]=new Statement();
				OutPatternBlockStatements[k].value=getaline(location, FilePath);
			//	System.out.println(Stat.value);
				OutPatternBlockStatements[k].location = location;
				String StatType = getStatementType(location,FilePath);
				if(StatType=="if")
				{
					OutPatternBlockStatements[k].isIf = true;
				}
				else if(StatType=="for")
				{
					OutPatternBlockStatements[k].isFor = true;
				}
				else
				{
					OutPatternBlockStatements[k].isGeneral = true;
				}
				k++;
			}
			k = 0;
			}
			////////////////////////////////////

			EObject ActionBlock = (EObject) eGet(retUnit, "actionBlock");
			if (ActionBlock != null) {				
			EList<?> Statements = (EList<?>) eGetList(ActionBlock, "statements");
			ActionBlockStatements=getActionBlock(ActionBlockStatements, Statements);
			}
			
			
			///////////////////////////////////////
			String RuleType = retUnit.eClass().getName();
			String RuleName = (String) eGet(retUnit, "name");
		
			if(RuleType.equals("MatchedRule")){
				//System.out.println("I am matched rule\n");
				for(int i=0;i<matchedRules.size();i++){
					if(RuleName.equals(matchedRules.get(i).name)){
						//matchedRules.get(i).ActionBlockStatements.add(ActionBlockStatements.value);
						if(ActionBlockStatements!=null){
							for(int l=0;l<ActionBlockStatements.length;l++)
						matchedRules.get(i).ActionBlockInfo.add(ActionBlockStatements[l]);
						}
						if(InPatternBlockStatements!=null){
							for(int l=0;l<InPatternBlockStatements.length;l++)	
						matchedRules.get(i).InPatternInfo.add(InPatternBlockStatements[l]);
						}
						if(OutPatternBlockStatements!=null){
							for(int l=0;l<OutPatternBlockStatements.length;l++)
						matchedRules.get(i).OutPatternInfo.add(OutPatternBlockStatements[l]);
						}
					}
				}
			}
			else if(RuleType.equals("CalledRule")){
				//System.out.println("I am called rule\n");
				for(int i=0;i<calledRules.size();i++)	{
					if(RuleName.equals(calledRules.get(i).name)){
							if(ActionBlockStatements!=null){
							for(int l=0;l<ActionBlockStatements.length;l++)
						calledRules.get(i).ActionBlockInfo.add(ActionBlockStatements[l]);
						}
						if(InPatternBlockStatements!=null){
							for(int l=0;l<InPatternBlockStatements.length;l++)	
						calledRules.get(i).InPatternInfo.add(InPatternBlockStatements[l]);
						}
						if(OutPatternBlockStatements!=null){
							for(int l=0;l<OutPatternBlockStatements.length;l++)
						calledRules.get(i).OutPatternInfo.add(OutPatternBlockStatements[l]);
						}
					}
				}
				
			}
			else if(RuleType.equals("LazyMatchedRule"))
			{
				//System.out.println("I am lazy rule\n");
				for(int i=0;i<lazyMatchedRules.size();i++)
				{
					if(RuleName.equals(lazyMatchedRules.get(i).name))
					{
						//lazyMatchedRules.get(i).ActionBlockStatements.add(getaline(location, FilePath));
						if(ActionBlockStatements!=null){
							for(int l=0;l<ActionBlockStatements.length;l++)
						lazyMatchedRules.get(i).ActionBlockInfo.add(ActionBlockStatements[l]);
						}
						if(InPatternBlockStatements!=null){
							for(int l=0;l<InPatternBlockStatements.length;l++)	
						lazyMatchedRules.get(i).InPatternInfo.add(InPatternBlockStatements[l]);
						}
						if(OutPatternBlockStatements!=null){
							for(int l=0;l<OutPatternBlockStatements.length;l++)
						lazyMatchedRules.get(i).OutPatternInfo.add(OutPatternBlockStatements[l]);
						}
					}
				}
				
			}		
		}
		Reference obj = new Reference();
		obj.CalledRules = calledRules;
		obj.LazyMatchedRules = lazyMatchedRules;
		obj.MatchedRules = matchedRules;		
		return obj;

	}

	public StringBuffer getaline(String location, String FilePath)
			throws Exception {
//modified 19-11-13
		
		String value[] = location.split("-");
		String starting[] = value[0].split(":");
		String ending[] = value[1].split(":");

		int startFrom = Integer.parseInt(starting[0]);
		int endTo = Integer.parseInt(ending[0]);


		BufferedReader br = new BufferedReader(new FileReader(FilePath));
		StringBuffer ActionBlock = new StringBuffer("");
		String line;
		int lineNo = 0;
		while ((line = br.readLine()) != null) {
			lineNo++;
			//System.out.println(lineNo+ " # "+line);
			//line = line.trim();
			if (lineNo >= startFrom && lineNo <= endTo) {
				//System.out.println(line+"\n");
				if(startFrom == endTo){
					try{
//						for(int l=0; l<line.length(); l++)
//							System.out.println(l+" # "+line.charAt(l));
						String templine = line.substring(Integer.parseInt(starting[1]) - 1,Integer.parseInt(ending[1]) - 1);
						line = templine;
					}catch(Exception e){}
					//ActionBlock.append(line + System.lineSeparator());
					ActionBlock.append(line + "\n");
				}else
//				{
//					if(lineNo == startFrom){
//						try{
//							String templine = line.substring(Integer.parseInt(starting[1]) - 1, line.length() - 1);
//							line = templine;
//						}catch(Exception e){}
//						ActionBlock.append(line + System.lineSeparator());
//						break;
//					}
//						else if(lineNo == endTo){
//						try{
//							String templine = line.substring(0, Integer.parseInt(ending[1]) - 1);
//							line = templine;
//						}catch(Exception e){}
//						ActionBlock.append(line + System.lineSeparator());
//					}
//					else
						//ActionBlock.append(line + System.lineSeparator());
				        ActionBlock.append(line + "\n");
//				}
			}
		}
		br.close();

		return ActionBlock;
	}
	
	public StringBuffer getasingleline(String location, String FilePath)
			throws Exception {
//modified 31-12-13
		
		String value[] = location.split("-");
		String starting[] = value[0].split(":");
		String ending[] = value[1].split(":");

		int startFrom = Integer.parseInt(starting[0]);
		int endTo = Integer.parseInt(ending[0]);


		BufferedReader br = new BufferedReader(new FileReader(FilePath));
		StringBuffer ActionBlock = new StringBuffer("");
		String line;
		int lineNo = 0;
		while ((line = br.readLine()) != null) {
			lineNo++;
			if (lineNo >= startFrom && lineNo <= endTo) {
				// System.out.println(line+"\n");
				if(startFrom == endTo){
					line = line.substring(Integer.parseInt(starting[1]) - 1,Integer.parseInt(ending[1]) - 1);
					line.trim();
					//ActionBlock.append(line + System.lineSeparator());
					ActionBlock.append(line + "\n");
				}else
					//ActionBlock.append(line + System.lineSeparator());
				    ActionBlock.append(line + "\n");
			}
		}
		br.close();

		return ActionBlock;
	}
	public String getFirstLine(String location, String FilePath) throws Exception {

		String value[] = location.split("-");
		String starting[] = value[0].split(":");
		String ending[] = value[1].split(":");

		int startFrom = Integer.parseInt(starting[0]);
		int endTo = Integer.parseInt(ending[0]);


		BufferedReader br = new BufferedReader(new FileReader(FilePath));
		String line;
		int lineNo = 0;
		while ((line = br.readLine()) != null) {
			lineNo++;
			if (lineNo >= startFrom && lineNo <= endTo) {
				break;
			}
		}
		br.close();
		return line;
	}

	private String getStatementType (String location, String FilePath) throws Exception {
		String value[] = location.split("-");
		String starting[] = value[0].split(":");
		int startFrom = Integer.parseInt(starting[0]);
		BufferedReader br = new BufferedReader(new FileReader(FilePath));
		String line;
		int lineNo = 0;
		while ((line = br.readLine()) != null) {
			lineNo++;
			if (lineNo == startFrom ) {
			 if(line.contains("for(")||line.contains("for (")){return "for";}
			 if(line.contains("if(")||line.contains("if (")){return "if";}

			}
		}
		br.close();

		return "general";
	}
    
	private Statement[] getActionBlock(Statement[] ActionBlockStatements, EList<?> Statements)throws Exception{
		int ii=0;
		String location;
		ActionBlockStatements=new Statement[Statements.size()];
		while (ii < Statements.size()) {
			EObject Statement = (EObject) Statements.get(ii);
			location = (String) eGet(Statement, "location");
		    StringBuffer line=getaline(location, FilePath);
		    ActionBlockStatements[ii]=new Statement();
			ActionBlockStatements[ii].value.append(line);
			ActionBlockStatements[ii].location = location;
			String StatType = getStatementType(location,FilePath);
			if(StatType=="if"){
				ActionBlockStatements[ii].isIf = true;
				String firstLine = getFirstLine(location, FilePath);
				ActionBlockStatements[ii].condition = getIfExpressionFromStatement(firstLine);
			}
			else if(StatType=="for"){
				ActionBlockStatements[ii].isFor = true;
				String firstLine = getFirstLine(location, FilePath);
				ActionBlockStatements[ii].condition = getForLoopExpressionFromStatement(firstLine);
			}
			else{
				ActionBlockStatements[ii].isGeneral = true;
			}
			if (ActionBlockStatements[ii].isGeneral == false) {
				EList<?> NestedStatement = null;
				if(ActionBlockStatements[ii].isFor){
					NestedStatement = (EList<?>) eGetList(Statement,"statements");
					if (NestedStatement != null && NestedStatement.size() > 0) {
						ActionBlockStatements[ii].Nestedstmt = getActionBlock(
								ActionBlockStatements[ii].Nestedstmt,
								NestedStatement);
					}
				}else if(ActionBlockStatements[ii].isIf){
					NestedStatement = (EList<?>) eGetList(Statement,"thenStatements");
					if (NestedStatement != null && NestedStatement.size() > 0) {
						ActionBlockStatements[ii].Nestedstmt = getActionBlock(
								ActionBlockStatements[ii].Nestedstmt,
								NestedStatement);
					}
					NestedStatement = (EList<?>) eGetList(Statement,"elseStatements");
					if (NestedStatement != null && NestedStatement.size() > 0) {
						ActionBlockStatements[ii].Elsestmt = getActionBlock(
								ActionBlockStatements[ii].Elsestmt,
								NestedStatement);
					}
				}
			}
			ii++;
		}
		ii= 0;
		
		return ActionBlockStatements;
	}
	
	private static String getForLoopExpressionFromStatement(String statement){
		if(statement.toLowerCase().contains("for(") || statement.toLowerCase().contains("for (")){
			int forIndex = statement.indexOf("for");
			int firstBracketIndex = statement.indexOf("(", forIndex);
			int secondBracketIndex = statement.lastIndexOf(")");
			//secondBracketIndex += statement.length() - 1;
			String expression = statement.substring((firstBracketIndex + 1), secondBracketIndex);
			return expression;
		}
		return null;
	}
	
	private static String getIfExpressionFromStatement(String statement){
		if(statement.toLowerCase().contains("if(") || statement.toLowerCase().contains("if (")){
			int forIndex = statement.indexOf("if");
			int firstBracketIndex = statement.indexOf("(", forIndex);
			//int secondBracketIndex = statement.lastIndexOf(")", firstBracketIndex);
			int secondBracketIndex = statement.lastIndexOf(")");
			//secondBracketIndex += statement.length() - 1;
			String expression = statement.substring((firstBracketIndex + 1), secondBracketIndex);
			return expression;
		}
		return null;
	}
}
