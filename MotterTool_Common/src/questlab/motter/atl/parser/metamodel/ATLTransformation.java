package questlab.motter.atl.parser.metamodel;
import java.util.ArrayList;


public class ATLTransformation {

	public ArrayList<CalledRule> CalledRules = new ArrayList<CalledRule>();
	public ArrayList<MatchedRule> MatchedRules = new ArrayList<MatchedRule>();
	public ArrayList<LazyMatchedRule> LazyMatchedRules = new ArrayList<LazyMatchedRule>();
	public ArrayList<Helper> Helpers = new ArrayList<Helper>();
}
