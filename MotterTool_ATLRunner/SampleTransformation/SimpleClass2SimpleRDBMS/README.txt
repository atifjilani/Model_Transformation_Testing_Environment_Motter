This transformation implements the case study used in the Workshop Model Transformations
in Practice collocated with the MoDELS Conference, October, 2005. The description of the
case study can be found at the workshop web site: http://sosym.dcs.kcl.ac.uk/events/mtip05/.

Two versions of the SimpleClass2SimpleRDBMS transformation have been developed:
- The first one, which is presented in [1], uses some ATL features that are not implemented
in the engine yet. Its purpose is to illustrate the planned features of the language.
- The second one, coming with this README.txt, is another version that works with
the ATL engine available on http://eclipse.org/gmt as of November 2005. This second version
uses roughly the same helpers as the first version.

Example files:

	SimpleClass.km3 - the source metamodel expressed in KM3.

	SimpleRDBMS.km3 - the target metamodel expressed in KM3.

	KM32SimpleClass.atl - ATL transformation that creates a model conforming to
            the source metamodel from a KM3 model: SimpleClass is really close
            to KM3 and we do not want to type a sample in XMI.

	Sample.km3 - this is a sample source model expressed in KM3. It must be transformed to 
			a SimpleClass model by using the KM32SimpleClass transformation.
			
	Sample-KM3.ecore - this is an XMI version of Sample.km3: a sample KM3 model.

	Sample-SimpleClass.ecore - a sample source model obtained by applying KM32SimpleClass
			to Sample.km3.

	SimpleClass2SimpleRDBMS.atl - the implementation of the case study in ATL.

	Sample-SimpleRDBMS.ecore - the resulting model derived from Sample_SimpleClass.ecore after applying 
            the SimpleClass2SimpleRDBMS transformation.

References:

[1] Jouault, F., and Kurtev, I., Transforming Models with ATL, in proceedings of Model Transformations in Practice
Workshop, October 3rd 2005, part of the MoDELS 2005 Conference
This paper may be obtained from http://sosym.dcs.kcl.ac.uk/events/mtip05/submissions/jouault_kurtev__transforming_models_with_atl.pdf