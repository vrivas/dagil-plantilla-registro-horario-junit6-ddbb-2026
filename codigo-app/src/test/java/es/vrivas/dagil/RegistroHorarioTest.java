package es.vrivas.dagil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

public class RegistroHorarioTest {
    @Test
    public void testRegistroHorario() {
        String fechaHora = "2020-03-04T05:06:00";
        String tipoEvento = RegistroHorario.TIPO_EVENTO_ENTRADA;
        RegistroHorario registroHorario = new RegistroHorario(1, 2,
                LocalDateTime.parse(fechaHora),
                tipoEvento);

        // Otra forma de establecer la hora
        LocalDateTime fechaHoraEsperada = LocalDateTime.of(2020, 3, 4, 5, 6, 0);
        assertEquals(fechaHoraEsperada, registroHorario.getFechaHora());
    }

    @Test
    public void testGetfechaHora() {
        String fechaHora = "2020-03-04T05:06:00";
        String tipoEvento = RegistroHorario.TIPO_EVENTO_ENTRADA;

        RegistroHorario registroHorario = new RegistroHorario(1, 2,
                LocalDateTime.parse(fechaHora),
                tipoEvento);

        LocalDateTime fechaHoraEsperada = LocalDateTime.parse(fechaHora);

        assertEquals(fechaHoraEsperada, registroHorario.getFechaHora());
    }

    @Test
    public void testGetTipoEvento() {
        String fechaHora = "2020-03-04T05:06:00";
        String tipoEvento = RegistroHorario.TIPO_EVENTO_ENTRADA;
        RegistroHorario registroHorario = new RegistroHorario(1, 2,
                LocalDateTime.parse(fechaHora),
                tipoEvento);
        assertEquals(RegistroHorario.TIPO_EVENTO_ENTRADA, registroHorario.getTipoEvento());

    }

    @Test
    public void testGetIdEmpresa() {
        int Idempresa = 2;
        String fechaHora = "2020-03-04T05:06:00";
        String tipoEvento = RegistroHorario.TIPO_EVENTO_ENTRADA;

        RegistroHorario registroHorario = new RegistroHorario(1, Idempresa,
                LocalDateTime.parse(fechaHora),
                tipoEvento);

        assertEquals(Idempresa, registroHorario.getIdEmpresa());
    }

    @Test
    public void testGetIdPersona() {
        int Idpersona = 1;
        String fechaHora = "2020-03-04T05:06:00";
        String tipoEvento = RegistroHorario.TIPO_EVENTO_ENTRADA;
        RegistroHorario registroHorario = new RegistroHorario(Idpersona, 2,
                LocalDateTime.parse(fechaHora),
                tipoEvento);

        assertEquals(Idpersona, registroHorario.getIdPersona());
    }

    @Test
    public void testSetIdEmpresa() {
        String fechaHora = "2020-03-04T05:06:00";
        String tipoEvento = RegistroHorario.TIPO_EVENTO_ENTRADA;

        // pruebo excepción
        try {
            new RegistroHorario(1, 0,
                    LocalDateTime.parse(fechaHora),
                    tipoEvento);
            fail();
        } catch (IllegalArgumentException e) {
            System.err.println("testSetIdEmpresa: Excepción capturada: " + e.getMessage());
        }
        // Continúo con la prueba del cambio de id empresa
        int Idempresa = 1;

        RegistroHorario registroHorario = new RegistroHorario(1, Idempresa,
                LocalDateTime.parse(fechaHora),
                tipoEvento);

        assertSame(registroHorario, registroHorario.setIdEmpresa(Idempresa + 10));
        assertEquals(Idempresa + 10, registroHorario.getIdEmpresa());
    }

    @Test
    public void testSetIdpersona() {
        String fechaHora = "2020-03-04T05:06:00";
        String tipoEvento = RegistroHorario.TIPO_EVENTO_ENTRADA;
        // pruebo excepción
        try {
            new RegistroHorario(0, 1,
                    LocalDateTime.parse(fechaHora),
                    tipoEvento);
            fail();
        } catch (IllegalArgumentException e) {
            System.err.println("testSetIdPersona: Excepción capturada: " + e.getMessage());
        }

        // Continúo con la prueba del cambio de id persona
        int Idpersona = 1;

        RegistroHorario registroHorario = new RegistroHorario(Idpersona, 1,
                LocalDateTime.parse(fechaHora),
                tipoEvento);

        assertSame(registroHorario, registroHorario.setIdPersona(Idpersona + 10));
        assertEquals(Idpersona + 10, registroHorario.getIdPersona());
    }

