
package pl.uwb.kloc.model;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * User
 * <p>
 * A representation of a user
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "kloc_user")
@JsonPropertyOrder({
    "user_id",
    "login",
    "password",
    "userrole",
    "userstatus",
    "wins",
    "loses",
    "chesstitle",
    "icon"
})
public class User {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("user_id")
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("login")
    @Column(name = "login")
    private String login;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("password")
    @Column(name = "password")
    private String password;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("userrole")
    @Enumerated(EnumType.STRING)
    @Column(name = "userrole")
    private User.Userrole userrole;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("userstatus")
    private User.Userstatus userstatus;
    @JsonProperty("wins")
    private Integer wins;
    @JsonProperty("loses")
    private Integer loses;
    @JsonProperty("chesstitle")
    private User.Chesstitle chesstitle;
    @JsonProperty("icon")
    private String icon;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("userrole")
    public User.Userrole getUserrole() {
        return userrole;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("userrole")
    public void setUserrole(User.Userrole userrole) {
        this.userrole = userrole;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("userstatus")
    public User.Userstatus getUserstatus() {
        return userstatus;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("userstatus")
    public void setUserstatus(User.Userstatus userstatus) {
        this.userstatus = userstatus;
    }

    @JsonProperty("wins")
    public Integer getWins() {
        return wins;
    }

    @JsonProperty("wins")
    public void setWins(Integer wins) {
        this.wins = wins;
    }

    @JsonProperty("loses")
    public Integer getLoses() {
        return loses;
    }

    @JsonProperty("loses")
    public void setLoses(Integer loses) {
        this.loses = loses;
    }

    @JsonProperty("chesstitle")
    public User.Chesstitle getChesstitle() {
        return chesstitle;
    }

    @JsonProperty("chesstitle")
    public void setChesstitle(User.Chesstitle chesstitle) {
        this.chesstitle = chesstitle;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    @JsonProperty("icon")
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userId");
        sb.append('=');
        sb.append(((this.userId == null)?"<null>":this.userId));
        sb.append(',');
        sb.append("login");
        sb.append('=');
        sb.append(((this.login == null)?"<null>":this.login));
        sb.append(',');
        sb.append("password");
        sb.append('=');
        sb.append(((this.password == null)?"<null>":this.password));
        sb.append(',');
        sb.append("userrole");
        sb.append('=');
        sb.append(((this.userrole == null)?"<null>":this.userrole));
        sb.append(',');
        sb.append("userstatus");
        sb.append('=');
        sb.append(((this.userstatus == null)?"<null>":this.userstatus));
        sb.append(',');
        sb.append("wins");
        sb.append('=');
        sb.append(((this.wins == null)?"<null>":this.wins));
        sb.append(',');
        sb.append("loses");
        sb.append('=');
        sb.append(((this.loses == null)?"<null>":this.loses));
        sb.append(',');
        sb.append("chesstitle");
        sb.append('=');
        sb.append(((this.chesstitle == null)?"<null>":this.chesstitle));
        sb.append(',');
        sb.append("icon");
        sb.append('=');
        sb.append(((this.icon == null)?"<null>":this.icon));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.wins == null)? 0 :this.wins.hashCode()));
        result = ((result* 31)+((this.chesstitle == null)? 0 :this.chesstitle.hashCode()));
        result = ((result* 31)+((this.password == null)? 0 :this.password.hashCode()));
        result = ((result* 31)+((this.userstatus == null)? 0 :this.userstatus.hashCode()));
        result = ((result* 31)+((this.loses == null)? 0 :this.loses.hashCode()));
        result = ((result* 31)+((this.icon == null)? 0 :this.icon.hashCode()));
        result = ((result* 31)+((this.userrole == null)? 0 :this.userrole.hashCode()));
        result = ((result* 31)+((this.login == null)? 0 :this.login.hashCode()));
        result = ((result* 31)+((this.userId == null)? 0 :this.userId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof User) == false) {
            return false;
        }
        User rhs = ((User) other);
        return ((((((((((this.wins == rhs.wins)||((this.wins!= null)&&this.wins.equals(rhs.wins)))&&((this.chesstitle == rhs.chesstitle)||((this.chesstitle!= null)&&this.chesstitle.equals(rhs.chesstitle))))&&((this.password == rhs.password)||((this.password!= null)&&this.password.equals(rhs.password))))&&((this.userstatus == rhs.userstatus)||((this.userstatus!= null)&&this.userstatus.equals(rhs.userstatus))))&&((this.loses == rhs.loses)||((this.loses!= null)&&this.loses.equals(rhs.loses))))&&((this.icon == rhs.icon)||((this.icon!= null)&&this.icon.equals(rhs.icon))))&&((this.userrole == rhs.userrole)||((this.userrole!= null)&&this.userrole.equals(rhs.userrole))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.userId == rhs.userId)||((this.userId!= null)&&this.userId.equals(rhs.userId))));
    }

    public enum Chesstitle {

        GM("GM"),
        IM("IM"),
        FM("FM"),
        CM("CM"),
        WGM("WGM"),
        WIM("WIM"),
        WFM("WFM"),
        WCM("WCM");
        private final String value;
        private final static Map<String, User.Chesstitle> CONSTANTS = new HashMap<String, User.Chesstitle>();

        static {
            for (User.Chesstitle c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Chesstitle(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static User.Chesstitle fromValue(String value) {
            User.Chesstitle constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public enum Userrole {

        ROLE_USER("ROLE_USER"),
        ROLE_ADMIN("ROLE_ADMIN");
        private final String value;
        private final static Map<String, User.Userrole> CONSTANTS = new HashMap<String, User.Userrole>();

        static {
            for (User.Userrole c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Userrole(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static User.Userrole fromValue(String value) {
            User.Userrole constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public enum Userstatus {

        ACTIVE("ACTIVE"),
        BANNED("BANNED");
        private final String value;
        private final static Map<String, User.Userstatus> CONSTANTS = new HashMap<String, User.Userstatus>();

        static {
            for (User.Userstatus c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Userstatus(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static User.Userstatus fromValue(String value) {
            User.Userstatus constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
