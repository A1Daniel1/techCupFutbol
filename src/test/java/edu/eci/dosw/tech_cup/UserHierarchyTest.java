package edu.eci.dosw.tech_cup;

import edu.eci.dosw.tech_cup.model.*;
import edu.eci.dosw.tech_cup.enums.*;
import edu.eci.dosw.tech_cup.exception.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for all User concrete subclasses and Player concrete subclasses.
 * Grouped with @Nested for clarity and JaCoCo branch coverage.
 */
@DisplayName("User Hierarchy Tests")
class UserHierarchyTest {

    // ─── Referee ─────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Referee Tests")
    class RefereeTests {

        @Test
        @DisplayName("Should create referee with valid arguments")
        void shouldCreateRefereeWithValidArguments() {
            Referee referee = new Referee(1, "John", "john@eci.edu.co", 35, "Central");
            assertEquals(1, referee.getId());
            assertEquals("John", referee.getName());
            assertEquals("john@eci.edu.co", referee.getEmail());
            assertEquals(35, referee.getAge());
            assertEquals(TypeUser.REFEREE, referee.getRole());
            assertEquals("Central", referee.getRefereeType());
        }

        @Test
        @DisplayName("Should create referee with default constructor")
        void shouldCreateRefereeWithDefaultConstructor() {
            Referee referee = new Referee();
            assertNull(referee.getRefereeType());
        }

        @Test
        @DisplayName("Should update referee type via setter")
        void shouldUpdateRefereeTypeViaSetter() {
            Referee referee = new Referee(1, "John", "john@eci.edu.co", 35, "Central");
            referee.setRefereeType("Assistant");
            assertEquals("Assistant", referee.getRefereeType());
        }

        @Test
        @DisplayName("Should return correct string representation")
        void shouldReturnCorrectStringRepresentation() {
            Referee referee = new Referee(1, "John", "john@eci.edu.co", 35, "Central");
            assertTrue(referee.toString().contains("Central"));
        }
    }

    // ─── Administrator ───────────────────────────────────────────────────────

    @Nested
    @DisplayName("Administrator Tests")
    class AdministratorTests {

        @Test
        @DisplayName("Should create administrator with correct role")
        void shouldCreateAdministratorWithCorrectRole() {
            Administrator admin = new Administrator(2, "Alice", "alice@eci.edu.co", 40);
            assertEquals(TypeUser.ADMINISTRATOR, admin.getRole());
            assertEquals("Alice", admin.getName());
        }

        @Test
        @DisplayName("Should create administrator with default constructor")
        void shouldCreateAdministratorWithDefaultConstructor() {
            Administrator admin = new Administrator();
            assertNotNull(admin);
        }
    }

    // ─── Visitor ─────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Visitor Tests")
    class VisitorTests {

        @Test
        @DisplayName("Should create visitor with GEST role")
        void shouldCreateVisitorWithGestRole() {
            Visitor visitor = new Visitor(3, "Bob", "bob@eci.edu.co", 25);
            assertEquals(TypeUser.GEST, visitor.getRole());
        }

        @Test
        @DisplayName("Should create visitor with default constructor")
        void shouldCreateVisitorWithDefaultConstructor() {
            Visitor visitor = new Visitor();
            assertNotNull(visitor);
        }
    }

    // ─── Gest ────────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Gest Tests")
    class GestTests {

        @Test
        @DisplayName("Should create gest with GEST role")
        void shouldCreateGestWithGestRole() {
            Gest gest = new Gest(4, "Carlos", "carlos@eci.edu.co", 22);
            assertEquals(TypeUser.GEST, gest.getRole());
        }

        @Test
        @DisplayName("Should create gest with default constructor")
        void shouldCreateGestWithDefaultConstructor() {
            Gest gest = new Gest();
            assertNotNull(gest);
        }
    }

    // ─── Professor ───────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Professor Tests")
    class ProfessorTests {

        @Test
        @DisplayName("Should create professor with GEST role")
        void shouldCreateProfessorWithGestRole() {
            Professor professor = new Professor(5, "Dr. Smith", "smith@eci.edu.co", 50);
            assertEquals(TypeUser.GEST, professor.getRole());
            assertEquals("Dr. Smith", professor.getName());
        }

