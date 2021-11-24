DROP DATABASE HospitalTec	;

CREATE DATABASE HospitalTec	;
USE HospitalTec; 

SELECT * FROM Vacuna;

CREATE TABLE CentroDeAtencion(
  codigoCentro int PRIMARY KEY,
  nombre varchar(100), 
  ubicacion varchar(150),
  capacidadMaxima int, 
  tipoDeCentro varchar(100)
);
CREATE TABLE AreaDeTrabajo(
  id int PRIMARY KEY, 
  nombre varchar (100)
);

CREATE TABLE Persona(
  cedula int PRIMARY KEY, 
  nombre varchar (150),
  primerApellido varchar (150),
  segundoApellido varchar (150)
);

CREATE TABLE Paciente(
  cedula int,
  contrasenna varchar(10), 
  fechaNacimiento Date, 
  tipoDeSangre varchar (2), 
  nacionalidad varchar (50),	
  lugarDeResidencia varchar (100), 
  telefono int,
  FOREIGN KEY (cedula) REFERENCES Persona(cedula) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Funcionario(
  cedula int, 
  areaDeTrabajo int,
  identificacion int PRIMARY KEY, 
  contrasenna varchar(10), 
  tipoFuncionario varchar (20), 
  fechaDeIngreso Date,
  FOREIGN KEY (cedula) REFERENCES Persona(cedula) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (areaDeTrabajo) REFERENCES AreaDeTrabajo(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Enfermero(
  cedula int,
  identificacion int, 
  indicadorPersonasCargo boolean, 
  experienciaCapacitaciones boolean,
  FOREIGN KEY (cedula) REFERENCES Persona(cedula) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (identificacion) REFERENCES Funcionario(identificacion) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE Doctor(
  cedula int,
  identificacion int, 
  codigoMedico int, 
  especialidad varchar(100),
  FOREIGN KEY (cedula) REFERENCES Persona(cedula) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (identificacion) REFERENCES Funcionario(identificacion) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Cita(
  identificador int PRIMARY KEY AUTO_INCREMENT, 
  cedula int,
  areaDeTrabajo int, 
  estado varchar (50), 
  observacion varchar (100), 
  fecha datetime,
  FOREIGN KEY (cedula) REFERENCES Persona(cedula) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (areaDeTrabajo) REFERENCES AreaDeTrabajo(id) ON DELETE CASCADE ON UPDATE CASCADE
); 

 CREATE TABLE FuncionarioCita(
   idFuncionario int, 
   idCita int,
   FOREIGN KEY (idFuncionario) REFERENCES Funcionario(identificacion) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (idCita) REFERENCES Cita(identificador)  ON DELETE CASCADE ON UPDATE CASCADE
 );
 
  CREATE TABLE BitacoraCitas(
   idFuncionario int, 
   idPaciente int, 
   idCita int,
   fecha datetime,
   descripcion varchar(100)
 );
 
 CREATE TABLE Vacuna(
   cedula int, 
   fechaAplicacion DATE,
   nombreVacuna varchar (100),
   farmaceutica varchar(100), 
   numeroLote int, 
   FOREIGN KEY (cedula) REFERENCES Persona(cedula) ON DELETE CASCADE ON UPDATE CASCADE
 );
 
 CREATE TABLE Diagnostico(
	nombre varchar(100) PRIMARY KEY
 );
 
CREATE TABLE Tratamiento(
	nombre varchar(100) PRIMARY KEY,
    tipo varchar (50)
 );
  
 CREATE TABLE DiagnosticoPaciente(
   cedula int,
   nombre varchar (100), 
   nivel varchar (50),
   observaciones varchar (100),
   FOREIGN KEY (cedula) REFERENCES Persona(cedula) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (nombre) REFERENCES Diagnostico(nombre) ON DELETE CASCADE ON UPDATE CASCADE
 );
SELECT DiagnosticoPaciente.cedula, DiagnosticoPaciente.nombre,DiagnosticoPaciente.nivel,DiagnosticoPaciente.observaciones FROM 
(((DiagnosticoPaciente JOIN DiagnosticoCita ON DiagnosticoPaciente.nombre=DiagnosticoCita.nombre) JOIN
Cita ON Cita.identificador=DiagnosticoCita.identificador) JOIN
Persona ON DiagnosticoPaciente.cedula=Persona.cedula) WHERE
 (Cita.fecha BETWEEN '2020/05/10' AND '2020/05/16') AND
 Persona.nombre='Manuel' AND
 DiagnosticoPaciente.nombre='' AND
 DiagnosticoPaciente.nivel='';
 


CREATE TABLE TratamientoPaciente(
   cedula int,
   nombre varchar (100), 
   dosis varchar (50),
   FOREIGN KEY (cedula) REFERENCES Persona(cedula) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (nombre) REFERENCES Tratamiento(nombre) ON DELETE CASCADE ON UPDATE CASCADE

 );

 CREATE TABLE DiagnosticoTratamiento(
   nombreDiagnostico varchar (100), 
   nombreTratamiento varchar (100),
   FOREIGN KEY (nombreDiagnostico) REFERENCES Diagnostico(nombre) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (nombreTratamiento) REFERENCES Tratamiento(nombre)  ON DELETE CASCADE ON UPDATE CASCADE
 );
 
 CREATE TABLE DiagnosticoCita(
   nombre varchar (100), 
   identificador int,
   FOREIGN KEY (nombre) REFERENCES Diagnostico(nombre) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (identificador) REFERENCES Cita(identificador) ON DELETE CASCADE ON UPDATE CASCADE
 );
 
 
 CREATE TABLE Hospitalizacion(
  codigoCentro int,
  cedula int,
  diagnostico varchar(50),
  fechaInicio Date,
  fechaFinal Date,
  idArea int,
  identificacion int,
  FOREIGN KEY (codigoCentro) REFERENCES CentroDeAtencion(codigoCentro) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (idArea) REFERENCES AreaDeTrabajo(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (cedula) REFERENCES Persona(cedula) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (identificacion) REFERENCES Funcionario(identificacion) ON DELETE CASCADE ON UPDATE CASCADE
);


 
 -- Tabla intermedia -- 
CREATE TABLE CentoDeAtencionAreaDeTrabajo(
  codigoCentro int,
  idArea int, 
  FOREIGN KEY (codigoCentro) REFERENCES CentroDeAtencion(codigoCentro) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (idArea) REFERENCES AreaDeTrabajo(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla intermedia --
CREATE TABLE CentoDeAtencionPersona(
  codigoCentro int,
  cedula int, 
  FOREIGN KEY (codigoCentro) REFERENCES CentroDeAtencion(codigoCentro) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (cedula) REFERENCES Persona(cedula) ON DELETE CASCADE ON UPDATE CASCADE
);