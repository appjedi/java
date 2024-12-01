package application;

public class App {
	models.Profile userProfile=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
		app.showMainMenu(args);
	}
	public void showMainMenu(String[] args)
	{
		MainMenu.SetApp(this);
		MainMenu.main(args);
	}
	public void setProfile(models.Profile profile)
	{
		this.userProfile=profile;
	}
	public void goForm(int id)
	{
		System.out.println ("Go:"+id);
	}
}
