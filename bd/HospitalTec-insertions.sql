USE HospitalTec; 
SET SQL_SAFE_UPDATES=0;

-- INSERTS SIMPLES TABLAS NORMALES
INSERT INTO CentroDeAtencion (codigoCentro,nombre,ubicacion,capacidadMaxima) VALUES
(1,"Centro de Atencion Edificio D3","TEC Cartago",20);

INSERT INTO AreaDeTrabajo (id,nombre) VALUES
(1,"Pediatria"),
(2,"Neurologia"),
(3,"Med. General"),
(4,"Salud Dental");

INSERT INTO Persona (cedula,nombre,primerApellido,segundoApellido) VALUES 
(116840425,"Manuel","Loaiza", "Sanchez"),
(305130565,"Josseline", "Mata","Mora"),
(118100381,"Nazareth","Briceñi","Madriz"),
(901060129,"Elodia","Sanchez","Badilla"),
(104780154,"Stefhanie","Loaiza","Sanchez");

INSERT INTO Paciente (cedula,contrasenna,fechaNacimiento,TipoDeSangre,nacionalidad,lugarDeResidencia,telefono) VALUES
(116840425,"1234",STR_TO_DATE('Aug 14,1997', '%M %d,%Y'),"O+","CR","Cartago",88708182),
(305130565,"1234",STR_TO_DATE('Oct 28,1998', '%M %d,%Y'),"O+","CR","Cartago",70473811),
(901060129,"1234",STR_TO_DATE('May 15,1975', '%M %d,%Y'),"O+","CR","Cartago",70473811),
(104780154,"1234",STR_TO_DATE('April 29,1999', '%M %d,%Y'),"O-","CR","Cartago",84704402);

INSERT INTO Funcionario (cedula,areaDeTrabajo,identificacion,contrasenna,tipoFuncionario,fechaDeIngreso) VALUES
(116840425,1,1111,"1234","Ayudante de sala",STR_TO_DATE('May 15,2020', '%M %d,%Y')),
(305130565,2,2222,"1234","Enfermero",STR_TO_DATE('May 15,2020', '%M %d,%Y')),
(118100381,3,3333,"1234","Secretario",STR_TO_DATE('May 15,2020', '%M %d,%Y')),
(901060129,1,4444,"1234","Enfermero",STR_TO_DATE('May 15,2020', '%M %d,%Y'));

INSERT INTO Enfermero (cedula,identificacion,indicadorPersonasCargo,experienciaCapacitaciones) VALUES
(305130565,2222,false,false);

INSERT INTO Doctor (cedula,identificacion,codigoMedico,especialidad) VALUES
(118100381,3333,1,"Cirujano");


-- INSERTS TABLAS INTERMEDIAS
INSERT INTO CentoDeAtencionAreaDeTrabajo (codigoCentro, idArea) VALUES
(1,1),
(1,2),
(1,3),
(1,4);

INSERT INTO CentoDeAtencionPersona (codigoCentro, cedula) VALUES
(1,116840425),
(1,305130565),
(1,118100381),
(1,901060129),
(1,104780154);
