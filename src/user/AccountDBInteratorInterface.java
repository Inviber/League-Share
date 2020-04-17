package user;

public interface AccountDBInteratorInterface {
	public boolean existingAccount(String username);
	public void createUser(String username, String password);
}
