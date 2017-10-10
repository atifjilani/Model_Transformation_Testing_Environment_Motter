package questlab.motter.CFG;

public class CFGATLDriverU {
	//Conditions =9  Branches =16
	public CFGATLDriverU() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		CFGATLDriverU d=new CFGATLDriverU();
		CFG TP=d.funRulePersistentClass2Table();
		
		//Node temp = TP.BFS("ASC5");
		//System.out.println(temp.getNodeId()+" - "+temp.getAtlCode());
		
		//TP.reInitializeIsVisisted(TP.getRootNode());
//			TP.dfs(TP.getRootNode())	;
        TP.BFS();
        TP.reInitializeIsVisisted(TP.getRootNode());
        
//        TP.PreOrder();
//        TP.reInitializeIsVisisted(TP.getRootNode());
//       
//        System.out.println();
//        
//		TP.PostOrder();
//		TP.reInitializeIsVisisted(TP.getRootNode());
	}
	//Conditions =2  Branches =3
	public CFG funRulePersistentClass2Table(){
		AtomicNode atNode1,atNode2,atNode3;
		DecisionNode decNode,decNode1;
		CompositeNode compNode,compNode1;
		Condition cond;
		
		CFG R =new CFG();
		//FROM BLOCK
		atNode1=new AtomicNode("8#R1","c : SimpleClass!Class");
		R.insert(atNode1, null);
		atNode2=new AtomicNode("9#R2:Declaration:C1","c.is_persistent and c.parent->oclIsUndefined()");
		R.insert(atNode2, atNode1);
		///USING BLOCK
		//primary_attributes : Sequence(TupleType(name : String, type : SimpleClass!Classifier,isPrimary : Boolean)) =
		compNode=new CompositeNode("15#R3:Comp","c.flattenedFeatures");
		R.insert(compNode, atNode2);
		Node returnNode = this.funFlattenedFeature(R, compNode);
		atNode3=new AtomicNode("16#R4","->select(f | f.isPrimary);");
		R.insert(atNode3,returnNode);
		//persistent_features : Sequence(TupleType(name : String,class : SimpleClass!Class,	offcet : Integer,nofAttrs : Integer	)) =
		atNode1=new AtomicNode("28#R5","Sequence{}");
		R.insert(atNode1, atNode3);
		compNode=new CompositeNode("25#R6","c.flattenedFeatures");
		R.insert(compNode, atNode1);
		returnNode = this.funFlattenedFeature(R, compNode);
		atNode1=new AtomicNode("26#R7","->iterate(tuple; acc : Sequence(TupleType(name : String,class : SimpleClass!Class,offcet : Integer,nofAttrs : Integer");//=
		R.insert(atNode1, returnNode);
		decNode=new DecisionNode("30#R8:DecisionNode:C6","tuple.type->oclIsKindOf(SimpleClass!Class)");//if
		cond=new Condition("Attribute.allInstances()->forAll(c| c.is_primary>0 and c.type.oclAsType(Class).is_persistent>0)");
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition("Attribute.allInstances()->forAll(c| c.is_primary<0 and c.type.oclAsType(Class).is_persistent<0)"));
		R.insert(decNode, atNode1);
		atNode1=new AtomicNode("33#R9:then:R8","acc");
				//+"->append(Tuple{name=tuple.name,class = tuple.type,offcet";//=
		R.insert(atNode1, decNode);
		decNode1=new DecisionNode("36#R10:DecisionNode:C6.1","acc->size()=0");//if
		cond=new Condition("Class.allInstances()->forAll(c|c.attrs->isEmpty())");
		decNode1.setcondition(cond);
		decNode1.setFalseCondition(new Condition("Class.allInstances()->forAll(c|c.attrs->notEmpty())"));
		R.insert(decNode1, atNode1);
		atNode1=new AtomicNode("38#R11:then:R10"," 1");
		R.insert(atNode1, decNode1);
		atNode2=new AtomicNode("40#R12:else:R10","acc->last().offcet + acc->last().nofAttrs");
		R.insert(atNode2, decNode1);
		//atNode3=new AtomicNode("42#R13","tuple.type.topParent.flattenedFeatures->->select(t | t.isPrimary)->size()");
		//R.insert(atNode3, atNode1);
		//atNode3.setParentNode(atNode2);
		
		compNode=new CompositeNode("42#R14:Comp", "tuple.type.topParent");
		R.insert(compNode, atNode1);
		compNode.setParentNode(atNode2);
		returnNode = this.funTopParent(R, compNode);
		compNode1=new CompositeNode("43#R15:Comp", ".flattenedFeatures");
		R.insert(compNode1, returnNode);
		returnNode = this.funFlattenedFeature(R, compNode1);
		atNode1=new AtomicNode("44#R16","->select(t | t.isPrimary)->size() } )");
		R.insert(atNode1, returnNode);
		atNode3=new AtomicNode("41#R17","nofAttrs=");
		R.insert(atNode3, atNode1);
		atNode2=new AtomicNode("46#R18:else:R8","acc");
		R.insert(atNode2, decNode);
		//foreign_key_attributes : Sequence(TupleType(name : String,type : SimpleClass!Classifier)) =			
		atNode1=new AtomicNode("50#R19","persistent_features");
		R.insert(atNode1, atNode3);
		atNode1.setParentNode(atNode2);
		
		atNode2=new AtomicNode("51#R20","->collect(tuple|");
		R.insert(atNode2, atNode1);
		compNode=new CompositeNode("52#R21","tuple.class.topParent");
		R.insert(compNode, atNode2);
		returnNode = this.funTopParent(R, compNode);
		compNode1=new CompositeNode("53#R22:composite", ".flattenedFeatures");
		R.insert(compNode1, returnNode);
		returnNode = this.funFlattenedFeature(R, compNode1);
		atNode1=new AtomicNode("54#R23","->select(t | t.isPrimary)");
				//+ "->collect(a |Tuple {name=tuple.name + '_' + a.name,type=a.type}))->flatten();");
		R.insert(atNode1, returnNode);
		//rest_of_attributes : Sequence(TupleType(name : String,type : SimpleClass!Classifier)) =
		compNode1=new CompositeNode("60#R24:composite", "c.flattenedFeatures");
		R.insert(compNode1, atNode1);
		returnNode = this.funFlattenedFeature(R, compNode1);
		atNode1=new AtomicNode("61#R25","->select(tuple | not tuple.isPrimary and not tuple.type->oclIsKindOf(SimpleClass!Class));}");
		R.insert(atNode1, returnNode);
		/// To
		atNode2=new AtomicNode("64#R26","t : SimpleRDBMS!Table (name<-c.name,cols<-primary_key_columns->union(foreign_key_columns)->union(rest),pkey<-primary_key_columns,fkeys<-foreign_keys ),");
		R.insert(atNode2, atNode3);
		atNode1=new AtomicNode("70#R27","primary_key_columns : distinct SimpleRDBMS!Column foreach (primAttr in primary_attributes)(name<-primAttr.name,type<-primAttr.type.name),");
		R.insert(atNode1, atNode2);
		String q= "foreign_keys : distinct SimpleRDBMS!FKey foreach (persAttr in persistent_features)(";
 			q+="references<-persAttr.class.topParent," ;
			q+="cols<-persistent_features->iterate(tuple; acc : Sequence(Sequence(SimpleRDBMS!Column))=Sequence{} |";
			q+="acc->append(foreign_key_columns.subSequence(tuple.offcet,tuple.offcet + tuple.nofAttrs-1))) ),";
		atNode2=new AtomicNode("75#R28",q);
		R.insert(atNode2, atNode1);
		q="";
		q="foreign_key_columns : distinct SimpleRDBMS!Column foreach (attr in foreign_key_attributes)(";
		        q+="name<-attr.name,";
				q+="type<-attr.type.name),";
		atNode3=new AtomicNode("82#R29",q);
		R.insert(atNode3, atNode2);
		q="";
		q="rest : distinct SimpleRDBMS!Column foreach (attr in rest_of_attributes)(";
 			q+="name<-attr.name,";
		    q+="type<-attr.type.name)";
		atNode1=new AtomicNode("87#R30",q);
		R.insert(atNode1, atNode3);		
		return R;
	}
    //Conditions =1  Branches =2
	public Node funTopParent(CFG TP, Node parent){
		AtomicNode atNode1,atNode3;
		DecisionNode decNode;
		CompositeNode compNode;
		Condition cond;
		
//		CFG TP =new CFG();
		//topParent : SimpleClass!Class =
		decNode=new DecisionNode("180#TP1:DecisionNode:C2","self.parent.oclIsUndefined()");//if
		cond=new Condition("Class.allInstances()->forAll(c|c.parent.oclIsUndefined()) ");
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition("Class.allInstances()->forAll(c|c.parent.oclIsKindOf(Class))"));
		TP.insert(decNode, parent);
		//then
		atNode1=new AtomicNode("182#TP2:then:TP1","self");
		TP.insert(atNode1, decNode);
		//else
		compNode=new CompositeNode("184#TP3:else:TP1","self.parent.topParent ");
		TP.insert(compNode, decNode);
	//	compNode.addChildNode(decNode);
		atNode3=new AtomicNode("185#TP4","endif;");
		TP.insert(atNode3, atNode1);
		atNode3.setParentNode(compNode);
		//TP.insert(atNode3, atNode2);
		//atNode=new AtomicNode("TP5","topParent : SimpleClass!Class =");
		//TP.insert(atNode, atNode3);
		//TP.BFS();
	/////////////////
		return atNode3;
	}
	//Conditions =0  Branches =1
	public Node funAssociationofSubClasses(CFG ASC, Node parent){
		AtomicNode atNode, atNode1,atNode2,atNode3;
		//DecisionNode decNode;
		CompositeNode compNode;
		//Condition cond;
		//////////////////////////////////
//		CFG ASC =new CFG();
		//Sequence(SimpleClass!Association) =
		atNode=new AtomicNode("160#ASC1","SimpleClass!Association.allInstances()");
		ASC.insert(atNode, parent);
		atNode3=new AtomicNode("161#ASC2","->select(assoc |assoc.src = self)");
		ASC.insert(atNode3, atNode);
		atNode1=new AtomicNode("162#ASC3","->Union(SimpleClass!Class.allInstances()");
		ASC.insert(atNode1, atNode3);
		atNode3=new AtomicNode("163#ASC4","->select(c |c.parent = self)->collect(subclass | ");
		ASC.insert(atNode3, atNode1);
		compNode=new CompositeNode("165#ASC5:Comp","subclass.associationsOfSubclasses");
		ASC.insert(compNode, atNode3);
		//compNode.addChildNode(atNode);
		atNode1=new AtomicNode("166#ASC6",")->flatten())");
		ASC.insert(atNode1, compNode);
		atNode2=new AtomicNode("167#ASC7","->flatten();");
		ASC.insert(atNode2, atNode1);
		//atNode3=new AtomicNode("ASC8","Sequence(SimpleClass!Association) =");
		//ASC.insert(atNode3, atNode2);
		//ASC.insert(this.funTopParent().getRootNode(), compNode);
		
		return atNode2;
				
	}
	//Conditions =0  Branches =1	
	public Node funAttributeofSubClasses(CFG ASC, Node parent){
		AtomicNode atNode, atNode1,atNode2,atNode3;
		//DecisionNode decNode;
		CompositeNode compNode;
		//Condition cond;
		//////////////////////////////////
//		CFG ASC =new CFG();
		//Sequence(SimpleClass!Attribute) =
//		//let attrsInSubclasses : Sequence(SimpleClass!Attribute) =
		atNode=new AtomicNode("145#AtSC1","SimpleClass!Class.allInstances()");//->select(c |c.parent=self)");
		ASC.insert(atNode, parent);
		atNode2=new AtomicNode("147#AtSC2","->collect(directSubclass | ");
				//+ "directSubclass.attributesOfSubclasses");
		ASC.insert(atNode2, atNode);
		//compNode.addChildNode(atNode);
		compNode=new CompositeNode("148#AtSC3","directSubclass.attributesOfSubclasses)");
		ASC.insert(compNode, atNode2);
		//compNode.addChildNode(atNode);
		//in
		atNode2=new AtomicNode("149#AtSC4",")->flatten()");
		ASC.insert(atNode2, compNode);
		atNode1=new AtomicNode("151#ATSC5","attrsInSubclasses->union(self.attrs->select(attr | ");
		ASC.insert(atNode1, atNode2);
		atNode3=new AtomicNode("152#AtSC6","not attrsInSubclasses->exists(a |	a.name = attr.name)));");
		ASC.insert(atNode3, atNode1);
		atNode1=new AtomicNode("153#AtSC7","->flatten()");//| not attrsInSubclasses->exists(a |	a.name = attr.name)))flatten();
		ASC.insert(atNode1, atNode3);
		
		return atNode1;
				
	}
	//Conditions =1  Branches =2
	public Node funAllAssociations(CFG AA, Node parent){
		AtomicNode atNode, atNode1,atNode2,atNode3;
		DecisionNode decNode;
		CompositeNode compNode;
		Condition cond;
		//////////////////////////////////
//		CFG AA =new CFG();
		//Sequence(SimpleClass!Association) = let defAssoc : Sequence(SimpleClass!Association) =
		atNode=new AtomicNode("124#AA1","SimpleClass!Association.allInstances()->select(assoc | assoc.src = self)");
		AA.insert(atNode, parent);
		//in
		atNode3=new AtomicNode("126#AA2","defAssoc->union(");
		AA.insert(atNode3, atNode);
		decNode=new DecisionNode("128#AA3:DecisionNode:C3.2","self.parent.oclIsUndefined()");//if
		cond=new Condition("Association.allInstances()->forAll(c|c.src.oclIsKindOf(Class) and c.dest.oclIsKindOf(Class))");
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition("Association.allInstances()->forAll(c|c.src.oclIsUndefined() and c.dest.oclIsUndefined())"));
		AA.insert(decNode, atNode3);//if
		compNode=new CompositeNode("130#AA4:then:AA3","self.parent.allAssociations");
		AA.insert(compNode, decNode);
