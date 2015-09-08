<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:nt="http://www.jcp.org/jcr/nt/1.0">
    <xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
    <xsl:template match="/">
        <xsl:copy>
            <xsl:apply-templates select="NewsML/NewsItem/NewsComponent/NewsLines/HeadLine" />
        </xsl:copy>
    </xsl:template>

 	<xsl:template match="NewsML/NewsItem/NewsComponent/NewsLines/HeadLine">
        <xsl:output method="text"/>
        <xsl:value-of select="." />
    </xsl:template>
</xsl:stylesheet>