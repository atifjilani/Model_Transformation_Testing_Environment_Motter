package questlab.motter.atl.metamodel;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

import questlab.motter.atl.parser.metamodel.Rule;

/**
 *
 * @author Abdul Ali
 */
public class OutPattern extends LocatedElement{
    Rule RuleObj;
    DropPattern DropPatternObj;
    ArrayList<OutPatternElement> elements = new ArrayList<OutPatternElement>();
}