//		compNode.addChildNode(atNode);//else		
		atNode2=new AtomicNode("132#AA5:else:AA3","Sequence {}");
		AA.insert(atNode2, decNode);
		//atNode3=new AtomicNode("AA6","endif;");
		//AA.insert(atNode3, compNode);
		//atNode3.setParentNode(atNode2);
		//AA.insert(atNode3, atNode2);
		atNode1=new AtomicNode("134#AA6",")->flatten();");
		AA.insert(atNode1, compNode);
		atNode1.setParentNode(atNode2);
		//atNode2=new AtomicNode("AA8","Sequence(SimpleClass!Association) = let defAssoc : Sequence(SimpleClass!Association) =");
		//AA.insert(atNode, atNode1);
		return atNode1;				
	}
	//Conditions =1  Branches =2
	public Node funAllAttributes(CFG AAt, Node parent){
		AtomicNode atNode, atNode1,atNode2,atNode3;
		DecisionNode decNode;
		CompositeNode compNode;
		Condition cond;
		//////////////////////////////////
//		CFG AAt =new CFG();
        //Sequence(SimpleClass!Attribute) =
		atNode=new AtomicNode("102#AAt1","self.attrs");
		AAt.insert(atNode, parent);
		//->union(
		decNode=new DecisionNode("105#AAt2:DecisionNode:C3.1","self.parent.oclIsUndefined()");//if
		cond=new Condition("Class.allInstances()->forAll(c|c.parent.oclIsKindOf(Class))");
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition("Class.allInstances()->forAll(c|c.parent.oclIsUndefined())"));
		AAt.insert(decNode, atNode);
		compNode=new CompositeNode("108#AAt3:then:AAt2","self.parent.allAttributes");//then
		AAt.insert(compNode, decNode);
	//	compNode.addChildNode(atNode);	
		atNode1=new AtomicNode("109#AAt4","->select(attr |not self.attrs->exists(at | at.name = attr.name))");
		AAt.insert(atNode1, compNode);
		atNode2=new AtomicNode("113#AAt5:else:AAt2","Sequence {}");//else
		AAt.insert(atNode2, decNode);
		//atNode3=new AtomicNode("AAt6","endif;");
		//AAt.insert(atNode3, atNode1);
		//atNode3.setParentNode(atNode2);
		//AA.insert(atNode3, atNode2);
		atNode3=new AtomicNode("114#AAt6",")->flatten();");
		AAt.insert(atNode3, atNode1);
		atNode3.setParentNode(atNode2);
		//atNode2=new AtomicNode("AAt8","Sequence(SimpleClass!Attribute) =");
		//AAt.insert(atNode2, atNode1);
		return atNode3;				
	}
	//Conditions =4  Branches =5
	public Node funFlattenedFeature(CFG FF, Node parent){
		AtomicNode atNode1,atNode2,atNode3,at1,at2;
		DecisionNode decNode;
		CompositeNode comp,compNode,compNode1,compNode2;
		Condition cond;
		//////////////////////////////////
//U		CFG FF =new CFG();
        //flattenedFeatures : Sequence(TupleType(name : String,type : SimpleClass!Classifier, isPrimary : Boolean )) =
		comp=new CompositeNode("194#FF1:Comp","self.topParent");
		FF.insert(comp, parent);
		Node returnNode = this.funTopParent(FF, comp); 
		//if self.topParent.is_persistent
		decNode=new DecisionNode("195#FF2:DecisionNode:C3","self.topParent.is_persistent");//if
		cond=new Condition("Class.allInstances()->forAll(c|c.parent.oclIsKindOf(Class) and c.parent.is_persistent>0)");
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition("Class.allInstances()->forAll(c|c.parent.oclIsKindOf(Class) and c.parent.is_persistent<0)"));
		FF.insert(decNode, returnNode);
		//self.topParent.attributesOfSubclasses->union(	self.topParent.associationsOfSubclasses)
		compNode=new CompositeNode("197#FF3:then:FF2","self.topParent");
		FF.insert(compNode, decNode);
		returnNode = this.funTopParent(FF, compNode);
		compNode1=new CompositeNode("198#FF4:Comp",".attributesOfSubclasses");
		FF.insert(compNode1, returnNode);
		returnNode = this.funAttributeofSubClasses(FF, compNode1);
		//->union(	
		//atNode1=new AtomicNode("FF5","->union(");
		//FF.insert(atNode1, compNode1);
		//self.topParent.associationsOfSubclasses)
		compNode2=new CompositeNode("200#FF5:Comp","self.topParent");
		FF.insert(compNode2,returnNode);
		returnNode = this.funTopParent(FF, compNode2);
		compNode1=new CompositeNode("201#FF6:Comp",".associationsOfSubclasses)");
		FF.insert(compNode1, returnNode);
		Node returnNode1 = this.funAssociationofSubClasses(FF, compNode1);
		//else		self.allAttributes->union(self.allAssociations)//else
		compNode=new CompositeNode("204#FF7:else:FF2","self.allAttributes");
		FF.insert(compNode, decNode);
		returnNode = this.funAllAttributes(FF, compNode);
		//->union(
		//atNode2=new AtomicNode("FF4.2","->union(");
		//FF.insert(atNode2, compNode);
		compNode2=new CompositeNode("206#FF8:Comp","self.allAssociations)");
		FF.insert(compNode2, returnNode);
		returnNode = this.funAllAssociations(FF, compNode2);
	//	atNode3=new AtomicNode("FF5","endif; ");
		//FF.insert(atNode3, compNode1);
		//atNode3.setParentNode(compNode2);
		//////->collect(f | let feature : TupleType(name : String,type : SimpleClass!Classifier,isPrimary : Boolean) =
		atNode1=new AtomicNode("209#FF9","->collect(f | let feature : TupleType(name : String,type : SimpleClass!Classifier,isPrimary : Boolean) = ");
		FF.insert(atNode1, returnNode1);
		atNode1.setParentNode(returnNode);
		
		decNode=new DecisionNode("214#FF10:DecisionNode:C4","f.oclIsKindOf(SimpleClass!Attribute)");//if
		cond=new Condition("Class.allInstances()->forAll(c|c.attrs->forAll(a|a.oclIsKindOf(Attribute)))");
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition("Class.allInstances()->forAll(c|c.attrs->size()=0)"));
		FF.insert(decNode, atNode1);
		atNode1=new AtomicNode("216#FF11:then:FF10","Tuple{name = f.name, type = f.type, isPrimary = f.is_primary} ");//then/
		FF.insert(atNode1, decNode);
		atNode2=new AtomicNode("218#FF12:else:FF10","Tuple{name = f.name, type = f.dest, isPrimary = false} ");//else
		FF.insert(atNode2, decNode);
		//atNode3=new AtomicNode("FF10","endif; in");
		//FF.insert(atNode3, atNode1);
		//atNode3.setParentNode(atNode2);
		//endif in
		decNode=new DecisionNode("222#FF13:DecisionNode:C5","feature.type.oclIsKindOf(SimpleClass!PrimitiveDataType)");
		cond=new Condition("Class.allInstances()->forAll(c|c.attrs->forAll(a|a.type.oclIsTypeOf(PrimitiveDataType)))");
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition("Class.allInstances()->forAll(c|c.attrs->forAll(a|a.type.oclIsTypeOf(Class)))"));
		FF.insert(decNode, atNode1);
		decNode.setParentNode(atNode2);
		atNode3=new AtomicNode("224#FF14:then:FF13","feature");//then
		FF.insert(atNode3, decNode);
		//atNode2=new AtomicNode("FF15:else:FF13","");//else
		//FF.insert(atNode2, decNode);
		//not feature.type.topParent.is_persistent
		compNode=new CompositeNode("226#FF15:else:FF13","feature.type.topParent");
		FF.insert(compNode, decNode);
		returnNode = this.funTopParent(FF, compNode);
		decNode=new DecisionNode("227#FF16:DecisionNode:C5.1","not feature.type.topParent.is_persistent");//if
		cond=new Condition("Attribute.allInstances()->forAll(c| c.type.oclAsType(Class).is_persistent>0)");
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition("Attribute.allInstances()->forAll(c| c.type.oclAsType(Class).is_persistent<0)"));
		FF.insert(decNode, returnNode);
		//atNode1=new AtomicNode("FF16","then");
		//FF.insert(atNode1, decNode);
		//feature.type.flattenedFeatures->collect (f | Tuple{name=feature.name+ '_' + f.name, type=f.type, isPrimary=f.isPrimary})
		compNode1=new CompositeNode("229#FF17:then:FF16","feature.type.flattenedFeatures");
		FF.insert(compNode1, decNode);
//		compNode1.addChildNode(comp);
		atNode1=new AtomicNode("230#FF18","->collect (f | Tuple{name=feature.name+ '_' + f.name, type=f.type, isPrimary=f.isPrimary})");
		FF.insert(atNode1, compNode1);
		
		atNode2=new AtomicNode("233#FF19:else:FF16","feature");
		FF.insert(atNode2, decNode);
		
		at1=new AtomicNode("234#FF20","endif;");
		FF.insert(at1, atNode1);
		at1.setParentNode(atNode2);
		
		//
		at2=new AtomicNode("235#FF20","endif;");
		FF.insert(at2, at1);
		at2.setParentNode(atNode3);
		
		atNode3=new AtomicNode("237#FF20","->flatten()");
		FF.insert(atNode3, at2);
		return atNode3;				
	}
}

	

