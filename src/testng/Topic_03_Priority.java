package testng;

import org.testng.annotations.Test;

public class Topic_03_Priority {
  @Test(enabled = false) // để enabled = false thì sẽ không chạy TC này
  public void EndUser_01_Create_New_Employee() {
	  
	  
  }
  
  @Test(priority = 1, enabled = true)  // set priority ở @Test cũng được, tuy nhiên mất thời gian
  public void EndUser_02_View_Employee() { // setpriorit ở ngay tên TC, TC sẽ chạy theo thứ tự số từ nhỏ đến lớn rồi mới đến Alphabet
	  
	  
  }
  
  @Test(description = "Liên quan đến ABC")
  public void EndUser_03_Edit_Employee() {
	  
	  
  }
  
  @Test(enabled = false)
  public void EndUser_04_Move_Employee() {
	  
	  
  }
}
