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

    public Optional<Utilisateur> getUtilisateur(final Long id) {
        return utilisateurRepository.findById(id);
    }

    public boolean getUtilisateurByEmailAndMdp(String email, String mdp, String cleCryptage) {
        String mdpCrypte = crypter(mdp,cleCryptage);
        List<Utilisateur> utilisateurs = utilisateurRepository.findByEmailAndMdp(email, mdpCrypte);
        if (utilisateurs.size() == 1){
            return true;
        }
        return false;
    }

    public Iterable<Utilisateur> getUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Cryptage d'un texte
    public String crypter(String texteACrypter, String cleCryptage){
        int j=0, i, intTemp, intTemp2, intRes;
        StringBuilder strbTemp = new StringBuilder();
        String texteCrypte = "";
        char[] charACrypter, charCle;
        
        try {
            charACrypter = texteACrypter.toCharArray();
            charCle = cleCryptage.toCharArray();

            for ( i = 0; i < charACrypter.length; i++){
                intTemp = (int) charACrypter[i];
                intTemp2 = (int) charCle[j];

                intRes = intTemp ^ intTemp2;
                strbTemp.append(String.format("%03d", intRes));

                j++;
                if(j >= charCle.length){
                    j=0;
                }
            }
            
            texteCrypte = strbTemp.toString();
            
        } catch(Exception e){
            return null;
        }

        return texteCrypte;
    }

    // Décryptage d'un texte
    public String decrypter(String texteADecrypte, String cleCryptage){
        int j=0, i, intTemp, intTemp2, intRes;
        StringBuilder strbTemp = new StringBuilder();
        String texteDecrypte = "";
        char[] charCle;

        try {
            charCle = cleCryptage.toCharArray();

            for(i=0; i < texteADecrypte.length(); i+=3){
                intTemp = Integer.parseInt(texteADecrypte.substring(i, i+3));
                intTemp2 = (int) charCle[j];
                intRes = intTemp ^ intTemp2;
                strbTemp.append((char) intRes);

                j++;
                if(j >= charCle.length){
                    j=0;
                }
            }
            texteDecrypte = strbTemp.toString();
        }catch(Exception e){
            return null;
        }

        return texteDecrypte;
    }

}
