package videostatusmaker.videostatus.jsonpass;

public class Modul {

    private String id, name, username,email,street,suite,city,zipcode,lat,lag;

//    public Modul() {
//    }

    public Modul(String id, String name, String username,String email,String street,String suite,String city,
                 String zipcode,String lat,String lag) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.lat = lat;
        this.lag = lag;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getLat() {
        return lat;
    }

    public String getLag() {
        return lag;
    }
}
