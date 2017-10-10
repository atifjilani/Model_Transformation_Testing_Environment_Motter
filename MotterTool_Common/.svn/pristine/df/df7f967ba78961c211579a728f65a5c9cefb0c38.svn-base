package questlab.motter.CFG;

public class CFGATLDriver {

	public CFGATLDriver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		CFGATLDriver d=new CFGATLDriver();
		CFG TP=d.funRulePersistentClass2Table();
		
		//Node temp = TP.BFS("ASC5");
		//System.out.println(temp.getNodeId()+" - "+temp.getAtlCode());
		
		//TP.reInitializeIsVisisted(TP.getRootNode());
				
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
	
	public CFG funRulePersistentClass2Table(){
		AtomicNode atNode1,atNode2,atNode3;
		DecisionNode decNode,decNode1;
		CompositeNode compNode,compNode1;
		Condition cond;
		
		CFG R =new CFG();
		//FROM BLOCK
		atNode1=new AtomicNode("R1:Declaration:C1","c : SimpleClass!Class (c.is_persistent and c.parent->oclIsUndefined())");
		R.insert(atNode1, null);
		///USING BLOCK
		compNode=new CompositeNode("R2:Comp","c.flattenedFeatures->select(f | f.isPrimary);");
		R.insert(compNode, atNode1);
		R.insert(this.funFlattenedFeature().getRootNode(), compNode);
		atNode2=new AtomicNode("R3","{ primary_attributes : Sequence(TupleType(name : String, type : SimpleClass!Classifier,isPrimary : Boolean)) =");
		R.insert(atNode2, compNode);
		atNode1=new AtomicNode("R4","->select(f | f.isPrimary);");
		R.insert(atNode1, atNode2);
		compNode=new CompositeNode("R5","c.flattenedFeatures");
		R.insert(compNode, atNode1);
		R.insert(this.funFlattenedFeature().getRootNode(), compNode);
		atNode1=new AtomicNode("R6","->iterate(tuple; acc : Sequence(TupleType(name : String,class : SimpleClass!Class,offcet : Integer, nofAttrs : Integer))=Sequence{}|");
		R.insert(atNode1, compNode);
		decNode=new DecisionNode("R7:DecisionNode:C6","if tuple.type->oclIsKindOf(SimpleClass!Class)");
		cond=new Condition("tuple.type->oclIsKindOf(SimpleClass!Class)");
		decNode.setcondition(cond);
		R.insert(decNode, atNode1);
		atNode1=new AtomicNode("R8","then acc->append(Tuple{name=tuple.name,class = tuple.type,offcet=");
		R.insert(atNode1, decNode);
		decNode1=new DecisionNode("R9:DecisionNode:C6.1","if acc->size()=0");
		cond=new Condition("acc->size()=0");
		decNode1.setcondition(cond);
		R.insert(decNode1, atNode1);
		atNode1=new AtomicNode("R10","then 1");
		R.insert(atNode1, decNode1);
		atNode2=new AtomicNode("R11","else acc->last().offcet + acc->last().nofAttrs");
		R.insert(atNode2, decNode1);
		atNode3=new AtomicNode("R12","endif, ");
		R.insert(atNode3, atNode1);
		atNode3.setParentNode(atNode2);
		compNode=new CompositeNode("R13:composite", "tuple.type.topParent");
		R.insert(compNode, atNode3);
		R.insert(this.funTopParent().getRootNode(), compNode);
		compNode1=new CompositeNode("R14:composite", ".flattenedFeatures");
		R.insert(compNode1, compNode);
		R.insert(this.funFlattenedFeature().getRootNode(), compNode1);
		atNode1=new AtomicNode("R15","->select(t | t.isPrimary)->size() } )");
		R.insert(atNode1, compNode1);
		atNode3=new AtomicNode("R16","nofAttrs=");
		R.insert(atNode3, atNode1);
		atNode2=new AtomicNode("R17","else acc ");
		R.insert(atNode2, decNode);
		atNode1=new AtomicNode("R18","endif ); ");
		R.insert(atNode1, atNode3);
		atNode1.setParentNode(atNode2);
		atNode2=new AtomicNode("R19","persistent_features : Sequence(TupleType(name : String,class : SimpleClass!Class,	offcet : Integer,nofAttrs : Integer	)) =");
		R.insert(atNode2, atNode1);
		atNode3=new AtomicNode("R20","persistent_features->collect(tuple |");
		R.insert(atNode3, atNode2);
		compNode=new CompositeNode("R21", "tuple.class.topParent");
		R.insert(compNode, atNode3);
		R.insert(this.funTopParent().getRootNode(), compNode);
		compNode1=new CompositeNode("R22:composite", ".flattenedFeatures");
		R.insert(compNode1, compNode);
		R.insert(this.funFlattenedFeature().getRootNode(), compNode1);
		atNode1=new AtomicNode("R23","->select(t | t.isPrimary)->collect(a |Tuple {name=tuple.name + '_' + a.name,type=a.type}))");
		R.insert(atNode1, compNode1);
		atNode2=new AtomicNode("R24","->flatten();");
		R.insert(atNode2, atNode1);
		atNode3=new AtomicNode("R25","foreign_key_attributes : Sequence(TupleType(name : String,type : SimpleClass!Classifier)) =");
		R.insert(atNode3, atNode2);
		
		
		compNode1=new CompositeNode("R26:composite", "c.flattenedFeatures");
		R.insert(compNode1, atNode3);
		R.insert(this.funFlattenedFeature().getRootNode(), compNode1);
		atNode1=new AtomicNode("R27","->select(tuple | not tuple.isPrimary and not tuple.type->oclIsKindOf(SimpleClass!Class));}");
		R.insert(atNode1, compNode1);
		atNode3=new AtomicNode("R28","rest_of_attributes : Sequence(TupleType(name : String,type : SimpleClass!Classifier)) =");
		R.insert(atNode3, atNode1);
		/// To
		atNode2=new AtomicNode("R29","t : SimpleRDBMS!Table (name<-c.name,cols<-primary_key_columns->union(foreign_key_columns)->union(rest),pkey<-primary_key_columns,fkeys<-foreign_keys ),");
		R.insert(atNode2, atNode3);
		atNode1=new AtomicNode("R30","primary_key_columns : distinct SimpleRDBMS!Column foreach (primAttr in primary_attributes)(name<-primAttr.name,type<-primAttr.type.name),");
		R.insert(atNode1, atNode2);
		String q= "foreign_keys : distinct SimpleRDBMS!FKey foreach (persAttr in persistent_features)(";
 			q+="references<-persAttr.class.topParent," ;
			q+="cols<-persistent_features->iterate(tuple; acc : Sequence(Sequence(SimpleRDBMS!Column))=Sequence{} |";
			q+="acc->append(foreign_key_columns.subSequence(tuple.offcet,tuple.offcet + tuple.nofAttrs-1))) ),";
		atNode2=new AtomicNode("R31",q);
		R.insert(atNode2, atNode1);
		q="";
		q="foreign_key_columns : distinct SimpleRDBMS!Column foreach (attr in foreign_key_attributes)(";
		        q+="name<-attr.name,";
				q+="type<-attr.type.name),";
		atNode3=new AtomicNode("R32",q);
		R.insert(atNode3, atNode2);
		q="";
		q="rest : distinct SimpleRDBMS!Column foreach (attr in rest_of_attributes)(";
 			q+="name<-attr.name,";
		    q+="type<-attr.type.name)";
		atNode1=new AtomicNode("R33",q);
		R.insert(atNode1, atNode3);		
		return R;
	}
	
	
	
///// CFG for topParent Helper
	public CFG funTopParent(){
		AtomicNode atNode,atNode1,atNode3;
		DecisionNode decNode;
		CompositeNode compNode;
		Condition cond;
		
		CFG TP =new CFG();
		//if
		decNode=new DecisionNode("TP1:DecisionNode-C2","if self.parent.oclIsUndefined()");
		cond=new Condition("self.parent.oclIsUndefined()");
		decNode.setcondition(cond);
		TP.insert(decNode, null);//then
		atNode1=new AtomicNode("TP2","self");
		TP.insert(atNode1, decNode);
		//else
		compNode=new CompositeNode("TP3","self.parent.topParent ");
		TP.insert(compNode, decNode);
		compNode.addChildNode(decNode);
		atNode3=new AtomicNode("TP4","endif;");
		TP.insert(atNode3, atNode1);
		atNode3.setParentNode(compNode);
		//TP.insert(atNode3, atNode2);
		atNode=new AtomicNode("TP5","topParent : SimpleClass!Class =");
		TP.insert(atNode, atNode3);
		//TP.BFS();
	/////////////////
		return TP;
	}
///// CFG for associationofSubClasses Helper	
	public CFG funAssociationofSubClasses(){
		AtomicNode atNode, atNode1,atNode2,atNode3;
		//DecisionNode decNode;
		CompositeNode compNode;
		//Condition cond;
		//////////////////////////////////
		CFG ASC =new CFG();
		
		atNode=new AtomicNode("ASC1","SimpleClass!Association.allInstances()->select(assoc |assoc.src = self)");
		ASC.insert(atNode, null);
		atNode3=new AtomicNode("ASC2","->Union(");
		ASC.insert(atNode3, atNode);
		atNode1=new AtomicNode("ASC3","SimpleClass!Class.allInstances()->select(c |c.parent = self)");
		ASC.insert(atNode1, atNode3);
		atNode3=new AtomicNode("ASC4","->collect(subclass | ");
		ASC.insert(atNode3, atNode1);
		compNode=new CompositeNode("ASC5:Comp","subclass.associationsOfSubclasses");
		ASC.insert(compNode, atNode3);
		compNode.addChildNode(atNode);
		atNode1=new AtomicNode("ASC6",")->flatten()) ");
		ASC.insert(atNode1, compNode);
		atNode2=new AtomicNode("ASC7","->flatten(); ");
		ASC.insert(atNode2, atNode1);
		atNode3=new AtomicNode("ASC8","Sequence(SimpleClass!Association) =");
		ASC.insert(atNode3, atNode2);
		//ASC.insert(this.funTopParent().getRootNode(), compNode);
		
		return ASC;
				
	}
///// CFG for associationofSubClasses Helper	
	public CFG funAttributeofSubClasses(){
		AtomicNode atNode, atNode1,atNode2,atNode3;
		//DecisionNode decNode;
		CompositeNode compNode;
		//Condition cond;
		//////////////////////////////////
		CFG ASC =new CFG();
//		
		atNode=new AtomicNode("AtSC1","SimpleClass!Class.allInstances()->select(c |c.parent=self)->collect(directSubclass | ");
		ASC.insert(atNode, null);
		compNode=new CompositeNode("AtSC2","directSubclass.attributesOfSubclasses");
		ASC.insert(compNode, atNode);
		compNode.addChildNode(atNode);
		atNode1=new AtomicNode("AtSC3",")->flatten();");
		ASC.insert(atNode1, compNode);
		atNode2=new AtomicNode("AtSC4","in attrsInSubclasses->union(self.attrs->select(attr | not attrsInSubclasses->exists(a |	a.name = attr.name)))");
		ASC.insert(atNode2, atNode1);
		atNode3=new AtomicNode("AtSC5","->flatten();");
		ASC.insert(atNode3, atNode2);
		atNode1=new AtomicNode("AtSC6","let attrsInSubclasses : Sequence(SimpleClass!Attribute) =");
		ASC.insert(atNode1, atNode3);
		atNode2=new AtomicNode("AtSC7","Sequence(SimpleClass!Attribute) =");
		ASC.insert(atNode2, atNode1);
		return ASC;
				
	}
	
