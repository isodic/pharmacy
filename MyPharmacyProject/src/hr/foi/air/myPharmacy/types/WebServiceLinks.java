package hr.foi.air.myPharmacy.types;

/*
remarks -> AV
 */
public class WebServiceLinks {
    /*
    Funkcija koja sadrzi popis svih URL-ova prema Web servisu, 3D polje podijeljeno po grupama linkova.
    komentar koji ima u sebi '-' odvaja linkove unutar jedne grupe,
    komnetar koji ima u sebi '=' odvaja grupu jednu od druge
    */
    public static String getUrl(int idGroup,int idLinka,String username, String password, String email, String spol, String prezime, String ime){
        String [][] links={
                {"http://hamby.co.nf/Data.php" +
                        "?id_funkcije=1&username="+ username+"&password="+password+"",
//------------------------------------------------------------------------------ ↑Check User Login
                "http://hamby.co.nf/Data.php" +
                        "?id_funkcije=2&" +
                        "username="+username+"&password="+password+"&ime="+ime+"" +
                        "&prezime="+prezime+"&spol="+spol+"&email="+email+""}
//============================================================================== ↑Register User
        };

        return links[idGroup][idLinka];
    }
}
