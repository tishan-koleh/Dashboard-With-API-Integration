package com.example.openinapp.network.response;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class DashboardResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("message")
    private String message;

    @SerializedName("support_whatsapp_number")
    private String supportWhatsappNumber;

    @SerializedName("extra_income")
    private double extraIncome;

    @SerializedName("total_links")
    private int totalLinks;

    @SerializedName("total_clicks")
    private int totalClicks;

    @SerializedName("today_clicks")
    private int todayClicks;

    @SerializedName("top_source")
    private String topSource;

    @SerializedName("top_location")
    private String topLocation;

    @SerializedName("startTime")
    private String startTime;

    @SerializedName("links_created_today")
    private int linksCreatedToday;

    @SerializedName("applied_campaign")
    private int appliedCampaign;

    @SerializedName("data")
    private Data data;

    // Getters and Setters

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSupportWhatsappNumber() {
        return supportWhatsappNumber;
    }

    public void setSupportWhatsappNumber(String supportWhatsappNumber) {
        this.supportWhatsappNumber = supportWhatsappNumber;
    }

    public double getExtraIncome() {
        return extraIncome;
    }

    public void setExtraIncome(double extraIncome) {
        this.extraIncome = extraIncome;
    }

    public int getTotalLinks() {
        return totalLinks;
    }

    public void setTotalLinks(int totalLinks) {
        this.totalLinks = totalLinks;
    }

    public int getTotalClicks() {
        return totalClicks;
    }

    public void setTotalClicks(int totalClicks) {
        this.totalClicks = totalClicks;
    }

    public int getTodayClicks() {
        return todayClicks;
    }

    public void setTodayClicks(int todayClicks) {
        this.todayClicks = todayClicks;
    }

    public String getTopSource() {
        return topSource;
    }

    public void setTopSource(String topSource) {
        this.topSource = topSource;
    }

    public String getTopLocation() {
        return topLocation;
    }

    public void setTopLocation(String topLocation) {
        this.topLocation = topLocation;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getLinksCreatedToday() {
        return linksCreatedToday;
    }

    public void setLinksCreatedToday(int linksCreatedToday) {
        this.linksCreatedToday = linksCreatedToday;
    }

    public int getAppliedCampaign() {
        return appliedCampaign;
    }

    public void setAppliedCampaign(int appliedCampaign) {
        this.appliedCampaign = appliedCampaign;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class
    Data {

        @SerializedName("recent_links")
        private List<Link> recentLinks;

        @SerializedName("top_links")
        private List<Link> topLinks;

        @SerializedName("favourite_links")
        private List<Link> favouriteLinks;

        @SerializedName("overall_url_chart")
        private Map<String, Integer> overallUrlChart;

        // Getters and Setters

        public List<Link> getRecentLinks() {
            return recentLinks;
        }

        public void setRecentLinks(List<Link> recentLinks) {
            this.recentLinks = recentLinks;
        }

        public List<Link> getTopLinks() {
            return topLinks;
        }

        public void setTopLinks(List<Link> topLinks) {
            this.topLinks = topLinks;
        }

        public List<Link> getFavouriteLinks() {
            return favouriteLinks;
        }

        public void setFavouriteLinks(List<Link> favouriteLinks) {
            this.favouriteLinks = favouriteLinks;
        }

        public Map<String, Integer> getOverallUrlChart() {
            return overallUrlChart;
        }

        public void setOverallUrlChart(Map<String, Integer> overallUrlChart) {
            this.overallUrlChart = overallUrlChart;
        }
    }

    public static class Link {

        @SerializedName("url_id")
        private int urlId;

        @SerializedName("web_link")
        private String webLink;

        @SerializedName("smart_link")
        private String smartLink;

        @SerializedName("title")
        private String title;

        @SerializedName("total_clicks")
        private int totalClicks;

        @SerializedName("original_image")
        private String originalImage;

        @SerializedName("thumbnail")
        private String thumbnail;

        @SerializedName("times_ago")
        private String timesAgo;

        @SerializedName("created_at")
        private String createdAt;

        @SerializedName("domain_id")
        private String domainId;

        @SerializedName("url_prefix")
        private String urlPrefix;

        @SerializedName("url_suffix")
        private String urlSuffix;

        @SerializedName("app")
        private String app;

        @SerializedName("is_favourite")
        private boolean isFavourite;

        // Getters and Setters

        public int getUrlId() {
            return urlId;
        }

        public void setUrlId(int urlId) {
            this.urlId = urlId;
        }

        public String getWebLink() {
            return webLink;
        }

        public void setWebLink(String webLink) {
            this.webLink = webLink;
        }

        public String getSmartLink() {
            return smartLink;
        }

        public void setSmartLink(String smartLink) {
            this.smartLink = smartLink;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTotalClicks() {
            return totalClicks;
        }

        public void setTotalClicks(int totalClicks) {
            this.totalClicks = totalClicks;
        }

        public String getOriginalImage() {
            return originalImage;
        }

        public void setOriginalImage(String originalImage) {
            this.originalImage = originalImage;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTimesAgo() {
            return timesAgo;
        }

        public void setTimesAgo(String timesAgo) {
            this.timesAgo = timesAgo;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDomainId() {
            return domainId;
        }

        public void setDomainId(String domainId) {
            this.domainId = domainId;
        }

        public String getUrlPrefix() {
            return urlPrefix;
        }

        public void setUrlPrefix(String urlPrefix) {
            this.urlPrefix = urlPrefix;
        }

        public String getUrlSuffix() {
            return urlSuffix;
        }

        public void setUrlSuffix(String urlSuffix) {
            this.urlSuffix = urlSuffix;
        }

        public String getApp() {
            return app;
        }

        public void setApp(String app) {
            this.app = app;
        }

        public boolean isFavourite() {
            return isFavourite;
        }

        public void setFavourite(boolean favourite) {
            isFavourite = favourite;
        }
    }
}

