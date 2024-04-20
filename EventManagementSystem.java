import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventManagementSystem {
    private List<User> users;
    private List<Event> events;
    private static final String EVENT_FILE = "events.data";

    public EventManagementSystem() {
        this.users = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public Event findEventById(int eventId) {
        for (Event event : events) {
            if (event.getId() == eventId) {
                return event;
            }
        }
        return null;
    }

    public void registerUserForEvent(int userId, int eventId) {
        User user = findUserById(userId);
        Event event = findEventById(eventId);

        if (user != null && event != null) {
            if (!event.getParticipants().contains(user)) {
                event.getParticipants().add(user);
                System.out.println("Usuário " + user.getName() + " registrado para o evento " + event.getName());
            } else {
                System.out.println("Usuário já registrado para este evento.");
            }
        } else {
            System.out.println("Usuário ou evento não encontrado.");
        }
    }

    public void cancelRegistrationForEvent(int userId, int eventId) {
        User user = findUserById(userId);
        Event event = findEventById(eventId);

        if (user != null && event != null) {
            if (event.getParticipants().contains(user)) {
                event.getParticipants().remove(user);
                System.out.println("Participação do usuário " + user.getName() + " cancelada no evento " + event.getName());
            } else {
                System.out.println("Usuário não está registrado para este evento.");
            }
        } else {
            System.out.println("Usuário ou evento não encontrado.");
        }
    }

    public void displayUpcomingEvents() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Eventos Futuros:");

        for (Event event : events) {
            if (event.getDateTime().isAfter(now)) {
                System.out.println(event.getName() + " - " + event.getDateTime());
            }
        }
    }

    public void displayPastEvents() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Eventos Passados:");

        for (Event event : events) {
            if (event.getDateTime().isBefore(now)) {
                System.out.println(event.getName() + " - " + event.getDateTime());
            }
        }
    }

    public void saveEventsToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(EVENT_FILE))) {
            outputStream.writeObject(events);
            System.out.println("Eventos salvos no arquivo com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos no arquivo: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadEventsFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(EVENT_FILE))) {
            events = (List<Event>) inputStream.readObject();
            System.out.println("Eventos carregados do arquivo com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar eventos do arquivo: " + e.getMessage());
        }
    }

    public void displayEventsInProgress() {
        throw new UnsupportedOperationException("Unimplemented method 'displayEventsInProgress'");
    }

    public List<Event> getUserEvents(int userId) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserEvents'");
    }
}
 