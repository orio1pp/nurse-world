package nurse.world.utils.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String role;

    private String email;

    private ConstantUtils.Province province;

    private String birthDate;

    private ConstantUtils.EmploymentStatus employmentStatus;

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
