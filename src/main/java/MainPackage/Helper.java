package MainPackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.TakesScreenshot;

public class Helper {

	WebDriver webdriver;;
	ObjectRepository ob;

	public void EditTodos(String text, String edittext) {
		ob = new ObjectRepository(webdriver);
		JavascriptExecutor js = (JavascriptExecutor) webdriver;
		By elt = ob.dynamicElement(text);
		ob.WaitElementExists(elt);
		js.executeScript("window.scrollBy(0,250)", "");
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
		System.out.println(ob.FIndElement(elt).getAttribute("class"));
		// js.executeScript("window.scrollBy(0,250)", "");
		Actions act = new Actions(webdriver);
		act.moveToElement(ob.FIndElement(elt)).perform();
		ob.ClickElement(Byelt);

	}

	public File ScreenShotpath() throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) webdriver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File path = new File(".//ScreenShots//Image.png");
		FileUtils.copyFile(src, path);
		return path;
	}

	public List<String> getToDosList() {
		ob = new ObjectRepository(webdriver);
		return ob.findListOfWebElements(ob.eltSection);
	}

	public boolean validateGivenTodo(String TodoName) {
		ob = new ObjectRepository(webdriver);
		List<String> lst = new ArrayList<String>();
		lst = ob.findListOfWebElements(ob.eltSection);
		boolean flag = false;
		for (String todo : lst) {
			if (todo.equalsIgnoreCase(TodoName)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public int ToDosCount() {
		ob = new ObjectRepository(webdriver);
		List<String> lstAll = new ArrayList<String>();
		lstAll = ob.findListOfWebElements(ob.eltSection);

		return lstAll.size();
	}

	public boolean ToDosCountValidation() {

		ob = new ObjectRepository(webdriver);
		List<String> lstAll = new ArrayList<String>();
		List<String> lstActive = new ArrayList<String>();
		List<String> lstCompleted = new ArrayList<String>();
		lstAll = ob.findListOfWebElements(ob.eltSection);

		ob.ClickElement(ob.eltActive);
		lstActive = ob.findListOfWebElements(ob.eltActiveList);

		ob.ClickElement(ob.eltCompleted);
		lstCompleted = ob.findListOfWebElements(ob.eltCompletedList);

		for (String all : lstAll)
			System.out.print(all + "   ");
		System.out.println("Active List : ");
		for (String Active : lstActive)
			System.out.print(Active + "   ");
		System.out.println("Completed List : ");

		for (String completed : lstCompleted)
			System.out.print(completed + "   ");
		if (lstAll.size() - 1 == (lstActive.size() + lstCompleted.size()))
			return true;

		else
			return false;

	}

	public void AllToDosVisibilitySection() {
		ob = new ObjectRepository(webdriver);
		ob.ClickElement(ob.eltAll);

	}

	public boolean validateGivenTodoCheck(String TodoName, String sectionName) {
		ob = new ObjectRepository(webdriver);
		List<String> lst = new ArrayList<String>();

		if (sectionName.equalsIgnoreCase("Active")) {
			ob.ClickElement(ob.eltActive);
			lst = ob.findListOfWebElements(ob.eltActiveList);
		} else {
			ob.ClickElement(ob.eltCompleted);
			lst = ob.findListOfWebElements(ob.eltCompletedList);
		}
		boolean flag = false;
		for (String todo : lst) {
			System.out.println(todo);
			if (todo.equalsIgnoreCase(TodoName)) {

				flag = true;
				break;
			}
		}
		return flag;
	}

	public void ClearCompletedToDos() {
		ob = new ObjectRepository(webdriver);
		ob.ScrollDownElementClick(ob.eltClearCompletedbtn);

	}

	public void MakeActivetoCompleted(String ActiveToDo) {

		ob = new ObjectRepository(webdriver);
		JavascriptExecutor js = (JavascriptExecutor) webdriver;
		js.executeScript("window.scrollBy(0,300)", "");
		By elt = ob.dynamicElement1(ActiveToDo, "UnChecked");

		ob.ClickElement(elt);
	}//

	public void MakeCompletedtoActive(String CompletedToDo) {

		ob = new ObjectRepository(webdriver);
		JavascriptExecutor js = (JavascriptExecutor) webdriver;
		js.executeScript("window.scrollBy(0,300)", "");
		By elt = ob.dynamicElement1(CompletedToDo, "Checked");

		ob.ClickElement(elt);
	}

	public Helper(WebDriver driver) {
		this.webdriver = driver;
	}

}