	public CFG funAllAssociations(){
		AtomicNode atNode, atNode1,atNode2,atNode3;
		DecisionNode decNode;
		CompositeNode compNode;
		Condition cond;
		//////////////////////////////////
		CFG AA =new CFG();
		atNode=new AtomicNode("AA1","SimpleClass!Association.allInstances()->select(assoc | assoc.src = self) in");
		AA.insert(atNode, null);
		atNode3=new AtomicNode("AA2","defAssoc->union(");
		AA.insert(atNode3, atNode);
		decNode=new DecisionNode("AA3:DecisionNode:C3.2","if not self.parent.oclIsUndefined()");
		cond=new Condition("not self.parent.oclIsUndefined()");
		decNode.setcondition(cond);
		AA.insert(decNode, atNode3);//if
		compNode=new CompositeNode("AA4:Comp","self.parent.allAssociations");
		AA.insert(compNode, decNode);
		compNode.addChildNode(atNode);//else		
		atNode2=new AtomicNode("AA5","Sequence {}");
		AA.insert(atNode2, decNode);
		atNode3=new AtomicNode("AA6","endif;");
		AA.insert(atNode3, compNode);
		atNode3.setParentNode(atNode2);
		//AA.insert(atNode3, atNode2);
		atNode1=new AtomicNode("AA7",")->flatten();");
		AA.insert(atNode1, atNode3);
		atNode2=new AtomicNode("AA8","Sequence(SimpleClass!Association) = let defAssoc : Sequence(SimpleClass!Association) =");
		AA.insert(atNode, atNode1);
		return AA;				
	}

