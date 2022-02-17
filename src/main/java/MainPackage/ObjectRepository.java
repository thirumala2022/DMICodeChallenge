package MainPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ObjectRepository {
	WebDriver _driver;

	public By eltNewTodo = By.id("new-todo");
	public By eltDelete = By.xpath("//label[text()='a todo']//following::button");
	public By eltClearCompletedbtn = By.xpath("//button[@id='clear-completed']'");
	
	
	
//!driver.findElement(By.id("idOfTheElement")).isSelected() )
	
	public By eltChecked=By.cssSelector("input:checked[type='checkbox']");	
	public By eltUnchecked=By.cssSelector("input:not(:checked)[type='checkbox']");
	/* Element Click */
	public void ClickElement(By elt) {

		WaitElementExists(elt).click();

	}

	/* Text entry in TextBox field */
	public void Sendtext(By elt, String text) {
		WaitElementExists(elt);
		FIndElement(elt).clear();
		FIndElement(elt).sendKeys(text);
		FIndElement(elt).sendKeys(Keys.ENTER);

	}

	public WebElement WaitElementExists(By elt) {

		WebDriverWait wait = new WebDriverWait(_driver, 5);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elt));

		return element;
	}

	public WebElement FIndElement(By elt) {

		WebElement element = _driver.findElement(elt);

		return element;
	}

	public By dynamicElement(String Text) {
		return By.xpath("//label[text()='" + Text + "']");
	}

	public By dynamicElement1(String Text, String eltType) {
		if (eltType == "destroy")
			return By.xpath("//label[text()='" + Text + "']//following::button");
		else if (eltType == "checkBox")
			return By.xpath("//label[text()='" + Text + "']/preceding-sibling::input[@type='checkbox']"); // label[text()='false']
		else if (eltType == "edit")
			return By.xpath("//input[@class='edit' and @value='" + Text + "']");
		else 
			return By.xpath("//label[text()='" + Text + "']");
		
		//input[@class='edit' and @value='a todo']

	}

	public void EditTodos(String text, String edittext) {
		By elt = dynamicElement(text);
		WaitElementExists(elt);
		Actions ac = new Actions(_driver);

		ac.doubleClick(FIndElement(elt)).sendKeys(edittext).perform();

	}

	public boolean findElementByScoll(String Text) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) _driver;
			By element = dynamicElement1(Text, "destroy");
			js.executeScript("arguments[0].scrollIntoView();", FIndElement(element));
			return true;
		}

		catch (Exception ex) {
			return false;
		}
	}

	public ObjectRepository(WebDriver driver) {
		this._driver = driver;
	}

}
