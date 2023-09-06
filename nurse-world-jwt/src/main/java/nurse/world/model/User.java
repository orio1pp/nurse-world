package nurse.world.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import nurse.world.utils.dto.ConstantUtils;
import nurse.world.utils.dto.UserDTO;
import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.BCryptPassword;
import org.wildfly.security.password.util.ModularCrypt;

import jakarta.transaction.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Entity
@Table(name = "users")
@UserDefinition
public class User extends PanacheEntity {
    @Username
    public String username;
    @Password
    public String password;
    @Roles
    public String role;

    @Column
    public String email;

    @Column
    public ConstantUtils.Province province;

    @Column
    public String birthDate;

    @Column
    public ConstantUtils.EmploymentStatus employmentStatus;

    @Transactional
    public static boolean add(UserDTO userDTO) {
        if (!checkData(userDTO)) {
            return false;
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(BcryptUtil.bcryptHash(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        user.setEmail(userDTO.getEmail());
        user.setProvince(userDTO.getProvince());
        user.setBirthDate(userDTO.getBirthDate());
        user.setEmploymentStatus(userDTO.getEmploymentStatus());
        user.persist();
        return true;
    }
    private static boolean checkData(UserDTO userDTO){
        return userDTO.getUsername() != null && userDTO.getPassword() != null && checkValidDate(userDTO.getBirthDate());
    }

    private static boolean checkValidDate(String date){
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public static boolean existsByUsername(String username){
        return findByUsername(username) != null;
    }
    @Transactional
    public static User findByUsername(String username){
         try {
             return find("username", username).firstResult();
         }catch (Exception e){
             return null;
         }
    }

    @Transactional
    public static boolean existsByUsernameAndPassword(String username, String password) {
        WildFlyElytronPasswordProvider provider = new WildFlyElytronPasswordProvider();
        User user = findByUsername(username);
        try{
            org.wildfly.security.password.Password userPasswordDecoded =  ModularCrypt.decode(user.password);
            PasswordFactory passwordFactory = PasswordFactory.getInstance(BCryptPassword.ALGORITHM_BCRYPT, provider);
            org.wildfly.security.password.Password userPasswordRestored =  passwordFactory.translate(userPasswordDecoded);
            return passwordFactory.verify(userPasswordRestored, password.toCharArray());
        }catch (Exception e){
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ConstantUtils.Province getProvince() {
        return province;
    }

    public void setProvince(ConstantUtils.Province province) {
        this.province = province;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public ConstantUtils.EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(ConstantUtils.EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }
}