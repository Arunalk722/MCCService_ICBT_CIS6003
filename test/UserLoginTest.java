import dao.UserDAO;
import static junit.framework.Assert.assertEquals;
import model.User;
import org.junit.Test;


public class UserLoginTest {

    
    @Test
    public void testValidLogin() {
        String username = "admin";
        String password = "admin";        
        UserDAO userDAO = new UserDAO();         
        User result = userDAO.validateUser(username, password);   
        assertEquals(username, result.getUsername());
    }
    @Test
    public void testInvalidLoginIncorrectUsername() {
        String username = "worngUser";
        String password = "admin";        
        UserDAO userDAO = new UserDAO();         
        User result = userDAO.validateUser(username, password); 
        assertEquals(0, result.getUserId());
    }
    @Test
    public void testEmptyUserNamePassword() {
        String username = "";
        String password = "";        
        UserDAO userDAO = new UserDAO();
        User result = userDAO.validateUser(username, password);        
        assertEquals(0, result.getUserId());
    }
   @Test
    public void testException() {
        String username = "";
        String password = "";        
        UserDAO userDAO = new UserDAO();
        User result = userDAO.exceptionTest(username, password);        
        assertEquals(-1, result.getUserId());
    }
}
