<?xml version='1.0' encoding='UTF-8'?>
<!-- 
 title: UMLSD2STMD
 release: 0.1
 date: 18 oktober 2006

 author:      Bas Graaf
                      SWERL SoftWare Evolution Research Lab
                      Delft University of Technology
                      b.s.graaf@tudelft.nl
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:UML = 'org.omg.xmi.namespace.UML'>

<xsl:output method="xml" indent="yes"/>

  <xsl:template match="*|@*">
    <xsl:copy>
      <xsl:apply-templates select="@*"/>
      <xsl:apply-templates/>
    </xsl:copy>
  </xsl:template>

  <xsl:template match="//UML:Transition">
    <UML:Transition>
      <xsl:for-each select="UML:Transition.source">
        <xsl:attribute name="source">
          <xsl:value-of select="./*[1]/@xmi.idref"/>
        </xsl:attribute>
      </xsl:for-each>
      <xsl:for-each select="UML:Transition.target">
        <xsl:attribute name="target">
          <xsl:value-of select="./*[1]/@xmi.idref"/>
        </xsl:attribute>
      </xsl:for-each>
      <xsl:apply-templates select="*|@*"/>
    </UML:Transition>
  </xsl:template>

  <xsl:template match="//UML:Namespace.ownedElement/UML:CallEvent">
    <UML:SignalEvent>
      <xsl:apply-templates select="*|@*"/>      
    </UML:SignalEvent>
  </xsl:template>

  <xsl:template match="//UML:Transition/UML:Transition.trigger/UML:CallEvent">
    <UML:Event>
      <xsl:apply-templates select="*|@*"/>
    </UML:Event>    
  </xsl:template>

  <xsl:template match="//UML:Transition/UML:Transition.effect/UML:CallAction">
    <UML:ActionSequence>
      <xsl:apply-templates select="*|@*"/>
    </UML:ActionSequence>    
  </xsl:template>

  <xsl:template match="//UML:Transition/UML:Transition.effect/UML:CallAction/UML:Action.script">
    <UML:ActionSequence.action>
      <xsl:apply-templates select="*|@*"/>
    </UML:ActionSequence.action>    
  </xsl:template>

  <xsl:template match="//UML:Transition/UML:Transition.effect/UML:CallAction/UML:Action.script/UML:ActionExpression">
    <UML:UninterpretedAction>
      <xsl:apply-templates select="*|@*"/>
    </UML:UninterpretedAction>    
  </xsl:template>

  <xsl:template match="//UML:Transition/UML:Transition.effect/UML:CallAction/UML:Action.script/UML:ActionExpression/@body">
        <xsl:attribute name="name">
          <xsl:value-of select="."/>
        </xsl:attribute>

  </xsl:template>

</xsl:stylesheet>

