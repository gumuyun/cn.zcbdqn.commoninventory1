package cn.zcbdqn.commoninventory.entity;

/**
 *
 * {"result:1","id":111111}
 * {"result:0"}
 * Created by gumuyun on 2018/9/15.
 */

public class Result {
    /**
     * 服务器返回的结果
     * result:1表示成功
     * result:0 表示失败
     */
    private int result;
    /**
     * 成功时,返回id
     * id:id值
     */
    private String id;
    private String androidId;

    public Result() {
    }

    public Result(int result, String id) {
        this.result = result;
        this.id = id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public Result(int result, String id, String androidId) {
        this.result = result;
        this.id = id;
        this.androidId = androidId;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                ", id='" + id + '\'' +
                ", androidId='" + androidId + '\'' +
                '}';
    }
}