        @Test
        @DisplayName("Should create professor with default constructor")
        void shouldCreateProfessorWithDefaultConstructor() {
            Professor professor = new Professor();
            assertNotNull(professor);
        }
    }

    // ─── Graduate ────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Graduate Tests")
    class GraduateTests {

        @Test
        @DisplayName("Should create graduate with academic program")
        void shouldCreateGraduateWithAcademicProgram() {
            Graduate grad = new Graduate(6, "Maria", "maria@eci.edu.co", 28, "Systems Engineering");
            assertEquals("Systems Engineering", grad.getAcademicProgram());
            assertEquals(TypeUser.PLAYER, grad.getRole());
        }

        @Test
        @DisplayName("Should update academic program via setter")
        void shouldUpdateAcademicProgramViaSetter() {
            Graduate grad = new Graduate(6, "Maria", "maria@eci.edu.co", 28, "Systems Engineering");
            grad.setAcademicProgram("Software Engineering");
            assertEquals("Software Engineering", grad.getAcademicProgram());
        }

        @Test
        @DisplayName("Should create graduate with default constructor")
        void shouldCreateGraduateWithDefaultConstructor() {
            Graduate grad = new Graduate();
            assertNotNull(grad);
        }
    }

    // ─── Student ─────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Student Tests")
    class StudentTests {

        @Test
        @DisplayName("Should create student with academic program")
        void shouldCreateStudentWithAcademicProgram() {
            Student student = new Student(7, "Luis", "luis@eci.edu.co", 21, "Electronic Engineering");
            assertEquals("Electronic Engineering", student.getAcademicProgram());
            assertEquals(TypeUser.PLAYER, student.getRole());
        }

        @Test
        @DisplayName("Should update academic program via setter")
        void shouldUpdateAcademicProgramViaSetter() {
            Student student = new Student(7, "Luis", "luis@eci.edu.co", 21, "Electronic Engineering");
            student.setAcademicProgram("Systems Engineering");
            assertEquals("Systems Engineering", student.getAcademicProgram());
        }

        @Test
        @DisplayName("Should create student with default constructor")
        void shouldCreateStudentWithDefaultConstructor() {
            Student student = new Student();
            assertNotNull(student);
        }
    }

    // ─── User setters (via concrete class) ───────────────────────────────────

    @Nested
    @DisplayName("User Base Setters Tests")
    class UserBaseSettersTests {

        @Test
        @DisplayName("Should update user fields via setters")
        void shouldUpdateUserFieldsViaSetters() {
            Referee user = new Referee(1, "Old", "old@eci.edu.co", 30, "Central");
            user.setId(99);
            user.setName("New");
            user.setEmail("new@eci.edu.co");
            user.setAge(25);
            user.setRole(TypeUser.ADMINISTRATOR);

            assertEquals(99, user.getId());
            assertEquals("New", user.getName());
            assertEquals("new@eci.edu.co", user.getEmail());
            assertEquals(25, user.getAge());
            assertEquals(TypeUser.ADMINISTRATOR, user.getRole());
        }

        @Test
        @DisplayName("Should return correct user toString")
        void shouldReturnCorrectUserToString() {
            Referee user = new Referee(1, "John", "john@eci.edu.co", 35, "Central");
            String result = user.toString();
            assertTrue(result.contains("John"));
            assertTrue(result.contains("john@eci.edu.co"));
        }
    }

    // ─── StudentPlayer ───────────────────────────────────────────────────────

    @Nested
    @DisplayName("StudentPlayer Tests")
    class StudentPlayerTests {

        @Test
        @DisplayName("Should create student player with all fields")
        void shouldCreateStudentPlayerWithAllFields() {
            StudentPlayer sp = new StudentPlayer(1, "Ana", "ana@eci.edu.co", 20,
                    10, "Ani", "Forward", true, TypePlayer.STRIKER, "Systems");
            assertEquals("Systems", sp.getAcademicProgram());
            assertEquals(10, sp.getNumber());
            assertEquals("Ani", sp.getNickName());
            assertTrue(sp.isAvailable());
            assertEquals(TypePlayer.STRIKER, sp.getTypePlayer());
        }

