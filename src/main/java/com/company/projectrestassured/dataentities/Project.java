package com.company.projectrestassured.dataentities;

public class Project {  //se crea ésta clase para la serialización
    private Integer id;
    private String name;
    private String identifier;
    private String description;
    private String homepage;
    private Integer status;
    private boolean is_public;
    private boolean inherit_members;
    private String created_on;
    private String updated_on;

    public Project(){
    }

    public Project(Integer id, String name, String identifier, String description, String homepage, Integer status, boolean is_public, boolean inherit_members, String created_on, String updated_on) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.description = description;
        this.homepage = homepage;
        this.status = status;
        this.is_public = is_public;
        this.inherit_members = inherit_members;
        this.created_on = created_on;
        this.updated_on = updated_on;
    }

    public Project(String name, String identifier, String description, boolean is_public) { //se crea un 3er constructor que reciba como parámetros, los campos que se utilizarán para crear el objeto java en la serialización
        this.name = name;
        this.identifier = identifier;
        this.description = description;
        this.is_public = is_public;
    } //en postman la petición es post y se crea un proyecto definiendo los valores para los campos mencionados en éste método constructor

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isIs_public() {
        return is_public;
    }

    public void setIs_public(boolean is_public) {
        this.is_public = is_public;
    }

    public boolean isInherit_members() {
        return inherit_members;
    }

    public void setInherit_members(boolean inherit_members) {
        this.inherit_members = inherit_members;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(String updated_on) {
        this.updated_on = updated_on;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", description='" + description + '\'' +
                ", homepage='" + homepage + '\'' +
                ", status=" + status +
                ", is_public=" + is_public +
                ", inherit_members=" + inherit_members +
                ", created_on='" + created_on + '\'' +
                ", updated_on='" + updated_on + '\'' +
                '}';
    }
}
