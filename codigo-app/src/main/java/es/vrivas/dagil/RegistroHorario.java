package es.vrivas.dagil;

import java.time.LocalDateTime;

/**
 * Clase para almacenar los datos de cada uno de los registros horarios de entrada y salida de una persona.
 */
public class RegistroHorario {
    /// idPersona.
    private int idPersona;

    /// idEmpresa.
    private int idEmpresa;

    /// fecha y hora del evento como tipo LocalDateTime.
    private LocalDateTime fechaHora;

    /// Tipo del evento como String.
    String tipoEvento;

    /// Constantes
    final public static String TIPO_EVENTO_ENTRADA = "ENTRADA";
    final public static String TIPO_EVENTO_SALIDA = "SALIDA";

    /**
    * Constructor parametrizado con fechas.
    */
    public RegistroHorario(int idPersona,
            int idEmpresa,
            LocalDateTime fechaHora,
            String tipoEvento) {
        try {
            this.setIdPersona(idPersona);
            this.setIdEmpresa(idEmpresa);
            this.setFechaHora(fechaHora);
            this.setTipoEvento(tipoEvento);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error en parámetos de llamada a constructor Registro Horario: "
                    + e.getMessage());
        }
    }

    /**
     * Devuelve el valor de idPersona.
     * @return Atributo idPersona
     */
    public int getIdPersona() {
        return idPersona;
    }

    /**
     * Establece el valor de idPersona.
     * @param nuevoIdPersona
     * @exception IllegalArgumentException Si el id de la persona no es mayor que 0
     * @return La propia instancia de RegistroHorario
     */
    public RegistroHorario setIdPersona(int nuevoIdPersona) {
        if (nuevoIdPersona <= 0) {
            throw new IllegalArgumentException("El id de la persona debe ser mayor que 0");
        }
        this.idPersona = nuevoIdPersona;
        return this;
    }

    /**
     * Devuelve el valor de idEmpresa.
     * @return Atributo idEmpresa
     */
    public int getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * Establece el valor de idEmpresa.
     * @param nuevoIdEmpresa
     * @exception IllegalArgumentException Si el id de la empresa no es mayor que 0
     * @return La propia instancia de RegistroHorario
     */
    public RegistroHorario setIdEmpresa(int nuevoIdEmpresa) {
        if (nuevoIdEmpresa <= 0) {
            throw new IllegalArgumentException("El id de la empresa debe ser mayor que 0");
        }
        this.idEmpresa = nuevoIdEmpresa;
        return this;
    }

    /**
     * Devuelve el valor de fechaHora.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Devuelve el valor de tipoEvento.
     */
    public String getTipoEvento() {
        return tipoEvento;
    }

    /**
     * Establece el valor de la fecha y hora de entrada.
     * @param fechaHora Valor de la fecha y hora de entrada
     * @exception IllegalArgumentException Si la fecha y hora de entrada es null
     * @return La propia instancia de RegistroHorario
     */
    public RegistroHorario setFechaHora(final LocalDateTime fechaHora) {
        if (fechaHora == null) {
            throw new IllegalArgumentException("La fecha y hora no puede ser null");
        }
        this.fechaHora = fechaHora;
        return this;
    }

    /**
     * Establece el tipo de evento.
     * @param tipoEvento Tipo del evento
     * @exception IllegalArgumentException Si la fecha y hora de entrada es null
     * @exception IllegalArgumentException Si la fecha es posterior a la fecha de salida
     * @return La propia instancia de RegistroHorario
     */
    public RegistroHorario setTipoEvento(final String tipoEvento) {
        if (tipoEvento == null) {
            throw new IllegalArgumentException("RegistroHorario:setTipoEvento: El tipo de evento no puede ser null");
        }
        if (!tipoEvento.equals(TIPO_EVENTO_ENTRADA) &&
                !tipoEvento.equals(TIPO_EVENTO_SALIDA)

        ) {
            throw new IllegalArgumentException("RegistroHorario:setTipoEvento: el tipo de evento debe ser "
                    + TIPO_EVENTO_ENTRADA + " o " + TIPO_EVENTO_SALIDA +
                    " Y ES " + tipoEvento);
        }
        this.tipoEvento = tipoEvento;
        return this;
    }

    /**
     * Devuelve los atributos del Contenido en una cadena.
     * @return Cadena con los atributos del contenido en formato JSON
     */
    public final String toString() {
        return "{idPersona: " + idPersona
                + ", idEmpresa: " + idEmpresa
                + ", fechaHora: \"" + fechaHora + "\""
                + ", tipoEvento: \"" + tipoEvento + "\""
                + "}";
    }

    /**
     * Compara dos registros horarios por la fecha como LocalDateTime.
     * @param r2 RegistroHorario con el que comparar
     * @return -1, si este registro es anterior; 0 si son iguales; y 1, si es posterior.
     */
    public int comparaFechaHora(final RegistroHorario r2) {
        return this.fechaHora.compareTo(r2.fechaHora);
    }
}
