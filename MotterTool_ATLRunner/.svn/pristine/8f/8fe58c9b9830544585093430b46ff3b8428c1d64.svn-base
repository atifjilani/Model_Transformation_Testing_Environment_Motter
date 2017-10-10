UML Sequence Diagrams to UML Statechart Diagrams

Bas Graaf
SWERL - SoftWare Evolution Research Lab
Delft University of Technology
b.s.graaf@tudelft.nl


These transformations transform a set of UML sequence diagrams into a
(hierarchical) UML statechart.  The transformations are based on the
algorithm described by [1]. The complete generation of a statechart
from a set of scenarios comprises four consecutive model
transformations:

applyDomainTheory: Applies pre- and postconditions specified on a
class' operations to messages in sequence diagrams sent or received by
objects of that class.

SD2flatSTMD: Generates for every sequence diagram a `flat' state
machine for the object of the class that is marked
active. Furthermore, based on the pre- and postconditions applied to
the messages in the sequence diagrams, it applies a set of constraint
to each generated state. These constraints are state invariants. Each
set is referred to as a state vector and describes the current state
of the object in terms of state variables.

mergeflatSTMD: States that are identical, based on their state
vectors, are merged. Two state are identical if their state vectors
are unifiable. Redundant transitions (as result of the merging) are
discarded. For merging we only consider a partition of the state
variables. The user can indicate the state variables to be included in
the partition by setting the visibility of their corresponding class
attributes to public, and that of all others to private.

addHierarchy: Using an ordering on the state variables in the
selected partition, composite states are added to the state chart. The
ordering is represented by the order in which corresponding attributes
occur on the class (the top one is most important).

The example input model is the same ATM example as used in [1]. In
general input models contain:

* a set of sequence diagrams: 
  All messages should correspond to operations in the class model.

* a class model:
  For all objects that occur in the sequence diagram a class is
  modelled that include all operations referred to in the sequence
  diagrams. To these operations pre- and postconditions can be applied
  as constraints having <<precondition>>, and <<postcondition>>
  stereotypes. These constraints have the form:
  {object.statevariable=value}.

  The class of interest (for which to generate a statechart) should be
  marked active. Its state variables are added as attributes. Pubic
  attributes are included in the state variable partition for adding
  hierarchy, private attributes not. Every attribute is of a dedicated
  type. This is an enumeration that represents (an abstraction of) the
  domain of the statevariable. Enumeration literals are encoded as
  attribute names.

Poseidon for UML 3.01 Community Edition was used to create the input
model. The output was visualized using Borland's Together. Here, we
used two different tools because a metamodel was available for
Poseidon, while Together on the other hand is capable of displaying
UML models that lack diagram information. To transform the XMI flavor
used by Poseidon into that of Together a simple XSLT transformation was
used.


CONTENTS

./models: 
* The ATM example is in atm.zuml and was
  exported to atm.xmi. This is the source model for the first
  transformation (applyDomainTheory
* All target models are named similar to the transformation.
* Models suffixed with _tcc.xmi are the result of applying the
  poseidon2together XSLT transformation. These can be imported into
  Together.

./transformations:
* ATL transformations corresponding to the steps described above
 (these are to be executed in the following order: applyDomainTheory
 -> SD2flatSTMD -> mergeflatSTMD -> addHierarchy applyDomainTheory
 used ./models/atm.xmi as source model the others use the target of
 the previous transformation as source model)

* XSLT transformation Poseidon->Together

./metamodel:
* Metamodel used by Poseidon (3.01)

./doc
* A together project containing diagrams of the results of the
  transformations,
* Screen-dumps of the diagrams of the ATM input example,
* Screen-dumps of the results of the transformations,
* Screen-dumps of Eclipse run configurations,



[1] Jon Whittle and Johann Schumann. Generating statechart designs
from scenarios. In Proc. 22nd Int'l Conf. Software Engineering (ICSE
2000), pages 314-323. IEEE CS, 2000.