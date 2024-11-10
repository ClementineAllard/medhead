package com.medhead.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.api.model.Utilisateur;
import com.medhead.api.repository.UtilisateurRepository;

import lombok.Data;

@Data
@Service
public class UtilisateurService {
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    /**
     * Récupération d'un utilisateur à partir de son id
     * @param id - id de l'utilisateur
     * @return l'utilisateur demandé
     */
    public Optional<Utilisateur> getUtilisateur(final Long id) {
        return utilisateurRepository.findById(id);
    }


    /**
     * Récupération de tous les utilisateurs
     * @return liste des utilisateurs
     */
    public Iterable<Utilisateur> getUtilisateurs() {
        return utilisateurRepository.findAll();
    }


    /**
     * Suppression d'un utilisateur
     * @param id - id de l'utilisateur à supprimer
     */
	public void deleteUtilisateur(final Long id) {
		utilisateurRepository.deleteById(id);
	}
	

    /**
     * Enregistrement d'un nouvel utilisateur
     * @param utilisateur - utilisateur à enregistrer
     * @param cleCryptage - clé de cryptage pour le mot de passe
     * @return l'utilsateur qui a été créé
     */
	public Utilisateur saveUtilisateur(Utilisateur utilisateur, String cleCryptage) {
        // On crypte le mot de passe avant d'enregistrer l'utilisateur
        String mdpCrypte = crypter(utilisateur.getMdp(), cleCryptage);
        utilisateur.setMdp(mdpCrypte);

		Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
		return savedUtilisateur;
	}


    /**
     * Vérification que l'utilisateur peut se connecter
     * @param email - email du compte
     * @param mdp - mot de passe saisi
     * @param cleCryptage - clé de cryptage pour le mot de passe
     * @return true - si l'utilisateur a bien saisi son mot de passe
     */
    public boolean verifConnexion(String email, String mdp, String cleCryptage) {
        // On crypte le mot de passe pour le comparer à celui en base
        String mdpCrypte = crypter(mdp,cleCryptage);

        // On recherche s'il existe un utilisateur avec cet identifiant et ce mot de passe
        List<Utilisateur> utilisateurs = utilisateurRepository.findByEmailAndMdp(email, mdpCrypte);

        return (utilisateurs.size() == 1); // Si un utilisateur a été trouvé alors l'utilisateur peut se connecter au système
    }


    /**
     * Cryptage d'une chaine
     * @param texteACrypter - texte devant être crypté
     * @param cleCryptage - clé pour crypter le texte
     * @return texte crypté avec la clé
     */
    public String crypter(String texteACrypter, String cleCryptage){
        // On initialise les variables
        int j=0, i, intTemp, intTemp2, intRes;
        StringBuilder strbTemp = new StringBuilder();
        String texteCrypte = "";
        char[] charACrypter, charCle;
        
        try {
            // On convertit les chaines en tableau de caractères
            charACrypter = texteACrypter.toCharArray();
            charCle = cleCryptage.toCharArray();

            // On parcourt la chaine à crypter pour crypter chaque caractère
            for (i = 0; i < charACrypter.length; i++){
                intTemp = (int) charACrypter[i];
                intTemp2 = (int) charCle[j];

                intRes = intTemp ^ intTemp2; // cryptage du caractère
                strbTemp.append(String.format("%03d", intRes)); // ajout à la chaine finale

                j++;
                if(j >= charCle.length){ // si on a dépassé la taille de la clé, on recommence le parcours
                    j=0;
                }
            }
            
            texteCrypte = strbTemp.toString(); // on obtient le texte crypté
            
        } catch(Exception e){
            return null;
        }

        return texteCrypte;
    }


    /**
     * Décryptage d'une chaine
     * @param texteADecrypte - texte devant être décrypté
     * @param cleCryptage - clé qui a servi pour crypter le texte d'origine
     * @return texte d'origine décrypté
     */
    public String decrypter(String texteADecrypte, String cleCryptage){
        // On initialise les variables
        int j=0, i, intTemp, intTemp2, intRes;
        StringBuilder strbTemp = new StringBuilder();
        String texteDecrypte = "";
        char[] charCle;

        try {
            // On convertit la clé en tableau de caractères
            charCle = cleCryptage.toCharArray();

            // On parcourt la chaîne à décrypter pour décrypter chaque ensemble de 3 entiers (car elle a été cryptée de cette manière)
            for(i=0; i < texteADecrypte.length(); i+=3){
                intTemp = Integer.parseInt(texteADecrypte.substring(i, i+3));
                intTemp2 = (int) charCle[j];

                intRes = intTemp ^ intTemp2; // décryptage des 3 entiers en 1 caractère 
                strbTemp.append((char) intRes); // on ajoute le caractère décrypté à la chaîne finale

                j++;
                if(j >= charCle.length){ // si on a dépassé la taille de la clé, on recommence le parcours
                    j=0;
                }
            }

            texteDecrypte = strbTemp.toString(); // on obtient le texte décrypté

        }catch(Exception e){
            return null;
        }

        return texteDecrypte;
    }

}