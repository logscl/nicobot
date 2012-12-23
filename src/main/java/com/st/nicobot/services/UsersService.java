package com.st.nicobot.services;

import java.util.Set;

import org.jibble.pircbot.User;

public interface UsersService {
	
	/**
	 * Ajoute un utilisateur dans la liste des admins, seulement si il n'y est pas déjà, et qu'il est "op".
	 * @param user l'utilisateur {@link User}
	 */
	void addUser(User user);
	
	/**
	 * Ajoute un utilisateur dans la liste des admins
	 * @param username le pseudo de l'utilisateur
	 */
	void addUser(String username);
	
	/**
	 * Modifie un utilisateur, dans le cas ou il modifie son username
	 * @param oldUsername l'ancien pseudo
	 * @param newUsername le nouveau pseudo
	 */
	void updateUser(String oldUsername, String newUsername);
	
	/**
	 * Supprime un utilisateur de la liste des admins
	 * @param username le pseudo de l'utilisateur à retirer
	 */
	void removeUser(String username);
	
	/**
	 * Vérifie si un pseudo se retrouve dans la liste des administrateur
	 * @param username
	 * @return
	 */
	boolean isAdmin(String username);
	
	/**
	 * Retourne la liste des administrateurs
	 * @return
	 */
	Set<String> getUsers();

}
