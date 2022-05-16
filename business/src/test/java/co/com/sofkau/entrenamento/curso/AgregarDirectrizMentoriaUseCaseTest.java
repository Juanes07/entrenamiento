package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectrizMentoria;
import co.com.sofkau.entrenamiento.curso.events.CursoCreado;
import co.com.sofkau.entrenamiento.curso.events.DirectrizAgregadaAMentoria;
import co.com.sofkau.entrenamiento.curso.events.MentoriaCreada;
import co.com.sofkau.entrenamiento.curso.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgregarDirectrizMentoriaUseCaseTest {

    @InjectMocks
    private AgregarDirectrizMentoriaUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    public void crearDirectrizAMentoriaHappyPass() {
        //arrange
        CursoId cursoId = CursoId.of("dadada");
        MentoriaId mentoriaId = MentoriaId.of("dada");
        Directriz directriz = new Directriz("Nueva directriz");
        var command = new AgregarDirectrizMentoria(mentoriaId,directriz,cursoId);


        when(repository.getEventsBy("dadada")).thenReturn(history());
        useCase.addRepository(repository);
        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(command.getCursoId().value())
                .syncExecutor(useCase,new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();
        var event = (DirectrizAgregadaAMentoria) events.get(0);
        //assert
        Assertions.assertEquals("Nueva directriz", event.getDirectriz().value());
    }

    private List<DomainEvent> history() {
        Nombre nombre = new Nombre("DDD");
        Descripcion descripcion = new Descripcion("Directriz useCase");
        var event = new CursoCreado(
                nombre, descripcion
        );


        MentoriaId mentoriaId = MentoriaId.of("dada");
        Nombre nombreMentoria = new Nombre("Use case Directriz");
        Fecha fecha = new Fecha(LocalDateTime.now(), LocalDate.now());
        var event2 = new MentoriaCreada(
                mentoriaId,
                nombreMentoria,
                fecha
        );
        event.setAggregateRootId("xxxxxx");
        return List.of(event, event2);
    }
}