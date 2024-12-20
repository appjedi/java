import java.util.ArrayList;
import java.util.List;

import net.appdojo.demo.models.User;

public class Homework {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<User>users = new ArrayList<>();
		User user1 = new User ();
		user1.setEmail("bob@test.com");
		user1.setUsername("bob");
		user1.setPassword("Test1234");
		user1.setFullName("Bob Test");
		user1.setRoleId(1);
		user1.setStatus(1);
		User user2 = new User();
		user2.setEmail("james@test.com");
		user2.setUsername("James");
		user2.setPassword("Test1234");
		user2.setFullName("James Test");
		user2.setRoleId(1);
		user2.setStatus(1);
		
		users.add(user1);
		users.add(user2);
		
		for (User user:users)
		{
			System.out.println(user);
		}
	}
}
