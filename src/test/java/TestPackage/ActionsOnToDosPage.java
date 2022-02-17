package TestPackage;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import MainPackage.Helper;
import MainPackage.baseClass;

public class ActionsOnToDosPage extends baseClass  {
	Helper hp;
	@Test
	public void AddToDosListTest() {
		hp = new Helper(driver);
		hp.AddTodos("ThiruNew1");

	}

	@Test
	public void EditToDosTest() {
		hp = new Helper(driver);
		hp.EditTodos("a todo", "Edit text100000000");

	}

	@Test
	public void DeleteTodos() {
		hp = new Helper(driver);
    hp.DeleteTodo("ThiruNew1");
	}

	public boolean searchTodos(String todoCheck) {

		return true;
	}

	@AfterTest
	public void Cleartest() {
		// driver.quit();

	}

}
