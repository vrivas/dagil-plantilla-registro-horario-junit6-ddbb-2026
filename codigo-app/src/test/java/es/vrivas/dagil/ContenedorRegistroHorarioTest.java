package es.vrivas.dagil;

import org.junit.jupiter.api.Test;

import es.vrivas.dagil.App.CONF;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class ContenedorRegistroHorarioTest {
    @Test
    public void testConstructor() {
        ContenedorRegistroHorario contenedor = new ContenedorRegistroHorario();
        assertEquals(0, contenedor.getNumObjetosContenidos());
    }

    @Test
    public void testAdd() {
        // Compruebo la excepción si se intenta añadir un objeto nulo
        try {
            ContenedorRegistroHorario contenedor = new ContenedorRegistroHorario();
            contenedor.add(null);
            fail("Debería haber lanzado una excepción al intentar añadir un objeto nulo");
        } catch (IllegalArgumentException e) {
            System.err.println("testAdd: Excepción capturada: " + e.getMessage());
        }

        ContenedorRegistroHorario contenedor = new ContenedorRegistroHorario();
        String fechaHora = "2020-03-04T05:06:00";
        String tipoEvento = RegistroHorario.TIPO_EVENTO_ENTRADA;
        RegistroHorario registro = new RegistroHorario(1, 101,
                LocalDateTime.parse(fechaHora), tipoEvento);
        contenedor.add(registro);
        assertEquals(1, contenedor.getNumObjetosContenidos());
        assertSame(registro, contenedor.getPorPosicion(0));

        // Compruebo la excepción si se intenta añadir un objeto que ya está en el contenedor
        try {
            contenedor.add(registro);
            fail("Debería haber lanzado una excepción al intentar añadir un objeto que ya está en el contenedor");
        } catch (IllegalArgumentException e) {
            System.err.println("testAdd: Excepción capturada: " + e.getMessage());
        }
    }

    @Test
    public void testRemove() {
        // Compruebo la excepción si se intenta eliminar un objeto nulo
        try {
            ContenedorRegistroHorario contenedor = new ContenedorRegistroHorario();
            contenedor.remove(null);
            fail("Debería haber lanzado una excepción al intentar eliminar un objeto nulo");
        } catch (IllegalArgumentException e) {
            System.err.println("testAdd: Excepción capturada: " + e.getMessage());
        }

        // Compruebo la excepción si se intenta eliminar un objeto que no existe

        ContenedorRegistroHorario contenedor = new ContenedorRegistroHorario();
        String fechaHora = "2020-03-04T05:06:00";
        String tipoEvento = RegistroHorario.TIPO_EVENTO_ENTRADA;

        RegistroHorario registro = new RegistroHorario(1, 101,
                LocalDateTime.parse(fechaHora), tipoEvento);

        // Compruebo la excepción si se intenta añadir un objeto que ya está en el contenedor
        try {
            contenedor.remove(registro);
            fail("Debería haber lanzado una excepción al intentar eliminar un objeto que no está en el contenedor");
        } catch (IllegalArgumentException e) {
            System.err.println("testAdd: Excepción capturada: " + e.getMessage());
        }
        // Compruebo que elimina bien un objeto que sí existe.
        contenedor.add(registro);
        assertEquals(1, contenedor.getNumObjetosContenidos());
        contenedor.remove(registro);
        assertEquals(0, contenedor.getNumObjetosContenidos());
    }

    @Test
    public void testGetPorPosicion() {
        ContenedorRegistroHorario contenedor = new ContenedorRegistroHorario();
        String fechaHora = "2020-03-04T05:06:00";
        String tipoEvento = RegistroHorario.TIPO_EVENTO_ENTRADA;
        RegistroHorario registro = new RegistroHorario(1, 101,
                LocalDateTime.parse(fechaHora), tipoEvento);
        contenedor.add(registro);
        assertSame(registro, contenedor.getPorPosicion(0));

        // Compruebo la excepción si se intenta acceder a una posición no válida
        try {
            contenedor.getPorPosicion(1);
            fail("Debería haber lanzado una excepción al intentar acceder a una posición no válida");
        } catch (IllegalArgumentException e) {
            System.err.println("testGetPorPosicion: Excepción capturada: " + e.getMessage());
        }
        try {
            contenedor.getPorPosicion(-1);
            fail("Debería haber lanzado una excepción al intentar acceder a una posición no válida");
        } catch (IllegalArgumentException e) {
            System.err.println("testGetPorPosicion: Excepción capturada: " + e.getMessage());
        }
    }

    @Test
    public void testGetPorIdPersona() {
        ContenedorRegistroHorario contenedor = new ContenedorRegistroHorario();
        RegistroHorario registro1 = new RegistroHorario(1, 101,
                LocalDateTime.parse("2021-01-01T08:00"),
                RegistroHorario.TIPO_EVENTO_ENTRADA);
        RegistroHorario registro2 = new RegistroHorario(2, 101,
                LocalDateTime.parse("2022-02-02T10:00"),
                RegistroHorario.TIPO_EVENTO_ENTRADA);
        RegistroHorario registro3 = new RegistroHorario(1, 101,
                LocalDateTime.parse("2021-01-03T12:20"),
                RegistroHorario.TIPO_EVENTO_ENTRADA);
        contenedor.add(registro1);
        contenedor.add(registro2);
        contenedor.add(registro3);

        // Comprobamos que se devuelven los registros correctos para la persona 1
        try { // uso try porque getIdPorPersona puede lanzar una excepción, pero no para
              // probar la excepción
            ContenedorRegistroHorario contenedor2 = contenedor.getPorIdPersona(1);
            assertEquals(2, contenedor2.getNumObjetosContenidos());
            assertSame(registro1, contenedor2.getPorPosicion(0));
            assertSame(registro3, contenedor2.getPorPosicion(1));

        } catch (IllegalArgumentException e) {
            System.err.println("testGetPorIdPersona: Excepción capturada: " + e.getMessage());
        }

        // Comprobamos que se devuelve el registro correcto para la persona 2
        try { // uso try porque getIdPorPersona puede lanzar una excepción, pero no para
              // probar la excepción
            ContenedorRegistroHorario contenedor3 = contenedor.getPorIdPersona(2);
            assertEquals(1, contenedor3.getNumObjetosContenidos());
            assertSame(registro2, contenedor3.getPorPosicion(0));
        } catch (IllegalArgumentException e) {
            System.err.println("testGetPorIdPersona: Excepción capturada: " + e.getMessage());
        }

    }

    @Test
    public void testGetObjetosOrdenados_fechaHora() {
        ContenedorRegistroHorario contenedor = new ContenedorRegistroHorario();
        RegistroHorario registro1 = new RegistroHorario(1, 101,
                LocalDateTime.parse("2021-01-01T08:00"),
                RegistroHorario.TIPO_EVENTO_ENTRADA);
        RegistroHorario registro2 = new RegistroHorario(2, 101,
                LocalDateTime.parse("2022-02-02T10:00"),
                RegistroHorario.TIPO_EVENTO_ENTRADA);
        RegistroHorario registro3 = new RegistroHorario(1, 101,
                LocalDateTime.parse("2021-01-03T12:20"),
                RegistroHorario.TIPO_EVENTO_ENTRADA);
        RegistroHorario registro4 = new RegistroHorario(1, 101,
                LocalDateTime.parse("2020-01-02T12:20"),
                RegistroHorario.TIPO_EVENTO_ENTRADA);
        contenedor.add(registro1);
        contenedor.add(registro2);
        contenedor.add(registro3);
        contenedor.add(registro4);

        ContenedorRegistroHorario contenedorOrdenado = contenedor.getOrdenadosFechaHora();
        assertEquals(4, contenedorOrdenado.getNumObjetosContenidos());
        assertSame(registro4, contenedorOrdenado.getPorPosicion(0));
        assertSame(registro1, contenedorOrdenado.getPorPosicion(1));
        assertSame(registro3, contenedorOrdenado.getPorPosicion(2));
        assertSame(registro2, contenedorOrdenado.getPorPosicion(3));
    }

    @Test
    public void testToString() {
        ContenedorRegistroHorario contenedor = new ContenedorRegistroHorario();
        RegistroHorario registro1 = new RegistroHorario(1, 101,
                LocalDateTime.parse("2021-01-01T08:00"),
                RegistroHorario.TIPO_EVENTO_ENTRADA);
        RegistroHorario registro2 = new RegistroHorario(2, 101,
                LocalDateTime.parse("2022-02-02T10:00"), RegistroHorario.TIPO_EVENTO_SALIDA);
        RegistroHorario registro3 = new RegistroHorario(1, 101,
                LocalDateTime.of(2021, 1, 3, 12, 20),
                RegistroHorario.TIPO_EVENTO_ENTRADA);
        RegistroHorario registro4 = new RegistroHorario(3, 102,
                LocalDateTime.of(2020, 5, 7, 11, 12),
                RegistroHorario.TIPO_EVENTO_SALIDA);
        contenedor.add(registro1);
        contenedor.add(registro2);
        contenedor.add(registro3);
        contenedor.add(registro4);

        String esperado = "{\n" +
                "objetosContenidos = [\n" +
                "{idPersona: 1, idEmpresa: 101, fechaHora: \"2021-01-01T08:00\", tipoEvento: \""
                + RegistroHorario.TIPO_EVENTO_ENTRADA + "\"},\n" +
                "{idPersona: 2, idEmpresa: 101, fechaHora: \"2022-02-02T10:00\", tipoEvento: \""
                + RegistroHorario.TIPO_EVENTO_SALIDA + "\"},\n"
                + "{idPersona: 1, idEmpresa: 101, fechaHora: \"2021-01-03T12:20\", tipoEvento: \""
                + RegistroHorario.TIPO_EVENTO_ENTRADA + "\"},\n" +
                "{idPersona: 3, idEmpresa: 102, fechaHora: \"2020-05-07T11:12\", tipoEvento: \""
                + RegistroHorario.TIPO_EVENTO_SALIDA + "\"},\n" +
                "]\n" +
                "}";
        assertEquals(esperado, contenedor.toString());
    }

    @Test
    public void testLeerDesdeBBDD() {
        try {
            ContenedorRegistroHorario contenedor = new ContenedorRegistroHorario();
            contenedor.leerDesdeBBDD(CONF.JDBC_DRIVER, CONF.URL, CONF.DBUSER, CONF.PASSWORD);
            assertTrue(contenedor.getNumObjetosContenidos() > 0);

            // Hago que salte la excepción)
            contenedor.leerDesdeBBDD(CONF.JDBC_DRIVER, CONF.URL, CONF.DBUSER, CONF.PASSWORD + "patata");

        } catch (SQLException e) {
            System.err.println(
                    "SQLException: testLeerDesdeBBDD falló al leer registros horarios desde la base de datos: "
                            + e.getMessage());
        }
    }
}
