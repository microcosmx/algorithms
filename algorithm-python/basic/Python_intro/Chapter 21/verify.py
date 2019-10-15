import sys
from javax.servlet.http import HttpServlet
 
class verify(HttpServlet):
  def doGet(self, request, response):
     self.handleRequest(request, response)

  def doPost(self, request, response):
     self.handleRequest(request, response)
     
  def handleRequest(self, request, response):
     response.setContentType("text/html");
     
     out = response.getOutputStream()
     print >>out, "<html><head><title>"
     print >>out, "Jython Is Running</title></head>"
     print >>out, "<body>"
     print >>out, "<h2>Jython is running</h2>"
     print >>out, "<p>"
     print >>out, "Version:", sys.version, " verified."
     print >>out, "</p>"
     print >>out, "</body></html>"
     out.close()
     return
