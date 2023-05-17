<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <head>
        <title>Blood Information</title>
      </head>
      <body>
        <h1>Blood Donation Information</h1>
        <table border="1">
          <tr>
            <th>ID</th>
            <th>Amount</th>
            <th>Date</th>
            <th>Donor</th>
            <th>Donee</th>
          </tr>
          <xsl:for-each select="Blood">
            <tr>
              <td><xsl:value-of select="id"/></td>
              <td><xsl:value-of select="amount"/></td>
              <td><xsl:value-of select="fecha"/></td>
              <td><xsl:value-of select="donor"/></td>
              <td><xsl:value-of select="donee"/></td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
