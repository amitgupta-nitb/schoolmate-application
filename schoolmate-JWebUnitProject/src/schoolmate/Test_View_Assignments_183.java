package schoolmate;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.InputElementFactory;



/**
 * @author AmitGupta
 *
 */
 public class Test_View_Assignments_183  extends Globals {
  
  WebTester tester;
  
  public String user = "parent";  
  public String pass = "parent";
  public String landingPageAssert = "Students of Bob Gupta";
  public String page = "5";  
  public String page2 = "2";  
  public String onpage = "";
  public String selectclass = "";
  public String form2 = "student";    
  public String form = "assignments";    
  public String pageName = "View Assignments";  
    
  
  @Before
  public void prepare(){
    tester = new WebTester();
	tester.setBaseUrl(baseUrl);
  }
  
  @Test
  public void pageAndPage2(){
    tester.beginAt("/index.php");
    tester.setTextField("username", user);
    tester.setTextField("password", pass);
    tester.submit();
    
    tester.assertMatch(landingPageAssert);
 
    tester.clickLinkWithExactText("Alice Gupta");
    tester.assertMatch("Alice Gupta's Classes");

    tester.clickLinkWithExactText("Presentation Skills");
    tester.assertMatch("Class Settings");

    tester.clickLinkWithExactText("Assignments");
    tester.assertMatch(pageName);
    
    tester.setWorkingForm(form);
    
    tester.setHiddenField("page", page + " '> <a href=\"hacker.it\">malicious link page</a><br '");
    tester.setHiddenField("page2", page2 + " '> <a href=\"hacker.it\">malicious link page2</a><br '");
    tester.setTextField("onpage", onpage + " '> <a href=\"hacker.it\">malicious link onpage</a><br '");
    
    addSubmitButton("//form[@name='"+form+"']");
    tester.submit();
    
    tester.assertMatch(pageName);
    assertMatch(tester, "malicious link page");
    assertMatch(tester, "malicious link page2");
    myAssertLink(tester, "malicious link onpage");
      
  }
  
  @Test
  public void selectClass(){
    tester.beginAt("/index.php");
    tester.setTextField("username", user);
    tester.setTextField("password", pass);
    tester.submit();
    
    tester.assertMatch(landingPageAssert);
 
    tester.setWorkingForm(form2);
    
    tester.setHiddenField("page", page + " '> <a href=\"hacker.it\">malicious link page</a><br '");
    tester.setHiddenField("page2", page2 + " '> <a href=\"hacker.it\">malicious link page2</a><br '");
    tester.setTextField("selectclass", selectclass + " '> <a href=\"hacker.it\">malicious link selectclass</a><br '");
    
    addSubmitButton("//form[@name='"+form2+"']");
    tester.submit();
    
    assertMatch(tester, "malicious link page");
    assertMatch(tester, "malicious link page2");
    myAssertLink(tester, "malicious link selectclass");
      
  }

  
  private void addSubmitButton(String fromXpath) {
    IElement element = tester.getElementByXPath(fromXpath);
    DomElement form = ((HtmlUnitElementImpl)element).getHtmlElement();
    InputElementFactory factory = InputElementFactory.instance;
    AttributesImpl attributes = new AttributesImpl();
    attributes.addAttribute("", "", "type", "", "submit");
    HtmlElement submit = factory.createElement(form.getPage(), "input", attributes);
    form.appendChild(submit);
  }

}