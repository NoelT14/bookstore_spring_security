package NoelSpring.Bookstore.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;


@Entity
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@NotEmpty
    private String name;

    //@Email
   // @NotBlank
    private String email;

   // @Size(min=8,max=25)
    private String password;

   // @NotBlank
    private String address;

   // @NotNull
    private String role;

    public  String getAddress() {
        return address;
    }

    public void setAddress( String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  String getRole() {
        return role;
    }

    public void setRole( String role) {
        this.role = role;
    }
}
