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
 public class Test_Edit_Assignment_37  extends Globals {
	
	WebTester tester;
	
	@Before
	public void prepare(){
		tester = new WebTester();
		tester.setBaseUrl(baseUrl);
	}
	
	@Test
	public void page(){
		tester.beginAt("/index.php");
		tester.setTextField("username", "professor");	
		tester.setTextField("password", "professor");	
		tester.submit();
		
		tester.assertMatch("Steve Jobs's Classes");
		tester.clickLinkWithText("Presentation Skills");
		
		tester.assertMatch("Class Settings");
		tester.clickLinkWithText("Assignments");
		
		tester.assertMatch("Manage Assignments");
		tester.setWorkingForm("assignments");
		tester.checkCheckbox("delete[]");
		
		tester.setTextField("page", "2 '> <a href=\"hacker.it\">malicious link page</a><br '");
		tester.setTextField("page2", "5 '> <a href=\"hacker.it\">malicious link page2</a><br '");
		tester.setTextField("selectclass", "1 '> <a href=\"hacker.it\">malicious link selectclass</a><br '");
		
		addSubmitButton("//form[@name='assignments']");
		tester.submit();
		tester.assertMatch("Edit Assignment");
		
		myAssertLink(tester, "malicious link page");
		myAssertLink(tester, "malicious link page2");
		myAssertLink(tester, "malicious link selectclass");

//		tester.assertLinkNotPresentWithText("malicious link page");
			
	}
	
	@Test
	public void page2(){
		tester.beginAt("/index.php");
		tester.setTextField("username", "professor");	
		tester.setTextField("password", "professor");	
		tester.submit();
		
		tester.assertMatch("Steve Jobs's Classes");
		tester.clickLinkWithText("Presentation Skills");
		
		tester.assertMatch("Class Settings");
		tester.clickLinkWithText("Assignments");
		
		tester.assertMatch("Manage Assignments");
		tester.setWorkingForm("assignments");
		tester.checkCheckbox("delete[]");
		
		tester.setTextField("page2", "5 '> <a href=\"hacker.it\">malicious link page2</a><br '");
		addSubmitButton("//form[@name='assignments']");
		tester.submit();
		tester.assertMatch("Edit Assignment");
		
		myAssertLink(tester, "malicious link page2");
//		tester.assertLinkNotPresentWithText("malicious link page");
			
	}
	
	@Test
	public void selectclass(){
		tester.beginAt("/index.php");
		tester.setTextField("username", "professor");	
		tester.setTextField("password", "professor");	
		tester.submit();
		
		tester.assertMatch("Steve Jobs's Classes");
		tester.clickLinkWithText("Presentation Skills");
		
		tester.assertMatch("Class Settings");
		tester.clickLinkWithText("Assignments");
		
		tester.assertMatch("Manage Assignments");
		tester.setWorkingForm("assignments");
		
		tester.checkCheckbox("delete[]");
		
		tester.setTextField("page2", "5 '> <a href=\"hacker.it\">malicious link page2</a><br '");
		tester.setTextField("selectclass", "1 '> <a href=\"hacker.it\">malicious link selectclass</a><br '");
		addSubmitButton("//form[@name='assignments']");
		tester.submit();
		tester.assertMatch("Edit Assignment");
		
		myAssertLink(tester, "malicious link selectclass");
		myAssertLink(tester, "malicious link page2");
//		tester.assertLinkNotPresentWithText("malicious link page");
			
	}
	
	@Test
	public void delete(){
		tester.beginAt("/index.php");
		tester.setTextField("username", "professor");	
		tester.setTextField("password", "professor");	
		tester.submit();
		
		tester.assertMatch("Steve Jobs's Classes");
		tester.clickLinkWithText("Presentation Skills");
		
		tester.assertMatch("Class Settings");
		tester.clickLinkWithText("Assignments");
		
		tester.assertMatch("Manage Assignments");
		tester.setWorkingForm("assignments");
		
		tester.checkCheckbox("delete[]");
		
		tester.setTextField("page2", "5 '> <a href=\"hacker.it\">malicious link page2</a><br '");
		tester.setTextField("delete[]", "1 '> <a href=\"hacker.it\">Click Here to resolve!</a><br '");
		addSubmitButton("//form[@name='assignments']");
		tester.submit();
		//tester.assertMatch("Edit Assignment"); //doesn't reach the Edit assignment page as xss is sent on error note
		
		myAssertLink(tester, "Click Here to resolve!");
		myAssertLink(tester, "malicious link page2");
//		tester.assertLinkNotPresentWithText("malicious link page");
			
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
