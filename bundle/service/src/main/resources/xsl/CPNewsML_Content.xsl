<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:nt="http://www.jcp.org/jcr/nt/1.0">

    <xsl:output indent="yes" />
    <xsl:param name="tags" />
    <xsl:param name="sectionPath"/>
    <xsl:param name="sitePath"/>
    <xsl:param name="organizationsTags"/>
    <xsl:param name="peopleTags"/>
    <xsl:param name="placesTags"/>

    <xsl:param name="assetLink"/>
    <xsl:param name="articleNodeName"/>
       
    
    <xsl:template match="/">
        <xsl:copy>
            <xsl:apply-templates select="NewsML/NewsItem" />
        </xsl:copy>
    </xsl:template>
        <xsl:variable name="year"><xsl:value-of select="substring(NewsML/NewsItem/NewsManagement/FirstCreated,1,4)" /></xsl:variable>
        <xsl:variable name="month"><xsl:value-of select="substring(NewsML/NewsItem/NewsManagement/FirstCreated,6,2)" /></xsl:variable>
        <xsl:variable name="day"><xsl:value-of select="substring(NewsML/NewsItem/NewsManagement/FirstCreated,9,2)" /></xsl:variable>
        <xsl:variable name="newsId"><xsl:value-of select="NewsML/NewsItem/Identification/NewsIdentifier/NewsItemId" /></xsl:variable>

    <xsl:template match="NewsItem">
        <xsl:output method="xml" indent="yes" />
        <xsl:element name="jcr:root">
            <xsl:copy-of
                    select="document('')/xsl:stylesheet/namespace::*[name()!='xsl']" />
            <xsl:attribute name="jcr:primaryType">cq:Page</xsl:attribute>

            <xsl:element name="jcr:content">
                <xsl:attribute name="cq:designPath">/etc/designs/tc</xsl:attribute>
                <xsl:attribute name="cq:template">/apps/tc/templates/pagetypes/article</xsl:attribute>
                <xsl:attribute name="jcr:primaryType">cq:PageContent</xsl:attribute>
                <xsl:attribute name="jcr:title"><xsl:value-of select="./NewsComponent/NewsLines/HeadLine" /></xsl:attribute>
                <xsl:attribute name="sling:resourceType">/apps/tc/components/pagetypes/article</xsl:attribute>
                <xsl:attribute name="author">/content/tc/assets/authors/c/canadian_press</xsl:attribute>
                <xsl:attribute name="excludeFromFeaturedNews">true</xsl:attribute>
                <xsl:attribute name="commentModerationCheck">true</xsl:attribute>
                <xsl:attribute name="commentModerationFlag">true</xsl:attribute>
                <xsl:attribute name="site"><xsl:value-of select="$sitePath"/></xsl:attribute>
                <xsl:attribute name="templateType">smallmedia</xsl:attribute>
                <xsl:attribute name="sourceName">Canadian Press</xsl:attribute>
                <xsl:attribute name="onTime">{Date}<xsl:value-of select="./NewsManagement/FirstCreated" />.000-05:00</xsl:attribute>
                <xsl:attribute name="section"><xsl:value-of select="$sectionPath"/></xsl:attribute>
                <xsl:attribute name="assetLink"><xsl:value-of select="$assetLink"/>/<xsl:value-of select="number($year)"/>/<xsl:value-of select="number($month)"/>/<xsl:value-of select="number($day)"/>/<xsl:value-of select="$articleNodeName"/>-<xsl:value-of select="$newsId"/></xsl:attribute>
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
                
                <xsl:element name="title">
                        <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>             
                         <xsl:element name="start">
                            <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>  
                            <xsl:attribute name="sling:resourceType">foundation/components/form/start</xsl:attribute>
                            <xsl:attribute name="actionType">/apps/tc/components/modules/send_email_friend/submitForm</xsl:attribute> 
                            <xsl:attribute name="formid">email_form</xsl:attribute>            
                         </xsl:element>
                          <xsl:element name="friendname">
                            <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>  
                            <xsl:attribute name="sling:resourceType">foundation/components/form/text</xsl:attribute>
                            <xsl:attribute name="sling:resourceSuperType">foundation/components/form/defaults/field</xsl:attribute> 
                            <xsl:attribute name="formid">email_form</xsl:attribute> 
                            <xsl:attribute name="name">friendName</xsl:attribute>  
                            <!-- <xsl:attribute name="jcr:title">Friends Name</xsl:attribute> -->
                            
                                  <xsl:attribute name="jcr:title">Friends Name</xsl:attribute>
                                                            
                            <xsl:attribute name="constraintType">foundation/components/form/constraints/name</xsl:attribute> 
                            <xsl:attribute name="required">true</xsl:attribute>            
                         </xsl:element>
                         <xsl:element name="friendemail">
                            <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>  
                            <xsl:attribute name="sling:resourceType">foundation/components/form/text</xsl:attribute>
                            <xsl:attribute name="sling:resourceSuperType">foundation/components/form/defaults/field</xsl:attribute> 
                            <xsl:attribute name="formid">email_form</xsl:attribute> 
                            <xsl:attribute name="name">friendEmail</xsl:attribute>  
                            <!-- <xsl:attribute name="jcr:title">Friends Email</xsl:attribute> -->                             
                                  <xsl:attribute name="jcr:title">Friends Email</xsl:attribute>                               
                            <xsl:attribute name="constraintType">foundation/components/form/constraints/email</xsl:attribute> 
                            <xsl:attribute name="required">true</xsl:attribute>            
                         </xsl:element>
                         <xsl:element name="personname">
                            <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>  
                            <xsl:attribute name="sling:resourceType">foundation/components/form/text</xsl:attribute>
                            <xsl:attribute name="sling:resourceSuperType">foundation/components/form/defaults/field</xsl:attribute> 
                            <xsl:attribute name="formid">email_form</xsl:attribute> 
                            <xsl:attribute name="name">personName</xsl:attribute>  
                           <!--  <xsl:attribute name="jcr:title">Your Name</xsl:attribute>  -->
                                    <xsl:attribute name="jcr:title">Your Name</xsl:attribute>
                                
                            
                            <xsl:attribute name="constraintType">foundation/components/form/constraints/name</xsl:attribute> 
                            <xsl:attribute name="required">true</xsl:attribute>            
                         </xsl:element>
                         <xsl:element name="personemail">
                            <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>  
                            <xsl:attribute name="sling:resourceType">foundation/components/form/text</xsl:attribute>
                            <xsl:attribute name="sling:resourceSuperType">foundation/components/form/defaults/field</xsl:attribute> 
                            <xsl:attribute name="formid">email_form</xsl:attribute> 
                            <xsl:attribute name="name">personEmail</xsl:attribute>  
                           <!--  <xsl:attribute name="jcr:title">Your Email</xsl:attribute> -->                            
                                  <xsl:attribute name="jcr:title">Your Email</xsl:attribute>                               
                            <xsl:attribute name="constraintType">foundation/components/form/constraints/email</xsl:attribute> 
                            <xsl:attribute name="required">true</xsl:attribute>            
                         </xsl:element>
                         <xsl:element name="comments">
                            <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>  
                            <xsl:attribute name="sling:resourceType">foundation/components/form/text</xsl:attribute>
                            <xsl:attribute name="sling:resourceSuperType">foundation/components/form/defaults/field</xsl:attribute> 
                            <xsl:attribute name="name">comments</xsl:attribute>  
                            <!-- <xsl:attribute name="jcr:title">Comments</xsl:attribute> -->
                                  <xsl:attribute name="jcr:title">Comments</xsl:attribute>                              
                            <xsl:attribute name="cols">40</xsl:attribute> 
                            <xsl:attribute name="rows">3</xsl:attribute>          
                         </xsl:element>
                         <xsl:element name="captcha">
                            <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>  
                            <xsl:attribute name="sling:resourceType">foundation/components/form/captcha</xsl:attribute>
                            <xsl:attribute name="sling:resourceSuperType">foundation/components/form/defaults/field</xsl:attribute> 
                            <xsl:attribute name="name">Captcha</xsl:attribute>  
                            <!-- <xsl:attribute name="jcr:title">captcha</xsl:attribute> -->                               
                                <xsl:attribute name="jcr:title">Captcha</xsl:attribute>                               
                            <xsl:attribute name="required">true</xsl:attribute>  
                            <xsl:attribute name="constraintMessage">Validation Failed</xsl:attribute>          
                         </xsl:element>
                          <xsl:element name="end">
                            <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>  
                            <xsl:attribute name="sling:resourceType">foundation/components/form/end</xsl:attribute>
                           <!--  <xsl:attribute name="jcr:title">Submit</xsl:attribute> -->
                                <xsl:attribute name="jcr:title">Submit</xsl:attribute>                             
                            <xsl:attribute name="submit">true</xsl:attribute>  
                         </xsl:element>
                    </xsl:element>

                
                
                
                 <xsl:element name="content-left">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="sling:resourceType">foundation/components/parsys</xsl:attribute>
                    <xsl:element name="comments">
                           <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                           <xsl:attribute name="moderateComments">true</xsl:attribute>
                           <xsl:attribute name="sling:resourceType">tc/components/modules/hbs/comments</xsl:attribute>
                    </xsl:element>
                    <xsl:element name="related-articles">
                        <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                        <xsl:attribute name="sling:resourceType">tc/components/modules/relatedarticles</xsl:attribute>
                    </xsl:element>
                    <xsl:element name="featured-news">
                        <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                        <xsl:attribute name="sling:resourceType">tc/components/modules/featured-news-article-variation</xsl:attribute>
                        <xsl:attribute name="articlesRoot"><xsl:value-of select="$sitePath" /></xsl:attribute>
                        <xsl:attribute name="articleType">smalltemplate-3</xsl:attribute>
                        <xsl:attribute name="isMainFeaturedNews">true</xsl:attribute>
                        <xsl:attribute name="title">TOP NEWS</xsl:attribute>
                        <xsl:element name="position_0">
                                  <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                                  <xsl:attribute name="sling:resourceType">/apps/tc/components/modules/feature-news-items/smalltemplate1-position1</xsl:attribute>
                        </xsl:element>
                        <xsl:element name="position_1">
                            <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                            <xsl:attribute name="sling:resourceType">/apps/tc/components/modules/feature-news-items/feature-news-item-smalltemplate3</xsl:attribute>
                        </xsl:element>
                        <xsl:element name="position_2">
                            <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                            <xsl:attribute name="sling:resourceType">/apps/tc/components/modules/feature-news-items/feature-news-item-smalltemplate3</xsl:attribute>
                        </xsl:element>
                    </xsl:element>
                 </xsl:element>
                <xsl:element name="content-small-media">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="sling:resourceType">foundation/components/parsys</xsl:attribute>
                </xsl:element>
                <xsl:element name="simple-search">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="sling:resourceType">tc/components/modules/simple-search</xsl:attribute>
                </xsl:element>

                 <xsl:element name="content-bottom">
                    <xsl:attribute name="jcr:primaryType">nt:unstructured</xsl:attribute>
                    <xsl:attribute name="sling:resourceType">foundation/components/parsys</xsl:attribute>
                 </xsl:element>

            </xsl:element>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
