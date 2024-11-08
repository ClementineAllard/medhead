package com.medhead.webapp.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.webapp.model.Specialisation;
import com.medhead.webapp.CustomProperties;
import com.medhead.webapp.model.Hopital;
import com.medhead.webapp.repository.SpecialisationProxy;

import lombok.Data;

@Data
@Service
public class SpecialisationService {

    @Autowired
	private SpecialisationProxy specialisationProxy;

	
	@Autowired
	private CustomProperties props;

	/**
	 * Récupération de l'hôpital le plus proche ayant la spécialité demandée
     * @param id specialité demandée
	 * @param adresse lieu de recherche
	 * @return hôpital le plus adapté à la recherche
	 */
	public Hopital getHopitalBySpecialiteAndDistance(final Long id, String adresse) {
		Iterable<Specialisation> specialisations = specialisationProxy.getHopitauxBySpecialite(id);
		Hopital hopitalProche = null;
		Integer distanceMin = null; 

		for (Specialisation specialisation : specialisations) {
			Integer distance = getDistance(adresse, specialisation);

			if(hopitalProche==null){
				hopitalProche = specialisation.getHopital();
				distanceMin = distance;
			}else{
				if(distance<distanceMin){
					distanceMin = distance;
					hopitalProche = specialisation.getHopital();
				}
			}	
		}

		return hopitalProche;
	}

	/**
	 * Récupération, via l'API Google Maps, de la distance entre l'adresse saisie et celle d'un hôpital
	 * @param origine adresse renseignée par l'utilisateur
	 * @param specialisation hôpital spécialisé avec lequel on doit calculer la distance
	 * @return nombre de secondes nécessaire pour le trajet
	 */
	public Integer getDistance(String origine, Specialisation specialisation) {
		// Construction de l'appel à l'API
		String baseApiUrl = props.getApiGoogleMapsUrl();
		String key = props.getKeyGoogleMaps();

        String destination = specialisation.getHopital().getAdresse();
		destination += ", " + specialisation.getHopital().getVille();
		destination += ", " + specialisation.getHopital().getPays();
        
        try {
			// Url de l'appel
            String urlString = baseApiUrl + "origins=" 
                               + java.net.URLEncoder.encode(origine, "UTF-8") 
                               + "&destinations=" 
                               + java.net.URLEncoder.encode(destination, "UTF-8") 
                               + "&key=" + key;
            
			// Appel
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

			// Réponse 
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
				// Récupération de la réponse pour la lire
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                // Conversion en JSON
                JSONObject json = new JSONObject(content.toString());
                JSONObject elements = json.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0);
                // Durée en secondes du trajet en voiture
				return elements.getJSONObject("duration").getInt("value");

            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
			return null;
        }
	}

}
