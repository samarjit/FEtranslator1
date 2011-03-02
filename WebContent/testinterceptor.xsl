<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
<xsl:output method="html"/>
<xsl:template match="result">
<html>
<body>
      <h4><xsl:value-of select="testHeader"/></h4>
      <br/>
      <xsl:value-of select="testFooter"/>
</body>
</html>
</xsl:template>
</xsl:stylesheet>