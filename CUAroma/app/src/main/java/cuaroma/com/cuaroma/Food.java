package cuaroma.com.cuaroma;

/**
 * Created by Suraj on 3/27/2018.
 */

class Food {
    private String name;
    private String price;
    private String image;
    private String desc;
    private String quantity;

    public Food(){

    }

    public Food(String name, String price, String image, String desc){
        this.name=name;
        this.price=price;
        this.image=image;
        this.desc=desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
