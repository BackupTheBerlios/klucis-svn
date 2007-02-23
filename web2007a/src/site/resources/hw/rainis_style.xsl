<!--
*
*			XSL stylesheet
*			autors: Maris Orbidans
*			
-->

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:fox="http://xml.apache.org/fop/extensions"
>

<xsl:output method="xml" indent="yes"/>

<xsl:template match="/">
<fo:root>
<!-- izveido graamatziimes -->
<xsl:apply-templates select="/" mode="gramatzimes"/>

<fo:layout-master-set>
<fo:simple-page-master margin-right="2cm" margin-left="3cm" margin-bottom="2cm" margin-top="2cm" page-width="21.0cm" page-height="29.7cm" master-name="first">
<fo:region-before extent="1cm"/>
<fo:region-body margin-bottom="1cm" margin-top="1cm"/>
<fo:region-after extent="1cm"/>
</fo:simple-page-master>

<fo:simple-page-master margin-right="2cm" margin-left="3cm" margin-bottom="2cm" margin-top="2cm" page-width="21.0cm" page-height="29.7cm" master-name="right">
<fo:region-before region-name="before-odd" extent="1cm"/>
<fo:region-body column-count="2" margin-bottom="1cm" margin-top="1cm"/>
<fo:region-after extent="1cm"/>
</fo:simple-page-master>

<fo:simple-page-master margin-right="3cm" margin-left="2cm" margin-bottom="2cm" margin-top="2cm" page-width="21.0cm" page-height="29.7cm" master-name="left">
<fo:region-before region-name="before-even" extent="1cm"/><fo:region-body column-count="2" margin-bottom="1cm" margin-top="1cm"/>
<fo:region-after extent="1cm"/>
</fo:simple-page-master>

<fo:page-sequence-master master-name="run">
<fo:repeatable-page-master-alternatives>
<fo:conditional-page-master-reference master-reference="first" page-position="first" />
<fo:conditional-page-master-reference	master-reference="left"	odd-or-even="odd" />
<fo:conditional-page-master-reference	master-reference="right" odd-or-even="even" />
</fo:repeatable-page-master-alternatives>
</fo:page-sequence-master>
</fo:layout-master-set>

<fo:page-sequence master-reference="run">
<fo:static-content flow-name="before-odd">
<fo:block line-height="10pt" font-family="Times" font-size="10pt" text-align="left">
<fo:inline font-size="12pt"><fo:page-number/>
</fo:inline>   Rainis: 
TĀLAS NOSKAŅAS ZILĀ VAKARĀ
</fo:block>

<fo:block><fo:leader space-after.optimum="12pt" leader-pattern="rule"/>
</fo:block>
</fo:static-content>

<fo:static-content flow-name="before-even">
<fo:block line-height="10pt" font-family="Times" font-size="10pt" text-align="right">
<fo:retrieve-marker retrieve-position="last-starting-within-page" retrieve-boundary="page" retrieve-class-name="nodala"/>
   <fo:inline font-size="12pt">
<fo:page-number/>
</fo:inline>
</fo:block>
<fo:block>
<fo:leader space-after.optimum="12pt" leader-pattern="rule"/>
</fo:block>
</fo:static-content>

<fo:flow flow-name="xsl-region-body">
<fo:block
	space-after.optimum="10pt">    
<fo:external-graphic src="file:rainis.jpg"/>  
</fo:block>
<fo:block font-size="32pt" 
	font-family="Times" 
	line-height="32pt"
	text-align="center"
	padding-top="3pt">
<xsl:value-of select="krajums/autors"/>: 
<xsl:value-of select="krajums/nosaukums/virsraksts"/>
</fo:block>

<fo:block>
<fo:leader leader-pattern="rule" space-before.optimum="12pt" space-after.optimum="12pt"/>
</fo:block>

<xsl:apply-templates select="krajums/nodala"/>

<!-- izveido dzejolju indeksu -->
<xsl:apply-templates select="/" mode="indekss"/>

</fo:flow> 
</fo:page-sequence> 
</fo:root>
</xsl:template>

<xsl:template match="nodala">
<fo:block font-size="18pt" 
    font-family="Times" 
    line-height="18pt"
    text-align="center"
    padding-top="3pt"
    break-before="page">
    <xsl:attribute name="id">
					<xsl:number level="single" count="nodala" format="1"/>
		</xsl:attribute>
		<xsl:value-of select="nosaukums/virsraksts"/>
</fo:block>
<xsl:apply-templates select="moto|dzejolis"/>
</xsl:template>

<xsl:template match="nodala" mode="gramatzimes">
<fox:outline>
	<xsl:attribute name="internal-destination">
		<xsl:number level="single" count="nodala" format="1"/>
	</xsl:attribute>
	<fox:label>
		<xsl:if test="nosaukums/virsraksts=''">*&#160;*&#160;*</xsl:if>
		<xsl:if test="nosaukums/virsraksts!=''"><xsl:value-of select="nosaukums/virsraksts"/></xsl:if>
	</fox:label>
	<xsl:apply-templates select="dzejolis" mode="gramatzimes"/>
