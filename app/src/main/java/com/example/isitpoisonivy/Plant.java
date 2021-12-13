package com.example.isitpoisonivy;

public class Plant {
    private int plant_id;
    private String Plant_name;
    private String Botanical_name;
    private String Species_Afflicted;
    private String Continents;
    private String Toxicity_Cause;
    private String Symptoms;
    private String Plant_Distribution;

    public Plant(int id, String name, String bot_name, String speciesAff, String conts, String toxicity,
                 String symp, String distribution){
        plant_id = id;
        Plant_name = name;
        Botanical_name = bot_name;
        Species_Afflicted = speciesAff;
        Continents = conts;
        Toxicity_Cause = toxicity;
        Symptoms = symp;
        Plant_Distribution = distribution;

    }


    public int getPlant_id() {
        return plant_id;
    }

    public void setPlant_id(int plant_id) {
        this.plant_id = plant_id;
    }

    public String getPlant_name() {
        return Plant_name;
    }

    public void setPlant_name(String plant_name) {
        Plant_name = plant_name;
    }

    public String getBotanical_name() {
        return Botanical_name;
    }

    public void setBotanical_name(String botanical_name) {
        Botanical_name = botanical_name;
    }

    public String getSpecies_Afflicted() {
        return Species_Afflicted;
    }

    public void setSpecies_Afflicted(String species_Afflicted) {
        Species_Afflicted = species_Afflicted;
    }

    public String getContinents() {
        return Continents;
    }

    public void setContinents(String continents) {
        Continents = continents;
    }

    public String getToxicity_Cause() {
        return Toxicity_Cause;
    }

    public void setToxicity_Cause(String toxicity_Cause) {
        Toxicity_Cause = toxicity_Cause;
    }

    public String getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(String symptoms) {
        Symptoms = symptoms;
    }

    public String getPlant_Distribution() {
        return Plant_Distribution;
    }

    public void setPlant_Distribution(String plant_Distribution) {
        Plant_Distribution = plant_Distribution;
    }

    @Override
    public String toString(){
        return Plant_name;
    }
}
