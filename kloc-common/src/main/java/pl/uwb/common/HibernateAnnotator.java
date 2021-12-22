package pl.uwb.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import org.jsonschema2pojo.AbstractAnnotator;

import javax.persistence.*;

public class HibernateAnnotator extends AbstractAnnotator {

    @Override
    public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
        super.propertyField(field, clazz, propertyName, propertyNode);

        // Note: does not have to be the propertyName, could be the field or propertyNode that is verified.

        //user
        if(propertyName.equals("userid")) {
            clazz.annotate(Entity.class);
            clazz.annotate(Table.class).param("name", "kloc_user");
            field.annotate(Id.class);
            field.annotate(Column.class).param("name", "user_id");

            field.annotate(GeneratedValue.class).param("strategy", GenerationType.IDENTITY);
        }

//        if(propertyName.equals("login")) {
//            field.annotate(Column.class).param("name", "login");
//        }
//

    }
}