<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:nt="http://www.jcp.org/jcr/nt/1.0">

	<xsl:output indent="yes" />
	<xsl:param name="tags" />
	<xsl:param name="damFolder"/>
	<xsl:param name="sectionPath"/>
	<xsl:param name="siteProperty"/>
	<xsl:param name="referrerNodeName"/>
    <xsl:param name="organizationsTags"/>
    <xsl:param name="peopleTags"/>
    <xsl:param name="placesTags"/>
	<xsl:param name="smallPhotoText"/>
	<xsl:variable name="referrer"><xsl:value-of select="$referrerNodeName"/></xsl:variable>
	<xsl:template match="/">
		<xsl:copy>
			<xsl:apply-templates select="NewsML/NewsItem" />
		</xsl:copy>
	</xsl:template>
		<xsl:variable name="year"><xsl:value-of select="substring(NewsML/NewsItem/NewsManagement/FirstCreated,1,4)" /></xsl:variable>
		<xsl:variable name="month"><xsl:value-of select="substring(NewsML/NewsItem/NewsManagement/FirstCreated,6,2)" /></xsl:variable>
		<xsl:variable name="day"><xsl:value-of select="substring(NewsML/NewsItem/NewsManagement/FirstCreated,9,2)" /></xsl:variable>
		<xsl:variable name="newsId"><xsl:value-of select="NewsML/NewsItem/Identification/NewsIdentifier/NewsItemId" /></xsl:variable>
		<xsl:variable name="fullDamPath"><xsl:value-of select="$damFolder"/>/<xsl:value-of select="number($year)"/>/<xsl:value-of select="number($month)"/>/<xsl:value-of select="number($day)"/>/<xsl:value-of select="$newsId"/></xsl:variable>

	<xsl:template match="NewsItem">
		<xsl:output method="xml" indent="yes" />
		<xsl:element name="jcr:root">
			<xsl:copy-of
					select="document('')/xsl:stylesheet/namespace::*[name()!='xsl']" />
			<xsl:attribute name="jcr:primaryType">cq:Page</xsl:attribute>

			<xsl:element name="jcr:content">
	            <xsl:attribute name="cq:designPath">/etc/designs/tc</xsl:attribute>
				<xsl:attribute name="cq:template">/apps/tc/templates/assets/article</xsl:attribute>
				<xsl:attribute name="jcr:primaryType">cq:PageContent</xsl:attribute>
				<xsl:attribute name="jcr:title"><xsl:value-of select="./NewsComponent/NewsLines/HeadLine" /></xsl:attribute>
				<xsl:attribute name="sling:resourceType">tc/components/assets/article</xsl:attribute>
				<xsl:attribute name="author">/content/tc/assets/authors/c/canadian_press</xsl:attribute>
				<!-- excludeFromFeaturedNews property is meant for excluding the article from appearing in featuredNews component -->
				<xsl:attribute name="excludeFromFeaturedNews">true</xsl:attribute>
				<xsl:attribute name="site"><xsl:value-of select="$siteProperty"/></xsl:attribute>
				<xsl:attribute name="section"><xsl:value-of select="$sectionPath"/></xsl:attribute>
				<xsl:attribute name="templateType">smallmedia</xsl:attribute>
                <xsl:attribute name="source">Canadian Press</xsl:attribute>
                <xsl:attribute name="sourceId"></xsl:attribute>
                <xsl:attribute name="sourceVersion"></xsl:attribute>
				<xsl:attribute name="onTime">{Date}<xsl:value-of select="./NewsManagement/FirstCreated" />.000-05:00</xsl:attribute>
                <xsl:attribute name="createdDate"><xsl:value-of select="./NewsManagement/FirstCreated" /></xsl:attribute>
                <xsl:attribute name="newsId"><xsl:value-of select="./Identification/NewsIdentifier/NewsItemId" /></xsl:attribute>
				
   				<xsl:attribute name="cq:tags"><xsl:value-of select="$tags" /></xsl:attribute>
                <xsl:if test="$organizationsTags">
                    <xsl:attribute name="organizationsTags"><xsl:value-of select="$organizationsTags" /></xsl:attribute>
                </xsl:if>

                <xsl:if test="$peopleTags">
                    <xsl:attribute name="peopleTags"><xsl:value-of select="$peopleTags" /></xsl:attribute>
                </xsl:if>

                <xsl:if test="$placesTags">
                    <xsl:attribute name="placesTags"><xsl:value-of select="$placesTags" /></xsl:attribute>
                </xsl:if>
				
				<xsl:element name="content-small-media">
					<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
					<xsl:attribute name="sling:resourceType">foundation/components/parsys</xsl:attribute>

					<!--  there can be multiple ContentItem -->		
						<xsl:apply-templates
							select="./NewsComponent" />
					<xsl:element name="articletext">
	                   <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
	                   <xsl:attribute name="sling:resourceType">tc/components/modules/articletext</xsl:attribute>
	                </xsl:element>
				</xsl:element>
				
				<xsl:element name="referringPages">
				  <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
				  <xsl:element name="{$referrer}">
					   <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
	                   <xsl:attribute name="isCanonical">true</xsl:attribute>
	                   <xsl:attribute name="name"><xsl:value-of select="$siteProperty"/></xsl:attribute>
	                   <xsl:attribute name="section"><xsl:value-of select="$sectionPath"/></xsl:attribute>
	              </xsl:element>
				</xsl:element>
			</xsl:element>
		</xsl:element>
		
		
	</xsl:template>
	
	<xsl:template match="NewsItem/NewsComponent">
		<xsl:for-each select="./ContentItem/DataContent/CPOnlineFile/CPLink">
			<xsl:apply-templates select="." >
			<!-- For multiple CPLinks -->
				<!-- <xsl:with-param name="imageId" select="position()"/> -->
				<xsl:with-param name="imageId" select="1"/>
			</xsl:apply-templates>
		</xsl:for-each>
	    <xsl:if test="not(./ContentItem/DataContent/CPOnlineFile/CPLink)">
            <xsl:element name="small-photo">
                <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                <xsl:attribute name="text"><xsl:value-of select="$smallPhotoText" /></xsl:attribute>
                <xsl:attribute name="sling:resourceType">tc/components/modules/articletextimage</xsl:attribute>
            </xsl:element>
	    </xsl:if>
	</xsl:template>
	
	<!-- image component mapping for CPLink -->
	<xsl:template
		match="NewsComponent/ContentItem/DataContent/CPOnlineFile/CPLink">
		<xsl:param name="imageId"/>
		<xsl:if test="contains(@SourceFilePath, '.jpg')"> 
			<xsl:element name="small-photo">
				<xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
				<xsl:attribute name="caption"><xsl:value-of select="@Caption" /></xsl:attribute>
				<xsl:attribute name="text"><xsl:value-of select="$smallPhotoText" /></xsl:attribute>
				<xsl:attribute name="jcr:title"><xsl:value-of select="@Caption" /></xsl:attribute>
				<xsl:attribute name="height"><xsl:value-of select="206" /></xsl:attribute>
				<xsl:attribute name="width"><xsl:value-of select="310" /></xsl:attribute>
				<xsl:attribute name="photoType">310*206</xsl:attribute>
				<xsl:attribute name="largePhotoSize">650*433</xsl:attribute>
				<xsl:attribute name="quality">full</xsl:attribute>
				<xsl:attribute name="scalingEnabled">true</xsl:attribute>
                <xsl:variable name="ref"><xsl:value-of select="@SourceFilePath" /></xsl:variable>

				<xsl:if test="starts-with($ref, './')">
					<xsl:attribute name="fileReference"><xsl:copy-of select="$fullDamPath" />/<xsl:copy-of select="substring($ref, 3)" /></xsl:attribute>
				</xsl:if>
				
				<xsl:if test="not(starts-with($ref, './'))">
					<xsl:attribute name="fileReference"><xsl:copy-of select="$fullDamPath" />/<xsl:copy-of select="$ref"/></xsl:attribute>
				</xsl:if>
				
				<xsl:attribute name="sling:resourceType">tc/components/modules/articletextimage</xsl:attribute>
			</xsl:element>
		</xsl:if>
	</xsl:template>
	
</xsl:stylesheet>