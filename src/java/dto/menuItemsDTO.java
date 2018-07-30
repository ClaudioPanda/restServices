package dto;

public class menuItemsDTO {
    
    private Integer id;
    private String  menu_item;
    private String  menu_item_obs;
    //representa a la fk de la tabla menu
    private Integer id_menu;
    private String  nombre_menu;

    
    public String getNombre_menu() {
        return nombre_menu;
    }

    public void setNombre_menu(String nombre_menu) {
        this.nombre_menu = nombre_menu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenu_item() {
        return menu_item;
    }

    public void setMenu_item(String menu_item) {
        this.menu_item = menu_item;
    }

    public String getMenu_item_obs() {
        return menu_item_obs;
    }

    public void setMenu_item_obs(String menu_item_obs) {
        this.menu_item_obs = menu_item_obs;
    }

    public Integer getId_menu() {
        return id_menu;
    }

    public void setId_menu(Integer id_menu) {
        this.id_menu = id_menu;
    }
    
}
