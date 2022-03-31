import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    private String copyright;
    private String date;
    private String explanation;
    private String hdurl;
    @JsonProperty("media_type")
    private String mediaType;
    @JsonProperty("service_version")
    private String serviceVersion;
    private String title;
    private String url;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getDate() {
        return date;
    }

    public String getCopyright() {
        return copyright;
    }
}
