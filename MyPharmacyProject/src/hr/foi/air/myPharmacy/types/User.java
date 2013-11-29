package hr.foi.air.myPharmacy.types;

/*
remarks -> AV
 */
public class User {

    public User(String ime,String prezime, int id,String username,String password, String email, String spol){
        this.ime=ime;
        this.prezime=prezime;
        this.id=id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.spol=spol;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public String getSpol() {
        return spol;
    }

    private String username;
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private String password;
    private String spol;
}
