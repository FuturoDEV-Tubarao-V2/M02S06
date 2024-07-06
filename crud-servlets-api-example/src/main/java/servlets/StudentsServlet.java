package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.Database;
import model.Student;

@WebServlet("/student")
public class StudentsServlet extends HttpServlet {

	private static final long serialVersionUID = 8063506165683943630L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Recupera os estudantes da base de dados fictícia
		List<Student> students = Database.getStudents();
		
//		Instancia a biblioteca GSON para serialização e desserializção de objetos
		Gson gson = new Gson();
		
//		Converte a lista de estudantes para uma String Json (serialização)
		String studentsAsJson = gson.toJson(students);
		
		
//		Realiza a negociação de conteúdo, informando que o retorno será um JSON
		response.setContentType("application/json");
		
//		Recupera o objeto de escrita dentro de response
		PrintWriter writer = response.getWriter();
		
//		Checa se a listagem de estudantes está vazia, para que seja retornado um status 204 adequado
		if (students.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			writer.print(studentsAsJson);
			return;
		}
		
//		Escreve o JSON na resposta para retornar
		writer.print(studentsAsJson);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		Recupera o estudante como JSON e o converte em uma classe
		Student student = this.convertJsonToClass(request);
		
//		Adiciona-o no banco e retorna o status HTTP 201
		Database.add(student);
		response.setStatus(HttpServletResponse.SC_CREATED);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		Recupera a matrícula e converte o JSON em um estudante
		Integer registration = Integer.valueOf(req.getParameter("registration"));
		Student studentUpdated = this.convertJsonToClass(req);

//		Recupera as informações atuais do estudante e atualiza as informações
		Student currentStudent = Database.getStudent(registration);
		currentStudent.setName(studentUpdated.getName());
		currentStudent.setEmail(studentUpdated.getEmail());
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Recupera o estudante
		Integer registration = Integer.valueOf(req.getParameter("registration"));
		Student student = Database.getStudent(registration);
		
//		Caso não encontre o estudante, retorna o 404, com uma mensagem
		if (student == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			resp.getWriter().write(String.format("O estudante com identificador %d não foi encontrado", registration));
			return;
		}

//		Caso encontre, remove o estudante e retorna o status 200
		Database.remove(registration);
		resp.setStatus(HttpServletResponse.SC_OK);
	}
	
//	Método para receber o estudante e convertê-lo em um objeto
	private Student convertJsonToClass(HttpServletRequest req) throws IOException {
		Gson gson = new Gson();	
		BufferedReader reader = req.getReader();
		String studentAsJson = reader.lines().collect(Collectors.joining());
		return gson.fromJson(studentAsJson, Student.class);
	}
	
}