	public CFG funAllAttributes(){
		AtomicNode atNode, atNode1,atNode2,atNode3;
		DecisionNode decNode;
		CompositeNode compNode;
		Condition cond;
		//////////////////////////////////
		CFG AAt =new CFG();

		atNode=new AtomicNode("AAt1","self.attrs->union(");
		AAt.insert(atNode, null);
		decNode=new DecisionNode("AAt2:DecisionNode:C3.1","if not self.parent.oclIsUndefined()");
		cond=new Condition("not self.parent.oclIsUndefined()");
		decNode.setcondition(cond);
		AAt.insert(decNode, atNode);
		compNode=new CompositeNode("AAt3:Comp","then self.parent.allAttributes");
		AAt.insert(compNode, decNode);
		compNode.addChildNode(atNode);	
		atNode1=new AtomicNode("AAt4","->select(attr |not self.attrs->exists(at | at.name = attr.name))");
		AAt.insert(atNode1, compNode);
		atNode2=new AtomicNode("AAt5","else Sequence {}");
		AAt.insert(atNode2, decNode);
		atNode3=new AtomicNode("AAt6","endif;");
		AAt.insert(atNode3, atNode1);
		atNode3.setParentNode(atNode2);
		//AA.insert(atNode3, atNode2);
		atNode1=new AtomicNode("AAt7",")->flatten();");
		AAt.insert(atNode1, atNode3);	
		atNode2=new AtomicNode("AAt8","Sequence(SimpleClass!Attribute) =");
		AAt.insert(atNode2, atNode1);
		return AAt;				
	}
	
