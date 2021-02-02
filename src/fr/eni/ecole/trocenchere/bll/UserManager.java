package fr.eni.ecole.trocenchere.bll;

import fr.eni.ecole.trocenchere.BusinessException;
import fr.eni.ecole.trocenchere.bo.User;
import fr.eni.ecole.trocenchere.dal.DAO;
import fr.eni.ecole.trocenchere.dal.DAOFactory;
import fr.eni.ecole.trocenchere.gestion.erreurs.CodesResultatBLL;

public class UserManager {
	private DAO userDao;

	// TODO
	public UserManager() {
		this.userDao = DAOFactory.getUser();
	}

	// method in order to get user data from pseudo
	public User selectUser(String userName) {
		return this.userDao.selectUser(userName);
	}

	// method in order to create a new user in database
	public User addUser(String user, String name, String firstName, String email, String phone, String street,
			String postCode, String city, String passwordEncrypted) throws BusinessException {
		
		BusinessException be = new BusinessException();
		
		User currentUser = new User(user, name, firstName, email, phone, street, postCode, city, passwordEncrypted);
		this.validateUser(currentUser, be);
		
		if(!be.hasErreurs())
		{
			this.userDao.createUser(currentUser);
		}
		
		if(be.hasErreurs())
		{
			throw be;
		}
	
		
		
				return currentUser;

	}
	
	//User validation before creating in database
	private void validateUser(User currentUser, BusinessException be) {
		if (currentUser.getUser()==null || currentUser.getUser()=="" || currentUser.getUser().length()>30) {
			be.ajouterErreur(CodesResultatBLL.USER_ERROR);
		}
		if (currentUser.getName()==null || currentUser.getName()=="" || currentUser.getName().length()>30) {
			be.ajouterErreur(CodesResultatBLL.NAME_ERROR);
		}
		if (currentUser.getFirstName()==null || currentUser.getFirstName()=="" || currentUser.getFirstName().length()>30) {
			be.ajouterErreur(CodesResultatBLL.FIRSTNAME_ERROR);
		}
		if (currentUser.getEmail()==null || currentUser.getEmail()=="" || currentUser.getEmail().length()>20 || !currentUser.getEmail().contains("@")) {
			be.ajouterErreur(CodesResultatBLL.EMAIL_ERROR);
		}
		if (currentUser.getPhone()==null || currentUser.getPhone()=="" || currentUser.getPhone().length()>15 || currentUser.getPhone().contains("[a-zA-Z]")) {
			be.ajouterErreur(CodesResultatBLL.PHONE_ERROR);
		}
		if (currentUser.getStreet()==null || currentUser.getStreet()=="" || currentUser.getStreet().length()>30) {
			be.ajouterErreur(CodesResultatBLL.STREET_ERROR);
		}
		if (currentUser.getPostCode()==null || currentUser.getPostCode()=="" || currentUser.getPostCode().length()>10 || currentUser.getPostCode().contains("[a-zA-Z]")) {
			be.ajouterErreur(CodesResultatBLL.POSTCODE_ERROR);
		}
		if (currentUser.getCity()==null || currentUser.getCity()=="" || currentUser.getCity().length()>30) {
			be.ajouterErreur(CodesResultatBLL.CITY_ERROR);
		}
		if (currentUser.getPasswordEncrypted()==null || currentUser.getPasswordEncrypted()=="" || currentUser.getPasswordEncrypted().length()>30) {
			be.ajouterErreur(CodesResultatBLL.PASSWORD_ERROR);
		}
		
	}

}
