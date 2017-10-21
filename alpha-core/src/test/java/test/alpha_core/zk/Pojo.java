package test.alpha_core.zk;

public class Pojo {

    private String name;
    private String key;
    private Long timestamp;

    public Long getTimestamp() {
        return timestamp;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "POJO=(name:"+ name +", key:"+ key +", ts:"+ timestamp +")";
    }
}