        @Test
        @DisplayName("Should update academic program via setter")
        void shouldUpdateAcademicProgramViaSetter() {
            StudentPlayer sp = new StudentPlayer(1, "Ana", "ana@eci.edu.co", 20,
                    10, "Ani", "Forward", true, TypePlayer.STRIKER, "Systems");
            sp.setAcademicProgram("Electronic");
            assertEquals("Electronic", sp.getAcademicProgram());
        }

        @Test
        @DisplayName("Should return correct string representation")
        void shouldReturnCorrectStudentPlayerToString() {
            StudentPlayer sp = new StudentPlayer(1, "Ana", "ana@eci.edu.co", 20,
                    10, "Ani", "Forward", true, TypePlayer.STRIKER, "Systems");
            assertTrue(sp.toString().contains("Systems"));
        }

        @Test
        @DisplayName("Should create student player with default constructor")
        void shouldCreateStudentPlayerWithDefaultConstructor() {
            StudentPlayer sp = new StudentPlayer();
            assertNotNull(sp);
        }
    }

    // ─── GraduatePlayer ──────────────────────────────────────────────────────

    @Nested
    @DisplayName("GraduatePlayer Tests")
    class GraduatePlayerTests {

        @Test
        @DisplayName("Should create graduate player with all fields")
        void shouldCreateGraduatePlayerWithAllFields() {
            GraduatePlayer gp = new GraduatePlayer(2, "Pedro", "pedro@eci.edu.co", 27,
                    9, "Ped", "Striker", true, TypePlayer.FORWARD, "Civil Engineering");
            assertEquals("Civil Engineering", gp.getAcademicProgram());
            assertEquals(TypePlayer.FORWARD, gp.getTypePlayer());
        }

        @Test
        @DisplayName("Should update academic program via setter")
        void shouldUpdateAcademicProgramViaSetter() {
            GraduatePlayer gp = new GraduatePlayer(2, "Pedro", "pedro@eci.edu.co", 27,
                    9, "Ped", "Striker", true, TypePlayer.FORWARD, "Civil Engineering");
            gp.setAcademicProgram("Industrial Engineering");
            assertEquals("Industrial Engineering", gp.getAcademicProgram());
        }

        @Test
        @DisplayName("Should return correct string representation")
        void shouldReturnCorrectGraduatePlayerToString() {
            GraduatePlayer gp = new GraduatePlayer(2, "Pedro", "pedro@eci.edu.co", 27,
                    9, "Ped", "Striker", true, TypePlayer.FORWARD, "Civil Engineering");
            assertTrue(gp.toString().contains("Civil Engineering"));
        }

        @Test
        @DisplayName("Should create graduate player with default constructor")
        void shouldCreateGraduatePlayerWithDefaultConstructor() {
            GraduatePlayer gp = new GraduatePlayer();
            assertNotNull(gp);
        }
    }

    // ─── Player setters ──────────────────────────────────────────────────────

    @Nested
    @DisplayName("Player Base Setters Tests")
    class PlayerBaseSettersTests {

        @Test
        @DisplayName("Should update player fields via setters")
        void shouldUpdatePlayerFieldsViaSetters() {
            StudentPlayer sp = new StudentPlayer(1, "Ana", "ana@eci.edu.co", 20,
                    10, "Ani", "Forward", true, TypePlayer.STRIKER, "Systems");
            sp.setNumber(7);
            sp.setNickName("NewNick");
            sp.setPosition("Goalkeeper");
            sp.setAvailable(false);
            sp.setTypePlayer(TypePlayer.GOALKEEPER);

            assertEquals(7, sp.getNumber());
            assertEquals("NewNick", sp.getNickName());
            assertEquals("Goalkeeper", sp.getPosition());
            assertFalse(sp.isAvailable());
            assertEquals(TypePlayer.GOALKEEPER, sp.getTypePlayer());
        }
    }
}