    @Test
    public void testSetfechaHora() {
        String fechaHora = "2020-03-04T05:06:00";
        String tipoEvento = RegistroHorario.TIPO_EVENTO_ENTRADA;
        RegistroHorario registroHorario = new RegistroHorario(1, 2,
                LocalDateTime.parse(fechaHora),
                tipoEvento);

        // Compruebo excepción cuando la fechaHora es nula
        try {
            registroHorario.setFechaHora(null);
            fail();
        } catch (IllegalArgumentException e) {
            System.err.println("testSetfechaHora: Excepción capturada: " + e.getMessage());
        }

        LocalDateTime nuevafechaHoraEsperada = LocalDateTime.parse("2021-03-04T05:06:00");

        assertSame(registroHorario, registroHorario.setFechaHora(nuevafechaHoraEsperada));
        assertEquals(nuevafechaHoraEsperada, registroHorario.getFechaHora());
    }

    @Test
    public void testSetTipoEvento() {
        String fechaHora = "2020-03-04T05:06:00";
        String tipoEvento = RegistroHorario.TIPO_EVENTO_ENTRADA;
        RegistroHorario registroHorario = new RegistroHorario(1, 2,
                LocalDateTime.parse(fechaHora),
                tipoEvento);

        // Compruebo excepción cuando la salida es nula
        try {
            registroHorario.setTipoEvento(null);
            fail();
        } catch (IllegalArgumentException e) {
            System.err.println("testSetTipoEvento: Excepción capturada: " + e.getMessage());
        }

        // Compruebo excepción cuando tipoEvento no es ninguno de los permitidos
        try {
            registroHorario.setTipoEvento(tipoEvento + "ALGO");
            fail();
        } catch (IllegalArgumentException e) {
            System.err.println("testSetSalida: Excepción capturada: " + e.getMessage());
        }
        String nuevoTipoEventoEsperado = RegistroHorario.TIPO_EVENTO_SALIDA;

        assertSame(registroHorario, registroHorario.setTipoEvento(nuevoTipoEventoEsperado));
        assertEquals(nuevoTipoEventoEsperado, registroHorario.getTipoEvento());
    }

    @Test
    public void testToString() {

        String fechaHora = "2020-03-04T05:06:00";
        String tipoEvento = RegistroHorario.TIPO_EVENTO_ENTRADA;
        RegistroHorario registroHorario = new RegistroHorario(1, 2,
                LocalDateTime.parse(fechaHora),
                tipoEvento);
        assertEquals(
                "{idPersona: 1, idEmpresa: 2, fechaHora: \"2020-03-04T05:06\", tipoEvento: \""
                        + RegistroHorario.TIPO_EVENTO_ENTRADA + "\"}",
                registroHorario.toString());
    }

    @Test
    public void testComparaFechaHora() {
        int year = 2020;
        int month = 5;
        int day = 5;
        int hour = 5;
        int minute = 5;

        RegistroHorario r1 = new RegistroHorario(1, 2,
                LocalDateTime.of(year, month, day, hour, minute),
                RegistroHorario.TIPO_EVENTO_ENTRADA);
        RegistroHorario r2 = new RegistroHorario(1, 2,
                LocalDateTime.of(year, month, day, hour, minute),
                RegistroHorario.TIPO_EVENTO_SALIDA);

        // Compruebo año
        r1.setFechaHora(LocalDateTime.of(year - 2, month, day, hour, minute));
        assertEquals(true, r1.comparaFechaHora(r2) < 0);

        r1.setFechaHora(LocalDateTime.of(year + 2, month, day, hour, minute));
        assertEquals(true, r1.comparaFechaHora(r2) > 0);

        // Compruebo mes
        r1.setFechaHora(LocalDateTime.of(year, month - 2, day, hour, minute));
        assertEquals(true, r1.comparaFechaHora(r2) < 0);

        r1.setFechaHora(LocalDateTime.of(year, month + 2, day, hour, minute));
        assertEquals(true, r1.comparaFechaHora(r2) > 0);

        // Compruebo día
        r1.setFechaHora(LocalDateTime.of(year, month, day - 2, hour, minute));
        assertEquals(true, r1.comparaFechaHora(r2) < 0);

        r1.setFechaHora(LocalDateTime.of(year, month, day + 2, hour, minute));
        assertEquals(true, r1.comparaFechaHora(r2) > 0);

        // Compruebo hora
        r1.setFechaHora(LocalDateTime.of(year, month, day, hour - 2, minute));
        assertEquals(true, r1.comparaFechaHora(r2) < 0);

        r1.setFechaHora(LocalDateTime.of(year, month, day, hour + 2, minute));
        assertEquals(true, r1.comparaFechaHora(r2) > 0);

        // Compruebo minuto
        r1.setFechaHora(LocalDateTime.of(year, month, day, hour, minute - 2));
        assertEquals(true, r1.comparaFechaHora(r2) < 0);

        r1.setFechaHora(LocalDateTime.of(year, month, day, hour, minute + 2));
        assertEquals(true, r1.comparaFechaHora(r2) > 0);
    }

}
