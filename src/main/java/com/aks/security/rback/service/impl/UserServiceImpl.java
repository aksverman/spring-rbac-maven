package com.aks.security.rback.service.impl;

import static com.aks.security.rback.constants.Constants.FROM_ADDRESS;
import static com.aks.security.rback.constants.Constants.RESET_TOKEN_SUB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aks.security.rback.exception.CustomException;
import com.aks.security.rback.model.PasswordResetToken;
import com.aks.security.rback.model.Role;
import com.aks.security.rback.model.UserBO;
import com.aks.security.rback.repository.RoleRepository;
import com.aks.security.rback.repository.TokenRepository;
import com.aks.security.rback.repository.UserRepository;
import com.aks.security.rback.service.RoleService;
import com.aks.security.rback.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository	userRepository;
	
	@Autowired
	RoleRepository	roleRepository;
	
	@Autowired
	TokenRepository		tokenRepo;
	
	@Autowired
	RoleService		roleService;
	
	@Autowired
	JavaMailSender		mailSender;
	
	@Autowired
	EntityManagerFactory	emFactory;
	
	private EntityManager entityManager;
	
	private String 	contextPath;
	
	@PostConstruct
	public void createEntityManager() {
		entityManager = emFactory.createEntityManager();
	}
	
	public Optional<UserBO> findUserByUserName(String username) {
		logger.info("Start: " + getClass().getName() + " : findUserByUserName");
		return userRepository.findOneByUsername(username);
	}

	@Override
	public boolean saveUser(UserBO user) {
		UserBO	userbo = null;
		if( user.getRole() == null )
			addDefaultRole(user);
		addSelectedRoleToUser(user);
		userbo = userRepository.save(user);
		logger.info("User saved : " + user);
		return userbo != null;
	}


	@Override
	public UserBO findUserById(int userid) {
		UserBO userBO = userRepository.findOne(userid);
		return userBO;
	}

	@Override
	public boolean deleteUser(String username) throws Exception {
		try {	
			userRepository.delete(userRepository.findOneByUsername(username).get());	
		}
		catch ( Exception ex) {
			throw new CustomException("E0002", username);		
		}
		return true;
	}
	
	
	@Override
	public List<UserBO> usersList() {
		return userRepository.findAll();
	}

	/*
	 * To Update an existing user.
	 * Get existing user' userid with proxy and 
	 * set expected user's details to proxy and update user with session.
	 * 
	 * @see com.rudra.aks.service.UserService#updateUser(com.rudra.aks.model.UserBO)
	 */
	@Override
	public UserBO updateUser(UserBO user) throws CustomException {
		//return userRepository.save(user);
		//user.setUserid(userRepository.findOneByUsername(user.getUsername()).get().getUserid());
		logger.info("Start : " + getClass().getName() + " : updateUser()");
		logger.info("User to update : " + user);
		Session session = entityManager.unwrap(Session.class);
		Transaction tx = session.beginTransaction();
		
		UserBO userToUpdate = null;
		try {
			UserBO userfromdb = userRepository.findOneByUsername(user.getUsername()).get();
			int userid = userfromdb.getUserid();
			Role existingRole = userfromdb.getRole();
			
			userToUpdate = userRepository.getOne(userid);
			userToUpdate.setEmailid(user.getEmailid());
			userToUpdate.setPassword(user.getPassword());
			userToUpdate.setRole(existingRole);
			
			session.update(userToUpdate);
		} catch (Exception e) {
			logger.info("Exception occurred while updating user : " + user);
			throw new CustomException("E0003", new Object[] {  user.getUsername(), user.getEmailid() }, e);
		}
		tx.commit();
		session.close();
		
		return userToUpdate;
	}
	
	/**
	 * Get list of users' id which are updatable
	 */
	/*@Override
	public List<Integer> getUpdatableUsersList() {
		List<UserBO> allUsers = userRepository.findAll();
		List<Integer>	userIds;
		if ( !allUsers.isEmpty()) {
			userIds = new ArrayList<Integer>( allUsers.size() );
			for ( UserBO user : allUsers )
				userIds.add(user.getUserid());
			return userIds;
		}
		return null;
	}*/
	
	/**
	 * Users name to update
	 */
	@Override
	public List<String> getUpdatableUsersList() {
		List<UserBO> allUsers = userRepository.findAll();
		List<String>	usernames;
		if ( !allUsers.isEmpty()) {
			usernames = new ArrayList<String>( allUsers.size() );
			for ( UserBO user : allUsers )
				usernames.add(user.getUsername());
			return usernames;
		}
		return null;
	}
	
	
	@Override
	public boolean resetPassword(String username, String contextPath) {
		this.contextPath = contextPath;
		UserBO user = userRepository.findOneByUsername(username).get();
		if(user == null)
			return false;
		String token = UUID.randomUUID().toString();
		createPasswordResetToken(user, token);
		sendMailToUser(user, token);
		return true;
	}

	
	@Override
	public String validatePasswordChangeRequest(int userid, String token) {
		
		PasswordResetToken resetToken = tokenRepo.findByToken(token).get();
		if (resetToken == null || userid != resetToken.getUser().getUserid())
			return "Token Not Found or Invalid.";
		
		Calendar	cal = Calendar.getInstance();
		if ((resetToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0)
			return 	"Token is exipred.";
		
		return "token validated";
	}
		
	@Override
	public String updatePassword(int userid, String pass1) {
		UserBO usertoupdate = userRepository.getOne(userid);
		usertoupdate.setPassword(pass1);
		return userRepository.save(usertoupdate).getPassword();		
	}

	private void sendMailToUser(UserBO user, String token) {
		String mailContent = prepareMailContent(user, token);
		
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(FROM_ADDRESS);
			mailMessage.setTo(user.getEmailid());
			mailMessage.setSubject(RESET_TOKEN_SUB);
			mailMessage.setText(mailContent);
			
			mailSender.send(mailMessage);
		} catch (MailException e) {
			logger.error("Exception while sending mail... " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occurred " + e);
		}
			
		logger.info("Mail Send to ... " + user.getEmailid());
	}

	private String prepareMailContent(UserBO user, String token) {
		
		String resetUrl = new StringBuilder().append(contextPath)
				.append("/admin/changePasswordform?userid=")
				.append(user.getUserid())
				.append("&token=").append(token).toString();
		
		String msgBody = new StringBuilder().append("Hi, " + user.getUsername() )
			.append("\n You have requested to reset your password.")
			.append("\n\n Click below link to reset your password.")
			.append("\n\n" + resetUrl ).toString();
		
		return msgBody;
	}

	private void createPasswordResetToken(UserBO user, String token) {
		PasswordResetToken resetToken = new PasswordResetToken(token, user);
		tokenRepo.save(resetToken);
	}

	/**
	 * Set default role to new user, DEV is default role.
	 * @param user
	 */
	private void addDefaultRole(UserBO user) {
		logger.info("Adding default roles to user : " + user.getUsername());
		//Collection<Role> roles = Arrays.asList( roleService.findRoleById(1) );
		Role roleToAssign = roleService.findRoleById(1);
		user.setRole(roleToAssign);
	}

	/**
	 * Set requested role to user, bcoz, ROLEs cannot be added.
	 * It has to update the reference only.
	 * @param user
	 */
	private void addSelectedRoleToUser(UserBO user) {
		logger.info("Assigning role selected by user : " + user.getRole());
		Role  role = roleService.findRoleWithRoleName(user.getRole().getName()).get();
		user.setRole(role);
		logger.info("User's role added : " + user);
	}

	
	
}