	public CFG funFlattenedFeature(){
		AtomicNode  atNode1,atNode2,atNode3,at1,at2;
		DecisionNode decNode;
		CompositeNode comp,compNode,compNode1,compNode2;
		Condition cond;
		//////////////////////////////////
		CFG FF =new CFG();

		comp=new CompositeNode("FF1:Comp","self.topParent");
		FF.insert(comp, null);
		FF.insert(this.funTopParent().getRootNode(), comp);
		//if self.topParent.is_persistent
		decNode=new DecisionNode("FF2:DecisionNode:C3","if self.topParent.is_persistent");
		cond=new Condition("self.topParent.is_persistent");
		decNode.setcondition(cond);
		FF.insert(decNode, comp);
		//self.topParent.attributesOfSubclasses->union(	self.topParent.associationsOfSubclasses)
		compNode=new CompositeNode("FF2.1:Comp","then self.topParent");
		FF.insert(compNode, decNode);
		FF.insert(this.funTopParent().getRootNode(), compNode);
		compNode1=new CompositeNode("FF2.2:Comp",".attributesOfSubclasses");
		FF.insert(compNode1, compNode);
		FF.insert(this.funAttributeofSubClasses().getRootNode(), compNode1);
		//->union(	
		atNode1=new AtomicNode("FF3","->union(");
		FF.insert(atNode1, compNode1);
		//self.topParent.associationsOfSubclasses)
		compNode2=new CompositeNode("FF3.1:Comp","self.topParent");
		FF.insert(compNode2, atNode1);
		FF.insert(this.funTopParent().getRootNode(), compNode2);
		compNode1=new CompositeNode("FF3.2:Comp",".associationsOfSubclasses)");
		FF.insert(compNode1, compNode2);
		FF.insert(this.funAssociationofSubClasses().getRootNode(), compNode1);
		//else		self.allAttributes->union(self.allAssociations)//else
		compNode=new CompositeNode("FF4.1:Comp","self.allAttributes");
		FF.insert(compNode, decNode);
		FF.insert(this.funAllAttributes().getRootNode(), compNode);
		atNode2=new AtomicNode("FF4.2","->union(");
		FF.insert(atNode2, compNode);
		compNode2=new CompositeNode("FF4.3:Comp","self.allAssociations)");
		FF.insert(compNode2, atNode2);
		FF.insert(this.funAllAssociations().getRootNode(), compNode2);
		atNode3=new AtomicNode("FF5","endif; ");
		FF.insert(atNode3, compNode1);
		atNode3.setParentNode(compNode2);
		//////->collect(f | let feature : TupleType(name : String,type : SimpleClass!Classifier,isPrimary : Boolean) =
		atNode1=new AtomicNode("FF6","->collect(f | let feature : TupleType(name : String,type : SimpleClass!Classifier,isPrimary : Boolean) = ");
		FF.insert(atNode1, atNode3);
		
		decNode=new DecisionNode("FF7:DecisionNode:C4","if f.oclIsKindOf(SimpleClass!Attribute)");
		cond=new Condition("f.oclIsKindOf(SimpleClass!Attribute)");
		decNode.setcondition(cond);
		FF.insert(decNode, atNode1);
		atNode1=new AtomicNode("FF8","then Tuple{name = f.name, type = f.type, isPrimary = f.is_primary} ");
		FF.insert(atNode1, decNode);
		atNode2=new AtomicNode("FF9","else Tuple{name = f.name, type = f.dest, isPrimary = false} ");
		FF.insert(atNode2, decNode);
		atNode3=new AtomicNode("FF10","endif; in");
		FF.insert(atNode3, atNode1);
		atNode3.setParentNode(atNode2);
		
		decNode=new DecisionNode("FF11:DecisionNode:C5","if feature.type.oclIsKindOf(SimpleClass!PrimitiveDataType)");
		cond=new Condition("feature.type.oclIsKindOf(SimpleClass!PrimitiveDataType)");
		decNode.setcondition(cond);
		FF.insert(decNode, atNode3);
		atNode3=new AtomicNode("FF12","then feature ");
		FF.insert(atNode3, decNode);
		atNode2=new AtomicNode("FF13","else");
		FF.insert(atNode2, decNode);
		//not feature.type.topParent.is_persistent
		compNode=new CompositeNode("FF14:Comp","feature.type.topParent");
		FF.insert(compNode, atNode2);
		FF.insert(this.funTopParent().getRootNode(), compNode);
		decNode=new DecisionNode("FF15:DecisionNode:C5.1","if not feature.type.topParent.is_persistent");
		cond=new Condition("not feature.type.topParent.is_persistent");
		decNode.setcondition(cond);
		FF.insert(decNode, compNode);
		atNode1=new AtomicNode("FF16","then");
		FF.insert(atNode1, decNode);
		//feature.type.flattenedFeatures->collect (f | Tuple{name=feature.name+ '_' + f.name, type=f.type, isPrimary=f.isPrimary})
		compNode1=new CompositeNode("FF17:Comp","feature.type.flattenedFeatures");
		FF.insert(compNode1, atNode1);
		compNode1.addChildNode(comp);
		atNode1=new AtomicNode("FF18","->collect (f | Tuple{name=feature.name+ '_' + f.name, type=f.type, isPrimary=f.isPrimary})");
		FF.insert(atNode1, compNode1);
		
		atNode2=new AtomicNode("FF19","else feature");
		FF.insert(atNode2, decNode);
		
		at1=new AtomicNode("FF20","endif;");
		FF.insert(at1, atNode1);
		at1.setParentNode(atNode2);
		
		//
		at2=new AtomicNode("FF21","endif;");
		FF.insert(at2, at1);
		at2.setParentNode(atNode3);
		
		atNode3=new AtomicNode("FF22","flattenedFeatures : Sequence(TupleType(name : String,type : SimpleClass!Classifier, isPrimary : Boolean )) =");
		FF.insert(atNode3, at2);
		return FF;				
	}
}

	

