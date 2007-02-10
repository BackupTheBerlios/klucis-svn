<?xml version="1.0"?>
<xsl:stylesheet xmlns:svg="http://www.w3.org/2000/svg" 
  xmlns:xlink="http://www.w3.org/1999/xlink" 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0">
 
<xsl:output method="html"/>
<xsl:template match="/">
<html>
<head>
<title>SVG Transformed as HTML for Selenium Testing</title>
</head>
<body style="font-family:Arial;font-size:10pt">
<xsl:apply-templates />
</body>
</html>
</xsl:template>
    
<xsl:template match="*">
<div style="border: 1px solid black; margin: 5px">
<b><xsl:value-of select="name()"/></b><br />
<xsl:for-each select="@*">
<em style="color:blue"><xsl:value-of select="name(.)"/></em>&#160;&#160;:&#160;&#160;<code><xsl:value-of select="."/></code><br />
</xsl:for-each>
<xsl:apply-templates/>
</div>
</xsl:template>

<xsl:template match="text()">
<xsl:if test="not(normalize-space(.) = ' ' or normalize-space(.) = '')">
<div style="border: 1px solid black; margin: 5px">
<span style="color:red"><xsl:value-of select="."/></span>
</div>
</xsl:if>
</xsl:template>

<!--
<xsl:template match="@*">
<em><xsl:value-of select="name()"/></em>: <em><xsl:value-of select="."/></em>;
</xsl:template>
-->


</xsl:stylesheet>