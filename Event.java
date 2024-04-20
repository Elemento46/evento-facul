import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private int id;
    private String name;
    private String address;
    private String category;
    private LocalDateTime dateTime;
    private String description;

    public Event(int id, String name, String address, String category, LocalDateTime dateTime, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.category = category;
        this.dateTime = dateTime;
        this.description = description;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Método para verificar se o evento está ocorrendo no momento
    public boolean isEventInProgress() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(dateTime.minusHours(1)) && now.isBefore(dateTime.plusHours(1));
    }

    public List<User> getParticipants() {
        throw new UnsupportedOperationException("Unimplemented method 'getParticipants'");
    }
}
