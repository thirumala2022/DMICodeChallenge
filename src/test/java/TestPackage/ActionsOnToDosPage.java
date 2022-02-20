package TestPackage;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MainPackage.ExcelUtility;
import MainPackage.Helper;
import MainPackage.baseClass;

public class ActionsOnToDosPage {
	Helper hp;

	/**
	 * Launch the application in chrome browser
	 */
	@BeforeClass
	public void setup() {
		baseClass.initilize();
	}

	/**
	 * Add new List of ToDos
	 * 
	 * @param TodoName
	 */
	@Test(dataProvider = "InputData")
	public void AddToDosListTest(String TodoName) {
		hp = new Helper(baseClass.driver);
		try {
			Reporter.log("Add New Todo to existing list - ToDo Name :" + TodoName);
			hp.AddTodos(TodoName);

		} catch (Exception ex) {
			Reporter.log("Add failed");
			Reporter.log(ex.getStackTrace().toString());
			Assert.assertEquals(true, false);

		}

	}

	/**
	 * Edit the existing ToDo
	 */
	@Test
	public void EditToDosTest() {
		hp = new Helper(baseClass.driver);
		String edittodo = baseClass.prop.getProperty("EditToDoOld");
		String edittodoUpdated = baseClass.prop.getProperty("EditToDoUpdated");
		try {
			Reporter.log("Edit the Existing ToDo ");
			hp.EditTodos(edittodo, edittodoUpdated);
			Assert.assertEquals(hp.validateGivenTodo(edittodoUpdated), true);

		} catch (Exception ex) {
			Reporter.log("Edit got failed");
			Reporter.log(ex.getStackTrace().toString());
			Assert.assertEquals(true, false);
		}

	}

	/**
	 * Delete the existing ToDo *
	 */
	@Test
	public void DeleteTodos() {
		hp = new Helper(baseClass.driver);
		String deletetodo = baseClass.prop.getProperty("DeleteToDo");
		try {
			Reporter.log("Delete existing ToDo");
			int beforeDeleteToDosCount = hp.ToDosCount();
			Reporter.log("Before DelteToDos Count=" + beforeDeleteToDosCount);

			Reporter.log("Delete the TodoName= " + deletetodo);
			;
			hp.DeleteTodo(deletetodo);
			int afterDeleteToDosCount = hp.ToDosCount();

			Reporter.log("After DelteToDos Count=" + afterDeleteToDosCount);
			Assert.assertEquals(beforeDeleteToDosCount - 1, afterDeleteToDosCount);
		} catch (Exception ex) {
			Reporter.log("Edit got failed");
			Reporter.log(ex.getStackTrace().toString());
			Assert.assertEquals(true, false);
		}
	}

	/**
	 * Take Screen shot of failure Case
	 * 
	 * @param result
	 */

	@AfterMethod
	public void screenShot(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {

			try {

				Reporter.log("<a href='" + hp.ScreenShotpath().getAbsolutePath() + "'> <img src='"
						+ hp.ScreenShotpath().getAbsolutePath() + "' height='300' width='300'/>Screenshot Link</a>");
				System.out.println("Successfully captured a screenshot");
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}

	}

	@DataProvider
	public Object[][] InputData() throws Exception {

		Object[][] testObjArray = ExcelUtility.getTableArray(".\\TestData\\TestData.xlsx", "Sheet1");

		return (testObjArray);

	}

	/**
	 * Close all browser Instances
	 */
	@AfterTest
	public void Cleartest() {
		baseClass.driver.quit();

	}

}
