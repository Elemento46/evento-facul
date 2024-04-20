import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criar um sistema de gerenciamento de eventos
        EventManagementSystem eventSystem = new EventManagementSystem();

        // Carregar eventos do arquivo, se houver
        eventSystem.loadEventsFromFile();

        // Menu principal
        int option;
        do {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar novo evento");
            System.out.println("2. Consultar eventos cadastrados");
            System.out.println("3. Participar de um evento");
            System.out.println("4. Visualizar eventos participados");
            System.out.println("5. Verificar eventos próximos");
            System.out.println("6. Verificar eventos ocorrendo agora");
            System.out.println("7. Verificar eventos passados");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (option) {
                case 1:
                    // Cadastrar novo evento
                    System.out.println("Cadastro de novo evento:");
                    System.out.print("Nome: ");
                    String eventName = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String eventAddress = scanner.nextLine();
                    System.out.print("Categoria: ");
                    String eventCategory = scanner.nextLine();
                    System.out.print("Data e hora (yyyy-MM-dd HH:mm): ");
                    String eventDateTimeString = scanner.nextLine();
                    LocalDateTime eventDateTime = LocalDateTime.parse(eventDateTimeString);
                    System.out.print("Descrição: ");
                    String eventDescription = scanner.nextLine();
                    Event newEvent = new Event(eventSystem.getEvents().size() + 1, eventName, eventAddress, eventCategory, eventDateTime, eventDescription);
                    eventSystem.addEvent(newEvent);
                    System.out.println("Evento cadastrado com sucesso!");
                    break;
                case 2:
                    // Consultar eventos cadastrados
                    List<Event> events = eventSystem.getEvents();
                    System.out.println("Eventos cadastrados:");
                    for (Event event : events) {
                        System.out.println(event.getId() + ": " + event.getName());
                    }
                    break;
                case 3:
                    // Participar de um evento
                    System.out.println("Digite o ID do evento que deseja participar: ");
                    int eventId = scanner.nextInt();
                    System.out.println("Digite o seu ID de usuário: ");
                    int userId = scanner.nextInt();
                    eventSystem.registerUserForEvent(userId, eventId);
                    System.out.println("Você está participando do evento!");
                    break;
                case 4:
                    // Visualizar eventos participados
                    System.out.println("Digite o seu ID de usuário: ");
                    userId = scanner.nextInt();
                    List<Event> userEvents = eventSystem.getUserEvents(userId);
                    System.out.println("Eventos em que você está participando:");
                    for (Event event : userEvents) {
                        System.out.println(event.getName());
                    }
                    break;
                case 5:
                    // Verificar eventos próximos
                    System.out.println("Eventos próximos:");
                    eventSystem.displayUpcomingEvents();
                    break;
                case 6:
                    // Verificar eventos ocorrendo agora
                    System.out.println("Eventos ocorrendo agora:");
                    eventSystem.displayEventsInProgress();
                    break;
                case 7:
                    // Verificar eventos passados
                    System.out.println("Eventos passados:");
                    eventSystem.displayPastEvents();
                    break;
                case 8:
                    // Sair
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (option != 8);

        // Salvar eventos em um arquivo antes de sair
        eventSystem.saveEventsToFile();

        // Fechar o scanner
        scanner.close();
    }
}
