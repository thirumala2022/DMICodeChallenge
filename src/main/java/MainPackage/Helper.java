package MainPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Helper {

	WebDriver webdriver;;
	ObjectRepository ob;

	public void EditTodos(String text, String edittext) {
		ob = new ObjectRepository(webdriver);
		By elt = ob.dynamicElement(text);
		ob.WaitElementExists(elt);
		Actions ac = new Actions(ob._driver);
		ac.doubleClick(ob.FIndElement(elt)).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT)
				.sendKeys(Keys.BACK_SPACE).perform();
		ac.sendKeys(edittext).perform();
		ac.sendKeys(Keys.TAB).perform();
	}

	public void AddTodos(String text) {
		ob = new ObjectRepository(webdriver);
		ob.Sendtext(ob.eltNewTodo, text);
	}

	public void DeleteTodo(String text) {
		ob = new ObjectRepository(webdriver);
		JavascriptExecutor js = (JavascriptExecutor) webdriver;
		By Byelt = ob.dynamicElement1(text, "destory");
		By elt = ob.dynamicElement(text);
		js.executeScript("window.scrollBy(0,250)", "");
		Actions act = new Actions(webdriver);
		act.moveToElement(ob.FIndElement(elt)).perform();
		ob.ClickElement(Byelt);

	}

	public Helper(WebDriver driver) {
		this.webdriver = driver;
	}

}
