package FrameWork;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Locator {


    private By by;
    private String name;
    private WebElement element;


    public Locator(By by, String name) {
        super();
        this.by = by;
        this.name = name;
    }

    public Locator(WebElement element, By by, String name) {
        super();
        this.by = by;
        this.name = name;
        this.element = element;
    }


    public By getBy() {
        return by;
    }


    public void setBy(By by) {
        this.by = by;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Locator concatLocator(Locator locator) {
        Locator newlocator = new Locator(null, null);
        By by = By.xpath(getByContentString(this.by).concat(getByContentString(locator.by)));
        String name = this.name.concat(locator.name);
        newlocator.setBy(by);
        newlocator.setName(name);
        return newlocator;


    }


    public Locator getCustomLocator(WebElement element, String elementname, Locator locator, WebDriver driver) {
        Locator newLocator = new Locator(null, null);
        String Xpath = getAbsoluteXPath(element, driver);
        String NewLocatorXpath = Xpath.concat(getByContentString(locator.getBy()));
        String locatorName = elementname.concat(locator.getName());
        newLocator.setBy(By.xpath(NewLocatorXpath));
        newLocator.setName(locatorName);
        return newLocator;
    }

    String getByContentString(By by) {
        return by.toString().split(":", 2)[1].trim();
    }

    public String getAbsoluteXPath(WebElement element, WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript(
                "function absoluteXPath(element) {" +
                        "var comp, comps = [];" +
                        "var parent = null;" +
                        "var xpath = '';" +
                        "var getPos = function(element) {" +
                        "var position = 1, curNode;" +
                        "if (element.nodeType == Node.ATTRIBUTE_NODE) {" +
                        "return null;" +
                        "}" +
                        "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling){" +
                        "if (curNode.nodeName == element.nodeName) {" +
                        "++position;" +
                        "}" +
                        "}" +
                        "return position;" +
                        "};" +

                        "if (element instanceof Document) {" +
                        "return '/';" +
                        "}" +

                        "for (; element && !(element instanceof Document); element = element.nodeType ==Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {" +
                        "comp = comps[comps.length] = {};" +
                        "switch (element.nodeType) {" +
                        "case Node.TEXT_NODE:" +
                        "comp.name = 'text()';" +
                        "break;" +
                        "case Node.ATTRIBUTE_NODE:" +
                        "comp.name = '@' + element.nodeName;" +
                        "break;" +
                        "case Node.PROCESSING_INSTRUCTION_NODE:" +
                        "comp.name = 'processing-instruction()';" +
                        "break;" +
                        "case Node.COMMENT_NODE:" +
                        "comp.name = 'comment()';" +
                        "break;" +
                        "case Node.ELEMENT_NODE:" +
                        "comp.name = element.nodeName;" +
                        "break;" +
                        "}" +
                        "comp.position = getPos(element);" +
                        "}" +

                        "for (var i = comps.length - 1; i >= 0; i--) {" +
                        "comp = comps[i];" +
                        "xpath += '/' + comp.name.toLowerCase();" +
                        "if (comp.position !== null) {" +
                        "xpath += '[' + comp.position + ']';" +
                        "}" +
                        "}" +

                        "return xpath;" +

                        "} return absoluteXPath(arguments[0]);", element);
    }

    public WebElement getElement() {
        return element;
    }

    public String GetElementXPath(WebElement element, WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript(
                "getXPath=function(node)" +
                        "{" +
                        "if (node.id !== '')" +
                        "{" +
                        "return '//' + node.tagName.toLowerCase() + '[@id=\"' + node.id + '\"]'" +
                        "}" +

                        "if (node === document.body)" +
                        "{" +
                        "return node.tagName.toLowerCase()" +
                        "}" +

                        "var nodeCount = 0;" +
                        "var childNodes = node.parentNode.childNodes;" +

                        "for (var i=0; i<childNodes.length; i++)" +
                        "{" +
                        "var currentNode = childNodes[i];" +

                        "if (currentNode === node)" +
                        "{" + "return getXPath(node.parentNode) " + '/' + "node.tagName.toLowerCase()" + '[' + "(nodeCount+1) " + "']'" + "}" +

                        "if (currentNode.nodeType === 1 && " +
                        "currentNode.tagName.toLowerCase() === node.tagName.toLowerCase())" +
                        "{" +
                        "nodeCount++" +
                        "}" +
                        "}" +
                        "};" +

                        "return getXPath(arguments[0]);", element);
    }


}
