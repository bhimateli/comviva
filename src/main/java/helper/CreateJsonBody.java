package helper;

/*
@author Bhimashankar Teli
 */
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class CreateJsonBody {

    /*
     * For POST API - assumed three attributes are required. Userid, title and body.
     */
    public String createPOSTInputBody(String titleName, String title, String bodyName, String body, String userIdName,
                                      String userId) throws JSONException {

        JSONObject userLoginInputParam = new JSONObject();

        if (titleName != null && !"".equals(titleName))
            userLoginInputParam.put(titleName, title);

        if (bodyName != null && !"".equals(bodyName))
            userLoginInputParam.put(bodyName, body);

        if (userIdName != null && !"".equals(userIdName))
            userLoginInputParam.put(userIdName, userId);
        return userLoginInputParam.toString();
    }


    public String createCommentInputBody(String emailName, String email, String bodyName, String body, String PostIdName,
                                         String postId,String name,String nameValue) throws JSONException {

        JSONObject userLoginInputParam = new JSONObject();

        if (emailName != null && !"".equals(emailName))
            userLoginInputParam.put(emailName, email);

        if (bodyName != null && !"".equals(bodyName))
            userLoginInputParam.put(bodyName, body);

        if (PostIdName != null && !"".equals(PostIdName))
            userLoginInputParam.put(PostIdName, postId);
        if (name != null && !"".equals(name))
            userLoginInputParam.put(name, nameValue);

        return userLoginInputParam.toString();
    }
}