</fox:outline>
</xsl:template>

<xsl:template match="moto" xml:space="preserve">
<fo:block font-size="10pt" 
    font-family="Antiqua" 
    line-height="10pt"
    text-align="right"
    padding-top="3pt"
    font-style="italic">
<xsl:for-each select="rinda">
<fo:block>
<xsl:value-of select="."/>
</fo:block>
</xsl:for-each>
</fo:block>
</xsl:template>

<xsl:template match="dzejolis">
<fo:block
    font-family="Times" 
    line-height="12pt"
    text-align="left"
    padding-top="20pt">
	<xsl:choose>
		<xsl:when test="ancestor::dzejolis">
			<xsl:attribute name="font-size">10pt</xsl:attribute>
			</xsl:when>
		<xsl:otherwise>
			<xsl:attribute name="font-size">12pt</xsl:attribute>
	    	<xsl:attribute name="id">
					<xsl:number level="multiple" count="nodala|dzejolis" format="1.1"/>
				</xsl:attribute>				
		</xsl:otherwise>
	</xsl:choose>
<xsl:if test="virsraksts=''">*&#160;*&#160;*</xsl:if>
<xsl:if test="virsraksts!=''"><xsl:value-of select="virsraksts"/></xsl:if>
</fo:block>
<xsl:apply-templates/>
</xsl:template>

<xsl:template match="dzejolis" mode="gramatzimes">
<xsl:if test="not(ancestor::dzejolis)">
	<fox:outline>
		<xsl:attribute name="internal-destination">
			<xsl:number level="multiple" count="nodala|dzejolis" format="1.1"/>
		</xsl:attribute>
		<fox:label>
			<xsl:if test="virsraksts=''"><xsl:value-of select="pants/rinda"/></xsl:if>
			<xsl:if test="virsraksts!=''"><xsl:value-of select="virsraksts"/></xsl:if>
		</fox:label>
	</fox:outline>
</xsl:if>
</xsl:template>

<xsl:template match="dzejolis" mode="indekss">
<xsl:if test="not(ancestor::dzejolis)">
	<fo:table-row>
		<fo:table-cell>
				
					<fo:block>
					<fo:inline white-space-collapse="true" font-style="normal" font-weight="normal" font-size="10pt" font-family="Times">
					<fo:marker marker-class-name="nodala">
					Dzejoļu indekss
					</fo:marker>
					</fo:inline>
					<fo:basic-link color="navy">
							<xsl:attribute name="internal-destination">
								<xsl:number level="multiple" count="nodala|dzejolis" format="1.1"/>
							</xsl:attribute>
							<xsl:if test="virsraksts=''"><xsl:value-of select="pants/rinda"/></xsl:if>
							<xsl:if test="virsraksts!=''"><xsl:value-of select="virsraksts"/></xsl:if>
					</fo:basic-link>
					</fo:block>
				</fo:table-cell>
				<fo:table-cell>
					<fo:block text-align="right">
					<fo:page-number-citation>
							<xsl:attribute name="ref-id">
								<xsl:number level="multiple" count="nodala|dzejolis" format="1.1"/>
							</xsl:attribute>
					</fo:page-number-citation>		
					</fo:block>				
		</fo:table-cell>
	</fo:table-row>
</xsl:if>
</xsl:template>

<xsl:template match="pants">
<fo:block padding-top="5pt"
    font-size="10pt" 
    font-family="Georgia"
    text-align="left">
<xsl:for-each select="rinda">
<fo:block>
<xsl:value-of select="."/>
</fo:block>
</xsl:for-each>
<fo:inline white-space-collapse="true" font-style="normal" font-weight="normal" font-size="10pt" font-family="Times">
	<fo:marker marker-class-name="nodala"><xsl:value-of select="ancestor::nodala/nosaukums/virsraksts"/></fo:marker>
</fo:inline>
</fo:block>
</xsl:template>

<xsl:template match="/" mode="gramatzimes">
	<xsl:apply-templates select="krajums/nodala" mode="gramatzimes" />
	<fox:outline>
	<xsl:attribute name="internal-destination">indekss</xsl:attribute>
	<fox:label>Dzejoļu indekss</fox:label>
	</fox:outline>
</xsl:template>

<xsl:template match="/" mode="indekss">
<fo:block id="indekss" break-before="page" padding-bottom="10pt" padding-top="3pt" text-align="center" line-height="16pt" font-family="Times" font-size="16pt">
Dzejoļu indekss
</fo:block>
<fo:table margin-bottom="10pt" margin-top="10pt">
<fo:table-column column-width="180pt"/>
<fo:table-column column-width="36pt"/>
<fo:table-body line-height="12pt" font-family="Times" font-size="9pt">
	<xsl:apply-templates select="krajums/nodala/dzejolis" mode="indekss">
		<xsl:sort data-type="text" case-order="lower-first" select="./virsraksts"/>
	</xsl:apply-templates>
</fo:table-body>
</fo:table>
</xsl:template>

<xsl:template match="text()">
<xsl:value-of select="."/>
</xsl:template>

<xsl:template match="*"/>

</xsl:stylesheet>