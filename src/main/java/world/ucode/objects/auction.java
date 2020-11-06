package world.ucode.objects;

public class auction {
    public String title;
    public String Description;
    public int startPrice;
    public int maxPrice;
    public long startTime;
    public int duration;

    public void setDescription(String description) {
        Description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
