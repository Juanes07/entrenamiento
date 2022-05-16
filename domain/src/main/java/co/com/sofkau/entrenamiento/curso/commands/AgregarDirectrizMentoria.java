package co.com.sofkau.entrenamiento.curso.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofkau.entrenamiento.curso.values.CursoId;
import co.com.sofkau.entrenamiento.curso.values.Directriz;
import co.com.sofkau.entrenamiento.curso.values.MentoriaId;

public class AgregarDirectrizMentoria extends Command {
    private final MentoriaId mentoriaId;
    private final Directriz directriz;
    private final CursoId cursoId;

    public AgregarDirectrizMentoria(MentoriaId mentoriaId, Directriz directriz, CursoId cursoId) {
        this.mentoriaId = mentoriaId;
        this.directriz = directriz;
        this.cursoId = cursoId;
    }

    public MentoriaId getMentoriaId() {
        return mentoriaId;
    }

    public Directriz getDirectriz() {
        return directriz;
    }

    public CursoId getCursoId() {
        return cursoId;
    }
}
