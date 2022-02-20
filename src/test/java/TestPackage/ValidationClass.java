package TestPackage;

import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MainPackage.ExcelUtility;
import MainPackage.Helper;
import MainPackage.baseClass;

public class ValidationClass {

	Helper hp;

	@BeforeClass
	public void setup() {
		baseClass.initilize();
	}

	/**
	 * Search required ToDo from the Existing List *
	 */
	@Test(dataProvider = "SearchInput")
	public void searchTodos(String searchtext) {

		hp = new Helper(baseClass.driver);
		Reporter.log("Search " + searchtext + " from existing ToDos List");
		Assert.assertEquals(hp.validateGivenTodo(searchtext), true);
	}

	/**
	 * Clear the Completed ToDos
	 **/
	@Test
	public void ClearCompletedToDos() {

		hp = new Helper(baseClass.driver);
		Reporter.log("Clear the completed ToDos");
		hp.ClearCompletedToDos();

	}

	/**
	 * Make Active ToDo to Completed ToDo
	 **/
	@Test
	public void MakeActiveToDo2Completed() {

		hp = new Helper(baseClass.driver);
		hp.AllToDosVisibilitySection();
		String newToDo = baseClass.prop.getProperty("AddNewToDo");
		try {
			hp.AddTodos(newToDo);
			hp.MakeActivetoCompleted(newToDo);
			boolean flag = hp.validateGivenTodoCheck(newToDo, "Completed");
			System.out.println(" flag value : " + flag);
			Assert.assertEquals(flag, true);
		} catch (Exception ex) {

			Assert.assertEquals(false, true);
		}
	}

	/**
	 * Make Completed ToDo to Active ToDo *
	 **/
	@Test
	public void MakeCompletedToDo2Active() {

		hp = new Helper(baseClass.driver);
		hp.AllToDosVisibilitySection();

		String AddNew = baseClass.prop.getProperty("AddToDo");
		try {
			hp.AddTodos(AddNew);
			hp.MakeActivetoCompleted(AddNew);
			hp.MakeCompletedtoActive(AddNew);
			boolean flag = hp.validateGivenTodoCheck(AddNew, "Active");

			System.out.println(" flag value : " + flag);
			Assert.assertEquals(flag, true);
		} catch (Exception ex) {

			Assert.assertEquals(false, true);
		}
	}

	/*
	 * Total ToDos under All section are equal to Total ToDos Under Active Section
	 * and Total ToDos under Completed Section
	 */
	@Test
	public void ToDosCountValidationTest() {

		hp = new Helper(baseClass.driver);
		hp.AllToDosVisibilitySection();
		boolean flag = hp.ToDosCountValidation();

		Assert.assertEquals(flag, true);

	}

	/**
	 * Take Screen shot of failure Case
	 **/

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
	public Object[][] SearchInput() throws Exception {

		Object[][] testObjArray = ExcelUtility.getTableArray(".\\TestData\\TestData.xlsx", "Sheet2");

		return (testObjArray);

	}

	/**
	 * Close all browser Instances
	 **/
	@AfterClass
	public void Cleartest() {
		baseClass.driver.quit();

	}
}
