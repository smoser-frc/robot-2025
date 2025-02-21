package frc.robot.commands;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VisionTest {

  @Test
  public void test1() {
    String jblob =
        "{"
            + "\"center\": [3.1, 3.1], "
            + "\"corners\": [[2.51, 2.52], [3.5, 2.53], [2.5, 3.5], [3.5, 3.5]], "
            + "\"decision_margin\": 3.23, \"homography\": [1, 2, 3, 4, 5, 6, 7, 8, 9], "
            + "\"pose_R\": [1.2, 2.2, 3.2, 4.2, 5.2, 6.2, 7.2, 8.2, 9.2], "
            + "\"pose_err\": 19.23, "
            + "\"pose_t\": [1.3, 2.3, 3.3, 4.3, 5.3, 6.3, 7.3, 8.3, 9.3], "
            + "\"tag_family\": \"24t\", \"tag_id\": \"19d\""
            + "}";

    JSONObject obj = new JSONObject(jblob);
    // String pageName = obj.getJSONObject("pageInfo").getString("pageName");
    String pageName = obj.getString("tag_family");

    Assertions.assertEquals("24t", pageName);
    // this would fail
    // Assertions.assertEquals(obj.getString("tag_id"), "19a");

    Double pose_err = obj.getDouble("pose_err");
    Assertions.assertEquals(obj.getDouble("pose_err"), 19.23);

  }
}